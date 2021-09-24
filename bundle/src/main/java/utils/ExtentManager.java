package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.Base;

/**
 * @author amohan31
 *
 */
public class ExtentManager extends Base {
	private static ExtentReports extent;

	/**
	 * This method is used to fetch an instance of the Extent Report
	 * 
	 * @param ReportName
	 *            Name to be given to the report
	 * @param client
	 *            CLient name to be included in the report
	 * @param executorName
	 *            member name executing the suite
	 * @param environment
	 *            Environment on which the suite needs to be executed
	 * @param browser
	 *            The browser opted for execution
	 * @param remoteExecution
	 *            if execution moe is set to remote or not
	 * @return
	 */
	public static ExtentReports getInstance(String ReportName, String client, String executorName, String environment,
			String browser, String remoteExecution) {
		if (extent == null)
			createInstance(ReportName, client, executorName, environment, browser, remoteExecution);
		return extent;
	}

	/**
	 * This method is used to create an instance of the Extent Report
	 * 
	 * @param ReportName
	 *            Name to be given to the report
	 * @param client
	 *            CLient name to be included in the report
	 * @param executorName
	 *            member name executing the suite
	 * @param environment
	 *            Environment on which the suite needs to be executed
	 * @param browser
	 *            The browser opted for execution
	 * @param remoteExecution
	 *            if execution moe is set to remote or not
	 * @return
	 */
	public static ExtentReports createInstance(String ReportName, String client, String executorName,
			String environment, String browser, String remoteExecution) {
		extent = new ExtentReports();
		ExtentHtmlReporter reporter = new ExtentHtmlReporter((System.getProperty("user.dir") + "//Reports//" + dateName
				+ "//Report_" + client + "_" + dateName + "_" + browser + ".html"));
		reporter.config().setProtocol(Protocol.HTTPS);

		reporter.config().setDocumentTitle(client + "- Automation Test Report");
		//
		reporter.config().setReportName(ReportName);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		// Environment Setup
		extent.setSystemInfo("OS Name", System.getProperty("os.name"));
		extent.setSystemInfo("Username", executorName);
		extent.setSystemInfo("Environment", environment);
		extent.setSystemInfo("Execution Device/Application", browser);
		extent.setSystemInfo("Remote Execution", remoteExecution);
		if (isRegression == true) {
			extent.setSystemInfo("Regression Run", "True");
		} else if (isRegression == false) {
			extent.setSystemInfo("Regression Run", "False");
		}

		reporter.config().enableTimeline(true);
		extent.attachReporter(reporter);
		extent.setReportUsesManualConfiguration(true);
		return extent;
	}

}