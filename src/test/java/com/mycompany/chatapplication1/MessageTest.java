/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication1;

import com.mycompany.chatapplication1.Message;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
}
