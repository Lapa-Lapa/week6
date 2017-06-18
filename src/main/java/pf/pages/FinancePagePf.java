package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class FinancePagePf extends AbstractPagePf {

    @FindBy(xpath = "//*[@class='button flat-white']")
    WebElement choseCreditButton;

    @FindBy(xpath = "//*[contains(text(),'Все условия кредитования')]")
    WebElement additionalOptionsForCredit;

    @FindBy(xpath = "//*[@id='bank_list']")
    WebElement bankThatGiveCreditList;

//    @FindBy(xpath = "//*[@id='bank_list']/option[9]") but index 8
//    WebElement belarusBankFromList;

    @FindBy(name = "sum")
    WebElement sumOfCreditField;

    @FindBy(name = "submit")
            //(xpath = "//*[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//*[@id='kred_compare']/table")
    WebElement tableWithResults;

//    @FindBy(css = "#priceblock_ourprice")
//    private WebElement itemPrice;

    @FindBy(css = "#priceblock_ourprice")
    private WebElement itemPrice;

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
//        https://www.youtube.com/watch?v=H322v_HRzC8     11.08
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
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(2000);
        waitForElementVisible(submitButton);
        submitButton.click();
        Thread.sleep(360000);
        String results = tableWithResults.getText();
        System.out.println(results);
        //Thread.sleep(360000);
        Assert.assertEquals("15%", results, "есть хоть один кредит со ставкой 15%");
        return new FinancePagePf(driver);
    }


//
}
//String priceTextValue = itemPrice.getText();
//return Double.parseDouble(priceTextValue.substring(1));