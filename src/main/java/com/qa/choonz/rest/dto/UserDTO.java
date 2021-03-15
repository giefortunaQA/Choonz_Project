package com.qa.choonz.rest.dto;

import java.util.List;

import com.qa.choonz.persistence.domain.Playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private List<Playlist> playlists;

}
