package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;

public class PlaylistServiceTest {
	@Autowired
	private PlaylistService service;
	
	@MockBean
	private PlaylistRepository repo;
	
	private ModelMapper mapper=new ModelMapper();
	
	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist,PlaylistDTO.class);
	}
	//class resources
	private final Playlist testPlaylist1=new Playlist("Playlist 1","description 1","artwork 1",testTracks1,testUser);
	private final Playlist testPlaylist2=new Playlist("Playlist 2","description 2","artwork 2",testTracks2,testUser);
	private final User testUser=new User("user","pass");
	private final List<Playlist> testList=List.of(testPlaylist1,testPlaylist2); 
	private final Track testTrack1=new Track("Track 1",testAlbum,testPlaylist1,200,"lyrics 1");
	private final Track testTrack2=new Track("Track 2",testAlbum,testPlaylist2,200,"lyrics 2");
	private final List<Track> testTracks1=List.of(testTrack1); 
	private final List<Track> testTracks2=List.of(testTrack2); 
	
	@Test
	void testCreate() throws Exception{
		Playlist toCreate=new Playlist("Playlist","desc","art",testTracks1,testUser);
		Playlist created=new Playlist(5L,"Playlist","desc","art",testTracks1,testUser);
		
		when(this.repo.save(toCreate)).thenReturn(created);
		
		assertThat(this.service.create(toCreate)).isEqualTo(this.mapToDTO(created));
		verify(this.repo,times(1)).save(toCreate);
	}
	
	@Test
	void testReadAll() throws Exception{
		List<PlaylistDTO> testListAsDtos=testList.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(testList);
		assertThat(this.service.read()).isEqualTo(testListAsDtos);
	}
	
	@Test
	void testReadById() throws Exception{
		Long id=1L;
		when(this.repo.findById(id)).thenReturn(Optional.of(testPlaylist1));
		assertThat(this.service.read(id)).isEqualTo(this.mapToDTO(testPlaylist1));
		verify(this.repo,times(1)).findById(id);
	}
	
	@Test
	void testUpdate() throws Exception{
		Long id=1L;
		Playlist updated=new Playlist(1L,"Playlist","desc","art",testTracks1,testUser);
		PlaylistDTO updatedAsDto=this.mapToDTO(updated);
		when(this.repo.findById(id)).thenReturn(Optional.of(testPlaylist1));
		when(this.repo.save(updated)).thenReturn(updated);
		assertThat(this.service.update(updated, id)).isEqualTo(updatedAsDto);
		verify(this.repo,times(1)).findById(id);
		verify(this.repo,times(1)).save(updated);
	}
	
	@Test
	void testDeleteFail() throws Exception{
		Long id=1L;
		when(this.repo.existsById(id)).thenReturn(true);
		assertThat(this.service.delete(id)).isFalse();
		verify(this.repo,times(1)).existsById(id);
	}
	
}
