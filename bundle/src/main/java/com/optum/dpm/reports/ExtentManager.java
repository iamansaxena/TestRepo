package com.optum.dpm.reports;

import static com.optum.dpm.utils.DPMConfigurationsUtil.AUT;
import static com.optum.dpm.utils.DPMConfigurationsUtil.Environment;
import static com.optum.dpm.utils.DPMConfigurationsUtil.Executor;
import static com.optum.dpm.utils.DPMConfigurationsUtil.browser;
import static com.optum.dpm.utils.DPMConfigurationsUtil.clientName;
import static com.optum.dpm.utils.DPMConfigurationsUtil.dateName;
import static com.optum.dpm.utils.DPMConfigurationsUtil.isRegression;
import static com.optum.dpm.utils.DPMConfigurationsUtil.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author amohan31
 *
 */
public class ExtentManager {
	private static final ExtentReports extentReports = new ExtentReports();
	
	private static Logger logger = LogManager.getLogger(ExtentManager.class);
	
	static {
		createInstance();
		logger.info("ExtentReports object created and initilized");
	}

	private  static ExtentReports createInstance() {
		//TODO: move to config
		ExtentSparkReporter reporter = new ExtentSparkReporter((System.getProperty("user.dir") + "//Reports//" + dateName
				+ "//Report_" + clientName + "_" + dateName + "_" +  browserMap.get(browserName) + ".html"));
		
		reporter.config().setProtocol(Protocol.HTTPS);

		reporter.config().setDocumentTitle(clientName + "- Automation Test Report");
		reporter.config().setReportName(AUT);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		reporter.config().setTimelineEnabled(true);
		reporter.config().enableOfflineMode(true);
		
		// Environment Setup
		extentReports.setSystemInfo("OS Name", System.getProperty("os.name"));
		extentReports.setSystemInfo("Username", Executor);
		extentReports.setSystemInfo("Environment", Environment);
		extentReports.setSystemInfo("Execution Device/Application", browser);
		extentReports.setSystemInfo("Remote Execution", remoteExecution+"");
		if (isRegression == true) {
			extentReports.setSystemInfo("Regression Run", "True");
		} else if (isRegression == false) {
			extentReports.setSystemInfo("Regression Run", "False");
		}

		extentReports.attachReporter(reporter);
		extentReports.setReportUsesManualConfiguration(true);
		return extentReports;
	}
	
	public static ExtentReports getInstance() {
		return extentReports;
	}

}