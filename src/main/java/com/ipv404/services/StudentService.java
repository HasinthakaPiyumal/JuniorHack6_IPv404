package com.ipv404.services;

import com.ipv404.models.Student;
import com.ipv404.repositories.StudentRepository;
import com.ipv404.exceptions.NotFoundException;
import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) throws NotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new NotFoundException("Student not found with id: " + id);
        }
        return student;
    }

    public void deleteStudent(String id) throws NotFoundException {
        if (!studentRepository.delete(id)) {
            throw new NotFoundException("Student not found with id: " + id);
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void updateStudent(Student student) {
        studentRepository.update(student);
    }
} 