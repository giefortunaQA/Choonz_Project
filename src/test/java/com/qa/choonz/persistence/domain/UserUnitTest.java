package com.qa.choonz.persistence.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserUnitTest {
	
	User testUser;
	
	@Test
	public void createUserTest() {
		testUser = new User("same", "Thank U, Next");
		assertNotNull(testUser);
		assertTrue(testUser instanceof User);
		assertEquals(new User("same", "Thank U, Next"), testUser);
	}

}
