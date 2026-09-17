/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapplication1;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class UserTest {
    
   
    
     @Test
    public void testCheckUserNameValid() {
        User user = new User();
        User.clearUsers();
        assertTrue(user.checkUserName("kyl_1"));
    }
    
    @Test
    public void testCheckUserNameInvalid() {
        User user = new User();
        User.clearUsers();
        assertFalse(user.checkUserName("kyle!!!!!!"));
    }
    
    @Test
    public void testCheckPasswordComplexityValid() {
        User user = new User();
        User.clearUsers();
        assertTrue(user.checkPasswordComplexity("Ch&sec(8)a99"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalid() {
        User user = new User();
        User.clearUsers();
        assertFalse(user.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckCellPhoneNumberValid() {
        User user = new User();
        User.clearUsers();
        assertTrue(user.checkCellPhoneNumber("+27813968976"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        User user = new User();
        User.clearUsers();
        assertFalse(user.checkCellPhoneNumber("08966553"));
    }
    
    @Test
    public void testRegisterUserSuccessfully() {
        User user = new User();
        User.clearUsers();
        String result = user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        assertEquals("Welcome User,  it is great to see you again.", result);
    }
    
    @Test
    public void testRegisterUserNameIncorrectlyFormatted() {
        User user = new User();
        User.clearUsers();
        String result = user.registerUser("kyle!!!!!!!", "Ch&sec(8)a99", "+27813968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }
    
    @Test
    public void testRegisterPasswordIncorrectlyFormatted() {
        User user = new User();
        User.clearUsers();
        String result = user.registerUser("kyl_1", "password", "+27813968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }
    
    @Test
    public void testRegisterCellPhoneIncorrectlyFormatted() {
        User user = new User();
        User.clearUsers();
        String result = user.registerUser("kyl_1", "Ch&sec(8)a99", "08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
    }
    
    @Test
    public void testLoginUserSuccessfully() {
        User user = new User();
        User.clearUsers();
        user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        boolean loginResult = user.loginUser("kyl_1", "Ch&sec(8)a99");
        String result = user.returnLoginStatus(loginResult);
        assertEquals("Welcome User,  it is great to see you again.", result);
    }
    
    @Test
    public void testLoginUserFailed() {
        User user = new User();
        User.clearUsers();
        user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        boolean loginResult = user.loginUser("kyl_1", "wrongpassword");
        String result = user.returnLoginStatus(loginResult);
        assertEquals("Username or password incorrect, please try again.", result);
    }
    
}
