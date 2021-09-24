package core;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.LoggerLog4j;
/*
 *	@website the website required to be scanned 
 * 
 * 
 * 
 * */

public class ParamountNew extends Base implements Runnable {
	String website = "";
	private static ThreadLocal<WebDriver> driverPoolParamount = new ThreadLocal<>();
	private static ThreadLocal<List<WebElement>> componentPool = new ThreadLocal<>();
	private static ThreadLocal<ChromeOptions> options = new ThreadLocal<>();
	private static ThreadLocal<DesiredCapabilities> capabilities = new ThreadLocal<>();
	private static ThreadLocal<ConcurrentSkipListSet<String>> hyperlinksPool = new ThreadLocal<>();
	private static ThreadLocal<ConcurrentSkipListSet<String>> mainUrlsPool = new ThreadLocal<>();
	private static ThreadLocal<String> loopPool = new ThreadLocal<>();
	private static ThreadLocal<String> websites = new ThreadLocal<>();

	static ThreadLocal<List<WebElement>> listPool = new ThreadLocal<>();
	static ThreadLocal<List<WebElement>> elementPool = new ThreadLocal<>();

	public static void retrieveHyperlinks(List<WebElement> list) {
		synchronized(list) {elementPool.set(list);}
		try {
			elementPool.get().stream().forEach(a -> {

				try {
					if (!(hyperlinksPool.get().contains(a.getAttribute("href")))
							&& !a.getAttribute("href").contains("tel:") && !a.getAttribute("href").contains("mailto")
							&& !a.getAttribute("href").trim().isEmpty() && a.getAttribute("href").trim() != null
							&& !a.getAttribute("href").trim().contains("%")) {
						if (getDomainName(a.getAttribute("href")) != null) {
							if (getDomainName(a.getAttribute("href")).trim()
									.equalsIgnoreCase(getDomainName(Thread.currentThread().getName()).trim())) {
								hyperlinksPool.get().add(a.getAttribute("href"));
								System.out.println(
										Thread.currentThread().getName() + "  Valid ::> " + a.getAttribute("href"));
							} else {
								System.out.println(
										Thread.currentThread().getName() + "  NOT ::> " + a.getAttribute("href"));
							}
						}
					} else {
						System.out
								.println(Thread.currentThread().getName() + "  Already ::> " + a.getAttribute("href"));
					}
				} catch (NullPointerException ee) {
					loggerPool.get().error(("Error in URL=>" + "     " + a.getAttribute("href")));
					// ee.printStackTrace();
				}
			});
		} catch (StaleElementReferenceException e) {

		}

		loggerPool.get().info("Current Redirection count for Thread-" + Thread.currentThread().getName() + "=> "
				+ hyperlinksPool.get().size());
		// loggerPool.get().info("Pages checked so far for Thread-" +
		// Thread.currentThread().getId() + "=> "
		// + mainUrlsPool.get().size());
		elementPool.remove();
	}

	static ThreadLocal<Boolean> isScanningComplete = new ThreadLocal<>();

	public static void startScanning(String website) {
		synchronized (website) {
			websites.set(website);
			hyperlinksPool.set(new ConcurrentSkipListSet<String>());
			mainUrlsPool.set(new ConcurrentSkipListSet<String>());
			LoggerLog4j.startLoggerPool(String.valueOf(Thread.currentThread().getId()));
			driverPoolParamount.set(getWebdriver("Paramount"));
			isScanningComplete.set(false);
		}
		driverPoolParamount.get().get(websites.get());
		hyperlinksPool.get().add(websites.get());
		System.out.println(hyperlinksPool.get());

		retrieveHyperlinks(driverPoolParamount.get().findElements(By.xpath(
				"//a[@href and not(contains(@href,'tel:') and contains(@href,'mailto') and contains(@href,'.pdf')and contains(@href,'nutrition-wellness-resources')) ]")));

		// for (String page : hyperlinksPool.get()) {
		hyperlinksPool.get().stream().forEach(page -> {
			if (isScanningComplete.get() == false) {

				if (page.trim() != null || !page.trim().isEmpty()) {
					loopPool.set(page);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					if (getDomainName(loopPool.get()).equals(getDomainName(Thread.currentThread().getName()))) {
						if (loopPool.get().endsWith(".html") && loopPool.get().startsWith("http")) {
							loggerPool.get().info("Scanning => " + loopPool.get());
							try {
								Thread.currentThread().sleep(1500);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								driverPoolParamount.get().get(loopPool.get());

							} catch (WebDriverException e) {
								loggerPool.get().error("COnnection refused ::> " + loopPool.get());
							}
							findComponent(loopPool.get());

						}
						mainUrlsPool.get().add(loopPool.get());
						if (!mainUrlsPool.get().equals(hyperlinksPool.get())) {
							retrieveHyperlinks(driverPoolParamount.get().findElements(By.xpath(
									"//a[contains(@href,'.html') and starts-with(@href,'http') and not(contains(@href,'.pdf')and contains(@href,'nutrition-wellness-resources')) ]")));
							isScanningComplete.set(false);
						} else {
							loggerPool.get()
									.info("Total redirections found for "
											+ getDomainName(Thread.currentThread().getName()) + " => "
											+ mainUrlsPool.get().size());
							isScanningComplete.set(true);
							loggerPool.get().info("Total redirections found for " + getDomainName(Thread.currentThread().getName())
							+ " => " + mainUrlsPool.get().size());
						}
					}
				} catch (NullPointerException e) {
					System.out.println("Found Null point excep at => " + loopPool.get());
					e.printStackTrace();
				}
				
				

			}else {
				driverPoolParamount.get().quit();
			}
		});

	}

	static ThreadLocal<String> urlToSearchPool = new ThreadLocal<>();

	public static void findComponent(String urlToSearch) {

		synchronized (urlToSearch) {
			urlToSearchPool.set(urlToSearch);
			componentPool.set(driverPoolParamount.get().findElements(By.xpath(qaHandleLocator)));
		}
		fetchComponentName(componentPool, urlToSearchPool.get());
	}

	/*
	 * 
	 * Need to synchronized this method in optimized way
	 * 
	 * 
	 * */	
	public synchronized static void fetchComponentName(ThreadLocal<List<WebElement>> componentPool, String pageUrl) {
		
			synchronized(pageUrl) {
				
			}
		
		for (WebElement element : componentPool.get()) {
			element.getAttribute(qaHandleAttribute);

			try {
				if (componentData.get(element.getAttribute(qaHandleAttribute)) == null
						|| componentData.get(element.getAttribute(qaHandleAttribute)).isEmpty()) {

					componentData.put(element.getAttribute(qaHandleAttribute), "\"" + pageUrl + "\"");
					loggerPool.get().info("Found New Component: '" + element.getAttribute(qaHandleAttribute)
							+ "' on page => " + pageUrl);
					// loggerPool.get().info("Component Data => "
					// + componentProperties.getProperty(element.getAttribute(qaHandleAttribute)));

				} else if (!componentData.get(element.getAttribute(qaHandleAttribute)).isEmpty() && !componentData
						.get(element.getAttribute(qaHandleAttribute)).contains(getDomainName(pageUrl))) {
					@SuppressWarnings("unused")
					String existingURL = componentData.get(element.getAttribute(qaHandleAttribute));
					componentData.replace(element.getAttribute(qaHandleAttribute),
							componentData.get(element.getAttribute(qaHandleAttribute)) + "," + "\"" + pageUrl + "\""); // "avc","asca"
					loggerPool.get()
							.info("Found '" + element.getAttribute(qaHandleAttribute) + "' on page => " + pageUrl);
					// loggerPool.get().info("Component Data => "
					// + componentProperties.getProperty(element.getAttribute(qaHandleAttribute)));

				}
			} catch (NullPointerException e) {
				loggerPool.get().info("Component '" + element.getAttribute(qaHandleAttribute)
						+ "' found but is is not covered in suite yet!");
			}

		}
	}

	static boolean execution = true;

	private static WebDriver getWebdriver(String className) {

		// if (remoteExecution.equalsIgnoreCase("true") && remoteExecution != null) {
		String URL = "https://sso-optum-" + sauceUserName + ":" + sauceAccessKey
				+ "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
		browser = "HChrome";

		// if (execution ==true|| sauceAccessKey != null || sauceUserName != null ||
		// remoteVersion != null) {
		try {
			switch (browser) {
			case "edge":
				// browserName = "Edge";
				synchronized (className) {
					tEdgeOptions.set(new EdgeOptions());
				}
				tEdgeOptions.get().setCapability("sauce:options", capability);
				synchronized (className) {
					driverPoolParamount.set(new RemoteWebDriver(new java.net.URL(URL), tEdgeOptions.get()));
				}
				break;

			case "HChrome":
				browserName = "HeadlessChrome";
				System.setProperty("webdriver.chrome.driver", chrome_path);
				synchronized (className) {
					options.set(new ChromeOptions());
				}
//				options.get().setPageLoadStrategy(PageLoadStrategy.EAGER);
				options.get().addArguments("--headless");
				options.get().setCapability(ChromeOptions.CAPABILITY, capability);
				loggerPool.get().info(
						"Now just sit back and relax. \nExecution will take place via Google Chrome [HEADLESS] and you'll be informed once everything is done..");

				synchronized (className) {
					driverPoolParamount.set(new ChromeDriver(options.get()));
				}
				break;

			default:
				logger.info("Remote Browser Configuration is invalid and now exiting!!");
				System.exit(0);
			}
		} catch (MalformedURLException e) {
			logger.fatal("Remote Configuration Error!");
			System.exit(0);
		}

		driverPoolParamount.get().manage().window().maximize();
		driverPoolParamount.get().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		logger.info("Browser session for website '" + Thread.currentThread().getName() + "' => "
				+ driverPoolParamount.get().toString());
		return driverPoolParamount.get();
	}

	@Override
	public void run() {
		startScanning(website);

	}

	public ParamountNew(String website) {
		this.website = website;
	}

}
