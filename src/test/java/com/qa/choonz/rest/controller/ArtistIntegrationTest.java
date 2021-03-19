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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts= {"classpath:Choonz-schema.sql","classpath:test-data.sql"},executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
public class ArtistIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper jsonify;
	private ModelMapper mapper=new ModelMapper();
	
	private ArtistDTO mapToDTO(Artist artist) {
		return this.mapper.map(artist, ArtistDTO.class);
	}
	
	private String URI="/artists";
	private final ArtistDTO ArianaAsDto=this.mapToDTO(new Artist(1L,"Ariana Grande"));
	private final ArtistDTO BieberAsDto=this.mapToDTO(new Artist(2L,"Justin Bieber"));
	private final ArtistDTO AdeleAsDto=this.mapToDTO(new Artist(3L,"Adele"));
	private final ArtistDTO BrunoAsDto=this.mapToDTO(new Artist(4L,"Bruno Mars"));
	private final List<ArtistDTO> artists=List.of(ArianaAsDto,BieberAsDto,AdeleAsDto,BrunoAsDto);
	
	@Test
	void testCreate() throws Exception{
		ArtistDTO toCreateDto=this.mapToDTO(new Artist("Test Artist"));
		ArtistDTO expectedDto=this.mapToDTO(new Artist(5L,"Test Artist"));
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
		String ArianaJson=this.jsonify.writeValueAsString(ArianaAsDto);
		RequestBuilder request=get(URI+"/read/1");
		ResultMatcher confirmStatus=status().isOk();
		ResultMatcher confirmBody=content().json(ArianaJson);
		this.mvc.perform(request).andExpect(confirmBody).andExpect(confirmStatus);
	}
	
	@Test
	void testUpdate() throws Exception{
		ArtistDTO toUpdate=this.mapToDTO(new Artist("Ariana Update"));
		ArtistDTO expected=this.mapToDTO(new Artist(1L,"Ariana Update"));
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
	
//	@Test
//	void testDeleteFail() throws Exception{
//		RequestBuilder request=delete(URI+"/delete/1000");
//		ResultMatcher confirmStatus=status().isInternalServerError();
//		this.mvc.perform(request).andExpect(confirmStatus);
//	}
}
