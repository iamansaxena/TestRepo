package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMConfigurationsUtil.Environment;
import static com.optum.dpm.utils.DPMTestUtils.getPresence;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.jsWaitForPageToLoad;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElementWithoutWait;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.BlogFeed_page;

import core.CustomDataProvider;

public class BlogFeed_StepDefinition extends BlogFeed_page {
	private static final Logger logger = LogManager.getLogger(BlogFeed_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

		int i = 0;
		List<WebElement> blogs = mydriver.findElements(By.xpath(feeds));
		for (WebElement blog : blogs) {
			scrollToElementWithoutWait(mydriver, blog);
			getVisibility(mydriver, mydriver.findElements(By.xpath(feedCategories)).get(i), 20);
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedCategories)).get(i),
					mydriver.findElements(By.xpath(feedCategories)).get(i).getText()));
			hardAssert
					.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedContentGlimpse)).get(i),
							mydriver.findElements(By.xpath(feedContentGlimpse)).get(i).getText()));
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedPublishDate)).get(i),
					mydriver.findElements(By.xpath(feedPublishDate)).get(i).getText()));
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i),
					mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i).getText()));
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(feedTitles)).get(i),
					mydriver.findElements(By.xpath(feedTitles)).get(i).getText()));

			i++;
		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void blankDefaultFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

		int i = 0;
		List<WebElement> blogs = mydriver.findElements(By.xpath(feeds));
		for (WebElement blog : blogs) {
			scrollToElementWithoutWait(mydriver, blog);
			getVisibility(mydriver, mydriver.findElements(By.xpath(feedCategories)).get(i), 20);
			hardAssert.assertFalse(
					mydriver.findElements(By.xpath(feedCategories)).get(i).getAttribute("innerText").isEmpty());

			hardAssert.assertFalse(
					mydriver.findElements(By.xpath(feedContentGlimpse)).get(i).getAttribute("innerText").isEmpty());

			hardAssert.assertFalse(
					mydriver.findElements(By.xpath(feedPublishDate)).get(i).getAttribute("innerText").isEmpty());

			hardAssert.assertFalse(
					mydriver.findElements(By.xpath(feedReadMoreLinks)).get(i).getAttribute("innerText").isEmpty());

			hardAssert.assertFalse(
					mydriver.findElements(By.xpath(feedTitles)).get(i).getAttribute("innerText").isEmpty());

			i++;
		}
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void paginationCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		new WebDriverWait(mydriver, 30)
				.until(ExpectedConditions.elementToBeClickable(mydriver.findElement(By.xpath(paginationPages))));

		scrollToElement(mydriver, mydriver.findElements(By.xpath(paginationPages)).get(1), logger);

		mydriver.findElements(By.xpath(paginationPages)).get(1).click();
		new BlogFeed_page();
		pleaseWait(5, logger);
		scrollToElement(mydriver, mydriver.findElements(By.xpath(paginationPages)).get(1), logger);
		hardAssert.assertTrue(
				mydriver.findElements(By.xpath(paginationPages)).get(1).getAttribute("class").contains("is--active"));
		hardAssert.assertTrue(verifyElementExists(logger, paginationPrev, "previous page button"));
		paginationPrev.click();
		new BlogFeed_page();
		try {
			scrollToElement(mydriver, paginationPrev, logger);
			fail("User is on the first page but the previous button of pagination is still available");
		} catch (NoSuchElementException e) {
			logger.info("User get back to the first page");
		}
		jsWaitForPageToLoad(30, mydriver, logger);
		scrollToElement(mydriver, paginationNext, logger);
		paginationNext.click();
		pleaseWait(3, logger);
		hardAssert.assertTrue(
				mydriver.findElements(By.xpath(paginationPages)).get(1).getAttribute("class").contains("is--active"));
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void redirectionToBlogPageCheck(String url) {
		skipNonExistingComponent(url);

		
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

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void blogCategoryOnFeedPageCheck(String url) {
		skipNonExistingComponent(url);

		
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

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void commentFormBlogPageErrorMessagesCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(feedReadMoreLinks)), logger).click();
		jsWaitForPageToLoad(30, mydriver, logger);
		scrollToElement(mydriver, blogpageUserCommentsSection, logger);
		commentFormCommentInputField.sendKeys("lorremIpsum");
		commentFormSubmitButton.click();
		hardAssert.assertTrue(verifyElementExists(logger, commentEmailFieldErrorMessage, "Email field error message"));
		hardAssert.assertTrue(
				verifyElementExists(logger, commentNameFieldErrorMessage, "Name/Author field error message"));
		commentFormCommentInputField.clear();
		commentNameInput.sendKeys("lorremIpsum");
		pleaseWait(5, logger);
		commentFormSubmitButton.click();
		hardAssert.assertTrue(verifyElementExists(logger, commentTextFieldErrorMessage, "Comment field error message"));
	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void blogPageCategoryTagsCheck(String url) {
		skipNonExistingComponent(url);

		
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

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void blankBlogContentCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		String selectedBlog = mydriver.findElements(By.xpath(feedTitles)).get(0).getAttribute("innerText");
		logger.info("Title of selected blog: " + selectedBlog);
		scrollToElement(mydriver, mydriver.findElements(By.xpath(feedReadMoreLinks)).get(0), logger).click();
		jsWaitForPageToLoad(30, mydriver, logger);
		scrollToElement(mydriver, blogPageContent, logger);
		if (blogPageContent.getText().isEmpty() || blogPageContent.getText() == null) {
			fail("Blog doesn't have any content");
		}

	}

	@Test(priority = 9, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void blankPageDefaultFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
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

	@Test(priority = 10, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void blogPageTagsRedirection(String url) {
		skipNonExistingComponent(url);

		
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

	@Test(priority = 11, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void likeFunctionalityCheck(String url) {
		skipNonExistingComponent(url);
		if (Environment.trim().equalsIgnoreCase("test") || Environment.trim().equalsIgnoreCase("uat")) {
			throw new SkipException("Can't execute this method on this environment as sign option not available here");
		}

		mydriver.manage().deleteAllCookies();
		
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
		pleaseWait(3, logger);
		hardAssert.assertTrue(newLikeCounts != likeCounts);

	}

	@Test(priority = 12, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" }, dependsOnMethods = "likeFunctionalityCheck")
	public void unlikeFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		if (Environment.trim().equalsIgnoreCase("test") || Environment.trim().equalsIgnoreCase("uat")) {
			throw new SkipException("Can't execute this method on this environment as sign option not available here");
		}
		
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
		hardAssert.assertNotEquals(newLikeCounts, likeCounts);

	}

	@Test(priority = 13, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"blog-feed" })
	public void commentFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
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
