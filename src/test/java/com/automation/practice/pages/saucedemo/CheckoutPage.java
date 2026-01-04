package com.automation.practice.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.practice.pages.BasePage;

public class CheckoutPage extends BasePage {
	
	
    private static final Logger log = LoggerFactory.getLogger(CheckoutPage.class);
    
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By successMessage = By.className("complete-header");

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
	    log.info("Waiting for checkout page to load");

	    wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));

	    log.info("Entering checkout information");
	    driver.findElement(firstNameField).sendKeys(firstName);
	    driver.findElement(lastNameField).sendKeys(lastName);
	    driver.findElement(postalCodeField).sendKeys(postalCode);

	    log.info("Entered checkout information: {}, {}, {}", firstName, lastName, postalCode);
	}

	
	public void clickContinue() {
		driver.findElement(continueButton).click();
		log.info("Clicked on Continue button");
	}
	
	public void clickFinish() {
	    log.info("Waiting for Finish button to be clickable");
	    wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
	    log.info("Clicked on Finish button");
	}

	
	public boolean isOrderSuccessMessageDisplayed() {
	    log.info("Waiting for order success message");
	    log.info("Final checkout URL: {}", driver.getCurrentUrl());
	    return wait.until(
	        ExpectedConditions.visibilityOfElementLocated(successMessage)
	    ).isDisplayed();
	}
	
	public String getOrderSuccessMessageText() {
	    return wait.until(
	        ExpectedConditions.visibilityOfElementLocated(successMessage)
	    ).getText();
	}
	

	
	

}
