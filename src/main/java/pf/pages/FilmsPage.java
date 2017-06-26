package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class FilmsPage extends AbstractPage {

    private static final int TIME = 20;
    private static final By FILMS = By.xpath("//a[contains(text(),'Кино')]");
    private static final By arrowRightAvailableDates = By.xpath("//i[@class='icon-right a-icon']");
    private static final By date9Jule = By.xpath("//a[@title='воскресенье, 9 июля']");
    private static final By popupWindowCloseButton = By.id("closebtn");
    private static final By popupFrame = By.xpath("//iframe[contains(@src, 'https://api.traq.li/publisher/unattended')]");
    private static final By defaultTimeStartPosition = By.xpath("//div[@id='slider']/div/div[1]/div");
    private static final By filmTransformers = By.xpath("//span[contains(text(),'Трансформеры: Последний рыцарь')]");
    private static final By areaForShots = By.xpath("//div[@class='fotorama__thumb-border']");
    private static final By anyPicture = By.xpath("//img[contains(@src, 'https://img.afisha.tut.by/img/138x72c/screens')]");

    public FilmsPage openFilms() {
        driver.findElement(FILMS).click();
        return new FilmsPage();
    }

    public FilmsPage selectDate() {
        for (int i = 0; i < 10; i++) {
            driver.findElement(arrowRightAvailableDates).click();
            try {
                driver.findElement(date9Jule).click();
                break;
            } catch (Exception exception) {
            }
        }
        return new FilmsPage();
    }

    public FilmsPage popupWindowClose() throws InterruptedException {
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(popupFrame));
        waitForElementClicable(popupWindowCloseButton);
        driver.findElement(popupWindowCloseButton).click();
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        return new FilmsPage();
    }

    public FilmsPage selectTime() throws InterruptedException {
        driver.navigate().refresh();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(filmTransformers));
        Actions actions = new Actions(driver);
        if (TIME <= 17) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47)), 0).click().release().perform();
        }
        if (TIME >= 20) {
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(385, 0).click().release().perform();
            Thread.sleep(1000);
            actions.click(driver.findElement(defaultTimeStartPosition)).moveByOffset(((int) Math.round((TIME - 9) * 47.5) - 385), 0).click().release().perform();
        }
        Thread.sleep(1000);
        return new FilmsPage();
    }

    public FilmsPage selectFilm() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(FILMS));
        driver.findElement(filmTransformers).click();
        return new FilmsPage();
    }

    public int allShots() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(areaForShots));
        waitForElementVisible(areaForShots);
        final List<WebElement> pictures = driver.findElements(anyPicture);
        int i = pictures.size();
        return i;
    }
}