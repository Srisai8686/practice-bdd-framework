package com.automation.practice.hooks;

import com.automation.practice.base.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverFactory.initDriver(browser);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
