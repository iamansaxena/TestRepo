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

		LATEST_DRIVER_POOL.put(className, getWebdriverInstance(className));

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

}
