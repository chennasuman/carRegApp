@fulltest
Feature: Car Tax Check with car registration and compare

  Scenario Outline: Preform Free Car tax check with reg multiple
    Given user launches car tax check site
    When user enter "<carNumberSeq>" carreg numbers
    Then user should see "<carNumberSeq>" car details

    Examples:
      | carNumberSeq |
      | 1            |
      | 2            |
      | 3            |
      | 4            |
