package com.automation.practice.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automation.practice.base.DriverFactory;

public class ScreenshotUtils {

	  public static void takeScreenshot(String scenarioName) {
	        WebDriver driver = DriverFactory.getDriver();
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        File dest = new File("screenshots/" + scenarioName + ".png");

	        try {
	            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
