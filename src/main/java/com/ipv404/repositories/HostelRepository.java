package com.ipv404.repositories;

import com.ipv404.models.Hostel;
import com.ipv404.utils.DatabaseUtil;

import java.sql.*;

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

    public boolean delete(Long id) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting student", e);
        }
    }
}
