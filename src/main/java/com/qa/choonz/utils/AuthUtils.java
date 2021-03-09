package com.qa.choonz.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AuthUtils {

	// variables
	private static Map<String, Long> userTokens;
	private static int tokenSize = 10;

	// methods

	private static String genToken() {
		int lLimit = 48; // 48 = 0
		int uLimit = 122; // 122 = z
		Random generated = new Random();
		// credit: https://www.baeldung.com/java-random-string
		return generated.ints(lLimit, uLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(tokenSize).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}

	public static void start() {
		userTokens = new HashMap<>();
	}

	public static String createUserToken(Long userId) {
		String newToken = genToken();
		userTokens.put(newToken, userId);
		return newToken;
	}

	public static void deleteUserToken(String token) {
		if (userTokens.containsKey(token)) {
			userTokens.remove(token);
		}
	}

	public static Long getToken(String token) {
		if (userTokens.containsKey(token)) {
			return userTokens.get(token);
		} else {
			return null;
		}
	}

}
