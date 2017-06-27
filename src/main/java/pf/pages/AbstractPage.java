package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pf.utils.WebDriverSingleton;

public class AbstractPage {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 35;
    protected WebDriver driver;

    protected AbstractPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
    }

    protected boolean isElementPresent(By locator) {
        waitForElementPresent(locator);
        return !driver.findElements(locator).isEmpty();
    }
    protected boolean isElementClicable(By locator) {
        waitForElementClicable(locator);
        return !driver.findElements(locator).isEmpty();
    }

    protected boolean isElementVisible(By locator) { return driver.findElement(locator).isDisplayed();}
    //protected boolean isElementVisible(By locator) { return driver.findElement(locator).();}

    protected void waitForElementPresent(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(locator));
    }//made better?

    protected void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }//don't touch! 24 slide of
    //https://www.slideshare.net/ssuser220b38/java-explicit-and-implicit-wait-testing-ajax-applications

    protected void waitForElementClicable(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", driver.findElement(locator));
    }

    protected void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

    private ExpectedCondition<Boolean> isAjaxFinished() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            }
        };
    }

    protected void waitForAjaxProcessed() {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(isAjaxFinished());
    }
}