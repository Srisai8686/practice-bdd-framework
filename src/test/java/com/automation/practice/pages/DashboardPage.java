package com.automation.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.practice.utils.WaitUtils;

public class DashboardPage extends BasePage {
	
	public  DashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
    // Dashboard page specific methods and elements can be added here
	By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

	public boolean isDashboardDisplayed() {
		WaitUtils.waitForVisible(driver, dashboardHeader);
		return driver.findElement(dashboardHeader).isDisplayed();
	}
}
