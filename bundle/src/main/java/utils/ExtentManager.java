package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.Base;

public class ExtentManager extends Base {
	private static ExtentReports extent;

	private static String fileSeperator = System.getProperty("file.separator");

	// private static String reportFileLocation = reportFilepath +fileSeperator+
	// reportFileName;

	public static ExtentReports getInstance(String ReportName, String client, String executorName, String environment,
			String browser, String remoteExecution) {
		if (extent == null)
			createInstance(ReportName, client, executorName, environment, browser, remoteExecution);
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance(String ReportName, String client, String executorName,
			String environment, String browser, String remoteExecution) {
//		String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		extent = new ExtentReports();
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(
				(System.getProperty("user.dir") + "//Reports//" + dateName
						+ "//Report_" + client +"_"+ dateName +"_"+browser+ ".html"));
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
		if(isRegression==true) {
			extent.setSystemInfo("Regression Run", "True");	
		}
		else if(isRegression==false) {
			extent.setSystemInfo("Regression Run", "False");	
		}
		
		reporter.config().enableTimeline(true);
		extent.attachReporter(reporter);
		extent.setReportUsesManualConfiguration(true);
		return extent;
	}

}