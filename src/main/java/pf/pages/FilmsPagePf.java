package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilmsPagePf extends AbstractPagePf {

    @FindBy(xpath = "//*[contains(text(),'Кино')]")
    WebElement films;

    @FindBy(xpath = "//*[@class='icon-right a-icon']")
    WebElement arrowRightAvailableDates;

    @FindBy(xpath = "//*[@title='воскресенье, 9 июля']")
    WebElement date9Jule;

    @FindBy(id = "closebtn")
    WebElement popupWindowCloseButton;

//    @FindBy(className = "closebtn icon-close")
//    WebElement popupWindowCloseButton;

    @FindBy(xpath = "//*[contains(@src, 'https://api.traq.li/publisher/unattended')]")
//*[@src='https://api.traq.li/publisher/unattended/48?wv=3&v=3.0.96_g.4784_s.2536']")
        //https://api.traq.li/publisher/unattended/48?wv=3&v=3.0.96_g.4768_s.2525']
        //https://api.traq.li/publisher/unattended/48?wv=3&v=3.0.96_g.4785_s.2536
            WebElement popupFrame;

//    @FindBy(xpath = "//span[contains(text(),'Ночью с 22:00 до 2:00')]")//<-Walkaround
//            //(xpath = "//*[@id='slider']/div/div[1]/div")
//            //(xpath = "//*[@style='left: 5.55556%;'")
//            //(xpath = "//*[@id='slider']/div/div[1]/div/span.crange-f | 45.6x18.8")
//            WebElement defaultTimeStartPosition;

    @FindBy(css = "div.noUi-handle.noUi-handle-lower")
    WebElement attenptFromIDE;

    @FindBy(xpath = "//*[@id=\"time-slider-times\"]/tbody/tr/td[1]/span")
    WebElement time8am;

    @FindBy(xpath = "//*[@id=\"time-slider-times\"]/tbody/tr/td[2]/span")
    WebElement time10am;

    @FindBy(xpath = "//*[@id=\"time-slider-times\"]/tbody/tr/td[4]/span")
    WebElement time2pm;

    @FindBy(xpath = "//*[@id=\"time-slider-times\"]/tbody/tr/td[5]/span")
    WebElement time4pm;

    @FindBy(xpath = "//*[@id=\"time-slider-times\"]/tbody/tr/td[6]/span")
    WebElement time6pm;

    @FindBy(xpath = "//*[@id=\"time-slider-times\"]/tbody/tr/td[7]/span")
    //(xpath = "//*[contains(text(),'left: 66.7284%;')]")
            WebElement time8pm;
    @FindBy(xpath = "//*[@id=\"slider\"]/div/div[1]/div")
    //(xpath = "//*[@id=\"slider\"]/div/div[1]/div/span")
            WebElement defaultTimeStartPosition;

    @FindBy(xpath = "//*[contains(text(),'Трансформеры: Последний рыцарь')]")
    WebElement filmTransformers;

    @FindBy(xpath = "//*[@class='fotorama__thumb-border']")
    //fotorama__nav fotorama__nav--thumbs
            WebElement areaForShots;

    @FindBy(xpath="//*[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]")
            //*[contains(text(),'width: 120px;')]")
    WebElement anyPicture;

    String window;

    public FilmsPagePf(WebDriver driver) {
        super(driver);
    }

    public FilmsPagePf openFilms() {
        waitForElementVisible(films);
        films.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf moveAvailableDatesToRight() throws InterruptedException {
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        waitForElementVisible(arrowRightAvailableDates);
        arrowRightAvailableDates.click();
        Thread.sleep(2000);
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectDate() {
        waitForElementVisible(date9Jule);
        date9Jule.click();
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf popupWindowClose() throws InterruptedException {
        window = driver.getWindowHandle();
        Thread.sleep(3000);
        driver.switchTo().frame(popupFrame);
        waitForElementVisible(popupWindowCloseButton);
        popupWindowCloseButton.click();
        driver.switchTo().window(window);
        return new FilmsPagePf(driver);
    }

    public FilmsPagePf selectTime() throws InterruptedException {
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.click(defaultTimeStartPosition).moveByOffset(385, 0).click().release().perform();
        Thread.sleep(1000);
        actions.click(defaultTimeStartPosition).moveByOffset(130, 0).click().release().perform();
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
        jse.executeScript("window.scrollBy(0,3200)", "");
        waitForElementVisible(areaForShots);
//        List<WebElement> optionCount = driver.findElements(By.xpath("//*[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]"));
//        System.out.println(optionCount.size());
        int i = areaForShots.findElements(By.xpath("//*[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]")).size();
        System.out.println(i);
        //https://img.afisha.tut.by/img/138x72c/screens/10/3/transformery-posledniy-rycar-087923.jpg
        ////*[@class='fotorama__img'
        //#event-photos > div > div.fotorama__nav-wrap > div > div > div:nth-child(5) > div > img
        Thread.sleep(5000);
        return i;
    }
}