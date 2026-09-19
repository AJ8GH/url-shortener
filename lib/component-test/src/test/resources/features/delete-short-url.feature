Feature: Delete short url

  Scenario: Delete happy path
    Given I make request to create short url for https://example.com
    And the response status code is 201
    And the response contains url mapping https://example.com to path 1
    When I make request to delete url mapping for path 1
    Then the response status code is 204
    And no url mappings are persisted

  Scenario: Delete idempotency
    Given no url mappings are persisted
    When I make request to delete url mapping for path 1
    Then the response status code is 204
    And no url mappings are persisted
