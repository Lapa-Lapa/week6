package com.epam.atm.driver;

import com.epam.atm.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static WebDriver instance;

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriverInstance() {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    private static WebDriver init() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//          *      *       *       *       *
//        WebDriver driver = null;
//        try {
//            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.browser());
//        } catch (MalformedURLException e) {
//            Logger.error("Error with creating URL");
//            e.printStackTrace();
//        }
//          *      *       *       *       *
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);//week5
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//week5
        driver.manage().timeouts().setScriptTimeout(35,TimeUnit.SECONDS);//next line 19 slide
        //https://www.slideshare.net/ssuser220b38/java-explicit-and-implicit-wait-testing-ajax-applications
        driver.manage().window().maximize();
        return driver;
    }

    public static void kill() {
        if (instance != null) {
            try {
                instance.quit();
            } catch (Exception e) {
                Logger.error("Cannot kill browser");
            } finally {
                instance = null;
            }
        }
    }
}