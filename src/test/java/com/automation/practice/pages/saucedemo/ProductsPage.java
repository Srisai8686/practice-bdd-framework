package com.automation.practice.pages.saucedemo;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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
    private By addToCartButtons = By.xpath("//button[contains(@id,'add-to-cart')]");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductsPageDisplayed() {
        return driver.findElement(productsTitle).isDisplayed()
                && driver.findElements(productItems).size() > 0;
    }
    
    public void sortProductsBy(String sortOption) {
		Select select = new Select(driver.findElement(sortDropdown));;
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
            prices.add(
                Double.parseDouble(element.getText().replace("$", ""))
            );
        }
        return prices;
    }
    
    public void addProductToCart(String productName) {
    	 log.info("Attempting to add product to cart: {}", productName);
        List<WebElement> inventoryItems = driver.findElements(productItems);
        log.info("Total products found on page: {}", inventoryItems.size());
        boolean productFound = false;
        for (WebElement item : inventoryItems) {
			String name = item.findElement(productNames).getText();
			 log.info("Checking product: {}", name);
			if (name.equals(productName)) {
				 log.info("Match found. Clicking Add to Cart for product: {}", name);
				item.findElement(addToCartButtons).click();
				  log.info("Product added to cart successfully: {}", name);
				  productFound = true;
				break;
			}
		}
        if (!productFound) {
            log.error("Product NOT found on page: {}", productName);
        }
    }
    
    public void goToCart() {
		driver.findElement(cartIcon).click();
	}

}

