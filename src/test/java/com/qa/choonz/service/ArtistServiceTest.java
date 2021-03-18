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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;


@SpringBootTest
@ActiveProfiles	("test")
public class ArtistServiceTest {

	@Autowired
	private ArtistService service;
	
	@MockBean
	private ArtistRepository repo;
	
	private ModelMapper mapper=new ModelMapper();
	
	private ArtistDTO mapToDTO(Artist artist) {
		return this.mapper.map(artist,ArtistDTO.class);
	}
	//class resources
	private final Artist testArtist1=new Artist("Artist 1");
	private final Artist testArtist2=new Artist("Artist 2");
	private final List<Artist> testList=List.of(testArtist1,testArtist2); 
	
	@Test
	void testCreate() throws Exception{
		Artist toCreate=new Artist("Artist");
		Artist created=new Artist(5L,"Artist");
		
		when(this.repo.save(toCreate)).thenReturn(created);
		
		assertThat(this.service.create(toCreate)).isEqualTo(this.mapToDTO(created));
		verify(this.repo,times(1)).save(toCreate);
	}
	
	@Test
	void testReadAll() throws Exception{
		List<ArtistDTO> testListAsDtos=testList.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(testList);
		assertThat(this.service.read()).isEqualTo(testListAsDtos);
	}
	
	@Test
	void testReadById() throws Exception{
		Long id=1L;
		when(this.repo.findById(id)).thenReturn(Optional.of(testArtist1));
		assertThat(this.service.read(id)).isEqualTo(this.mapToDTO(testArtist1));
		verify(this.repo,times(1)).findById(id);
	}
	
	@Test
	void testUpdate() throws Exception{
		Long id=1L;
		Artist toUpdate=new Artist(1L,"Artist");
		Artist updated=new Artist(1L,"Artist Updated");
		ArtistDTO updatedAsDto=this.mapToDTO(updated);
		when(this.repo.findById(id)).thenReturn(Optional.of(toUpdate));
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
	void testDeletePass() throws Exception{
		Long id=1L;
		when(this.repo.existsById(id)).thenReturn(false);
		assertThat(this.service.delete(id)).isTrue();
		verify(this.repo,times(1)).existsById(id);
	}
}
