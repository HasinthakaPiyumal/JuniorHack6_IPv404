package com.ipv404.models;

public class Room {

    private String roomId;
    private String hostelId;
    private int roomNumber;
    private int capacity;

    public Room(String roomId, String hostelId, int roomNumber, int capacity) {
        this.roomId = roomId;
        this.hostelId = hostelId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHostelId() {
        return hostelId;
    }

    public void setHostelId(String hostelId) {
        this.hostelId = hostelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
