package com.qa.choonz.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {

	private Long id;
	private String name;
	private ArtistDTO artist;
	private GenreDTO genre;
	private String cover;
	private List<TrackDTO> tracks;
	
}
