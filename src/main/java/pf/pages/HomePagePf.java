package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePf extends AbstractPagePf {

    @FindBy(xpath = "//a[contains(text(),'Афиша')]")
    public WebElement afisha;

    @FindBy(xpath = "//a[contains(text(),'Разделы')]")
    public WebElement section;

    @FindBy(xpath = "//ul[@class='b-topbar-more-list']/li[4]")
    public WebElement finance;

    @FindBy(xpath = "//iframe[1]")
    public WebElement popupFrame;

    @FindBy(id = "smartwelcomeClose")
    public WebElement popupWindowCloseButton;

    @FindBy(xpath = "//a[contains(text(),'Версия для смартфонов')]")
    public WebElement mobileVersionEnableLink;

    @FindBy(id = "menu_target")
    public WebElement mobileSection;

    @FindBy(xpath = "//ul[@id='responsive_menu']//span[@class=contains( text(),'Все разделы')]")
            //ul[@id='responsive_menu']//span[@class='b-aside-nav_item_title'][@class=contains( text(),'Все разделы')]
    public WebElement mobileMoreSections;

    @FindBy(xpath = "//i[@class='b-res-icon res1752 b-icon']")
    public WebElement auto;

    public HomePagePf(WebDriver driver) { //конструктор класса
        super(driver);
    }

    public HomePagePf open() {
        driver.get("https://www.tut.by");
        return this;
    }

    public HomePagePf afishaOpen() {
        waitForElementVisible(afisha);
        afisha.click();
        return this;
    }

    public HomePagePf financeOpen() throws InterruptedException {
        waitForElementVisible(section);
        section.click();
        finance.click();
        return this;
    }

    public HomePagePf mobileVersionEnable() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,8000)", "");
        waitForElementVisible(mobileVersionEnableLink);
        mobileVersionEnableLink.click();
        return this;
    }

    public HomePagePf autoOpen() throws InterruptedException {
        String window = driver.getWindowHandle();
        Thread.sleep(2000);
        driver.switchTo().frame(popupFrame);
        Thread.sleep(2000);
        popupWindowCloseButton.click();
        driver.switchTo().window(window);
        waitForElementVisible(mobileSection);
        mobileSection.click();
        Thread.sleep(3000);
        mobileMoreSections.click();
        Thread.sleep(2000);
        auto.click();
        return this;
    }
}