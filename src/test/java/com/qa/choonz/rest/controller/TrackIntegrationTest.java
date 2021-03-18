package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.qa.choonz.rest.dto.TrackDTO;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts= {"classpath:Choonz-schema.sql","classpath:test-data.sql"},executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
public class TrackIntegrationTest {

	private static final Genre pop = null;
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper jsonify;
	private ModelMapper mapper=new ModelMapper();
	
	private TrackDTO mapToDTO(Track artist) {
		return this.mapper.map(artist, TrackDTO.class);
	}
	
	
	private String URI="/tracks";
	private final User testUser=new User(1L,"admin","admin");
	private Artist Ariana;
	private final Album testAlbum=new Album(1L,"Thank U, Next",Ariana,pop,"https://upload.wikimedia.org/wikipedia/en/d/dd/Thank_U%2C_Next_album_cover.png");
	private final Playlist testPlaylist= new Playlist(1L,"Favourites","This is a track by admin consisting of public favourites.","https://icons.iconarchive.com/icons/aha-soft/3d-social/512/Favourites-icon.png",testUser);
	private final TrackDTO trackAsDto=this.mapToDTO(new Track(2L, "needy", null, null, 212L, "needy lyrics"));
	private final List<TrackDTO> tracks=List.of(trackAsDto);
	
	@Test
	void testCreate() throws Exception{
		TrackDTO toCreateDto=this.mapToDTO(new Track(null, "Track Name", null, null, 600L, "Lyrics"));
		TrackDTO expectedDto=this.mapToDTO(new Track(6L, "Track Name", null, null, 600L, "Lyrics"));
		String toCreateJson=this.jsonify.writeValueAsString(toCreateDto);
		String expectedJson=this.jsonify.writeValueAsString(expectedDto);
		
		RequestBuilder request=post(URI+"/create").contentType(MediaType.APPLICATION_JSON).content(toCreateJson);
	
		ResultMatcher confirmStatus=status().isCreated();
		ResultMatcher confirmBody=content().json(expectedJson);
		this.mvc.perform(request).andExpect(confirmStatus).andExpect(confirmBody);
	}
	
	@Test
	void testReadAll() throws Exception{
		String artistsJson=this.jsonify.writeValueAsString(tracks);
		RequestBuilder request=get(URI+"/read");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(artistsJson);
		this.mvc.perform(request).andExpect(confirmStatus);
		assertThat("artist").isIn();
	}
	
	@Test
	void testReadById() throws Exception{
		String ArianaJson=this.jsonify.writeValueAsString(trackAsDto);
		RequestBuilder request=get(URI+"/read/2");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(ArianaJson);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testUpdate() throws Exception{
		TrackDTO toUpdate=this.mapToDTO(new Track(1L,"UpdatedName", null, null, 900L, "UpdatedLyrics"));
		TrackDTO expected=this.mapToDTO(new Track(1L, "UpdatedName", null, null, 900L, "UpdatedLyrics"));
		String toUpdateJson=this.jsonify.writeValueAsString(toUpdate);
		String expectedJson=this.jsonify.writeValueAsString(expected);
		RequestBuilder request=put(URI+"/update/1").contentType(MediaType.APPLICATION_JSON).content(toUpdateJson);
		ResultMatcher confirmStatus=status().isAccepted();
		ResultMatcher confirmBody=content().json(expectedJson);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testDeletePass() throws Exception{
		RequestBuilder request=delete(URI+"/delete/2");
		ResultMatcher confirmStatus=status().isNoContent();
		ResultMatcher confirmBody=content().string("");
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
}
