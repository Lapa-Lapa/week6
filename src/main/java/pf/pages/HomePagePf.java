package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePf extends AbstractPagePf {

    @FindBy(xpath = "//a[contains(text(),'Афиша')]")
    WebElement afisha;

    @FindBy(xpath = "//[@class='b-topbar-more-list']/li[4]")
    WebElement finance;

    @FindBy(xpath = "//[contains(text(),'Разделы')]")
    WebElement section;

    @FindBy(xpath = "//[contains(text(),'Версия для смартфонов')]")
    WebElement mobileVersionEnableLink;

    @FindBy(id = "menu_target")
    WebElement mobileSection;

    @FindBy(xpath = "//[@id='responsive_menu']/li[6]/ul/li[9]/a/span")
    WebElement mobileMoreSections;

    @FindBy(xpath = "//[@id='section_list']/li[2]/ul/li[10]/a/span")
    WebElement auto;

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
        waitForElementVisible(mobileSection);
        mobileSection.click();
        Thread.sleep(1000);
        mobileMoreSections.click();
        Thread.sleep(1000);
        auto.click();
        return this;
    }
}