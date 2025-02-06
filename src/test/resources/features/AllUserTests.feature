@test @user
Feature: TC03 All User Tests

  Scenario Outline: TC03.01 Create user from list
    Given Userlist <userlist>
    Then User is retrieved

    Examples: 
      | userlist                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
      | "[ { \\"id\\": 0, \\"username\\": \\"name1\\", \\"firstName\\": \\"fname1\\", \\"lastName\\": \\"fname1\\", \\"email\\": \\"email1@test.com\\", \\"password\\": \\"name1\\", \\"phone\\": \\"0123456789\\", \\"userStatus\\": 1 }, { \\"id\\": 0, \\"username\\": \\"name2\\", \\"firstName\\": \\"fname2\\", \\"lastName\\": \\"lname2\\", \\"email\\": \\"email2@test.com\\", \\"password\\": \\"pwname2\\", \\"phone\\": \\"name2\\", \\"userStatus\\": 2 } ]" |

  Scenario Outline: TC03.02 Get user by username
    Given Search for user by username '<username>' code <code>
    Then User is retrieved

    Examples: 
      | username  | code |
      | name1     |  200 |
      | name2     |  200 |
      | name33333 |  404 |


  Scenario Outline: TC03.03 Update user by username
    When A user is updated with username '<username>' firstname '<firstname>' lastname '<lastname>' email '<email>' password '<password>' phone '<phone>' userStatus <userStatus> code <code>
    Then The user record is updated

    Examples: 
      | username  | firstname  | lastname  | email           | password  | phone      | userStatus | code |
      | name1     | firstname1 | lastname1 | test1@email.com | password1 | 0450123123 |          1 |  200 |
      | name2     | firstname2 | lastname2 | test2@email.com | password2 | 0234123123 |          4 |  200 |
      | name23456 | firstname3 | lastname3 | test3@email.com | password3 |      12345 |          6 |  404 |

  Scenario Outline: TC03.04 User logs into the system
    Then User can login to the system with username '<username>' password '<password>' code <code>

    Examples: 
      | username  | password  | code |
      | name1     | password1 |  200 |
      | name2     | password2 |  200 |
      | name33333 | password3 |  400 |


  Scenario Outline: TC03.05 Create a user by logged in user
    Then User can login to the system with username '<username>' password '<password>' code <code>
    When A user is created with username '<username>' firstname '<firstname>' lastname '<lastname>' email '<email>' password '<password>' phone '<phone>' userStatus <userStatus> code <code>

    Examples: 
      | username  | firstname  | lastname  | email           | password  | phone      | userStatus | code |
      | name5     | firstname5 | lastname1 | test5@email.com | password5 | 0450123125 |          5 |  200 |
      | name6     | firstname6 | lastname6 | test6@email.com | password6 | 0234123126 |          6 |  200 |
      | name34567 | firstname7 | lastname7 | test3@email.com | password7 |      12345 |          7 |  404 |


  Scenario Outline: TC03.06 User logout of the system
    Then User can logout from the system with username '<username>' code <code>

    Examples: 
      | username  | code |
      | name1     |  200 |
      | name2     |  200 |
      | name23456 |  404 |


  Scenario Outline: TC03.07 Delete user by username
    Given User is deleted with username '<username>', code <code>
    Then User record '<username>' is deleted

    Examples: 
      | username  | code |
      | name1     |  200 |
      | name2     |  200 |
      | name23456 |  404 |


  Scenario Outline: TC03.08 Create user with array
    Given Userarray <userarray>
    Then User is retrieved

    Examples: 
      | userarray                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
      | "[ { \\"id\\": 0, \\"username\\": \\"name3\\", \\"firstName\\": \\"fname3\\", \\"lastName\\": \\"lname1\\", \\"email\\": \\"email3@test.com\\", \\"password\\": \\"pw3\\", \\"phone\\": \\"0254123456\\", \\"userStatus\\": 0 }, { \\"id\\": 0, \\"username\\": \\"name4\\", \\"firstName\\": \\"fname4\\", \\"lastName\\": \\"lname4\\", \\"email\\": \\"email4@test.com\\", \\"password\\": \\"password4\\", \\"phone\\": \\"0340456789\\", \\"userStatus\\": 4 } ]" |
