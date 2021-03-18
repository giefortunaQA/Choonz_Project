package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Sql(scripts= {"classpath:Choonz-schema.sql","classpath:data.sql"},executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
public class UserIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper jsonify;
	private ModelMapper mapper=new ModelMapper();
	
	private UserDTO mapToDTO(User artist) {
		return this.mapper.map(artist, UserDTO.class);
	}
	
	private String URI="/users";
	private final UserDTO userAsDto=this.mapToDTO(new User(1L,"admin","admin"));
	private final List<UserDTO> users=List.of(userAsDto);
	private final String playlistsJson="[{\"id\":1,\"name\":\"Favourites\",\"description\":\"This is a playlist by admin consisting of public favourites.\",\"artwork\":\"https://icons.iconarchive.com/icons/aha-soft/3d-social/512/Favourites-icon.png\",\"tracks\":[{\"id\":1,\"name\":\"Thank U Next\",\"duration\":207,\"lyrics\":\"Thank U Next lyrics\"},{\"id\":2,\"name\":\"needy\",\"duration\":212,\"lyrics\":\"needy lyrics\"},{\"id\":3,\"name\":\"24K Magic\",\"duration\":240,\"lyrics\":\"24K Magic Lyrics\"},{\"id\":4,\"name\":\"Hello\",\"duration\":220,\"lyrics\":\"Hello lyrics\"},{\"id\":5,\"name\":\"What Do You Mean\",\"duration\":210,\"lyrics\":\"What Do You Mean lyrics\"}]}]}";
	
	@Test
	void testCreate() throws Exception{
		UserDTO toCreateDto=this.mapToDTO(new User("Test Username","Test Password"));
		UserDTO expectedDto=this.mapToDTO(new User(2L,"Test Username","Test Password"));
		String toCreateJson=this.jsonify.writeValueAsString(toCreateDto);
		String expectedJson=this.jsonify.writeValueAsString(expectedDto);
		
		RequestBuilder request=post(URI+"/create").contentType(MediaType.APPLICATION_JSON).content(toCreateJson);
	
		ResultMatcher confirmStatus=status().isCreated();
		ResultMatcher confirmBody=content().json(expectedJson);
		this.mvc.perform(request).andExpect(confirmStatus).andExpect(confirmBody);
	}
	
	@Test
	void testReadAll() throws Exception{
		String artistsJson=this.jsonify.writeValueAsString(users);
		String expectedString=artistsJson.substring(0,artistsJson.length()-6);
		expectedString+=playlistsJson+"]";
		RequestBuilder request=get(URI+"/read");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(expectedString);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testReadById() throws Exception{
		String ArianaJson=this.jsonify.writeValueAsString(userAsDto);
		String expectedString=ArianaJson.substring(0,ArianaJson.length()-5);
		expectedString+=playlistsJson;
		RequestBuilder request=get(URI+"/read/1");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(expectedString);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testUpdate() throws Exception{
		String token = null;
		UserDTO toUpdate=this.mapToDTO(new User("Pop Update","Description update"));
		UserDTO expected=this.mapToDTO(new User(1L,"Pop Update","Description update"));
		String toUpdateJson=this.jsonify.writeValueAsString(toUpdate);
		String expectedJson=this.jsonify.writeValueAsString(expected);
		RequestBuilder request=put(URI+"/update/1").contentType(MediaType.APPLICATION_JSON).content(toUpdateJson).header("token", token);
		ResultMatcher confirmStatus=status().isAccepted();
		ResultMatcher confirmBody=content().json(expectedJson);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testDeletePass() throws Exception{
		String token = null;
		RequestBuilder request=delete(URI+"/delete/1").header("token", token);
		ResultMatcher confirmStatus=status().isNoContent();
		ResultMatcher confirmBody=content().string("");
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	@Test
	void testLogin() throws Exception{
		
	}
	
	@Test 
	void testLogout() throws Exception{
		
	}
	
}
