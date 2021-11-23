package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MCL_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MCL_StepDefinition extends MCL_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		
		fetchSession(MCL_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MCL_StepDefinition.class.getName());
		new MCL_page();
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("mcl-library") == null) {
			if (Environment.equalsIgnoreCase("stage")) {

			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("https://library.optum.com/media-assets-library.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("mcl-library").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(MCL_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("MCL", author, currentDomain, MCL_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MCL_StepDefinition.class);
		logger.info("Urls for '" + MCL_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MCL_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MCL_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + MCL_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		try {
			clearFilters();
		} catch (Exception e) {

		}
		softAssert = new SoftAssert();
		// mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true)
	public void elementVisiblityCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			visitMainSearchPage(cardUrls.get(0));
			// getVisibility(mydriver, mydriver.findElement(By.xpath(resultCardNames)), 10);
			scrollToElement(mydriver, sortDropDown, logger);
			softAssert.assertTrue(verifyElementExists(logger, sortDropDown, "Sort drop down"));
			scrollToElement(mydriver, mydriver.findElement(By.xpath(resultCardNames)), logger);
			softAssert.assertTrue(verifyElementExists(logger, mydriver.findElement(By.xpath(resultCardNames)),
					"Resource Hub Section"));
			scrollToElement(mydriver, mydriver.findElement(By.xpath(filterCategory)), logger);
			softAssert.assertTrue(
					verifyElementExists(logger, mydriver.findElement(By.xpath(filterCategory)), "Filter Section"));
			scrollToElement(mydriver, clearFilterButton, logger);
			softAssert.assertTrue(verifyElementExists(logger, clearFilterButton, "Clear Filter Button"));
			softAssert.assertAll();
		}
	}

	@Test(priority = 2, enabled = true)
	public void sortFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);

		// for (String cardUrl : cardUrls) {
		// customLogsPool.get().add(url); mydriver.get(cardUrl);
		scrollToElement(mydriver, sortDropDown, logger);
		selectByOptionIndex(sortDropDown, 1, logger);

		ArrayList<String> resultList = new ArrayList<>();
		ArrayList<String> sortedResultList = new ArrayList<>();
		// pleaseWait(20);
		List<WebElement> cards = mydriver.findElements(By.xpath(resultCardNames));
		for (WebElement card : cards) {
			// scrollToElement(mydriver, card);
			resultList.add(card.getText());
		}
		for (String a : resultList) {
			sortedResultList.add(a);
		}
		Collections.sort(sortedResultList, String.CASE_INSENSITIVE_ORDER);
		hardAssert.assertEquals(resultList, sortedResultList);

		selectByOptionIndex(sortDropDown, 2, logger);
		resultList = new ArrayList<>();
		sortedResultList = new ArrayList<>();
		pleaseWait(2, logger);
		cards = mydriver.findElements(By.xpath(resultCardNames));
		for (WebElement card : cards) {
			resultList.add(card.getText());
		}
		for (String a : resultList) {
			sortedResultList.add(a);
		}
		Collections.sort(sortedResultList, String.CASE_INSENSITIVE_ORDER);
		Collections.reverse(sortedResultList);
		hardAssert.assertEquals(resultList, sortedResultList);

		selectByOptionIndex(sortDropDown, 3, logger);
		resultList = new ArrayList<>();
		sortedResultList = new ArrayList<>();
		pleaseWait(2, logger);
		cards = mydriver.findElements(By.xpath(resultLastModifiedDate));
		for (WebElement card : cards) {
			// scrollToElement(mydriver, card);
			resultList.add(card.getText());
		}
		for (String a : resultList) {
			sortedResultList.add(a);
		}
		Collections.sort(sortedResultList, String.CASE_INSENSITIVE_ORDER);
		Collections.reverse(sortedResultList);
		hardAssert.assertEquals(resultList, sortedResultList);

		// }
	}

	@Test(priority = 3, enabled = true)
	public void filterCategorySectionFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);

		// for (String cardUrl : cardUrls) {
		scrollToElement(mydriver, mydriver.findElement(By.xpath(filterCategory)), logger);
		List<WebElement> categories = mydriver.findElements(By.xpath(filterCategory));
		boolean flag = false;
		int i = 0;
		while (flag == false) {
			i = getRandomInteger(categories.size(), 1);
			if (!categories.get(i).getText().contains("Date") && !categories.get(i).getText().contains("Favor")) {
				flag = true;
			}
		}

		scrollToElement(mydriver, categories.get(i), logger);
		categories.get(i).click();
		if (categories.get(i).getAttribute("aria-expanded").equals("true")) {
			logger.info(categories.get(i).getText() + " section expanded");
		} else {
			fail(categories.get(i).getText() + " section not expanded when clicked");
		}

		categories.get(i).click();
		if (categories.get(i).getAttribute("aria-expanded").equals("false")) {
			logger.info(categories.get(i).getText() + " section contracted");
		} else {
			fail(categories.get(i).getText() + " section not contracted when clicked");
		}

	}

	@Test(priority = 4, enabled = true)
	public void clearFilterFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);
		scrollToElement(mydriver, clearFilterButton, logger);
		clearFilterButton.click();
		try {
			scrollToElement(mydriver, mydriver.findElement(By.xpath(resultFilterTags)), logger);
			fail("'Clear Filter Button' functionality is not working");
		} catch (NoSuchElementException e) {
			logger.info("All the filter were removed on clicking 'Clear filter button'");
		}

	}

	@Test(priority = 5, enabled = true)
	public void filterTagsFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(filterCategory)), logger);
		List<WebElement> categories = mydriver.findElements(By.xpath(filterCategory));
		boolean flag = false;
		int i = 0;
		while (flag == false) {
			i = getRandomInteger(categories.size(), 1);

			if (!categories.get(i).getText().contains("Date") && !categories.get(i).getText().contains("Favor")) {
				flag = true;
			}
		}
		scrollToElement(mydriver, categories.get(i), logger);
		categories.get(i).click();
		int j = i + 1;
		List<WebElement> filterOptions = mydriver.findElements(
				By.xpath("(//*[@class=\"mcl-tray\"]/div[@class=\"mcl-filter\"])[" + j + "]//ul/li/label"));
		ArrayList<String> optionLabels = new ArrayList<>();
		ArrayList<String> tagLabels = new ArrayList<>();

		for (WebElement filterOption : filterOptions) {
			optionLabels.add(filterOption.getText());
			scrollToElement(mydriver, filterOption, logger);
			filterOption.click();
		}
		scrollToElement(mydriver, mydriver.findElement(By.xpath(resultFilterTags)), logger);
		List<WebElement> tags = mydriver.findElements(By.xpath(resultFilterTags));
		for (WebElement tag : tags) {
			tagLabels.add(tag.getText());
			scrollToElementWithoutWait(mydriver, tag);
		}
		hardAssert.assertEquals(tagLabels.size(), optionLabels.size());

	}

	@Test(priority = 6, enabled = true)
	public void hoverEffectFnctionalityCheck() {
		skipNonExistingComponent(cardUrls);
		scrollToElement(mydriver, mydriver.findElements(By.xpath(resultImages)).get(0), logger);

		moveMouseOnToElement(mydriver, mydriver.findElements(By.xpath(resultImages)).get(0));
		pleaseWait(1, logger);
		hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElement(By.xpath(resultCardsHoverEffect)),
				"Hover Effect area"));

	}

	@Test(priority = 7, enabled = true)
	public void assetDownloadFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);
		scrollToElement(mydriver, mydriver.findElements(By.xpath(resultDownloadButton)).get(0), logger);
		mydriver.findElements(By.xpath(resultDownloadButton)).get(0).click();
		mydriver.findElements(By.xpath(resultDownloadButton)).get(0).getAttribute("lang").contains("en");
	}

	@Test(priority = 8, enabled = true)
	public void paginationCheck() {
		skipNonExistingComponent(cardUrls);
		scrollToElement(mydriver, mydriver.findElements(By.xpath(pages)).get(0), logger);

		mydriver.findElements(By.xpath(pages)).get(0).click();
		pleaseWait(5, logger);
		hardAssert
				.assertTrue(mydriver.findElements(By.xpath(pages)).get(1).getAttribute("class").contains("is-active"));
		hardAssert.assertTrue(verifyElementExists(logger, paginationPrev, "previous page button"));
		paginationPrev.click();
		try {
			scrollToElement(mydriver, paginationPrev, logger);
			fail("User is on the first page but the previous button of pagination is still available");
		} catch (NoSuchElementException e) {
			logger.info("User get back to the first page");
		}
		scrollToElement(mydriver, paginationNext, logger);
		paginationNext.click();
		pleaseWait(1, logger);
		hardAssert
				.assertTrue(mydriver.findElements(By.xpath(pages)).get(1).getAttribute("class").contains("is-active"));
	}

	@Test(priority = 9, enabled = true)
	public void assetCardDetailsCheck() {
		skipNonExistingComponent(cardUrls);

		List<WebElement> assets = mydriver.findElements(By.xpath(resultCards));
		List<WebElement> lastModified = mydriver.findElements(By.xpath(resultCards));
		List<WebElement> names = mydriver.findElements(By.xpath(resultCards));
		int i = 0;
		for (WebElement asset : assets) {
			scrollToElementWithoutWait(mydriver, asset);
			scrollToElementWithoutWait(mydriver, lastModified.get(i));
			logger.info("LastModified: " + lastModified.get(i).getText());
			hardAssert.assertTrue(verifyElementExists(logger, lastModified.get(i),
					"Last Modified field for asset '" + i + "' => " + lastModified.get(i).getText()));
			scrollToElementWithoutWait(mydriver, names.get(i));
			logger.info("NAME: " + names.get(i).getText());
			hardAssert.assertTrue(verifyElementExists(logger, names.get(i), "Name field for asset '" + i + "' => "));
			i++;
		}
	}

	@Test(priority = 10, enabled = true)
	public void filterTagsInUrl() {
		skipNonExistingComponent(cardUrls);
		if (mydriver.getCurrentUrl().contains(cardUrls.get(0))) {
			visitMainSearchPage(cardUrls.get(0));
		}

		scrollToElement(mydriver, mydriver.findElement(By.xpath(filterCategory)), logger);
		List<WebElement> categories = mydriver.findElements(By.xpath(filterCategory));
		int i = 4;
		scrollToElement(mydriver, categories.get(i), logger);
		categories.get(i).click();
		int j = i + 1;
		List<WebElement> filterOptions = mydriver.findElements(
				By.xpath("(//*[@class=\"mcl-tray\"]/div[@class=\"mcl-filter\"])[" + j + "]//ul/li/label"));

		for (WebElement filterOption : filterOptions) {
			scrollToElementWithoutWait(mydriver, filterOption);
			filterOption.click();
			logger.info("Current Filter Option: " + filterOption.getText());
			logger.info("Current URL: " + mydriver.getCurrentUrl());
			hardAssert
					.assertTrue(mydriver.getCurrentUrl().toLowerCase().contains(filterOption.getText().toLowerCase()));

		}
	}

	@Test(priority = 11, enabled = true)
	public void copyLinkFunctionalityField() throws UnsupportedFlavorException, IOException {
		skipNonExistingComponent(cardUrls);
		pleaseWait(1, logger);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(filterCategory)), logger);
		List<WebElement> categories = mydriver.findElements(By.xpath(filterCategory));
		int i = 2;
		scrollToElement(mydriver, categories.get(i), logger);
		categories.get(i).click();
		int j = i + 1;
		List<WebElement> filterOptions = mydriver.findElements(
				By.xpath("(//*[@class=\"mcl-tray\"]/div[@class=\"mcl-filter\"])[" + j + "]//ul/li/label"));

		for (WebElement filterOption : filterOptions) {
			scrollToElementWithoutWait(mydriver, filterOption);
			filterOption.click();
			scrollToElementWithoutWait(mydriver, copyLinkButton);
			copyLinkButton.click();

			String expectedURL = mydriver.getCurrentUrl();
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(null);
			String x = (String) contents.getTransferData(DataFlavor.stringFlavor);

			hardAssert.assertEquals(x, expectedURL);

		}
	}

	@Test(priority = 12, enabled = true)
	public void filterTagsInCopyInptField() throws UnsupportedFlavorException, IOException {
		skipNonExistingComponent(cardUrls);

		pleaseWait(1, logger);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(filterCategory)), logger);
		List<WebElement> categories = mydriver.findElements(By.xpath(filterCategory));
		int i = 4;
		scrollToElement(mydriver, categories.get(i), logger);
		if (categories.get(i).getAttribute("aria-expanded").equals("false")) {
			categories.get(i).click();
		}
		int j = i + 1;
		List<WebElement> filterOptions = mydriver.findElements(
				By.xpath("(//*[@class=\"mcl-tray\"]/div[@class=\"mcl-filter\"])[" + j + "]//ul/li/label"));

		for (WebElement filterOption : filterOptions) {
			scrollToElement(mydriver, filterOption, logger);
			filterOption.click();
			logger.info("Current Filter Option: " + filterOption.getText().toLowerCase());
			scrollToElement(mydriver, copyLinkButton, logger);
			copyLinkButton.click();
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(null);
			String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
			logger.info("Copied URL: " + x);

			hardAssert.assertTrue(x.toLowerCase().contains(filterOption.getText().toLowerCase()));

		}

	}

	@Test(priority = 13, enabled = true)
	public void assetResultElementRedirectionCheck() {
		skipNonExistingComponent(cardUrls);

		List<WebElement> assets = mydriver.findElements(By.xpath(resultImages));
		int i = getRandomInteger(assets.size(), 0);
		scrollToElement(mydriver, assets.get(i).findElement(By.xpath("(parent::a)[1]")), logger);
		String expectedURL = assets.get(i).findElement(By.xpath("(parent::a)[1]")).getAttribute("href");
		assets.get(i).click();
		hardAssert.assertEquals(mydriver.getCurrentUrl(), expectedURL);
		mydriver.navigate().back();

	}

	@Test(priority = 14, enabled = true)
	public void assetDetailsPageDetailFieldsCheck() {
		skipNonExistingComponent(cardUrls);
		visitMainSearchPage(cardUrls.get(0));
		List<WebElement> assets = mydriver.findElements(By.xpath(resultImages));
		int i = getRandomInteger(assets.size(), 0);
		scrollToElement(mydriver, assets.get(i).findElement(By.xpath("(parent::a)[1]")), logger);
		assets.get(i).click();
		scrollToElementWithoutWait(mydriver, assetPreviewField);
		softAssert.assertTrue(verifyElementExists(logger, assetPreviewField, "Asset Preview Field"));
		softAssert.assertFalse(assetPreviewField.getAttribute("src").isEmpty());
		scrollToElementWithoutWait(mydriver, assetPageHeader);
		softAssert.assertTrue(verifyElementExists(logger, assetPageHeader, "Asset Page Header"));
		softAssert.assertFalse(assetPageHeader.getText().isEmpty());
		scrollToElementWithoutWait(mydriver, assetName);
		softAssert.assertTrue(verifyElementExists(logger, assetName, "Asset Name"));
		softAssert.assertFalse(assetName.getText().isEmpty());
		scrollToElementWithoutWait(mydriver, assetPageFavoriteButton);
		softAssert.assertTrue(verifyElementExists(logger, assetPageFavoriteButton, "Asset Page Favorite Button"));
		scrollToElementWithoutWait(mydriver, assetPageShareButton);
		softAssert.assertTrue(verifyElementExists(logger, assetPageShareButton, "assetPageShareButton"));
		scrollToElementWithoutWait(mydriver, assetPageDownloadButton);
		softAssert.assertTrue(verifyElementExists(logger, assetPageDownloadButton, "assetPageDownloadButton"));

		try {
			scrollToElementWithoutWait(mydriver, assetOwnerEmail);
			if (verifyElementExists(logger, assetOwnerEmail, "assetOwnerEmail")) {
				softAssert.assertFalse(assetOwnerEmail.getText().isEmpty());
			}
		} catch (NoSuchElementException e) {

		}
		softAssert.assertAll();
	}

	@Test(priority = 15, enabled = true)
	public void technicalAssetDetatialsCheck() {
		skipNonExistingComponent(cardUrls);
		scrollToElementWithoutWait(mydriver, technicalDetailSection);
		List<WebElement> techKeys = mydriver.findElements(By.xpath(assetTechInfoKeys));
		for (WebElement techKey : techKeys) {
			scrollToElementWithoutWait(mydriver, techKey);
			hardAssert.assertTrue(verifyElementExists(logger, techKey, techKey.getText()));
		}
		hardAssert.assertEquals(techKeys.size(), 5);
	}

	@Test(priority = 16, enabled = true)
	public void assetDownloadFromDetailsPage() {
		skipNonExistingComponent(cardUrls);
		scrollToElement(mydriver, assetPageDownloadButton, logger);
		assetPageDownloadButton.click();
		assetPageDownloadButton.getAttribute("lang").contains("en");

	}

	@Test(priority = 17, enabled = true)
	public void markFavFromDetailsPage() {
		skipNonExistingComponent(cardUrls);
		unFavAllAsset();
		visitMainSearchPage(cardUrls.get(0));

		List<WebElement> assetsResults = mydriver.findElements(By.xpath(resultImages));
		int i = getRandomInteger(assetsResults.size(), 0);
		scrollToElement(mydriver, assetsResults.get(i).findElement(By.xpath("(parent::a)[1]")), logger);
		assetsResults.get(i).click();
		String reqAssetname = assetName.getText();
		scrollToElement(mydriver, assetPageFavoriteButton, logger);
		pleaseWait(2, logger);
		assetPageFavoriteButton.click();
		scrollToElementWithoutWait(mydriver, myAssetDropDown);
		myAssetDropDown.click();
		myFavoritesSubNav.click();
		logger.info("Asset marked as favorite: " + reqAssetname);
		boolean found = false;
		pleaseWait(2, logger);
		List<WebElement> assets = mydriver
				.findElements(By.xpath("//*[@class=\"asset-item__detail--desc js-HubItemTitle\"]"));
		for (WebElement asset : assets) {
			scrollToElement(mydriver, asset, logger);
			if (asset.getText().contains(reqAssetname)) {
				found = true;
				scrollToElement(mydriver, asset.findElement(By.xpath("(parent::div/following-sibling::div/div)")), logger);
				asset.findElement(By.xpath("(parent::div/following-sibling::div/div)")).click();
			}
		}
		if (found == false) {
			fail("Asset Marked as favorite from 'Asset Detail Page' is not available in the 'Favorite Asset List'");
		}
	}

	@Test(priority = 18, enabled = true)
	public void markFavFromResultPage() {
		skipNonExistingComponent(cardUrls);
		unFavAllAsset();
		visitMainSearchPage(cardUrls.get(0));
		List<WebElement> favButtonResults = mydriver.findElements(By.xpath(resultMarkFavoriteButton));
		int i = getRandomInteger(favButtonResults.size(), 0);
		scrollToElement(mydriver, favButtonResults.get(i), logger);// .findElement(By.xpath("(parent::a)[1]")));
		favButtonResults.get(i).click();
		String reqAssetname = mydriver.findElements(By.xpath(resultCardNames)).get(i).getText();
		// scrollToElement(mydriver, assetPageFavoriteButton);
		// pleaseWait(2);
		// assetPageFavoriteButton.click();
		scrollToElementWithoutWait(mydriver, myAssetDropDown);
		myAssetDropDown.click();
		myFavoritesSubNav.click();
		logger.info("Asset marked as favorite: " + reqAssetname);
		boolean found = false;
		pleaseWait(2, logger);
		List<WebElement> assets = mydriver
				.findElements(By.xpath("//*[@class=\"asset-item__detail--desc js-HubItemTitle\"]"));
		for (WebElement asset : assets) {
			scrollToElement(mydriver, asset, logger);
			if (asset.getText().contains(reqAssetname)) {
				found = true;
				scrollToElement(mydriver, asset.findElement(By.xpath("(parent::div/following-sibling::div/div)")), logger);
				asset.findElement(By.xpath("(parent::div/following-sibling::div/div)")).click();
			}
		}
		if (found == false) {
			fail("Asset Marked as favorite from 'Search Results Page' is not available in the 'Favorite Asset List'");
		}
	}

	@Test(priority = 19, enabled = true)
	public void filterCondition() {
		skipNonExistingComponent(cardUrls);
		try{clearFilters();}catch (Exception e) {
			
		}
		visitMainSearchPage(cardUrls.get(0));
		scrollToElement(mydriver, mydriver.findElement(By.xpath(filterCategory)), logger);
		List<WebElement> categories = mydriver.findElements(By.xpath(filterCategory));
		int i = 2;
		scrollToElement(mydriver, categories.get(i), logger);
		categories.get(i).click();
		String filterName = categories.get(i).getText();
		logger.info("Selected filter => "+filterName);
		int j = i + 1;
		List<WebElement> filterOptions = mydriver.findElements(
				By.xpath("(//*[@class=\"mcl-tray\"]/div[@class=\"mcl-filter\"])[" + j + "]//ul/li/label"));
		filterOptions.get(0).click();
		
		List<WebElement> resultAssets= mydriver.findElements(By.xpath(resultImages));
		int p = getRandomInteger(resultAssets.size(), 0);
		logger.info("Asset being checked => "+mydriver.findElements(By.xpath(resultCardNames)).get(p).getText());
		scrollToElement(mydriver, resultAssets.get(p), logger);
		resultAssets.get(i).click();
		boolean found = false;
				List<WebElement> infoKeys = mydriver.findElements(By.xpath(assetInfoKeys));
				for(WebElement infoKey: infoKeys) {
					scrollToElementWithoutWait(mydriver, infoKey);
					String keyText = infoKey.getText();
					if(keyText.replace(":", "").equalsIgnoreCase(filterName)) {
						found=true;
					}
				}
		
				if(found==false) {
				fail("Filter applied but no related key present on the asset page");
				}
	}

}
