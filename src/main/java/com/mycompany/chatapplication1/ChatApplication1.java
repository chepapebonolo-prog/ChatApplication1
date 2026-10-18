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
        if (isLoggedIn) {
            quickChat(scanner);
        }
    }
    // Runs the QuickChat menu after a successful login

    private static void quickChat(Scanner scanner) {
        System.out.println("\nWelcome to QuickChat.");
        // Asks how many messages the user wants to enter
        System.out.print("How many messages do you want to enter? ");
        int numMessages = Integer.parseInt(scanner.nextLine());
        int messagesEntered = 0;
        boolean chatting = true;
        while (chatting) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Select option: ");
            String option = scanner.nextLine();
            if (option.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (option.equals("3")) {
                chatting = false;
            } else if (option.equals("1")) {
                // Collects a message and shows its generated ID
                if (messagesEntered < numMessages) {
                    Message message = enterMessage(scanner, messagesEntered);
                    message.createMessageHash();
                    System.out.println("Message ID generated: " + message.getMessageID());
                    // Lets the user send, disregard or store the message
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    System.out.print("Select option: ");
                    int sendChoice = Integer.parseInt(scanner.nextLine());
                    System.out.println(message.sentMessage(sendChoice));
                    if (sendChoice == 1) {
                        message.addToSent();
                     // Shows the full details of the sent message
                        System.out.println(message.messageDetails());
                    } else if (sendChoice == 2) {
                        scanner.nextLine();
                    } else if (sendChoice == 3) {
                        message.storeMessage();
                    }
                    messagesEntered = messagesEntered + 1;
                } else {
                    System.out.println("You have entered all your messages.");
                }
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
    // Collects one message and repeats until the recipient and text are valid

    private static Message enterMessage(Scanner scanner, int messageNumber) {
        while (true) {
            System.out.print("Recipient cell number: ");
            String recipient = scanner.nextLine();
            System.out.print("Message: ");
            String text = scanner.nextLine();
            Message message = new Message(messageNumber, recipient, text);
            String cellResult = message.checkRecipientCell();
            String lengthResult = message.checkMessageLength();
            System.out.println(cellResult);
            System.out.println(lengthResult);
            if (cellResult.equals("Cell phone number successfully captured.")
                    && lengthResult.equals("Message ready to send.")) {
                return message;
            }
        }
    }
}
