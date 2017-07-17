package com.epam.atm.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HighlitersUnhighliters{

    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 35;

    public static void waitForElementVisible(By locator, WebDriver driver) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementClicable(By locator, WebDriver driver) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void highlightElement(By locator, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", driver.findElement(locator));
    }

    public static void highlightUnhighlightClickElement(By locator, WebDriver driver) {
        waitForElementVisible(locator,driver);
        waitForElementClicable(locator,driver);
        //highlightElement(locator,driver);
        //unHighlightElement(locator,driver);
        driver.findElement(locator).click();
    }

    public static void unHighlightElement(By locator, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }
}