package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.utils.ExceptionUtil;
import com.google.common.base.Throwables;

import utils.ExecutionTImeCalculator;
import utils.FileUploader;
import utils.LoggerLog4j;
import utils.Logo;

/**
 * @author amohan31
 *
 */
public class Base extends LoggerLog4j {
	public static HashMap<String, String> xpathList = new HashMap<>();
	public static ConcurrentHashMap<String, WebDriver> LATEST_DRIVER_POOL = new ConcurrentHashMap<>();
	static String parentTunnel = "optumtest";
	static String tunnelIdentifier = "Optum-Prd";
	public static String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	public static String serverIP = "http://127.7.7.7:5353/";
	protected static int MAX_DURATION = 2000;
	protected static int SAUCE_SESSION_TIMEOUT = 6000;
	public static DesiredCapabilities capability;
	public static String browserName;
	private static final Properties componentProperties = new Properties();
	public static ConcurrentHashMap<String, String> authors;
	public static ConcurrentHashMap<String, String> tags;
	public static ConcurrentHashMap<String, WebDriver> driverMap;
	public static ConcurrentHashMap<String, String> testURLS;
	protected static Assertion hardAssert;
	protected static String localBrowser;
	protected static Boolean isRegression;
	protected static String[] url;
	protected static String clientName;
	protected static String AUT;
	protected static String Executor;
	protected static String Environment;
	protected static SoftAssert softAssert;
	protected static Actions action;
	public static String tag;
	public static String author;
	protected static String browser;
	public static String IeDriverPath;
	protected static String chrome_path;
	protected static String firefox_path;
	protected static String remoteBrowser;
	protected static String remoteVersion;
	protected static String edgeDriverPath;
	protected static String sauceAccessKey;
	protected static String sauceUserName;
	protected static String remoteExecution;
	protected static String platform;
	public static String startTime;
	protected static boolean upload;
	public static ConcurrentHashMap<String, String> componentData = new ConcurrentHashMap<String, String>();
	public static String qaHandleAttribute = "data-qahandle";
	public static String qaHandleLocator = "//*[@data-qahandle]";
	public static ThreadLocal<ArrayList<String>> urlUnderTest = new ThreadLocal<>();
	public static ThreadLocal<ArrayList<String>> customTestLogs = new ThreadLocal<>();
	public static ThreadLocal<EdgeOptions> tEdgeOptions = new ThreadLocal<>();
	private static ThreadLocal<WebDriverWait> waitPool = new ThreadLocal<WebDriverWait>();
	private static ThreadLocal<String> classNameSession = new ThreadLocal<>();
	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	protected static ThreadLocal<ChromeOptions> tChromeOptions = new ThreadLocal<>();
	protected static ThreadLocal<InternetExplorerOptions> tIeOptions = new ThreadLocal<>();
	protected static ThreadLocal<FirefoxOptions> tFirefoxOptions = new ThreadLocal<>();
	protected static ThreadLocal<SafariOptions> tSafariOptions = new ThreadLocal<>();
	private static ThreadLocal<WebElement> elementPool = new ThreadLocal<WebElement>();
	private static ThreadLocal<WebDriver> elementManipulationDriverPool = new ThreadLocal<WebDriver>();
	private static ThreadLocal<Select> selectByOptionPool = new ThreadLocal<Select>();
	private static ThreadLocal<String> elementNamePool = new ThreadLocal<String>();
	private static ThreadLocal<Logger> methodLoggerPool = new ThreadLocal<Logger>();
	private static ThreadLocal<Logger> verifyElementLoggerPool = new ThreadLocal<Logger>();
	private static ThreadLocal<Integer> optionIndexPool = new ThreadLocal<Integer>();
	private static ThreadLocal<Boolean> statusPool = new ThreadLocal<Boolean>();
	private static ThreadLocal<Actions> actionPool = new ThreadLocal<Actions>();
	private static ThreadLocal<WebElement> moveMouseElementPool = new ThreadLocal<WebElement>();
	private static ThreadLocal<String> domainPool = new ThreadLocal<>();
	private static ThreadLocal<URI> urlPoolForDomain = new ThreadLocal<>();
	public static ThreadLocal<HashMap<String, Boolean>> skipConditionMapPool = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> urlsPool = new ThreadLocal<>();
	static ThreadLocal<String> expectedDomainPool = new ThreadLocal<>();
	static ThreadLocal<String> expUrlPool = new ThreadLocal<>();
	static ThreadLocal<String> currentWindowHandlePool = new ThreadLocal<>();
	static ThreadLocal<String> errorStackPool = new ThreadLocal<>();

	private static Properties property;

	public static void fetchSession(@SuppressWarnings("rawtypes") Class clazz) {

		DriverSessionHandler obj = new DriverSessionHandler(clazz);
		Thread t = new Thread(obj);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void initialize() throws InterruptedException {
//		 System.setProperty("scan", "true");
		// System.setProperty("upload", "true");
//		 System.setProperty("defaultExecution", "false");
		startTime = ExecutionTImeCalculator.getCurrentTime();
		authors = new ConcurrentHashMap<>();
		tags = new ConcurrentHashMap<>();
		driverMap = new ConcurrentHashMap<String, WebDriver>();
		testURLS = new ConcurrentHashMap<String, String>();
		int logoCount = 0;
		System.setProperty("logFilename", "./logs");
		System.setProperty("org.freemarker.loggerLibrary", "none");

		logger = LoggerLog4j.startTestCase(Base.class);
		if (logoCount == 0) {
			new Logo();
			logger.info("\n\n\n\n \t\t\t\t\tFresh Suite Execution");
			logoCount++;
		}

		property = new Properties();

		try {

//			 property.load(new FileInputStream("./src/test/java/runner/Config-stg.properties"));
			property.load(new FileInputStream(System.getProperty("config-file")));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LoggerLog4j.logger.fatal("Configurations file not found");
			System.exit(0);
		} catch (IOException e) {

			e.printStackTrace();
			logger.fatal("Unable to load cofigurations");
			System.exit(0);
		}
		url = property.getProperty("URL_FOR_AUT").split(";");

		clientName = property.getProperty("client");
		setBrowser();

		remoteVersion = property.getProperty("remoteVersion");

		if (System.getProperty("remoteExecution") == null) {
			remoteExecution = property.getProperty("remoteExecution");
		} else {
			remoteExecution = System.getProperty("remoteExecution");
		}

		if (System.getProperty("sauceUsername") == null) {
			sauceUserName = property.getProperty("sauceUserName");
		} else {
			sauceUserName = System.getProperty("sauceUsername");
		}

		if (System.getProperty("sauceKey") == null) {
			sauceAccessKey = property.getProperty("sauceAccessKey");
		} else {
			sauceAccessKey = System.getProperty("sauceKey");
		}
		if (System.getProperty("upload") == null || !(System.getProperty("upload").trim().equalsIgnoreCase("true"))) {
			upload = false;
		} else if (System.getProperty("upload").equalsIgnoreCase("true")) {
			upload = true;
		}
		if (System.getProperty("regression") == null
				|| !(System.getProperty("regression").trim().equalsIgnoreCase("true"))) {
			System.setProperty("regression", "false");
			isRegression = false;
		} else if (System.getProperty("regression").trim().equalsIgnoreCase("true")) {
			isRegression = true;
		}

		platform = property.getProperty("platform");
		chrome_path = property.getProperty("ChromeDriverPath");
		firefox_path = property.getProperty("FirefoxDriverPath");
		IeDriverPath = property.getProperty("IeDriverPath");
		edgeDriverPath = property.getProperty("edgeDriverPath");
		AUT = property.getProperty("AUT");
		Executor = property.getProperty("Executor");
		Environment = property.getProperty("Environment").trim();
		logger.info("Current Environment is : " + Environment);
		if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("dataFile",
					System.getProperty("user.dir") + "\\src\\main\\resources\\ComponentTestData.xlsx");
		} else {
			System.setProperty("dataFile",
					System.getProperty("user.dir") + "/src/main/resources/ComponentTestData.xlsx");
		}
		Scanner xpathProperties = null;

		try {
			xpathProperties = new Scanner(new File("src/main/resources/XpathList.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (System.getProperty("scanWith") == null || System.getProperty("scanWith").toLowerCase().contains("xpath")) {
			while (xpathProperties.hasNextLine()) {
				componentData.put(xpathProperties.nextLine(), "-");
			}
		} else {

			try {
				componentProperties.load(new FileInputStream("src/main/resources/ComponentList.properties"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Set<Object> componentList = componentProperties.keySet();
			for (Object key : componentList) {

				if (!componentProperties.getProperty(key.toString()).isEmpty()
						|| componentProperties.getProperty(key.toString()) != null) {
					componentData.put(key.toString().trim(), componentProperties.getProperty(key.toString()));
				} else {
					componentData.put(key.toString().trim(), "-");
				}

			}
		}

		capability = DriverSessionHandler.getCapabilities();

		if (System.getProperty("scan") == null || !(System.getProperty("scan").trim().equalsIgnoreCase("true"))) {

			if (System.getProperty("defaultExecution") == null
					|| !(System.getProperty("defaultExecution").trim().equalsIgnoreCase("true"))) {
				logger.info(
						"User has not opted for scanning. Hence, we'll pick from pre-defined list of component available at "
								+ serverIP);
				logger.info("Downloading the updated file from the server..");
				getComponentUrl(logger);
			} else {
				logger.info("Execution with static URLs is starting...");
			}

		} else if (System.getProperty("scan").equalsIgnoreCase("true")) {

			for (String url : Base.url) {
				new CrawlerService(url);

			}
			for (Thread t : CrawlerService.threadPool) {
				t.join();
			}
			Path p3 = Paths.get(System.getProperty("user.dir") + "/src/main/resources/COMPONENT-URL-LIST-"
					+ Environment.trim() + ".json");
			try {
				FileWriter a = new FileWriter(new File(p3.toString()));
				Set<String> dataKey = componentData.keySet();
				a.write("{\n");
				Iterator<String> it = dataKey.iterator();
				int i = 0;
				while (it.hasNext()) {

					String key = it.next();
					if (i < dataKey.size() - 1) {
						a.write("\"" + key + "\": [" + componentData.get(key) + "],\n");
					}

					else if (i == dataKey.size() - 1) {
						a.write("\"" + key + "\": [" + componentData.get(key) + "]\n");
					}
					i++;
				}
				a.write("}");
				a.flush();
				logger.info("URL List is ready to be uploaded!");

				logger.info("Uploading the fresh url list for " + Environment);
				Thread.sleep(2000);
				if (upload == true) {
					FileUploader.uploadUrlList();
				} else {
					// FileReader b = new FileReader(p3.toFile());
					List<String> lines = Files.readAllLines(p3, StandardCharsets.UTF_8);
					logger.info("Not uploading the Scanning results as the 'Upload function is disabled'");
					logger.info("URLS going to be used are:");
					for (String jLines : lines) {
						logger.info(jLines);
					}
				}
				a.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		logger.info("INITIALIZED AND READY TO GO!!  \\\\(^ ^)//");

	}

	public WebDriver getWebdriverInstance(String className) {
		synchronized (className) {
			loggerPool.set(LoggerLog4j.startTestCase(Base.class));
			classNameSession.set(className);
		}
		softAssert = new SoftAssert();
		hardAssert = new Assertion();

		if (remoteExecution.equalsIgnoreCase("true") && remoteExecution != null) {
			String URL = "https://sso-optum-" + sauceUserName + ":" + sauceAccessKey
					+ "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
			// String URL = "http://localhost:80/wd/hub";
			browser = remoteBrowser;
			if (remoteBrowser != null || sauceAccessKey != null || sauceUserName != null || remoteVersion != null) {
				try {
					// DesiredCapabilities cap;
					switch (remoteBrowser) {
					case "-c":

						synchronized (className) {
							tChromeOptions.set(new ChromeOptions());
						}
						tChromeOptions.get().setCapability("sauce:options", capability);
						tChromeOptions.get().setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
						tChromeOptions.get().setAcceptInsecureCerts(true);
						tChromeOptions.get().setCapability("acceptInsecureCerts", true);
						tChromeOptions.get().setCapability("platformName", platform);
						tChromeOptions.get().setCapability("browserVersion", remoteVersion);
						// tChromeOptions.get().setPageLoadStrategy(PageLoadStrategy.EAGER);

						// cap = new DesiredCapabilities().chrome();
						// cap.setPlatform(Platform.WINDOWS);
						synchronized (className) {
							tDriver.set(new RemoteWebDriver(new java.net.URL(URL), tChromeOptions.get()));
						}
						break;
					case "-f":

						synchronized (className) {
							tFirefoxOptions.set(new FirefoxOptions());
						}
						tFirefoxOptions.get().setCapability("sauce:options", capability);
						tFirefoxOptions.get().setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
						tFirefoxOptions.get().setAcceptInsecureCerts(true);
						tFirefoxOptions.get().setCapability("acceptInsecureCerts", true);
						tFirefoxOptions.get().setCapability("platformName", platform);
						tFirefoxOptions.get().setCapability("browserVersion", remoteVersion);
						// tFirefoxOptions.get().setPageLoadStrategy(PageLoadStrategy.EAGER);
						synchronized (className) {
							tDriver.set(new RemoteWebDriver(new java.net.URL(URL), tFirefoxOptions.get()));
						}
						break;

					case "-e":

						synchronized (className) {
							tEdgeOptions.set(new EdgeOptions());
						}
						tEdgeOptions.get().setCapability("sauce:options", capability);
						tEdgeOptions.get().setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
						tEdgeOptions.get().setCapability("acceptInsecureCerts", true);
						tEdgeOptions.get().setCapability("platformName", platform);
						tEdgeOptions.get().setCapability("browserVersion", remoteVersion);
						// tEdgeOptions.get().setCapability(CapabilityType.PAGE_LOAD_STRATEGY,
						// PageLoadStrategy.EAGER);

						// cap = new DesiredCapabilities().edge();
						// cap.setPlatform(Platform.WINDOWS);
						synchronized (className) {
							tDriver.set(new RemoteWebDriver(new java.net.URL(URL), tEdgeOptions.get()));
						}
						break;

					case "-s":

						synchronized (className) {
							tSafariOptions.set(new SafariOptions());
						}
						tSafariOptions.get().setCapability("sauce:options", capability);
						tSafariOptions.get().setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
						tSafariOptions.get().setCapability("platformName", "macOS 11");
						tSafariOptions.get().setCapability("browserVersion", "14");
						synchronized (className) {
							tDriver.set(new RemoteWebDriver(new java.net.URL(URL), tSafariOptions.get()));
						}
						break;

					default:
						loggerPool.get().info("Remote Browser Configuration is invalid and now exiting!!");
						System.exit(0);
					}
				} catch (MalformedURLException e) {
					loggerPool.get().fatal("Remote Configuration Error!");
					System.exit(0);
				}

			}

		} else {
			browser = localBrowser;
			switch (localBrowser) {

			case "-c":
				System.setProperty("webdriver.chrome.driver", chrome_path);
				tChromeOptions.set(new ChromeOptions());
				tChromeOptions.get().setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR,
						ElementScrollBehavior.BOTTOM);
				loggerPool.get().info(
						"Now just sit back and relax. \nExecution will take place via Google Chrome and you'll be informed once everything is done..");

				tDriver.set(new ChromeDriver(tChromeOptions.get()));
				break;

			case "-hc":
				System.setProperty("webdriver.chrome.driver", chrome_path);
				tChromeOptions.set(new ChromeOptions());

				tChromeOptions.get().addArguments("--headless");
				loggerPool.get().info(
						"Now just sit back and relax. \nExecution will take place via Google Chrome [HEADLESS] and you'll be informed once everything is done..");

				tDriver.set(new ChromeDriver(tChromeOptions.get()));
				break;

			case "-e":
				System.setProperty("webdriver.edge.driver", edgeDriverPath);
				tEdgeOptions.set(new EdgeOptions());

				loggerPool.get().info(
						"Now just sit back and relax. \nExecution will take place via Edge and you'll be informed once everything is done..");

				tDriver.set(new EdgeDriver());
				break;

			case "-ie":
				loggerPool.get().info(
						"Now just sit back and relax. \nWe'll use IE11 for test excution and you'll be informed once everything is done.");
				System.setProperty("webdriver.ie.driver", IeDriverPath);
				tDriver.set(new InternetExplorerDriver());
				break;
			default:
				loggerPool.get().info("Local Browser Configuration is invalid and now exiting!!");
				System.exit(0);
			}

		}

		tDriver.get().manage().window().maximize();
		tDriver.get().manage().deleteAllCookies();
		tDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		tDriver.get().manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);

		loggerPool.get()
				.info("Browser session for class '" + classNameSession.get() + "' => " + tDriver.get().toString());
		return tDriver.get();
	}

	ThreadLocal<Integer> waitTimeout = new ThreadLocal<>();

	@SuppressWarnings("static-access")
	public void pleaseWait(Integer durationInSeconds, Logger logger) {
		synchronized (durationInSeconds) {
			synchronized (logger) {
				loggerPool.set(logger);
				waitTimeout.set(durationInSeconds);
			}
		}
		loggerPool.get().info("Forced wait");
		try {
			Thread.currentThread().sleep(waitTimeout.get() * 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void focusElement(WebDriver driver, WebElement element) {
		synchronized (driver) {
			synchronized (element) {
				elementManipulationDriverPool.set(driver);
				elementPool.set(element);
			}
		}

		((JavascriptExecutor) elementManipulationDriverPool.get())
				.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", elementPool.get());
	}

	public void selectByOptionName(Logger logger, WebElement element, String optionTextToSelect) {

		synchronized (logger) {
			synchronized (element) {
				synchronized (optionTextToSelect) {

					methodLoggerPool.set(logger);
					elementPool.set(element);
					elementNamePool.set(optionTextToSelect);
					selectByOptionPool.set(new Select(element));
				}
			}
		}

		selectByOptionPool.get().selectByVisibleText(elementNamePool.get());
		methodLoggerPool.get()
				.info("Selected option '" + elementNamePool.get() + "' from dropdown  " + elementPool.get().getText());
	}

	public String selectByOptionIndex(WebElement element, Integer optionIndex, Logger logger) {
		synchronized (element) {
			synchronized (optionIndex) {
				synchronized (logger) {

					selectByOptionPool.set(new Select(element));
					optionIndexPool.set(optionIndex);
					elementPool.set(element);
					loggerPool.set(logger);
				}
			}
		}

		selectByOptionPool.get().selectByIndex(optionIndexPool.get());
		loggerPool.get()
				.info("Selected option '" + selectByOptionPool.get().getOptions().get(optionIndexPool.get()).getText()
						+ "' from dropdown  \n" + elementPool.get().getAttribute("innterText"));
		return elementPool.get().getAttribute("innterText");
	}

	public boolean verifyElementExists(Logger logger, WebElement element, String elementName) {
		synchronized (logger) {
			synchronized (element) {
				synchronized (elementName) {

					statusPool.set(false);
					elementPool.set(element);
					elementNamePool.set(elementName);
					verifyElementLoggerPool.set(logger);
				}
			}
		}
		try {
			if (elementPool.get().isDisplayed()) {
				verifyElementLoggerPool.get().info("Element '" + elementNamePool.get() + "' exists");
				statusPool.set(true);
			} else {
				verifyElementLoggerPool.get().error("Element '" + elementNamePool.get() + "' exists but not visible!");
				statusPool.set(false);
			}
		} catch (Exception e) {
			verifyElementLoggerPool.get().info(Throwables.getStackTraceAsString(e));

			verifyElementLoggerPool.get().error("Element " + elementNamePool.get() + " doesn't exists!");

			statusPool.set(false);
		}

		return statusPool.get();

	}

	public synchronized boolean verifyElementText(WebElement element, String expectedText) {
		try {
			String actualText = element.getText();
			if (actualText.trim().equalsIgnoreCase(actualText))
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public synchronized boolean verifyAttributeValue(WebElement element, String attributeName, String expectedText) {
		boolean status = false;
		try {
			logger.info("Verifying element attribute " + attributeName);
			if (element.getAttribute(attributeName) != null) {
				if (element.getAttribute(attributeName).trim().equalsIgnoreCase(expectedText)) {
					status = true;

					logger.info("Expected and Actual attribute values are similar");
				} else {
					logger.error("Expected and Actual values are '" + element.getAttribute(attributeName) + "' and '"
							+ expectedText + "' respectively");
					status = false;
				}
			}

			else {

				logger.error("Css Attribute '" + attributeName + "' does not exists");
				status = false;
			}
		} catch (Exception e) {
			status = false;
			logger.fatal("Some thing went wrong while reading attribute: " + attributeName);
			e.printStackTrace();
		}
		return status;
	}

	public synchronized boolean verifyCSSValue(WebElement element, String cssAttributeName, String expectedValue) {
		boolean status = false;
		try {

			logger.info("Verifying element's css attribute " + cssAttributeName);
			if (element.getCssValue(cssAttributeName) != null) {

				if (element.getCssValue(cssAttributeName).trim().contains(expectedValue)) {
					status = true;
					logger.info("Expected and Actual css values are similar ");
				} else {
					logger.error("Expected and Actual values are '" + element.getAttribute(cssAttributeName) + "' and '"
							+ expectedValue + "' respectively");
					status = false;
				}
			} else {
				logger.error("Css Attribute '" + cssAttributeName + "' does not exists");
				status = false;
			}
		} catch (Exception e) {
			status = false;
			logger.fatal("Some thing went wrong while reading attribute: " + cssAttributeName);
		}
		return status;
	}

	// private static ThreadLocal<WebDriver> moveMouseElementPool = new
	// ThreadLocal<WebElement>();
	public WebElement moveMouseOnToElement(WebDriver mydriver, WebElement element) {

		synchronized (mydriver) {
			synchronized (element) {
				elementManipulationDriverPool.set(mydriver);
				actionPool.set(new Actions(elementManipulationDriverPool.get()));
				moveMouseElementPool.set(element);
			}
		}
		try {
			actionPool.get().moveToElement(moveMouseElementPool.get()).perform();
			focusElement(elementManipulationDriverPool.get(), moveMouseElementPool.get());// build().perform();
		} catch (Exception e) {

			logger.fatal("Unable to hover mouse cursor over: " + moveMouseElementPool.get());
		}
		return moveMouseElementPool.get();
	}

	public synchronized void scrollPageByDimensions(WebDriver mydriver, int i, int j) {
		JavascriptExecutor js = (JavascriptExecutor) mydriver;
		js.executeScript("window.scrollBy(" + i + "," + j + ")");
	}

	public WebElement scrollToElement(WebDriver mydriver, WebElement element, Logger logger) {
		synchronized (mydriver) {
			synchronized (element) {
				synchronized (pageLoadLoggerPool) {
					pageLoadLoggerPool.set(logger);
					elementManipulationDriverPool.set(mydriver);
					elementPool.set(element);
				}
			}
		}
		// arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})
		((JavascriptExecutor) elementManipulationDriverPool.get())
				.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", elementPool.get());

		getVisibility(elementManipulationDriverPool.get(), elementPool.get(), 10);

		pleaseWait(4, pageLoadLoggerPool.get());
		focusElement(elementManipulationDriverPool.get(), elementPool.get());
		return elementPool.get();

	}

	public void scrollToElementWithoutWait(WebDriver mydriver, WebElement element) {
		synchronized (mydriver) {
			synchronized (element) {
				elementPool.set(element);
				elementManipulationDriverPool.set(mydriver);
			}
		}

		((JavascriptExecutor) elementManipulationDriverPool.get()).executeScript("arguments[0].scrollIntoView(true);",
				elementPool.get());
		focusElement(elementManipulationDriverPool.get(), elementPool.get());
	}

	public WebElement getVisibility(WebDriver mydriver, WebElement e, Integer timeout) {
		synchronized (mydriver) {
			synchronized (e) {
				synchronized (timeout) {

					elementManipulationDriverPool.set(mydriver);
					elementPool.set(e);
					waitPool.set(new WebDriverWait(elementManipulationDriverPool.get(), 30));
				}
			}
		}
		return waitPool.get().until(ExpectedConditions.visibilityOf(elementPool.get()));

	}

	private static ThreadLocal<String> xpathPool = new ThreadLocal<>();
	private static ThreadLocal<String> xpathManipulation = new ThreadLocal<>();

	public WebElement getPresence(WebDriver mydriver, String xpath, Integer timeout) {
		synchronized (mydriver) {
			synchronized (xpath) {
				synchronized (timeout) {
					elementManipulationDriverPool.set(mydriver);

					xpathManipulation.set(xpath);

					if (xpathManipulation.get().contains("']]") || xpathManipulation.get().contains("\"]]")) {
						xpathPool.set(xpathManipulation.get().substring(0, xpathManipulation.get().length() - 1));
					} else if (xpathManipulation.get().contains("]]")
							|| !xpathManipulation.get()
									.substring(xpathManipulation.get().length() - 2, xpathManipulation.get().length())
									.contains("']")
							|| !xpathManipulation.get()
									.substring(xpathManipulation.get().length() - 2, xpathManipulation.get().length())
									.contains("\"]")
							|| !!xpathManipulation.get()
									.substring(xpathManipulation.get().length() - 2, xpathManipulation.get().length())
									.contains(")")) {
						xpathPool.set(xpathManipulation.get().substring(0, xpathManipulation.get().length() - 1));
					} else {
						xpathPool.set(xpathManipulation.get());
					}
					waitPool.set(new WebDriverWait(elementManipulationDriverPool.get(), timeout));
				}
			}
		}
		return waitPool.get().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathPool.get())));

	}

	public synchronized static String getFontSize(WebElement element) {
		String fontSize = element.getCssValue("font-size");
		return fontSize;
	}

	public synchronized static String getHexFontColor(WebElement element) {
		String rgbaValue = element.getCssValue("background-color");
		String[] hexValue = rgbaValue.replace("rgba(", "").replace(")", "").split(",");

		hexValue[0] = hexValue[0].trim();
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);

		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		return actualColor.toUpperCase();
	}

	public synchronized static void setTagForTestClass(String tag, String author, String website, String ClassName) {

		// testURLS.put(ClassName, website);

		tags.put(ClassName, tag);

		authors.put(ClassName, author);

	}

	public synchronized String getDomainNameSubset(String url) {
		try {
			urlPoolForDomain.set(new URI(url));

			domainPool.set(urlPoolForDomain.get().getHost().substring(0, 5));
		} catch (URISyntaxException e) {
			domainPool.set("Invalid Domain");
			e.printStackTrace();
		}

		return domainPool.get();
	}

	public synchronized static String getDomainName(String url) {
		try {
			urlPoolForDomain.set(new URI(url));
			domainPool.set(urlPoolForDomain.get().getHost());
		} catch (URISyntaxException e) {
			domainPool.set("Invalid Domain for => " + urlPoolForDomain.get());
			e.printStackTrace();
			return null;
		}
		return domainPool.get();
	}

	public HashMap<String, Boolean> skipNonExistingComponent(ArrayList<String> urls) {
		synchronized (urls) {
			urlsPool.set(urls);
			skipConditionMapPool.set(new HashMap<>());
		}
		if (urlsPool.get().size() == 0 || urlsPool.get().toString().trim().isEmpty()) {
			skipConditionMapPool.set(null);
			throw new SkipException("Can't find Component url");
		} else {
			urlsPool.get().stream().forEach(a -> {
				skipConditionMapPool.get().put(a, false);
			});
			/*
			 * for(String url : urlsPool.get()) {
			 * 
			 * skipConditionMapPool.get().put(url, false); }
			 */
		}
		return skipConditionMapPool.get();
	}

	public synchronized static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	@Deprecated
	public static boolean isDownloaded(String filename, WebDriver mydriver) {
		System.setProperty("java.io.tmpdir", "./Dowloads");
		String tmpFolderPath = System.getProperty("java.io.tmpdir");
		String expectedFileName = filename;
		boolean status;
		File file = new File(tmpFolderPath + expectedFileName);
		if (file.exists()) {
			status = true;
			file.delete();
			WebDriverWait wait = new WebDriverWait(mydriver, 30);
			wait.until((ExpectedCondition<Boolean>) webDriver -> file.exists());
		} else {
			status = false;

		}
		return status;
	}

	/// only to use when using unpublished urls for test purpose
	public synchronized void loginTestEnv(WebDriver mydriver, String userName, String password, Logger logger) {
		try {
			scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@aria-label=\"User name\"]")), logger);
			mydriver.findElement(By.xpath("//*[@aria-label=\"User name\"]")).sendKeys(userName);
			mydriver.findElement(By.xpath("//*[@aria-label=\"Password\"]")).sendKeys(password);
			mydriver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			mydriver.navigate().refresh();

		}
		logger.info("User Is logged into Test environment");
	}

	public synchronized static String capitalizeWhiteString(String givenString) {

		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	public synchronized static String capitalizeHyphenString(String givenString) {

		String[] arr = givenString.split("-");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	public static synchronized void switchToNextTab(WebDriver mydriver, Logger logger, String currentWindowHandle) {
		Set<String> tabs = mydriver.getWindowHandles();
		Iterator<String> it = tabs.iterator();

		while (it.hasNext()) {
			String a = it.next();
			if (!a.equals(currentWindowHandle)) {
				logger.info("Current tab title => '" + mydriver.getTitle() + "'");
				mydriver.switchTo().window(a);
				logger.info("Switch to next tab with title => '" + mydriver.getTitle() + "'");

				break;
			}
		}
	}

	static ThreadLocal<String> stringManipulationPool = new ThreadLocal<>();

	public void assertRedirection(WebDriver mydriver, Logger logger, String testUrlDomain, String expHyperLink,
			String currentWindowHandle) {
		synchronized (mydriver) {
			synchronized (logger) {
				synchronized (testUrlDomain) {
					synchronized (expHyperLink) {
						synchronized (currentWindowHandle) {
							expectedDomainPool.set(testUrlDomain);
							expUrlPool.set(expHyperLink);
							elementManipulationDriverPool.set(mydriver);
							verifyElementLoggerPool.set(logger);
							currentWindowHandlePool.set(currentWindowHandle);
							stringManipulationPool.set(expUrlPool.get() + "dummy");
						}
					}
				}
			}
		}
		try {

			if ((!expUrlPool.get().contains("pdf"))
					&& expectedDomainPool.get().equalsIgnoreCase(getDomainName(expUrlPool.get()))) {
				pleaseWait(3, verifyElementLoggerPool.get());
				verifyElementLoggerPool.get().info("Expected Url path: "
						+ expUrlPool.get().split(".//")[1]
								.split("/")[expUrlPool.get().split(".//")[1].split("/").length - 1].split(".html")[0]
						+ "\nActual Url path: "
						+ elementManipulationDriverPool.get().getCurrentUrl()
								.split("/")[elementManipulationDriverPool.get().getCurrentUrl().split("/").length - 1]
										.split(".html")[0]);

				hardAssert.assertEquals(
						elementManipulationDriverPool.get().getCurrentUrl()
								.split("/")[elementManipulationDriverPool.get().getCurrentUrl().split("/").length - 1]
										.split(".html")[0],
						expUrlPool.get().split(".//")[1].split("/")[expUrlPool.get().split(".//")[1].split("/").length
								- 1].split(".html")[0]);
				verifyElementLoggerPool.get().info("Assertion passes for same tab!");
			} else if (expectedDomainPool.get().contains(".pdf")
					|| !expectedDomainPool.get().equalsIgnoreCase(getDomainName(expUrlPool.get()))) {

				// String a = expUrlPool.get().split(getDomainName(expUrlPool.get()))[1];
				// boolean b =
				// expUrlPool.get().split(getDomainName(expUrlPool.get()))[1].substring(0,1)=="/";
				// System.out.println(" a ==> " + a);
				// System.out.println(" b ==> " + b);

				if ((expUrlPool.get().split(getDomainName(expUrlPool.get()))[1].isEmpty()
						|| expUrlPool.get().split(getDomainName(expUrlPool.get()))[1].equals("/"))
						&& stringManipulationPool.get().split(getDomainName(stringManipulationPool.get()))[1]
								.split("/")[1].equals("dummy")) {

					switchToNextTab(elementManipulationDriverPool.get(), verifyElementLoggerPool.get(),
							currentWindowHandlePool.get());
					pleaseWait(3, verifyElementLoggerPool.get());
					verifyElementLoggerPool.get().info("Expected Url : " + getDomainName(expUrlPool.get())
							+ "\nActual Url: " + getDomainName(elementManipulationDriverPool.get().getCurrentUrl()));

					hardAssert.assertEquals(getDomainName(elementManipulationDriverPool.get().getCurrentUrl()),
							getDomainName(expUrlPool.get()));
					verifyElementLoggerPool.get().info("Assertion passes for different tab [Homelink]!");
					elementManipulationDriverPool.get().close();
					elementManipulationDriverPool.get().switchTo().window(currentWindowHandlePool.get());
					System.out.println(elementManipulationDriverPool.get().getTitle());

				} else {
					pleaseWait(3, verifyElementLoggerPool.get());
					switchToNextTab(elementManipulationDriverPool.get(), verifyElementLoggerPool.get(),
							currentWindowHandlePool.get());
					pleaseWait(3, verifyElementLoggerPool.get());
					verifyElementLoggerPool.get().info("Expected Url path: "
							+ expUrlPool.get().split(".//")[1].split(
									"/")[expUrlPool.get().split(".//")[1].split("/").length - 1].split(".html")[0]
							+ "\nActual Url path: "
							+ elementManipulationDriverPool.get().getCurrentUrl().split(
									"/")[elementManipulationDriverPool.get().getCurrentUrl().split("/").length - 1]
											.split(".html")[0]);

					hardAssert
							.assertEquals(
									elementManipulationDriverPool.get().getCurrentUrl()
											.split("/")[elementManipulationDriverPool.get().getCurrentUrl()
													.split("/").length - 1].split(".html")[0],
									expUrlPool.get().split(".//")[1]
											.split("/")[expUrlPool.get().split(".//")[1].split("/").length - 1]
													.split(".html")[0]);
					verifyElementLoggerPool.get().info("Assertion passes for different tab!");
					elementManipulationDriverPool.get().close();
					elementManipulationDriverPool.get().switchTo().window(currentWindowHandlePool.get());
					System.out.println(elementManipulationDriverPool.get().getTitle());

				}
			}
		} catch (Exception e) {
			synchronized (this) {
				errorStackPool.set(ExceptionUtil.getStackTrace(e));
			}
			verifyElementLoggerPool.get().error(errorStackPool.get());
			switchToPreviousTab(elementManipulationDriverPool.get(), verifyElementLoggerPool.get());
		}

	}

	static ThreadLocal<String> urlPathPool = new ThreadLocal<>();

	public void assertStaticRedirection(WebDriver mydriver, Logger logger, String testUrlDomain, String expHyperLink,
			String expRelativePath, String currentWindowHandle) {
		synchronized (mydriver) {
			synchronized (logger) {
				synchronized (testUrlDomain) {
					synchronized (expHyperLink) {
						synchronized (currentWindowHandle) {
							synchronized (expRelativePath) {

								urlPathPool.set(expRelativePath);

								expectedDomainPool.set(testUrlDomain);
								expUrlPool.set(expHyperLink);
								elementManipulationDriverPool.set(mydriver);
								verifyElementLoggerPool.set(logger);
								currentWindowHandlePool.set(currentWindowHandle);
							}
						}
					}
				}
			}
		}

		if (expectedDomainPool.get().equalsIgnoreCase(getDomainName(expUrlPool.get()))) {
			verifyElementLoggerPool.get().info("Expected Url path: " + expUrlPool.get().split(".html")[0]
					+ "\nActual Url path: " + elementManipulationDriverPool.get().getCurrentUrl().split(".html")[0]);
			hardAssert.assertTrue(
					elementManipulationDriverPool.get().getCurrentUrl().split(".html")[0].contains(urlPathPool.get()));
			verifyElementLoggerPool.get().info("Assertion passes for same tab!");
		} else {
			switchToNextTab(elementManipulationDriverPool.get(), verifyElementLoggerPool.get(),
					currentWindowHandlePool.get());
			pleaseWait(3, verifyElementLoggerPool.get());
			verifyElementLoggerPool.get().info("Expected Url path: " + expUrlPool.get().split(".html")[0]
					+ "\nActual Url path: " + elementManipulationDriverPool.get().getCurrentUrl().split(".html")[0]);
			hardAssert.assertTrue(
					elementManipulationDriverPool.get().getCurrentUrl().split(".html")[0].contains(urlPathPool.get()));
			verifyElementLoggerPool.get().info("Assertion passes for deifferent tab!");
			elementManipulationDriverPool.get().close();
			elementManipulationDriverPool.get().switchTo().window(currentWindowHandlePool.get());
			System.out.println(elementManipulationDriverPool.get().getTitle());
		}

	}

	static ThreadLocal<WebDriver> pageLoadDriverPool = new ThreadLocal<>();
	static ThreadLocal<Integer> intTimeout = new ThreadLocal<>();

	static ThreadLocal<Logger> pageLoadLoggerPool = new ThreadLocal<>();

	public void jsWaitForPageToLoad(Integer timeOutInSeconds, WebDriver mydriver, Logger logger) {
		synchronized (timeOutInSeconds) {
			synchronized (mydriver) {
				synchronized (logger) {
					pageLoadLoggerPool.set(logger);
					pageLoadDriverPool.set(mydriver);
					intTimeout.set(timeOutInSeconds);
				}
			}
		}
		String jsCommand = "return document.readyState";

		// Validate readyState before doing any waits
		if (((JavascriptExecutor) pageLoadDriverPool.get()).executeScript(jsCommand).toString().equals("complete")) {
			return;
		}

		for (int i = 0; i < intTimeout.get(); i++) {
			pleaseWait(2, pageLoadLoggerPool.get());
			if (((JavascriptExecutor) pageLoadDriverPool.get()).executeScript(jsCommand).toString()
					.equals("complete")) {
				break;
			}
		}
	}

	public static synchronized void switchToPreviousTab(WebDriver mydriver, Logger logger) {
		Set<String> tabs = mydriver.getWindowHandles();
		Iterator<String> it = tabs.iterator();
		int i = 0;
		String firstTab = "";
		while (it.hasNext()) {

			if (i == 0) {
				firstTab = it.next();
			}

			else {
				String a = it.next();
				mydriver.switchTo().window(a).close();

			}
			i++;
		}
		mydriver.switchTo().window(firstTab);
		logger.info("Switched to first tab with title => '" + mydriver.getTitle() + "'");
		i = 0;
	}

	static ThreadLocal<WebDriver> jsePool = new ThreadLocal<>();
	static ThreadLocal<WebElement> mouseElementPool = new ThreadLocal<>();

	public void clickWithJS(WebElement element, WebDriver mydriver) {
		synchronized (element) {
			synchronized (mydriver) {
				jsePool.set(mydriver);
				mouseElementPool.set(element);
				waitPool.set(new WebDriverWait(jsePool.get(), 30));
			}
		}
		getVisibility(mydriver, mouseElementPool.get(), 30);
		waitPool.get().until(ExpectedConditions.elementToBeClickable(mouseElementPool.get()));
		((JavascriptExecutor) jsePool.get()).executeScript("arguments[0].click()", mouseElementPool.get());

	}

	public void mouseWithJS(WebElement element, WebDriver mydriver) {

		synchronized (element) {
			synchronized (mydriver) {
				jsePool.set(mydriver);
				mouseElementPool.set(element);
			}
		}
		// scrollToElement(jsePool.get(), mouseElementPool.get());
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) jsePool.get()).executeScript(mouseOverScript, mouseElementPool.get());
	}

	public static void getComponentUrl(Logger logger) {

		JSONParser json = new JSONParser();
		FileReader read = null;
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

	public synchronized static String fetchUrl(String componentName) {
		if (System.getProperty("defaultExecution") == null
				|| System.getProperty("defaultExecution").equalsIgnoreCase("false")) {
			if (componentData.containsKey(componentName)) {
				String a = componentData.get(componentName).replace("[", "").replace("]", "").replace("\"", "");
				String[] urlSet = a.split(",");
				String randomURLs = null;
				if (a != null && !a.isEmpty()) {
					int i = getRandomInteger(urlSet.length, 0);
					int j = 0;
					if (urlSet.length - 1 != 0) {
						while (j == i) {
							j = getRandomInteger(urlSet.length, 0);
						}
						randomURLs = urlSet[i] + "," + urlSet[j];
					} else {
						randomURLs = urlSet[i];
					}

					return randomURLs;
				} else
					return null;
			} else
				return null;
		} else {
			return null;
		}
	}

	public static Actions getActions(WebDriver mydriver) {
		synchronized (mydriver) {
			actionPool.set(new Actions(mydriver));
		}
		return actionPool.get();
	}

	private static void setBrowser() {
		// System.setProperty("browser", "-ie");
		if (System.getProperty("browser") == null || System.getProperty("browser").trim().isEmpty()
				|| System.getProperty("browser").trim().equalsIgnoreCase("na")) {
			localBrowser = property.getProperty("localBrowser");
			remoteBrowser = property.getProperty("remoteBrowser");
		} else if (System.getProperty("browser").trim().equalsIgnoreCase("-c")) {
			localBrowser = "-c";
			remoteBrowser = "-c";
		} else if (System.getProperty("browser").trim().equalsIgnoreCase("-f")) {
			localBrowser = "-f";
			remoteBrowser = "-f";
		} else if (System.getProperty("browser").trim().equalsIgnoreCase("-ie")) {
			localBrowser = "-ie";
			remoteBrowser = "-ie";
		} else if (System.getProperty("browser").trim().equalsIgnoreCase("-e")) {
			localBrowser = "-e";
			remoteBrowser = "-e";
		} else if (System.getProperty("browser").trim().equalsIgnoreCase("-s")) {
			localBrowser = "-s";
			remoteBrowser = "-s";
		} else {
			logger.info("Only select your browser from the available list =>\n-ie; -e; -c; -f");
			System.exit(0);
		}
	}
}
