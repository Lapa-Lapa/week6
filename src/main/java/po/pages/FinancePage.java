package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pf.pages.FinancePagePf;

public class FinancePage extends AbstractPage {
    private static final By choseCreditButton  = By.xpath("//[@class='button flat-white']");
    private static final By additionalOptionsForCredit  = By.xpath("//[contains(text(),'Все условия кредитования')]");
    private static final By bankThatGiveCreditList  = By.xpath("//[@id='bank_list']");
    private static final By sumOfCreditField  = By.name("sum");
    private static final By submitButton  = By.name("submit");
    private static final By tableWithResults  = By.xpath("//[@id='kred_compare']/table");
    private static final By sortByRateItem  = By.xpath("//span[contains(text(),'Ставка')]");
    private static final By thehighestRate  = By.xpath("//[@class='wrapper']/big");

    public FinancePage(WebDriver driver) {
        super(driver);
    }

//    public FilmsPage openFirstSearchResult(){
//        waitForElementPresent(FIRST_RESULT_LOCATOR);
//        driver.findElement(FIRST_RESULT_LOCATOR).click();
//        return new FilmsPage(driver);
//    }
    public FinancePage pressChoseCreditButton() {
        waitForElementPresent(choseCreditButton);
        driver.findElement(choseCreditButton).click();
        return new FinancePage(driver);
    }

    public FinancePage setBelarusBankAsOptionForCredit() {
        waitForElementPresent(additionalOptionsForCredit);
        driver.findElement(additionalOptionsForCredit).click();
//        WebElement dropdown = bankThatGiveCreditList;
//        Select bank = new Select(dropdown);
//        bank.selectByIndex(8);
        return new FinancePage(driver);
    }

    public FinancePage setSumOfCredit() {
        driver.findElement(sumOfCreditField).sendKeys("3000");
        return new FinancePage(driver);
    }

    public FinancePage getResults() throws InterruptedException {
        waitForElementPresent(submitButton);
        driver.findElement(submitButton).click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,450)", "");
        driver.findElement(sortByRateItem).click();
        jse.executeScript("window.scrollBy(0,450)", "");
        Thread.sleep(2000); //Assending
        driver.findElement(sortByRateItem).click();
        jse.executeScript("window.scrollBy(0,450)", "");
        Thread.sleep(2000); //Desending
        String results = driver.findElement(thehighestRate).getText();
        double res = Double.parseDouble(results.substring(0,2));
        if (res > 15) {
            System.out.println("Есть ставка более 15% и это:"+results);
        } else System.out.println("Нет ставки более 15%, самая большая " + res +"%" );
        return new FinancePage(driver);
    }
}