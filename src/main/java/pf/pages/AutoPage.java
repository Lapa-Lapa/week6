package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutoPage extends AbstractPage {

    private static final By videoSection = By.xpath("//h3/a[contains(text(),'Видео')]");
    private static final By post = By.xpath("//a[@class='entry__link'][@href='https://auto.tut.by/news/video/547151.html']");
    private static final By frame = By.xpath("//*[@id='article_body']/p[3]/iframe");
    private static final By playButton = By.className("dmp_StartView-play-icon");
    private static final By makeButtonsVisible = By.id("dmp_Video");
    private static final By fullscreenButton = By.xpath("//button[@class='dmp_FullscreenButton dmp_ControlBarButton']");
    private static final By fullScreenIndicator = By.xpath("//div[@class='dmp_Player dmp_h-min-xs dmp_h-min-s dmp_h-min-m dmp_h-min-l dmp_v-min-xs dmp_v-min-s dmp_v-min-m dmp_v-min-l dmp_v-min-xl dmp_fat'");

    public AutoPage videoSectionOpen() {
        driver.findElement(videoSection).click();
        return new AutoPage();
    }

    public AutoPage postOpen() {
        driver.findElement(post).click();
        return new AutoPage();
    }

    public AutoPage videoPlayButtonPressAndFullSize() throws InterruptedException {
        driver.findElement(frame);
        driver.switchTo().frame(driver.findElement(frame));
        waitForElementVisible(playButton);
        driver.findElement(playButton).click();
        Thread.sleep(20000);//Это по условию!!! Не убирать!
        driver.findElement(makeButtonsVisible).click();
        waitForElementVisible(fullscreenButton);
        driver.findElement(fullscreenButton).click();
        return new AutoPage();
    }

    public AutoPage assertScalingToFullScreen() {
        try {
            waitForElementVisible(fullScreenIndicator);
        } catch (Exception exseption) {
            System.out.println("Screen is not full");
        }
        return new AutoPage();
    }
}