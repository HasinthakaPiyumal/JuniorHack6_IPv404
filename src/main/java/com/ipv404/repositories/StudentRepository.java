package com.ipv404.repositories;

import com.ipv404.models.Student;
import com.ipv404.utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final DatabaseUtil dbUtil;

    public StudentRepository() {
        this.dbUtil = DatabaseUtil.getInstance();
    }

    public Student save(Student student) {
        String sql = "INSERT INTO student (student_id, name, birthday, department, faculty, academic_year) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getBirthDay());
            pstmt.setString(4, student.getDepartment());
            pstmt.setString(5, student.getFaculty());
            pstmt.setString(6, student.getAcademicYear());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setStudentId(generatedKeys.getString("student_id"));
                }
            }
            return student;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Error saving student", e);
        }
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student(
            rs.getString("student_id"),
            rs.getString("name"),
            rs.getString("birthday"),
            rs.getString("academic_year"),
            rs.getString("department"),
            rs.getString("faculty")
        );
        return student;
    }

    public Student findById(Long id) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding student", e);
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting student", e);
        }
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString("student_id"));
                students.add(mapResultSetToStudent(rs));
            }
            return students;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Error retrieving students", e);
        }
    }

    public Student findByStudentId(String studentId) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding student by Student ID", e);
        }
    }

    public void update(Student student) {
        String sql = "UPDATE student SET name = ?, birthday = ?, department = ?, faculty = ?, academic_year = ? WHERE student_id = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getBirthDay());
            pstmt.setString(3, student.getDepartment());
            pstmt.setString(4, student.getFaculty());
            pstmt.setString(5, student.getAcademicYear());
            pstmt.setString(6, student.getStudentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student", e);
        }
    }
}