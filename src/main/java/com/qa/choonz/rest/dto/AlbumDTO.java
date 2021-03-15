package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {

	private long id;
	private String name;
	private Artist artist;
	private Genre genre;
	private String cover;

}
