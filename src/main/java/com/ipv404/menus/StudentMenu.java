package com.ipv404.menus;

import java.util.List;
import java.util.Scanner;

import com.ipv404.models.Student;
import com.ipv404.services.StudentService;

public class StudentMenu {
    private final Scanner scanner;
    private final StudentService studentService;

    public StudentMenu() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
    }

    public void display() {
        System.out.println("Please enter the number of the action you want to proceed ..");
        System.out.println("[1] Add Student");
        System.out.println("[2] Remove Student");
        System.out.println("[3] Update Student");
        System.out.println("[4] View Students");
        System.out.println("[0] Back");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                removeStudent();
                break;
            case 3:
                updateStudent();
                break;
            case 4:
                viewStudents();
                break;
            case 0:
                MainMenu mainMenu = new MainMenu();
                mainMenu.display();
                break;
            default:
                MenuUtils.invalidInput();
                display();
        }
    }

    private void addStudent() {
        System.out.println("Please enter the student details ..");
        String studentId = MenuUtils.requiredInput(scanner, "Student ID: ");
        String studentName = MenuUtils.requiredInput(scanner, "Student Name: ");
        String studentBirthDay = MenuUtils.requiredDateInput(scanner, "Student Birth Day: ");
        String studentDepartment = MenuUtils.requiredInput(scanner, "Student Department: ");
        String studentFaculty = MenuUtils.requiredInput(scanner, "Student Faculty: ");
        String studentAcademicYear = MenuUtils.requiredInput(scanner, "Student Academic Year: ");

        Student student = new Student(studentId, studentName, studentBirthDay, studentDepartment, studentFaculty, studentAcademicYear);
        try {
            studentService.createStudent(student);
            System.out.println("Student added successfully");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
        display();
    }

    private void removeStudent() {
        System.out.println("Please enter the student ID you want to remove ..");
        String studentId = scanner.nextLine();
        try {
            studentService.deleteStudent(studentId);
            System.out.println("Student removed successfully");
        } catch (Exception e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
        display();
    }

    private void updateStudent() {
        System.out.println("Please enter the student ID you want to update ..");
        String studentId = MenuUtils.requiredInput(scanner, "Student ID: ");
        System.out.println("Please enter the new student details ..");
        String studentName = MenuUtils.requiredInput(scanner, "Student Name: ");
        String studentBirthDay = MenuUtils.requiredDateInput(scanner, "Student Birth Day: ");
        String studentDepartment = MenuUtils.requiredInput(scanner, "Student Department: ");
        String studentFaculty = MenuUtils.requiredInput(scanner, "Student Faculty: ");
        String studentAcademicYear = MenuUtils.requiredInput(scanner, "Student Academic Year: ");

        Student student = new Student(studentId, studentName, studentBirthDay, studentDepartment, studentFaculty, studentAcademicYear);
        try {
            studentService.updateStudent(student);
            System.out.println("Student updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private void viewStudents() {
        List<Student> students = studentService.getAllStudents();
        System.out.printf("%-15s %-30s %-10s %-20s %-20s %-10s\n", "ID", "Name", "Birth Day", "Department", "Faculty", "Academic Year");
        for (Student student : students) {
            System.out.printf("%-15s %-30s %-10s %-20s %-20s %-10s\n", student.getStudentId(), student.getName(), student.getBirthDay(), student.getDepartment(), student.getFaculty(), student.getAcademicYear());
        }
        display();
    }

}
