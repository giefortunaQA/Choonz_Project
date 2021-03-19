package com.qa.choonz.rest.controller;


public class PseduoUserControllerUnitTest {
	
	

	public void pseudoCreateTest()
	{
						  // RESOURCES \\
		
		//New User Object (username,password)
		//To store our user values
		
		//MapToDTO Method
		// A method used to convert a User entity into a Data transfer object (UserDTO)
		
							 // ACTIONS \\
		
		//Mock when a userService invokes it's create method with the new User object as it's parameter
		//Return a UserDTO object that has had an User entity mapped to it
		
		
						    // ASSERTIONS \\
		
		//Confirm that the a new responseEntiy object is made of a UserDTo object
		//Which contains a response code of 201
		//And that it is equal to of how our actual controller will perform the create method 
		//using our user object 
		
		//assertThat(new ResponseEntity<>(this.mapToDTO(), HttpStatus.CREATED))
		//.isEqualTo(this.controller.create());
		
		//Verify the userService create method has been executed at least once 
		
	}
	
	public void pseudoReadAllUsersTest()
	{
		
	}
	
	

}
