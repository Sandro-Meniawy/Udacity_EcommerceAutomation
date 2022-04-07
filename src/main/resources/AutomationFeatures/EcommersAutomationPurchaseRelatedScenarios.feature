@EcommerceAutomationTesting
Feature: Automated purchase related Scenarios for E-Commerce web app

  Scenario: Create successful Order
    Given user should be registered and logged in
    And user selects a category and subcategory
    And user selects and clicks on one of available tags
    When user adds products to "Shopping cart"
    And user Navigates to Shopping cart
    And user accepts terms of service and then clicks checkout button
    And user fills checkout form
    Then validate completed order success message