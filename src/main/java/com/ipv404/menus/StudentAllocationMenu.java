package com.ipv404.menus;

import java.util.Scanner;

public class StudentAllocationMenu {
    private final Scanner scanner;

    public StudentAllocationMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        System.out.println("Please enter the number of the action you want to proceed ..");
        System.out.println("[1] Allocate a room to a student");
        System.out.println("[2] Delete an allocation");
        System.out.println("[3] View an allocation");
        System.out.println("[0] Back");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 0:
                break;
            default:
                MenuUtils.invalidInput();
                display();
        }
    }

    private void addHostel() {
        System.out.println("Please enter the new hostel details ..");
        String hostelName = MenuUtils.requiredInput(scanner, "Hostel Name: ");
        String hostelAddress = MenuUtils.requiredInput(scanner, "Hostel Address: ");
    }
    
}

