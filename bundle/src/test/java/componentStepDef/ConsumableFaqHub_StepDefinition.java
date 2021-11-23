package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ConsumableFaqHub_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ConsumableFaqHub_StepDefinition extends ConsumableFaqHub_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(ConsumableFaqHub_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ConsumableFaqHub_StepDefinition.class.getName());
		new ConsumableFaqHub_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("consumable-faq") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/consumable-faq-hub.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/consumable-faq-hub.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("consumable-faq").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(ConsumableFaqHub_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("BlogFeed", author, currentDomain, ConsumableFaqHub_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ConsumableFaqHub_StepDefinition.class);
		logger.info("Urls for '" + ConsumableFaqHub_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ConsumableFaqHub_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ConsumableFaqHub_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ConsumableFaqHub_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	public void sectionHeaderVisibilityCheck() {
		skipNonExistingComponent(cardUrls);

		for (String url : cardUrls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			scrollToElement(mydriver, sectionContainer, logger);
			focusElement(mydriver, sectionHeader);
			softAssert.assertTrue(verifyElementExists(logger, sectionHeader,
					"Section Heading ::> " + sectionHeader.getAttribute("innerText")));
			softAssert.assertFalse(sectionHeader.getAttribute("innerText").isEmpty());
			softAssert.assertAll();
		}
	}

	@Test(priority = 2, enabled = true)
	public void questionTitleConditionalCheck() {
		skipNonExistingComponent(cardUrls);

		for (String url : cardUrls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			scrollToElement(mydriver, sectionContainer, logger);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(questionsText)), logger);
			} catch (Exception e) {
				throw new SkipException("There're no question fields available");
			}
			List<WebElement> questions = mydriver.findElements(By.xpath(questionsText));
			int i = 1;
			for (WebElement question : questions) {
				scrollToElement(mydriver, question, logger);
				softAssert.assertTrue(verifyElementExists(logger, question,
						"Question " + i + "::> " + question.getAttribute("innerText")));
				softAssert.assertFalse(question.getAttribute("innerText").isEmpty());
				softAssert.assertAll();
				i++;

			}

		}
	}

	@Test(priority = 3, enabled = true)
	public void questionDropDownFunctionalityConditionalCheck() {
		skipNonExistingComponent(cardUrls);

		for (String url : cardUrls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			scrollToElement(mydriver, sectionContainer, logger);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(questionsText)), logger);
			} catch (Exception e) {
				throw new SkipException("There're no question fields available");
			}
			List<WebElement> questions = mydriver.findElements(By.xpath(questionsText));
			List<WebElement> answers = mydriver.findElements(By.xpath(answerText));

			scrollToElement(mydriver, questions.get(0), logger);
			questions.get(0).click();
			pleaseWait(2, logger);
			hardAssert.assertTrue(verifyElementExists(logger, answers.get(0), "Answers fields"));
			focusElement(mydriver, mydriver.findElement(By.xpath(questionsClose)));
			mydriver.findElement(By.xpath(questionsClose)).click();
			pleaseWait(2, logger);
			try {
				if (answers.get(0).isDisplayed()) {
					logger.error("Tested Faq question ::> " + questions.get(0).getAttribute("innerText"));
					fail("Question section is not collapsing on clicking close button");
				}
			} catch (Exception e) {
			}

		}
	}

	@Test(priority = 4, enabled = true)
	public void faqAnswerSectionConditionalCheck() {
		skipNonExistingComponent(cardUrls);

		for (String url : cardUrls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			scrollToElement(mydriver, sectionContainer, logger);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(questionsText)), logger);
			} catch (Exception e) {
				throw new SkipException("There're no question fields available");
			}
			List<WebElement> questions = mydriver.findElements(By.xpath(questionsText));
			List<WebElement> answers = mydriver.findElements(By.xpath(answerText));
			int i = getRandomInteger(questions.size(), 0);
				scrollToElement(mydriver, questions.get(i), logger);
				questions.get(i).click();
				pleaseWait(3, logger);
				softAssert.assertTrue(verifyElementExists(logger, answers.get(i), "Answers fields ::> "+answers.get(i).getAttribute("innerText")));
				softAssert.assertFalse(answers.get(i).getAttribute("innerText").isEmpty());
				softAssert.assertAll();
		}
	}
	
}
