package com.epam.atm.tests;

import com.epam.atm.driver.WebDriverSingleton;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(strict = true, plugin = {"json:target/cucumber-report.json",
        "json:target/cucumber-report"}, features = "src/main/resources/cucumber_features/tutby.feature",
        glue = { "com.epam.atm.steps"})

public class BaseTutByTest extends AbstractTestNGCucumberTests {
    @BeforeClass
    public static void startBrowser() {
        WebDriverSingleton.getWebDriverInstance();
    }

    @AfterClass
    public static void closeSelenium() {
        WebDriverSingleton.kill();
    }
}