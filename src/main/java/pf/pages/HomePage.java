package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class HomePage extends AbstractPage {

    private static final String URL = "https://www.tut.by";
    private static final By AFISHA = By.xpath("//a[contains(text(),'Афиша')]");
    private static final By SECTION = By.xpath("//a[contains(text(),'Разделы')]");
    private static final By PAGE = By.xpath("/html/body");
    private static final By MOBILE_VERSION_ENABLE_LINK = By.xpath("//a[contains(text(),'Версия для смартфонов')]");
    private static final By POPUP_FRAME = By.xpath("//iframe[contains(@sandbox,'allow-top-navigation')]");
    private static final By FINANCE = By.xpath("//ul[@class='b-topbar-more-list']/li[4]");
    private static final By POPUP_WINDOW_CLOSE_BUTTON = By.id("smartwelcomeClose");
    private static final By MOBILE_SECTION = By.id("menu_target");
    private static final By MOBILE_MORE_SECTIONS = By.xpath("//ul[@id='responsive_menu']//span[@class=contains( text(),'Все разделы')]");
    private static final By AUTO = By.xpath("//i[@class='b-res-icon res1752 b-icon']/../span");

    public HomePage open() {
        driver.get(URL);
        return this;
    }

    public HomePage afishaOpen() {
        highlightElement(AFISHA);
        driver.findElement(AFISHA).click();
        unHighlightElement(AFISHA);
        return new HomePage();
    }

    public HomePage financeOpen() {
        highlightElement(SECTION);
        unHighlightElement(SECTION);
        driver.findElement(SECTION).click();
        highlightElement(FINANCE);
        unHighlightElement(FINANCE);
        driver.findElement(FINANCE).click();
        return new HomePage();
    }

    public HomePage mobileVersionEnable() {
        int i=0;
        while (!isElementVisible(MOBILE_VERSION_ENABLE_LINK)&&i<30){
            driver.findElement(PAGE).sendKeys(Keys.SPACE);
        }
        //JavascriptExecutor jse = (JavascriptExecutor) driver;
        //jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(MOBILE_VERSION_ENABLE_LINK));
        highlightElement(MOBILE_VERSION_ENABLE_LINK);
        unHighlightElement(MOBILE_VERSION_ENABLE_LINK);
        driver.findElement(MOBILE_VERSION_ENABLE_LINK).click();
        return new HomePage();
    }

    public HomePage autoOpen() {
        highlightElement(POPUP_FRAME);
        driver.switchTo().frame(driver.findElement(POPUP_FRAME));
        waitForElementVisible(POPUP_WINDOW_CLOSE_BUTTON);
        highlightElement(POPUP_WINDOW_CLOSE_BUTTON);
        driver.findElement(POPUP_WINDOW_CLOSE_BUTTON).click();
        unHighlightElement(POPUP_WINDOW_CLOSE_BUTTON);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        waitForElementVisible(MOBILE_SECTION);
        highlightElement(MOBILE_SECTION);
        driver.findElement(MOBILE_SECTION).click();
        unHighlightElement(MOBILE_SECTION);
        waitForElementVisible(MOBILE_MORE_SECTIONS);
        highlightElement(MOBILE_MORE_SECTIONS);
        driver.findElement(MOBILE_MORE_SECTIONS).click();
        unHighlightElement(MOBILE_MORE_SECTIONS);
        waitForElementVisible(AUTO);
        highlightElement(AUTO);
        driver.findElement(AUTO).click();
        return new HomePage();
    }
}