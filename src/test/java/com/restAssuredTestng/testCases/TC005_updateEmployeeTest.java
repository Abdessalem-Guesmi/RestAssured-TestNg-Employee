package com.restAssuredTestng.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredTestng.model.Employee;
import com.restAssuredTestng.utilities.BaseTest;
import com.restAssuredTestng.utilities.RestUtils;

import io.restassured.http.Method;

public class TC005_updateEmployeeTest extends BaseTest {
	String nameEmployee;
	Employee employee = new Employee();

	@BeforeClass
	public void addnewEmployee() throws InterruptedException {
		employee.setNameEmploy(RestUtils.generateEmpName());
		employee.setSalaryEmploy(RestUtils.generateEmpSalary());
		employee.setAgeEmploy(RestUtils.generateEmpAge());
		System.out.println(employee.getNameEmploy());
		System.out.println(employee.getSalaryEmploy());
		System.out.println(employee.getAgeEmploy());
		requestHttp.header("content-Type", "application/json");
		requestHttp.body(employee);

		response = requestHttp.request(Method.PUT, "/update/" + readconfig.getEmployID());
		Thread.sleep(5000);
		nameEmployee = response.getBody().asString();
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

		assertEquals(nameEmployee.contains(employee.getNameEmploy()), true);

	}

	@Test
	public void checkSalary() {

		assertEquals(nameEmployee.contains(employee.getSalaryEmploy()), true);

	}

	@Test
	public void checkAge() {

		assertEquals(nameEmployee.contains(employee.getAgeEmploy()), true);

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

}
