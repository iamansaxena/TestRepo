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

public class ExtentTestManager extends Base {
	private static ThreadLocal<String> classNamePool = new ThreadLocal<>();
	static int i =0;
	public static ConcurrentHashMap<String, ExtentTest> extentTestMap = new ConcurrentHashMap<>();
	public static String destination;
	private static int count = 0;
	static ExtentReports extent ;

	public static  ExtentTest getTest(String className) {
		classNamePool.set(className);
		return (ExtentTest) extentTestMap.get(className);
	}

	public static synchronized void endTest() {
		logger.info("Flushing Reports");
		extent.flush();
	}
public static void setExecutionTime() throws ParseException {
	extent.setSystemInfo("Execution Time", ExecutionTImeCalculator.getTotalExecutionTime(startTime, ExecutionTImeCalculator.getCurrentTime())+" minutes");	
	}
	public static synchronized void startReport() {
		extent = ExtentManager.getInstance(AUT, clientName, Executor, Environment, browserName,
				remoteExecution);
	}
		public static  void startTest(String className) {
		synchronized (className) {
			classNamePool.set(className);
		}System.out.println("ExtentTestManager thread from start ==> "+ Thread.currentThread().getId());
		
		extentTestMap.put(classNamePool.get(), extent.createTest(classNamePool.get()));
		if(i==0) {
		logger.info("Initializing Reports for => "+classNamePool.get());
		i++;}
		
		classNamePool.remove();
	}

	public synchronized static String reportScreenshot(String methodname, String className) {
		String meth = methodname;
		String clazz= className;
		String serverDir = null;
		TakesScreenshot ts=null ;
		File source=null;
	try {	ts= (TakesScreenshot) driverMap.get(clazz);
		source = ts.getScreenshotAs(OutputType.FILE);}
	catch (Exception e) {
		System.out.println("[ERROR] Unable to find browser session while taking screenshot");
		System.out.println("Tried finding browser for => "+clazz +" ["+meth+"]");
	}

		destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + meth + "_"
				+ count + ".png";
		if(Environment.equalsIgnoreCase("test")){
			serverDir = "./Grabs/"+meth + "_"+ count + ".png";
			}
		else if(Environment.equalsIgnoreCase("stage")) {
			if(System.getProperty("regression").equalsIgnoreCase("true")) {
				serverDir = "./Grabs/"+meth + "_"+ count + ".png";
			}
			else {
			serverDir = "./Grabs/"+meth + "_"+ count + ".png";
			}
		}
		
		else if(Environment.equalsIgnoreCase("uat")) {
			if(System.getProperty("regression").equalsIgnoreCase("true")) {
				serverDir = "./Grabs/"+meth + "_"+ count + ".png";
			}
			else {
			serverDir = "./Grabs/"+meth + "_"+ count + ".png";
			}
		}
		else if(Environment.equalsIgnoreCase("prod")) {
			serverDir = "./Grabs/"+meth + "_"+ count + ".png";
		}
		count++;
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
		logger.info("Unable to find screenshot file");
			e.printStackTrace();
		}
		
		if(upload==true) {
		return serverDir;
//		return destination;
		}
		else {
			return destination;
		}
	}

}
