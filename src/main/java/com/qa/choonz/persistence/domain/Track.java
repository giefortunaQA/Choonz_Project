package com.qa.choonz.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Playlist playlist;

    // in seconds
    private int duration;
    
    @Size(max = 5000)
    private String lyrics;

	public Track(@NotNull @Size(max = 100) String name, Album album, Playlist playlist, int duration,
			@Size(max = 5000) String lyrics) {
		super();
		this.name = name;
		this.album = album;
		this.playlist = playlist;
		this.duration = duration;
		this.lyrics = lyrics;
	}
    


public Track(Long id, @NotNull @Size(max = 100) String name, Album album, Playlist playlist, int duration,
		@Size(max = 5000) String lyrics) {
	super();
	this.id = id;
	this.name = name;
	this.album = album;
	this.playlist = playlist;
	this.duration = duration;
	this.lyrics = lyrics;
}}
