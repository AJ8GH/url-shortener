Feature: Create short URL

  Scenario: Get happy path
    Given I make request to create short url for https://example.com
    And the response status code is 201
    And the response contains url mapping https://example.com to path 1
    When I make request to get url mapping for path 1
    Then the response status code is 200
    And the response contains url mapping https://example.com to path 1

  Scenario: Get 404
    Given no url mappings are persisted
    When I make request to get non-existent url mapping for path 1
    Then the response status code is 404
    And the response contains error message no url mapping found for short-url path 1
