package com.automation.practice.stepDefinitions;

import com.automation.practice.pages.GooglePage;
import io.cucumber.java.en.*;

public class GoogleSteps {

    GooglePage googlePage = new GooglePage();

    @Given("I open Google homepage")
    public void openGoogle() {
        googlePage.openGoogle();
    }

    @When("I search for {string}")
    public void search(String keyword) {
        googlePage.searchFor(keyword);
    }

    @Then("results should be displayed")
    public void verifyResults() {
        boolean status = googlePage.isResultDisplayed("Selenium WebDriver");
        System.out.println("Search results displayed: " + status);
}
}
