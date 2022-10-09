package com.optum.dpm.reports;

import static com.optum.dpm.utils.DPMConfigurationsUtil.startTime;

import java.text.ParseException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExecutionTImeCalculator;

/**
 * @author amohan31
 *
 */
public class ExtentTestManager{
	private static Logger logger = LogManager.getLogger(ExtentTestManager.class);

	private static ExtentReports extent = ExtentManager.getInstance();
	private final static ConcurrentHashMap<Long, ExtentTest> extentTestMap = new ConcurrentHashMap<>();


	/**
	 * This method is used to fetch the total execution time and store it in the
	 * Reports itself
	 * 
	 * @throws ParseException
	 */
	public static void setExecutionTime() {
		try {
			extent.setSystemInfo("Execution Time",ExecutionTImeCalculator.getTotalExecutionTime(startTime, ExecutionTImeCalculator.getCurrentTime())+ " minutes");
		}catch(Exception ex) {
			//no-op
		}
	}

	public static synchronized void startTest(String testName) {
		logger.info("ExtentTestManager thread from start ==> {} ");
		extentTestMap.put(Thread.currentThread().getId(), extent.createTest(testName));
		logger.info("Initialized Extent Reports for  test {} with report id {} ");
	}
	
	public static ExtentTest getTest() {
		return extentTestMap.get(Thread.currentThread().getId());
	}

	/**
	 * This method is used to flush the report
	 */
	public static void endTest() {
		logger.info("Flushing Reports {}");
		extent.flush();
		extentTestMap.remove( Thread.currentThread().getId());
	}
}