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

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;

@SpringBootTest
public class ArtistControllerUnitTest {
	
	@Autowired
	private ArtistController controller;

	@MockBean
	private ArtistService ArtistService;

	@Autowired
	private ModelMapper mapper;
	
    private final Artist testArtist = new Artist(2L, "Ariana Grande");
	private Artist updateArtist = new Artist(2L, "Test");
	private final List<Artist> listOfArtists = List.of(testArtist);

	private ArtistDTO mapToDTO(Artist artist) {
		return this.mapper.map(artist, ArtistDTO.class);
	}
	
	@Test
	public void createArtistTest() {
		
		// ACTIONS
		Mockito.when(this.ArtistService.create(testArtist)).thenReturn(this.mapToDTO(testArtist));

		// ASSERTIONS

		assertThat(new ResponseEntity<ArtistDTO>(this.mapToDTO(testArtist), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testArtist));

		verify(this.ArtistService, atLeastOnce()).create(testArtist);

	}

	@Test
	public void readAllArtistsTest() {

		// RESOURCES
		List<ArtistDTO> testReadList = listOfArtists.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.ArtistService.read()).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<ArtistDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<ArtistDTO>> result = this.controller.read();
		assertEquals(expected, result);

	}

	@Test
	public void readArtistTest() {

		// RESOURCES
		Long testID = 2L;
		ArtistDTO testReadArtist = this.mapToDTO(testArtist);

		// ACTIONS
		when(this.ArtistService.read(testID)).thenReturn(testReadArtist);

		// ASSERTIONS

		ResponseEntity<ArtistDTO> expected = ResponseEntity.ok(testReadArtist);
		ResponseEntity<ArtistDTO> result = this.controller.read(testID);
		assertEquals(expected, result);
		verify(this.ArtistService, atLeastOnce()).read(testID);

	}
	
	@Test
	public void updateArtistTest() {
		// RESOURCES
		Long testID = 2L;
		
		// ACTIONS
		when(this.ArtistService.update(testArtist, testID)).thenReturn(mapToDTO(updateArtist));
		
		//ASSERTIONS
		ResponseEntity<ArtistDTO> expected = new ResponseEntity<ArtistDTO>(mapToDTO(updateArtist), HttpStatus.ACCEPTED);
		ResponseEntity<ArtistDTO> result = this.controller.update(testArtist, testID);
		assertEquals(expected, result);
		verify(this.ArtistService, atLeastOnce()).update(testArtist,testID);
	}
	
	@Test
	public void deleteArtistTest() {
		// RESOURCES
		Long testID = 2L;

		// ACTIONS
		when(this.ArtistService.delete(testID)).thenReturn(true);

		// ASSERTIONS

		ResponseEntity<ArtistDTO> expected = new ResponseEntity<ArtistDTO>(HttpStatus.NO_CONTENT);
		ResponseEntity<ArtistDTO> result = this.controller.delete(testID);
		assertEquals(expected, result);
		verify(this.ArtistService, atLeastOnce()).delete(testID);
	}
	
	@Test
	public void deleteArtistIdFailureTest() {
		// RESOURCES
		Long testID = 999L;

		// ACTIONS
		when(this.ArtistService.delete(testID)).thenReturn(false);

		// ASSERTIONS

		ResponseEntity<ArtistDTO> expected = new ResponseEntity<ArtistDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<ArtistDTO> result = this.controller.delete(testID);
		assertEquals(expected, result);
		verify(this.ArtistService, atLeastOnce()).delete(testID);
	}
}
