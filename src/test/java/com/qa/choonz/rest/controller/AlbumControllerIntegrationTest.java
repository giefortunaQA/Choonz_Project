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
import com.qa.choonz.rest.dto.AlbumDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:Choonz-schema.sql",
"classpath:data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	private ModelMapper mapper = new ModelMapper();

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	Artist testArtist1 = new Artist(1L, "Ariana Grande");
	Artist testArtist2 = new Artist(2L, "Justin Bieber");
	Artist testArtist3 = new Artist(3L, "Adele");
	Artist testArtist4 = new Artist(4L, "Bruno Mars");

	Genre testGenre1 = new Genre(1L, "Pop", "Popular music, any commercially oriented music principally intended to be received and appreciated by a wide audience, generally in literate, technologically advanced societies dominated by urban culture.");
	Genre testGenre2 = new Genre(2L, "Rock","Rock music is a broad genre of popular music that originated as rock and roll in the United States in the late 1940s and early 1950s.");
	Genre testGenre3 = new Genre(3L, "Country","Country (also called country and western) is a genre of popular music that originated with blues, old-time music, and various types of American folk music.");

	Album testAlbum1 = new Album(1L, "Thank U, Next",testArtist1,testGenre1, "https://upload.wikimedia.org/wikipedia/en/d/dd/Thank_U%2C_Next_album_cover.png");
	Album testAlbum2 = new Album(2L, "24K Magic",testArtist4,testGenre1,"https://upload.wikimedia.org/wikipedia/en/2/2b/Bruno_Mars_-_24K_Magic_%28Official_Album_Cover%29.png");
	Album testAlbum3 = new Album(3L, "25 Album",testArtist3,testGenre1,"https://upload.wikimedia.org/wikipedia/en/9/96/Adele_-_25_%28Official_Album_Cover%29.png");
	Album testAlbum4 = new Album(4L, "Purpose",testArtist2,testGenre1,"https://upload.wikimedia.org/wikipedia/en/2/27/Justin_Bieber_-_Purpose_%28Official_Album_Cover%29.png");



	private final String URI = "/albums";
	
	@Test
	void createIntegrationTest() throws Exception {       
		// RESOURCES
		AlbumDTO testSavedDTO = mapToDTO(testAlbum1); 
		String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
		
		// ACTIONS
		RequestBuilder request = post(URI + "/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testAlbum1));
		
		// ASSERTIONS
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
		assertNotNull(checkBody);
		assertNotNull(checkStatus);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void readAllIntegrationTest() throws Exception {
		// RESOURCES
		List<AlbumDTO> testSavedListDTO = List.of(mapToDTO(testAlbum1), mapToDTO(testAlbum2), mapToDTO(testAlbum3), mapToDTO(testAlbum4)); 

		String testSavedListAsJson = this.jsonifier.writeValueAsString(testSavedListDTO);

		// ACTIONS
		RequestBuilder request = get(URI + "/read")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testSavedListDTO));

		// ASSERTIONS
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testSavedListAsJson);
		assertNotNull(checkBody);
		assertNotNull(checkStatus);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void readByIdIntegrationTest() throws Exception {
		// RESOURCES
		AlbumDTO testSavedDTO = mapToDTO(testAlbum2);    

		// ACTIONS
		RequestBuilder request = get(URI + "/read/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testSavedDTO));

		// ASSERTIONS
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testAlbum2));
		assertNotNull(checkBody);
		assertNotNull(checkStatus);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void updateIntegrationTest() throws Exception {
		// RESOURCES
		AlbumDTO testSavedDTO = mapToDTO(testAlbum1);     
		
		AlbumDTO updatedDTO = testSavedDTO;
		updatedDTO.setName("I am Error");
		updatedDTO.setArtist(testArtist3);
		updatedDTO.setGenre(testGenre3);
		updatedDTO.setCover("String");

		// ACTIONS
		RequestBuilder request = put(URI + "/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(updatedDTO))
				.accept(MediaType.APPLICATION_JSON);

		// ASSERTIONS
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(updatedDTO));
		assertNotNull(checkBody);
		assertNotNull(checkStatus);
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

		assertNotNull(checkStatus);
		this.mvc.perform(request).andExpect(checkStatus);
	}
	
	@Test
	void deleteIntegrationIDFailTest() throws Exception {
		// RESOURCES

		// ACTIONS
		RequestBuilder request = delete(URI + "/delete/999");
		
		// ASSERTIONS
		ResultMatcher checkStatus = status().isInternalServerError();
		assertNotNull(checkStatus);
		this.mvc.perform(request).andExpect(checkStatus);
	}
	
	//TODO Research how to manipulate context under springboot so below situation can occur.
	//Where ID does exist but delete doesn't work so ID continues to exist.
	/*
	 * @Test void deleteIntegrationErrorTest() throws Exception { // RESOURCES Album
	 * falseAlbum = new Album(999L, "here", testArtist1, testGenre1, "cover");
	 * 
	 * // ACTIONS RequestBuilder request = delete(URI + "/delete/999"); //Need to be
	 * change context of the test st that repo can read the DB but not delete
	 * 
	 * // ASSERTIONS ResultMatcher checkStatus = status().isInternalServerError();
	 * assertNotNull(checkStatus);
	 * this.mvc.perform(request).andExpect(status().isInternalServerError()); }
	 */

}
