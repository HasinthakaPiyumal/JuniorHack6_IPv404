package com.ipv404.models;

public enum UserRole {
    ADMIN,
    USER;

    public static UserRole fromString(String role) {
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return USER; // Default role
        }
    }
} 