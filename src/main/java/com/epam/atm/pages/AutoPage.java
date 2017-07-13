package com.epam.atm.pages;

import com.epam.atm.utils.Logger;
import com.epam.atm.utils.SwitchTo;
import com.epam.atm.waiters.SmartWaiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;

import static com.epam.atm.waiters.ThreadSleep.waitElement;

public class AutoPage extends SmartWaiters {

    private static final By videoSection = By.xpath("//h3/a[contains(text(),'Видео')]");
    private static final By post = By.xpath("//span[@class='entry-head _title'][contains(text(),'Видеофакт. Во Франции мотоцикл без водителя проехал несколько километров')]");
    private static final By frame = By.xpath("//*[@id='article_body']/p[3]/iframe");
    private static final By playButton = By.className("dmp_StartView-play-icon");
    private static final By makeButtonsVisible = By.id("dmp_Video");
    private static final By fullscreenButton = By.xpath("//button[@class='dmp_FullscreenButton dmp_ControlBarButton']");

    public AutoPage videoSectionOpen() {
        driver.findElement(videoSection).click();
        Logger.info("Section video is open");
        return new AutoPage();
    }

    public AutoPage postOpen() {
        driver.findElement(post).click();
        Logger.info("Post is open");
        return new AutoPage();
    }

    public AutoPage videoPlayButtonPressAndFullSize() {
        SwitchTo.switchToFrame(driver,frame);
        waitForElementVisible(playButton);
        driver.findElement(playButton).click();
        waitElement(15000);
        driver.findElement(makeButtonsVisible).click();
        waitElement(500);
        waitForElementClicable(fullscreenButton);
        driver.findElement(fullscreenButton).click();
        Logger.info("Video full start");
        return new AutoPage();
    }

    public boolean getResultOfScalingToFullScreen() {
        waitElement(1000);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double height = screenSize.getHeight();
        WebElement frame = driver.findElement(By.xpath("//*[@id='article_body']/p[3]/iframe"));
        int frameheight = frame.getSize().getHeight();
        boolean result = false;
        if(frameheight >= (0.9*height)){
            result=true;
        }
        Logger.info("Results of test were collected");
        return result;
    }
}