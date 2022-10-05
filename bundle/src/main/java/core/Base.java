package core;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.utils.ExceptionUtil;

import utils.ExecutionTImeCalculator;
import utils.FileUploader;
import utils.LoggerLog4j;
import utils.Logo;

/**
 * @author amohan31
 *
 */
public class Base extends LoggerLog4j {
	protected static HashMap<String, String> xpathList = new HashMap<>();
	protected static ConcurrentHashMap<String, WebDriver> LATEST_DRIVER_POOL = new ConcurrentHashMap<>();
	protected static String parentTunnel = "optumtest"; // "sso-optum-aman.mohan";
	protected static String tunnelIdentifier = "Optum-Prd"; // "mytunnel";
	protected static String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	protected static String serverIP = "http://127.7.7.7:5353/";
	protected static int MAX_DURATION = 10800;
	protected static int SAUCE_SESSION_TIMEOUT = 5000;
	protected static DesiredCapabilities capability;
	protected static String browserName;
	protected static final Properties componentProperties = new Properties();
	protected static ConcurrentHashMap<String, String> authors;
	protected static ConcurrentHashMap<String, String> tags;
	protected static ConcurrentHashMap<String, WebDriver> driverMap;
	protected static ConcurrentHashMap<String, String> testURLS;
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
	protected static String tag;
	protected static String author;
	protected static String browser;
	protected static String IeDriverPath;
	protected static String chrome_path;
	protected static String firefox_path;
	protected static String nasPath;
	protected static String remoteBrowser;
	protected static String remoteVersion;
	protected static String edgeDriverPath;
	protected static String sauceAccessKey;
	protected static String sauceUserName;
	protected static String remoteExecution;
	protected static String platform;
	protected static String startTime;
	protected static boolean upload;
	protected static ConcurrentHashMap<String, String> componentData = new ConcurrentHashMap<String, String>();
	protected static String qaHandleAttribute = "data-qahandle";
	protected static String qaHandleLocator = "//*[@data-qahandle]";
	protected static ThreadLocal<ArrayList<String>> urlUnderTest = new ThreadLocal<>();
	protected static ThreadLocal<ArrayList<String>> customTestLogs = new ThreadLocal<>();
	protected static ThreadLocal<EdgeOptions> tEdgeOptions = new ThreadLocal<>();
	private static ThreadLocal<WebDriverWait> waitPool = new ThreadLocal<WebDriverWait>();
	protected static ThreadLocal<String> classNameSession = new ThreadLocal<>();
	protected static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
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
	protected static ThreadLocal<HashMap<String, Boolean>> skipConditionMapPool = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> urlsPool = new ThreadLocal<>();
	static ThreadLocal<String> expectedDomainPool = new ThreadLocal<>();
	private static ThreadLocal<String> xpathPool = new ThreadLocal<>();
	private static ThreadLocal<String> xpathManipulation = new ThreadLocal<>();
	static ThreadLocal<String> expUrlPool = new ThreadLocal<>();
	static ThreadLocal<String> currentWindowHandlePool = new ThreadLocal<>();
	static ThreadLocal<String> errorStackPool = new ThreadLocal<>();
	static ThreadLocal<Integer> waitTimeout = new ThreadLocal<>();
	static ThreadLocal<String> stringManipulationPool = new ThreadLocal<>();
	static ThreadLocal<WebDriver> pageLoadDriverPool = new ThreadLocal<>();
	static ThreadLocal<Integer> intTimeout = new ThreadLocal<>();
	static ThreadLocal<WebDriver> jsePool = new ThreadLocal<>();
	static ThreadLocal<WebElement> mouseElementPool = new ThreadLocal<>();
	static ThreadLocal<Logger> pageLoadLoggerPool = new ThreadLocal<>();
	protected static Properties property;

	/**
	 * This method is like a getter method for creating a script-specific browser
	 * session and will store the session in LATEST_DRIVER_POOL where the 'key'
	 * would be the class name
	 * 
	 * @param clazz
	 *            Class name of the requesting script
	 */
	protected static void fetchSession(@SuppressWarnings("rawtypes") Class clazz) {

		synchronized (clazz) {
			DriverSessionHandler obj = new DriverSessionHandler(clazz);
			Thread t = new Thread(obj);
			t.setName(clazz.getName());
			t.start();

			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method initialize and prepares the setup required before starting the
	 * execution
	 */
	protected static void initialize() {
		// System.setProperty("scan", "true");
		// System.setProperty("scanWith", "xpath");
		// System.setProperty("upload", "true");
		 System.setProperty("defaultExecution", "true");
		// System.setProperty("regression","true");
		startTime = ExecutionTImeCalculator.getCurrentTime();
		authors = new ConcurrentHashMap<>();
		tags = new ConcurrentHashMap<>();
		driverMap = new ConcurrentHashMap<String, WebDriver>();
		testURLS = new ConcurrentHashMap<String, String>();
		logger = LoggerLog4j.startTestCase(Base.class);
		Logo.init();
		System.setProperty("logFilename", "./logs");
		System.setProperty("org.freemarker.loggerLibrary", "none");
		 Configurator.loadConfig("./src/test/java/runner/Config-test.properties");
//		Configurator.loadConfig(System.getProperty("config-file"));
		Configurator.setConfig();
		logger.info("INITIALIZED AND READY TO GO!!  \\\\(^ ^)//");

	}

	/**
	 * This method is used to wait explicitly
	 * 
	 * @param durationInSeconds
	 *            time for which wscript need to wait
	 * @param logger
	 *            logger object of the requesting class
	 */
	@SuppressWarnings("static-access")
	protected static void pleaseWait(Integer durationInSeconds, Logger logger) {
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

	/**
	 * This method is to focus and highlight the required WebElement using red
	 * outline
	 * 
	 * @param driver
	 *            driver object of the requesting script
	 * @param element
	 *            WebElement needs to be highlighted
	 */
	protected static void focusElement(WebDriver driver, WebElement element) {
		synchronized (driver) {
			synchronized (element) {
				elementManipulationDriverPool.set(driver);
				elementPool.set(element);
			}
		}

		((JavascriptExecutor) elementManipulationDriverPool.get())
				.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", elementPool.get());
	}

	/**
	 * This method is used to select an option using select class with the help of
	 * visible text of the option
	 * 
	 * @param logger
	 * @param element
	 *            WebElement [Drop down]
	 * @param optionTextToSelect
	 *            visible text of element to be selected
	 */
	protected void selectByOptionName(Logger logger, WebElement element, String optionTextToSelect) {

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
				.info("Selected by name '" + elementNamePool.get() + "' from dropdown  " + elementPool.get().getText());
	}

	/**
	 * This method is used to select an option using select class with the help of
	 * value of the 'value' attribute of the option
	 * 
	 * @param logger
	 * @param element
	 *            WebElement [Drop down]
	 * @param optionTextToSelect
	 *            'value' attribute of the element to be selected
	 */
	protected void selectByValue(Logger logger, WebElement element, String optionTextToSelect) {

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

		selectByOptionPool.get().selectByValue(elementNamePool.get());
		methodLoggerPool.get().info(
				"Selected by value '" + elementNamePool.get() + "' from dropdown  " + elementPool.get().getText());
	}

	/**
	 * This method is used to select an option using select class with the help of
	 * index no. of the option
	 * 
	 * @param element
	 *            element WebElement [Drop down]
	 * @param optionIndex
	 *            index of the webelement to be selected
	 * @param logger
	 * @return
	 */
	protected String selectByOptionIndex(WebElement element, Integer optionIndex, Logger logger) {
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
				.info("Selected by index: '"
						+ selectByOptionPool.get().getOptions().get(optionIndexPool.get()).getText()
						+ "' from dropdown  \n" + selectByOptionPool.get().getFirstSelectedOption().getText());
		return elementPool.get().getAttribute("innterText");
	}

	/**
	 * This method is used to check availability and visibility of a WebElement in a
	 * handled way
	 * 
	 * @param logger
	 * @param element
	 *            WebElement who's existence needs to be asserted
	 * @param elementName
	 *            Name for reference
	 * @return boolean
	 */
	protected static boolean verifyElementExists(Logger logger, WebElement element, String elementName) {
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
			// verifyElementLoggerPool.get().info(Throwables.getStackTraceAsString(e));

			verifyElementLoggerPool.get().error("Element " + elementNamePool.get() + " doesn't exists!");

			statusPool.set(false);
		}

		return statusPool.get();

	}

	/**
	 * This method is used to compare the actual and expected visible text of a
	 * WebElement
	 * 
	 * @param element
	 *            WebElement who's text to be verified
	 * @param expectedText
	 *            WebElement's inner text
	 * @return boolean
	 */
	protected synchronized boolean verifyElementText(WebElement element, String expectedText) {
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

	/**
	 * This method is used to compare the actual and expected attribute value of a
	 * WebElement
	 * 
	 * @param element
	 *            WebElement who's attribute to be verified
	 * @param attributeName
	 *            Attribute who's value to be asserted
	 * @param expectedText
	 * @return
	 */
	protected synchronized boolean verifyAttributeValue(WebElement element, String attributeName, String expectedText) {
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

	/**
	 * This method is used to compare the actual and expected CSS attribute value of
	 * a WebElement
	 * 
	 * @param element
	 *            WebElement who's attribute to be verified
	 * @param cssAttributeName
	 *            Attribute who's value to be asserted
	 * @param expectedValue
	 *            expected attribute value
	 * @return boolean
	 */
	protected synchronized boolean verifyCSSValue(WebElement element, String cssAttributeName, String expectedValue) {
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

	/**
	 * This method is used to hover the mouse cursor over the required WebElement
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param element
	 *            WebElent on which mouse t be hovered
	 * @return WebElement
	 */
	protected WebElement moveMouseOnToElement(WebDriver mydriver, WebElement element) {

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

	/**
	 * This method is used to scroll the page by pre-defined coordinates in pixels
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param i
	 *            X - Coordinate
	 * @param j
	 *            Y - Coordinate
	 */
	protected synchronized void scrollPageByDimensions(WebDriver mydriver, int pixelX, int pixelY) {
		JavascriptExecutor js = (JavascriptExecutor) mydriver;
		js.executeScript("window.scrollBy(" + pixelX + "," + pixelY + ")");
	}

	/**
	 * This method is used to scroll the page to a particular WebElement and wait
	 * till its visibility
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param element
	 *            WebElement to which page needs to be scrolled
	 * @param logger
	 * @return WebElement
	 */
	protected static WebElement scrollToElement(WebDriver mydriver, WebElement element, Logger logger) {
		synchronized (mydriver) {
			synchronized (element) {
				synchronized (pageLoadLoggerPool) {
					pageLoadLoggerPool.set(logger);
					elementManipulationDriverPool.set(mydriver);
					elementPool.set(element);
				}
			}
		}
		((JavascriptExecutor) elementManipulationDriverPool.get()).executeScript("arguments[0].scrollIntoView()",
				elementPool.get());

		getVisibility(elementManipulationDriverPool.get(), elementPool.get(), 10);

		pleaseWait(4, pageLoadLoggerPool.get());
		focusElement(elementManipulationDriverPool.get(), elementPool.get());
		return elementPool.get();

	}

	/**
	 * This method is used to scroll the page to a particular WebElement without
	 * waiting for its visibility Under this method page will be scrolled without
	 * waiting for visibility
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param element
	 *            WebElement to which page needs to be scrolled
	 */
	protected void scrollToElementWithoutWait(WebDriver mydriver, WebElement element) {
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

	/**
	 * This method is used to wait for the visibility of a particular WebElement
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param e
	 *            WebElement needs to be visible
	 * @param timeout
	 *            Time interval for which script needs to wait
	 * @return WebElement
	 */
	protected static WebElement getVisibility(WebDriver mydriver, WebElement e, Integer timeout) {
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

	/**
	 * This method is used to wait till the availability or presence in the DOM of a
	 * WebElement
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param xpath
	 *            Xpath of the webelement
	 * @param timeout
	 *            Time interval for which script needs to wait
	 * @return WEbElement
	 */
	protected WebElement getPresence(WebDriver mydriver, String xpath, Integer timeout) {
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

	/**
	 * This method is to get the font size of the visible text
	 * 
	 * @param element
	 *            WebElement who's font size needs to be fetched
	 * @return Font Size
	 */
	protected synchronized static String getFontSize(WebElement element) {
		String fontSize = element.getCssValue("font-size");
		return fontSize;
	}

	/**
	 * This method is to get the font color of the visible text
	 * 
	 * @param element
	 *            WebElement who's font size needs to be fetched
	 * @return String Hex color code
	 */
	protected synchronized static String getHexFontColor(WebElement element) {
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

	/**
	 * This method is used to set the script info for reporting purpose
	 * 
	 * @param tag
	 *            Component/Script Name
	 * @param author
	 *            Script Owner Name
	 * @param className
	 *            Script class name
	 */
	protected synchronized static void setTagForTestClass(String tag, String author, String className) {
		tags.put(className, tag);
		authors.put(className, author);
	}

	/**
	 * This method is to find first 5 characters of the domain of any given URL
	 * 
	 * @param url
	 *            Hyperlink
	 * @return First 5 character of the domain
	 */
	protected synchronized String getDomainNameSubset(String url) {
		try {
			urlPoolForDomain.set(new URI(url));

			domainPool.set(urlPoolForDomain.get().getHost().substring(0, 5));
		} catch (URISyntaxException e) {
			domainPool.set("Invalid Domain");
			e.printStackTrace();
		}

		return domainPool.get();
	}

	/**
	 * This method is to find the full domain of any given URL
	 * 
	 * @param url
	 *            Hyperlink
	 * @return Domain of the given hyperlink
	 */
	protected synchronized static String getDomainName(String url) {
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

	/**
	 * This method is required to be called on the very first line of each TC. It'll
	 * skip the TC if there's no URL available to run the TC
	 * 
	 * @param urls
	 *            Url data from the script
	 * @return Throws SkipException if the given Url data is empty
	 */
	protected HashMap<String, Boolean> skipNonExistingComponent(ArrayList<String> urls) {
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

		}
		return skipConditionMapPool.get();
	}

	protected HashMap<String, Boolean> skipNonExistingComponent(String urls) {
		synchronized (urls) {
			urlsPool.set(new ArrayList<>());
			urlsPool.get().add(urls);
			skipConditionMapPool.set(new HashMap<>());
		}
		if (urlsPool.get().size() == 0
				|| urlsPool.get().toString().trim().replace("[", "").replace("]", "").isEmpty()) {
			skipConditionMapPool.set(null);
			throw new SkipException("Can't find Component url");
		} else {
			urlsPool.get().stream().forEach(a -> {
				skipConditionMapPool.get().put(a, false);
			});

		}
		return skipConditionMapPool.get();
	}

	/**
	 * This method is used to fetch random value within the given range where
	 * 'maximum' will be exclusive and 'minimum' will be inclusive
	 * 
	 * @param maximum
	 *            end of range (exclusive)
	 * @param minimum
	 *            start of range (inclusive)
	 * @return random integer
	 */
	protected synchronized static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	/**
	 * This method is used to fetch random value within the given range where
	 * 'maximum' will be exclusive and 'minimum' will be inclusive
	 * 
	 * @param start
	 *            start of range (inclusive)
	 * @param end
	 *            end of range (exclusive)
	 * @param excludes
	 *            numbers to exclude (= numbers you do not want)
	 * @return the random number within start-end but not one of excludes
	 */
	protected static int getRandomIntegerWithExclusion(int start, int end, int... excludes) {
		int rangeLength = end - start - excludes.length;
		int randomInt = new Random().nextInt(rangeLength) + start;

		for (int i = 0; i < excludes.length; i++) {
			if (excludes[i] > randomInt) {
				return randomInt;
			}

			randomInt++;
		}

		return randomInt;
	}

	/**
	 * This method is used to check if the the required file is downloaded or not
	 * 
	 * @param filename
	 *            Name of the file downloaded
	 * @param mydriver
	 *            Driver object of requesting class
	 * @return boolean
	 */
	@Deprecated
	protected static boolean isDownloaded(String filename, WebDriver mydriver) {
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

	/**
	 * This method is used to log into the AEM only to use when using unpublished
	 * urls for test purpose
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param userName
	 *            AEM login username
	 * @param password
	 *            AEM login password
	 * @param logger
	 */
	protected synchronized void loginTestEnv(WebDriver mydriver, String userName, String password, Logger logger) {
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

	/**
	 * This method is used to capitalize every character before ' '
	 * 
	 * @param givenString
	 *            Capitalize every character before ' '
	 * @return
	 */
	protected synchronized static String capitalizeWhiteString(String givenString) {

		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * This method is used to capitalize every character before '-' and for
	 * replacing '-' with ' '
	 * 
	 * @param givenString
	 *            Capitalize the string in camel case and replacing '-' with ' '
	 * @return
	 */
	protected synchronized static String capitalizeHyphenString(String givenString) {

		String[] arr = givenString.split("-");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * This method is used to Switch the focus of the WebDriver to next tab
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * @param currentWindowHandle
	 *            Window handle of the active browser tab
	 * @return
	 */
	protected static synchronized WebDriver switchToNextTab(WebDriver mydriver, Logger logger,
			String currentWindowHandle) {
		Set<String> tabSet = mydriver.getWindowHandles();
		Iterator<String> it = tabSet.iterator();

		while (it.hasNext()) {
			String a = it.next();
			if (!a.equals(currentWindowHandle)) {
				logger.info("Current tab title => '" + mydriver.getTitle() + "'   " + mydriver.getWindowHandle());
				mydriver.switchTo().window(a);
				logger.info("Switched to prev tab with title => '" + mydriver.getTitle() + "'");

				break;
			}
		}
		return mydriver;
	}

	protected static synchronized WebDriver switchToTab(WebDriver mydriver, Logger logger, String currentWindowHandle) {
		Set<String> tabSet = mydriver.getWindowHandles();
		Iterator<String> it = tabSet.iterator();

		while (it.hasNext()) {
			String a = it.next();
			if (!a.equals(currentWindowHandle)) {
				logger.info("Current tab title => '" + mydriver.getTitle() + "'   " + mydriver.getWindowHandle());
				mydriver.switchTo().window(a).close();

			}
		}
		mydriver.switchTo().window(currentWindowHandle);
		return mydriver;
	}

	/**
	 * This method is used to fetch only the last node of the URL without '.html'
	 * using the current url of the provided browser session
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 * @return String
	 */
	protected synchronized String getLastNodeOfActualLinkWithoutHtml(WebDriver mydriver) {
		String lastNode = mydriver.getCurrentUrl().split("/")[mydriver.getCurrentUrl().split("/").length - 1]
				.split(".html")[0];
		return lastNode;
	}

	/**
	 * This method is used to fetch only the last node of the provided URL with
	 * '.html'
	 * 
	 * @param expURL
	 *            Driver object of the requesting class
	 * @return
	 */
	protected synchronized String getLastNodeOfExpectedLinkWithoutHtml(String expURL) {
		String lastNode = expURL.split(".//")[1].split("/")[expURL.split(".//")[1].split("/").length - 1]
				.split(".html")[0];
		return lastNode;
	}

	/**
	 * This is to verify if the given is not a pdf link and the domain of both the
	 * expected and actual link is same
	 * 
	 * @param expUrl
	 *            Hyperlink fetched from the WebElement
	 * @param expectedDomain
	 *            Domain of the Test URL
	 * @param actualUrl
	 *            Current Url of
	 * @return
	 */
	protected synchronized boolean isDomainSame(String expectedDomain, String expUrl) {
		if (!expUrl.contains("pdf") && getDomainName(expUrl).equalsIgnoreCase(expectedDomain)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to assert hyperlink redirecting functionality
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * @param testUrlDomain
	 *            Main test component url's domain
	 * @param expHyperLink
	 * @param currentWindowHandle
	 * @return
	 */
	protected WebDriver assertRedirection(WebDriver mydriver, Logger logger, String testUrlDomain, String expHyperLink,
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

			if (isDomainSame(expectedDomainPool.get(), expUrlPool.get())) {
				pleaseWait(3, verifyElementLoggerPool.get());
				verifyElementLoggerPool.get()
						.info("Expected Url path: " + getLastNodeOfExpectedLinkWithoutHtml(expUrlPool.get())
								+ "\nActual Url path: "
								+ getLastNodeOfActualLinkWithoutHtml(elementManipulationDriverPool.get()));

				hardAssert.assertEquals(elementManipulationDriverPool.get().getWindowHandles().size(), 1);
				// hardAssert.assertNotEquals(BrokenLinks.isBroken(expUrlPool.get()), 404);
				// //temporarily disabled
				verifyElementLoggerPool.get().info("Assertion passes for same tab!");
				switchToTab(elementManipulationDriverPool.get(), verifyElementLoggerPool.get(),
						currentWindowHandlePool.get());
			} else if (expectedDomainPool.get().contains(".pdf")
					|| !expectedDomainPool.get().equalsIgnoreCase(getDomainName(expUrlPool.get()))) {

				pleaseWait(3, verifyElementLoggerPool.get());
				verifyElementLoggerPool.get().info("Expected Url path: " + expUrlPool.get() + "\nActual Url path: "
						+ elementManipulationDriverPool.get().getCurrentUrl());

				hardAssert.assertEquals(elementManipulationDriverPool.get().getWindowHandles().size(), 2);
				verifyElementLoggerPool.get().info("Assertion passes for different tab!");
				switchToTab(elementManipulationDriverPool.get(), verifyElementLoggerPool.get(),
						currentWindowHandlePool.get());

			}
		} catch (Exception e) {
			synchronized (this) {
				errorStackPool.set(ExceptionUtil.getStackTrace(e));
			}
			verifyElementLoggerPool.get().error(errorStackPool.get());
			switchToPreviousTab(elementManipulationDriverPool.get(), verifyElementLoggerPool.get(),
					currentWindowHandlePool.get());
		}
		return elementManipulationDriverPool.get();
	}

	static ThreadLocal<String> urlPathPool = new ThreadLocal<>();

	/**
	 * This method is used to assert hyperlink redirecting functionality where
	 * expected key in the url is known
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * @param testUrlDomain
	 *            Main test component url's domain
	 * @param expHyperLink
	 * @param currentWindowHandle
	 */
	protected void assertStaticRedirection(WebDriver mydriver, Logger logger, String testUrlDomain, String expHyperLink,
			String expRelativePath, String currentWindowHandle) {
		synchronized (mydriver) {
			synchronized (logger) {
				synchronized (testUrlDomain) {
					synchronized (expHyperLink) {
						synchronized (currentWindowHandle) {
							synchronized (expRelativePath) {

								urlPathPool.set(expRelativePath);
								expectedDomainPool.set(testUrlDomain);
								String expURL;
								try {
									expURL = Jsoup.connect(expHyperLink).followRedirects(true).ignoreContentType(true)
											.maxBodySize(48000000).ignoreHttpErrors(true).execute().url().toString();
								} catch (IOException e) {
									expURL = null;
									logger.fatal(ExceptionUtil.getStackTrace(e));
								}
								expUrlPool.set(expURL);
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

	/**
	 * Here the script will wait for the page to completely load
	 * 
	 * @param timeOutInSeconds
	 *            Time interval for which the script needs to wait
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 */
	protected void jsWaitForPageToLoad(Integer timeOutInSeconds, WebDriver mydriver, Logger logger) {
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

		if (((JavascriptExecutor) pageLoadDriverPool.get()).executeScript(jsCommand).toString().equals("complete")) {
			return;
		}

		for (int i = 0; i < intTimeout.get(); i++) {
			pleaseWait(2, pageLoadLoggerPool.get());
			if (((JavascriptExecutor) pageLoadDriverPool.get()).executeScript(jsCommand).toString()
					.equals("complete")) {
				System.out.println("LOAD COMPLETE");
				break;
			}
		}
	}

	/**
	 * This method is used to Switch the focus of the WebDriver to previous tab
	 * 
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * 
	 */
	protected static synchronized void switchToPreviousTab(WebDriver mydriver, Logger logger,
			String currentWindowHandle) {
		Set<String> tabSet = mydriver.getWindowHandles();
		Iterator<String> it = tabSet.iterator();
		ArrayList<String> tabList = new ArrayList<>();

		while (it.hasNext()) {
			String a = it.next();
			tabList.add(a);
		}
		Collections.reverse(tabList);
		Iterator<String> reverseIterator = tabList.iterator();
		while (it.hasNext()) {
			String a = reverseIterator.next();
			if (!a.equals(currentWindowHandle)) {
				logger.info("Current tab title => '" + mydriver.getTitle() + "'");
				mydriver.switchTo().window(a);
				logger.info("Switch to next tab with title => '" + mydriver.getTitle() + "'");

				break;
			}
		}
	}

	/**
	 * Here the element will be clicked using JavaScriptExecutor
	 * 
	 * @param element
	 *            WebELement to be clicked
	 * @param mydriver
	 *            Driver object of the requesting script
	 */
	protected void clickWithJS(WebElement element, WebDriver mydriver) {
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

	/**
	 * Here the element will be hovered by mouse cursor using JavaScriptExecutor
	 * 
	 * @param element
	 *            WebELement to be clicked
	 * @param mydriver
	 *            Driver object of the requesting script
	 */
	protected void mouseWithJS(WebElement element, WebDriver mydriver) {

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

	/**
	 * Here the Json file with the previous scanning result will be downloaded and
	 * processed
	 * 
	 * @param logger
	 */
	protected static void getComponentUrl(Logger logger) {

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

	/**
	 * This method is used to fetch the URLs out of the test data using the given
	 * key
	 * 
	 * @param componentName
	 *            Value from either CmponentList.properties or XpathList.properties
	 *            will be parsed by the script
	 * @param depth
	 *            how many URLs to be retrieve
	 * @return An array of Script Test URLs
	 */

	protected synchronized static String fetchUrl(String componentName, int depth) {
		if (System.getProperty("defaultExecution") == null
				|| System.getProperty("defaultExecution").equalsIgnoreCase("false")) {
			int count = 0;
			String COMPONENT = null;
			Iterator<String> it = componentData.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (key.toLowerCase().contains(componentName.toLowerCase())) {
					COMPONENT = key;
					break;
				}
			}
			if (COMPONENT == null || !(componentData.get(COMPONENT).contains("http"))) {
				// return "ERROR WHILE FETCHING URL FOR "+componentName;
				return null;
			} else {
				if (componentData.containsKey(COMPONENT)) {
					String a = componentData.get(COMPONENT).replace("[", "").replace("]", "").replace("\"", "");
					String[] urlSet = a.split(",");
					String randomURLs = null;
					String temp = "";
					int p = 0;
					while (count != depth && p < urlSet.length && urlSet.length != 0) {
						temp = temp + urlSet[p] + ",";
						count++;
						p++;

					}

					if (!(temp.isEmpty())) {
						randomURLs = temp.substring(0, (temp.length() - 1));
						return randomURLs;
					} else {
						return null;
					}

				} else {
					return null;
				}
			}
		} else {
			return null;
		}
	}

	/**
	 * Here Action Class's object will be created for specific script
	 * 
	 * @param mydriver
	 * @return Action Class's object
	 */
	protected static Actions getActions(WebDriver mydriver) {
		synchronized (mydriver) {
			actionPool.set(new Actions(mydriver));
		}
		return actionPool.get();
	}

	ThreadLocal<WebDriver> medexVisibilityDriverPool = new ThreadLocal<>();
	ThreadLocal<WebElement> medexVisibilityComponentPool = new ThreadLocal<>();
	ThreadLocal<Logger> medexVisibilityLoggerPool = new ThreadLocal<>();

	/**
	 * This method is used to remove the static header which appears on scrolling
	 * the page and restricts the view of the elements
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 * @param medexComponent
	 *            Medex Component Section locator
	 * @param logger
	 */
	protected void scrolltillvisibilityMedex(WebDriver mydriver, WebElement medexComponent, Logger logger) {

		synchronized (mydriver) {
			synchronized (medexComponent) {
				synchronized (logger) {
					medexVisibilityComponentPool.set(medexComponent);
					medexVisibilityDriverPool.set(mydriver);
					medexVisibilityLoggerPool.set(logger);
				}
			}
		}
		scrollToElementWithoutWait(medexVisibilityDriverPool.get(), medexVisibilityComponentPool.get());
		getWebDriverWait(medexVisibilityDriverPool.get(), 50)
				.until(ExpectedConditions.visibilityOf(medexVisibilityComponentPool.get()));

		try {
			((JavascriptExecutor) medexVisibilityDriverPool.get())
					.executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");
		} catch (WebDriverException e) {
		}

		scrollToElementWithoutWait(medexVisibilityDriverPool.get(), medexVisibilityComponentPool.get());
		getWebDriverWait(medexVisibilityDriverPool.get(), 50)
				.until(ExpectedConditions.visibilityOf(medexVisibilityComponentPool.get()));

	}

	/**
	 * This method is used to get a WebDriverWait object in a Threadsafe way
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 * @param timeout
	 *            Max time interval for which the script need to wait
	 * @return
	 */
	public synchronized static FluentWait<WebDriver> getWebDriverWait(WebDriver mydriver, int timeout) {
//		return new FluentWait<WebDriver>(mydriver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofMillis(300));
		return new WebDriverWait(mydriver, timeout);
	}

	ThreadLocal<String> tryLoopVisibilityErrorPool = new ThreadLocal<>();
	ThreadLocal<WebElement> tryLoopVisibilityElementPool = new ThreadLocal<>();
	ThreadLocal<Boolean> tryLoopVisibilityStatusPool = new ThreadLocal<>();
	ThreadLocal<Boolean> tryLoopVisibilityThrowErrorOptionPool = new ThreadLocal<>();

	/**
	 * This method is used to re use try-catch block and wait for element
	 * availability. Also it can be declared if you want to throw skip exception or
	 * not when element is not available.
	 * 
	 * @param element
	 *            Element to be found
	 * @param errorMessage
	 *            Error to be displayed if element not found
	 * @param logger
	 * @return
	 */
	protected boolean isElementExists(WebElement element, String errorMessage, Logger logger, Boolean throwErrorOrNot) {
		synchronized (element) {
			synchronized (errorMessage) {
				synchronized (logger) {
					synchronized (throwErrorOrNot) {
						tryLoopVisibilityElementPool.set(element);
						tryLoopVisibilityErrorPool.set(errorMessage);
						tryLoopVisibilityStatusPool.set(false);
						tryLoopVisibilityThrowErrorOptionPool.set(throwErrorOrNot);
					}
				}
			}

		}
		try {
			tryLoopVisibilityElementPool.get().isDisplayed();
			tryLoopVisibilityStatusPool.set(true);
		} catch (Exception e) {
			tryLoopVisibilityStatusPool.set(false);
			if (tryLoopVisibilityThrowErrorOptionPool.get() == true) {
				throw new SkipException(tryLoopVisibilityErrorPool.get());
			}
		}
		return tryLoopVisibilityStatusPool.get();
	}
}
