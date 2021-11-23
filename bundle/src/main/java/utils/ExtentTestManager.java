package utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import core.Base;

/**
 * @author amohan31
 *
 */
public class ExtentTestManager extends Base {
	private static ThreadLocal<String> classNamePool = new ThreadLocal<>();
	static int i = 0;
	public static ConcurrentHashMap<String, ExtentTest> extentTestMap = new ConcurrentHashMap<>();
	public static String destination;
	private static int count = 0;
	static ExtentReports extent;

	public static ExtentTest getTest(String className) {
		classNamePool.set(className);
		return (ExtentTest) extentTestMap.get(className);
	}

	/**
	 * This method is used to flush the report
	 */
	public static synchronized void endTest() {
		logger.info("Flushing Reports");
		extent.flush();
	}

	/**
	 * This method is used to fetch the total execution time and store it in the
	 * Reports itself
	 * 
	 * @throws ParseException
	 */
	public static void setExecutionTime() throws ParseException {
		extent.setSystemInfo("Execution Time",
				ExecutionTImeCalculator.getTotalExecutionTime(startTime, ExecutionTImeCalculator.getCurrentTime())
						+ " minutes");
	}

	/**
	 * This is a helper method to fetch main instance of the extent report
	 */
	public static synchronized void startReport() {
		extent = ExtentManager.getInstance(AUT, clientName, Executor, Environment, browserName, remoteExecution);
	}

	/**
	 * This method is used to create a new test under the extent report and store
	 * the object as a key-value pair where the key is the class name
	 * 
	 * @param className
	 *            Name of the Respective Test class
	 */
	public static void startTest(String className) {
		synchronized (className) {
			classNamePool.set(className);
		}
		System.out.println("ExtentTestManager thread from start ==> " + Thread.currentThread().getId());

		extentTestMap.put(classNamePool.get(), extent.createTest(classNamePool.get()));
		if (i == 0) {
			logger.info("Initializing Reports for => " + classNamePool.get());
			i++;
		}

		classNamePool.remove();
	}

	/**
	 * This function is used to capture a screenshot and store it as per the
	 * pre-defined conditions
	 * 
	 * @param methodname
	 *            Name of the TC requesting to take screenshot
	 * @param className
	 *            Name of the TC's class requesting to take screenshot
	 * @return
	 */
	public synchronized static String reportScreenshot(String methodname, String className) {
		String meth = methodname;
		String clazz = className;
		String serverDir = null;
		TakesScreenshot ts = null;
		File source = null;
		try {
			ts = (TakesScreenshot) driverMap.get(clazz);
			source = ts.getScreenshotAs(OutputType.FILE);
		} catch (Exception e) {
			System.out.println("[ERROR] Unable to find browser session while taking screenshot");
			System.out.println("Tried finding browser for => " + clazz + " [" + meth + "]");
		}

		destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + meth + "_" + count + "_"
				+ browserName + ".png";
		if (Environment.equalsIgnoreCase("test")) {
			serverDir = "../grabs/" + meth + "_" + count + "_" + browserName + ".png";
		} else if (Environment.equalsIgnoreCase("stage")) {
			if (System.getProperty("regression").equalsIgnoreCase("true")) {
				serverDir = "../grabs/" + meth + "_" + count + "_" + browserName + ".png";
			} else {
				serverDir = "../grabs/" + meth + "_" + count + "_" + browserName + ".png";
			}
		}

		else if (Environment.equalsIgnoreCase("uat")) {
			if (System.getProperty("regression").equalsIgnoreCase("true")) {
				serverDir = "../grabs/" + meth + "_" + count + "_" + browserName + ".png";
			} else {
				serverDir = "../grabs/" + meth + "_" + count + "_" + browserName + ".png";
			}
		} else if (System.getProperty("regression").equalsIgnoreCase("true")) {
			serverDir = "../grabs/" + meth + "_" + count + "_" + browserName + ".png";
		} else {
			serverDir = "../grabs/" + meth + "_" + count + "_" + browserName + ".png";
		}
		count++;
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			logger.info("Unable to find screenshot file 'IO EXCEPTION'");
//			e.printStackTrace();
		}
		catch (NullPointerException e) {
			logger.info("Unable to find screenshot file 'NullPointer'");
		}

		if (upload == true) {
			if (serverDir == null || serverDir.isEmpty()) {
				serverDir = System.getProperty("user.dir") + "\\UnableToCaptureScreenshot";
			}
			return serverDir;
			// return destination;
		} else {
			if (destination == null || destination.isEmpty()) {
				destination = System.getProperty("user.dir") + "\\UnableToCaptureScreenshot";
			}
			return destination;
		}
	}

}
