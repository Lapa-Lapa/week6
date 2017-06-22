package pf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FinancePage extends AbstractPage {

    @FindBy(xpath = "//a[@class='button flat-white']")
    public WebElement choseCreditButton;

    @FindBy(xpath = "//span[@class='scrollable-close')]")
    public WebElement popUpWindowClose;

    @FindBy(id = "show_full_filter")
    public WebElement additionalOptionsForCredit;

    @FindBy(xpath = "//select[@id='bank_list']")
    public WebElement bankThatGiveCreditList;

    @FindBy(name = "sum")
    public WebElement sumOfCreditField;

    @FindBy(name = "submit")
    public WebElement submitButton;

    @FindBy(xpath = "//div[@id='kred_compare']/table")
    public WebElement tableWithResults;

    @FindBy(xpath = "//span[contains(text(),'Ставка')]")
    public WebElement sortByRateItem;

    @FindBy(xpath = "//div[@class='wrapper']/big")
    public WebElement thehighestRate;

    public FinancePage(WebDriver driver) {
        super(driver);
    }

    public FinancePage pressChoseCreditButton() {
        waitForElementVisible(choseCreditButton);
        choseCreditButton.click();
        return new FinancePage(driver);
    }

    public FinancePage setBelarusBankAsOptionForCredit() throws InterruptedException {
        Thread.sleep(1500);
        //TODO: style= 'display:block' --> style= 'display:none'
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementsByTagName('DIV')[9].style= 'display:none';");
        //"document.getElementById('periodId').style.display='block';");
        try {
            popUpWindowClose.click();
            additionalOptionsForCredit.click();
            Actions actions = new Actions(driver);
            actions.click(popUpWindowClose).build().perform();
        } catch (Exception exception) {
            System.out.println("Pop up window disappear");
        }
        Thread.sleep(6000);// Вот в этот момент закрываем поп-ап руками
        additionalOptionsForCredit.click();
        WebElement dropdown = bankThatGiveCreditList;
        Select bank = new Select(dropdown);
        bank.selectByIndex(8);
        Thread.sleep(5000);
        return new FinancePage(driver);
    }

    public FinancePage setSumOfCredit() {
        waitForElementVisible(sumOfCreditField);
        sumOfCreditField.sendKeys("3000");
        return new FinancePage(driver);
    }

    public FinancePage getResults() throws InterruptedException {
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
        return new FinancePage(driver);
    }
}