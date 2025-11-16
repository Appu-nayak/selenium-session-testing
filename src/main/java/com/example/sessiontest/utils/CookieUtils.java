package com.example.sessiontest.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CookieUtils {

    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static Cookie getCookie(WebDriver driver, String name) {
        return driver.manage().getCookieNamed(name);
    }

    public static void addCookie(WebDriver driver, Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    public static void deleteCookie(WebDriver driver, String name) {
        driver.manage().deleteCookieNamed(name);
    }

    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }
}
