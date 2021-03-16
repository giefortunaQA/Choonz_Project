Feature: Choonz website tests

  Scenario: As a User I want to create an account on the website so that I can login
    Given that I can navigate to "http://localhost:8082/index.html"
    When I click the account button
    And I cancel the alert
    And I enter a username of "<username>"
    And I enter a password of "<password>"
    And I submit the sign up form
    Then I see the text "User created."
    
		Examples:
  		| username | password |
  		| test_user | test_password |