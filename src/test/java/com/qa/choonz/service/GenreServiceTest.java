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

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;

public class GenreServiceTest {
	@Autowired
	private GenreService service;
	
	@MockBean
	private GenreRepository repo;
	
	private ModelMapper mapper=new ModelMapper();
	
	private GenreDTO mapToDTO(Genre genre) {
		return this.mapper.map(genre,GenreDTO.class);
	}
	//class resources
	private final Genre testGenre1=new Genre("Genre 1","description 1");
	private final Genre testGenre2=new Genre("Genre 2","description 2");
	private final List<Genre> testList=List.of(testGenre1,testGenre2); 
	
	@Test
	void testCreate() throws Exception{
		Genre toCreate=new Genre("Genre");
		Genre created=new Genre(5L,"Genre");
		
		when(this.repo.save(toCreate)).thenReturn(created);
		
		assertThat(this.service.create(toCreate)).isEqualTo(this.mapToDTO(created));
		verify(this.repo,times(1)).save(toCreate);
	}
	
	@Test
	void testReadAll() throws Exception{
		List<GenreDTO> testListAsDtos=testList.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(testList);
		assertThat(this.service.read()).isEqualTo(testListAsDtos);
	}
	
	@Test
	void testReadById() throws Exception{
		Long id=1L;
		when(this.repo.findById(id)).thenReturn(Optional.of(testGenre1));
		assertThat(this.service.read(id)).isEqualTo(this.mapToDTO(testGenre1));
		verify(this.repo,times(1)).findById(id);
	}
	
	@Test
	void testUpdate() throws Exception{
		Long id=1L;
		Genre updated=new Genre(1L,"Genre 1 Updated");
		GenreDTO updatedAsDto=this.mapToDTO(updated);
		when(this.repo.findById(id)).thenReturn(Optional.of(testGenre1));
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
