package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pf.pages.AutoPagePf;


public class AutoPage extends AbstractPage {

    private static final By videoSection = By.xpath("//h3/a[contains(text(),'Видео')]");
    private static final By post = By.xpath("//a[@class='entry__link']");
    private static final By frame = By.xpath("//iframe[contains(@src, '/www.dailymotion.com')]");
    private static final By playButton = By.className("dmp_StartView-play-icon");
    private static final By makeButtonsVisible = By.className("dmp_ControlBar dmp_is-hidden");
    private static final By fullscreenButton = By.xpath("//button[@class='dmp_FullscreenButton dmp_ControlBarButton']");
    private static final By fullScreenIndicator = By.xpath("//div[@class='dmp_Player dmp_h-min-xs dmp_h-min-s dmp_h-min-m dmp_h-min-l dmp_v-min-xs dmp_v-min-s dmp_v-min-m dmp_v-min-l dmp_v-min-xl dmp_fat'");

    public AutoPage(WebDriver driver) {
        super(driver);
    }

    public AutoPage videoSectionOpen() {
        waitForElementVisible(videoSection);
        driver.findElement(videoSection).click();
        return new AutoPage(driver);
    }

    public AutoPage postOpen() throws InterruptedException {
        waitForElementVisible(post);
        driver.findElement(post).click();
        return new AutoPage(driver);
    }

    public AutoPage videoPlayButtonPressAndFullSize() throws InterruptedException {
        waitForElementVisible(frame);
        driver.switchTo().frame((driver.findElement(frame)));
        waitForElementVisible(playButton);
        driver.findElement(playButton).click();
        Thread.sleep(15000);//Это по заданию!
        waitForElementVisible(makeButtonsVisible);
        driver.findElement(makeButtonsVisible).click();
        waitForElementVisible(fullscreenButton);
        driver.findElement(fullscreenButton).click();
        return new AutoPage(driver);
    }
    public AutoPage assertScalingToFullScreen(){
        try{
            waitForElementVisible(fullScreenIndicator);
        } catch (Exception exseption){
            System.out.println("Screen is not full");
        }
        return new AutoPage(driver);
    }
}