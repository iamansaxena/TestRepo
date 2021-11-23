package core;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

public class DriverSessionHandler extends Base implements Runnable {
	int count = 5;
	String className;
	WebDriver driver;
	static DesiredCapabilities capabilities = new DesiredCapabilities();

	public DriverSessionHandler(Class className) {
		this.className = className.getName();
	}

	@Override
	public void run() {
<<<<<<< Updated upstream
		
		LATEST_DRIVER_POOL.put(Thread.currentThread().getName(), getWebdriverInstance(Thread.currentThread().getName()));
=======

		LATEST_DRIVER_POOL.put(className, getWebdriverInstance(className));
>>>>>>> Stashed changes

	}

	// Visit random website with multiple session

	static ThreadLocal<String> sessionName = new ThreadLocal<>();
//	static String browsername;
	static DesiredCapabilities tCaps = new DesiredCapabilities();

	public static DesiredCapabilities getCapabilities() {
		if (remoteExecution.equalsIgnoreCase("true")) {
			browserName = remoteBrowser;
		} else if (remoteExecution.equalsIgnoreCase("false")) {
			browserName = localBrowser;
		}
		if (!browserName.equals("-s")) {
//		tCaps.setAcceptInsecureCerts(true);
		tCaps.setCapability("idleTimeout", SAUCE_SESSION_TIMEOUT);
//		tCaps.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		tCaps.setCapability("maxDuration", MAX_DURATION);
		tCaps.setCapability("parentTunnel", parentTunnel);
		tCaps.setCapability("tunnelIdentifier", tunnelIdentifier);
//		tCaps.setCapability("acceptInsecureCerts", true);
		tCaps.setCapability("seleniumVersion", "3.141.59");
		tCaps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
//		tCaps.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.EAGER);
		// tCaps.setCapability("extendedDebugging", "true");
		// tCaps.setCapability("capturePerformance", "true");
		// tCaps.setCapability("name", sessionName.get());

		
			tCaps.setCapability("screenResolution", "1920x1200");
		}
		tCaps.setCapability("commandTimeout", 400);

		if (browserName.equals("-c")) {
			browserName = "Chrome";
			tCaps.setCapability("browserName", new ChromeOptions().getBrowserName());
		}
		if (browserName.equals("-f")) {
			browserName = "Firefox";
			tCaps.setCapability("browserName", new FirefoxOptions().getBrowserName());
		}
		if (browserName.equals("-s")) {
			browserName = "Safari";
			tCaps.setAcceptInsecureCerts(true);
			tCaps.setCapability("idleTimeout", SAUCE_SESSION_TIMEOUT);
			tCaps.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
			tCaps.acceptInsecureCerts();
			tCaps.setCapability("maxDuration", MAX_DURATION);
			tCaps.setCapability("parentTunnel", parentTunnel);
			tCaps.setCapability("tunnelIdentifier", tunnelIdentifier);
			tCaps.setCapability("acceptInsecureCerts", "true");
			tCaps.setCapability("seleniumVersion", "3.141.59");
			tCaps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			tCaps.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.EAGER);
			tCaps.setCapability("browserName", new SafariOptions().getBrowserName());
			tCaps.setCapability("screenResolution", "1920x1440");
		}
		if (browserName.equals("-e")) {
			browserName = "Edge";
			tCaps.setCapability("browserName", new EdgeOptions().getBrowserName());
		}
		return tCaps;
	}

<<<<<<< Updated upstream
	/**
	 * This method is used to setup custom remote Chrome options
	 * 
	 * @return
	 */
	public static ChromeOptions getRemoteChromeOptions(String className) {
		synchronized (className) {
			tChromeOptions.set(new ChromeOptions());
		}
		tChromeOptions.get().setCapability("sauce:options", capability);
		tChromeOptions.get().setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		tChromeOptions.get().setAcceptInsecureCerts(true);
		tChromeOptions.get().setCapability("acceptInsecureCerts", true);
		tChromeOptions.get().setCapability("platformName", platform);
		tChromeOptions.get().setCapability("browserVersion", remoteVersion);
		return tChromeOptions.get();
	}

	/**
	 * This method is used to setup custom remote firefox options
	 * 
	 * @return
	 */
	public static FirefoxOptions getRemoteFireFoxOptions(String className) {
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
		return tFirefoxOptions.get();
	}

	/**
	 * This method is used to setup custom remote Edge options
	 * 
	 * @return
	 */
	public static EdgeOptions getRemoteEdgeOptions(String className) {
		synchronized (className) {
			tEdgeOptions.set(new EdgeOptions());
		}
		tEdgeOptions.get().setCapability("sauce:options", capability);
		tEdgeOptions.get().setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		tEdgeOptions.get().setCapability("acceptInsecureCerts", true);
		tEdgeOptions.get().setCapability("platformName", platform);
		tEdgeOptions.get().setCapability("browserVersion", remoteVersion);
		return tEdgeOptions.get();

	}

	/**
	 * This method is used to setup custom remote Safari options
	 * 
	 * @return
	 */
	public static SafariOptions getRemoteSafariOptions(String className) {
		synchronized (className) {
			tSafariOptions.set(new SafariOptions());
		}
		tSafariOptions.get().setCapability("sauce:options", capability);
		tSafariOptions.get().setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, "true");
		tSafariOptions.get().setCapability("platformName", "macOS 11");
//		tSafariOptions.get().setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		tSafariOptions.get().setCapability("browserVersion", "latest");
		return tSafariOptions.get();
	}

	/**
	 * This method is used to setup custom local Chrome options
	 * 
	 * @return
	 */
	public static ChromeOptions getLocalChromeOptions() {
		System.setProperty("webdriver.chrome.driver", chrome_path);
		tChromeOptions.set(new ChromeOptions());
		tChromeOptions.get().setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		return tChromeOptions.get();
	}

	/**
	 * This method is used to setup custom local Chrome options [Headless]
	 * 
	 * @return
	 */
	public static ChromeOptions getLocalChromeHeadLessOptions() {
		System.setProperty("webdriver.chrome.driver", chrome_path);
		tChromeOptions.set(new ChromeOptions());
		tChromeOptions.get().addArguments("--headless");
		return tChromeOptions.get();
	}

	/**
	 * This method is used to setup custom local Edge options
	 * 
	 * @return
	 */
	public static EdgeOptions getLocalEdgeOptions() {
		System.setProperty("webdriver.edge.driver", edgeDriverPath);
		tEdgeOptions.set(new EdgeOptions());
		return tEdgeOptions.get();
	}

	/**
	 * This method is used to set the pre-defined implicit waits
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 */
	public static void setDriverWaits(WebDriver mydriver) {
		mydriver.manage().window().maximize();
		mydriver.manage().deleteAllCookies();
		mydriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		mydriver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to create new WebDriver Session as per the pre-defined
	 * configurations
	 * 
	 * @param className
	 *            Name of the class requesting the browser session
	 * @return WebDriver
	 */
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
			browser = remoteBrowser;
			if (remoteBrowser != null || sauceAccessKey != null || sauceUserName != null || remoteVersion != null) {
				try {
					switch (remoteBrowser) {
					case "-c":
						// synchronized (className) {
						tDriver.set(new RemoteWebDriver(new java.net.URL(URL),
								DriverSessionHandler.getRemoteChromeOptions(classNameSession.get())));
						// }
						break;
					case "-f":
						// synchronized (className) {
						tDriver.set(new RemoteWebDriver(new java.net.URL(URL),
								DriverSessionHandler.getRemoteFireFoxOptions(classNameSession.get())));
						// }
						break;

					case "-e":

						// synchronized (className) {
						tDriver.set(new RemoteWebDriver(new java.net.URL(URL),
								DriverSessionHandler.getRemoteEdgeOptions(classNameSession.get())));
						// }
						break;

					case "-s":

						// synchronized (className) {
						tDriver.set(new RemoteWebDriver(new java.net.URL(URL),
								DriverSessionHandler.getRemoteSafariOptions(classNameSession.get())));
						// }
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

				loggerPool.get().info(
						"Now just sit back and relax. \nExecution will take place via Google Chrome and you'll be informed once everything is done..");

				tDriver.set(new ChromeDriver(DriverSessionHandler.getLocalChromeOptions()));
				break;

			case "-hc":

				loggerPool.get().info(
						"Now just sit back and relax. \nExecution will take place via Google Chrome [HEADLESS] and you'll be informed once everything is done..");

				tDriver.set(new ChromeDriver(DriverSessionHandler.getLocalChromeHeadLessOptions()));
				break;

			case "-e":

				loggerPool.get().info(
						"Now just sit back and relax. \nExecution will take place via Edge and you'll be informed once everything is done..");

				tDriver.set(new EdgeDriver(DriverSessionHandler.getLocalEdgeOptions()));
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

		DriverSessionHandler.setDriverWaits(tDriver.get());

		loggerPool.get()
				.info("Browser session for class '" + classNameSession.get() + "' => " + tDriver.get().toString());
		return tDriver.get();
	}

	protected static void setBrowser() {
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
=======
}
>>>>>>> Stashed changes
