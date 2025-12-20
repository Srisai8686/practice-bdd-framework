package com.automation.practice.hooks;

import com.automation.practice.base.DriverFactory;
import com.automation.practice.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot(scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
