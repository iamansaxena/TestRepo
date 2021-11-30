package visualTestSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;

import compontentPages.PluV2_page;
import core.Base;

public class VisualTest extends PluV2_page {

	private static EyesRunner runner;
	private static Eyes eyes;
	private static BatchInfo batch;

	@BeforeClass
	public static void setBatch() {
		// Must be before ALL tests (at Class-level)
		batch = new BatchInfo("PLUV2");
	}

	@AfterClass
	public static void tearUp() {
		mydriver.quit();
		eyes.abortAsync();
	}

	@AfterMethod
	public static void closeTest() {
		eyes.abortIfNotClosed();
	}

	@SuppressWarnings("static-access")
	@BeforeTest
	public void beforeEach() {
		// Initialize the Runner for your test.
		Base.initialize();
		runner = new ClassicRunner();
		Configuration ddd = new Configuration();
		ddd.setUseDom(true);

		eyes = new Eyes(runner);
		eyes.setConfiguration(ddd);
		eyes.setApiKey("90Y5yJnd105KXbLb4C2DEwqi104uDvNe3S78GjFFBI105dGFA110");
		batch = new BatchInfo("PLUV2");
		// set batch name
		eyes.setBatch(batch);

		System.setProperty("webdriver.chrome.driver", "./Drivers/chrome64_56.0.2924.87/chromedriver.exe");
		// Use Chrome browser
		mydriver = new ChromeDriver();
		mydriver.manage().window().maximize();
		eyes.setExplicitViewportSize(eyes.getViewportSize(mydriver));
		eyes.setForceFullPageScreenshot(true);
		eyes.setStitchMode(StitchMode.CSS);
		eyes.setMatchLevel(MatchLevel.STRICT);
		eyes.setSendDom(true);
		new PluV2_page();
	}

	@Test(priority = 1)
	public void intakeTest() {

		eyes.open(mydriver, "Intake", "Intake Page");
		eyes.setSendDom(true);
		mydriver.get("http://apvrt31468:4503/content/automation-directory/plu-v2-feature/intake-form-everett.html"); // for
		getWebDriverWait(mydriver, 40).until(ExpectedConditions
				.visibilityOf(mydriver.findElement(By.xpath("//*[@class=\"pluv2-intake-form section\"]"))));
		eyes.checkElement(By.xpath("//*[@class=\"pluv2-intake-form section\"]"), "Intake form (CLT)"); // CLT =
		eyes.checkWindow("Intake form (PLT)", true); // CLT = Page Level Test
		eyes.closeAsync();
		// TestResultsSummary allTestResults = runner.getAllTestResults();
	}

	@Test(priority = 2, enabled = true)
	public void serpTest() {
		eyes.open(mydriver, "Serp", "Serp Page");
		eyes.setSendDom(true);
		searchField.click();
		searchField.click();
		System.out.println("sending keys");
		searchField.sendKeys("elizabeth");
		System.out.println("sended keys");
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(searchSuggestion));
		searchSuggestion.click();
		searchButton.click();
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(serpPage));
		eyes.checkElement(By.xpath("//*[@class=\"pluv2-serp-form section\"]"), "Serp page (CLT)"); // CLT = Component
		eyes.checkWindow("Serp page (PLT)", true); // CLT = Page Level Test
		eyes.closeAsync();
		// TestResultsSummary allTestResults = runner.getAllTestResults();
	}

	@Test(priority = 3, enabled = true)
	public void providerPageTest() {
		eyes.open(mydriver, "Provider Page", "Provider Details Page");
		eyes.setSendDom(true);
		resultProviderCardNames.get(0).click();
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(providerDetailPage));
		eyes.checkElement(providerDetailPage, "Provider Details page (CLT)"); // CLT = Component Level Test
		eyes.checkWindow("Provider Details (PLT)", true); // CLT = Page Level Test
		eyes.closeAsync();
		// TestResultsSummary allTestResults = runner.getAllTestResults();
	}

	@Test(priority = 4, enabled = true)
	public void LocationDetailsPageTest() {
		eyes.open(mydriver, "Locaiton Page", "Location Details Page");
		eyes.setSendDom(true);
		mydriver.navigate().back();
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(serpPage));
		locationTab.click();
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(locationSerpResultSection));
		resultLocationCardNames.get(0).click();
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(locationDetailPage));
		eyes.checkElement(locationDetailPage, "Location Details page (CLT)"); // CLT = Component Level Test
		eyes.checkWindow("Location Details (PLT)", true); // CLT = Page Level Test
		eyes.closeAsync();
		// TestResultsSummary allTestResults = runner.getAllTestResults();
	}

}
// mydriver.get("http://apvrt31468:4503/content/automation-directory/plu-v2-feature/intake-form-prohealth-ct.html");
// mydriver.get("http://apvrt31468:4503/content/automation-directory/plu-v2-feature/intake-form-ahni.html");