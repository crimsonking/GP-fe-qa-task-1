Feature: WH customer has ability to place a bet

  As a WH Customer
  I want the ability to place a bet on a English Premier League event

  Scenario: WH customer place a bet on a English Premier League event
    Given I see events page
    When I open football events
    And I open first event
    Then I am able to place a bet
    When I select "Home team to win"
    And bet size is "0.15" GBP
    Then returns are offered