package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;
import com.qa.choonz.utils.AuthUtils;



@SpringBootTest
public class UserControllerUnitTest {

	@Autowired
	private UserController controller;

	@MockBean
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	private final User testUser = new User(2L, "Sehun", "Password", null);
	private final List<User> listOfUsers = List.of(testUser);

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	@Test
	public void createUserTest() {
		
		// ACTIONS
		Mockito.when(this.userService.create(testUser)).thenReturn(this.mapToDTO(testUser));

		// ASSERTIONS

		assertThat(new ResponseEntity<UserDTO>(this.mapToDTO(testUser), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testUser));

		verify(this.userService, atLeastOnce()).create(testUser);

	}

	@Test
	public void readAllUsersTest() {

		// RESOURCES
		List<UserDTO> testReadList = listOfUsers.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.userService.read()).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<UserDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<UserDTO>> result = this.controller.read();
		assertEquals(expected, result);
		verify(this.userService, atLeastOnce()).read();
	}

	@Test
	public void readUserByIDTest() {

		// RESOURCES
		Long testID = 2L;
		UserDTO testReadUser = this.mapToDTO(testUser);

		// ACTIONS
		when(this.userService.read(testID)).thenReturn(testReadUser);

		// ASSERTIONS

		ResponseEntity<UserDTO> expected = ResponseEntity.ok(testReadUser);
		ResponseEntity<UserDTO> result = this.controller.read(testID);
		assertEquals(expected, result);
		verify(this.userService, Mockito.times(1)).read(testID);

	}

	@Test
	public void readUserByNameTest() {

		         // RESOURCES
				String testName = "Sehun";
				UserDTO testReadUser = this.mapToDTO(testUser);

				// ACTIONS
				when(this.userService.read(testName)).thenReturn(testReadUser);

				// ASSERTIONS

				ResponseEntity<UserDTO> expected = ResponseEntity.ok(testReadUser);
				ResponseEntity<UserDTO> result = this.controller.read(testName);
				assertEquals(expected, result);
				verify(this.userService,Mockito.times(1)).read(testName);
	}

	
	@Test
	public void updateUserTest()
	{
		   // RESOURCES
		Long testID = 2L;
		String token = AuthUtils.createUserToken(testID);
		 
		 User newUser = new User();
		 newUser.setId(testID);
		 newUser.setUsername("Updated Task name");
		 
		          // ACTIONS
		 UserDTO testUpdateUser = this.mapToDTO(newUser);
		
		 when(this.userService.update(newUser, testID)).thenReturn(testUpdateUser);
		 
		 
		       // ASSERTIONS
		 ResponseEntity <UserDTO> expected = new ResponseEntity<UserDTO>(testUpdateUser, HttpStatus.ACCEPTED);
		 ResponseEntity <UserDTO> result = this.controller.update(newUser, testID, token);
	
			assertEquals(expected,result);
		  verify(this.userService, Mockito.times(1)).update(newUser, testID);
		  
		  
		  
			
	
	}
	
	@Test
	public void updateUnauthorisedUser()
	{
			// RESOURCES
		Long testID = 2L;
		Long badTestID = 3L;
		String secondToken = AuthUtils.createUserToken(badTestID);
		 
			// ACTIONS
		 User newUser2 = new User();
		 newUser2.setId(2L);
		 newUser2.setUsername("Updated Task name");
		 UserDTO testUpdateUser2 = this.mapToDTO(newUser2);
		
		 when(this.userService.update(newUser2, testID)).thenReturn(testUpdateUser2);
		 
		 
		 				// ASSERTIONS
		 ResponseEntity <UserDTO> expected2 = new ResponseEntity<UserDTO>(HttpStatus.UNAUTHORIZED);
		 ResponseEntity <UserDTO> result2 = this.controller.update(newUser2, testID, secondToken);
	
			assertEquals(expected2,result2);
		 verify(this.userService, Mockito.times(0)).update(newUser2, testID);
	}
	
	
	
	@Test
	public void deleteUserTest()
	{
				// RESOURCES
		Long testID = 2L;
		String token = AuthUtils.createUserToken(testID);
		
					// ACTIONS
		 when(this.userService.delete(testID)).thenReturn(true);
		 
		 		// ASSERTIONS
		 ResponseEntity <UserDTO> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 ResponseEntity <UserDTO> result = this.controller.delete(testID, token);
		 System.out.println(expected);
		 System.out.println(result);
		 
		 assertEquals(expected,result);
		  verify(this.userService, Mockito.times(1)).delete(testID);
		  
		  
		      // ACTIONS
			
			 when(this.userService.delete(testID)).thenReturn(false);
			   
			 // ASSERTIONS
			 ResponseEntity <UserDTO> expected2 = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			 
			 
			 ResponseEntity <UserDTO> result2 = this.controller.delete(testID, token);
			 System.out.println(expected2);
			 System.out.println(result2);
			 
			 assertEquals(expected,result);
			  verify(this.userService, Mockito.times(2)).delete(testID);
		
	}
	
	@Test
	public void deleteUnauthorisedUserTest()
	{
		      //RESOURCES
		Long testID = 2L;
		Long badTestID = 3L;
		String token = AuthUtils.createUserToken(badTestID);
		
					//ACTIONS
		 when(this.userService.delete(testID)).thenReturn(false);
		 
		 			// ASSERTIONS
		 ResponseEntity <UserDTO> expected = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		 ResponseEntity <UserDTO> result = this.controller.delete(testID, token);
		 System.out.println(expected);
		 System.out.println(result);
		 
		 assertEquals(expected,result);
		  verify(this.userService, Mockito.times(0)).delete(testID);
		  
	}
	
	
	
	
}


