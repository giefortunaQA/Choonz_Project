package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Playlist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackDTO {

	private long id;
	private String name;
	//private Album album;
	private Playlist playlist;
	private int duration;
	private String lyrics;

}
