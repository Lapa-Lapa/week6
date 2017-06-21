package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FilmsPage extends AbstractPage {

    private static final By films = By.xpath("//a[contains(text(),'Кино')]");
    private static final By arrowRightAvailableDates = By.xpath("//i[@class='icon-right a-icon']");
    private static final By date9Jule = By.xpath("//a[@title='воскресенье, 9 июля']");
    private static final By popupWindowCloseButton = By.id("closebtn");
    private static final By popupFrame = By.xpath("//iframe[contains(@src, 'https://api.traq.li/publisher/unattended')]");
    private static final By defaultTimeStartPosition = By.xpath("//div[@id='slider']/div/div[1]/div");
    private static final By filmTransformers = By.xpath("//span[contains(text(),'Трансформеры: Последний рыцарь')]");
    private static final By areaForShots = By.xpath("//div[@class='fotorama__thumb-border']");
    private static final By anyPicture = By.xpath("//img[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]");
    private static final Integer TIME = 20;
    private static String window="";

    public FilmsPage(WebDriver driver) {
        super(driver);
    }
    public FilmsPage openFilms() throws InterruptedException {
        Thread.sleep(5000);
        waitForElementVisible(films);
        driver.findElement(films).click();
        return new FilmsPage(driver);
    }

    public FilmsPage moveAvailableDatesToRight(){
        for (int i = 0; i < 10; i++) {
            driver.findElement(arrowRightAvailableDates);
            driver.findElement(arrowRightAvailableDates).click();
            try {
                driver.findElement(date9Jule).click();
                break;
            } catch (Exception exception) {
            }
        }
        return new FilmsPage(driver);
    }

    public FilmsPage selectDate() {
        driver.findElement(date9Jule).click();
        return new FilmsPage(driver);
    }

    public FilmsPage popupWindowClose() throws InterruptedException {
        window = driver.getWindowHandle();
        Thread.sleep(3000);
        driver.switchTo().frame((driver.findElement(popupFrame)));
        driver.findElement(popupWindowCloseButton).click();
        driver.switchTo().window(window);
        return new FilmsPage(driver);
    }

    public FilmsPage selectTime() {
        Actions actions = new Actions(driver);
        waitForElementVisible(defaultTimeStartPosition);
        int a = TIME * 27;
        if (a >= 385) {
            int b = a - 385;
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(385, 0).click().release().perform();
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(b, 0).click().release().perform();
        }
        if (a < 385) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(a, 0).click().release().perform();
        }
        return new FilmsPage(driver);
    }

    public FilmsPage selectFilm() throws InterruptedException {
        driver.findElement(filmTransformers).click();
        return new FilmsPage(driver);
    }

    public int allShots() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,pageYOffset)", "");
        waitForElementVisible(areaForShots);
        int i =  driver.findElement(areaForShots).findElements( By.partialLinkText("https://img.afisha.tut.by/img/138x72c/screens")).size();
        return i;
    }
}