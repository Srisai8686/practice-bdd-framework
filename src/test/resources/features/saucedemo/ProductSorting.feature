@saucedemo @sorting
Feature: Product Sorting

Scenario Outline: Verify products are sorted correctly
  Given user is logged in to Sauce Demo
  When user sorts products by "<sortOption>"
  Then products should be sorted correctly by "<sortOption>"

Examples:
  | sortOption          |
  | Name (A to Z)       |
  | Name (Z to A)       |
  | Price (low to high) |
  | Price (high to low) |
