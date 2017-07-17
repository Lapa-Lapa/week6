package com.epam.atm.pages;

import com.epam.atm.factorymethod.WebDriverCreator;
import com.epam.atm.utils.Logger;
import com.epam.atm.utils.SwitchTo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementClicable;
import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementVisible;
import static com.epam.atm.waiters.ThreadSleep.waitSetTime;

public class AutoPage extends WebDriverCreator {
    @Override
    public WebDriver CreateCustomDriver() {
        return null;
    }

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
        SwitchTo.switchToFrame(driver, frame);
        waitForElementVisible(playButton,driver);
        driver.findElement(playButton).click();
        waitSetTime(15000);
        driver.findElement(makeButtonsVisible).click();
        waitSetTime(500);
        waitForElementClicable(fullscreenButton,driver);
        driver.findElement(fullscreenButton).click();
        Logger.info("Video full start");
        return new AutoPage();
    }

    public boolean getResultOfScalingToFullScreen() {
        waitSetTime(700);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double height = screenSize.getHeight();
        if (driver.findElement(By.xpath("//*[@id='article_body']/p[3]/iframe")).getSize().getHeight() >= (0.9 * height)) {
            return true;
        }
        return false;
    }
}