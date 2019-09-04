package com.epam.atm.pages;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.utils.MyLogger;
import com.epam.atm.utils.WorkWithFrames;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;


import static com.epam.atm.waiters.HighlitersUnhighliters.*;
import static com.epam.atm.waiters.ThreadSleep.waitSetTime;

public class AutoPage {
    WebDriver driver;
    private static final By videoSection = By.xpath("//h3/a[contains(text(),'Видео')]");
    private static final By post = By.xpath("//span[@class='entry-head _title'][contains(text(),'Видеофакт. Во Франции мотоцикл без водителя проехал несколько километров')]");
    private static final By frame = By.xpath("//*[@id='article_body']/p[3]/iframe");
    private static final By playButton = By.className("dmp_StartView-play-icon");
    private static final By videoManagementTools = By.id("dmp_Video");
    private static final By fullscreenButton = By.xpath("//button[@class='dmp_FullscreenButton dmp_ControlBarButton']");

    public AutoPage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
    }

    public AutoPage videoSectionOpen() {
        highlightClickUnhighlightElement(videoSection, driver);
        MyLogger.info("Section video is open");
        return new AutoPage();
    }

    public AutoPage postOpen() {
        highlightClickUnhighlightElement(post,driver);
        MyLogger.info("Post is open");
        return new AutoPage();
    }

    public AutoPage videoPlayButtonPressAndFullSize() {
        WorkWithFrames.switchToFrame(driver, frame);
        highlightClickUnhighlightElement(playButton, driver);
        MyLogger.warn("Thread sllep for 20 seconds");
        waitSetTime(20000);
        highlightClickUnhighlightElement(videoManagementTools, driver);
        waitSetTime(500);
        highlightClickUnhighlightElement(fullscreenButton, driver);
        MyLogger.info("Video full start");
        return new AutoPage();
    }

    public boolean getResultOfScalingToFullScreen() {
        waitSetTime(700);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double height = screenSize.getHeight();
        takeScreenshot(driver);
        if (driver.findElement(By.xpath("//*[@id='article_body']/p[3]/iframe")).getSize().getHeight() >= (0.9 * height)) {
            MyLogger.debug("Smth get wrong");

            return true;
        }
        return false;
    }
}