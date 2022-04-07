@EcommerceAutomationTesting
Feature: Automated registration and login related Scenarios for E-Commerce web app

  Scenario: User could register with valid data
    When user clicks on register link
    And user fills all required data fields
    And user clicks on Register button
    Then A successful registration message should be displayed

  Scenario: User could log in with valid email and password
    Given user should register first
    When user clicks on login link
    And user enters valid username and password
    And user clicks on Login button
    Then User should be logged in successfully

  Scenario: User could reset his/her password successfully
    Given user should register first
    When user clicks on login link
    And user clicks on forgot password link
    And user enters email address
    And user clicks on Recover button
    Then A successful reset password message should be displayed
