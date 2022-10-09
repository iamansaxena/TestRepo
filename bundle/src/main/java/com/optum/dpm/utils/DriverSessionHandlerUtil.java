package com.optum.dpm.utils;

import static com.optum.dpm.utils.DPMConfigurationsUtil.*;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author amohan31
 *
 */
public class DriverSessionHandlerUtil {

	private static Logger logger = LogManager.getLogger(DriverSessionHandlerUtil.class);

	/**
	 * This method is used to set the pre-defined implicit waits
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 */
	private static void setDriverWaits(WebDriver mydriver) {
		mydriver.manage().window().maximize();
		mydriver.manage().deleteAllCookies();
		mydriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		mydriver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to create new WebDriver Session as per the pre-defined
	 * configurations
	 * 
	 * @param className
	 *            Name of the class requesting the browser session
	 * @return WebDriver
	 */
	public static WebDriver getWebdriverInstance() {
		WebDriver webDriver = null;
		if (remoteExecution) {
			String URL = "https://sso-optum-" + sauceUserName + ":" + sauceAccessKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
			try {
				if(remoteBrowserOptions.get(remoteBrowser) != null) {
					webDriver = new RemoteWebDriver(new java.net.URL(URL),remoteBrowserOptions.get(remoteBrowser)); 
				}else {
					logger.info("Remote Browser " + remoteBrowser + " Configurations is invalid and now exiting!!");
					System.exit(0);
				}
			} catch (MalformedURLException e) {
				logger.fatal("Remote Configuration Error! " + remoteBrowserOptions.get(remoteBrowser) , e);
				System.exit(-1);
			}
		} else {
			logger.info("Now just sit back and relax. \nExecution will take place via" + browserMap.get(localBrowser) + "and you'll be informed once everything is done..");
			switch (localBrowser) {
				case "-c":
					webDriver = new ChromeDriver(getLocalChromeOptions());
					break;
				case "-hc":
					webDriver = new ChromeDriver(getLocalChromeHeadLessOptions());
					break;
				case "-e":
					webDriver = new EdgeDriver(getLocalEdgeOptions());
					break;
				case "-ie":
					System.setProperty("webdriver.ie.driver", IeDriverPath);
					webDriver = new InternetExplorerDriver();
					break;
				default:
					logger.info("Local Browser Configuration is invalid and now exiting!!");
					System.exit(0);
			}
		}
		setDriverWaits(webDriver);
		logger.info("Browser session for  Thread  {} => {} ", Thread.currentThread().getName() , webDriver.toString());
		return webDriver;
	}
}