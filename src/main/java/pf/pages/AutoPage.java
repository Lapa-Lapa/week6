package pf.pages;

import org.openqa.selenium.By;

public class AutoPage extends AbstractPage {

    private static final By videoSection = By.xpath("//h3/a[contains(text(),'Видео')]");
    private static final By post = By.xpath("//span[@class='entry-head _title'][contains(text(),'Видеофакт. Во Франции мотоцикл без водителя проехал несколько километров')]");
    private static final By frame = By.xpath("//*[@id='article_body']/p[3]/iframe");
    private static final By playButton = By.className("dmp_StartView-play-icon");
    private static final By makeButtonsVisible = By.id("dmp_Video");
    private static final By fullscreenButton = By.xpath("//button[@class='dmp_FullscreenButton dmp_ControlBarButton']");
    private static final By header = By.xpath("//h1[contains(text(),'Видеофакт. Во Франции мотоцикл без водителя проехал несколько километров')]");

    public AutoPage videoSectionOpen() {
        driver.findElement(videoSection).click();
        return new AutoPage();
    }

    public AutoPage postOpen() {
        driver.findElement(post).click();
        return new AutoPage();
    }

    public AutoPage videoPlayButtonPressAndFullSize() {
        try {
            driver.findElement(frame);
            driver.switchTo().frame(driver.findElement(frame));
            waitForElementVisible(playButton);
            driver.findElement(playButton).click();
            Thread.sleep(20000);//Это по условию!!! Не убирать!
            driver.findElement(makeButtonsVisible).click();
            Thread.sleep(500);
            waitForElementClicable(fullscreenButton);
            driver.findElement(fullscreenButton).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AutoPage();
    }

    public boolean getResultOfScalingToFullScreen() {
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        return isElementVisible(header);// Игорь!))Почему оно его видит если fullscreen и он не виден?
    }//А если не переключаться на window, то падает тест! :(
}