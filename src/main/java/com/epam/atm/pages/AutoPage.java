package com.epam.atm.pages;

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
    private static final By header = By.xpath("//h1[contains(text(),'Видеофакт. Во Франции мотоцикл без водителя проехал несколько километров')]");

    public AutoPage videoSectionOpen() {
        driver.findElement(videoSection).click();
        System.out.println("Section video is open");
        return new AutoPage();
    }

    public AutoPage postOpen() {
        driver.findElement(post).click();
        System.out.println("Post is open");
        return new AutoPage();
    }

    public AutoPage videoPlayButtonPressAndFullSize() {
        driver.findElement(frame);
        driver.switchTo().frame(driver.findElement(frame));
        waitForElementVisible(playButton);
        driver.findElement(playButton).click();
        waitElement(20000);
        driver.findElement(makeButtonsVisible).click();
        waitElement(500);
        waitForElementClicable(fullscreenButton);
        driver.findElement(fullscreenButton).click();
        System.out.println("Video start");
        return new AutoPage();
    }

    public boolean getResultOfScalingToFullScreen() {
        waitElement(1000);
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        WebElement frame = driver.findElement(By.xpath("//*[@id='article_body']/p[3]/iframe"));
        int frameheight = frame.getSize().getHeight();
        System.out.println(frameheight);
        System.out.println(height);
        boolean result = false;
        if(frameheight >= (0.9*height)){
            result=true;
        }
        System.out.println("Results of test were collected");
        return result;// Игорь!))Почему оно его видит если fullscreen и он не виден?
    }//А если не переключаться на window, то падает тест! :(
}