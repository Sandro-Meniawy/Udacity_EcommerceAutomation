@EcommerceAutomationTesting
Feature: Automated products related Scenarios for E-Commerce web app

  Scenario: Logged User could search for any product
    Given user should be registered and logged in
    When user enter search text in search input field
    And user clicks on search button
    Then user should get search results

  Scenario: Logged User could switch between currencies US-Euro
    Given user should be registered and logged in
    When user switch the currency between US-Euro
    Then the products currency should be changed

  Scenario: Logged user could select different Categories
    Given user should be registered and logged in
    When user selects a category
    Then validate that correct category is selected

  Scenario: Logged user could filter with color
    Given user should be registered and logged in
    When user selects a category and subcategory
    And user filters between products by color
    Then validate that data shown after filter

  Scenario: Logged user could select different tags
    Given user should be registered and logged in
    And user selects a category and subcategory
    When user selects and clicks on one of available tags
    Then validate that correct tag is selected

  Scenario: Logged user could add different products to Shopping cart
    Given user should be registered and logged in
    And user selects a category and subcategory
    And user selects and clicks on one of available tags
    When user adds products to "Shopping cart"
    Then validate adding product to "Shopping cart" success message displayed
    And validate that the selected products added to "Shopping cart"

  Scenario: Logged user could add different products to Wishlist
    Given user should be registered and logged in
    And user selects a category and subcategory
    And user selects and clicks on one of available tags
    When user adds products to "Wishlist"
    Then validate adding product to "Wishlist" success message displayed
    And validate that the selected products added to "Wishlist"

  Scenario: Logged user could add different products to Compare list
    Given user should be registered and logged in
    And user selects a category and subcategory
    And user selects and clicks on one of available tags
    When user adds products to "Compare list"
    Then validate adding product to "Compare list" success message displayed
    And validate that the selected products added to "Compare list"
