package com.epam.atm.driver;

import com.epam.atm.factorymethod.ChromeDriverCreator;
import com.epam.atm.factorymethod.WebDriverCreator;
import com.epam.atm.utils.Logger;
import org.openqa.selenium.WebDriver;

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
//        System.setProperty("webdriverdecorator.chrome.driver", "src/main/resources/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        WebDriverCreator creator = new ChromeDriverCreator();
        WebDriver driver = creator.CreateCustomDriver();
//          *      *       *       *       *
//        WebDriver driver = null;
//        try {
//            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.browser());
//        } catch (MalformedURLException e) {
//            Logger.error("Error with creating URL");
//            e.printStackTrace();
//        }
//          *      *       *       *       *
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(35, TimeUnit.SECONDS);
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