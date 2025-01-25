package com.ipv404;

import com.ipv404.menus.MainMenu;
import com.ipv404.utils.ConfigManager;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting " + ConfigManager.getInstance().getProperty("app.name") +
                " v" + ConfigManager.getInstance().getProperty("app.version"));

        try {
            // Start the application
            MainMenu mainMenu = new MainMenu();
            mainMenu.display();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}