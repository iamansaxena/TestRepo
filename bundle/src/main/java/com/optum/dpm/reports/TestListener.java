package com.optum.dpm.reports;

import static com.optum.dpm.reports.ExtentTestManager.*;
import static com.optum.dpm.utils.DPMConfigurationsUtil.browserName;
import static com.optum.dpm.utils.DPMConfigurationsUtil.upload;

import java.io.File;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.FileUploader;

public class TestListener implements ITestListener {
	private static Logger logger = LogManager.getLogger(TestListener.class);
	private final static ThreadLocal<ExtentTest> exTestLocal = new ThreadLocal<ExtentTest>();

	@Override
	public void onStart(ITestContext context) {
		// TODO: add context attributes
	}

	public void onFinish(ITestContext context) {
		try {
			logger.info("All done, just making some finishing checks!! ");
			endTest();
			uploadArtifacts();
		} catch (Exception ex) {
			logger.error("Error while uploading files.");
		}
	}

	private void uploadArtifacts() {
		if (upload == true) {
			logger.info("*** Uploading Artifacts to NAS");
			try {
				FileUploader.uploadArtifacts();
			}catch(Exception ex) {
				logger.error(ExceptionUtils.getStackTrace(ex));
			}
		}
	}

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = getNewExtentTest().createNode(getTestMethodName(result));
		exTestLocal.set(extentTest);
		logger.info("*** Test Case being executed:  {} [ {} ]", getTestClassName(result), getTestMethodName(result));
		try {
			extentTest.assignCategory(getTestFieldValue(result, "tag"));
			extentTest.assignAuthor(getTestFieldValue(result, "author"));
		} catch (Exception ex) {
			logger.warn("Test Tag / Author field is missing in {} ", getTestClassName(result));
		}
	}

	private String getTestFieldValue(ITestResult result, String fieldName) {
		try {
			return (String) FieldUtils.readField(result.getInstance(), fieldName, true);
		} catch (Exception ex) {
			logger.error("Test {} field is missing in {}", fieldName, getTestClassName(result));
		}
		return fieldName; // return default
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("*** Executed :  {} [ {} ]. Test is passed.] ", getTestClassName(result),
				getTestMethodName(result));
		exTestLocal.get().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		String methodName = getTestMethodName(result);
		logger.error("*** Executed :  {} [ {} ]. Test is failed.] ", getTestClassName(result),methodName);
		try {
			WebDriver webDriver = (WebDriver) FieldUtils.readField(result.getInstance(), "mydriver", true);
			File screentShotFile = ((TakesScreenshot) Objects.requireNonNull(webDriver)).getScreenshotAs(OutputType.FILE);
			String screenShotFileName = "/grabs/" + methodName + "_" + new Random().nextLong() + "_" + browserName + ".png";
			FileUtils.copyFile(screentShotFile, new File(System.getProperty("user.dir") + screenShotFileName));
			exTestLocal.get().log(Status.FAIL, "<a href='../.." + screenShotFileName + "'>" + methodName + "</a>");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Failed to capture  screenshot {}", methodName);
			exTestLocal.get().log(Status.FAIL, "Test Failed");
		} finally {
			exTestLocal.get().createNode("Exception under method: ").fail(result.getThrowable());
		}
		logger.error(result.getThrowable());
		result.getThrowable().printStackTrace();
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = getTestMethodName(result);
		logger.error("*** Executed :  {} [ {} ]. Test is failed.] ", getTestClassName(result),methodName);
		try {
			WebDriver webDriver = (WebDriver) FieldUtils.readField(result.getInstance(), "mydriver", true);
			File screentShotFile = ((TakesScreenshot) Objects.requireNonNull(webDriver)).getScreenshotAs(OutputType.FILE);
			String screenShotFileName = "/grabs/" + methodName + "_" + new Random().nextLong() + "_" + browserName + ".png";
			FileUtils.copyFile(screentShotFile, new File(System.getProperty("user.dir") + screenShotFileName));
			exTestLocal.get().log(Status.SKIP, "<a href='../.." + screenShotFileName + "'>" + methodName + "</a>");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Failed to capture  screenshot {}", methodName);
			exTestLocal.get().log(Status.SKIP, "Test Skipped");
		} finally {
			exTestLocal.get().createNode("Exception under method: ").skip(result.getThrowable());
		}
		logger.error(result.getThrowable());
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
	
	public static ExtentTest  getExtentTest() {
		return exTestLocal.get();
	}
}
