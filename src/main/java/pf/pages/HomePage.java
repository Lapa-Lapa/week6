package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[contains(text(),'Афиша')]")
    public WebElement afisha;

    @FindBy(xpath = "//a[contains(text(),'Разделы')]")
    public WebElement section;

    @FindBy(xpath = "//ul[@class='b-topbar-more-list']/li[4]")
    public WebElement finance;

    @FindBy(xpath = "//iframe[2]")
    public WebElement popupFrame;

    @FindBy(className = "close")
    public WebElement popupWindowCloseButton;

    @FindBy(xpath = "//a[contains(text(),'Версия для смартфонов')]")
    public WebElement mobileVersionEnableLink;

    @FindBy(id = "menu_target")
    public WebElement mobileSection;

    @FindBy(xpath = "//ul[@id='responsive_menu']//span[@class=contains( text(),'Все разделы')]")
    //ul[@id='responsive_menu']//span[@class='b-aside-nav_item_title'][@class=contains( text(),'Все разделы')]
    public WebElement mobileMoreSections;

    @FindBy(xpath = "//i[@class='b-res-icon res1752 b-icon']/../span")
    public WebElement auto;

    public HomePage(WebDriver driver) { //конструктор класса
        super(driver);
    }

    public HomePage open() {
        driver.get("https://www.tut.by");
        return this;
    }

    public HomePage afishaOpen() {
        waitForElementVisible(afisha);
        afisha.click();
        return this;
    }

    public HomePage financeOpen() throws InterruptedException {
        waitForElementVisible(section);
        section.click();
        finance.click();
        return this;
    }

    public HomePage mobileVersionEnable() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //jse.executeScript("window.scrollBy(0,8000)", "");
        jse.executeScript("arguments[0].scrollIntoView(true);",mobileVersionEnableLink);
        waitForElementVisible(mobileVersionEnableLink);
        mobileVersionEnableLink.click();
        return this;
    }

    public HomePage autoOpen() throws InterruptedException {
        Thread.sleep(5000);
        driver.switchTo().frame(popupFrame);
        popupWindowCloseButton.click();
        Thread.sleep(2000);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        Thread.sleep(3000);
        mobileSection.click();
        Thread.sleep(3000);
        mobileMoreSections.click();
        Thread.sleep(2000);
        waitForElementVisible(auto);
        auto.click();
        return this;
    }
}