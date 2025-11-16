// StorageUtils.java (new file under utils)
package com.example.sessiontest.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class StorageUtils {
    public static String getLocalStorageItem(WebDriver driver, String key) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object value = js.executeScript("return window.localStorage.getItem(arguments[0]);", key);
        return value == null ? null : value.toString();
    }

    public static String getSessionStorageItem(WebDriver driver, String key) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object value = js.executeScript("return window.sessionStorage.getItem(arguments[0]);", key);
        return value == null ? null : value.toString();
    }
}
