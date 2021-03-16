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


@SpringBootTest
public class UserControllerUnitTest {
	
  
	        @Autowired
			private UserController controller;
		    
		    @MockBean
		    private UserService userService;
		 
		    
		    @Autowired
		    private ModelMapper mapper;
			
		    private final User testUser = new User(2L, "Sehun",  "Password", null);
		    private final List<User> listOfUsers = List.of(testUser);
		    
		    private UserDTO mapToDTO(User user)
		    {
		    	return this.mapper.map(user, UserDTO.class);
		    }
	
	
	@Test
	public void createUserTest()
	{
                             	//ACTIONS
		Mockito.when(this.userService.create(testUser)).thenReturn(this.mapToDTO(testUser));
		
								
		                       //ASSERTIONS
		
		assertThat(new ResponseEntity<UserDTO>(this.mapToDTO(testUser), HttpStatus.CREATED))
		.isEqualTo(this.controller.create(testUser));
			
		
	    verify(this.userService, atLeastOnce()).create(testUser);
		
	}
	
	@Test
	public void readAllUsersTest()
	{
		
							//RESOURCES
		List<UserDTO> testReadList = listOfUsers.stream().map(this:: mapToDTO).
				collect(Collectors.toList());
				
		
							  //ACTIONS
		when(this.userService.read()).thenReturn(testReadList);
		
						
							//ASSERTIONS
		
		ResponseEntity<List<UserDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<UserDTO>> result = this.controller.read();
		assertEquals(expected, result);
		
		
	}
	
	

	@Test
	public void readUserByIDTest()
	{

		
		
	}
	

	@Test
	public void readUserByNameTest()
	{

	
		
	}
	
	

}