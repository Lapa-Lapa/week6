package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private static final By afisha = By.xpath("//a[contains(text(),'Афиша')]");
    private static final By finance = By.xpath("/[@class='b-topbar-more-list']/li[4]");
    private static final By section = By.xpath("//[contains(text(),'Разделы')]");
    private static final By mobileVersionEnableLink = By.xpath("//[contains(text(),'Версия для смартфонов')]]");
    private static final By mobileSection = By.xpath("menu_target");
    private static final By mobileMoreSections = By.xpath("//[@id='responsive_menu']/li[6]/ul/li[9]/a/span");
    private static final By auto = By.xpath("//[@id='section_list']/li[2]/ul/li[10]/a/span");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://www.tut.by");
        return this;
    }

    public HomePage afishaOpen() {
        driver.findElement(afisha).click();
        return new HomePage(driver);
    }

    public HomePage financeOpen() {
        driver.findElement(section).click();
        driver.findElement(finance).click();
        return new HomePage(driver);
    }

    public HomePage mobileVersionEnable() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,8000)", "");
        driver.findElement(mobileVersionEnableLink).click();
        return new HomePage(driver);
    }

    public HomePage autoOpen() throws InterruptedException {
        driver.findElement(mobileSection).click();
        driver.findElement(mobileMoreSections).click();
        driver.findElement(auto).click();
        return new HomePage(driver);
    }
}