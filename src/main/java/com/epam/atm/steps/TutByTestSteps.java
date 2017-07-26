package com.epam.atm.steps;

import com.epam.atm.driver.WebDriverSingleton;
import com.epam.atm.pages.AutoPage;
import com.epam.atm.pages.FilmsPage;
import com.epam.atm.pages.FinancePage;
import com.epam.atm.pages.HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TutByTestSteps {
    private static WebDriver driver = WebDriverSingleton.getWebDriverInstance();
    private static final String START_URL = "https://www.tut.by";

    @Given("^user open tut.by home page$")
    public void navigate_to_home_page() {
        driver.get(START_URL);
    }

    @When("^afisha page open$")
    public void afisha_page_open() {
        new HomePage().afishaOpen();
    }

    @And(value = "^select date, time (\\d+) and film$")
    public void select_date_time_and_film(int TIME) {
        new FilmsPage().openFilms().selectDate().popupWindowClose().selectTime(TIME).selectFilm();
    }

    @Then("^quantity of shots is five$")
    public void verify_quantity_of_shots() {
        Assert.assertEquals(new FilmsPage().allShots(), 5, "There are five shots to this film");
    }

    @When("^finance page open$")
    public void finance_page_open() {
        new HomePage().financeOpen();
    }

    @And("^set bank \"([^\"]*)\" and summ \"([^\"]*)\" of credit$")
    public void select_date_time_and_film(String BANK, int SUM_OF_CREDIT) {
        new FinancePage().pressChoseCreditButton()
                .popupWindowClose()
                .setBankAsOptionForCredit(BANK)
                .setSumOfCredit(SUM_OF_CREDIT);
    }

    @Then("^credit rate more than JSON:%$")
    public void verify_credit_rates(int ExpRes) {
        Double res = new FinancePage().getResults();
        Assert.assertTrue(res > ExpRes, "The biggest rate is = " + res + "%");
    }

    @When("^mobile version enable$")
    public void mobile_version_enable() {
        new HomePage().open().mobileVersionEnable();
    }

    @And("^post in video section in auto news open$")
    public void post_video_auto_open() {
        new HomePage().autoOpen().videoSectionOpen().postOpen();
    }

    @Then("^screen is full$")
    public void verify_full_screen() {
        Assert.assertTrue(new AutoPage().videoPlayButtonPressAndFullSize().getResultOfScalingToFullScreen(), "Full screen player");
    }
}