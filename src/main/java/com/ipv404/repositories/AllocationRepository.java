package com.ipv404.repositories;

import com.ipv404.models.Allocation;
import com.ipv404.models.Hostel;
import com.ipv404.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllocationRepository {

    private final DatabaseUtil dbUtil;

    public AllocationRepository(){
        this.dbUtil = DatabaseUtil.getInstance();
    }

    public Allocation save(Allocation allocation){
        String sql = "INSERT INTO allocation (student_id, room_id, hostel_id) VALUES (?, ?, ?)";

        try{
            try (Connection conn = dbUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, allocation.getStudentId());
                pstmt.setString(2, allocation.getRoomId());
                pstmt.setString(3, allocation.getHostelId());
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        allocation.setAllocationId(generatedKeys.getInt("allocation_id"));
                    }
                }
                return allocation;
            }
        }catch (SQLException e) {
            throw new RuntimeException("Error finding allocation", e);
        }
    }

    private Allocation mapResultSetToAllocation(ResultSet rs) throws SQLException {
        Allocation allocation = new Allocation(
                rs.getString("student_id"),
                rs.getString("hostel_id"),
                rs.getString("room_id")

        );
        return allocation;
    }

    public Allocation findById(Long id) {
        String sql = "SELECT * FROM allocation WHERE allocation_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToAllocation(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding Allocation", e);
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM allocation WHERE allocation_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting allocation", e);
        }
    }

    public List<Allocation> findAll() {
        String sql = "SELECT * FROM allocation";
        List<Allocation> allocations = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                allocations.add(mapResultSetToAllocation(rs));
            }
            return allocations;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving allocations", e);
        }
    }
}
