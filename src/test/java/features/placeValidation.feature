Feature: Validating Place API

  @AddPlace
  Scenario Outline: Verify if place is being added using Add Place API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "Post" Request
    Then API call success with status code 200
    And "status" is "OK"
    And "scope" is "APP"
    And Verify Place id created maps to "<name>" using "GetPlaceAPI"

    Examples:
    |name|language|address|
    |Rohit|Hindi  |GanGan |

  @DeletePlace
  Scenario: Verify If delete place functionality is working fine
    Given Delete Place Payload
    When User calls "DeletePlaceAPI" with "Post" Request
    Then API call success with status code 200
    And "status" is "OK"
