/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication1;

/**
 *
 * @author Student
 */


import java.util.HashMap;
import java.util.Map;

public class User {
    
    private static Map<String, Map<String, String>> registeredUsers = new HashMap<>();
    
    // Check if username contains underscore and is max 5 characters
    public boolean checkUserName(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }
    
    // Check password has 8+ chars, capital letter, number, and special character
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        
        return hasCapital && hasNumber && hasSpecialChar;
    }
    
    // Check if phone number starts with + and has 11-13 digits
    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone == null || cellPhone.isEmpty()) {
            return false;
        }
        
        if (!cellPhone.startsWith("+")) {
            return false;
        }
        
        String digits = cellPhone.substring(1);
        
        if (digits.length() < 11 || digits.length() > 13) {
            return false;
        }
        
        for (char c : digits.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        
        return true;
    }
    
    // Register user - validate all fields then store
    public String registerUser(String username, String password, String cellPhone) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        
        Map<String, String> userData = new HashMap<>();
        userData.put("password", password);
        userData.put("cellPhone", cellPhone);
        userData.put("firstName", "User");
        userData.put("lastName", "");
        
        registeredUsers.put(username, userData);
        
        return "Welcome User,  it is great to see you again.";
    }
    
    // Check if login credentials are correct
    public boolean loginUser(String username, String password) {
        if (!registeredUsers.containsKey(username)) {
            return false;
        }
        
        Map<String, String> userData = registeredUsers.get(username);
        return userData.get("password").equals(password);
    }
    
    // Return login status message
    public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            return "Welcome User,  it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Helper methods for testing
    public static Map<String, Map<String, String>> getRegisteredUsers() {
        return registeredUsers;
    }
    
    public static void clearUsers() {
        registeredUsers.clear();
    }
}