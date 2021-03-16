package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AlbumUnitTest {

	Album testAlbum;
	
	@Test
	public void createAlbumTest() {
		testAlbum = new Album(1L, "Thank U, Next", null, null, "cover");
		assertNotNull(testAlbum);
		assertTrue(testAlbum instanceof Album);
		assertEquals(new Album(1L, "Thank U, Next", null, null, "cover"), testAlbum);
	}
}
