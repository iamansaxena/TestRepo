package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import compontentPages.MedExpressCenters_page;
import junit.framework.Assert;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MedExpressCenters_StepDefinition extends MedExpressCenters_page {

	private String author = "Rekha Vasugan";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(MedExpressCenters_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MedExpressCenters_StepDefinition.class.getName());
		new MedExpressCenters_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("medexpress-centers=") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/medexpressautomationdirectory/medexpresscenters.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/medexpressautomationdirectory/medexpresscenters.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("medexpress-centers=").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		ExtentTestManager.startTest(MedExpressCenters_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("medexpress-centers", author, currentDomain, MedExpressCenters_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MedExpressCenters_StepDefinition.class);
		logger.info("Urls for '" + MedExpressCenters_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MedExpressCenters_StepDefinition.class.getName(), currentDomain);
		driverMap.put(MedExpressCenters_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + MedExpressCenters_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		// mydriver.manage().deleteAllCookies();
	}
	
	@Test(priority = 1, enabled = true)
	public void elementVisibilityCheck(){
		skipNonExistingComponent(urls);
		for(String url:urls){
			urlUnderTest.get().add(url); 
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(medExpressCenterSection));
			scrollToElement(mydriver, medExpressCenterSection, logger);
			customTestLogs.get().add("Verify Med Express Center Section : " + medExpressCenterSection.isDisplayed());
			customTestLogs.get().add("Verify Med Express Center : " + medExpresscenterList.get(0).isDisplayed());
			hardAssert.assertEquals(medExpressCenterSection.isDisplayed(), true);
			hardAssert.assertEquals(medExpresscenterList.get(0).isDisplayed(), true);
			//verifyCenterList(mydriver, logger);
			//mydriver.close();
		}
	}
	
	@Test(priority =2, enabled = true)
	public void verifyCenterDetails(){
		skipNonExistingComponent(urls);
		for(String url:urls){
			urlUnderTest.get().add(url); 
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(medExpressCenterSection));
			scrollToElement(mydriver, medExpressCenterSection, logger);
			hardAssert.assertEquals(verifyCenterList(mydriver , logger), true);
		}
	}
	
}

