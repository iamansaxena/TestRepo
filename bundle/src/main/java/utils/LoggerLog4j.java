package utils;



import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerLog4j {
	public static Logger logger;

	public static HashMap<String, Logger> loggerInstance = new HashMap<>();
public static ThreadLocal<Logger> loggerPool = new ThreadLocal<>();
	public static  Logger startTestCase(Class name) {
//		String sTestCaseName = name.getName();
//		logger = LogManager.getLogger(name);
		loggerInstance.put(name.getName(), LogManager.getLogger(name));
		
		return loggerInstance.get(name.getName());

	}
	public static synchronized void startLoggerPool(String name) {
		
		loggerPool.set( LogManager.getLogger(name));
		
		
		

	}
}
