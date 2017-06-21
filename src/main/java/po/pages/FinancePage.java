package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FinancePage extends AbstractPage {
    private static final By choseCreditButton  = By.xpath("//a[@class='button flat-white']");
    private static final By popUpWindowClose  = By.xpath("//span[@class='scrollable-close')]");
    private static final By additionalOptionsForCredit  = By.id("show_full_filter");
    private static final By bankThatGiveCreditList  = By.xpath("//select[@id='bank_list']");
    private static final By sumOfCreditField  = By.name("sum");
    private static final By submitButton  = By.name("submit");
    private static final By tableWithResults  = By.xpath("//div[@id='kred_compare']/table");
    private static final By sortByRateItem  = By.xpath("//span[contains(text(),'Ставка')]");
    private static final By thehighestRate  = By.xpath("//div[@class='wrapper']/big");

    public FinancePage(WebDriver driver) {
        super(driver);
    }

    public FinancePage pressChoseCreditButton() {
        waitForElementPresent(choseCreditButton);
        driver.findElement(choseCreditButton).click();
        return new FinancePage(driver);
    }

    public FinancePage setBelarusBankAsOptionForCredit(){
        waitForElementPresent(additionalOptionsForCredit);
        driver.findElement(additionalOptionsForCredit).click();
        Select bank = new Select(driver.findElement(bankThatGiveCreditList));
        bank.selectByIndex(8);
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