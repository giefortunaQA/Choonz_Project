package com.qa.choonz.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDTO {

	private Long id;
	private String name;
	private Long duration;
	private String lyrics;

}
