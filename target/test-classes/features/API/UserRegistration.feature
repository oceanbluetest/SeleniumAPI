@userRegistrationAPI
  Feature: User Registration API

    @POST_member
    Scenario: Verify resource is active
      Given Resource "/members" is up and running
      When I send POST request with a pojo "member"
      Then Response code should be 200
      Then Print out and log the response body