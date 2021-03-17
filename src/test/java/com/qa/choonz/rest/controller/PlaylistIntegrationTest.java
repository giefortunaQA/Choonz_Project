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
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Sql(scripts= {"classpath:Choonz-schema.sql","classpath:data.sql"},executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
public class PlaylistIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper jsonify;
	private ModelMapper mapper=new ModelMapper();
	
	private PlaylistDTO mapToDTO(Playlist artist) {
		return this.mapper.map(artist, PlaylistDTO.class);
	}
	
	private String URI="/playlists";
	private final User testUser=new User(1L,"admin","admin");
	private final Artist Ariana=new Artist(1L,"Ariana Grande");
	private final Artist Bieber=new Artist(2L,"Justin Bieber");
	private final Artist Adele=new Artist(3L,"Adele");
	private final Artist Bruno=new Artist(4L,"Bruno Mars");
	private final Genre pop=new Genre(1L,"Pop","Popular music, any commercially oriented music principally intended to be received and appreciated by a wide audience, generally in literate, technologically advanced societies dominated by urban culture.");
	private final Genre rock=new Genre(2L,"Rock","Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s.");
	private final Genre country=new Genre(3L,"Country","Country (also called country and western) is a genre of popular music that originated with blues, old-time music, and various types of American folk music.");
	private final Genre reggae=new Genre(4L,"Reggae","Reggae is a popular style of music that began in Jamaica and often has political and spiritual themes.");
	private final Album testAlbum=new Album(1L,"Thank U, Next",Ariana,pop,"https://upload.wikimedia.org/wikipedia/en/d/dd/Thank_U%2C_Next_album_cover.png");
	private final Playlist testPlaylist=new Playlist(1L,"Favourites","This is a playlist by admin consisting of public favourites.","https://icons.iconarchive.com/icons/aha-soft/3d-social/512/Favourites-icon.png",testUser);
	private final String tracksAsString="[{\"id\":1,\"name\":\"Thank U Next\",\"duration\":207,\"lyrics\":\"Thank U Next lyrics\"},{\"id\":2,\"name\":\"needy\",\"duration\":212,\"lyrics\":\"needy lyrics\"},{\"id\":3,\"name\":\"24K Magic\",\"duration\":240,\"lyrics\":\"24K Magic Lyrics\"},{\"id\":4,\"name\":\"Hello\",\"duration\":220,\"lyrics\":\"Hello lyrics\"},{\"id\":5,\"name\":\"What Do You Mean\",\"duration\":210,\"lyrics\":\"What Do You Mean lyrics\"}]}";
	private final PlaylistDTO playlistAsDto=this.mapToDTO(testPlaylist);
	private final List<PlaylistDTO> playlists=List.of(playlistAsDto);
	
	@Test
	void testCreate() throws Exception{
		PlaylistDTO toCreateDto=this.mapToDTO(new Playlist("Test Playlist","Test Description","Test Artwork",testUser));
		PlaylistDTO expectedDto=this.mapToDTO(new Playlist(2L,"Test Playlist","Test Description","Test Artwork",testUser));
		String toCreateJson=this.jsonify.writeValueAsString(toCreateDto);
		String expectedJson=this.jsonify.writeValueAsString(expectedDto);
		
		RequestBuilder request=post(URI+"/create").contentType(MediaType.APPLICATION_JSON).content(toCreateJson);
	
		ResultMatcher confirmStatus=status().isCreated();
		ResultMatcher confirmBody=content().json(expectedJson);
		this.mvc.perform(request).andExpect(confirmStatus).andExpect(confirmBody);
	}
	
	@Test
	void testReadAll() throws Exception{
		String playlistsJson=this.jsonify.writeValueAsString(playlists);
		String expectedString=playlistsJson.substring(0,playlistsJson.length()-6);
		expectedString+=tracksAsString+"]";
		System.out.println("The expected string is "+expectedString);
		RequestBuilder request=get(URI+"/read");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(expectedString);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testReadById() throws Exception{
		String playlistJson=this.jsonify.writeValueAsString(playlistAsDto);
		System.out.println(playlistJson);
		String expectedString=playlistJson.substring(0,playlistJson.length()-5);
		expectedString+=tracksAsString;
		System.out.println("The expected String is :"+expectedString);
		RequestBuilder request=get(URI+"/read/1");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(expectedString);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testUpdate() throws Exception{
		PlaylistDTO toUpdate=this.mapToDTO(new Playlist("Update","Description update","Artwork Update",testUser));
		PlaylistDTO expected=this.mapToDTO(new Playlist(1L,"Update","Description update","Artwork Update",testUser));
		String toUpdateJson=this.jsonify.writeValueAsString(toUpdate);
		String expectedJson=this.jsonify.writeValueAsString(expected);
		String expectedString=expectedJson.substring(0,expectedJson.length()-5);
		expectedString+=tracksAsString;
		RequestBuilder request=put(URI+"/update/1").contentType(MediaType.APPLICATION_JSON).content(toUpdateJson);
		ResultMatcher confirmStatus=status().isAccepted();
		ResultMatcher confirmBody=content().json(expectedString);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testDeletePass() throws Exception{
		RequestBuilder request=delete(URI+"/delete/1");
		ResultMatcher confirmStatus=status().isNoContent();
		ResultMatcher confirmBody=content().string("");
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
}
