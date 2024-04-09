package com.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ForumApp {
    private Map<String, User> users = new HashMap<>();
    private User currentUser;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ForumApp forumApp = new ForumApp();
        forumApp.run();
    }

    public void run() {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("Welcome to the Forum!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void showMainMenu() {
        System.out.println("Welcome, " + currentUser.getUsername() + "!");
        System.out.println("1. Logout");
        System.out.println("2. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                currentUser = null;
                break;
            case 2:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void register() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.put(username, newUser);
        System.out.println("User registered successfully.");
    }

    private void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid password.");
        }
    }
}