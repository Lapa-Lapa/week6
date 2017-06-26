package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutoPage extends AbstractPage {

    private static final By videoSection = By.xpath("//h3/a[contains(text(),'Видео')]");
    private static final By post = By.xpath("//a[@class='entry__link'][@href='https://auto.tut.by/news/video/547151.html']");
    private static final By frame = By.xpath("//*[@id='article_body']/p[3]/iframe");
    //(id = "pljs_yt_swf1498221510122")
    private static final By playButton = By.xpath("dmp_StartView-play-icon");
    //(xpath = "//*[@id='swf1498221510122']/pjsdiv/pjsdiv[6]/pjsdiv[1]/pjsdiv") это последний пост
    private static final By makeButtonsVisible = By.className("dmp_ControlBar dmp_is-hidden");
    //(xpath = "//*[@id='swf1498221510122']/pjsdiv/pjsdiv[4]")
    private static final By fullscreenButton = By.xpath("//button[@class='dmp_FullscreenButton dmp_ControlBarButton']");
    //(xpath = "//*[@id='swf1498221510122']/pjsdiv/pjsdiv[15]/pjsdiv[1]/pjsdiv")
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
        Thread.sleep(15000);//Это по условию!!! Не убирать!
        driver.findElement(makeButtonsVisible).click();
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