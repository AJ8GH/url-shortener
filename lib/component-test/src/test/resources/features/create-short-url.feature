Feature: Create short url

  Scenario: Create happy path
    When I make request to create short url for https://example.com
    Then the response status code is 201
    And the response contains a short url with path /1
    And url mapping https://example.com to path /1 is persisted
