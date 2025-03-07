package com.ipv404.repositories;

import com.ipv404.models.Room;
import com.ipv404.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

        private final DatabaseUtil dbUtil;

        public RoomRepository() {
            this.dbUtil = DatabaseUtil.getInstance();
        }

        public Room save(Room room){
            String sql = "INSERT INTO room (room_id,hostel_id, room_number, capacity) VALUES (?, ?, ?,?)";

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
    private Room mapResultSetToRoom(ResultSet rs) throws SQLException {
        Room room = new Room(
                rs.getString("room_id"),
                rs.getString("hostel_id"),
                rs.getInt("room_number"),
                rs.getInt("capacity")

        );
        return room;
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

    public Room findById(Long id) {
        String sql = "SELECT * FROM student WHERE room_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToRoom(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding student", e);
        }
    }

    public List<Room> findAll() {
        String sql = "SELECT * FROM room";
        List<Room> rooms = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                rooms.add(mapResultSetToRoom(rs));
            }
            return rooms;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving rooms", e);
        }
    }

    public Room findByRoomId(String roomId) {
        String sql = "SELECT * FROM room WHERE room_id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, roomId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToRoom(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding room by Room ID", e);
        }
    }
}



