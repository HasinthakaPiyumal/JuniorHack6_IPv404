package com.ipv404.repositories;

import com.ipv404.models.Hostel;
import com.ipv404.models.Room;
import com.ipv404.utils.DatabaseUtil;

import java.sql.*;

    public class RoomRepository {

        private final DatabaseUtil dbUtil;

        public RoomRepository() {
            this.dbUtil = DatabaseUtil.getInstance();
        }

        public Room save(Room room){
            String sql = "INSERT INTO hostel (room_id,hostel_id, room_number, capacity) VALUES (?, ?, ?,?)";

            try{
                try (Connection conn = dbUtil.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, room.getRoomId());
                    pstmt.setString(2, room.getHostelId());
                    pstmt.setInt(3, room.getRoomNumber());
                    pstmt.setInt(4, room.getCapacity());
                    pstmt.executeUpdate();

                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            room.setRoomId(generatedKeys.getString("room_id"));
                        }
                    }
                    return room;
                }
            }catch (SQLException e) {
                throw new RuntimeException("Error finding Hostel", e);
            }
        }

        public boolean delete(Long id) {
            String sql = "DELETE FROM room WHERE room_id = ?";
            try (Connection conn = dbUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setLong(1, id);
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting room", e);
            }
        }
    }


