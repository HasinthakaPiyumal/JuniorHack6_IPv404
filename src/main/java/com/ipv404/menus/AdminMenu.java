package com.ipv404.menus;

import com.ipv404.models.User;
import com.ipv404.exceptions.UserNotFoundException;
import com.ipv404.services.UserService;
import java.util.List;

public class AdminMenu extends BaseMenu {
    
    public AdminMenu(UserService userService) {
        super(userService);
    }

    @Override
    public void display() {
        System.out.println("\n=== Fuel Quota Management System - Admin Menu ===");
        System.out.println("Logged in as: " + userService.getCurrentUser().getName() + " (ADMIN)");
        System.out.println("1. Create New User");
        System.out.println("2. Find User by ID");
        System.out.println("3. View All Users");
        System.out.println("4. Delete User");
        System.out.println("5. View My Profile");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.print("Choose an option (1-7): ");

        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1" -> createUser();
                case "2" -> findUser();
                case "3" -> viewAllUsers();
                case "4" -> deleteUser();
                case "5" -> viewMyProfile();
                case "6" -> {
                    userService.logout();
                    System.out.println("Logged out successfully.");
                }
                case "7" -> exit();
                default -> System.out.println("Invalid option. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewMyProfile() {
        MenuUtils.viewMyProfile(userService.getCurrentUser());
    }

    private void createUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.print("Enter user email: ");
        String email = scanner.nextLine();

        System.out.print("Enter user password: ");
        String password = scanner.nextLine();

        try {
            User newUser = new User(name, email, password);
            User createdUser = userService.createUser(newUser);
            System.out.println("User created successfully with ID: " + createdUser.getId());
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    private void findUser() {
        System.out.print("Enter user ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            User user = userService.getUserById(id);
            MenuUtils.viewMyProfile(user);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            userService.deleteUser(id);
            System.out.println("User deleted successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("No users found in the system.");
                return;
            }

            System.out.println("\nAll Users:");
            System.out.println("----------------------------------------");
            System.out.printf("%-5s | %-20s | %-20s%n", "ID", "Name", "Email");
            System.out.println("----------------------------------------");
            
            for (User user : users) {
                System.out.printf("%-5d | %-20s | %-20s%n", 
                    user.getId(), 
                    user.getName(), 
                    user.getEmail());
            }
            System.out.println("----------------------------------------");
            System.out.println("Total users: " + users.size());
        } catch (Exception e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }
} 