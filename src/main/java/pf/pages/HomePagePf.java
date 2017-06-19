package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePf extends AbstractPagePf {

    @FindBy(xpath = "//*[contains(text(),'Афиша')]")
    WebElement afisha;

    @FindBy(xpath = "//*[@class='b-topbar-more-list']/li[4]")
    WebElement finance;

    @FindBy(xpath = "//*[contains(text(),'Разделы')]")
    WebElement section;

    @FindBy(xpath = "//*[contains(text(),'Версия для смартфонов')]")
    WebElement mobileVersionEnableLink;

    @FindBy(id = "menu_target")
    WebElement mobileSection;
    //.//*[@id='menu_target']
    //*[@id="menu_target"]/i
    @FindBy(xpath = "//*[@id='responsive_menu']/li[6]/ul/li[9]/a/span")
            //"//*[@class='b-aside-nav_link')]")
    //"//*[@id='rubric_panel']/li[1]/a/span")
            //*[@id=\"rubric_panel\"]/li[1]/a/i")
            //"//*[@id='rubric_menu']/li[1]/a/span")
            //"//*[contains(text(),'Рубрики')]")
    WebElement mobileMoreSections;

    @FindBy(xpath = "//*[@id='section_list']/li[2]/ul/li[10]/a/span")
            //*[@id='rubric_menu']/li[12]/a/span")
    WebElement auto;

//    @FindBy(xpath = "//input[@value='Go']")
//    WebElement goButton;
    //(xpath = "//[@title='Финансы']")
    //*[@id="mainmenu"]/div/div/div/div/ul[2]/li[4]/a

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
        //Thread.sleep(180000);
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