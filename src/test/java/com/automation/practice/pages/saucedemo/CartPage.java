package com.automation.practice.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.practice.pages.BasePage;

public class CartPage extends BasePage {
   
	 
	    private static final Logger log = LoggerFactory.getLogger(CartPage.class);

	    private By checkoutButton = By.id("checkout");
	    
	    public CartPage(WebDriver driver) {
	        super(driver);
	    }
	    
	    public void clickCheckout() {
	        driver.findElement(checkoutButton).click();
	        log.info("Clicked on Checkout button");
	    }
}
