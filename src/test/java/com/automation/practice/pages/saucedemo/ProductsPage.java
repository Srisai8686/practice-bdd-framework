package com.automation.practice.pages.saucedemo;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.automation.practice.pages.BasePage;

public class ProductsPage extends BasePage {

    private By productsTitle = By.className("title");
    private By productItems = By.className("inventory_item");

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductsPageDisplayed() {
        return driver.findElement(productsTitle).isDisplayed()
                && driver.findElements(productItems).size() > 0;
    }
}

