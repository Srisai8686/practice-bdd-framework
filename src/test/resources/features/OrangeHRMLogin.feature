Feature: OrangeHRM Login

  Scenario: Successful login to OrangeHRM
    Given I open OrangeHRM application
    When I login with valid credentials
    Then Dashboard should be displayed