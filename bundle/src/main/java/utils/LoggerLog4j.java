package utils;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author amohan31
 *
 */
public class LoggerLog4j {
	public static Logger logger;

	public static ConcurrentHashMap<String, Logger> loggerInstance = new ConcurrentHashMap<>();
	public static ThreadLocal<Logger> loggerPool = new ThreadLocal<>();

	/**
	 * This is a synchronized method to generate logger object and store it in a
	 * ConcurrentHashMap
	 * 
	 * @param name
	 *            name to be associated with the logger object
	 * @return
	 */
	public static Logger startTestCase(Class name) {
		loggerInstance.put(name.getName(), LogManager.getLogger(name));

		return loggerInstance.get(name.getName());

	}

	/**
	 * This is a thread-safe method used to create logger objects which support
	 * multi-threading
	 * 
	 * @param name
	 *            name to be given to the thread
	 */
	public static synchronized void startLoggerPool(String name) {

		loggerPool.set(LogManager.getLogger(name));

	}
}
