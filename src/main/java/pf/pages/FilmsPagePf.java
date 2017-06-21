package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilmsPagePf extends AbstractPagePf {

    @FindBy(xpath = "//a[contains(text(),'Кино')]")
    public WebElement films;

    @FindBy(xpath = "//i[@class='icon-right a-icon']")
    public WebElement arrowRightAvailableDates;

    @FindBy(xpath = "//a[@title='воскресенье, 9 июля']")
    public WebElement date9Jule;

    @FindBy(id = "closebtn")
    public WebElement popupWindowCloseButton;

    @FindBy(xpath = "//iframe[contains(@src, 'https://api.traq.li/publisher/unattended')]")
    public WebElement popupFrame;

    @FindBy(xpath = "//div[@id='slider']/div/div[1]/div")
    public WebElement defaultTimeStartPosition;

    @FindBy(xpath = "//span[contains(text(),'Трансформеры: Последний рыцарь')]")
    public WebElement filmTransformers;

    @FindBy(xpath = "//div[@class='fotorama__thumb-border']")
    public WebElement areaForShots;

    @FindBy(xpath = "//img[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]")
    public List<WebElement> anyPicture;

    final Integer TIME = 20;

    public FilmsPagePf(WebDriver driver) {
        super(driver);
    }

    public FilmsPagePf openFilms() {
        waitForElementVisible(films);
        films.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf moveAvailableDatesToRight() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            waitForElementVisible(arrowRightAvailableDates);
            arrowRightAvailableDates.click();
            try {
                date9Jule.click();
                break;
            } catch (Exception exception) {
            }
        }
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectDate() throws InterruptedException {
        waitForElementVisible(date9Jule);
        date9Jule.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf popupWindowClose() throws InterruptedException {
        String window = driver.getWindowHandle();
        Thread.sleep(3000);
        driver.switchTo().frame(popupFrame);
        waitForElementVisible(popupWindowCloseButton);
        popupWindowCloseButton.click();
        driver.switchTo().window(window);
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectTime() throws InterruptedException {
        Actions actions = new Actions(driver);
        waitForElementVisible(defaultTimeStartPosition);
        int a = TIME * 27;
        if (a >= 385) {
            int b = a - 385;
            actions.click(defaultTimeStartPosition).moveByOffset(385, 0).click().release().perform();
            actions.click(defaultTimeStartPosition).moveByOffset(b, 0).click().release().perform();
        }
        if (a < 385) {
            actions.click(defaultTimeStartPosition).moveByOffset(a, 0).click().release().perform();
        }
        Thread.sleep(1000);
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectFilm() throws InterruptedException {
        waitForElementVisible(filmTransformers);
        filmTransformers.click();
        return new FilmsPagePf(driver);
    }

    public int allShots() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,pageYOffset)", "");
        waitForElementVisible(areaForShots);
        int i = anyPicture.size();
        return i;
    }
}