package pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FinancePage extends AbstractPage {

    private static final By choseCreditButton = By.xpath("//a[@class='button flat-white']");
    private static final By popUpWindowClose = By.className("scrollable-close");
    private static final By additionalOptionsForCredit = By.id("show_full_filter");
    private static final By bankThatGiveCreditList = By.xpath("//select[@id='bank_list']");
    private static final By sumOfCreditField = By.name("sum");
    private static final By submitButton = By.name("submit");
    private static final By tableWithResults = By.xpath("//div[@id='kred_compare']/table");
    private static final By sortByRateItem = By.xpath("//span[contains(text(),'Ставка')]");
    private static final By thehighestRate = By.xpath("//div[@class='wrapper']/big");


    public FinancePage pressChoseCreditButton() {
        driver.findElement(choseCreditButton).click();
        return new FinancePage();
    }

    public FinancePage setBelarusBankAsOptionForCredit() throws InterruptedException {
        waitForElementVisible(popUpWindowClose);
        driver.findElement(popUpWindowClose).click();
        for(int i =0;i<1000;i++){
            if(isElementPresent(popUpWindowClose)==true){
                System.out.println("Pop-up still not disappear");
                Thread.sleep(1500);
            } else{
                break;
            }
        }
        String window = driver.getWindowHandle();
        driver.switchTo().window(window);
        driver.findElement(additionalOptionsForCredit).click();
        WebElement dropdown = driver.findElement(bankThatGiveCreditList);
        Select bank = new Select(dropdown);
        bank.selectByIndex(8);
        return new FinancePage();
    }

    public FinancePage setSumOfCredit() {
        driver.findElement(sumOfCreditField).sendKeys("3000");
        return new FinancePage();
    }

    public FinancePage getResults() {
        waitForElementVisible(tableWithResults);
        driver.findElement(submitButton).click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        driver.findElement(sortByRateItem).click();//Assending
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        driver.findElement(sortByRateItem).click();//Desending
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(sortByRateItem));
        String results = driver.findElement(thehighestRate).getText();
        double res = Double.parseDouble(results.substring(0, 2));
        if (res > 15) {
            System.out.println("Есть ставка более 15% и это:" + results);
        } else System.out.println("Нет ставки более 15%, самая большая " + res + "%");
        return new FinancePage();
    }
}