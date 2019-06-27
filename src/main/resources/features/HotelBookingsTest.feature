Feature: Save and Delete Bookings
  In order to verify that hotel booking sire
  As a user of hotel booking site
  I should be able to save and delete the booking

  @eentest
  Scenario Outline: Save and delete the booking
    Given I access equal experts hotel booking site
    When I enter the fields with the values '<FirstName>', '<LastName>', '<Price>', '<Deposit>', '<Checkin>', '<Checkout>' and click on Save
    Then I should find a booking with the values '<FirstName>', '<LastName>', '<Price>', '<Deposit>', '<Checkin>', '<Checkout>'
    When I delete the booking with the value '<FirstName>'
    Then I should not find a booking with the value '<FirstName>'
    Examples:
      | FirstName   | LastName            | Price         | Deposit | Checkin    | Checkout   |
      | Magam_1234  | Magam_1234          | 200           | true    | 2019-07-01 | 2019-07-03 |
      | Magam_**$^& | Magam_**$^&         | 20            | false   | 2019-10-01 | 2019-12-03 |
      | Magam_**$^& | Magam_**$^&         | 1000000000000 | false   | 2019-10-01 | 2019-12-03 |
      | .           | Magam_**$^& 0000000 | 1000000000000 | false   | 2019-12-31 | 2020-02-20 |


  @eentest
  Scenario Outline: Save fails when I don't fill mandatory fields
    Given I access equal experts hotel booking site
    When I enter the fields with the values '<FirstName>', '<LastName>', '<Price>', '<Deposit>', '<Checkin>', '<Checkout>' and click on Save
    Then I should not find a booking with the value '<FirstName>'
    Examples:
      | FirstName   | LastName   | Price | Deposit | Checkin    | Checkout   |
      | Magam_**$^& |            | 200   | true    | 2019-07-01 | 2019-07-03 |
      | Magam_**$^& | Magam_1234 |       | false   | 2019-10-01 |            |
      | Magam_**$^& | Magam_1234 | 0     | false   | 2019000000 |            |