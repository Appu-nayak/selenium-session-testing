package com.example.sessiontest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    // Example locator verifying successful login - change accordingly
    private final By loggedInIndicator = By.xpath("//h1[contains(., 'Logged In') or contains(., 'Welcome')]");
    private final By logoutButton = By.linkText("Log out"); // change if different

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAt() {
        try {
            return driver.findElement(loggedInIndicator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        try {
            driver.findElement(logoutButton).click();
        } catch (Exception e) {
            // fallback: maybe a direct URL for logout in your app
            // driver.get("https://your-app/logout");
        }
    }
}
