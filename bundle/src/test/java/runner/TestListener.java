package runner;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import core.Base;
import utils.ExtentTestManager;
import utils.FileUploader;

public class TestListener extends Base implements ITestListener {
	public static String stackTrace;
	public static String className;
	public static ThreadLocal<ConcurrentHashMap<String, ExtentTest>> reportLogger = new ThreadLocal<>();
	int count = 0;
	{
		try {
			Base.initialize();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	@Override
	public void onStart(ITestContext context) {
		ExtentTestManager.startReport();
	}

	public void onFinish(ITestContext context) {
		try {
			ExtentTestManager.setExecutionTime();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loggerInstance.get(Base.class.getName()).info("All done, just making some finishing checks!! ");
		ExtentTestManager.endTest();
		if (upload == true) {
			try {
				FileUploader.uploadArtifacts();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestStart(ITestResult result) {
		synchronized (result) {
			urlUnderTest.set(new ArrayList<String>());
			reportLogger.set(new ConcurrentHashMap<String, ExtentTest>());
			customTestLogs.set(new ArrayList<>());
			reportLogger.get().put(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName(),
					ExtentTestManager.getTest(result.getMethod().getTestClass().getName())
							.createNode(result.getMethod().getMethodName()));
		}

		loggerInstance.get(result.getMethod().getTestClass().getName()).info("*** Test Case being executed: "
				+ result.getMethod().getMethodName() + "[ " + result.getMethod().getTestClass().getName() + " ]");

		reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
				.assignCategory(Base.tags.get(result.getMethod().getTestClass().getName()));
		reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
				.assignAuthor(Base.authors.get(result.getMethod().getTestClass().getName()));

	}

	public void onTestSuccess(ITestResult result) {
		loggerInstance.get(result.getMethod().getTestClass().getName())
				.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
				.info("Test URLs: " + testURLS.get(result.getMethod().getTestClass().getName()));
		urlUnderTest.get().stream().forEach(a -> {
			reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
					.info("Last  Tested On: " + a);
		});
		try {
			customTestLogs.get().stream().forEach(a -> {
				reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
						.info(a);
			});
		} catch (NullPointerException e) {

		}
	}

<<<<<<< Updated upstream
	public void onTestFailure(ITestResult result) {
		synchronized (result) {
			failResultPool.set(result);
=======
	public  void onTestFailure(ITestResult result) {
		loggerInstance.get(result.getMethod().getTestClass().getName())
				.error("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		try {
			reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
					.log(Status.FAIL,
							"<a href='"
									+ ExtentTestManager.reportScreenshot(result.getMethod().getMethodName(),
											result.getMethod().getTestClass().getName().split("\\.")[1])
									+ "'>Failed Screenshot" + count + "</a>");
		} catch (NullPointerException e) {
			loggerInstance.get(result.getMethod().getTestClass().getName())
					.fatal("Unable to fetch screenshot for failure at " + result.getMethod().getMethodName());
>>>>>>> Stashed changes
		}
		count++;
		urlUnderTest.get().stream().forEach(a -> {
			reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
					.info("Last  Tested On: " + a);
		});

		reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
				.createNode("Exception under method: " + result.getMethod().getMethodName())
				.fail(result.getThrowable());
		try {
			customTestLogs.get().stream().forEach(a -> {
				reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
						.info(a);
			});
		} catch (NullPointerException e) {

		}
		loggerInstance.get(result.getMethod().getTestClass().getName()).error(result.getThrowable());

	}

<<<<<<< Updated upstream
	public void onTestSkipped(ITestResult result) {
		synchronized (result) {
			skipResultPool.set(result);
=======
	public  void onTestSkipped(ITestResult result) {
		try {

			reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
					.log(Status.SKIP,
							"<a href='"
									+ ExtentTestManager.reportScreenshot(result.getMethod().getMethodName(),
											result.getMethod().getTestClass().getName().split("\\.")[1])
									+ "'>Skipped Screenshot" + count + "</a>");
		} catch (NullPointerException e) {
			loggerInstance.get(result.getMethod().getTestClass().getName())
					.fatal("Unable to fetch screenshot for failure at " + result.getMethod().getMethodName());
>>>>>>> Stashed changes
		}
		urlUnderTest.get().stream().forEach(a -> {
			reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
					.info("Last  Tested On: " + a);
		});
		try {
			customTestLogs.get().stream().forEach(a -> {
				reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
						.info(a);
			});
		} catch (NullPointerException e) {

		}

		count++;

		reportLogger.get().get(result.getMethod().getTestClass().getName() + result.getMethod().getMethodName())
				.createNode("Skip Exception under method: " + result.getMethod().getMethodName())
				.skip(result.getThrowable());
		loggerInstance.get(result.getMethod().getTestClass().getName()).info(
				"*** Test: " + result.getMethod().getMethodName() + " [ " + className + " ] " + " has been Skipped...");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		loggerInstance.get(result.getMethod().getTestClass().getName())
				.warn("***Retrying " + result.getMethod().getMethodName());
	}

}
