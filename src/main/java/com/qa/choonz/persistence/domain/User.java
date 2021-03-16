package com.qa.choonz.persistence.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 20)
	@Column(unique = true)
	private String username;
	
	@NotNull
	@Size(max = 20)
	private String password;
	
	// https://www.logicbig.com/tutorials/misc/jackson/json-managed-reference.html
	@JsonManagedReference(value="ownedBy")
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Playlist> playlists;
	
//	public User() {
//		this.playlists = new ArrayList<>();
//	};
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}


}
