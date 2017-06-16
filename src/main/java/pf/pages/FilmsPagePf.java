package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilmsPagePf extends AbstractPagePf {

    @FindBy(xpath = "//*[contains(text(),'Кино')]")
    WebElement films;

    @FindBy(xpath = "//*[@class='icon-right a-icon']")
    WebElement arrowRightAvailableDates;

    //    @FindBy(xpath = "//*[contains(text(),'9 июля')]")
//    WebElement date9Jule;
    @FindBy(xpath = "//*[@title='воскресенье, 9 июля']")
    WebElement date9Jule;
    //    @FindBy(xpath = "//*[contains(text(),'left: 66.7284%;')]")
//    WebElement time8pm;
    @FindBy(xpath = "//*[contains(text(),'20:00')]")
    WebElement time8pm;

    @FindBy(xpath = "//*[contains(text(),'Трансформеры: Последний рыцарь')]")
    WebElement filmTransformers;

    @FindBy(xpath = "//*[@class='fotorama__nav fotorama__nav--thumbs']")
    WebElement areaForShots;

    @FindBy(xpath = "//*[contains(text(),'width: 120px;')]")
    WebElement anyPicture;

    public FilmsPagePf(WebDriver driver) {
        super(driver);
    }

    public FilmsPagePf openFilms() {
        waitForElementVisible(films);
        films.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf moveAvailableDatesToRight() {
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectDate() {
        waitForElementVisible(date9Jule);
        date9Jule.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectTime() throws InterruptedException {
        Thread.sleep(1000);
        time8pm.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectFilm() throws InterruptedException {
        waitForElementVisible(filmTransformers);
        filmTransformers.click();
        return new FilmsPagePf(driver);
    }

    public int allShots() {
        waitForElementVisible(areaForShots);
        int i = areaForShots.findElements(By.xpath("//*[contains(text(),'width: 120px;')]")).size();
        return i;
    }
}