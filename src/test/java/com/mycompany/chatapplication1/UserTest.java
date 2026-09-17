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
    

    
    private User user;
    
    @BeforeEach
    public void setUp() {
        user = new User();
        User.clearUsers();
    }
    
    // ===== USERNAME TESTS =====
    
    @Test
    public void testCheckUserNameValid() {
        assertTrue(user.checkUserName("kyl_1"));
    }
    
    @Test
    public void testCheckUserNameInvalid() {
        assertFalse(user.checkUserName("kyle!!!!!!"));
    }
    
    // ===== PASSWORD COMPLEXITY TESTS =====
    
    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(user.checkPasswordComplexity("Ch&sec(8)a99"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(user.checkPasswordComplexity("password"));
    }
    
    // ===== CELL PHONE TESTS =====
    
    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue(user.checkCellPhoneNumber("+27813968976"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        assertFalse(user.checkCellPhoneNumber("08966553"));
    }
    
    // ===== REGISTRATION TESTS =====
    
    @Test
    public void testRegisterUserSuccessfully() {
        String result = user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        assertEquals("Welcome User,  it is great to see you again.", result);
    }
    
    @Test
    public void testRegisterUserNameIncorrectlyFormatted() {
        String result = user.registerUser("kyle!!!!!!!", "Ch&sec(8)a99", "+27813968976");
        assertTrue(result.contains("Username is not correctly formatted"));
    }
    
    @Test
    public void testRegisterPasswordIncorrectlyFormatted() {
        String result = user.registerUser("kyl_1", "password", "+27813968976");
        assertTrue(result.contains("Password is not correctly formatted"));
    }
    
    @Test
    public void testRegisterCellPhoneIncorrectlyFormatted() {
        String result = user.registerUser("kyl_1", "Ch&sec(8)a99", "08966553");
        assertTrue(result.contains("Cell number is incorrectly formatted"));
    }
    
    // ===== LOGIN TESTS =====
    
    @Test
    public void testLoginUserSuccessfully() {
        user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        boolean loginResult = user.loginUser("kyl_1", "Ch&sec(8)a99");
        assertTrue(loginResult);
    }
    
    @Test
    public void testLoginUserFailed() {
        user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        boolean loginResult = user.loginUser("kyl_1", "wrongpassword");
        assertFalse(loginResult);
    }
    
    // ===== LOGIN STATUS MESSAGE TESTS =====
    
    @Test
    public void testReturnLoginStatusSuccess() {
        user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        boolean loginResult = user.loginUser("kyl_1", "Ch&sec(8)a99");
        String statusMessage = user.returnLoginStatus(loginResult);
        assertEquals("Welcome User,  it is great to see you again.", statusMessage);
    }
    
    @Test
    public void testReturnLoginStatusFailed() {
        user.registerUser("kyl_1", "Ch&sec(8)a99", "+27813968976");
        boolean loginResult = user.loginUser("kyl_1", "wrongpassword");
        String statusMessage = user.returnLoginStatus(loginResult);
        assertEquals("Username or password incorrect, please try again.", statusMessage);
    }
}
    
    

