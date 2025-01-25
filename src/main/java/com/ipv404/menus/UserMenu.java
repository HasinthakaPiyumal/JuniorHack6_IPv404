package com.ipv404.menus;

import com.ipv404.services.UserService;

public class UserMenu extends BaseMenu {
    
    public UserMenu(UserService userService) {
        super(userService);
    }

    @Override
    public void display() {
        System.out.println("\n=== Fuel Quota Management System - User Menu ===");
        System.out.println("Logged in as: " + userService.getCurrentUser().getName() + " (USER)");
        System.out.println("1. View My Profile");
        System.out.println("2. Logout");
        System.out.println("3. Exit");
        System.out.print("Choose an option (1-3): ");

        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1" -> viewMyProfile();
                case "2" -> {
                    userService.logout();
                    System.out.println("Logged out successfully.");
                }
                case "3" -> exit();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewMyProfile() {
        MenuUtils.viewMyProfile(userService.getCurrentUser());
    }
} 