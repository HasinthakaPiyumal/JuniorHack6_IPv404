package com.ipv404.models;

public class Allocation {
    private int allocationId;
    private String studentId;
    private String hostelId;
    private String roomId;
    private String date;

    public Allocation( String studentId, String hostelId, String roomId) {
        this.studentId = studentId;
        this.hostelId = hostelId;
        this.roomId = roomId;
    }

    public int getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getHostelId() {
        return hostelId;
    }

    public void setHostelId(String hostelId) {
        this.hostelId = hostelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }
}
