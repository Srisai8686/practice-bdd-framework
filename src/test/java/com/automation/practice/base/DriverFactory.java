package com.automation.practice.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // 🔥 MOST IMPORTANT: Incognito mode
            options.addArguments("--incognito");

            // 🔐 Disable password manager services
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            // 🚫 Disable browser UI interruptions
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-save-password-bubble");

            // 🚫 Disable Chrome password breach & onboarding
            options.addArguments("--disable-features=PasswordLeakDetection");
            options.addArguments("--disable-features=PasswordManagerOnboarding");

            // 🧹 Stability flags
            options.addArguments("--no-first-run");
            options.addArguments("--no-default-browser-check");

            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(options);

        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
