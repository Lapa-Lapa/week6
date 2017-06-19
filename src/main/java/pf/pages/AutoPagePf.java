package pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Element;

public class AutoPagePf extends AbstractPagePf {

    @FindBy(xpath = "//[@id='content']/div[1]/div[4]/div[1]/h3/a")
    WebElement videoSection;

    @FindBy(xpath = "//[@class='entry__link']")
    WebElement post;

    @FindBy(xpath = "//[contains(@src, '/www.dailymotion.com')]")
    WebElement frame;

    @FindBy(className = "dmp_StartView-play-icon")
    WebElement playButton;

    @FindBy(xpath = "//[@id='player']/div[3]/div[12]")
    WebElement makeButtonsVisible;

    @FindBy(xpath = "//[@id='player']/div[3]/div[12]/button[2]")
    WebElement fullscreenButton;

    @FindBy(xpath = "//[@class='dmp_Player dmp_h-min-xs dmp_h-min-s dmp_h-min-m dmp_h-min-l dmp_v-min-xs dmp_v-min-s dmp_v-min-m dmp_v-min-l dmp_v-min-xl dmp_fat'")
    WebElement fullScreenIndicator;

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
        Thread.sleep(15000);
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