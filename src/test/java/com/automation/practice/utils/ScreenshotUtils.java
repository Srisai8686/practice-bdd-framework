package com.automation.practice.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automation.practice.base.DriverFactory;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static void takeScreenshot(String scenarioName) {

        WebDriver driver = DriverFactory.getDriver();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Clean scenario name (remove spaces & special chars)
        String cleanName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_");

        // Timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Folder
        File screenshotsDir = new File("screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        // Destination file
        File dest = new File(screenshotsDir,
                cleanName + "_" + timestamp + ".png");

        try {
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
