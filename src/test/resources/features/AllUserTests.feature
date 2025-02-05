@test @user
Feature: TC03 All User Tests

  Scenario Outline: TC03.01 Create user from list
    Given Userlist <userlist>
    Then User is retrieved

    Examples: 
      | userlist                                                                                                                                                                                                                                                                                                                                                                                                                           |
      | "[ { \\"id\\": 0, \\"username\\": \\"name1\\", \\"firstName\\": \\"name1\\", \\"lastName\\": \\"name1\\", \\"email\\": \\"name1\\", \\"password\\": \\"name1\\", \\"phone\\": \\"name1\\", \\"userStatus\\": 0 }, { \\"id\\": 0, \\"username\\": \\"name2\\", \\"firstName\\": \\"name2\\", \\"lastName\\": \\"name2\\", \\"email\\": \\"name2\\", \\"password\\": \\"name2\\", \\"phone\\": \\"name2\\", \\"userStatus\\": 0 } ]" |

  Scenario Outline: TC03.02 Get user by username
    Given Search for user by username '<username>' code <code>
    Then User is retrieved

    Examples: 
      | username  | code |
      | name1     |  200 |
      | name2     |  200 |
      | name23456 |  404 |

  Scenario Outline: TC03.03 Update user by username
    Given A user is existing with username '<username>' code <code>
    When A user is updated with firstname '<firstname>' lastname '<lastname>' email '<email>' password '<password>' phone '<phone>' userStatus <userStatus> code <code>
    Then The user record is updated

    Examples: 
      | username  | firstname | lastname  | email           | password  | phone      | userStatus | code |
      | name1     | fname1    | lastname1 | test1@email.com | password1 | 0450123123 |          1 |  200 |
      | name2     | fname2    | lastname2 | test2@email.com | password2 | 0234123123 |          4 |  200 |
      | name23456 | fname3    | lastname3 | test3@email.com | password3 |      12345 |          6 |  404 |

  Scenario Outline: TC03.04 Delete user by username
    Given User is deleted with username '<username>', code <code>
    Then User record '<username>' is deleted

    Examples: 
      | username  | code |
      | name1     |  200 |
      | name2     |  200 |
      | name23456 |  404 |
