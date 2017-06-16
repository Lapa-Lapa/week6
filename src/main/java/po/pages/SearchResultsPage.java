package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends AbstractPage {
    private static final By FIRST_RESULT_LOCATOR  = By.cssSelector("#result_0 a.s-access-detail-page");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage openFirstSearchResult(){
        waitForElementPresent(FIRST_RESULT_LOCATOR);
        driver.findElement(FIRST_RESULT_LOCATOR).click();
        return new ItemPage(driver);
    }
}
