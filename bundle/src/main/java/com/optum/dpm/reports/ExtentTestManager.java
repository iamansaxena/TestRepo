package com.optum.dpm.reports;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * @author amohan31
 *
 */
public class ExtentTestManager{
	private static Logger logger = LogManager.getLogger(ExtentTestManager.class);

	private final static ExtentReports extent = ExtentManager.getInstance();
	private final static ThreadLocal<ExtentTest> extentTestLocal = new ThreadLocal<ExtentTest>();

	public static synchronized void startTest(String testName) {
		logger.info("ExtentTestManager thread from start ==> {} " , Thread.currentThread().getId());
		if(extentTestLocal.get() == null) {
			extentTestLocal.set(extent.createTest(testName));
		}
		logger.info("Initialized Extent Reports for  test {} with report id {} " , testName , Thread.currentThread().getId());
	}
	
	public static synchronized  ExtentTest getNewExtentTest() {
		return extentTestLocal.get();
	}
	
	public static synchronized  ExtentTest getTest() {
		return (TestListener.getExtentTest() !=null) ? TestListener.getExtentTest() : extentTestLocal.get();
	}

	/**
	 * This method is used to flush the report
	 */
	public static void endTest() {
		logger.info("Flushing Reports {}" , Thread.currentThread().getId());
		extent.flush();
	}
}