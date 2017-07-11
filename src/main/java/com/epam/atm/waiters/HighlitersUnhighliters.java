package com.epam.atm.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class HighlitersUnhighliters extends SmartWaiters {

    protected void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", driver.findElement(locator));
    }

    protected void highlightUnhighlightClickElement(By locator) {
        waitForElementVisible(locator);
        waitForElementClicable(locator);
        highlightElement(locator);
        unHighlightElement(locator);
        driver.findElement(locator).click();
    }

    protected void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }
}