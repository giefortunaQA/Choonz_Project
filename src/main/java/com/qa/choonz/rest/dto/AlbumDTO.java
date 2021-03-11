package com.qa.choonz.rest.dto;

import java.util.Objects;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

public class AlbumDTO {

	private long id;
	private String name;
	private Artist artist;
	private Genre genre;
	private String cover;

	public AlbumDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlbumDTO(long id, String name, Artist artist, Genre genre, String cover) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.cover = cover;
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

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlbumDTO [id=").append(id).append(", name=").append(name).append(", artist=").append(artist)
				.append(", genre=").append(genre).append(", cover=").append(cover).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(artist, cover, genre, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AlbumDTO)) {
			return false;
		}
		AlbumDTO other = (AlbumDTO) obj;
		return Objects.equals(artist, other.artist) && Objects.equals(cover, other.cover)
				&& Objects.equals(genre, other.genre) && id == other.id && Objects.equals(name, other.name);
	}

}
