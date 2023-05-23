Feature: As a user, I want to get the latest weather information

  Background:
    Given the endpoint is "/weather"

  Scenario: Weather information is successfully retrieved
    Given a station ID = "039PG"
    When the endpoint is executed
    Then the status code is 200
    And the response is "weather.get.response.1.json"