package com.ipv404.menus;

import java.util.Scanner;

public class MenuUtils {
    public static void mainMenu() {
        System.out.println("Please enter the number of the action you want to proceed ..");
        System.out.println("[1] Manage Hostels");
        System.out.println("[2] Manage Students");
        System.out.println("[3] Manage Student Allocations");
        System.out.println("[0] Exit");
    }

    public static void invalidInput() {
        System.out.println("Invalid input. Please try again.");
    }

    public static String requiredInput(Scanner scanner, String message) {
        System.out.println(message);
        String input = scanner.nextLine();
        if(input.isEmpty()) {
            System.out.println("Input is required");
            System.out.println(message);
            requiredInput(scanner, message);
        }
        else {
            return input;
        }
        return null;
    }

    public static String requiredDateInput(Scanner scanner, String message) {
        System.out.println("Please enter the date in the format yyyy/mm/dd");
        System.out.println(message);
        String date = scanner.nextLine();
        if(date.isEmpty()) {
            System.out.println("Input is required");
            requiredDateInput(scanner, message);
        }
        else if(!date.matches("\\d{4}/\\d{2}/\\d{2}")) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy/mm/dd");
            requiredDateInput(scanner, message);
        }
        else {
            return date;
        }
        return null;    
    }
} 