package com.qa.choonz.rest.controller;

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
import com.qa.choonz.rest.dto.AlbumDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:Choonz-schema.sql",
		"classpath:Choonz-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	private ModelMapper mapper = new ModelMapper();
	
	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}
	
	
	private final String URI = "/taskList";
	 @Test
	    void createIntegrationTest() throws Exception {       
	        AlbumDTO testSavedDTO = mapToDTO(testList1); 
	        String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
	        
	        RequestBuilder request = post(URI + "/create")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testList1));
	        
	        ResultMatcher checkStatus = status().isCreated();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    void readAllIntegrationTest() throws Exception {
	        List<AlbumDTO> testSavedListDTO = List.of(mapToDTO(testList1), mapToDTO(testList2)); 
	        
	        String testSavedListAsJson = this.jsonifier.writeValueAsString(testSavedListDTO);
	        
	        RequestBuilder request = get(URI + "/read")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedListDTO));
	         
	        ResultMatcher checkStatus = status().isOk();
	        ResultMatcher checkBody = content().json(testSavedListAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    void readByIdIntegrationTest() throws Exception {

		 AlbumDTO testSavedDTO = mapToDTO(testList2);    
		 String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
	        
	        RequestBuilder request = get(URI + "/read/2")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isOk();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 @Test
	    void updateIntegrationTest() throws Exception {

		 AlbumDTO testSavedDTO = mapToDTO(testList1);    
		 String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
	        
	        RequestBuilder request = put(URI + "/update/1")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isAccepted();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    void deleteIntegrationTest() throws Exception {

		 AlbumDTO testSavedDTO = mapToDTO(testList1);    
		
	        RequestBuilder request = delete(URI + "/delete/1")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isNoContent();
	        
	        this.mvc.perform(request).andExpect(checkStatus);
	    }

}
