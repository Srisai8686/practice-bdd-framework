@saucedemo @e2e
Feature: Sauce Demo End to End Flow

Scenario: User completes purchase successfully
  Given I open Sauce Demo application
  When I login to Sauce Demo with valid credentials
  And user adds product "Sauce Labs Backpack" to cart
  And user completes checkout
  Then purchase confirmation message should be displayed
