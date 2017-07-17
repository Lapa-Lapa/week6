package com.epam.atm.pages;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementClicable;
import static com.epam.atm.waiters.HighlitersUnhighliters.waitForElementVisible;
import static com.epam.atm.waiters.AbstractPage.isElementPresent;
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
        driver.findElement(choseCreditButton).click();
        Logger.info("Credits page is open");
        return new FinancePage();
    }

    public FinancePage popupWindowClose() {
        try {
            waitForElementVisible(popUpWindowClose, driver);
            if (isElementPresent(popUpWindowClose, driver)) {
                waitForElementClicable(popUpWindowClose, driver);
                driver.findElement(popUpWindowClose).click();
                String window = driver.getWindowHandle();
                driver.switchTo().window(window);
                Logger.info("Pop-up window closed");
            }
        } catch (Exception exception) {
            Logger.error("Pop-up window not appear");
        }
        return new FinancePage();
    }

    public FinancePage setBankAsOptionForCredit(String BANK) {
        waitSetTime(500);
        waitForElementVisible(additionalOptionsForCredit, driver);
        driver.findElement(additionalOptionsForCredit).click();
        WebElement dropdown = driver.findElement(bankThatGiveCreditList);
        Select bank = new Select(dropdown);
        bank.selectByVisibleText(BANK);
        Logger.info("Bank is selected");
        return new FinancePage();
    }

    public FinancePage setSumOfCredit(int SUM_OF_CREDIT) {
        driver.findElement(sumOfCreditField).sendKeys(String.valueOf(SUM_OF_CREDIT));
        Logger.info("Summ of credit is set");
        return new FinancePage();
    }

    public Double getResults() {
        waitForElementVisible(tableWithResults, driver);
        driver.findElement(submitButton).click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        driver.findElement(sortByRateItem).click();//Assending
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        driver.findElement(sortByRateItem).click();//Desending
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        String results = driver.findElement(thehighestRate).getText();
        Logger.info("Results of test were collected");
        return Double.parseDouble(results.substring(0, 2));
    }
}