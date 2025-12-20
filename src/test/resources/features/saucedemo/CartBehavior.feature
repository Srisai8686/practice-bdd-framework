@saucedemo @cart
Feature: Cart Behavior

Scenario: Cart retains items after navigating back to products page
  Given user is logged in to Sauce Demo
  When user adds product "Sauce Labs Backpack" to cart
  And user navigates to products page
  Then cart badge count should be "1"
