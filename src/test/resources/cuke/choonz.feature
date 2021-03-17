Feature: Choonz website tests
	
  Scenario Outline: As a User I want to create an account on the website so that I can login
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I dismiss the alert
    And I enter a username of "<username>" in the signup form
    And I enter a password of "<password>" in the signup form
    And I submit the sign up form
    Then I see the text "User created."
    
		Examples:
  		| username | password |
  		| test_user | test_password |
  
	Scenario Outline: As a User I want to login to an account so that I can CRUD
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I go home
    And I toggle the navbar
    Then I can see the logout button
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
	Scenario Outline: As a User I want to create an Artist so that I can add albums to it
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the artists page
    And I click the create artist button
    And I enter the create artist details:
    | artist name | <artist name> |
    And I submit the create artist form
    Then I can read an artist with the name "<artist name>"
    
    Examples:
  		| username | password | artist name |
  		| test_user | test_password | test artist |
 
  Scenario Outline: As a User I want to update an Artist so that the information is current
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the artists page
    And I select an artist
    And I click the update artist button
    And I enter the update artist details:
    | artist updated name | <artist updated name> |
    And I submit the update artist form
    Then I can read an updated artist with the name "<artist updated name>"
    
    Examples:
  		| username | password | artist name | artist updated name |
  		| test_user | test_password | test artist | test artist updated |
  		
  	@ignore
  	Scenario: something is here
  		Given that I can genre "http://localhost:8082/genres.html"