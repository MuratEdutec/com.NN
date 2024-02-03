Feature: US01_- Accessing mijn.nn is prohibited for users without a valid username and password.

  Scenario: US01_TC01- Check if the banner saying that the username/password is invalid shows in English version

    Given go to "url"
    Then  accept cookies
    Then  click mijn.nn button
    Then  click English version link
    Then  enter an invalid username and password
    And   click Login to mijn.nn button
    Then  verify that the invalid username and password banner is shown


  Scenario: US01_TC02- Check if the banner saying that the username/password is invalid shows in teh Dutch version

    Given go to "url"
    #Then  accept cookies
    Then  click mijn.nn button
    Then  click the Dutch version link
    Then  enter an invalid username and password
    And   click Login to mijn.nn button
    Then  verify that the invalid username and password banner is shown



