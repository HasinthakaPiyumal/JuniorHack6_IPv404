package com.ipv404.menus;

import java.util.List;
import java.util.Scanner;

import com.ipv404.models.Hostel;
import com.ipv404.services.AllocationService;
import com.ipv404.models.Allocation;

public class StudentAllocationMenu {
    private final Scanner scanner;
    private final AllocationService allocationService;

    public StudentAllocationMenu() {
        this.scanner = new Scanner(System.in);
        this.allocationService = new AllocationService();
    }

    public void display() {
        System.out.println("Please enter the number of the action you want to proceed ..");
        System.out.println("[1] Allocate a room to a student");
        System.out.println("[2] Delete an allocation");
        System.out.println("[3] View an allocation");
        System.out.println("[0] Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                allocateRoom();
                break;
            case 2:
                deleteAllocation();
                break;
            case 3:
                viewAllocations();
                break;
            case 0:
                break;
            default:
                MenuUtils.invalidInput();
                display();
        }
    }

    private void allocateRoom() {
        System.out.println("Please enter the allocation details ..");
        String studentId = MenuUtils.requiredInput(scanner, "Student ID: ");
        String hostelId = MenuUtils.requiredInput(scanner, "Hostel ID: ");
        String roomNumber = MenuUtils.requiredInput(scanner, "Room Number: ");

        Allocation allocation = new Allocation(studentId, hostelId, roomNumber);
        try {
            allocationService.createAllocation(allocation);
            System.out.println("Room allocated successfully");
        } catch (Exception e) {
            System.out.println("Error allocating room: " + e.getMessage());
        }
        display();
    }

    private void deleteAllocation() {
        System.out.println("Please enter the allocation details to delete ..");
        String allocationId = MenuUtils.requiredInput(scanner, "Allocation ID: ");

        try {

            allocationService.deleteAllocation(allocationId);
            System.out.println("Allocation deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting allocation: " + e.getMessage());
        }
        display();
    }

    private void viewAllocation() {
        List<Allocation> allocations = allocationService.getAllAllocations();
        System.out.printf("%-15s %-15s %-10s\n", "Student ID", "Hostel ID", "Room ID");
        for (Allocation allocation : allocations) {
            System.out.printf("%-15s %-15s %-10s\n", 
                allocation.getStudentId(), 
                allocation.getHostelId(), 
                allocation.getRoomId());
        }
        display();
    }

        // private void addHostel() {
        //     System.out.println("Please enter the student details ..");
        //     String hostelId = MenuUtils.requiredInput(scanner, "Hostel ID: ");
        //     String hostelName = MenuUtils.requiredInput(scanner, "Hostel Name: ");
        //     String hostelAddress = MenuUtils.requiredInput(scanner, "Hostel Address: ");

        //     Hostel hostel = new Hostel(hostelId, hostelName, hostelAddress);
        //     try {
        //         allocationService.createAllocation(allocation);
        //         System.out.println("Allocation added successfully");
        //     } catch (Exception e) {
        //         System.out.println("Error adding allocation: " + e.getMessage());
        //     }
        //     display();
        // }

        // private void updateAllocation() {
        //     System.out.println("Please enter the hostel ID you want to update ..");
        //     String hostelId = MenuUtils.requiredInput(scanner, "Hostel ID: ");
        //     System.out.println("Please enter the new hostel details ..");
        //     String studentId = MenuUtils.requiredInput(scanner, "Student ID: ");
        //     String roomId = MenuUtils.requiredInput(scanner, "Room ID: ");

        //     Allocation allocation = new Allocation(studentId, hostelId, roomId);
        //     try {
        //         allocationService.u(allocation);
        //         System.out.println("Allocation updated successfully");
        //     } catch (Exception e) {
        //         System.out.println("Error updating allocation: " + e.getMessage());
        //     }
        // }

        private void viewAllocations() {
            List<Allocation> allocations = allocationService.getAllAllocations();
            System.out.printf("%-15s %-30s %-10s\n", "ID", "Student ID", "Hostel ID", "Room ID");
            for (Allocation allocation : allocations) {
                System.out.printf("%-15s %-30s %-10s\n", allocation.getAllocationId(), allocation.getStudentId(), allocation.getHostelId(), allocation.getRoomId());
            }
            display();
        }
    
}

