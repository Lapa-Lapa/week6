package pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePf extends AbstractPagePf {

    @FindBy(xpath = "//*[contains(text(),'Афиша')]")
    WebElement afisha;

    @FindBy (xpath ="//*[@class='b-topbar-more-list']/li[4]")
            WebElement finance;

    @FindBy(xpath = "//*[contains(text(),'Разделы')]")
    WebElement section;

    @FindBy(xpath = "//*[contains(text(),'Авто')]")
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
        //waitForElementVisible(finance);
        finance.click();
        return this;
    }

    public HomePagePf autoOpen() {
        waitForElementVisible(section);
        section.click();
        waitForElementVisible(auto);
        auto.click();
        return this;
    }
}