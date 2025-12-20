package com.automation.practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.practice.utils.ConfigReader;

public class LoginPage extends BasePage {
	
	@FindBy(name="username")
	private WebElement usernameField;
	
	@FindBy(name="password")
	private WebElement passwordField;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void openLoginPage(String url) {
		driver.get(url);
	}
   
	public void login(String username, String password) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
	}
}
