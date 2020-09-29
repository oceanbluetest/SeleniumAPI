@integration
  Feature: All integration tests

    @userMgt_RegistrationAPI
    Scenario: Verify POST request result is displayed in UI
      Given Resource "/members" is up and running
      When I send POST request with a pojo "member"
      Then Response code should be 200
      Then Print out and log the response body
      And I open "http://automation.techleadacademy.io/#/usermgt"
      And I click a button "access db"
      Then I Verify following fields of response are displayed on User Database table:
      |firstName|
      |lastName |
      |email    |