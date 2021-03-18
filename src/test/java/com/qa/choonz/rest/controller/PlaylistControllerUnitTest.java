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
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;

@SpringBootTest
@ActiveProfiles("test")
public class PlaylistControllerUnitTest {
	@Autowired
	private PlaylistController controller;

	@MockBean
	private PlaylistService playlistService;

	@Autowired
	private ModelMapper mapper;

	private final Playlist testPlaylist = new Playlist(2L, "Workout Music", "A cool music playlist", "Playlist Artwork",
			null);
	private final List<Playlist> listOfPlaylists = List.of(testPlaylist);

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}

	@Test
	public void createPlaylistTest() {

		// ACTIONS
		Mockito.when(this.playlistService.create(testPlaylist)).thenReturn(this.mapToDTO(testPlaylist));

		// ASSERTIONS

		assertThat(new ResponseEntity<PlaylistDTO>(this.mapToDTO(testPlaylist), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testPlaylist));

		verify(this.playlistService, atLeastOnce()).create(testPlaylist);

	}

	@Test
	public void readAllPlaylistTest() {
		// RESOURCES
		List<PlaylistDTO> testReadList = listOfPlaylists.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.playlistService.read()).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<PlaylistDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<PlaylistDTO>> result = this.controller.read();
		assertEquals(expected, result);
		verify(this.playlistService, atLeastOnce()).read();
	}

	@Test
	public void readPlaylistByID() {
		// RESOURCES
		Long testID = 2L;
		PlaylistDTO testReadPlaylist = this.mapToDTO(testPlaylist);

		// ACTIONS
		when(this.playlistService.read(testID)).thenReturn(testReadPlaylist);

		// ASSERTIONS

		ResponseEntity<PlaylistDTO> expected = ResponseEntity.ok(testReadPlaylist);
		ResponseEntity<PlaylistDTO> result = this.controller.read(testID);
		assertEquals(expected, result);
		verify(this.playlistService, Mockito.times(1)).read(testID);

	}

	@Test
	public void updatePlaylistTest() {

		// RESOURCES
		Long testID = 2L;
	    Playlist newPlaylist = new Playlist();
	    
	     // ACTIONS
		 newPlaylist.setId(testID);
		 newPlaylist.setName("Updated Track Playlist");
		 newPlaylist.setDescription("Updated Description");
		 newPlaylist.setArtwork("New Artwork");
		 
		 PlaylistDTO testUpdatePlaylist = this.mapToDTO(newPlaylist);
		
		 when(this.playlistService.update(newPlaylist, testID)).thenReturn(testUpdatePlaylist);
		 
		 // ASSERTIONS
		 ResponseEntity <PlaylistDTO> expected = new ResponseEntity<PlaylistDTO>(testUpdatePlaylist, HttpStatus.ACCEPTED);
		 ResponseEntity <PlaylistDTO> result = this.controller.update(newPlaylist, testID);
		 
			assertEquals(expected,result);
		  verify(this.playlistService, atLeastOnce()).update(newPlaylist, testID);
	}

	
	@Test
	public void deletePlaylistTest()
	{
		// RESOURCES
				Long testID = 2L;
				
				// ACTIONS
				 when(this.playlistService.delete(testID)).thenReturn(true);
				 
				// ASSERTIONS
				 ResponseEntity <PlaylistDTO> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				 ResponseEntity <PlaylistDTO> result = this.controller.delete(testID);
				
				 
				 assertEquals(expected,result);
				  verify(this.playlistService, atLeastOnce()).delete(testID);
				  
				  
				     //Second Delete Branch \\
				  
				    // ACTIONS
					
					 when(this.playlistService.delete(testID)).thenReturn(false);
					 
					// ASSERTIONS
					 ResponseEntity <PlaylistDTO> expected2 = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					 ResponseEntity <PlaylistDTO> result2 = this.controller.delete(testID);
					
					 assertEquals(expected2,result2);
					  verify(this.playlistService, atLeastOnce()).delete(testID);
	}
	
	
	
	
	
	
	
	
}
