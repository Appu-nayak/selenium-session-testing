🚀 <strong>Session Management & Cookie Handling Automation (Java + Selenium)</strong>

Automated end-to-end testing framework built using <strong>Java, Selenium WebDriver, Maven, and JUnit 5</strong> to validate login flow, session creation, cookie handling, session expiration, and logout behavior of a sample web application.

<hr/>
<strong>🔧 Tech Stack</strong>
<table> <tr> <td><strong>Java</strong></td> <td>Core programming language</td> </tr> <tr> <td><strong>Selenium WebDriver</strong></td> <td>Browser automation</td> </tr> <tr> <td><strong>JUnit 5</strong></td> <td>Test execution & assertions</td> </tr> <tr> <td><strong>Maven</strong></td> <td>Build & dependency management</td> </tr> <tr> <td><strong>WebDriverManager</strong></td> <td>Automatic driver handling</td> </tr> </table> <hr/>
<strong>✨ Features</strong>
<strong>✔ Login Scenarios</strong>

Valid login

Invalid login

Login without cookies

Fake session cookie injection

<strong>✔ Session Management</strong>

Detect session cookies

Simulate session expiry by deleting cookies

Validate access restrictions after expiry

<strong>✔ Logout Verification</strong>

Verify session cookie is removed

Ensure user is redirected to login page

<strong>✔ Framework Design</strong>

Page Object Model (POM) architecture

Reusable utility modules (CookieUtils, StorageUtils)

Centralized configuration (BASE_URL, cookie names, locators)

Compatible with multiple Chrome versions

<hr/>
<strong>📁 Project Structure</strong>
session-management-tests
│── src
│   └── main/java/com/example/sessiontest
│        ├── config
│        │     └── Config.java
│        ├── drivers
│        │     └── DriverFactory.java
│        ├── pages
│        │     ├── LoginPage.java
│        │     └── HomePage.java
│        ├── utils
│        │     ├── CookieUtils.java
│        │     └── StorageUtils.java
│        └── tests
│              └── SessionManagementTest.java
│
│── pom.xml
│── .gitignore
│── README.md


<hr/>
<strong>▶️ How to Run</strong>
Run all tests
mvn test

Run a single test

Right-click the test file →
<strong>Run As → JUnit Test</strong>

<hr/>
<strong>📘 About This Project</strong>

This project demonstrates real-world concepts of:

Authentication testing

Session and cookie handling

Automation framework development

Page Object Model (POM) design

Utility-driven modular automation

It is suitable for academic projects, internship portfolios, and QA automation resumes.

