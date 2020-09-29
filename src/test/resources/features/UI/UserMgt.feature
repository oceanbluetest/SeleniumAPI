@userMgt
Feature: User Management page tests
  Background: Open user registration page
    Given I open "http://automation.techleadacademy.io/#/usermgt"

  @dataTable @smoke
  Scenario: Register new user using DataTable
    And I enter following data:
      | firstname    | Kuba         |
      | lastname     | Z            |
      | phone number | 444-444-4444 |
      | email        | kuba@tla.com |
      | role         | Instructor   |

  @verifyButton
  Scenario: Verify Login button
    When I click a button "login"
    Then I verify title is "Login Page"

  @verifyButton @dbPage
  Scenario: Verify User Database button
    When I click a button "access db"
    Then I verify title is "User DB"
