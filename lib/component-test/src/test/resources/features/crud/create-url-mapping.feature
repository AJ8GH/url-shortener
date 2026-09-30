Feature: Create short URL mapping

  Scenario: Create happy path
    When I make request to create url-mapping for https://example.com
    Then the response status code is 201
    And the response contains url-mapping https://example.com to path 1
    And url-mapping https://example.com to path 1 is persisted

  Scenario Outline: Create happy path - encoding
    Given <number-of-urls> url-mappings are created
    When I make request to create url-mapping for https://example.com
    Then the response status code is 201
    And the response contains url-mapping https://example.com to path <expected-short-path>
    And url-mapping https://example.com to path <expected-short-path> is persisted
    Examples:
      | number-of-urls    | expected-short-path |
      | 9                 | A                   |
      | 29                | U                   |
      | 60                | z                   |
      | 64                | 13                  |
      | 722               | Bf                  |
      | 12239822          | pM8V                |
      | 98765432123456788 | 7ILUojvWNx          |
