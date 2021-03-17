Feature: Choonz website tests
	
	@ignore
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
  
	@ignore
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
  
	@ignore
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
  
  @ignore
  Scenario Outline: As a User I want to create a Genre so that I can add it to albums
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the genres page
    And I click the create genre button
    And I enter the create genre details:
    | genre name | <genre name> |
    | genre description | <genre description> |
    And I submit the create genre form
    Then I can read a genre with the name "<genre name>"
    
    Examples:
  		| username | password | genre name | genre description | 
  		| test_user | test_password | test genre | test genre description |
  		
	@ignore
  Scenario Outline: As a User I want to read a list of Artists so that I can choose one to update
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the artists page
    Then I can read a list of artists
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
  @ignore
  Scenario Outline: As a User I want to read a list of Genres so that I can choose one to update
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the genres page
    Then I can read a list of genres
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
	@ignore
  Scenario Outline: As a User I want to read a single Artist so that I can update or delete it
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
    Then I can read a single artist
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  		
  Scenario Outline: As a User I want to read a single Genre so that I can update or delete it
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the genres page
    And I select a genre
    Then I can read a single genre
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  		
	@ignore
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
  		| username | password | artist updated name |
  		| test_user | test_password | test artist updated |

	@ignore
	Scenario Outline: As a User I want to delete an Artist so that it is removed from the database
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
    And I click the delete artist button
    And I accept the alert
    Then I can read "Artist deleted." on the artist page
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
  @ignore
	Scenario Outline: As a User I want to delete my account so that it is removed from the database
		Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
  	And I click the account button
    And I click the delete user button
    And I accept the alert
    Then the user account is deleted
	    
  	Examples:
	  		| username | password |
	  		| test_user | test_password |
  	