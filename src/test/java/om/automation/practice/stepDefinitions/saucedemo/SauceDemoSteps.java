package om.automation.practice.stepDefinitions.saucedemo;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.automation.practice.base.DriverFactory;
import com.automation.practice.pages.saucedemo.ProductsPage;
import com.automation.practice.pages.saucedemo.SauceLoginPage;
import com.automation.practice.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoSteps {

    WebDriver driver;
    SauceLoginPage sauceLoginPage;
    ProductsPage productsPage;
    
    

    public SauceDemoSteps() {
        driver = DriverFactory.getDriver();
        sauceLoginPage = new SauceLoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Given("I open Sauce Demo application")
    public void open_sauce_demo() {
        sauceLoginPage.openSauceApp(ConfigReader.get("sauceUrl"));
    }

    @When("I login to Sauce Demo with valid credentials")
    public void login_sauce_demo() {
        sauceLoginPage.login(
            ConfigReader.get("sauceUsername"),
            ConfigReader.get("saucePassword")
        );
    }
    
    @When("I login to Sauce Demo with {string} and {string}")
    public void i_login_to_sauce_demo_with_and(String username, String password) {
        sauceLoginPage.login(username, password);
    }
    
    
    @Then("user should land on Products page")
    public void user_should_land_on_products_page() {
        assertTrue(productsPage.isProductsPageDisplayed());
    }
    
    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        assertTrue(sauceLoginPage.isErrorMessageDisplayed());
    }
    
}

