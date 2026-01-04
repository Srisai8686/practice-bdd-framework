package om.automation.practice.stepDefinitions.saucedemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.practice.base.DriverFactory;
import com.automation.practice.pages.saucedemo.CartPage;
import com.automation.practice.pages.saucedemo.CheckoutPage;
import com.automation.practice.pages.saucedemo.ProductsPage;
import com.automation.practice.pages.saucedemo.SauceLoginPage;
import com.automation.practice.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoSteps {

    WebDriver driver;
    private SauceLoginPage sauceLoginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private static final Logger log = LoggerFactory.getLogger(SauceDemoSteps.class);

    

    public SauceDemoSteps() {
        driver = DriverFactory.getDriver();
        sauceLoginPage = new SauceLoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
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
    
    @Given("user is logged in to Sauce Demo")
    public void user_is_logged_in_to_sauce_demo() {
        sauceLoginPage.openSauceApp(ConfigReader.get("sauceUrl"));
        sauceLoginPage.login(
            ConfigReader.get("sauceUsername"),
            ConfigReader.get("saucePassword")
        );
        assertTrue(productsPage.isProductsPageDisplayed());
    }
    
    @Then("user should land on Products page")
    public void user_should_land_on_products_page() {
        assertTrue(productsPage.isProductsPageDisplayed());
    }
    
    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        assertTrue(sauceLoginPage.isErrorMessageDisplayed());
    }
    
    @When("user sorts products by {string}")
    public void user_sorts_products_by(String sortOption) {
        productsPage.sortProductsBy(sortOption);
    }
    
    @Then("products should be sorted correctly by {string}")
    public void products_should_be_sorted_correctly_by(String sortOption) {

        log.info("=======================================");
        log.info("Sorting verification for: {}", sortOption);

        if (sortOption.contains("Name")) {

            List<String> actualNames = productsPage.getAllProductNames();
            log.info("Actual Names (UI order): {}", actualNames);

            List<String> expectedNames = new ArrayList<>(actualNames);

            if (sortOption.equals("Name (A to Z)")) {
                Collections.sort(expectedNames);
            } else if (sortOption.equals("Name (Z to A)")) {
                Collections.sort(expectedNames, Collections.reverseOrder());
            }

            log.info("Expected Names (Java sorted): {}", expectedNames);

            assertEquals(expectedNames, actualNames);
            log.info("Result: UI names are sorted correctly ✅");

        } else if (sortOption.contains("Price")) {

            List<Double> actualPrices = productsPage.getAllProductPrices();
            log.info("Actual Prices (UI order): {}", actualPrices);

            List<Double> expectedPrices = new ArrayList<>(actualPrices);

            if (sortOption.equals("Price (low to high)")) {
                Collections.sort(expectedPrices);
            } else if (sortOption.equals("Price (high to low)")) {
                Collections.sort(expectedPrices, Collections.reverseOrder());
            }

            log.info("Expected Prices (Java sorted): {}", expectedPrices);

            assertEquals(expectedPrices, actualPrices);
            log.info("Result: UI prices are sorted correctly ✅");
        }

        log.info("=======================================\n");
    }

    @When("user adds a product {string} to the cart")
    public void user_adds_a_product_to_the_cart(String productName) {
		
      productsPage.addProductToCart(productName);
      productsPage.goToCart();
	}
    
    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() throws InterruptedException {
    	cartPage.clickCheckout();
    	checkoutPage.enterCheckoutInformation("John", "Doe", "12345");
    	checkoutPage.clickContinue();
    	Thread.sleep(5000); // Adding a short wait to ensure the page loads before clicking Finish
    	checkoutPage.clickFinish();
    }
    
    @Then("order should be placed successfully")
    public void order_should_be_placed_successfully() {
    	assertTrue(checkoutPage.isOrderSuccessMessageDisplayed());
    	
    	String successMessage = checkoutPage.getOrderSuccessMessageText();
    	System.out.println("Order Success Message: " + successMessage);
    	assertEquals("Thank you for your order!", successMessage);
    }

    
}


