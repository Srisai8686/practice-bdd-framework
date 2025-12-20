@saucedemo @login
Feature: Sauce Demo Login

Scenario: Successful login with valid credentials
  Given I open Sauce Demo application
  When I login to Sauce Demo with valid credentials
  Then user should land on Products page

Scenario: Login fails with invalid credentials
  Given I open Sauce Demo application
  When I login to Sauce Demo with "invalid_user" and "invalid_password"
  Then error message should be displayed
