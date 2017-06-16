package pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePf extends AbstractPagePf { //унаследован от AbstractPagePf

    @FindBy(xpath = "//*[contains(text(),'Афиша')]")
    WebElement afisha;

    @FindBy(xpath = "//*[contains(text(),'Финансы')]")
    WebElement finance;

    @FindBy(xpath = "//*[contains(text(),'Разделы')]")
    WebElement section;

    @FindBy(xpath = "//*[contains(text(),'Авто')]")
    WebElement auto;

//    @FindBy(xpath = "//input[@value='Go']")
//    WebElement goButton;


    public HomePagePf(WebDriver driver) { //конструктор класса
        super(driver);
    }

    public HomePagePf open() {
        driver.get("https://www.tut.by");
        return this;
    }

    public HomePagePf afishaOpen () {
        afisha.click();
        return this;
    }

//    public HomePagePf fillSearchInput(String query) { //
//        searchInput.sendKeys(query);
//        return this;
//    }
//
//    public FilmsPagePf startSearch() {
//        goButton.click();
//        return new FilmsPagePf(driver);
//    }
}
