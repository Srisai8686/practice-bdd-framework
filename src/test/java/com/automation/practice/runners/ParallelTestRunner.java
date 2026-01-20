package com.automation.practice.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.automation.practice.stepDefinitions",
        "com.automation.practice.hooks"
    },
    plugin = {
        "pretty",
        "html:reports/cucumber-report.html",
        "json:reports/cucumber.json"
    },
    tags = "@saucedemo",
    monochrome = true
)
public class ParallelTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
