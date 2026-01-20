package com.automation.practice.stepDefinitions.saucedemo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


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
import com.automation.practice.utils.ScreenshotUtils;

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
    	assertTrue(
    		    productsPage.isProductsSortedCorrectly(sortOption),
    		    "Products are NOT sorted correctly for option: " + sortOption
    		);

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
    	//Thread.sleep(5000); // Adding a short wait to ensure the page loads before clicking Finish
    	checkoutPage.clickFinish();
    }
    
    @Then("order should be placed successfully")
    public void order_should_be_placed_successfully() {
    	assertTrue(checkoutPage.isOrderSuccessMessageDisplayed());
    	
    	String successMessage = checkoutPage.getOrderSuccessMessageText();
    	System.out.println("Order Success Message: " + successMessage);
    	assertEquals("Thank you for your order!", successMessage);
    }

    @When("user adds product {string} to cart")
    public void user_adds_product_to_cart_alias(String productName) {
        user_adds_a_product_to_the_cart(productName);
    }

    @When("user completes checkout")
    public void user_completes_checkout() throws InterruptedException {
        user_proceeds_to_checkout();
    }

    @Then("purchase confirmation message should be displayed")
    public void purchase_confirmation_message_should_be_displayed() {
        order_should_be_placed_successfully();
        ScreenshotUtils.takeScreenshot(
        	    "order_success_" + Thread.currentThread().getId()
        	);
    }

    @When("user adds the following products to cart")
    public void user_adds_the_following_products_to_cart(io.cucumber.datatable.DataTable dataTable) {

        List<String> products = dataTable.asList();

        for (String product : products) {
            productsPage.addProductToCart(product);
        }

        productsPage.goToCart();
    }

    @When("user adds all products to cart")
    public void user_adds_all_products_to_cart() {

        productsPage.addAllProductsToCart();
        productsPage.goToCart();
    }
    
    @Then("all products should be added to the cart")
    public void all_products_should_be_added_to_cart() {
        int expectedCount = productsPage.getAllProductNames().size();
        int actualCount = productsPage.getCartItemCount();
        System.out.println("Expected Cart Count: " + expectedCount);
        System.out.println("Actual Cart Count: " + actualCount);

        assertEquals(actualCount, expectedCount, "Cart count mismatch");
        ScreenshotUtils.takeScreenshot(
        	    "all_products_added_" + Thread.currentThread().getId()
        	);
    }

    @When("user navigates to products page")
    public void user_navigates_to_products_page() {
        productsPage.returnToProductsPage();
    }
    
    @Then("cart badge count should be {string}")
    public void cart_badge_count_should_be(String expectedCount) {

        int actualCount = productsPage.getCartItemCount();
        int expected = Integer.parseInt(expectedCount);

        System.out.println("Expected cart count: " + expected);
        System.out.println("Actual cart count: " + actualCount);

        assertEquals(expected, actualCount);
    }

    
}


