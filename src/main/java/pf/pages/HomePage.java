package pf.pages;

import com.gargoylesoftware.htmlunit.Page;
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
        highlightUnhighlightClickElement(AFISHA);
        return new HomePage();
    }

    public HomePage financeOpen() {
        highlightUnhighlightClickElement(SECTION);
        highlightUnhighlightClickElement(FINANCE);
        return new HomePage();
    }

    public HomePage mobileVersionEnable() {
        int i = 0;
        while (!isElementVisible(MOBILE_VERSION_ENABLE_LINK) && i < 30) {
            driver.findElement(PAGE).sendKeys(Keys.SPACE);
            i++;
        }
        //JavascriptExecutor jse = (JavascriptExecutor) driver;
        //jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(MOBILE_VERSION_ENABLE_LINK));
        highlightUnhighlightClickElement(MOBILE_VERSION_ENABLE_LINK);
        return new HomePage();
    }

    public HomePage autoOpen() {
        try {
            Thread.sleep(2000);
            driver.switchTo().frame(driver.findElement(POPUP_FRAME));
            highlightUnhighlightClickElement(POPUP_WINDOW_CLOSE_BUTTON);
            String window = driver.getWindowHandle();
            driver.switchTo().window(window);
                } catch (Exception e) {
        }
        highlightUnhighlightClickElement(MOBILE_SECTION);
        highlightUnhighlightClickElement(MOBILE_MORE_SECTIONS);
        highlightUnhighlightClickElement(AUTO);
        return new HomePage();
    }
}