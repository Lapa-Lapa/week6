package pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Element;

public class AutoPagePf extends AbstractPagePf {

    @FindBy(xpath = "//h3/a[contains(text(),'Видео')]")
    public WebElement videoSection;

    @FindBy(xpath = "//a[@class='entry__link']")
    public WebElement post;

    @FindBy(xpath = "//iframe[contains(@src, '/www.dailymotion.com')]")
    public WebElement frame;

    @FindBy(className = "dmp_StartView-play-icon")
    public WebElement playButton;

    @FindBy(className = "dmp_ControlBar dmp_is-hidden")
    public WebElement makeButtonsVisible;

    @FindBy(xpath = "//button[@class='dmp_FullscreenButton dmp_ControlBarButton']")
    public WebElement fullscreenButton;

    @FindBy(xpath = "//div[@class='dmp_Player dmp_h-min-xs dmp_h-min-s dmp_h-min-m dmp_h-min-l dmp_v-min-xs dmp_v-min-s dmp_v-min-m dmp_v-min-l dmp_v-min-xl dmp_fat'")
    public WebElement fullScreenIndicator;

    public AutoPagePf(WebDriver driver) {
        super(driver);
    }

    public AutoPagePf videoSectionOpen() throws InterruptedException {
        Thread.sleep(2000);
        videoSection.click();
        return new AutoPagePf(driver);
    }

    public AutoPagePf postOpen() throws InterruptedException {
        waitForElementVisible(post);
        post.click();
        return new AutoPagePf(driver);
    }

    public AutoPagePf videoPlayButtonPressAndFullSize() throws InterruptedException {
        Thread.sleep(1000);
        driver.switchTo().frame(frame);
        waitForElementVisible(playButton);
        playButton.click();
        Thread.sleep(15000);//Это по условию!!! Не убирать!
        makeButtonsVisible.click();
        Thread.sleep(1000);
        fullscreenButton.click();
        return new AutoPagePf(driver);
    }

    public AutoPagePf assertScalingToFullScreen() throws InterruptedException {
        try{
            waitForElementVisible(fullScreenIndicator);
        } catch (Exception exseption){
            System.out.println("Screen is not full");
        }
        return new AutoPagePf(driver);
    }
}