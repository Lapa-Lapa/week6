package com.epam.atm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WorkWithFrames {

    public static void switchToFrame(WebDriver driver, By frame) {
        driver.switchTo().frame(driver.findElement(frame));
        MyLogger.info("Switch to frame");
    }

    public static void frameClose(WebDriver driver,  By closeButton) {
        driver.findElement(closeButton).click();
        MyLogger.info("Pop up window closed");
    }

    public static void switchFromFrame(WebDriver driver) {
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        MyLogger.info("Switch from frame to window");
    }
}