Feature: Tip Calculator Testing

  Scenario: Valid Calculation
    Given user launches application
    When user enters bill "1000"
    And user selects currency "INR"
    And user enters people "2"
    And user selects rating "5"
    And user clicks submit
    Then result should be displayed

  Scenario: Invalid Bill
    Given user launches application
    When user enters bill "abc"
    And user clicks submit
    Then bill error should be shown