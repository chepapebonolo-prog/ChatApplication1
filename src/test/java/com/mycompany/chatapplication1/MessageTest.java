/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication1;

import com.mycompany.chatapplication1.Message;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class MessageTest {

    // Message under 250 characters is ready to send
             @Test
    public void testMessageLengthSuccess() {
        Message message = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", message.checkMessageLength());
    }

    // Message over 250 characters reports how many characters it is over
    @Test
    public void testMessageLengthFailure() {
        String longText = "";
        for (int i = 0; i < 260; i++) {
            longText = longText + "a";
        }
        Message message = new Message(0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", message.checkMessageLength());
    }

    // Number with an international code is accepted
    @Test
    public void testRecipientSuccess() {
        Message message = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell());
    }

    // Number without an international code is rejected
    @Test
    public void testRecipientFailure() {
        Message message = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", message.checkRecipientCell());
    }

    // First hash matches the test case, the rest are tested in a loop
    @Test
    public void testMessageHash() {
        Message first = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        first.setMessageID("0012345678");
        assertEquals("00:0:HITONIGHT", first.createMessageHash());

        for (int i = 1; i <= 3; i++) {
            Message next = new Message(i, "+27718693002", "Hi Keegan, did you receive the payment?");
            next.setMessageID("0012345678");
            assertEquals("00:" + i + ":HIPAYMENT", next.createMessageHash());
        }
    }

    // Message ID is created and is ten characters
    @Test
    public void testMessageIDCreated() {
        Message message = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    // Send, disregard and store return the correct messages
    @Test
    public void testMessageSent() {
        Message message = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", message.sentMessage(1));
        assertEquals("Press 0 to delete the message.", message.sentMessage(2));
        assertEquals("Message successfully stored.", message.sentMessage(3));
    }

    // Total counts only the messages that were sent
    @Test
    public void testTotalMessages() {
        Message message = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        message.createMessageHash();
        message.addToSent();
        assertEquals(1, Message.returnTotalMessages());
    }
        // Message of exactly 250 characters is still accepted
    @Test
    public void testMessageLengthBoundary() {
        String text = "";
        for (int i = 0; i < 250; i++) {
            text = text + "a";
        }
        Message message = new Message(0, "+27718693002", text);
        assertEquals("Message ready to send.", message.checkMessageLength());
    }

    // Full details are returned in the order ID, hash, recipient, message
    @Test
    public void testMessageDetails() {
        Message message = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        message.setMessageID("0012345678");
        message.createMessageHash();
        String expected = "Message ID: 0012345678\n"
                + "Message Hash: 00:0:HITONIGHT\n"
                + "Recipient: +27718693002\n"
                + "Message: Hi Mike, can you join us for dinner tonight?";
        assertEquals(expected, message.messageDetails());
    }
    
        // Starts each test with empty arrays
    @BeforeEach
    public void setUp() {
        Message.clearLists();
    }

    // Loads the five test messages from the brief into the arrays
    private void loadTestData() {
        Message m1 = new Message(0, "+27834557896", "Did you get the cake?");
        m1.setMessageID("1000000001");
        m1.createMessageHash();
        m1.addToSentList();

        Message m2 = new Message(1, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        m2.setMessageID("1000000002");
        m2.createMessageHash();
        m2.addToStoredList();

        Message m3 = new Message(2, "+27834484567", "Yohoooo, I am at your gate.");
        m3.setMessageID("1000000003");
        m3.createMessageHash();
        m3.addToDisregardedList();

        Message m4 = new Message(3, "0838884567", "It is dinner time!");
        m4.setMessageID("0838884567");
        m4.createMessageHash();
        m4.addToSentList();

        Message m5 = new Message(4, "+27838884567", "Ok, I am leaving without you.");
        m5.setMessageID("1000000005");
        m5.createMessageHash();
        m5.addToStoredList();
    }

    // Sent messages array holds the two sent messages
    @Test
    public void testSentMessagesArray() {
        loadTestData();
        assertEquals("Did you get the cake?, It is dinner time!", Message.sentTexts());
    }

    // Longest message is found across the sent and stored messages
    @Test
    public void testLongestMessage() {
        loadTestData();
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.longestMessage());
    }

    // Search by message ID returns the message text
    @Test
    public void testSearchByID() {
        loadTestData();
        assertEquals("It is dinner time!", Message.searchByID("0838884567"));
    }

    // Search by recipient returns all of that recipient's messages
    @Test
    public void testSearchByRecipient() {
        loadTestData();
        assertEquals("Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.",
                Message.searchByRecipient("+27838884567"));
    }

    // Delete by hash removes the stored message
    @Test
    public void testDeleteByHash() {
        loadTestData();
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
                Message.deleteByHash("10:1:WHERETIME"));
    }

    // Report shows the hash, recipient and message of the sent messages
    @Test
    public void testSentReport() {
        loadTestData();
        String expected = "Message Hash: 10:0:DIDCAKE\nRecipient: +27834557896\nMessage: Did you get the cake?\n\n"
                + "Message Hash: 08:3:ITTIME\nRecipient: 0838884567\nMessage: It is dinner time!\n\n";
        assertEquals(expected, Message.sentReport());
    }
}
