package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TrackUnitTest {
	
	Track testTrack;
	
	@Test
	public void createTrackTest() {
		testTrack = new Track(1L, "sample", 100, "sample");
		assertNotNull(testTrack);
		assertTrue(testTrack instanceof Track);
		assertEquals(new Track(1L,"sample", 100, "sample"), testTrack);
	}

}
