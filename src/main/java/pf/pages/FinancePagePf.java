package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FinancePagePf extends AbstractPagePf {

    @FindBy(xpath = "//a[@class='button flat-white']")
    WebElement choseCreditButton;

    @FindBy(xpath = "//span[@class='scrollable-close')]")
    WebElement popUpWindowClose;

    @FindBy(id = "show_full_filter")
    //*[contains(text(),'Все условия кредитования')]")
            WebElement additionalOptionsForCredit;

    @FindBy(xpath = "//select[@id='bank_list']")
    WebElement bankThatGiveCreditList;

    @FindBy(name = "sum")
    WebElement sumOfCreditField;

    @FindBy(name = "submit")
    WebElement submitButton;

    @FindBy(xpath = "//div[@id='kred_compare']/table")
    WebElement tableWithResults;

    @FindBy(xpath = "//span[contains(text(),'Ставка')]")
    WebElement sortByRateItem;

    @FindBy(xpath = "//div[@class='wrapper']/big")
    WebElement thehighestRate;

    String window;

    public FinancePagePf(WebDriver driver) {
        super(driver);
    }

    public FinancePagePf pressChoseCreditButton() {
        waitForElementVisible(choseCreditButton);
        choseCreditButton.click();
        window = driver.getWindowHandle();
        return new FinancePagePf(driver);
    }

    public FinancePagePf setBelarusBankAsOptionForCredit() throws InterruptedException {
        Thread.sleep(1500);
        try {
            popUpWindowClose.click();
            additionalOptionsForCredit.click();
            Actions actions = new Actions(driver);
            actions.click(popUpWindowClose).build().perform();
        } catch (Exception exception) {
            System.out.println("Pop up window disappear");
        }
        //driver.switchTo().window(window);
        Thread.sleep(1500);
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
        double res = Double.parseDouble(results.substring(0, 2));
        if (res > 15) {
            System.out.println("Есть ставка более 15% и это:" + results);
        } else System.out.println("Нет ставки более 15%, самая большая " + res + "%");
        return new FinancePagePf(driver);
    }
}