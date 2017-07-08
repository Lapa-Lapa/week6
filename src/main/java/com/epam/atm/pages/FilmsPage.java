package com.epam.atm.pages;

import com.epam.atm.utils.SwitchTo;
import com.epam.atm.waiters.SmartWaiters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import static com.epam.atm.waiters.ThreadSleep.waitElement;

public class FilmsPage extends SmartWaiters {

    private static final By FILMS = By.xpath("//a[contains(text(),'Кино')]");
    private static final By arrowRightAvailableDates = By.xpath("//i[@class='icon-right a-icon']");
    private static final By date9Jule = By.xpath("//a[@title='воскресенье, 16 июля']");
    private static final By popupWindowCloseButton = By.id("closebtn");
    private static final By popupFrame = By.xpath("//iframe[contains(@src, 'https://api.traq.li/publisher/unattended')]");
    private static final By defaultTimeStartPosition = By.xpath("//div[@id='slider']/div/div[1]/div");
    private static final By filmTransformers = By.xpath("//span[contains(text(),'Трансформеры: Последний рыцарь')]");
    private static final By areaForShots = By.xpath("//div[@class='fotorama__thumb-border']");
    private static final By anyPicture = By.xpath("//img[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]");

    public FilmsPage openFilms() {
        driver.findElement(FILMS).click();
        System.out.println("Films page is open");
        return new FilmsPage();
    }

    public FilmsPage selectDate() {
        int i = 0;
        while (!isElementVisible(date9Jule) && i < 30) {
            driver.findElement(arrowRightAvailableDates).click();
            i++;
        }
        driver.findElement(date9Jule).click();
        System.out.println("Date is selected");
        return new FilmsPage();
    }

    public FilmsPage popupWindowClose() {
        waitForElementVisible(popupFrame);
        if (isElementPresent(popupFrame)) {
            SwitchTo.switchToFrame(driver,popupFrame);
           // driver.switchTo().frame(driver.findElement(popupFrame));
            waitForElementClicable(popupWindowCloseButton);
            driver.findElement(popupWindowCloseButton).click();
            String window = driver.getWindowHandle();
            driver.switchTo().window(window);
            System.out.println("Pop up window closed");
        }
        return new FilmsPage();
    }

    public FilmsPage selectTime(int TIME) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(filmTransformers));
        Actions actions = new Actions(driver);
        waitElement(900);
        if (TIME <= 17) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47)), 0).click().release().perform();
        }
        if (TIME >= 20) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(385, 0).click().release().perform();
            waitElement(900);
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47.5) - 385), 0).click().release().perform();
        }
        waitElement(900);
        System.out.println("Time is selected");
        return new FilmsPage();
    }

    public FilmsPage selectFilm() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(FILMS));
        waitForAjaxProcessed();
        driver.findElement(filmTransformers).click();
        System.out.println("Film is selected");
        return new FilmsPage();
    }

    public int allShots() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(areaForShots));
        waitForElementVisible(areaForShots);
        System.out.println("Quantity of shots are get");
        return driver.findElements(anyPicture).size();
    }
}