package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MaterialCardImageDetail_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MaterialCardImageDetail_stepDefinition extends MaterialCardImageDetail_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;
	JSONArray links = new JSONArray();// = {
	// "http://apvrt31468:4503/content/AutomationDirectory/Material_Card_Image_Detail.html"
	// };
	// "http://test-cm.optum.com/content/optum3/pt_br/test/Material_Card_Image_Detail.html?wcmmode=disabled"
	// };

	@BeforeClass
	public void setup() {
		fetchSession(MaterialCardImageDetail_page.class);
		mydriver = LATEST_DRIVER_POOL.get(MaterialCardImageDetail_page.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new MaterialCardImageDetail_page();
		setTagForTestClass("MaterialCardImageDetail", author, MaterialCardImageDetail_stepDefinition.class.getName());
		ExtentTestManager.startTest(MaterialCardImageDetail_stepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MaterialCardImageDetail_stepDefinition.class);
		logger.info("Urls for '" + MaterialCardImageDetail_stepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MaterialCardImageDetail_stepDefinition.class.getName(), currentDomain);

		driverMap.put(MaterialCardImageDetail_stepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info(
				"Browser pool at '" + MaterialCardImageDetail_stepDefinition.class.getName() + "' =>\n" + driverMap);
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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-image-detail-cta"})
	public void elementVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);

			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardImageDetail_page.cards));

			scrollToElement(mydriver, cards.get(0), logger);
			hardAssert.assertEquals(mydriver
					.findElements(By.xpath(
							"//*[contains(@class,\"material-card-image-detail\")]//*[@class=\"mcard__imgcta__title\"]"))
					.size(), cards.size());



	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-image-detail-cta"})
	public void cardLinkRedirectionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);
			Set<String> tabs = mydriver.getWindowHandles();
			List<WebElement> cards = mydriver.findElements(By.xpath("//a[contains(@class,\"cta-link\")]"));
			int i = getRandomInteger(cards.size(), 0);
			scrollToElement(mydriver, cards.get(i), logger);
			String expUrl = cards.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			cards.get(i).click();;
			pleaseWait(4, logger);
			jsWaitForPageToLoad(10,mydriver, logger);
			assertRedirection(mydriver, logger, getDomainName(cardUrl), expUrl, handle);
		


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-image-detail-cta"})
	public void blankDescriptionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);

			List<WebElement> descriptions = mydriver.findElements(By.xpath(MaterialCardImageDetail_page.descriptions));
			for (WebElement description : descriptions) {
				scrollToElement(mydriver, description, logger);
				if (description.getText().isEmpty()) {
					fail("Found a material image detail card having a blank description ");
				} else {
					logger.info("Description ==> " + description.getText());
				}

			}

	}
//DISABLING THIS AS CONTENT CHECK IS NOT IN SCOPE
/*	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-image-detail-cta"})
	public void cardBrokenImageCheck() {
		skipNonExistingComponent(cardUrls);
		for (String cardUrl : cardUrls) {
			customLogsPool.get().add(cardUrl); mydriver.get(cardUrl);

			List<WebElement> images = mydriver.findElements(By.xpath(MaterialCardImageDetail_page.cardImages));
			int i = getRandomInteger(images.size(), 0);
			scrollToElement(mydriver, images.get(i));
			String imageLink = images.get(i).getAttribute("src");
			logger.info("Card Image Link ==>" + imageLink);
			customLogsPool.get().add(cardUrl); mydriver.get(imageLink);
			try {
				URL url = new URL(imageLink);
				logger.info("Image Link => " + url);
			} catch (MalformedURLException e) {
				fail("Image link is invalid on material Image detail card");
				logger.error("Image link is invalid on material Image detail card");
			}

		}
	}*/

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-image-detail-cta"})
	public void blankCardTitleCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);
			// *[@class="mcard__image-container"]/following-sibling::div[1]
			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardImageDetail_page.cards));
			int i = 1;

			for (i = 1; i <= cards.size(); i++) {
				String withTitle = "(//*[contains(@class,\"material-card-image-detail\")])[" + i
						+ "]//*[@class=\"mcard__imgcta__title\"]";
				String withoutTitle = "(//*[@class=\"material-card-image-detail-cta section\"])[" + i
						+ "]//*[@class=\"mcard__imgcta__title\"]/span";
				WebElement title = mydriver.findElement(By.xpath(withTitle));
				scrollToElement(mydriver, title, logger);
				if (!title.getText().isEmpty()) {

					logger.info("Material Image Details Card Title ==> " + title.getText());
				} else if (title.getText().isEmpty()) {
					String noTitle = mydriver.findElement(By.xpath(withoutTitle)).getText();
					if (!noTitle.isEmpty()) {
						fail("Found A Material Image Detail card not having even a default title");
					} else {
						fail("Card with no blank title field");
					}
				}

			}

	}

//	Disabling this as UX is not in the scope for now
	/*@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-image-detail-cta"})
	public void cardContentAlignmentCheck() {
		skipNonExistingComponent(cardUrls);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}
		for (String cardUrl : cardUrls) {
			customLogsPool.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> contents = mydriver
					.findElements(By.xpath("//*[@class=\"mcard__image-container\"]/following-sibling::div[1]"));
			scrollToElement(mydriver, contents.get(0));
			String[] alignmentOption = contents.get(0).getAttribute("class").split(" ");
			String expectedAlignment = alignmentOption[alignmentOption.length - 1];
			int i = 1;

			for (WebElement content : contents) {
				scrollToElement(mydriver, content);
				WebElement title = mydriver.findElement(By.xpath(
						"(//*[contains(@class,\"material-card-image-detail\")]//*[@class=\"mcard__imgcta__title\"])["
								+ i + "]"));
				logger.info("Card Title ==> " + title.getText());
				focusElement(mydriver, content);
				if (content.getAttribute("class").contains(expectedAlignment)) {

				} else {
					fail("Uneven Alignment");
				}
				i++;
			}
		}
	}*/
}
