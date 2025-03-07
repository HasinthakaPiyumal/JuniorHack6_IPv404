package com.ipv404.menus;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            MenuUtils.mainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    HostelMenu hostelMenu = new HostelMenu();
                    hostelMenu.display();
                    break;
                case 2:
                    StudentMenu studentMenu = new StudentMenu();
                    studentMenu.display();
                    break;
                case 3:
                    StudentAllocationMenu allocationMenu = new StudentAllocationMenu();
                    allocationMenu.display();
                    break;
                case 0:
                    System.exit(0);
                default:
                    MenuUtils.invalidInput();
                    display();
            }
        }
    }
}