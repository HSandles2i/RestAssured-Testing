Feature: Verify functionality of HTTP methods on Person API

  Scenario: Add new Person
    // POST Request
    Given John is added to the system
    Then is assigned an ID

    Then John's data is retrieved
    And  matches what was added

    Then John's location is changed to "newLocation"
    And John's new location is correct

    Then we remove John from the system
