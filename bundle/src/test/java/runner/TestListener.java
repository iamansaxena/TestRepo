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
	public static ThreadLocal<ITestResult> skipResultPool = new ThreadLocal<>();
	public static ThreadLocal<ITestResult> failResultPool = new ThreadLocal<>();
	public static ThreadLocal<ITestResult> passResultPool = new ThreadLocal<>();

	int count = 0;
	{
		Base.initialize();

	}

	@Override
	public void onStart(ITestContext context) {
		ExtentTestManager.startReport();
	}

	public void onFinish(ITestContext context) {
		try {
			ExtentTestManager.setExecutionTime();
		} catch (ParseException e1) {
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
		synchronized (result) {
			passResultPool.set(result);
		}
		loggerInstance.get(passResultPool.get().getMethod().getTestClass().getName())
				.info("*** Executed " + passResultPool.get().getMethod().getMethodName() + " test successfully...");
		reportLogger.get()
				.get(passResultPool.get().getMethod().getTestClass().getName()
						+ passResultPool.get().getMethod().getMethodName())
				.info("Test URLs: " + testURLS.get(passResultPool.get().getMethod().getTestClass().getName()));
		try {
			customTestLogs.get().stream().forEach(a -> {
				reportLogger.get().get(passResultPool.get().getMethod().getTestClass().getName()
						+ passResultPool.get().getMethod().getMethodName()).info(a);
			});
		} catch (NullPointerException e) {

		}
	}

	public void onTestFailure(ITestResult result) {
		synchronized (result) {
			failResultPool.set(result);
			System.out.println(result.getMethod().getTestClass().getName());
		}
		loggerInstance.get(failResultPool.get().getMethod().getTestClass().getName())
				.error("*** Test execution " + failResultPool.get().getMethod().getMethodName() + " failed...");
		// try {
		reportLogger.get()
				.get(failResultPool.get().getMethod().getTestClass().getName()
						+ failResultPool.get().getMethod().getMethodName())
				.log(Status.FAIL,
						"<a href='"
								+ ExtentTestManager.reportScreenshot(failResultPool.get().getMethod().getMethodName(),
										failResultPool.get().getMethod().getTestClass().getName().split("\\.")[1])
								+ "'>Failed Screenshot" + count + "</a>");
		// } catch (NullPointerException e) {
		// loggerInstance.get(failResultPool.get().getMethod().getTestClass().getName()).fatal(
		// "Unable to fetch screenshot for failure at "
		// +failResultPool.get().getTestClass().getName()+" ==> "+
		// failResultPool.get().getMethod().getMethodName());
		// }
		count++;

		reportLogger.get()
				.get(failResultPool.get().getMethod().getTestClass().getName()
						+ failResultPool.get().getMethod().getMethodName())
				.createNode("Exception under method: " + failResultPool.get().getMethod().getMethodName())
				.fail(failResultPool.get().getThrowable());
		try {
			customTestLogs.get().stream().forEach(a -> {
				reportLogger.get().get(failResultPool.get().getMethod().getTestClass().getName()
						+ failResultPool.get().getMethod().getMethodName()).info(a);
			});
		} catch (NullPointerException e) {

		}
		loggerInstance.get(failResultPool.get().getMethod().getTestClass().getName())
				.error(failResultPool.get().getThrowable());

	}

	public void onTestSkipped(ITestResult result) {
		synchronized (result) {
			skipResultPool.set(result);
			System.out.println(result.getMethod().getTestClass().getName());
		}
		// try {

		reportLogger.get()
				.get(skipResultPool.get().getMethod().getTestClass().getName()
						+ skipResultPool.get().getMethod().getMethodName())
				.log(Status.SKIP,
						"<a href='"
								+ ExtentTestManager.reportScreenshot(skipResultPool.get().getMethod().getMethodName(),
										skipResultPool.get().getMethod().getTestClass().getName().split("\\.")[1])
								+ "'>Skipped Screenshot" + count + "</a>");
		// } catch (NullPointerException e) {
		// loggerInstance.get(skipResultPool.get().getMethod().getTestClass().getName()).fatal(
		// "Unable to fetch screenshot for Skip at " +
		// skipResultPool.get().getTestClass().getName()+" ==>
		// "+skipResultPool.get().getMethod().getMethodName());
		// }
		try {
			customTestLogs.get().stream().forEach(a -> {
				reportLogger.get().get(skipResultPool.get().getMethod().getTestClass().getName()
						+ skipResultPool.get().getMethod().getMethodName()).info(a);
			});
		} catch (NullPointerException e) {

		}

		count++;

		reportLogger.get()
				.get(skipResultPool.get().getMethod().getTestClass().getName()
						+ skipResultPool.get().getMethod().getMethodName())
				.createNode("Skip Exception under method: " + skipResultPool.get().getMethod().getMethodName())
				.skip(skipResultPool.get().getThrowable());

		loggerInstance.get(skipResultPool.get().getMethod().getTestClass().getName())
				.info("*** Test: " + skipResultPool.get().getMethod().getMethodName() + " [ " + className + " ] "
						+ " has been Skipped...");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		loggerInstance.get(result.getMethod().getTestClass().getName())
				.warn("***Retrying " + result.getMethod().getMethodName());
	}

}
