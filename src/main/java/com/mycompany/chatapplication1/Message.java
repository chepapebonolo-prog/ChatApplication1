/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication1;

import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageText) {
        Random random = new Random();
        long id = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(id);
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
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
            public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String first = cleanWord(words[0]);
        String last = cleanWord(words[words.length - 1]);
        messageHash = (messageID.substring(0, 2) + ":" + messageNumber + ":" + first + last).toUpperCase();
        return messageHash;
    }

    private String cleanWord(String word) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetterOrDigit(word.charAt(i))) {
                result = result + word.charAt(i);
            }
        }
        return result;
    }

    public String getMessageID() {
        return messageID;
    }
}
