package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.LeadersHub_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class LeadersHub_StepDefinition extends LeadersHub_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(LeadersHub_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(LeadersHub_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new LeadersHub_page();
		ExtentTestManager.startTest(LeadersHub_StepDefinition.class.getName());
		setTagForTestClass("Resource Library Sidebar", author, LeadersHub_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(LeadersHub_StepDefinition.class);
		logger.info("Urls for '" + LeadersHub_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(LeadersHub_StepDefinition.class.getName(), currentDomain);

		driverMap.put(LeadersHub_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + LeadersHub_StepDefinition.class.getName() + "' =>\n" + driverMap);
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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"leadership-hub"})
	public void elementVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			 mydriver.get(cardUrl);
			List<WebElement> leaders = mydriver.findElements(By.xpath(leaderCard));
			List<WebElement> leaderNames = mydriver.findElements(By.xpath(leaderName));
			List<WebElement> leaderTitles = mydriver.findElements(By.xpath(leaderTitle));
			List<WebElement> leaderImages = mydriver.findElements(By.xpath(LeaderImage));
			List<WebElement> viewBioLinks = mydriver.findElements(By.xpath(viewBio));
			logger.info("Leader Cards => " + leaders.size());
			logger.info("Names => " + leaderNames.size());
			logger.info("Titles => " + leaderTitles.size());
			logger.info("Images => " + leaderImages.size());
			logger.info("ViewBio => " + viewBioLinks.size());
			softAssert.assertEquals(leaderNames.size(), leaders.size());
			softAssert.assertEquals(leaderTitles.size(), leaders.size());
			softAssert.assertEquals(leaderImages.size(), leaders.size());
			softAssert.assertEquals(viewBioLinks.size(), leaders.size());
			softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"leadership-hub"})
	public void viewBioRedirectionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			 mydriver.get(cardUrl);
			List<WebElement> viewBioLinks = mydriver.findElements(By.xpath(viewBio));
			List<WebElement> leaderNames = mydriver.findElements(By.xpath(leaderName));
			int i = getRandomInteger(viewBioLinks.size() - 1, 0);
			String[] leaderName = leaderNames.get(i).getText().split(" ");
			String url = mydriver.getCurrentUrl();
			scrollToElement(mydriver, viewBioLinks.get(i), logger).click();
			String expectedURL = mydriver.getCurrentUrl().split(url.split(".html")[0])[1];
			boolean found = false;
			for (String a : leaderName) {
				if (expectedURL.contains(a.toLowerCase())) {
					found = true;
				}

			}
			if (found == false) {
				fail("URL was expected to include '" + leaderName + "'" + "But actual url was '" + expectedURL + "'");
			}
	}
// Disabling this as only functional Testing is in our scope
//	@Test(priority = 3, enabled = true)
//	public void hubItemHoverEffectCheck() {
//		skipNonExistingComponent(cardUrls);
//
//		for (String cardUrl : cardUrls) {
//			customLogsPool.get().add(cardUrl); mydriver.get(cardUrl);
//			List<WebElement> cardName = mydriver.findElements(By.xpath(leaderName));
//			List<WebElement> viewbio = mydriver.findElements(By.xpath(viewBio));
//			AShot a = new AShot();
//			int i = getRandomInteger(cardName.size() - 1, 0);
//			scrollToElement(mydriver, cardName.get(i));
//			BufferedImage imgExp = a.takeScreenshot(mydriver, viewbio.get(i)).getImage();
//			pleaseWait(8, logger);
//			mouseWithJS(mydriver.findElements(By.xpath(LeaderCardHover)).get(i), mydriver);
////			act.moveToElement(cards.get(i)).perform();
//			pleaseWait(8, logger);
//			BufferedImage imgAct = a.takeScreenshot(mydriver, viewbio.get(i)).getImage();
//			ImageDiffer imgUtil = new ImageDiffer();
//			ImageDiff flag = imgUtil.makeDiff(imgExp, imgAct);
//			hardAssert.assertTrue(flag.hasDiff());
//		}
//	}
}
