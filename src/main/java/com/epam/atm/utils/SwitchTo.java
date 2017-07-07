package com.epam.atm.utils;

import com.epam.atm.waiters.SmartWaiters;
import org.openqa.selenium.By;

public class SwitchTo extends SmartWaiters {

    private void switchToFrame(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }

    public void switchToWindow(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }
}