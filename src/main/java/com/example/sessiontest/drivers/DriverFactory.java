package com.example.sessiontest.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // useful options:
        options.addArguments("--start-maximized");
        // Uncomment for CI/headless:
        // options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox");
        return new ChromeDriver(options);
    }
}
