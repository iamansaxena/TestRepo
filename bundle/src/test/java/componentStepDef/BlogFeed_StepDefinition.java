package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.BlogFeed_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class BlogFeed_StepDefinition extends BlogFeed_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(BlogFeed_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(BlogFeed_StepDefinition.class.getName());
		new BlogFeed_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("blog-feed") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/blogdetails.html?post=1685");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/blogdetails.html?post=1685");
			}
		} else {
			String[] scannedUrls = fetchUrl("blog-feed").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(BlogFeed_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("BlogFeed", author, currentDomain, BlogFeed_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(BlogFeed_StepDefinition.class);
		logger.info("Urls for '" + BlogFeed_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(BlogFeed_StepDefinition.class.getName(), currentDomain);

		driverMap.put(BlogFeed_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + BlogFeed_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	public void elementVisibilityCheck() {
		skipNonExistingComponent(cardUrls);

		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);

			int i = 0;
			List<WebElement> blogs = mydriver.findElements(By.xpath(feeds));
			for (WebElement blog : blogs) {
				scrollToElementWithoutWait(mydriver, blog);
				getVisibility(mydriver, mydriver.findElements(By.xpath(feedCategories)).get(i), 20);
				hardAssert
						.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedCategories)).get(i),
								mydriver.findElements(By.xpath(feedCategories)).get(i).getText()));
				hardAssert.assertTrue(
						verifyElementExists(logger, mydriver.findElements(By.xpath(feedContentGlimpse)).get(i),
								mydriver.findElements(By.xpath(feedContentGlimpse)).get(i).getText()));
				hardAssert
						.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedPublishDate)).get(i),
								mydriver.findElements(By.xpath(feedPublishDate)).get(i).getText()));
				hardAssert.assertTrue(
						verifyElementExists(logger, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i),
								mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i).getText()));
				hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedTitles)).get(i),
						mydriver.findElements(By.xpath(feedTitles)).get(i).getText()));

				i++;
			}
		}

	}

	@Test(priority = 2, enabled = true)
	public void blankDefaultFieldsCheck() {
		skipNonExistingComponent(cardUrls);

		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);

			int i = 0;
			List<WebElement> blogs = mydriver.findElements(By.xpath(feeds));
			for (WebElement blog : blogs) {
				scrollToElementWithoutWait(mydriver, blog);
				getVisibility(mydriver, mydriver.findElements(By.xpath(feedCategories)).get(i), 20);
				hardAssert.assertFalse(mydriver.findElements(By.xpath(feedCategories)).get(i).getAttribute("innerText").isEmpty());

				hardAssert.assertFalse(mydriver.findElements(By.xpath(feedContentGlimpse)).get(i).getAttribute("innerText").isEmpty());

				hardAssert.assertFalse(mydriver.findElements(By.xpath(feedPublishDate)).get(i).getAttribute("innerText").isEmpty());

				hardAssert.assertFalse(mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i).getAttribute("innerText").isEmpty());

				hardAssert.assertFalse(mydriver.findElements(By.xpath(feedTitles)).get(i).getAttribute("innerText").isEmpty());

				i++;
			}
		}

	}

	@Test(priority = 3, enabled = true)
	public void paginationCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			new WebDriverWait(mydriver, 30).until(ExpectedConditions.elementToBeClickable(mydriver.findElement(By.xpath(paginationPages))));
			
			scrollToElement(mydriver, mydriver.findElements(By.xpath(paginationPages)).get(1), logger);

			mydriver.findElements(By.xpath(paginationPages)).get(1).click();
			new BlogFeed_page();
			pleaseWait(5, logger);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(paginationPages)).get(1), logger);
			hardAssert.assertTrue(mydriver.findElements(By.xpath(paginationPages)).get(1).getAttribute("class")
					.contains("is--active"));
			hardAssert.assertTrue(verifyElementExists(logger, paginationPrev, "previous page button"));
			paginationPrev.click();
			new BlogFeed_page();
			try {
				scrollToElement(mydriver, paginationPrev, logger);
				fail("User is on the first page but the previous button of pagination is still available");
			} catch (NoSuchElementException e) {
				logger.info("User get back to the first page");
			}
			jsWaitForPageToLoad(30, mydriver, logger);scrollToElement(mydriver, paginationNext, logger);
			paginationNext.click();
			pleaseWait(3, logger);
			hardAssert.assertTrue(mydriver.findElements(By.xpath(paginationPages)).get(1).getAttribute("class")
					.contains("is--active"));
		}
	}

	@Test(priority = 4, enabled = true)
	public void redirectionToBlogPageCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			int i = getRandomInteger(mydriver.findElements(By.xpath(feedTitles)).size(), 0);
			String expectedTitle = mydriver.findElements(By.xpath(feedTitles)).get(i).getAttribute("innerText");
			String expectedLink = mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i).getAttribute("href");
			logger.info("Selected blog title: " + expectedTitle + "\n\nLink to blog: " + expectedLink);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i), logger).click();
			getPresence(mydriver, blogPageTitle.toString().split("xpath: ")[1], 20);
			scrollToElement(mydriver, blogPageTitle, logger);
			hardAssert.assertEquals(blogPageTitle.getText(), expectedTitle);
			hardAssert.assertEquals(mydriver.getCurrentUrl().split("\\?")[1], expectedLink.split("\\?")[1]);

		}
	}

	@Test(priority = 5, enabled = true)
	public void blogCategoryOnFeedPageCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			if (!url.contains("?segment=")) {
				throw new SkipException("Unfiltered Blogfeed page");
			}
			List<WebElement> blogsCategories = mydriver.findElements(By.xpath(feedCategories));
			String categories = blogsCategories.get(0).getAttribute("innerText");
			for (WebElement blogsCategory : blogsCategories) {
				scrollToElement(mydriver, blogsCategory, logger);
				hardAssert.assertTrue(blogsCategory.getText().contains(categories));
			}
		}
	}

	@Test(priority = 6, enabled = true)
	public void commentFormBlogPageErrorMessagesCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, mydriver.findElement(By.xpath(feedReadMoreLinks)), logger).click();
jsWaitForPageToLoad(30, mydriver, logger);	
scrollToElement(mydriver, blogpageUserCommentsSection, logger);
			commentFormCommentInputField.sendKeys("lorremIpsum");
			commentFormSubmitButton.click();
			hardAssert.assertTrue(
					verifyElementExists(logger, commentEmailFieldErrorMessage, "Email field error message"));
			hardAssert.assertTrue(
					verifyElementExists(logger, commentNameFieldErrorMessage, "Name/Author field error message"));
			commentFormCommentInputField.clear();
			commentNameInput.sendKeys("lorremIpsum");
			pleaseWait(5, logger);
			commentFormSubmitButton.click();
			hardAssert.assertTrue(
					verifyElementExists(logger, commentTextFieldErrorMessage, "Comment field error message"));
		}
	}

	@Test(priority = 7, enabled = true)
	public void blogPageCategoryTagsCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
			logger.info("Title of selected blog: " + selectedBlog);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(blogPageTagsLink)), logger);
			} catch (Exception e) {
				throw new SkipException("There's no tag field authored on blog detail page");
			}
			List<WebElement> tags = mydriver.findElements(By.xpath(blogPageTagsLink));
			for (WebElement tag : tags) {
				scrollToElementWithoutWait(mydriver, tag);

				if (tag.getText().isEmpty() || tag.getText() == null) {

					fail("blank tag");
				}
				String tagText = tag.getText();
				if (tag.getAttribute("href").isEmpty() || tag.getAttribute("href") == null) {
					fail("Tag " + tagText + " doesn't have any link");
				}

			}

		}
	}

	@Test(priority = 8, enabled = true)
	public void blankBlogContentCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
			logger.info("Title of selected blog: " + selectedBlog);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();
jsWaitForPageToLoad(30, mydriver, logger);			scrollToElement(mydriver, blogPageContent, logger);
			if (blogPageContent.getText().isEmpty() || blogPageContent.getText() == null) {
				fail("Blog doesn't have any content");
			}

		}
	}

	@Test(priority = 9, enabled = true)
	public void blankPageDefaultFieldsCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
			logger.info("Title of selected blog: " + selectedBlog);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();
			getVisibility(mydriver, blogPageTitle, 30);
			if (blogPagePublishDate.getText().isEmpty() || blogPagePublishDate.getText() == null) {
				
			}
			if (blogPageTitle.getText().isEmpty() || blogPageTitle.getText() == null) {
				
			}
			if (blogPageCategory.getText().isEmpty() || blogPageCategory.getText() == null) {
				
			}
		}
	}

	@Test(priority = 10, enabled = true)
	public void blogPageTagsRedirection() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
			logger.info("Title of selected blog: " + selectedBlog);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();
			try {
				mydriver.findElement(By.xpath(blogPageTagsLink)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no tag field authored on blog detail page");
			}
			List<WebElement> tags = mydriver.findElements(By.xpath(blogPageTagsLink));
			int i = getRandomInteger(tags.size(), 0);

			String expectedURL = tags.get(i).getAttribute("href");
			scrollToElement(mydriver, tags.get(i), logger).click();
			pleaseWait(5, logger);
			hardAssert.assertEquals(mydriver.getCurrentUrl(), expectedURL);

		}
	}

	@Test(priority = 11, enabled = true)
	public void likeFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);
		if (Environment.trim().equalsIgnoreCase("test")||Environment.trim().equalsIgnoreCase("uat")) {
			throw new SkipException("Can't execute this method on this environment as sign option not available here");
		}
		for (String url : cardUrls) {
			mydriver.manage().deleteAllCookies();
			urlUnderTest.get().add(url);
			mydriver.get(url);

			String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
			logger.info("Title of selected blog: " + selectedBlog);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();
			pleaseWait(5, logger);

			int likeCounts = Integer.valueOf(blogLikeCounter.getAttribute("innerText"));
			scrollToElement(mydriver, blogPageLikeButton, logger).click();
			softAssert.assertTrue(mydriver.getCurrentUrl().contains("wordpress"));
			wpEmailInput.sendKeys("optumwp");
			wpSubmitButton.click();
			wpPasswordInput.sendKeys("Optum@123");
			wpSubmitButton.click();
			pleaseWait(5, logger);
			wpSubmitButton.click();
			getPresence(mydriver, blogPageTitle.toString().split("xpath: ")[1], 20);
			scrollToElement(mydriver, blogPageTitle, logger);
			hardAssert.assertEquals(blogPageTitle.getText(), selectedBlog);
			int newLikeCounts = Integer.valueOf(blogLikeCounter.getText());
			blogPageLikeButton.click();
			hardAssert.assertTrue(newLikeCounts != likeCounts);

		}
	}

	@Test(priority = 12, enabled = true)
	public void unlikeFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {
			if (Environment.trim().equalsIgnoreCase("test")||Environment.trim().equalsIgnoreCase("uat")) {
				throw new SkipException("Can't execute this method on this environment as sign option not available here");
			}
			urlUnderTest.get().add(url);
			mydriver.get(url);
			String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
			logger.info("Title of selected blog: " + selectedBlog);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();

			scrollToElement(mydriver, blogPageLikeButton, logger).click();
			pleaseWait(5, logger);
			int likeCounts = Integer.valueOf(blogLikeCounter.getText());
			blogPageLikeButton.click();
			pleaseWait(5, logger);
			int newLikeCounts = Integer.valueOf(blogLikeCounter.getText());
			hardAssert.assertNotEquals(newLikeCounts , likeCounts);

		}
	}

	@Test(priority = 13, enabled = true)
	public void commentFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);
		for (String url : cardUrls) {

			urlUnderTest.get().add(url);
			mydriver.get(url);
			String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
			logger.info("Title of selected blog: " + selectedBlog);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();

			jsWaitForPageToLoad(30, mydriver, logger);		
			scrollToElement(mydriver, commentFormCommentInputField, logger);
			commentNameInput.sendKeys("Test User");
			commentEmailInput.sendKeys("aman.mohan@optum.com");
			commentFormCommentInputField.sendKeys("This is a test comment!");

			commentFormSubmitButton.click();
			// getVisibility(mydriver, commentSuccessMsg, 10);

		}
	}

}
