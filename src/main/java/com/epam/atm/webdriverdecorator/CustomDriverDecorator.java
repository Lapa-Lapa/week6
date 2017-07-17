package com.epam.atm.webdriverdecorator;

import com.epam.atm.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class CustomDriverDecorator implements WebDriver {

    public WebDriver driver;

    public CustomDriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    public void get(String url) {
        driver.get(url);
        Logger.info(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        Logger.info(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()));
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
        Logger.info("WebDriver Close");
    }

    public void quit() {
        Logger.info("Browser will be closed now...");
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        Logger.info("Swich to decorator");
        return driver.switchTo();
    }

    public Navigation navigate() {
        Logger.info("Navigate to decorator");
        return driver.navigate();
    }

    public Options manage() {
        Logger.info("Manage decorator");
        return driver.manage();
    }
}