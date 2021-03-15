package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

	// variables

	private UserRepository repo;
	private ModelMapper mapper;

	// methods

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	public UserDTO create(User user) {
		User created = this.repo.save(user);
		return this.mapToDTO(created);
	}

	public List<UserDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public UserDTO read(long id) {
		User user = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		return this.mapToDTO(user);
	}
	
	public UserDTO read(String username) {
		User user = this.repo.findByUsername(username).orElseThrow(UserNotFoundException::new);
		return this.mapToDTO(user);
	}

	

	public UserDTO update(User user, long id) {
		User toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		toUpdate.setUsername(user.getUsername());
		toUpdate.setPassword(user.getPassword());
		User updatedUser = this.repo.save(toUpdate);
		return this.mapToDTO(updatedUser);
	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public Long login(String username, String password) {
		List<UserDTO> account = this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		for (UserDTO thisUser: account) {
			if (thisUser.getUsername().equals(username) && thisUser.getPassword().equals(password)) {
				return thisUser.getId();
			}
		}
		return null;
	}
}
