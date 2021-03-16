package com.qa.choonz.persistence.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 100)
	//@Column(unique = true)
	private String name;

	@NotNull
	@Size(max = 500)
	//@Column(unique = true)
	private String description;

	@NotNull
	@Size(max = 1000)
	//@Column(unique = true)
	private String artwork;

	@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
	private List<Track> tracks;
	
	// https://www.logicbig.com/tutorials/misc/jackson/json-managed-reference.html
	@JsonBackReference(value="ownedBy")
	@ManyToOne
    private User user;

}
