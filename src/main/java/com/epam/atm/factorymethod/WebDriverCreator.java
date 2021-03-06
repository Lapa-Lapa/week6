package com.epam.atm.factorymethod;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverCreator {

    protected WebDriver driver;

    public abstract WebDriver CreateCustomDriver();
}