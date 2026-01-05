package com.automation.practice.pages.saucedemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.practice.pages.BasePage;

public class ProductsPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(ProductsPage.class);

    private By productsTitle = By.className("title");
    private By productItems = By.className("inventory_item");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtons = By.xpath(".//button[contains(@id,'add-to-cart')]");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // ================= PAGE CHECK =================
    public boolean isProductsPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle));
        return driver.findElements(productItems).size() > 0;
    }

    // ================= SORTING =================
    public void sortProductsBy(String sortOption) {
        log.info("Sorting products by: {}", sortOption);
        Select select = new Select(
                wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)));
        select.selectByVisibleText(sortOption);
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> elements = driver.findElements(productNames);

        for (WebElement element : elements) {
            names.add(element.getText());
        }
        return names;
    }

    public List<Double> getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        List<WebElement> elements = driver.findElements(productPrices);

        for (WebElement element : elements) {
            prices.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        return prices;
    }

   
    public boolean isProductsSortedCorrectly(String sortOption) {

        if (sortOption.contains("Name")) {
            List<String> actual = getAllProductNames();
            List<String> expected = new ArrayList<>(actual);

            if (sortOption.equals("Name (A to Z)")) {
                Collections.sort(expected);
            } else if (sortOption.equals("Name (Z to A)")) {
                Collections.sort(expected, Collections.reverseOrder());
            } else {
                throw new IllegalArgumentException("Invalid sort option: " + sortOption);
            }

            log.info("Actual Names  : {}", actual);
            log.info("Expected Names: {}", expected);
            return actual.equals(expected);
        }

        if (sortOption.contains("Price")) {
            List<Double> actual = getAllProductPrices();
            List<Double> expected = new ArrayList<>(actual);

            if (sortOption.equals("Price (low to high)")) {
                Collections.sort(expected);
            } else if (sortOption.equals("Price (high to low)")) {
                Collections.sort(expected, Collections.reverseOrder());
            } else {
                throw new IllegalArgumentException("Invalid sort option: " + sortOption);
            }

            log.info("Actual Prices  : {}", actual);
            log.info("Expected Prices: {}", expected);
            return actual.equals(expected);
        }

        throw new IllegalArgumentException("Unsupported sort option: " + sortOption);
    }

    // ================= CART =================
    public void addProductToCart(String productName) {
        log.info("Attempting to add product to cart: {}", productName);

        List<WebElement> inventoryItems = driver.findElements(productItems);
        boolean productFound = false;

        for (WebElement item : inventoryItems) {
            String name = item.findElement(productNames).getText();
            if (name.equals(productName)) {
                item.findElement(addToCartButtons).click();
                log.info("Product added to cart: {}", name);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            throw new RuntimeException("Product not found: " + productName);
        }
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}
