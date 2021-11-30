package visualTestSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import compontentPages.PluV2_page;
import core.Base;
@SuppressWarnings("rawtypes")
public class SauceVisual extends PluV2_page{

	private static String Environment = "Stage";
	static DesiredCapabilities tCaps = new DesiredCapabilities();
	static HashMap visualOptions = new HashMap<>();
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setBatch() {
		
		
		
	
	}

	@AfterClass
	public static void tearUp() {
		mydriver.quit();
//		eyes.abortAsync();
	}

	@AfterMethod
	public static void closeTest() {
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@BeforeTest
	public void beforeEach() throws MalformedURLException {
		// Initialize the Runner for your test.
		Base.initialize();
		System.setProperty("webdriver.chrome.driver", "./Drivers/chrome64_56.0.2924.87/chromedriver.exe");
		// Use Chrome browser
//		tChromeOptions.set(new ChromeOptions());
//		tChromeOptions.get().setCapability("sauce:options", capability);
		
//		aman.mohan
		
		
		
		DesiredCapabilities extraCapabilities = new DesiredCapabilities();
		MutableCapabilities sauceVisual = new MutableCapabilities();
		extraCapabilities.setCapability("browserName", new ChromeOptions().getBrowserName());
		sauceVisual.setCapability("apiKey", "94480970-67fb-4d29-aa1a-d702dc30f2ef");
		sauceVisual.setCapability("projectName", "optum-poc");
		sauceVisual.setCapability("viewportSize", "1280x1024");
//		sauceVisual.setCapability("launchSauceConnect", "true");
		extraCapabilities.setCapability("idleTimeout", SAUCE_SESSION_TIMEOUT);
		extraCapabilities.setCapability("maxDuration", MAX_DURATION);
		extraCapabilities.setCapability("parentTunnel", parentTunnel);
		extraCapabilities.setCapability("tunnelIdentifier", tunnelIdentifier);
		extraCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		extraCapabilities.setCapability("screenResolution", "1920x1200");
		DesiredCapabilities sauceCreds = new DesiredCapabilities();
		sauceCreds.setCapability("username", "sso-optum-aman.mohan");
		sauceCreds.setCapability("accesskey", "330c2b7b-bf0c-4d43-8793-6748808355aa");
//		sauceCreds.setCapability("launchSauceConnect", "true");
		
		ChromeOptions option = new ChromeOptions();
		option.setCapability("sauce:options", sauceCreds);
		option.setCapability("sauce:visual", sauceVisual);
		option.merge(extraCapabilities);
		visualOptions.put("branch", Environment);
		visualOptions.put("baseBranch", "OCAC Component");
		visualOptions.put("failOnNewStates", true);
		visualOptions.put("scrollAndStitchScreenshots", true);
		visualOptions.put("cropTo", "[class='pluv2-intake-form section']");
		
		System.out.println(option);
		mydriver = new RemoteWebDriver( new URL("https://hub.screener.io:443/wd/hub"),option);
		mydriver.manage().window().maximize();

		new PluV2_page();
	}

	@Test(priority = 1)
	public void simpleTest() {
		
		mydriver.get("https://stg-empire.optum.com/content/automation-directory/plu-v2-feature/intake-form-everett.html");
//		mydriver.get("https://stg-empire.optum.com/content/automation-directory/plu-v2-feature/intake-form-ahni.html");
		getWebDriverWait(mydriver, 50).pollingEvery(60,TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(mydriver.findElement(By.xpath("//*[@class=\"pluv2-search\"]"))));
		  JavascriptExecutor js = (JavascriptExecutor) mydriver;
		  js.executeScript("/*@visual.init*/", "PLU_V2" ,visualOptions);
		  js.executeScript("/*@visual.snapshot*/", "Intake page",visualOptions);
		  js.executeScript("/*@visual.end*/");
		}
	
	

	@Test(priority = 2, enabled = true)
	public void serpTest() {

	}

	@Test(priority = 3, enabled = true)
	public void providerPageTest() {

	}

	@Test(priority = 4, enabled = true)
	public void LocationDetailsPageTest() {

	}

}
// mydriver.get("http://apvrt31468:4503/content/automation-directory/plu-v2-feature/intake-form-prohealth-ct.html");
// mydriver.get("http://apvrt31468:4503/content/automation-directory/plu-v2-feature/intake-form-ahni.html");
