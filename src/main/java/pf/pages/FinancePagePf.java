package pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinancePagePf extends AbstractPagePf {

//    @FindBy(css = "#priceblock_ourprice")
//    private WebElement itemPrice;

    @FindBy(css = "#priceblock_ourprice")
    private WebElement itemPrice;

    public FinancePagePf(WebDriver driver) {
        super(driver);
    }

    public Double readItemPrice(){
        waitForElementVisible(itemPrice);
        String priceTextValue = itemPrice.getText();
        return Double.parseDouble(priceTextValue.substring(1));
    }
}