package com.restAssuredTestng.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredTestng.utilities.BaseTest;

import io.restassured.http.Method;

public class TC002_getOneEmployeeTest extends BaseTest {

	@BeforeClass
	public void getOneEmployee() {
		response = requestHttp.request(Method.GET, "/employee/" + readconfig.getEmployID());
		System.out.println(response.getContentType());
		System.out.println(response.getTime());
		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());
	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);

	}

	@Test
	public void checkTime() {
		long time = response.getTime();
		if (time > 500)
			assertTrue(true);
		else
			assertTrue(false);

	}

	@Test
	public void checkName() {
		String nameEmployee = response.getBody().asString();
		assertEquals(nameEmployee.contains("Successfully! Record has been fetched."), true);

	}

	@Test
	public void checkStatusLine() {
		String statusLine = response.getStatusLine();
		assertEquals("HTTP/1.1 200 OK", statusLine);

	}

	@Test
	public void checkContentType() {
		String contentType = response.getContentType();
		assertEquals("application/json", contentType);

	}

	@Test
	public void checkServer() {
		String server = response.header("Server");
		System.out.println(server);
		assertEquals(server, "nginx/1.16.0");

	}

}
