package pf.pages;

import org.openqa.selenium.WebDriver;

public class SwitchToFrame extends HomePage {
    public void switchToFrame() {
        driver.switchTo().frame(0);
    }
}