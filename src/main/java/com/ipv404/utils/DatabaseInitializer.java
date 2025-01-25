package com.ipv404.utils;

import com.ipv404.models.User;
import com.ipv404.models.UserRole;
import com.ipv404.repositories.UserRepository;

public class DatabaseInitializer {
    private final ConfigManager config;
    private final UserRepository userRepository;

    public DatabaseInitializer() {
        this.config = ConfigManager.getInstance();
        this.userRepository = new UserRepository();
    }

    public void initialize() {
        createAdminUser();
    }

    private void createAdminUser() {
        try {
            User adminUser = new User(
                config.getProperty("admin.name"),
                config.getProperty("admin.email"),
                config.getProperty("admin.password")
            );
            adminUser.setRole(UserRole.ADMIN);
            userRepository.save(adminUser);
        } catch (Exception e) {
            // Admin user might already exist, ignore the error
            System.out.println("Note: Admin user already exists");
        }
    }
} 