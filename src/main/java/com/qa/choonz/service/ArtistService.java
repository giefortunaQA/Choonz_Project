package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.ArtistNotFoundException;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistService {

	private final ArtistRepository repo;
	private final ModelMapper mapper;

	private ArtistDTO mapToDTO(Artist artist) {
		return this.mapper.map(artist, ArtistDTO.class);
	}

	public ArtistDTO create(Artist artist) {
		Artist created = this.repo.save(artist);
		return this.mapToDTO(created);
	}

	public List<ArtistDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ArtistDTO read(long id) {
		Artist found = this.repo.findById(id).orElseThrow(ArtistNotFoundException::new);
		return this.mapToDTO(found);
	}

	public ArtistDTO update(Artist artist, long id) {
		Artist toUpdate = this.repo.findById(id).orElseThrow(ArtistNotFoundException::new);
		BeanUtils.mergeNotNull(artist,toUpdate);
		Artist updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);
	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
