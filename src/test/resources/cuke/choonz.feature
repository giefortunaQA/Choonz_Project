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
  Scenario Outline: As a User I want to create a Playlist so that I can add tracks to it
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the playlists page
    And I click the create playlist button
    And I enter the create playlist details:
    | playlist name | <playlist name> |
    | playlist artwork | <playlist artwork> |
    | playlist description | <playlist description> |
    And I submit the create playlist form
    Then I can read a playlist with the name "<playlist name>"
    
    Examples:
  		| username | password | playlist name | playlist artwork | playlist description | 
  		| test_user | test_password | test playlist | https://upload.wikimedia.org/wikipedia/commons/3/34/Art-portrait-collage_2.jpg | test playlist description |
  
  @ignore
	Scenario Outline: As a User I want to create an Album so that I can add tracks to it
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the albums page
    And I click the create album button
    And I enter the create album details:
    | album name | <album name> |
    | album cover | <album cover> |
    | album artist id | <album artist id> |
    | album genre id | <album genre id> |
    And I submit the create album form
    Then I can read an album with the name "<album name>"
    
    Examples:
  		| username | password | album name | album cover | album artist id | album genre id |
  		| test_user | test_password | test album | https://png.pngtree.com/thumb_back/fw800/back_pic/03/90/51/0657dd16e50ff98.jpg | 1 | 1 |
  
  @ignore
	Scenario Outline: As a User I want to create a Track so that I can add it to an Album
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the tracks page
    And I click the create track button
    And I enter the create track details:
    | track name | <track name> |
    | track lyrics | <track lyrics> |
    | track duration | <track duration> |
    | track album id | <track album id> |
    | track playlist id | <track playlist id> |
    And I submit the create track form
    Then I can read an track with the name "<track name>"
    
    Examples:
  		| username | password | track name | track lyrics | track duration | track album id | track playlist id |
  		| test_user | test_password | test track | test track lyrics | 100 | 1 | 1 |
  	
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
  Scenario Outline: As a User I want to read a list of Playlists so that I can choose one to update
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the playlists page
    Then I can read a list of playlists
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
  @ignore
  Scenario Outline: As a User I want to read a list of Albums so that I can choose one to update
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the albums page
    Then I can read a list of albums
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  		
  @ignore
	Scenario Outline: As a User I want to read a list of Tracks so that I can choose one to update
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the tracks page
    Then I can read a list of tracks
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  	
  @ignore
  Scenario Outline: As a User I want to read my user details so that I can update or delete my account
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the user page
    Then I can read a single user
    
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
  
  @ignore
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
  Scenario Outline: As a User I want to read a single Playlists so that I can update or delete it
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the playlists page
    And I select a playlist
    Then I can read a single playlist
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
  @ignore
  Scenario Outline: As a User I want to read a single Albums so that I can update or delete it
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the albums page
    And I select an Ablum
    Then I can read a single Album
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
	@ignore
	Scenario Outline: As a User I want to read single Track so that I can update or delete it
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the tracks page
    And I select a Track
    Then I can read a single track
    
    Examples:
  		| username | password |
  		| test_user | test_password |
  
  @ignore
  Scenario Outline: As a User I want to update my user details so that the information is correct
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the user page
    And I click the update user button
    And I enter the update user details:
    | username updated | <username updated> |
    | password updated | <password updated> |
    And I submit the update user form
    Then the user account is updated
    
    Examples:
  		| username | password | username updated | password updated |
  		| test_user | test_password | test_user_updated | test_password_updated |
  		
	@ignore
  Scenario Outline: As a User I want to update an Artist so that the information is correct
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
  		| test_user_updated | test_password_updated | test artist updated |
  
  @ignore
  Scenario Outline: As a User I want to update a Genre so that the information is correct
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
    And I click the update genre button
    And I enter the update genre details:
    | genre updated name | <genre updated name> |
    | genre updated description | <genre updated description> |
    And I submit the update genre form
    Then I can read an updated genre with the name "<genre updated name>"
    
    Examples:
  		| username | password | genre updated name | genre updated description |
  		| test_user_updated | test_password_updated | test genre updated | test genre description updated |

	@ignore
  Scenario Outline: As a User I want to update a Playlist so that the information is correct
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the playlists page
    And I select a playlist
    And I click the update playlist button
    And I enter the update playlist details:
    | playlist updated name | <playlist updated name> |
    | playlist updated artwork | <playlist updated artwork> |
    | playlist updated description | <playlist updated description> |
    And I submit the update playlist form
    Then I can read an updated playlist with the name "<playlist updated name>"
    
    Examples:
  		| username | password | playlist updated name | playlist updated artwork | playlist updated description | 
  		| test_user_updated | test_password_updated | test playlist updated | https://upload.wikimedia.org/wikipedia/commons/b/b3/Leitor_de_cartuchos_JVC.jpg | test playlist description updated |
  
  @ignore
  Scenario Outline: As a User I want to update an Album so that the information is correct
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the albums page
    And I select an Ablum
    And I click the update album button
    And I enter the update album details:
    | album updated name | <album updated name> |
    | album updated cover | <album updated cover> |
    | album updated artist id | <album updated artist id> |
    | album updated genre id | <album updated genre id> |
    And I submit the update album form
    Then I can read an updated album with the name "<album updated name>"
    
    Examples:
  		| username | password | album updated name | album updated cover | album updated artist id | album updated genre id |
  		| test_user_updated | test_password_updated | test album updated | https://upload.wikimedia.org/wikipedia/commons/1/13/Bleu_phtalo.jpg | 1 | 1 |
  
  
	Scenario Outline: As a User I want to update a Track so that the information is correct
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the tracks page
    And I select a Track
    And I click the update track button
    And I enter the update track details:
    | track updated name | <track updated name> |
    | track updated lyrics | <track updated lyrics> |
    | track updated duration | <track updated duration> |
    | track updated album id | <track updated album id> |
    | track updated playlist id | <track updated playlist id> |
    And I submit the update track form
    Then I can read an updated track with the name "<track updated name>"
    
    Examples:
  		| username | password | track updated name | track updated lyrics | track updated duration | track updated album id | track updated playlist id |
  		| test_user_updated | test_password_updated | test track | test track lyrics | 100 | 1 | 1 |
  		
  @ignore
	Scenario Outline: As a User I want to delete an Album so that it is removed from the database
    Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the albums page
    And I select an Ablum
    And I click the delete album button
    And I accept the alert
    Then I can read read "Album deleted." on the album page
    
    Examples:
  		| username | password |
  		| test_user_updated | test_password_updated |
  
  @ignore
  Scenario Outline: As a User I want to delete a Playlist so that it is removed from the database
  	Given that I can navigate to "http://localhost:8082/index.html"
    When I toggle the navbar
    And I click the account button
    And I accept the alert
    And I enter a username of "<username>" in the login form
    And I enter a password of "<password>" in the login form
    And I submit the login form
    And I toggle the navbar
    And I navigate to the playlists page
    And I select a playlist
    And I click the delete playlist button
    And I accept the alert
    Then I can read "Playlist deleted." on the playlist page
    
    Examples:
  		| username | password |
  		| test_user_updated | test_password_updated |
  
  @ignore
 	Scenario Outline: As a User I want to delete a Genre so that it is removed from the database
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
    And I click the delete genre button
    And I accept the alert
    Then I can read "undefined" on the genre page
    
    Examples:
  		| username | password |
  		| test_user_updated | test_password_updated |
  		
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
  		| test_user_updated | test_password_updated |
  
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
	  		| test_user_updated | test_password_updated |
  	