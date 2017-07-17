package com.epam.atm.pages;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.utils.Logger;
import com.epam.atm.utils.WorkWithFrames;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementVisible;
import static com.epam.atm.waiters.AbstractPage.isElementVisible;
import static com.epam.atm.waiters.ThreadSleep.waitSetTime;

public class FilmsPage {
    WebDriver driver;
    private static final By FILMS = By.xpath("//a[contains(text(),'Кино')]");
    private static final By arrowRightAvailableDates = By.xpath("//i[@class='icon-right a-icon']");
    private static final By date9Jule = By.xpath("//a[@title='воскресенье, 23 июля']");
    private static final By popupWindowCloseButton = By.id("closebtn");
    private static final By popupFrame = By.xpath("//iframe[contains(@src, 'https://api.traq.li/publisher/unattended')]");
    private static final By defaultTimeStartPosition = By.xpath("//div[@id='slider']/div/div[1]/div");
    //    private static final By filmTransformers = By.xpath("//span[contains(text(),'Трансформеры: Последний рыцарь')]");
    private static final By filmTransformers = By.xpath("//span[contains(text(),'Гадкий я 3')]");
    private static final By areaForShots = By.xpath("//div[@class='fotorama__thumb-border']");
    private static final By anyPicture = By.xpath("//img[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]");

    public FilmsPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
    }

    public FilmsPage openFilms() {
        driver.findElement(FILMS).click();
        Logger.info("Films page is open");
        return new FilmsPage();
    }

    public FilmsPage selectDate() {
        int i = 0;
        while (!isElementVisible(date9Jule, driver) && i < 30) {
            driver.findElement(arrowRightAvailableDates).click();
            i++;
        }
        driver.findElement(date9Jule).click();
        Logger.info("Date is selected");
        return new FilmsPage();
    }

    public FilmsPage popupWindowClose() {
        try {
            waitForElementVisible(popupFrame, driver);
            WorkWithFrames.switchToFrame(driver, popupFrame);
            WorkWithFrames.frameClose(driver, popupWindowCloseButton);
            WorkWithFrames.switchFromFrame(driver);
        } catch (Exception exception) {
            Logger.error("Pop up window do not appear");
        }
        return new FilmsPage();
    }

    public FilmsPage selectTime(int TIME) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(filmTransformers));
        Actions actions = new Actions(driver);
        waitSetTime(900);
        if (TIME <= 17) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47)), 0).click().release().perform();
        }
        if (TIME >= 20) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(385, 0).click().release().perform();
            waitSetTime(900);
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47.5) - 385), 0).click().release().perform();
        }
        waitSetTime(1500);
        Logger.info("Time is selected");
        return new FilmsPage();
    }

    public FilmsPage selectFilm() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(FILMS));
        driver.findElement(filmTransformers).click();
        Logger.info("Film is selected");
        return new FilmsPage();
    }

    public int allShots() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(areaForShots));
        waitForElementVisible(areaForShots, driver);
        Logger.info("Quantity of shots are get");
        return driver.findElements(anyPicture).size();
    }
}