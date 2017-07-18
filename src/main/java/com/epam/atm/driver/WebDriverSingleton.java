package com.epam.atm.driver;

import com.epam.atm.factorymethod.ChromeDriverCreator;
import com.epam.atm.factorymethod.WebDriverCreator;
import com.epam.atm.utils.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static WebDriver instance;
    private static final int SECONDS = 25;

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriverInstance() {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    private static WebDriver init() {
        WebDriverCreator creator = new ChromeDriverCreator();
        WebDriver driver = creator.CreateCustomDriver();
        driver.manage().timeouts().pageLoadTimeout(SECONDS, TimeUnit.SECONDS);
        Logger.info("РageLoadTimeout set: "+SECONDS+" seconds");
        driver.manage().timeouts().implicitlyWait(SECONDS, TimeUnit.SECONDS);
        Logger.info("ImplicitlyWait set: "+SECONDS+" seconds");
        driver.manage().timeouts().setScriptTimeout(SECONDS, TimeUnit.SECONDS);
        Logger.info("ScroptTimeout set: "+SECONDS+" seconds");
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