@saucedemo @checkout
Feature: Purchase Flow

Scenario: Checkout with single product
  Given user is logged in to Sauce Demo
  When user adds product "Sauce Labs Backpack" to cart
  And user completes checkout
  Then purchase confirmation message should be displayed

Scenario: Checkout with multiple products
  Given user is logged in to Sauce Demo
  When user adds the following products to cart
    | Sauce Labs Backpack |
    | Sauce Labs Bike Light |
  And user completes checkout
  Then purchase confirmation message should be displayed

Scenario: Checkout with all products
  Given user is logged in to Sauce Demo
  When user adds all products to cart
  Then all products should be added to the cart
  And user completes checkout
  Then purchase confirmation message should be displayed
