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

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;


@SpringBootTest
@ActiveProfiles	("test")
public class TrackControllerTest {
	
	@Autowired
	private TrackController controller;

	@MockBean
	private TrackService trackService;

	@Autowired
	private ModelMapper mapper;

	private final Track testTrack = new Track(2L, "Track Name", 600L, "Lyrics");
	private final List<Track> listOfTracks = List.of(testTrack);

	private TrackDTO mapToDTO(Track track) {
		return this.mapper.map(track, TrackDTO.class);
	}

	@Test
	public void createTrackTest() {
		
		// ACTIONS
		Mockito.when(this.trackService.create(testTrack)).thenReturn(this.mapToDTO(testTrack));

		// ASSERTIONS

		assertThat(new ResponseEntity<TrackDTO>(this.mapToDTO(testTrack), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testTrack));

		verify(this.trackService, atLeastOnce()).create(testTrack);

	}

	@Test
	public void readAllTracksTest() {

		// RESOURCES
		List<TrackDTO> testReadList = listOfTracks.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.trackService.read()).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<TrackDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<TrackDTO>> result = this.controller.read();
		assertEquals(expected, result);

	}

	@Test
	public void readTrackByIDTest() {

		// RESOURCES
		Long testID = 2L;
		TrackDTO testReadTrack = this.mapToDTO(testTrack);

		// ACTIONS
		when(this.trackService.read(testID)).thenReturn(testReadTrack);

		// ASSERTIONS

		ResponseEntity<TrackDTO> expected = ResponseEntity.ok(testReadTrack);
		ResponseEntity<TrackDTO> result = this.controller.read(testID);
		assertEquals(expected, result);
		verify(this.trackService, atLeastOnce()).read(testID);

	}

	@Test
	public void readTrackByAlbumTest() {

		// RESOURCES
		
		List<TrackDTO> testReadList = listOfTracks.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.trackService.findTracksInAlbums(2L)).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<TrackDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<TrackDTO>> result = this.controller.readTracksByAlbums(2L);
		assertEquals(expected, result);

	

}
	
	

	@Test
	public void readTrackByPlaylistTest() {

		// RESOURCES
		
		List<TrackDTO> testReadList = listOfTracks.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.trackService.findTracksInPLaylists(2L)).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<TrackDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<TrackDTO>> result = this.controller.readTracksByPlaylists(2L);
		assertEquals(expected, result);

	

}
	
	@Test
	public void readTrackByArtistTest() {

		// RESOURCES
		
		List<TrackDTO> testReadList = listOfTracks.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.trackService.findTracksInArtists(2L)).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<TrackDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<TrackDTO>> result = this.controller.readTracksByArtists(2L);
		assertEquals(expected, result);

	

}
	
	@Test
	public void readTrackByGenreTest() {

		// RESOURCES
		
		List<TrackDTO> testReadList = listOfTracks.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ACTIONS
		when(this.trackService.findTracksInGenres(2L)).thenReturn(testReadList);

		// ASSERTIONS

		ResponseEntity<List<TrackDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<TrackDTO>> result = this.controller.readTracksByGenres(2L);
		assertEquals(expected, result);

	

}
	
	@Test
	public void updateTrackTest() throws Exception
    {
		
		// RESOURCES
		Long testID = 2L;
	    Track newTrack = new Track();
	    
	     // ACTIONS
		 newTrack.setId(testID);
		 newTrack.setName("Updated Track name");
		 newTrack.setDuration(4000L);
		 newTrack.setLyrics("cool lyrics");
		 TrackDTO testUpdateTrack = this.mapToDTO(newTrack);
		
		 when(this.trackService.update(newTrack, testID)).thenReturn(testUpdateTrack);
		 
		 // ASSERTIONS
		 ResponseEntity <TrackDTO> expected = new ResponseEntity<TrackDTO>(testUpdateTrack, HttpStatus.ACCEPTED);
		 ResponseEntity <TrackDTO> result = this.controller.update(newTrack, testID);
	
			assertEquals(expected,result);
		  verify(this.trackService, atLeastOnce()).update(newTrack, testID);
    }
	
	
	@Test
	public void deleteTrackTest() throws Exception
    {  
		// RESOURCES
		Long testID = 2L;
		
		// ACTIONS
		 when(this.trackService.delete(testID)).thenReturn(true);
		 
		// ASSERTIONS
		 ResponseEntity <TrackDTO> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 ResponseEntity <TrackDTO> result = this.controller.delete(testID);
		
		 
		 assertEquals(expected,result);
		  verify(this.trackService, atLeastOnce()).delete(testID);
		  
		    // ACTIONS
			
			 when(this.trackService.delete(testID)).thenReturn(false);
			 
			// ASSERTIONS
			 ResponseEntity <TrackDTO> expected2 = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			 ResponseEntity <TrackDTO> result2 = this.controller.delete(testID);
			
			 assertEquals(expected2,result2);
			  verify(this.trackService, atLeastOnce()).delete(testID);
		
	}

	
}