package com.automation.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.automation.practice.base.DriverFactory;

public class GooglePage {

    WebDriver driver;

    public GooglePage() {
        this.driver = DriverFactory.getDriver();
    }

    By searchBox = By.name("q");

    public void openGoogle() {
        driver.get("https://www.google.com");
    }

    public void searchFor(String text) {
        driver.findElement(searchBox).sendKeys(text + Keys.ENTER);
    }

    public boolean isResultDisplayed(String expectedText) {
        return driver.getTitle().contains(expectedText);
    }
}
