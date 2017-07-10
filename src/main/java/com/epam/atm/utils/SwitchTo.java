package com.epam.atm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwitchTo {
    public static void switchToFrame(WebDriver driver, By frame) {
        driver.switchTo().frame(driver.findElement(frame));
    }

    public static void switchToFrameAndClose(WebDriver driver, By frame, By closeButton) {
        driver.switchTo().frame(driver.findElement(frame));
        driver.findElement(closeButton).click();
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        System.out.println("Pop up window closed");
    }

    public void switchToWindow(By locator) {
        //   driver.switchTo().frame(driver.findElement(locator));
    }
}