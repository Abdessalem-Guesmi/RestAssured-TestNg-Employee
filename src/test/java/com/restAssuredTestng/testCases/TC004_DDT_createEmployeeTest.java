package com.restAssuredTestng.testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restAssuredTestng.model.Employee;
import com.restAssuredTestng.utilities.BaseTest;
import com.restAssuredTestng.utilities.ExcelUtils_V2;

import io.restassured.http.Method;

public class TC004_DDT_createEmployeeTest extends BaseTest {

	@Test(dataProvider = "addEmploy")
	public void addNewEmployees(String Employname, String Employsalary, String Employage) throws InterruptedException {
		employee = new Employee();
		employee.setNameEmploy(Employname);
		employee.setSalaryEmploy(Employsalary);
		employee.setAgeEmploy(Employage);
		System.out.println(employee.getNameEmploy());
		System.out.println(employee.getSalaryEmploy());
		System.out.println(employee.getAgeEmploy());
		requestHttp.header("content-Type", "application/json");
		requestHttp.body(employee);

		response = requestHttp.request(Method.POST, "/create");
		Thread.sleep(5000);
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		String Employee = response.getBody().asString();
		assertEquals(Employee.contains(employee.getNameEmploy()), true);
		assertEquals(Employee.contains(employee.getSalaryEmploy()), true);
		assertEquals(Employee.contains(employee.getAgeEmploy()), true);

		System.out.println(response.getContentType());
		System.out.println(response.getTime());
		System.out.println(response.getStatusLine());
		System.out.println(response.getBody().asString());
	}

	@DataProvider(name = "addEmploy")

	public String[][] getEmplData() throws IOException {
		String pathFile = System.getProperty("user.dir") + "/data/data1.xlsx";
		int rownum = ExcelUtils_V2.getRowCount(pathFile, "sheet1");
		int colCount = ExcelUtils_V2.getCellCount(pathFile, "sheet1", 1);

		String[][] empdata = new String[rownum][colCount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colCount; j++) {
				empdata[i - 1][j] = ExcelUtils_V2.getCellData(pathFile, "sheet1", i, j);
			}
		}

		return (empdata);
	}
}
