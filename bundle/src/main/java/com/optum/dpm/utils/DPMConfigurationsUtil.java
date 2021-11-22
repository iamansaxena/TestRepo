package com.optum.dpm.utils;

import static com.optum.dpm.utils.DPMConfigurationsUtil.Environment;
import static com.optum.dpm.utils.DPMConfigurationsUtil.componentData;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

import utils.ExecutionTImeCalculator;
import utils.FileUploader;

/**
 * @author amohan31
 *
 */
public class DPMConfigurationsUtil {

	private static final String DEFAULT_CONFIG_LOCATION = "./src/test/java/runner/Config-test.properties";
	private static final List<String> VALID_BROWSRS  = Arrays.asList("-c","-f","-ie","-e","-s","-hc");
	
	private static Logger logger = LogManager.getLogger(DPMConfigurationsUtil.class);
	
	private static final Properties testConfigs;
	public static final String clientName;
	public static final String remoteVersion ;
	public static final String[] url;
	public static final String sauceUserName;
	public static final String sauceAccessKey;
	public static final String nasPath;
	public static final boolean upload;
	public static final boolean isRegression;
	public static final boolean remoteExecution;
	public static final String platform;
	public static final String chrome_path;
	public static final String firefox_path;
	public static final String IeDriverPath;
	public static final String edgeDriverPath;
	public static final String AUT;
	public static final String Executor;
	public static final String Environment;
	public static final String browser;
	public static final String localBrowser;
	public static final String remoteBrowser;
	public static final String browserName;
	public static final boolean scan;
	public static final boolean defaultExecution;
	public static final int MAX_DURATION = 10800;
	public static final int SAUCE_SESSION_TIMEOUT = 1000;
	public static final String parentTunnel = "optumtest"; // "sso-optum-aman.mohan";
	public static final String tunnelIdentifier = "Optum-Prd"; // "mytunnel";
	public static final DesiredCapabilities capability;
	public static final String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	public static final String startTime = ExecutionTImeCalculator.getCurrentTime();
	public static final ConcurrentHashMap<String, String> componentData = new ConcurrentHashMap<String, String>();

	
	public static final Map<String, String> browserMap  = new HashMap<String, String>() {{
	    put("-c", new ChromeOptions().getBrowserName());
	    put("-f", new FirefoxOptions().getBrowserName());
	    put("-s", new SafariOptions().getBrowserName());
	    put("-e",new EdgeOptions().getBrowserName());
	}};
	
	public static final Map<String, MutableCapabilities> remoteBrowserOptions;
	
	static {
		testConfigs = new Properties();
		String config_location =  StringUtils.isNotBlank(System.getProperty("config-file")) ? System.getProperty("config-file") : DEFAULT_CONFIG_LOCATION;
		try{
			logger.info("Loading configurations  {} " , config_location);
			testConfigs.load(new FileInputStream(config_location));
			logger.info("Loaded configurations  {} sucessfully" , config_location);
		}catch(Exception ex) {
			logger.fatal("Unable to load test cofigurations {} ", ExceptionUtils.getStackTrace(ex));
			System.exit(-1); 
		}
		
		clientName = getProperty("client");
		remoteVersion = getProperty("remoteVersion");
		url = StringUtils.split(getProperty("URL_FOR_AUT"));
		sauceUserName = getProperty("sauceUserName");
		sauceAccessKey = getProperty("sauceAccessKey");
		nasPath = getProperty("nasPath");
		upload = getBooleanProperty("upload");
		isRegression = getBooleanProperty("regression");
		remoteExecution = getBooleanProperty("remoteExecution");
		platform = getProperty("platform");
		chrome_path = getProperty("ChromeDriverPath");
		firefox_path = getProperty("FirefoxDriverPath");
		IeDriverPath = getProperty("IeDriverPath");
		edgeDriverPath = getProperty("edgeDriverPath");
		AUT = getProperty("AUT");
		Executor = getProperty("Executor");
		Environment = getProperty("Environment");
		browser = getProperty("browser");
		localBrowser = getBrowser(browser,"localBrowser");
		remoteBrowser = getBrowser(browser,"remoteBrowser");
		browserName = remoteExecution ? remoteBrowser : localBrowser;
		scan = getBooleanProperty("scan");
		defaultExecution = getBooleanProperty("defaultExecution");
		capability = getCapabilities();
		//Validate Browser //TODO: ?
		validateBrowserType();
		
		//Log File
		System.setProperty("logFilename", "./logs");
		System.setProperty("org.freemarker.loggerLibrary", "none");
		
		//Scanning
		if(!scan && !defaultExecution) {
			logger.info("Downloading the updated file from the server..");
			getComponentUrl();
		}else {
			logger.info("Execution with static URLs is starting...");
		}
		
		remoteBrowserOptions  = new HashMap<String, MutableCapabilities>() {{
		    put("-c",getRemoteChromeOptions());
		    put("-f",getRemoteFireFoxOptions());
		    put("-s",getRemoteSafariOptions());
		    put("-e",getRemoteEdgeOptions());
		}};
	}
	
	
	private static String getProperty(String propName) {
		return StringUtils.trim(System.getProperty(propName, testConfigs.getProperty(propName)));
	}
	
	private static String getProperty(String propName,String defaultValue) {
		return StringUtils.trim(System.getProperty(propName, defaultValue));
	}
	
	private static boolean getBooleanProperty(String propName) {
		return BooleanUtils.toBoolean(getProperty(propName));
	}
	
	private static String getBrowser(String browserParam,String propName) {
		return (StringUtils.isNotBlank(browserParam) && StringUtils.equalsIgnoreCase(browserParam, "na")) ? browserParam : getProperty(propName); 
	}
	
	
	private static void validateBrowserType() {
		if( StringUtils.isNotBlank(browser) && !VALID_BROWSRS.contains(browser)) {
			logger.fatal("Invalid browsers {} !!. Supported browsers are {} ", browser,VALID_BROWSRS);
			System.exit(-1);
		}
	}
	
	/**
	 * This method is used to set the browser specific desired capabilities as per
	 * the browser selected by the user
	 * 
	 * @return
	 */
	public static DesiredCapabilities getCapabilities() {
		DesiredCapabilities sdCapabilities = new DesiredCapabilities();

		sdCapabilities.setCapability("commandTimeout", 600);
		sdCapabilities.setCapability("idleTimeout", SAUCE_SESSION_TIMEOUT);
		sdCapabilities.setCapability("maxDuration", MAX_DURATION);
		sdCapabilities.setCapability("parentTunnel", parentTunnel);
		sdCapabilities.setCapability("tunnelIdentifier", tunnelIdentifier);
		sdCapabilities.setCapability("seleniumVersion", "3.141.59");
		sdCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		sdCapabilities.setCapability("browserName", browserMap.get(browserName));
		
		if (!browserName.equals("-s")) {
			sdCapabilities.setCapability("capturePerformance", "true");
			sdCapabilities.setCapability("extendedDebugging", "true");
			sdCapabilities.setCapability("screenResolution", "1920x1200");
		}else {
			sdCapabilities.acceptInsecureCerts();
			sdCapabilities.setAcceptInsecureCerts(true);
			sdCapabilities.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
			sdCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.EAGER);
			sdCapabilities.setCapability("screenResolution", "1920x1440");
		}
		
		return sdCapabilities;
	}
	
	/**
	 * This method is used to setup custom remote Chrome options
	 * 
	 * @return
	 */
	public static ChromeOptions getRemoteChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setCapability("sauce:options", capability);
		chromeOptions.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.setCapability("acceptInsecureCerts", true);
		chromeOptions.setCapability("platformName", platform);
		chromeOptions.setCapability("browserVersion", remoteVersion);
		return chromeOptions;
	}

	/**
	 * This method is used to setup custom remote firefox options
	 * 
	 * @return
	 */
	public static FirefoxOptions getRemoteFireFoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setCapability("sauce:options", capability);
		firefoxOptions.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		firefoxOptions.setAcceptInsecureCerts(true);
		firefoxOptions.setCapability("acceptInsecureCerts", true);
		firefoxOptions.setCapability("platformName", platform);
		firefoxOptions.setCapability("browserVersion", remoteVersion);
		return firefoxOptions;
	}

	/**
	 * This method is used to setup custom remote Edge options
	 * 
	 * @return
	 */
	public static EdgeOptions getRemoteEdgeOptions() {
		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.setCapability("sauce:options", capability);
		edgeOptions.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		edgeOptions.setCapability("acceptInsecureCerts", true);
		edgeOptions.setCapability("platformName", platform);
		edgeOptions.setCapability("browserVersion", remoteVersion);
		return edgeOptions;

	}

	/**
	 * This method is used to setup custom remote Safari options
	 * 
	 * @return
	 */
	public static SafariOptions getRemoteSafariOptions() {
		SafariOptions safariOptions = new SafariOptions();
		safariOptions.setCapability("sauce:options", capability);
		safariOptions.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		safariOptions.setCapability("platformName", "macOS 11");
		safariOptions.setCapability("browserVersion", "latest");
		return safariOptions;
	}
	
	/**
	 * This method is used to setup custom local Chrome options
	 * 
	 * @return
	 */
	public static ChromeOptions getLocalChromeOptions() {
		System.setProperty("webdriver.chrome.driver", chrome_path);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		return chromeOptions;
	}

	/**
	 * This method is used to setup custom local Chrome options [Headless]
	 * 
	 * @return
	 */
	public static ChromeOptions getLocalChromeHeadLessOptions() {
		System.setProperty("webdriver.chrome.driver", chrome_path);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		return chromeOptions;
	}

	/**
	 * This method is used to setup custom local Edge options
	 * 
	 * @return
	 */
	public static EdgeOptions getLocalEdgeOptions() {
		System.setProperty("webdriver.edge.driver", edgeDriverPath);
		return new EdgeOptions();
	}
	
	/**
	 * Here the Json file with the previous scanning result will be downloaded and
	 * processed
	 * 
	 * @param logger
	 */
	protected static void getComponentUrl() {

		JSONParser json = new JSONParser();
		try {

			JSONObject obj = (JSONObject) json
					.parse(FileUploader.downloadFile("COMPONENT-URL-LIST-" + Environment.trim() + ".json"));

			for (Object key : obj.keySet()) {
				componentData.put(key.toString(), obj.get(key).toString().replace("\\", ""));
			}

		} catch (IOException | ParseException e) {
			logger.info("Unable to load/parse Json file for Component URLs.");
			e.printStackTrace();
			logger.info("System is now exiting !!");
			System.exit(0);
		}

	}		
}