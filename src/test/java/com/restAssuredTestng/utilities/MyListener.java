package com.restAssuredTestng.utilities;

import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class MyListener implements ITestListener {
	private static ExtentReports extent = BaseTest.getInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public ExtentTest logger;

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "::" + result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Pass </b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, markup);
		logger = extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN)); // send the passed
																								// information to the
																								// report with GREEN
																								// color highlighted

	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>Exception Occured, click to see details:"
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Failed </b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, markup);
		logger = extent.createTest(result.getName()); // create new entry in th report
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED)); // send the passed
	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b> Test Method " + result.getMethod().getMethodName() + " Skip </b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, markup);

		logger = extent.createTest(result.getName()); // create new entry in th report
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public static String getReporttName() {
		Date d = new Date();
		String fileName = "Report_" + d.toString().replace(":", "-") + ".html";
		return fileName;

	}
}
