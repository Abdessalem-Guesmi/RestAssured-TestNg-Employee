package com.restAssuredTestng.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String generateEmpName() {
		return "micheal" + RandomStringUtils.randomAlphabetic(3);
	}

	public static String generateEmpSalary() {
		return RandomStringUtils.randomNumeric(5);
	}

	public static String generateEmpAge() {
		return RandomStringUtils.randomNumeric(2);
	}

}
