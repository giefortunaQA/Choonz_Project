package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:Choonz-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class UserIntegrationTest {

	@Autowired
	private  MockMvc mvc;
	@Autowired
	private ObjectMapper jsonify;
	private ModelMapper mapper = new ModelMapper();

	private UserDTO mapToDTO(User artist) {
		return this.mapper.map(artist, UserDTO.class);
	}

	private static String userToken = "";
	private static String URI = "/users";
	private final User user = new User(1L, "admin", "admin");
	private final UserDTO userAsDto = this.mapToDTO(user);
	private final List<UserDTO> users = List.of(userAsDto);
	private final Playlist playlist = new Playlist("Favourites","This is a playlist by admin consisting of public favourites.","https://icons.iconarchive.com/icons/aha-soft/3d-social/512/Favourites-icon.png", user);
	private final String playlistsJson = "[{\"id\":1,\"name\":\"Favourites\",\"description\":\"This is a playlist by admin consisting of public favourites.\",\"artwork\":\"https://icons.iconarchive.com/icons/aha-soft/3d-social/512/Favourites-icon.png\",\"tracks\":[{\"id\":1,\"name\":\"Thank U Next\",\"duration\":207,\"lyrics\":\"Thank U Next lyrics\"},{\"id\":2,\"name\":\"needy\",\"duration\":212,\"lyrics\":\"needy lyrics\"},{\"id\":3,\"name\":\"24K Magic\",\"duration\":240,\"lyrics\":\"24K Magic Lyrics\"},{\"id\":4,\"name\":\"Hello\",\"duration\":220,\"lyrics\":\"Hello lyrics\"},{\"id\":5,\"name\":\"What Do You Mean\",\"duration\":210,\"lyrics\":\"What Do You Mean lyrics\"}]}]}";
	private final List<Playlist> playlist1 = List.of(playlist);

	
	@BeforeEach
	public void init() throws Exception {
		String username = "admin";
		String password = "admin";
		RequestBuilder request = post(URI + "/login").header("username", username).header("password", password);

		ResultMatcher confirmStatus = status().isOk();
		ResultActions result = this.mvc.perform(request).andExpect(confirmStatus);
		userToken = result.andReturn().getResponse().getContentAsString();

	}

	@Test
	void testCreate() throws Exception {
		UserDTO toCreateDto = this.mapToDTO(new User("admin1", "admin1"));
		UserDTO expectedDto = this.mapToDTO(new User(2L, "admin1", "admin1"));
		String toCreateJson = this.jsonify.writeValueAsString(toCreateDto);
		String expectedJson = this.jsonify.writeValueAsString(expectedDto);

		RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(toCreateJson);

		ResultMatcher confirmStatus = status().isCreated();
		ResultMatcher confirmBody = content().json(expectedJson);
		mvc.perform(request).andExpect(confirmStatus).andExpect(confirmBody);
	}

	@Test
	void testReadAll() throws Exception {
		String artistsJson = this.jsonify.writeValueAsString(users);
		String expectedString = artistsJson.substring(0, artistsJson.length() - 6);
		expectedString += playlistsJson + "]";
		RequestBuilder request = get(URI + "/read");
		ResultMatcher confirmStatus = status().isOk();
		ResultMatcher confirmBody = content().json(expectedString);
		mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}

	@Test
	void testReadById() throws Exception {
		String ArianaJson = this.jsonify.writeValueAsString(userAsDto);
		String expectedString = ArianaJson.substring(0, ArianaJson.length() - 5);
		expectedString += playlistsJson;
		RequestBuilder request = get(URI + "/read/1");
		ResultMatcher confirmStatus = status().isOk();
		ResultMatcher confirmBody = content().json(expectedString);
		mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}

	@Test
	public void testUpdate() throws Exception {

		UserDTO toUpdate = this.mapToDTO(new User(1L, "Admin Update", "UpdatedPassword", playlist1));
		UserDTO expected = this.mapToDTO(new User(1L, "Admin Update", "UpdatedPassword", playlist1));
		String toUpdateJson = this.jsonify.writeValueAsString(toUpdate);
		String expectedJson = this.jsonify.writeValueAsString(expected);
		String expectedString=expectedJson.substring(0,expectedJson.length()-5);
		expectedString+=playlistsJson;
		RequestBuilder request = put(URI + "/update/1").contentType(MediaType.APPLICATION_JSON).content(toUpdateJson)
				.header("token", userToken);
		ResultMatcher confirmStatus = status().isAccepted();
		ResultMatcher confirmBody = content().json(expectedString);
		mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}

	@Test
	void testLogin() throws Exception {

		String username = "admin";
		String password = "admin";
		RequestBuilder request = post(URI + "/login").header("username", username).header("password", password);

		ResultMatcher confirmStatus = status().isOk();
		ResultActions result = mvc.perform(request).andExpect(confirmStatus)
				.andExpect(content().contentType("text/plain;charset=UTF-8"));
		userToken = result.andReturn().getResponse().getContentAsString();

	}
//	
//	@AfterEach
//	public void initEach(){
//		//setUserToken(userToken);
//		System.out.println("Hello" + getUserToken());
//	}
//	

	@Test
	void testDeletePass() throws Exception {
		RequestBuilder request = delete(URI + "/delete/1").header("token", userToken);
		System.out.println("Hello from Delete" + userToken);
		ResultMatcher confirmStatus = status().isNoContent();
		ResultMatcher confirmBody = content().string("");
		mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}

	@Test
	void testLogout() throws Exception {

	}

}
