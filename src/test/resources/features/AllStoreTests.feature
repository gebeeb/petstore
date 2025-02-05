@test @store
Feature: TC02 All Store Tests

  Scenario: Return pet inventories
    When User sends an inventory request
    Then A map of status codes to quantities is returned

  Scenario Outline: Place an order for pet
    #    Given Pet with id <petId> is existing, code <code>
    When User places a store order with petId <petId> quantity <quantity> shipdate '<shipDate>' status '<status>' complete '<complete>' code <code>
    Then Order is created and retrieved

    Examples: 
      | petId | quantity | shipDate                 | status   | complete | code |
      |   111 |        3 | 2025-02-05T05:04:10.270Z | placed   | true     |  200 |
      |   222 |        1 | 2025-01-28T08:37:12.000Z | approved | false    |  200 |

  @thistest
  Scenario Outline: Place an order with id for pet
    When User places a store order with petId <petId> quantity <quantity> shipdate '<shipDate>' status '<status>' complete '<complete>' code <code>
    Then Order is created and retrieved

    Examples: 
      |  id    | petId | quantity | shipDate                 | status   | complete | code |
      |  501   |   111 |        3 | 2025-02-05T05:04:10.270Z | placed   | true     |  200 |
      |  502   |   222 |        1 | 2025-01-28T08:37:12.000Z | approved | false    |  200 |
      

  Scenario Outline: Find purchase by ID
    When User retrieves an order with id <id> , code <code>

    Examples: 
      | id  | code |
      |  -1 |  404 |
      |   1 |  200 |
      |  10 |  200 |
      | 501 |  404 |
      | 888 |  404 |


  Scenario Outline: Deletes purchase by ID
    Given User deletes an order with id <id>, code <code>
    Then Order record <id> is deleted

    Examples: 
      | id  | code |
      | 501 |  200 |
      | 502 |  200 |      
      | 10  |  200 |
      |  0  |  404 |      
      | -5  |  404 |
