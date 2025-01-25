package com.ipv404.menus;

import com.ipv404.models.User;
import com.ipv404.exceptions.UserNotFoundException;
import com.ipv404.services.UserService;

public class LoginMenu extends BaseMenu {
    
    public LoginMenu(UserService userService) {
        super(userService);
    }

    @Override
    public void display() {
        System.out.println("\n=== Fuel Quota Management System - Login ===");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Choose an option (1-2): ");

        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    exit();
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.login(email, password);
            System.out.println("Login successful! Welcome, " + user.getName());
        } catch (UserNotFoundException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
} 