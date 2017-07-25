Feature: Feature

  @Test
  Scenario: afishaTutByTest
    Given user open tut.by home page
    When afisha page open
    And select date, time 20 and film
    Then quantity of shots is five

  @Test
  Scenario Outline: financeTutByTest
    Given user open tut.by home page
    When finance page open
    And set bank "<BANK>" and summ "<SUM_OF_CREDIT>" of credit
    Then credit rate more than 15%
    Examples:
      | BANK            | SUM_OF_CREDIT |
      | Белагропромбанк | 3000          |
      | Беларусбанк     | 3000          |
      | Белинвестбанк   | 3000          |

  @Test
  Scenario: autoTutByTest
    Given user open tut.by home page
    When mobile version enable
    And post in video section in auto news open
    Then screen is full