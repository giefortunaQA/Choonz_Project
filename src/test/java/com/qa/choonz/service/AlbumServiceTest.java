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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.AlbumDTO;

public class AlbumServiceTest {
	@Autowired
	private AlbumService service;
	
	@MockBean
	private AlbumRepository repo;
	
	private ModelMapper mapper=new ModelMapper();
	
	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album,AlbumDTO.class);
	}
	//class resources
	private final Album testAlbum1=new Album("Album 1",testArtist,testGenre,"cover 1");
	private final Album testAlbum2=new Album("Album 2",testArtist,testGenre,"cover 2");
	private final Genre testGenre=new Genre(1L,"Genre","description");
	private final Artist testArtist=new Artist(1L,"Artist");
	private final List<Album> testList=List.of(testAlbum1,testAlbum2); 
	
	@Test
	void testCreate() throws Exception{
		Album toCreate=new Album("Album","cover",testArtist,testGenre);
		Album created=new Album(5L,"Album",testArtist,testGenre);
		
		when(this.repo.save(toCreate)).thenReturn(created);
		
		assertThat(this.service.create(toCreate)).isEqualTo(this.mapToDTO(created));
		verify(this.repo,times(1)).save(toCreate);
	}
	
	@Test
	void testReadAll() throws Exception{
		List<AlbumDTO> testListAsDtos=testList.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(testList);
		assertThat(this.service.read()).isEqualTo(testListAsDtos);
	}
	
	@Test
	void testReadById() throws Exception{
		Long id=1L;
		when(this.repo.findById(id)).thenReturn(Optional.of(testAlbum1));
		assertThat(this.service.read(id)).isEqualTo(this.mapToDTO(testAlbum1));
		verify(this.repo,times(1)).findById(id);
	}
	
	@Test
	void testUpdate() throws Exception{
		Long id=1L;
		Album updated=new Album(1L,"Album 1 Updated",testArtist,testGenre,"cover updated");
		AlbumDTO updatedAsDto=this.mapToDTO(updated);
		when(this.repo.findById(id)).thenReturn(Optional.of(testAlbum1));
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
	
	@Test
	void testReadAlbumsByArtists() throws Exception{
		Long id=1L;
		List<Album> albumsByArtist=List.of(testAlbum1,testAlbum2);
		List<AlbumDTO> albumsByArtistAsDtos=albumsByArtist.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAlbumsInArtist(id)).thenReturn(albumsByArtist);
		assertThat(this.service.findAlbumsInArtist(id)).isEqualTo(albumsByArtistAsDtos);
		verify(this.repo,times(1)).findAlbumsInArtist(id);
	}
	
	@Test
	void testReadAlbumsByGenres() throws Exception{
		Long id=1L;
		List<Album> albumsByGenre=List.of(testAlbum1,testAlbum2);
		List<AlbumDTO> albumsByGenreAsDtos=albumsByGenre.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAlbumsInGenres(id)).thenReturn(albumsByGenre);
		assertThat(this.service.findAlbumsInGenres(id)).isEqualTo(albumsByGenreAsDtos);
		verify(this.repo,times(1)).findAlbumsInGenres(id);
	}
}
