package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumService {

	private final AlbumRepository repo;
	private final ModelMapper mapper;

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	public AlbumDTO create(Album album) {
		Album created = this.repo.save(album);
		return this.mapToDTO(created);
	}

	public List<AlbumDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public AlbumDTO read(long id) {
		Album found = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
		return this.mapToDTO(found);
	}

	public AlbumDTO update(Album album, long id) {
		Album toUpdate = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
		BeanUtils.mergeNotNull(album, toUpdate);
		Album updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);
	}

	public boolean delete(long id) {
		try {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<AlbumDTO> findAlbumsInArtist(long id){
		return this.repo.findAlbumsInArtist(id).stream().map(this::mapToDTO).collect(Collectors.toList());
		
	}
	
	public List<AlbumDTO> findAlbumsInGenres(long id){
		return this.repo.findAlbumsInGenres(id).stream().map(this::mapToDTO).collect(Collectors.toList());
		
	}

}
