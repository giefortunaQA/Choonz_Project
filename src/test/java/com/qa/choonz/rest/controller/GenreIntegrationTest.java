package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.GenreDTO;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Sql(scripts= {"classpath:Choonz-schema.sql","classpath:data.sql"},executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
public class GenreIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper jsonify;
	private ModelMapper mapper=new ModelMapper();
	
	private GenreDTO mapToDTO(Genre artist) {
		return this.mapper.map(artist, GenreDTO.class);
	}
	
	private String URI="/genres";
	private final GenreDTO PopAsDto=this.mapToDTO(new Genre(1L,"Pop","Popular music, any commercially oriented music principally intended to be received and appreciated by a wide audience, generally in literate, technologically advanced societies dominated by urban culture."));
	private final GenreDTO RockAsDto=this.mapToDTO(new Genre(2L,"Rock","Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the late 1940s and early 1950s."));
	private final GenreDTO CountryAsDto=this.mapToDTO(new Genre(3L,"Country","Country (also called country and western) is a genre of popular music that originated with blues, old-time music, and various types of American folk music."));
	private final GenreDTO ReggaeAsDto=this.mapToDTO(new Genre(4L,"Reggae","Reggae is a popular style of music that began in Jamaica and often has political and spiritual themes."));
	private final List<GenreDTO> artists=List.of(PopAsDto,RockAsDto,CountryAsDto,ReggaeAsDto);
	
	@Test
	void testCreate() throws Exception{
		GenreDTO toCreateDto=this.mapToDTO(new Genre("Test Genre","Test Description"));
		GenreDTO expectedDto=this.mapToDTO(new Genre(5L,"Test Genre","Test Description"));
		String toCreateJson=this.jsonify.writeValueAsString(toCreateDto);
		String expectedJson=this.jsonify.writeValueAsString(expectedDto);
		
		RequestBuilder request=post(URI+"/create").contentType(MediaType.APPLICATION_JSON).content(toCreateJson);
	
		ResultMatcher confirmStatus=status().isCreated();
		ResultMatcher confirmBody=content().json(expectedJson);
		this.mvc.perform(request).andExpect(confirmStatus).andExpect(confirmBody);
	}
	
	@Test
	void testReadAll() throws Exception{
		String artistsJson=this.jsonify.writeValueAsString(artists);
		RequestBuilder request=get(URI+"/read");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(artistsJson);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testReadById() throws Exception{
		String ArianaJson=this.jsonify.writeValueAsString(PopAsDto);
		RequestBuilder request=get(URI+"/read/1");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(ArianaJson);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testUpdate() throws Exception{
		GenreDTO toUpdate=this.mapToDTO(new Genre("Pop Update","Description update"));
		GenreDTO expected=this.mapToDTO(new Genre(1L,"Pop Update","Description update"));
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
