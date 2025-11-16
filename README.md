Session Management & Cookie Handling Automation (Java + Selenium)

This project implements an automated test suite using Java, Selenium WebDriver, JUnit 5, and Maven to validate login flow, session creation, cookie handling, and logout behavior in a sample web application. It follows the Page Object Model (POM) for maintainability and clear separation of concerns.

Features
Login Scenarios

Valid login

Invalid login

Login without cookies

Fake session cookie injection

Session Management

Detect session cookies

Simulate session expiry by deleting cookies

Validate access restrictions after session expiry

Logout Verification

Ensure session cookie is removed

Verify redirection to login page after logout

Framework Design

Page Object Model (POM) structure

Utility classes for cookie and storage inspection

Centralized configuration for base URL, credentials, and session cookie name

Uses WebDriverManager for driver handling

Tech Stack
Technology	Purpose
Java	Programming language
Selenium WebDriver	Browser automation
JUnit 5	Test execution & assertions
Maven	Build & dependency management
WebDriverManager	Automatic driver setup
Project Structure
session-management-tests
│── src
│   └── main/java/com/example/sessiontest
│        ├── config
│        ├── drivers
│        ├── pages
│        ├── utils
│        └── tests
│
│── pom.xml
│── .gitignore
│── README.md

How to Run the Tests
Run all tests
mvn test

Run a single test (Eclipse, IntelliJ, or VS Code)

Right-click the test file →
Run as → JUnit Test

About the Project

This automation project demonstrates practical testing of authentication workflows and backend session mechanisms. The structure and tools used align with real-world QA automation frameworks.
