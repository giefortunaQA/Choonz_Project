package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@SpringBootTest
public class AlbumControllerUnitTest {

	@Autowired
	private AlbumController controller;

	@MockBean
	private AlbumService service;

	@Autowired
	private ModelMapper mapper;

	@MockBean
	private Artist artist;

	@MockBean
	private Genre genre;

	//@MockBean
	private final Album testAlbum = new Album(1L, "Album Name", artist, genre, "cover");

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	private final List<Album> listOfAlbums = List.of(testAlbum);

	// Create
	@Test
	public void createAlbumTest() {
		// RESOURCES
		// ACTIONS
		Mockito.when(this.service.create(testAlbum)).thenReturn(this.mapToDTO(testAlbum));

		//ASSERTIONS
		assertThat(new ResponseEntity<AlbumDTO>(this.mapToDTO(testAlbum), HttpStatus.CREATED))
		.isNotNull();
		assertThat(new ResponseEntity<AlbumDTO>(this.mapToDTO(testAlbum), HttpStatus.CREATED))
		.isEqualTo(this.controller.create(testAlbum));
		verify(this.service, atLeastOnce()).create(testAlbum);
	}
	// Read All
	@Test
	public void readAllAlbumTest() {

		// RESOURCES
		List<AlbumDTO> testReadList = listOfAlbums.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.service.read()).thenReturn(testReadList);

		// ASSERTIONS
		ResponseEntity<List<AlbumDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<AlbumDTO>> result = this.controller.read();
		assertNotNull(expected);
		assertEquals(expected, result);
		verify(this.service, atLeastOnce()).read();
	}
	// Read by ID
	@Test
	public void readAlbumByIDTest() {

		// RESOURCES
		Long testID = 1L;
		AlbumDTO testReadAlbum = this.mapToDTO(testAlbum);

		// ACTIONS
		when(this.service.read(testID)).thenReturn(testReadAlbum);

		// ASSERTIONS

		ResponseEntity<AlbumDTO> expected = ResponseEntity.ok(testReadAlbum);
		ResponseEntity<AlbumDTO> result = this.controller.read(testID);
		assertNotNull(result);
		assertEquals(expected, result);
		verify(this.service, atLeastOnce()).read(testID);

	}
	// Update by ID
	@Test
	public void updateAlbumTest() throws Exception {
		Long testID = 1L;

		Album newAlbum = new Album(1L, "Album Name", null, null, "cover");
		when(this.service.update(newAlbum, testID)).thenReturn(mapToDTO(testAlbum));
		ResponseEntity <AlbumDTO> expected = new ResponseEntity<AlbumDTO>(mapToDTO(testAlbum), HttpStatus.ACCEPTED);
		ResponseEntity <AlbumDTO> result = this.controller.update(newAlbum, testID);
		assertNotNull(result);
		assertEquals(expected,result);
		verify(this.service, atLeastOnce()).update(newAlbum, testID);
	}
	// Delete by ID
	@Test
	public void deleteAlbumTest() throws Exception{
		// RESOURCES
		Long testID = 1L;
		// ACTIONS
		when(this.service.delete(testID)).thenReturn(true);
		
		// ASSERTIONS
		ResponseEntity <AlbumDTO> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		ResponseEntity <AlbumDTO> result = this.controller.delete(testID);
		assertNotNull(result);
		assertEquals(expected,result);
		verify(this.service, atLeastOnce()).delete(testID);
	}

	@Test
	public void deleteAlbumErrorTest() throws Exception{
		// RESOURCES
		Long testID = 999L;
		// ACTIONS
		when(this.service.delete(999)).thenReturn(false);

		// ASSERTIONS
		ResponseEntity <AlbumDTO> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity <AlbumDTO> result = this.controller.delete(testID);
		assertNotNull(result);
		assertEquals(expected,result);
		verify(this.service, atLeastOnce()).delete(testID);
	}
		
		// Read by artist's ID
		@Test
		public void readAlbumByArtistTest() {
			// RESOURCES
			List<AlbumDTO> testReadList = listOfAlbums.stream().map(this::mapToDTO).collect(Collectors.toList());
			Long testID = 1L;
			// ACTIONS
			when(this.service.findAlbumsInArtist(testID)).thenReturn(testReadList);

			// ASSERTIONS

			ResponseEntity<List<AlbumDTO>> expected = ResponseEntity.ok(testReadList);
			ResponseEntity<List<AlbumDTO>> result = this.controller.readAlbumsByArtists(testID);
			assertNotNull(result);
			assertEquals(expected, result);
		}

		// Read by genre's ID
		@Test
		public void readAlbumByGenreTest() {
			// RESOURCES
			Long testID = 1L;
			List<AlbumDTO> testReadList = listOfAlbums.stream().map(this::mapToDTO).collect(Collectors.toList());

			// ACTIONS
			when(this.service.findAlbumsInGenres(testID)).thenReturn(testReadList);

			// ASSERTIONS
			ResponseEntity<List<AlbumDTO>> expected = ResponseEntity.ok(testReadList);
			ResponseEntity<List<AlbumDTO>> result = this.controller.readAlbumsByGenres(testID);
			assertNotNull(result);
			assertEquals(expected, result);
		}
	}
