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
            System.out.println("4) Stored Messages");
            System.out.print("Select option: ");
            String option = scanner.nextLine();
            if (option.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (option.equals("3")) {
                chatting = false;
            } else if (option.equals("4")) {
                storedMessagesMenu(scanner);
            } else if (option.equals("1")) {
                // Collects a message and shows its generated ID
                if (messagesEntered < numMessages) {
                    Message message = enterMessage(scanner, messagesEntered);
                    message.createMessageHash();
                    message.addToIdAndHashLists();
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
                        message.addToSentList();
                        // Shows the full details of the sent message
                        System.out.println(message.messageDetails());
                    } else if (sendChoice == 2) {
                        message.addToDisregardedList();
                        scanner.nextLine();
                    } else if (sendChoice == 3) {
                        message.storeMessage();
                    }
                    messagesEntered = messagesEntered + 1;
                    // Shows the total sent once all the messages have been entered
                    if (messagesEntered == numMessages) {
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    }
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
    // Shows the Stored Messages menu and runs the chosen action

    private static void storedMessagesMenu(Scanner scanner) {
        Message.loadStoredMessages();
        boolean viewing = true;
        while (viewing) {
            System.out.println("\n--- Stored Messages ---");
            System.out.println("1) Display the sender and recipient of all stored messages");
            System.out.println("2) Display the longest message");
            System.out.println("3) Search for a message ID");
            System.out.println("4) Search for all messages to a recipient");
            System.out.println("5) Delete a message using its hash");
            System.out.println("6) Display the stored messages report");
            System.out.println("7) Back");
            System.out.print("Select option: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Sender: You");
                System.out.println(Message.storedRecipients());
            } else if (choice.equals("2")) {
                System.out.println(Message.longestMessage());
            } else if (choice.equals("3")) {
                System.out.print("Message ID: ");
                String id = scanner.nextLine();
                System.out.println("Recipient: " + Message.searchRecipientByID(id));
                System.out.println("Message: " + Message.searchByID(id));
            } else if (choice.equals("4")) {
                System.out.print("Recipient: ");
                System.out.println(Message.searchByRecipient(scanner.nextLine()));
            } else if (choice.equals("5")) {
                System.out.print("Message hash: ");
                System.out.println(Message.deleteByHash(scanner.nextLine()));
            } else if (choice.equals("6")) {
                System.out.println(Message.storedReport());
            } else if (choice.equals("7")) {
                viewing = false;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
