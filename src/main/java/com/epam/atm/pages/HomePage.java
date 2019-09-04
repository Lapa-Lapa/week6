package com.epam.atm.pages;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.utils.MyLogger;
import com.epam.atm.waiters.HighlitersUnhighliters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.epam.atm.waiters.AbstractPage.isElementVisible;
import static com.epam.atm.waiters.HighlitersUnhighliters.takeScreenshot;
import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementVisible;
import static com.epam.atm.waiters.ThreadSleep.waitSetTime;

public class HomePage {
    private static WebDriver driver;
    private static final String URL = "https://www.tut.by";
    private static final By AFISHA = By.xpath("//a[contains(text(),'Афиша')]");
    private static final By SECTION = By.xpath("//a[contains(text(),'Разделы')]");
    private static final By MOBILE_VERSION_ENABLE_LINK = By.xpath("//a[contains(text(),'Мобильная версия')]");
    private static final By POPUP_FRAME = By.xpath("//iframe[contains(@sandbox,'allow-top-navigation')]");
    private static final By FINANCE = By.xpath("//ul[@class='b-topbar-more-list']/li[4]");
    private static final By POPUP_WINDOW_CLOSE_BUTTON = By.id("smartwelcomeClose");
    private static final By MOBILE_SECTION = By.id("menu_target");
    private static final By MOBILE_MORE_SECTIONS = By.xpath("//ul[@id='responsive_menu']//span[@class=contains( text(),'Все разделы')]");
    private static final By AUTO = By.xpath("//i[@class='b-res-icon res1752 b-icon']/../span");

    public HomePage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
    }

    public HomePage open() {
        driver.get(URL);
        takeScreenshot(driver);
        MyLogger.info("Home page is open");

        return this;
    }

    public FilmsPage afishaOpen() {
        HighlitersUnhighliters.highlightClickUnhighlightElement(AFISHA, driver);
        MyLogger.info("Afisha page is open");
        takeScreenshot(driver);
        return new FilmsPage();
    }

    public FinancePage financeOpen() {
        HighlitersUnhighliters.highlightClickUnhighlightElement(SECTION, driver);
        HighlitersUnhighliters.highlightClickUnhighlightElement(FINANCE, driver);
        MyLogger.info("Finance page is open");
        takeScreenshot(driver);
        return new FinancePage();
    }

    public HomePage mobileVersionEnable() {
        waitForElementVisible(AFISHA, driver);
        MyLogger.info("Afisha visible");
        while (!isElementVisible(MOBILE_VERSION_ENABLE_LINK, driver)) {
            driver.findElement(AFISHA).sendKeys(Keys.SPACE);
            waitForElementVisible(MOBILE_VERSION_ENABLE_LINK, driver);
        }
        HighlitersUnhighliters.highlightClickUnhighlightElement(MOBILE_VERSION_ENABLE_LINK, driver);
        MyLogger.info("Mobile version enabled");
        takeScreenshot(driver);
        return new HomePage();
    }

    public AutoPage autoOpen() {
        waitSetTime(2000);
        driver.switchTo().frame(driver.findElement(POPUP_FRAME));
        HighlitersUnhighliters.highlightClickUnhighlightElement(POPUP_WINDOW_CLOSE_BUTTON, driver);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        HighlitersUnhighliters.highlightClickUnhighlightElement(MOBILE_SECTION, driver);
        HighlitersUnhighliters.highlightClickUnhighlightElement(MOBILE_MORE_SECTIONS, driver);
        HighlitersUnhighliters.highlightClickUnhighlightElement(AUTO, driver);
        MyLogger.info("Auto page is open");
        takeScreenshot(driver);
        return new AutoPage();
    }
}