package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@NotNull
	@Size(max = 500)
	@Column(unique = true)
	private String description;

	@NotNull
	@Size(max = 1000)
	@Column(unique = true)
	private String artwork;

	@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
	private List<Track> tracks;
	
	// https://www.logicbig.com/tutorials/misc/jackson/json-managed-reference.html
	@JsonBackReference(value="ownedBy")
	@ManyToOne
	@NotNull
    private User user;

	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
//			@NotNull @Size(max = 1000) String artwork) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.artwork = artwork;
//	}
	
	

	public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
			@NotNull @Size(max = 1000) String artwork, List<Track> tracks, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.artwork = artwork;
		this.tracks = tracks;
		this.user = user;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArtwork() {
		return artwork;
	}

	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Playlist [id=").append(id).append(", name=").append(name).append(", description=")
				.append(description).append(", artwork=").append(artwork).append(user).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artwork == null) ? 0 : artwork.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		if (artwork == null) {
			if (other.artwork != null)
				return false;
		} else if (!artwork.equals(other.artwork))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	

}
