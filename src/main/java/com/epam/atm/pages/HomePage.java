package com.epam.atm.pages;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.utils.Logger;
import com.epam.atm.waiters.HighlitersUnhighliters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementVisible;
import static com.epam.atm.waiters.AbstractPage.isElementVisible;
import static com.epam.atm.waiters.ThreadSleep.waitSetTime;

public class HomePage {
    WebDriver driver;
    private static final String URL = "https://www.tut.by";
    private static final By AFISHA = By.xpath("//a[contains(text(),'Афиша')]");
    private static final By SECTION = By.xpath("//a[contains(text(),'Разделы')]");
    private static final By MOBILE_VERSION_ENABLE_LINK = By.xpath("//a[contains(text(),'Версия для смартфонов')]");
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
        Logger.info("Home page is open");
        return this;
    }

    public FilmsPage afishaOpen() {
        HighlitersUnhighliters.highlightUnhighlightClickElement(AFISHA, driver);
        Logger.info("Afisha page is open");
        return new FilmsPage();
    }

    public FinancePage financeOpen() {
        HighlitersUnhighliters.highlightUnhighlightClickElement(SECTION, driver);
        HighlitersUnhighliters.highlightUnhighlightClickElement(FINANCE, driver);
        Logger.info("Finance page is open");
        return new FinancePage();
    }

    public HomePage mobileVersionEnable() {
        waitForElementVisible(AFISHA, driver);
        int i = 0;
        while (!isElementVisible(MOBILE_VERSION_ENABLE_LINK, driver) && (i < 30)) {
            Actions actions = new Actions(driver);
            driver.findElement(AFISHA).sendKeys(Keys.SPACE);
            //actions.sendKeys(Keys.SPACE).sendKeys(Keys.SPACE).sendKeys(Keys.SPACE).perform();
            //actions.sendKeys(" ");
            waitSetTime(1000);
            //driver.findElement(PAGE).sendKeys(Keys.SPACE);
            i++;
        }
        //JavascriptExecutor jse = (JavascriptExecutor) driver;
        //jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(MOBILE_VERSION_ENABLE_LINK));
        HighlitersUnhighliters.highlightUnhighlightClickElement(MOBILE_VERSION_ENABLE_LINK, driver);
        Logger.info("Mobile version enabled");
        return new HomePage();
    }

    public AutoPage autoOpen() {
        waitSetTime(2000);
        driver.switchTo().frame(driver.findElement(POPUP_FRAME));
        HighlitersUnhighliters.highlightUnhighlightClickElement(POPUP_WINDOW_CLOSE_BUTTON, driver);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        HighlitersUnhighliters.highlightUnhighlightClickElement(MOBILE_SECTION, driver);
        HighlitersUnhighliters.highlightUnhighlightClickElement(MOBILE_MORE_SECTIONS, driver);
        HighlitersUnhighliters.highlightUnhighlightClickElement(AUTO, driver);
        Logger.info("Auto page is open");
        return new AutoPage();
    }
}