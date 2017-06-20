package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FinancePagePf extends AbstractPagePf {

    @FindBy(xpath = "//[@class='button flat-white']")
    WebElement choseCreditButton;

    @FindBy(xpath = "//[contains(text(),'Все условия кредитования')]")
    WebElement additionalOptionsForCredit;

    @FindBy(xpath = "//[@id='bank_list']")
    WebElement bankThatGiveCreditList;

    @FindBy(name = "sum")
    WebElement sumOfCreditField;

    @FindBy(name = "submit")
    WebElement submitButton;

    @FindBy(xpath = "//[@id='kred_compare']/table")
    WebElement tableWithResults;

    @FindBy(xpath = "//span[contains(text(),'Ставка')]")
    WebElement sortByRateItem;

    @FindBy(xpath = "//[@class='wrapper']/big")
    WebElement thehighestRate;

    public FinancePagePf(WebDriver driver) {
        super(driver);
    }

    public FinancePagePf pressChoseCreditButton() {
        waitForElementVisible(choseCreditButton);
        choseCreditButton.click();
        return new FinancePagePf(driver);
    }

    public FinancePagePf setBelarusBankAsOptionForCredit() throws InterruptedException {
        waitForElementVisible(additionalOptionsForCredit);
        additionalOptionsForCredit.click();
        WebElement dropdown = bankThatGiveCreditList;
        Select bank = new Select(dropdown);
        bank.selectByIndex(8);
        Thread.sleep(5000);
        return new FinancePagePf(driver);
    }

    public FinancePagePf setSumOfCredit() {
        waitForElementVisible(sumOfCreditField);
        sumOfCreditField.sendKeys("3000");
        return new FinancePagePf(driver);
    }

    public FinancePagePf getResults() throws InterruptedException {
        waitForElementVisible(tableWithResults);
        waitForElementVisible(submitButton);
        submitButton.click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        sortByRateItem.click();
        jse.executeScript("window.scrollBy(0,450)", "");
        Thread.sleep(2000); //Assending
        sortByRateItem.click();
        jse.executeScript("window.scrollBy(0,450)", "");
        Thread.sleep(2000); //Desending
        String results = thehighestRate.getText();
        double res = Double.parseDouble(results.substring(0,2));
        if (res > 15) {
            System.out.println("Есть ставка более 15% и это:"+results);
        } else System.out.println("Нет ставки более 15%, самая большая " + res +"%" );
        return new FinancePagePf(driver);
    }
}