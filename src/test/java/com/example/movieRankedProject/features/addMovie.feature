Feature: Add a Movie

  Scenario: Add new movie
    Given I have a browser open
    When I navigate to "http://localhost:8080/movie-list"
    And I enter "Lord of the Rings" in the Title submit text field
    And I click the submit button
