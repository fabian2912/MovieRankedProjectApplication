Feature:  Navigating around the movie app

  Scenario: Navigate to movie-list page
    Given I have a browser open
    When I navigate to "http://localhost:8080/movie-list"
    Then I am on the "Movie Ranker" page


  Scenario: Navigate to summary page then return back to the home page
    Given I have a browser open
    When I navigate to "http://localhost:8080/movie-list"
    Then I am on the "Movie Ranker" page
    And I click the hyperlink
    Then I am on the "Movie Summary" page
    And I click on the return button
    Then I am on the "Movie Ranker" page
