package com.epam.atm.utils;

import com.epam.atm.waiters.SmartWaiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwitchTo {

    public static void switchToFrame(WebDriver driver, By locator) {

        driver.switchTo().frame(driver.findElement(locator));
    }

    public void switchToWindow(By locator) {
     //   driver.switchTo().frame(driver.findElement(locator));
    }
}