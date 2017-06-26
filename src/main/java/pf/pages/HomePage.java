package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    private static final String URL = "https://www.tut.by";
    private static final By AFISHA = By.xpath("//a[contains(text(),'Афиша')]");
    private static final By SECTION = By.xpath("//a[contains(text(),'Разделы')]");
    private static final By MOBILE_VERSION_ENABLE_LINK = By.xpath("//a[contains(text(),'Версия для смартфонов')]");
    private static final By POPUP_FRAME = By.xpath("//iframe[2]");
    private static final By FINANCE = By.xpath("//ul[@class='b-topbar-more-list']/li[4]");
    private static final By POPUP_WINDOW_CLOSE_BUTTON = By.className("close");
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
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", MOBILE_VERSION_ENABLE_LINK);
        highlightElement(MOBILE_VERSION_ENABLE_LINK);
        driver.findElement(MOBILE_VERSION_ENABLE_LINK).click();
        unHighlightElement(MOBILE_VERSION_ENABLE_LINK);
        return new HomePage();
    }

    public HomePage autoOpen() {
        driver.switchTo().frame((WebElement) POPUP_FRAME);
        driver.findElement(POPUP_WINDOW_CLOSE_BUTTON).click();
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        driver.findElement(MOBILE_SECTION).click();
        driver.findElement(MOBILE_MORE_SECTIONS).click();
        driver.findElement(AUTO).click();
        return new HomePage();
    }
}