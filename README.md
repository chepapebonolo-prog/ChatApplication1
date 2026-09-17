# ChatApplication2

[![Java](https://img.shields.io/badge/Java-11+-blue)](https://www.java.com)
[![JUnit5](https://img.shields.io/badge/JUnit-5-green)](https://junit.org/junit5/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

A secure Java-based chat application featuring user registration and login functionality with comprehensive input validation.

##  Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Validation Rules](#validation-rules)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Author](#author)

##  Features

- **User Registration** - Create new accounts with validation
- **User Login** - Authenticate with secure credentials
- **Password Security** - Complex password requirements
- **Phone Validation** - International cell phone format support
- **Console Interface** - User-friendly menu system
- **Error Handling** - Clear validation error messages

##  Requirements

- Java 11 or higher
- JUnit 5 (for running tests)


### Clone the Repository
```bash
git clone https://github.com/chepapebonolo-prog/ChatApplication2.git
cd ChatApplication2
```

### Compile
```bash
javac User.java ChatApplication1.java
```

##  Usage

### Run the Application
```bash
java ChatApplication1
```

### Menu Options
```
========================================
        CHAT APPLICATION - MENU         
========================================
1. Register
2. Login
3. Exit
```

### Example Registration
```
Username: kyl_1
Password: Ch&sec(8)a99
Cell Phone: +27813968976
Response: Welcome User, it is great to see you again.
```

##  Validation Rules

### Username
- Must contain an underscore `_`
- Maximum 5 characters
- ✓ Valid: `kyl_1`
- ✗ Invalid: `kyle!!!!!!`

### Password
- Minimum 8 characters
- Must contain uppercase letter
- Must contain number
- Must contain special character
- ✓ Valid: `Ch&sec(8)a99`
- ✗ Invalid: `password`

### Cell Phone
- Must start with `+` (international code)
- Must contain 11-13 digits
- ✓ Valid: `+27813968976`
- ✗ Invalid: `08966553`

## Testing

### Run Tests in NetBeans
1. Right-click `UserTest.java`
2. Select "Run File" or press `Alt+F6`

### Test Coverage
14 comprehensive unit tests covering:
- ✓ Username validation (valid/invalid)
- ✓ Password complexity (valid/invalid)
- ✓ Cell phone format (valid/invalid)
- ✓ User registration scenarios
- ✓ Login functionality
- ✓ Status messages

##  Project Structure

```
ChatApplication2/
├── User.java              # User validation & authentication
├── ChatApplication1.java  # Main application & menu
├── UserTest.java          # 14 JUnit test cases
└── README.md              # This file
```

##  Methods

### User.java
```java
checkUserName(String username)                    // Validate username
checkPasswordComplexity(String password)          // Validate password strength
checkCellPhoneNumber(String cellPhone)            // Validate phone number
registerUser(String username, String password, String cellPhone) // Register user
loginUser(String username, String password)       // Authenticate user
returnLoginStatus(boolean loginStatus)            // Get status message
clearUsers()                                       // Clear registered users
```

##  Test Results

```
✓ testCheckUserNameValid
✓ testCheckUserNameInvalid
✓ testCheckPasswordComplexityValid
✓ testCheckPasswordComplexityInvalid
✓ testCheckCellPhoneNumberValid
✓ testCheckCellPhoneNumberInvalid
✓ testRegisterUserSuccessfully
✓ testRegisterUserNameIncorrectlyFormatted
✓ testRegisterPasswordIncorrectlyFormatted
✓ testRegisterCellPhoneIncorrectlyFormatted
✓ testLoginUserSuccessfully
✓ testLoginUserFailed
✓ testReturnLoginStatusSuccess
✓ testReturnLoginStatusFailed

14/14 tests passing 
```

##  Author

**Student**
- GitHub: [@chepapebonolo-prog](https://github.com/chepapebonolo-prog)

##  License

This project is licensed under the MIT License - see the LICENSE file for details.

##  Course

PROG POE - Programming Practical Coding Exam
Part 1: Registration and Login Feature 

---

