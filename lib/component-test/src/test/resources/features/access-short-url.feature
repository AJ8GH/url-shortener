Feature: Access short URL

  Scenario: Access happy path
    Given I make request to create short url for https://example.com
    And the response status code is 201
    And the response contains url mapping https://example.com to path 1
    When I make request to access url mapping for path 1
    Then the response status code is 302
    And the response contains header Location: https://example.com
