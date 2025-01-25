package com.ipv404.menus;

import java.util.List;
import java.util.Scanner;

import javax.swing.text.View;

import com.ipv404.models.Hostel;
import com.ipv404.models.Student;
import com.ipv404.services.HostelServices;

public class HostelMenu {
    private final Scanner scanner;
    private final HostelServices hostelService;

    public HostelMenu() {
        this.scanner = new Scanner(System.in);
        this.hostelService = new HostelServices();
    }

    public void display() {
        System.out.println("Please enter the number of the action you want to proceed ..");
        System.out.println("[1] Add a Hostel");
        System.out.println("[2] Update Hostel Information");
        System.out.println("[3] View Hostel Details");
        System.out.println("[4] Rooms Management");
        System.out.println("[0] Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addHostel();
                break;
            case 2:
                updateHostel();
                break;
            case 3:
                viewHostels();
                break;
            case 4:
                break;
            case 0:
                break;
            default:
                MenuUtils.invalidInput();
                display();
        }
    }
     private void addHostel() {
        System.out.println("Please enter the student details ..");
        String hostelId = MenuUtils.requiredInput(scanner, "Hostel ID: ");
        String hostelName = MenuUtils.requiredInput(scanner, "Hostel Name: ");
        String hostelAddress = MenuUtils.requiredInput(scanner, "Hostel Address: ");

        Hostel hostel = new Hostel(hostelId, hostelName, hostelAddress);
        try {
            hostelService.createHostel(hostel);
            System.out.println("Hostel added successfully");
        } catch (Exception e) {
            System.out.println("Error adding hostel: " + e.getMessage());
        }
        display();
    }

    private void updateHostel() {
        System.out.println("Please enter the hostel ID you want to update ..");
        String hostelId = MenuUtils.requiredInput(scanner, "Hostel ID: ");
        System.out.println("Please enter the new hostel details ..");
        String hostelName = MenuUtils.requiredInput(scanner, "Hostel Name: ");
        String hostelAddress = MenuUtils.requiredInput(scanner, "Hostel Address: ");

        Hostel hostel = new Hostel(hostelId, hostelName, hostelAddress);
        try {
            hostelService.updateHostel(hostel);
            System.out.println("Hostel updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating hostel: " + e.getMessage());
        }
    }

    private void viewHostels() {
        List<Hostel> hostels = hostelService.getAllHostels();
        System.out.printf("%-15s %-30s %-10s\n", "ID", "Name", "Address");
        for (Hostel hostel : hostels) {
            System.out.printf("%-15s %-30s %-10s\n", hostel.getHostelId(), hostel.getHostelName(), hostel.getAddress());
        }
        display();
    }
}

