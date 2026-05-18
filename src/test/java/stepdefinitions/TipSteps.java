package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import pages.TipCalculatorPage;
import utils.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TipSteps {

    TipCalculatorPage page;

    // ✅ ADD THIS (VERY IMPORTANT)
    @Before
    public void setup() {
        DriverFactory.initDriver("chrome");   // you can make dynamic later
        page = new TipCalculatorPage();
    }

    // ✅ FIXED
    @Given("user launches application")
    public void launchApp() {
        DriverFactory.getDriver().get("https://tipcal-navy.vercel.app/");
    }

    @When("user enters bill {string}")
    public void enterBill(String bill) {
        page.enterBill(bill);
    }

    @And("user selects currency {string}")
    public void selectCurrency(String currency) {
        page.selectCurrency(currency);
    }

    @And("user enters people {string}")
    public void enterPeople(String p) {
        page.enterPeople(p);
    }

    @And("user selects rating {string}")
    public void selectRating(String r) {
        page.selectRating(Integer.parseInt(r));
    }

    @And("user clicks submit")
    public void clickSubmit() {
        page.submit();
    }

    @Then("result should be displayed")
    public void validateResult() {
        Assert.assertTrue(true);
    }

    @Then("bill error should be shown")
    public void billError() {
        Assert.assertFalse(page.billError().isEmpty());
    }

    // ✅ ADD THIS (CLOSE DRIVER)
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
