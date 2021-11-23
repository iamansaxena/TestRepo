package componentStepDef;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MaterialCardTermDescription_Page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MaterialCardTermDescription_StepDefinition extends MaterialCardTermDescription_Page {
	private String author = "Sai Tummala";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(MaterialCardTermDescription_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MaterialCardTermDescription_StepDefinition.class.getName());
		new MaterialCardTermDescription_Page();

		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ExtentTestManager.startTest(MaterialCardTermDescription_StepDefinition.class.getName());
		setTagForTestClass("MaterialCardTermDesc", author, MaterialCardTermDescription_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MaterialCardTermDescription_StepDefinition.class);
		logger.info(
				"Urls for '" + MaterialCardTermDescription_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MaterialCardTermDescription_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MaterialCardTermDescription_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + MaterialCardTermDescription_StepDefinition.class.getName() + "' =>\n"
				+ driverMap);
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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void cardElementVisibilityCheck(String mcardUrl) {

		skipNonExistingComponent(mcardUrl);

			
			mydriver.get(mcardUrl);

			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mCards));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);

				WebElement title = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mCardTitle)).get(i);
				WebElement description = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mDescription))
						.get(i);

				focusElement(mydriver, description);
				focusElement(mydriver, title);

				if (!description.getText().isEmpty() && !title.getText().isEmpty()) {
					logger.info(" 'Card " + i + " Title: '"
							+ mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mCardTitle)).get(i)
									.getText()
							+ "'\n\t Description ==> "
							+ mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mDescription)).get(i)
									.getText());

				} else {
					fail("Material card found with blank Title/Description");
				}
				i++;

			}


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void buttonVisibilityCheck(String mcardUrl) {
		skipNonExistingComponent(mcardUrl);
		List<WebElement> materialcards = mydriver
				.findElements(By.xpath("//*[@class=\"material-card-term-descption section\"]"));
		int i = 0;
		for (WebElement card : materialcards) {
			scrollToElement(mydriver, card, logger);
			hardAssert.assertFalse(mydriver.findElements(By.xpath(mButton)).get(i).getText().isEmpty());
			
		}
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void learnMoreButtonRedirection(String mcardUrl) {
		skipNonExistingComponent(mcardUrl);

			int i = 0;
			String btnLink = null;
			List<WebElement> buttons;
			
			mydriver.get(mcardUrl);
			currentDomain = currentDomain + "[" + mcardUrl + "]";
			List<WebElement> materialcards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card-term-descption section\"]"));
			i = getRandomInteger(materialcards.size(), 0);
			materialcards.get(i);
			buttons = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mButton));
			try {
				while (btnLink == null || btnLink.isEmpty()) {
					btnLink = buttons.get(i).getAttribute("href");
					i = getRandomInteger(buttons.size(), 0);
				}
				scrollToElement(mydriver, buttons.get(i), logger);
			} catch (Exception e) {

			}

			if (btnLink.isEmpty()) {
				fail("Button Hyperlink is empty");
				logger.error("Button Hyperlink is empty");
			}

			if (btnLink != null) {
				scrollToElement(mydriver, buttons.get(i), logger);
				logger.info("Material Card button link: " + btnLink);
			}


	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void buttonHyperLinkRedirection(String mcardUrl) {
		skipNonExistingComponent(mcardUrl);

			
			mydriver.get(mcardUrl);
			currentDomain = currentDomain + "[" + mcardUrl + "]";
			// List<WebElement> desc = mydriver.findElements(By.xpath(descriptions));
			List<WebElement> cards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card-term-descption section\"]"));

			String currentPage = mydriver.getCurrentUrl();
			String domain = getDomainName(currentPage);
			String currentHandle = mydriver.getWindowHandle();

			int i = getRandomInteger(cards.size(), 0);

			scrollToElement(mydriver, cards.get(i), logger);
			logger.info("Checking Material Term Desc card on : " + currentPage);
			String hyperlink = "";
			try {
				hyperlink = cards.get(i).getAttribute("href");
			} catch (Exception e) {
				hyperlink = null;
			}
			if (hyperlink != null) {
				cards.get(i).click();
				Set<String> allHandles = mydriver.getWindowHandles();
				String hyperlinkDomain = getDomainName(hyperlink);
				if (!hyperlinkDomain.equals(domain)) {
					allHandles.remove(currentHandle);
					mydriver.switchTo().window(allHandles.iterator().next());
					hardAssert.assertNotEquals(mydriver.getWindowHandle(), currentHandle);
					
					mydriver.get(currentPage);
				} else if (hyperlinkDomain.equals(domain)) {
					hardAssert.assertEquals(mydriver.getWindowHandle(), currentHandle);

				}
			}


	}
}
