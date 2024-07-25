Feature: Evaluate Car Insurance

  Scenario: Calculate premium for car with Basic insurance
    Given Execute Calculator Business
    When I enter the car model "ModelX"
    And the car type "SUV"
    And the car price 20000.0
    And the insurance type "Basic"
    Then we should get the actual premium 2000.0

  Scenario: Calculate premium for a car with Premium insurance
    Given Execute Calculator Business
    When I enter the car model "ModelY"
    And the car type "SUV"
    And the car price 30000.0
    And the insurance type "Premium"
    Then we should get the actual premium 3600.0
