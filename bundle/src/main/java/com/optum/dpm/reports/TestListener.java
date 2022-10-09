package com.optum.dpm.reports;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMConfigurationsUtil.browserName;
import static com.optum.dpm.utils.DPMConfigurationsUtil.upload;

import java.io.File;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	private static Logger logger = LogManager.getLogger(TestListener.class);
	{//System.setProperty("scan", "true");
//	System.setProperty("scanWith", "util");
//	System.setProperty("defaultExecution", "false");
	
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO: add context attributes
	}

	public void onFinish(ITestContext context) {
		try {
			//ExtentTestManager.setExecutionTime();
			logger.info("All done, just making some finishing checks!! ");
			//ExtentTestManager.endTest();
			uploadArtifacts();
		} catch (Exception ex) {
			//no-op
		}
	}

	private void uploadArtifacts() {
		if (upload == true) {
			logger.info("*** Uploading Artifacts to NAS");
			// TODO: code refactoring for NAS upload
			//FileUploader.uploadArtifacts();
		}
	}

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = getTest().createNode(getTestMethodName(result));
		logger.info("*** Test Case being executed:  {} [ {} ]");
		try {
			extentTest.assignCategory(getTestFieldValue(result, "tag"));
			extentTest.assignAuthor(getTestFieldValue(result, "author"));
		} catch (Exception ex) {
			logger.warn("Test Tag / Author field is missing in {} ");
		}
	}

	private String getTestFieldValue(ITestResult result, String fieldName) {
		try {
			return (String) FieldUtils.readField(result.getInstance(), fieldName, true);
		} catch (Exception ex) {
			logger.error("Test {} field is missing in {}");   
		}
		return fieldName; // return default
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("*** Executed :  {} [ {} ]. Test is passed.] ");
		getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		String methodName = getTestMethodName(result);
		logger.error("*** Executed :  {} [ {} ]. Test is failed.] ");
		try {
			WebDriver webDriver = (WebDriver) FieldUtils.readField(result.getInstance(), "webDriver", true);
			File screentShotFile = ((TakesScreenshot) Objects.requireNonNull(webDriver)).getScreenshotAs(OutputType.FILE);
			String screenShotFileName = "/grabs/" + methodName + "_" + new Random().nextLong() + "_" + browserName + ".png";
			FileUtils.copyFile(screentShotFile, new File(System.getProperty("user.dir") + screenShotFileName));
			getTest().log(Status.FAIL, "<a href='.." + screenShotFileName + "'>" + methodName + "</a>");
		} catch (Exception ex) {
			logger.error("Failed to capture  screenshot {}");
			getTest().log(Status.FAIL, "Test Failed");
		} finally {
			getTest().createNode("Exception under method: ").fail(result.getThrowable());
		}
		logger.error(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		logger.info("*** {} [ {} ]. Test is skipped.] ");
		getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.warn("***Retrying " + getTestMethodName(result));
	}

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	private static String getTestClassName(ITestResult iTestResult) {
		return iTestResult.getMethod().getClass().getName();
	}
}
