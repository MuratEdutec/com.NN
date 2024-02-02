Feature: US01_- Accessing mijn.nn is prohibited for users without a valid username and password.

  Scenario: US01_TC01- Check if the banner saying that the username/password is invalid shows

    Given go to "url"
    Then  accept cookies
    Then  click mijn.nn button


