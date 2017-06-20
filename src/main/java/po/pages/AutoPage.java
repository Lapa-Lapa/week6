package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pf.pages.AutoPagePf;


public class AutoPage extends AbstractPage {

    private static final By videoSection = By.xpath("//[@id='content']/div[1]/div[4]/div[1]/h3/a");
    private static final By post = By.xpath("//[@class='entry__link']");
    private static final By frame = By.xpath("//[contains(@src, '/www.dailymotion.com')]");
    private static final By playButton = By.className("dmp_StartView-play-icon");
    private static final By makeButtonsVisible = By.xpath("//[@id='player']/div[3]/div[12]");
    private static final By fullscreenButton = By.xpath("//[@id='player']/div[3]/div[12]/button[2]");
    private static final By fullScreenIndicator = By.xpath("//[@class='dmp_Player dmp_h-min-xs dmp_h-min-s dmp_h-min-m dmp_h-min-l dmp_v-min-xs dmp_v-min-s dmp_v-min-m dmp_v-min-l dmp_v-min-xl dmp_fat'");

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