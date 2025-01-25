package com.ipv404.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static DatabaseUtil instance;
    private final ConfigManager config;

    private DatabaseUtil() {
        this.config = ConfigManager.getInstance();
        try {
            Class.forName(config.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public static DatabaseUtil getInstance() {
        if (instance == null) {
            instance = new DatabaseUtil();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            config.getProperty("db.url"),
            config.getProperty("db.username"),
            config.getProperty("db.password")
        );
    }
} 