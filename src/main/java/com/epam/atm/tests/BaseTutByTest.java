package com.epam.atm.tests;

import com.epam.atm.driver.WebDriverSingleton;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(strict = true, plugin = {"json:target/cucumber-report.json",
        "json:target/cucumber-report"}, tags = "@Test", features = "src/main/resources/cucumber_features/tutby.feature",
        glue = {"com.epam.atm.utils"})

public class BaseTutByTest extends AbstractTestNGCucumberTests {
    private static WebDriver driver = WebDriverSingleton.getWebDriverInstance();

    @BeforeClass
    public static void startBrowser() {
        WebDriverSingleton.getWebDriverInstance();
    }

    @AfterClass
    public static void closeSelenium() {
        WebDriverSingleton.kill();
    }
}