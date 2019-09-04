package com.epam.atm.pages;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.epam.atm.waiters.HighlitersUnhighliters.highlightClickUnhighlightElement;
import static com.epam.atm.waiters.HighlitersUnhighliters.takeScreenshot;
import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementVisible;
import static com.epam.atm.waiters.ThreadSleep.waitSetTime;

public class FinancePage {
    WebDriver driver;
    private static final By choseCreditButton = By.xpath("//a[@class='button flat-white']");
    private static final By popUpWindowClose = By.className("scrollable-close");
    private static final By additionalOptionsForCredit = By.id("show_full_filter");
    private static final By bankThatGiveCreditList = By.xpath("//select[@id='bank_list']");
    private static final By sumOfCreditField = By.name("sum");
    private static final By submitButton = By.name("submit");
    private static final By tableWithResults = By.xpath("//div[@id='kred_compare']/table");
    private static final By sortByRateItem = By.xpath("//span[contains(text(),'Ставка')]");
    private static final By thehighestRate = By.xpath("//div[@class='wrapper']/big");

    public FinancePage() {
        this.driver = WebDriverSingleton.getWebDriverInstance();
    }

    public FinancePage pressChoseCreditButton() {
        highlightClickUnhighlightElement(choseCreditButton,driver);
        MyLogger.info("Credits page is open");
        takeScreenshot(driver);
        return new FinancePage();
    }

    public FinancePage popupWindowClose() {
        try {
            highlightClickUnhighlightElement(popUpWindowClose,driver);
            MyLogger.info("Pop-up window closed");
        } catch (Exception exception) {
            MyLogger.error("Pop-up window not appear");
        }
        takeScreenshot(driver);
        return new FinancePage();
    }

    public FinancePage setBankAsOptionForCredit(String BANK) {
        waitSetTime(500);
        waitForElementVisible(additionalOptionsForCredit, driver);
        highlightClickUnhighlightElement(additionalOptionsForCredit,driver);
        WebElement dropdown = driver.findElement(bankThatGiveCreditList);
        Select bank = new Select(dropdown);
        bank.selectByVisibleText(BANK);
        MyLogger.info("Bank is selected");
        takeScreenshot(driver);
        return new FinancePage();
    }

    public FinancePage setSumOfCredit(int SUM_OF_CREDIT) {
        driver.findElement(sumOfCreditField).sendKeys(String.valueOf(SUM_OF_CREDIT));
        MyLogger.info("Summ of credit is set");
        takeScreenshot(driver);
        return new FinancePage();
    }

    public Double getResults() {
        waitForElementVisible(tableWithResults, driver);
        highlightClickUnhighlightElement(submitButton,driver);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        highlightClickUnhighlightElement(sortByRateItem,driver);
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        highlightClickUnhighlightElement(sortByRateItem,driver);
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        String results = driver.findElement(thehighestRate).getText();
        MyLogger.info("Results of test were collected");
        takeScreenshot(driver);
        return Double.parseDouble(results.substring(0, 2));
    }
}