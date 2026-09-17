/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapplication1;

/**
 *
 * @author Student
 */
// Display main menu options

import java.util.Scanner;
// Process user menu selection
public class ChatApplication1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        boolean running = true;
        
        while (running) {
            System.out.println("\n========================================");
            System.out.println("        CHAT APPLICATION - MENU         ");
            System.out.println("========================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            
            String choice = scanner.nextLine();
            // Close scanner resource
            switch (choice) {
                case "1":
                    register(scanner, user);
                    break;
                case "2":
                    login(scanner, user);
                    break;
                case "3":
                    System.out.println("\nGoodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        // Collect user registration details
        scanner.close();
    }
    
    private static void register(Scanner scanner, User user) {
        System.out.println("\n--- Register ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        // Handle user login process
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Cell Phone: ");
        String cellPhone = scanner.nextLine();
        
        String result = user.registerUser(username, password, cellPhone);
        System.out.println("\n" + result);
    }
    
    private static void login(Scanner scanner, User user) {
        System.out.println("\n--- Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        boolean isLoggedIn = user.loginUser(username, password);
        String result = user.returnLoginStatus(isLoggedIn);
        System.out.println("\n" + result);
    }
}
