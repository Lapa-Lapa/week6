package com.epam.atm.waiters;

import com.epam.atm.factorymethod.ChromeDriverCreator;
import com.epam.atm.factorymethod.WebDriverCreator;
import com.epam.atm.utils.MyLogger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class HighlitersUnhighliters {

    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 35;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

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
        waitForElementVisible(locator, driver);
        waitForElementClicable(locator, driver);
        highlightElement(locator, driver);
        unHighlightElement(locator, driver);
        driver.findElement(locator).click();
    }

    public static void unHighlightElement(By locator, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }
    public static void takeScreenshot(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            MyLogger.info("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            MyLogger.error("Failed to make screenshot");
        }
    }
}