Feature: API to add and validate booking details

#  Usecase1 , Usecase2
  Scenario: Add a new booking and validate the added booking details with valid id
    Given I add a new booking with valid details
    When I get the booking details using the booking ID
    Then I should receive a 200 success
    Then I should see the booking details match the expected values

#Usecase2
  Scenario: Attempt to retrieve a booking with an invalid ID
    Given I have an invalid booking ID "999999"
    When I get the booking details using the booking ID
    Then I should receive a 404 error

    #Usecase2
  Scenario: Attempt to add a new booking with invalid data
    Given I add a new booking with no firstName
    Then I should receive a 500 error