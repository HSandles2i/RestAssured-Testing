Feature: Verify functionality of HTTP methods on Person API

  Scenario: Add new Person
    // POST Request
    Given User is added to the system
    Then is assigned an ID

    Then User's data is retrieved
    And  matches what was added

    Then User's location is changed to "newLocation"
    And User's new location is correct

    Then we remove User from the system
