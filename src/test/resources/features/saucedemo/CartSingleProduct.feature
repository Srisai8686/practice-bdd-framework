@saucedemo @cart
Feature: Single product checkout

Scenario: User purchases a single product successfully
  Given user is logged in to Sauce Demo
  When user adds a product "Sauce Labs Backpack" to the cart
  And user proceeds to checkout
  Then order should be placed successfully
