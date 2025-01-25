package com.ipv404.repositories;

import com.ipv404.models.Hostel;
import com.ipv404.models.Student;
import com.ipv404.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostelRepository {

    private final DatabaseUtil dbUtil;

    public HostelRepository() {
        this.dbUtil = DatabaseUtil.getInstance();
    }

    public Hostel save(Hostel hostel){
        String sql = "INSERT INTO hostel (hostel_id, name, address) VALUES (?, ?, ?)";

        try{
            try (Connection conn = dbUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, hostel.getHostelId());
                pstmt.setString(2, hostel.getHostelName());
                pstmt.setString(3, hostel.getAddress());
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        hostel.setHostelId(generatedKeys.getString("student_id"));
                    }
                }
                return hostel;
        }
        }catch (SQLException e) {
            throw new RuntimeException("Error finding Hostel", e);
        }
    }

    private Hostel mapResultSetToHostel(ResultSet rs) throws SQLException {
        Hostel hostel = new Hostel(
                rs.getString("hostel_id"),
                rs.getString("name"),
                rs.getString("address")

        );
        return hostel;
    }

    public Hostel findById(Long id) {
        String sql = "SELECT * FROM hostel WHERE hostel_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToHostel(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding Hostel", e);
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM hostel WHERE hostel_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting hostel", e);
        }
    }

    public List<Hostel> findAll() {
        String sql = "SELECT * FROM hostel";
        List<Hostel> hostels = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                hostels.add(mapResultSetToHostel(rs));
            }
            return hostels;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving hostels", e);
        }
    }

    public boolean update(Hostel hostel) {
        String sql = "UPDATE hostel SET name = ?, address = ? WHERE hostel_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hostel.getHostelName());
            pstmt.setString(2, hostel.getAddress());
            pstmt.setString(3, hostel.getHostelId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating hostel", e);
        }
    }



}
