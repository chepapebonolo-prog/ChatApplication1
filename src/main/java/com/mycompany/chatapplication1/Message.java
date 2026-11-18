/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication1;

import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    // Holds the details of every message sent while the program runs
    private static String sentMessages = "";
    // Counts the messages sent
    private static int totalSent = 0;
    // Adds this message to the sent array

    public void addToSentList() {
        sentList.add(this);
    }

    // Adds this message to the disregarded array
    public void addToDisregardedList() {
        disregardedList.add(this);
    }

    // Records this message's ID and hash in their arrays
    public void addToIdAndHashLists() {
        idList.add(messageID);
        hashList.add(messageHash);
    }
    // Arrays holding the sent, disregarded and stored messages
    private static ArrayList<Message> sentList = new ArrayList<>();
    private static ArrayList<Message> disregardedList = new ArrayList<>();
    private static ArrayList<Message> storedList = new ArrayList<>();
    // Arrays holding every message hash and message ID
    private static ArrayList<String> hashList = new ArrayList<>();
    private static ArrayList<String> idList = new ArrayList<>();

    // Creates a message and generates a random 10-digit ID
    public Message(int messageNumber, String recipient, String messageText) {
        Random random = new Random();
        long id = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(id);
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    // Checks the message ID is no more than ten characters
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }
    // Checks the message is no more than 250 characters

    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }
        int extra = messageText.length() - 250;
        return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
    }

    // Checks the number has an international code and is valid
    public String checkRecipientCell() {
        boolean valid = recipient.startsWith("+") && recipient.length() > 1 && recipient.length() <= 13;
        for (int i = 1; i < recipient.length(); i++) {
            if (!Character.isDigit(recipient.charAt(i))) {
                valid = false;
            }
        }
        if (valid) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    // Builds the hash from the ID, message number and first and last words

    public String createMessageHash() {
        String text = messageText.trim();
        String first = text;
        if (text.indexOf(" ") != -1) {
            first = text.substring(0, text.indexOf(" "));
        }
        String last = text.substring(text.lastIndexOf(" ") + 1);
        first = cleanWord(first);
        last = cleanWord(last);
        messageHash = (messageID.substring(0, 2) + ":" + messageNumber + ":" + first + last).toUpperCase();
        return messageHash;
    }
// Removes punctuation from a word

    private String cleanWord(String word) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetterOrDigit(word.charAt(i))) {
                result = result + word.charAt(i);
            }
        }
        return result;
    }
    // Returns the result of the send, disregard or store choice

    public String sentMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        }
        if (choice == 2) {
            return "Press 0 to delete the message.";
        }
        if (choice == 3) {
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }
    // Adds this message to the list of sent messages

    public void addToSent() {
        sentMessages = sentMessages + "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageText + "\n\n";
        totalSent = totalSent + 1;
    }
    // Returns the total number of messages sent

    public static int returnTotalMessages() {
        return totalSent;
    }
    // Returns the details of all sent messages

    public static String printMessages() {
        return sentMessages;
    }
    // Saves this message to a JSON file

    public void storeMessage() {
        String safeText = messageText.replace("\"", "\\\"");
        try {
            FileWriter writer = new FileWriter("messages.json", true);
            writer.write("{\"messageID\": \"" + messageID + "\", \"messageHash\": \"" + messageHash
                    + "\", \"recipient\": \"" + recipient + "\", \"message\": \"" + safeText + "\"}\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not store the message.");
        }
    }

    // Sets the hash from stored data
    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }

    // Reads messages.json and fills the stored array
    public static void loadStoredMessages() {
        storedList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("messages.json"));
            String line = reader.readLine();
            while (line != null) {
                String id = getValue(line, "\"messageID\": \"");
                String hash = getValue(line, "\"messageHash\": \"");
                String recipient = getValue(line, "\"recipient\": \"");
                String key = "\"message\": \"";
                int textStart = line.indexOf(key) + key.length();
                String text = line.substring(textStart, line.lastIndexOf("\"}"));
                text = text.replace("\\\"", "\"");
                Message stored = new Message(storedList.size(), recipient, text);
                stored.setMessageID(id);
                stored.setMessageHash(hash);
                storedList.add(stored);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No stored messages found.");
        }
    }

    // Returns the text between a key and the next quote mark
    private static String getValue(String line, String key) {
        int start = line.indexOf(key) + key.length();
        return line.substring(start, line.indexOf("\"", start));
    }

    // Returns the longest message from the sent and stored arrays
    public static String longestMessage() {
        String longest = "";
        for (int i = 0; i < sentList.size(); i++) {
            if (sentList.get(i).messageText.length() > longest.length()) {
                longest = sentList.get(i).messageText;
            }
        }
        for (int i = 0; i < storedList.size(); i++) {
            if (storedList.get(i).messageText.length() > longest.length()) {
                longest = storedList.get(i).messageText;
            }
        }
        return longest;
    }

    // Finds a message by its ID and returns the message text
    public static String searchByID(String id) {
        for (int i = 0; i < sentList.size(); i++) {
            if (sentList.get(i).messageID.equals(id)) {
                return sentList.get(i).messageText;
            }
        }
        for (int i = 0; i < storedList.size(); i++) {
            if (storedList.get(i).messageID.equals(id)) {
                return storedList.get(i).messageText;
            }
        }
        return "Message ID not found.";
    }

    // Finds a message by its ID and returns the recipient
    public static String searchRecipientByID(String id) {
        for (int i = 0; i < sentList.size(); i++) {
            if (sentList.get(i).messageID.equals(id)) {
                return sentList.get(i).recipient;
            }
        }
        for (int i = 0; i < storedList.size(); i++) {
            if (storedList.get(i).messageID.equals(id)) {
                return storedList.get(i).recipient;
            }
        }
        return "Message ID not found.";
    }
    // Adds this message to the stored array

    public void addToStoredList() {
        storedList.add(this);
    }

    // Returns this message's hash
    public String getMessageHash() {
        return messageHash;
    }

    // Empties all the arrays
    public static void clearLists() {
        sentList.clear();
        disregardedList.clear();
        storedList.clear();
        hashList.clear();
        idList.clear();
    }

    // Returns the text of every sent message
    public static String sentTexts() {
        String result = "";
        for (int i = 0; i < sentList.size(); i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + sentList.get(i).messageText;
        }
        return result;
    }

    // Returns every sent or stored message for a recipient, one per line
    public static String searchByRecipient(String recipient) {
        String result = "";
        for (int i = 0; i < sentList.size(); i++) {
            if (sentList.get(i).recipient.equals(recipient)) {
                result = result + sentList.get(i).messageText + "\n";
            }
        }
        for (int i = 0; i < storedList.size(); i++) {
            if (storedList.get(i).recipient.equals(recipient)) {
                result = result + storedList.get(i).messageText + "\n";
            }
        }
        if (result.equals("")) {
            return "No messages found for this recipient.";
        }
        return result.trim();
    }

    // Deletes a stored message using its hash and updates the JSON file
    public static String deleteByHash(String hash) {
        for (int i = 0; i < storedList.size(); i++) {
            if (storedList.get(i).messageHash.equals(hash)) {
                String text = storedList.get(i).messageText;
                storedList.remove(i);
                saveAllStored();
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // Rewrites messages.json using the stored array
    private static void saveAllStored() {
        try {
            FileWriter writer = new FileWriter("messages.json");
            for (int i = 0; i < storedList.size(); i++) {
                Message m = storedList.get(i);
                String safeText = m.messageText.replace("\"", "\\\"");
                writer.write("{\"messageID\": \"" + m.messageID + "\", \"messageHash\": \"" + m.messageHash
                        + "\", \"recipient\": \"" + m.recipient + "\", \"message\": \"" + safeText + "\"}\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not update the stored messages file.");
        }
    }

    // Returns the hash, recipient and message of every sent message
    public static String sentReport() {
        String report = "";
        for (int i = 0; i < sentList.size(); i++) {
            Message m = sentList.get(i);
            report = report + "Message Hash: " + m.messageHash + "\n"
                    + "Recipient: " + m.recipient + "\n"
                    + "Message: " + m.messageText + "\n\n";
        }
        return report;
    }

    // Returns the full details of every stored message
    public static String storedReport() {
        String report = "";
        for (int i = 0; i < storedList.size(); i++) {
            Message m = storedList.get(i);
            report = report + "Message ID: " + m.messageID + "\n"
                    + "Message Hash: " + m.messageHash + "\n"
                    + "Recipient: " + m.recipient + "\n"
                    + "Message: " + m.messageText + "\n\n";
        }
        return report;
    }

    // Returns the recipient of every stored message, one per line
    public static String storedRecipients() {
        String result = "";
        for (int i = 0; i < storedList.size(); i++) {
            result = result + "Recipient: " + storedList.get(i).recipient + "\n";
        }
        return result;
    }
    // Returns the full details of this message

    public String messageDetails() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageText;
    }

    public String getMessageID() {
        return messageID;
    }
    // Sets the message ID so the unit tests can use a fixed one

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
}
