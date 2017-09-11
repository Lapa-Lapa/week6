package com.epam.atm.pages;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.utils.MyLogger;
import com.epam.atm.utils.WorkWithFrames;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.epam.atm.waiters.HighlitersUnhighliters.highlightClickUnhighlightElement;
import static com.epam.atm.waiters.HighlitersUnhighliters.takeScreenshot;
import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementVisible;
import static com.epam.atm.waiters.AbstractPage.isElementVisible;
import static com.epam.atm.waiters.ThreadSleep.waitSetTime;

public class FilmsPage {
    WebDriver driver;
    private static final By FILMS = By.xpath("//a[contains(text(),'Кино')]");
    private static final By arrowRightAvailableDates = By.xpath("//i[@class='icon-right a-icon']");
    private static final By date9Jule = By.xpath("//a[@title='воскресенье, 17 сентября']");
    private static final By popupWindowCloseButton = By.id("closebtn");
    private static final By popupFrame = By.xpath("//iframe[contains(@src, 'https://api.traq.li/publisher/unattended')]");
    private static final By defaultTimeStartPosition = By.xpath("//div[@id='slider']/div/div[1]/div");
    private static final By byeTikets = By.xpath("//div[contains(text(),'Купить билеты')]");
    private static final By filmPicture = By.xpath("//img[contains(@src,'gadkiy-ya-3')]");
    private static final By film = By.xpath("//a[contains(@href,'gadkiy_ya_3')][@class='name']");
    private static final By areaForShots = By.xpath("//div[@class='fotorama__thumb-border']");
    private static final By anyPicture = By.xpath("//img[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]");
    public static final String SCROLL_JS = "arguments[0].scrollIntoView(true);";

    public FilmsPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
    }

    public FilmsPage openFilms() {
        highlightClickUnhighlightElement(FILMS,driver);
        MyLogger.info("Films page is open");
        takeScreenshot(driver);
        return new FilmsPage();
    }

    public FilmsPage selectDate() {
        int i = 0;
        while (!isElementVisible(date9Jule, driver) && i < 30) {
            highlightClickUnhighlightElement(arrowRightAvailableDates,driver);
            i++;
        }
        highlightClickUnhighlightElement(date9Jule,driver);
        MyLogger.info("Date is selected");
        takeScreenshot(driver);
        return new FilmsPage();
    }

    public FilmsPage popupWindowClose() {
        try {
            waitSetTime(3000);
            waitForElementVisible(popupFrame, driver);
            WorkWithFrames.switchToFrame(driver, popupFrame);
            WorkWithFrames.frameClose(driver, popupWindowCloseButton);
            WorkWithFrames.switchFromFrame(driver);
        } catch (Exception exception) {
            MyLogger.error("Pop up window do not appear");
        }
        takeScreenshot(driver);
        return new FilmsPage();
    }

    public FilmsPage selectTime(int TIME) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(SCROLL_JS, driver.findElement(byeTikets));
        Actions actions = new Actions(driver);
        waitSetTime(900);
        MyLogger.trace("Mini waiting");
        if (TIME <= 17) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47)), 0).click().release().perform();
        }
        if (TIME >= 20) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(385, 0).click().release().perform();
            waitSetTime(900);
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47.5) - 385), 0).click().release().perform();
        }
        waitSetTime(1500);
        MyLogger.info("Time is selected");
        takeScreenshot(driver);
        return new FilmsPage();
    }

    public FilmsPage selectFilm() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(SCROLL_JS, driver.findElement(filmPicture));
        highlightClickUnhighlightElement(film,driver);
        MyLogger.info("Film is selected");
        takeScreenshot(driver);
        return new FilmsPage();
    }

    public int allShots() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(SCROLL_JS, driver.findElement(areaForShots));
        waitForElementVisible(areaForShots, driver);
        MyLogger.info("Quantity of shots are get");
        takeScreenshot(driver);
        return driver.findElements(anyPicture).size();
    }
}