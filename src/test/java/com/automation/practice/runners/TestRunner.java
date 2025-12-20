package com.automation.practice.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/OrangeHRMLogin.feature",
        glue = {"com.automation.practice.stepDefinitions",
        		"com.automation.practice.hooks"},
        plugin = {"pretty"},
        monochrome = true
)

public class TestRunner {

}
