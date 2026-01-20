package com.automation.practice.stepDefinitions;

import static org.testng.Assert.assertTrue;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.automation.practice.base.DriverFactory;
import com.automation.practice.pages.DashboardPage;
import com.automation.practice.pages.LoginPage;
import com.automation.practice.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrangeHRMSteps {
	
	WebDriver driver;
	
	LoginPage loginpage;
	
	DashboardPage dashboardpage;
	
	public OrangeHRMSteps() {
		this.driver = DriverFactory.getDriver();
		loginpage = new LoginPage(driver);
		dashboardpage = new DashboardPage(driver);
	}
	
	@Given("I open OrangeHRM application")
		
	public void i_am_on_orange_hrm_login_page() {
		loginpage.openLoginPage(ConfigReader.get("baseUrl"));
	}
	
	@When("I login with valid credentials")
	public void i_login_with_valid_credentials() {
		loginpage.login( ConfigReader.get("username"),
	            ConfigReader.get("password"));
	}
	
	@When("I login with username {string} and password {string}")
	public void i_login_with_username_and_password(String username, String password) {
	    loginpage.login(username, password);
	}
	
	@Then("Dashboard should be displayed")
	public void dashboard_should_be_displayed() {
		assertTrue(dashboardpage.isDashboardDisplayed());
		
	}

}
