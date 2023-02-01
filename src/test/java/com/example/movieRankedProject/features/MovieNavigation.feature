Feature:  Navigating around the movie app

  Scenario: Navigate to movie-list page
    Given I have a browser open
    When I navigate to "http://localhost:8080/movie-list"
    Then I am on the "Movie Ranker" page
