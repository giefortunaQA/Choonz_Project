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

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.service.GenreService;

@SpringBootTest
public class GenreControllerUnitTest {

	@Autowired
	private GenreController controller;

	@MockBean
	private GenreService service;

	@Autowired
	private ModelMapper mapper;

	private final Genre testGenre = new Genre(2L, "Pop", "Popular music, any commercially oriented music principally intended to be received and appreciated by a wide audience, generally in literate, technologically advanced societies dominated by urban culture.");
	private Genre updateGenre = new Genre(2L, "Test", "Sample");
	private final List<Genre> listOfGenres = List.of(testGenre);

	private GenreDTO mapToDTO(Genre Genre) {
		return this.mapper.map(Genre, GenreDTO.class);
	}

	@Test
	public void createGenreTest() {

		// ACTIONS
		Mockito.when(this.service.create(testGenre)).thenReturn(this.mapToDTO(testGenre));

		// ASSERTIONS

		assertThat(new ResponseEntity<GenreDTO>(this.mapToDTO(testGenre), HttpStatus.CREATED))
		.isEqualTo(this.controller.create(testGenre));

		verify(this.service, atLeastOnce()).create(testGenre);

	}

	@Test
	public void readAllGenresTest() {

		// RESOURCES
		List<GenreDTO> testReadList = listOfGenres.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.service.read()).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<GenreDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<GenreDTO>> result = this.controller.read();
		assertEquals(expected, result);

	}

	@Test
	public void readGenreTest() {

		// RESOURCES
		Long testID = 2L;
		GenreDTO testReadGenre = this.mapToDTO(testGenre);

		// ACTIONS
		when(this.service.read(testID)).thenReturn(testReadGenre);

		// ASSERTIONS

		ResponseEntity<GenreDTO> expected = ResponseEntity.ok(testReadGenre);
		ResponseEntity<GenreDTO> result = this.controller.read(testID);
		assertEquals(expected, result);
		verify(this.service, atLeastOnce()).read(testID);

	}

	@Test
	public void updateGenreTest() {
		// RESOURCES
		Long testID = 2L;

		// ACTIONS
		when(this.service.update(testGenre, testID)).thenReturn(mapToDTO(updateGenre));

		//ASSERTIONS
		ResponseEntity<GenreDTO> expected = new ResponseEntity<GenreDTO>(mapToDTO(updateGenre), HttpStatus.ACCEPTED);
		ResponseEntity<GenreDTO> result = this.controller.update(testGenre, testID);
		assertEquals(expected, result);
		verify(this.service, atLeastOnce()).update(testGenre,testID);
	}

	@Test
	public void deleteGenreTest() {
		// RESOURCES
		Long testID = 2L;

		// ACTIONS
		when(this.service.delete(testID)).thenReturn(true);

		// ASSERTIONS

		ResponseEntity<GenreDTO> expected = new ResponseEntity<GenreDTO>(HttpStatus.NO_CONTENT);
		ResponseEntity<GenreDTO> result = this.controller.delete(testID);
		assertEquals(expected, result);
		verify(this.service, atLeastOnce()).delete(testID);
	}

	@Test
	public void deleteGenreIdFailureTest() {
		// RESOURCES
		Long testID = 999L;

		// ACTIONS
		when(this.service.delete(testID)).thenReturn(false);

		// ASSERTIONS

		ResponseEntity<GenreDTO> expected = new ResponseEntity<GenreDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<GenreDTO> result = this.controller.delete(testID);
		assertEquals(expected, result);
		verify(this.service, atLeastOnce()).delete(testID);
	}

}
