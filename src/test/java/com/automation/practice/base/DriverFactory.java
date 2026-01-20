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

        if (browser == null) {
            browser = "chrome";
        }

        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // ✅ Incognito (avoids popups & saved state)
            options.addArguments("--incognito");

            // ✅ Disable password manager & services
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            // ✅ Disable browser interruptions
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-save-password-bubble");

            // ✅ Stability flags
            options.addArguments("--no-first-run");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-features=PasswordLeakDetection");
            options.addArguments("--disable-features=PasswordManagerOnboarding");

            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(options);

        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        webDriver.manage().window().maximize();

        // 🔥 Thread-safe driver set
        driver.set(webDriver);

        System.out.println(
            "Driver started for thread: " + Thread.currentThread().getId()
        );
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
