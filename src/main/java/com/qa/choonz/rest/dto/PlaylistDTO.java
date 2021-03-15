package com.qa.choonz.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {

	private long id;
	private String name;
	private String description;
	private String artwork;

}
