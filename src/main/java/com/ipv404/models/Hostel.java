package com.ipv404.models;

public class Hostel {

    private String hostelId;
    private String hostelName;
    private String address;

    public String getHostelId() {
        return hostelId;
    }

    public void setHostelId(String hostelId) {
        this.hostelId = hostelId;
    }

    public Hostel(String hostelId, String hostelName, String address) {
        this.hostelId = hostelId;
        this.hostelName = hostelName;
        this.address = address;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
