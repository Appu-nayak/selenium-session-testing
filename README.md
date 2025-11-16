<h1>🚀 Session Management & Cookie Handling Automation (Java + Selenium)</h1>

<p>
This project implements an automated test suite using <strong>Java</strong>, 
<strong>Selenium WebDriver</strong>, <strong>JUnit 5</strong>, and <strong>Maven</strong> to validate 
login flow, session creation, cookie handling, session expiration, and logout behavior of a web application.
</p>

<hr/>

<h2>🔧 Tech Stack</h2>

<table>
<tr><td><strong>Java</strong></td><td>Core programming language</td></tr>
<tr><td><strong>Selenium WebDriver</strong></td><td>Browser automation</td></tr>
<tr><td><strong>JUnit 5</strong></td><td>Test execution & assertions</td></tr>
<tr><td><strong>Maven</strong></td><td>Build & dependency management</td></tr>
<tr><td><strong>WebDriverManager</strong></td><td>Automatic ChromeDriver handling</td></tr>
</table>

<hr/>

<h2>✨ Features</h2>

<h3>✔ Login Scenarios</h3>
<ul>
<li>Valid login</li>
<li>Invalid login</li>
<li>Login without cookies</li>
<li>Fake session cookie injection</li>
</ul>

<h3>✔ Session Management</h3>
<ul>
<li>Detect session cookies</li>
<li>Simulate session expiry by deleting cookies</li>
<li>Validate blocked access after expiry</li>
</ul>

<h3>✔ Logout Verification</h3>
<ul>
<li>Verify session cookie removal</li>
<li>Ensure user is redirected to login page</li>
</ul>

<h3>✔ Framework Design</h3>
<ul>
<li>Page Object Model (POM)</li>
<li>Reusable utility modules (CookieUtils, StorageUtils)</li>
<li>Central configuration for BASE_URL and cookie name</li>
<li>Browser handled via WebDriverManager</li>
</ul>

<hr/>

<h2>📁 Project Structure</h2>

<pre>
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
</pre>

<hr/>

<h2>▶️ How to Run</h2>

<h3>Run all tests</h3>

<pre>
mvn test
</pre>

<h3>Run a single test (Eclipse / IntelliJ / VS Code)</h3>
<p>Right-click the test file → <strong>Run As → JUnit Test</strong></p>

<hr/>

<h2>📘 About This Project</h2>

<p>
This automation suite demonstrates real-world concepts related to web authentication, 
session lifecycle, cookie-based security, and automation framework development.
It is suitable for academic projects, automation portfolios, and QA engineering interviews.
</p>
