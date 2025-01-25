package com.ipv404.models;

public class Student {
    private String studentId;
    private String name;
    private String birthDay;
    private String department;
    private String academicYear;
    private String faculty;
    private String room;
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public Student(String studentId, String name, String birthDay ,String academicYear,
             String department,String faculty) {
        this.studentId = studentId;
        this.name = name;
        this.birthDay = birthDay;
        this.department = department;
        this.academicYear = academicYear;
        this.faculty = faculty;
    }

    
} 