package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.PlaylistDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:Choonz-schema.sql",
"classpath:data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PlaylistControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	private ModelMapper mapper = new ModelMapper();

	private PlaylistDTO mapToDTO(Playlist Playlist) {
		return this.mapper.map(Playlist, PlaylistDTO.class);
	}

	Artist testArtist1 = new Artist(1L, "Ariana Grande");
	Artist testArtist2 = new Artist(2L, "Justin Bieber");

	Genre testGenre1 = new Genre(1L, "Pop", "Popular music, any commercially oriented music principally intended to be received and appreciated by a wide audience, generally in literate, technologically advanced societies dominated by urban culture.");
	Genre testGenre2 = new Genre(2L, "Rock","Rock music is a broad genre of popular music that originated as rock and roll in the United States in the late 1940s and early 1950s.");
	
	Album testAlbum1 = new Album(1L, "Thank U, Next",testArtist1,testGenre1, "https://upload.wikimedia.org/wikipedia/en/d/dd/Thank_U%2C_Next_Playlist_cover.png");
	
	Track testTrack = new Track(1L, "Thank U Next", testAlbum1, null, 207, "Thank U Next lyrics");
	
	List<Track> tracks = List.of(testTrack);
	
	User testUser = new User(1L, "admin","admin", null);
	
	Playlist testPlaylist = new Playlist(1L, "Favourites","This is a playlist by admin consisting of public favourites.","https://icons.iconarchive.com/icons/aha-soft/3d-social/512/Favourites-icon.png", tracks, testUser);
	Playlist testPlaylist2 = new Playlist(2L, "Test", "Test", "Test", null, testUser);
	
	private final String URI = "/playlists";

	@Test
	void createIntegrationTest() throws Exception {       
		// RESOURCES
		PlaylistDTO testSavedDTO = mapToDTO(testPlaylist); 
		String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
		
		// ACTIONS
		RequestBuilder request = post(URI + "/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testPlaylist));
		
		// ASSERTIONS
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
		assertNotNull(request);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void readAllIntegrationTest() throws Exception {
		// RESOURCES
		List<PlaylistDTO> testSavedListDTO = List.of(mapToDTO(testPlaylist), mapToDTO(testPlaylist2)); 

		String testSavedListAsJson = this.jsonifier.writeValueAsString(testSavedListDTO);

		// ACTIONS
		RequestBuilder request = get(URI + "/read")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testSavedListDTO));

		// ASSERTIONS
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testSavedListAsJson);
		assertNotNull(request);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void readByIdIntegrationTest() throws Exception {
		// RESOURCES
		PlaylistDTO testSavedDTO = mapToDTO(testPlaylist2);    

		// ACTIONS
		RequestBuilder request = get(URI + "/read/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testSavedDTO));

		// ASSERTIONS
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testPlaylist2));
		assertNotNull(request);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void updateIntegrationTest() throws Exception {
		// RESOURCES
		PlaylistDTO testSavedDTO = mapToDTO(testPlaylist);     
		
		PlaylistDTO updatedDTO = testSavedDTO;
		updatedDTO.setName("I am Error");
		updatedDTO.setDescription("I am test");
		updatedDTO.setArtwork("String");

		// ACTIONS
		RequestBuilder request = put(URI + "/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(updatedDTO))
				.accept(MediaType.APPLICATION_JSON);

		// ASSERTIONS
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(updatedDTO));
		assertNotNull(request);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void deleteIntegrationTest() throws Exception {
		// RESOURCES
		// ACTIONS
		RequestBuilder request = delete(URI + "/delete/1")
				.contentType(MediaType.APPLICATION_JSON);
				
		// ASSERTIONS
		ResultMatcher checkStatus = status().isNoContent();

		assertNotNull(request);
		this.mvc.perform(request).andExpect(checkStatus);
	}
	
	@Test
	void deleteIntegrationIDFailTest() throws Exception {
		// RESOURCES

		// ACTIONS
		RequestBuilder request = delete(URI + "/delete/999");
		
		// ASSERTIONS
		ResultMatcher checkStatus = status().isInternalServerError();
		assertNotNull(request);
		this.mvc.perform(request).andExpect(checkStatus);
	}
	
	//TODO Research how to manipulate context under springboot such that attempting the delete method fails and the entity still exists
}
