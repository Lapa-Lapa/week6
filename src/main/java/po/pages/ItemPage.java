package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage extends AbstractPage {
    private static final By ITEM_PRICE_LOCATOR = By.cssSelector("#priceblock_ourprice");

    public ItemPage(WebDriver driver) {
        super(driver);
    }


    public Double readItemPrice(){
        waitForElementPresent(ITEM_PRICE_LOCATOR);
        String priceTextValue = driver.findElement(ITEM_PRICE_LOCATOR).getText();
        return Double.parseDouble(priceTextValue.substring(1));
    }
}
