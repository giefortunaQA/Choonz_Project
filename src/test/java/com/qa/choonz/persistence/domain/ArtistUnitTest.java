package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArtistUnitTest {
	
	Artist testArtist;
	
	@Test
	public void createArtistTest() {
		testArtist = new Artist(1L, "Sample");
		assertNotNull(testArtist);
		assertTrue(testArtist instanceof Artist);
		assertEquals(new Artist(1L, "Sample"), testArtist);
	}

}
