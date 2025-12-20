@e2e @orangehrm
Feature: OrangeHRM End to End Flow

Scenario: Admin logs in and sees dashboard
  Given I open OrangeHRM application
  When I login with username "Admin" and password "admin123"
  Then Dashboard should be displayed
