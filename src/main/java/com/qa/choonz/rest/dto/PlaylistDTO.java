package com.qa.choonz.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {

	private Long id;
	private String name;
	private String description;
	private String artwork;
	private List<TrackDTO> tracks;
	
}
