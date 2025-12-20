Feature: Google Search
  Scenario: Search in Google
    Given I open Google homepage
    When I search for "Selenium WebDriver"
    Then results should be displayed
