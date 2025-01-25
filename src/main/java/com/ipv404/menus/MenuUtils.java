package com.ipv404.menus;

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
} 