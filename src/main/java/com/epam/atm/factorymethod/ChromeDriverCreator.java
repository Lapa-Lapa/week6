package com.epam.atm.factorymethod;

import com.epam.atm.webdriverdecorator.CustomDriverDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

public class ChromeDriverCreator extends WebDriverCreator {
    @Override
    public WebDriver CreateCustomDriver() {
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(
                new File("src/main/resources/chromedriver.exe")).build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver(service);
        driver = new CustomDriverDecorator(driver);
        return driver;
    }
}