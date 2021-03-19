package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AlbumUnitTest {
	

	Album testAlbum;
	
	@Test
	public void createAlbumTest() {
		Artist testArtist=new Artist();
		Genre testGenre=new Genre();
		testAlbum = (new Album("Thank U, Next", testArtist, testGenre, "cover"));
		testAlbum.setId(1L);
		assertNotNull(testAlbum);
		assertTrue(testAlbum instanceof Album);
	}
}
