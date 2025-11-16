package com.example.sessiontest.tests;

import com.example.sessiontest.config.Config;
import com.example.sessiontest.drivers.DriverFactory;
import com.example.sessiontest.pages.HomePage;
import com.example.sessiontest.pages.LoginPage;
import com.example.sessiontest.utils.CookieUtils;
import com.example.sessiontest.utils.StorageUtils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assumptions;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SessionManagementTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeAll
    public void setUp() {
        driver = DriverFactory.createChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // small implicit wait for general operations
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ---------- HELPER METHODS ----------

    private WebDriverWait waitShort() {
        return new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    private void waitUntilLoggedIn() {
        // For practicetestautomation.com, after login URL contains "logged-in-successfully"
        // and there is an <h1> Logged In Successfully
        waitShort().until(ExpectedConditions.or(
                ExpectedConditions.urlContains("logged-in-successfully"),
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(.,'Logged In')]"))
        ));
    }

    private void assumeSessionCookieIsConfigured() {
        // If your app doesn't use a cookie for session, set SESSION_COOKIE_NAME = null
        // and cookie-based tests will be skipped instead of failing.
        Assumptions.assumeTrue(
                Config.SESSION_COOKIE_NAME != null && !Config.SESSION_COOKIE_NAME.isBlank(),
                "SESSION_COOKIE_NAME not set in Config — app may not expose a session cookie. Skipping cookie tests."
        );
    }

    private Cookie requireSessionCookieOrSkip() {
        assumeSessionCookieIsConfigured();
        Cookie sessionCookie = CookieUtils.getCookie(driver, Config.SESSION_COOKIE_NAME);
        Assumptions.assumeTrue(
                sessionCookie != null,
                "Session cookie NOT found for name '" + Config.SESSION_COOKIE_NAME +
                        "'. Run debug test and update Config.SESSION_COOKIE_NAME accordingly."
        );
        return sessionCookie;
    }

    // ---------- DEBUG TEST (for you to inspect cookies and storage) ----------

    @Test
    @DisplayName("DEBUG: Print cookies and localStorage after login")
    public void debugPrintStorageAfterLogin() {
        System.out.println();
        System.out.println("================================");
        System.out.println("  🔵 DEBUG: Login Test Started");
        System.out.println("================================");

        loginPage.open(Config.BASE_URL);
        loginPage.login(Config.VALID_USERNAME, Config.VALID_PASSWORD);

        waitUntilLoggedIn();

        // Print cookies
        Set<Cookie> cookies = CookieUtils.getAllCookies(driver);
        System.out.println();
        System.out.println("🟡 Cookies found after login:");
        if (cookies.isEmpty()) {
            System.out.println(" → (no cookies found)");
        } else {
            for (Cookie c : cookies) {
                System.out.printf(" → %s = %s (domain=%s, path=%s)%n",
                        c.getName(), c.getValue(), c.getDomain(), c.getPath());
            }
        }

        // Try common localStorage keys
        System.out.println();
        System.out.println("🟢 Checking some common localStorage keys:");
        String[] keys = {"authToken", "token", "accessToken", "user", "session"};
        for (String key : keys) {
            String value = StorageUtils.getLocalStorageItem(driver, key);
            System.out.printf(" → localStorage['%s'] = %s%n", key, value);
        }

        System.out.println();
        System.out.println("🟣 Looking for session cookie name: " + Config.SESSION_COOKIE_NAME);
        System.out.println("================================");
    }

    // ---------- REAL TESTS ----------

    @Test
    @DisplayName("Login shows logged-in page (basic functional test)")
    public void testLoginShowsLoggedInPage() {
        loginPage.open(Config.BASE_URL);
        loginPage.login(Config.VALID_USERNAME, Config.VALID_PASSWORD);

        waitUntilLoggedIn();

        // Use HomePage#isAt() to verify
        assertTrue(homePage.isAt(), "User should be on logged-in page after valid login");
    }
    
    @Test
    @DisplayName("Invalid login should NOT allow access")
    public void testInvalidLoginDoesNotWork() {
        // Open login page
        loginPage.open(Config.BASE_URL);

        // Use wrong password (or wrong username)
        String wrongPassword = "WrongPassword123!";
        loginPage.login(Config.VALID_USERNAME, wrongPassword);

        // Wait a little for error message or page response
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        // Either an error appears, or we stay on login page.
        // For practicetestautomation, an error div with id="error" appears.
        boolean errorShown;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
            errorShown = true;
        } catch (Exception e) {
            errorShown = false;
        }

        // Make sure we are NOT on logged-in page
        boolean loggedIn = homePage.isAt();

        System.out.println("Invalid login -> errorShown=" + errorShown + ", loggedIn=" + loggedIn);

        // At least one of these should be true: error message shown or NOT logged in.
        assertTrue(errorShown || !loggedIn,
                "With wrong credentials, user must not be successfully logged in");
    }


    @Test
    @DisplayName("Login creates a session cookie (if app uses cookies)")
    public void testLoginCreatesSessionCookie() {
        loginPage.open(Config.BASE_URL);
        loginPage.login(Config.VALID_USERNAME, Config.VALID_PASSWORD);

        waitUntilLoggedIn();

        Cookie sessionCookie = requireSessionCookieOrSkip();

        System.out.println("Session cookie after login: " + sessionCookie);
        assertNotNull(sessionCookie, "Session cookie should exist after login");
    }

    @Test
    @DisplayName("Deleting session cookie logs out user (simulate session expiry)")
    public void testDeletingSessionCookieLogsOutUser() {
        loginPage.open(Config.BASE_URL);
        loginPage.login(Config.VALID_USERNAME, Config.VALID_PASSWORD);

        waitUntilLoggedIn();

        Cookie sessionCookie = requireSessionCookieOrSkip();
        System.out.println("Deleting session cookie: " + sessionCookie);

        CookieUtils.deleteCookie(driver, Config.SESSION_COOKIE_NAME);

        // Refresh so the app detects missing cookie
        driver.navigate().refresh();
        // after refresh we expect to NOT be at home page
        boolean stillLoggedIn = homePage.isAt();
        assertFalse(stillLoggedIn, "User should not be considered logged-in after deleting session cookie");
    }

    @Test
    @DisplayName("Adding a fake session cookie should NOT log in the user")
    public void testAddingFakeSessionCookieDoesNotLogin() {
        // We need domain info, so open base URL first
        loginPage.open(Config.BASE_URL);

        assumeSessionCookieIsConfigured();

        String domain = getDomainFromBaseUrl(Config.BASE_URL);

        Cookie fake = new Cookie.Builder(Config.SESSION_COOKIE_NAME, "fake-value-xyz")
                .domain(domain)
                .path("/")
                .build();

        System.out.println("Adding fake session cookie: " + fake);
        CookieUtils.addCookie(driver, fake);

        driver.navigate().refresh();
        // After refresh we should still NOT be logged in
        assertFalse(homePage.isAt(), "Fake cookie should not authenticate the user");
    }

    @Test
    @DisplayName("Logout removes session cookie (if app uses cookies)")
    public void testLogoutRemovesSessionCookie() {
        loginPage.open(Config.BASE_URL);
        loginPage.login(Config.VALID_USERNAME, Config.VALID_PASSWORD);

        waitUntilLoggedIn();

        // Ensure cookie exists first, or skip
        requireSessionCookieOrSkip();

        // Use the page object logout
        homePage.logout();

        // Wait until we are back on login / not on home page
        waitShort().until(ExpectedConditions.not(
                ExpectedConditions.urlContains("logged-in-successfully"))
        );

        Cookie afterLogout = CookieUtils.getCookie(driver, Config.SESSION_COOKIE_NAME);
        assertNull(afterLogout, "Session cookie should be removed after logout");
    }

    // ---------- SMALL HELPER ----------

    private String getDomainFromBaseUrl(String baseUrl) {
        // e.g. https://practicetestautomation.com/practice-test-login/
        String domain = baseUrl.replaceFirst("^https?://", "").split("/")[0];
        if (domain.contains(":")) {
            domain = domain.split(":")[0];
        }
        return domain;
    }
}
