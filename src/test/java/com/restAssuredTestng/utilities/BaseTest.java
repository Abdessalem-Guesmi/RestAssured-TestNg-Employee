package com.restAssuredTestng.utilities;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.restAssuredTestng.model.Employee;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	private static ExtentHtmlReporter htmlReport;
	private static ExtentReports extent;
	public static RequestSpecification requestHttp;
	public static Response response;
	public String nameEmployee;
	public Employee employee;
	public static Logger logger;
	public ReadConfig readconfig = new ReadConfig();
	public String emplID;

	public static ExtentReports getInstance() {

		String reportName = getReporttName();
		String directory = System.getProperty("user.dir") + "/reports/";
		new File(directory).mkdirs();
		String path = directory + reportName;
		htmlReport = new ExtentHtmlReporter(path);
		htmlReport.config().setEncoding("utf-8");
		htmlReport.config().setDocumentTitle("Automation Test"); // title of the report
		htmlReport.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReport.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlReport.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("organization", "abdou guesmi");
		extent.setSystemInfo("browserowser", "Chrome");
		extent.setSystemInfo("organization", "abdou guesmi");
		extent.attachReporter(htmlReport);
		return extent;
	}

	public static String getReporttName() {
		Date d = new Date();
		String fileName = "Report_" + d.toString().replace(":", "-") + ".html";
		return fileName;

	}

	@BeforeClass
	public void setUp() {
		logger = Logger.getLogger("EmployeeLogger");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		logger.info("*****execution is started");
		RestAssured.baseURI = readconfig.getApplicationURL();
		requestHttp = RestAssured.given();

	}

}
