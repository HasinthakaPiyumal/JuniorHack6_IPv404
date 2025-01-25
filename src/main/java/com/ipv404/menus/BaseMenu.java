package com.ipv404.menus;

import com.ipv404.services.UserService;
import com.ipv404.utils.UserSession;
import java.util.Scanner;

public abstract class BaseMenu {
    protected final UserService userService;
    protected final UserSession userSession;
    protected final Scanner scanner;

    public BaseMenu(UserService userService) {
        this.userService = userService;
        this.userSession = UserSession.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public abstract void display();

    protected void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
} 