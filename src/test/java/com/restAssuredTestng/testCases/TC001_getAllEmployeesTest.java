package com.restAssuredTestng.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredTestng.utilities.BaseTest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class TC001_getAllEmployeesTest extends BaseTest {

	@BeforeClass
	public void getAllEmployees() {
		response = requestHttp.request(Method.GET, "/employees");
		System.out.println(response.getContentType());
		System.out.println(response.getTime());
		System.out.println(response.getStatusLine());
		Headers headers = response.headers();
		for (Header header : headers) {
			System.out.println(header.toString());
		}

	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);

	}

	@Test
	public void checkTime() {
		long time = response.getTime();
		if (time < 5000)
			assertTrue(true);
		else
			assertTrue(false);

	}

	@Test
	public void checkStatusLine() {
		String statusLine = response.getStatusLine();
		assertEquals("HTTP/1.1 200 OK", statusLine);

	}

	@Test
	public void checkContentType() {
		String contentType = response.getContentType();
		// assertEquals("", statusLine);

	}

	@Test
	public void checkServer() {
		String server = response.header("Server");
		System.out.println(server);
		assertEquals(server, "nginx/1.16.0");

	}

}
