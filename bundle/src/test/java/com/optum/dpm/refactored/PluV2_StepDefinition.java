package com.optum.dpm.refactored;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getActions;
import static com.optum.dpm.utils.DPMTestUtils.getFontSize;
import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.selectByOptionName;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.PluV2_page;

import core.CustomDataProvider;

public class PluV2_StepDefinition extends PluV2_page {

	private static ArrayList<String> availableFilters;
	private static int noOfFilters = 0;

	private static String providerDetailsUrl = "";
	private static String currentDomain = "=> ";
	private static final Logger logger = LogManager.getLogger(PluV2_StepDefinition.class);
	private static JavascriptExecutor js ;

	@Test(enabled = false, priority = 1,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void intakeFormElementVisibilityCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		
			
			 mydriver.get(testUrl);
			scrollToElement(mydriver, bannerImage, logger);

			softAssert.assertTrue(verifyElementExists(logger, searchCardHeader, "searchCardHeader"));
			softAssert.assertTrue(verifyElementExists(logger, searchHelperLabel, "searchHelperLabel"));

			softAssert.assertTrue(verifyElementExists(logger, topSearchFieldLabel, "topSearchFieldLabel"));
			softAssert.assertTrue(verifyElementExists(logger, bannerImage, "bannerImage"));
			softAssert.assertTrue(verifyElementExists(logger, searchField, "searchField"));
			softAssert.assertTrue(verifyElementExists(logger, selectRadius, "selectRadius"));
			softAssert.assertTrue(verifyElementExists(logger, searchButton, "searchButton"));
			softAssert.assertTrue(verifyElementExists(logger, mainSectionCheckbox, "mainSectionCheckbox"));
			softAssert.assertFalse(verifyElementExists(logger, findProviderOption1CB, "findProviderOption1CB"));
			softAssert.assertFalse(verifyElementExists(logger, findProviderOption2CB, "findProviderOption2CB"));
			softAssert.assertFalse(verifyElementExists(logger, findProviderOption3CB, "findProviderOption3CB"));
			softAssert.assertFalse(
					verifyElementExists(logger, findProviderOptionSectionLabel, "findProviderOptionSectionLabel"));
			softAssert.assertFalse(verifyElementExists(logger, findProviderSearchField, "findProviderSearchField"));
			softAssert.assertFalse(
					verifyElementExists(logger, findProviderSearchFieldLabel, "findProviderSearchFieldLabel"));

			moreSearchDD.click();
			pleaseWait(2, logger);
			softAssert.assertTrue(verifyElementExists(logger, fewSearchDD, "fewSearchDD"));
			softAssert.assertTrue(verifyElementExists(logger, findProviderMainLabelRB, "findProviderMainLabelRB"));
			softAssert.assertTrue(verifyElementExists(logger, findProviderSubLabel, "findProviderSubLabel"));

			softAssert.assertTrue(verifyElementExists(logger, findProviderOption1CB, "findProviderOption1CB"));
			softAssert.assertTrue(verifyElementExists(logger, findProviderOption2CB, "findProviderOption2CB"));
			softAssert.assertTrue(verifyElementExists(logger, findProviderOption3CB, "findProviderOption3CB"));
			softAssert.assertTrue(
					verifyElementExists(logger, findProviderOptionSectionLabel, "findProviderOptionSectionLabel"));
			softAssert.assertTrue(verifyElementExists(logger, findProviderSearchField, "findProviderSearchField"));
			softAssert.assertTrue(
					verifyElementExists(logger, findProviderSearchFieldLabel, "findProviderSearchFieldLabel"));
			softAssert.assertTrue(verifyElementExists(logger, findLocationSubLabel, "findLocationSubLabel"));

			softAssert.assertFalse(verifyElementExists(logger, findLocationOption1CB, "findLocationOption1CB"));
			softAssert.assertFalse(
					verifyElementExists(logger, findLocationOptionSectionLabel, "findLocationOptionSectionLabel"));
			softAssert.assertFalse(verifyElementExists(logger, findLocationSearchField, "findLocationSearchField"));
			softAssert.assertFalse(
					verifyElementExists(logger, findLocationSearchFieldLabel, "findLocationSearchFieldLabel"));

			findLocationMainLabelRB.click();
			pleaseWait(1, logger);
			softAssert.assertTrue(verifyElementExists(logger, findLocationOption1CB, "findLocationOption1CB"));
			softAssert.assertTrue(
					verifyElementExists(logger, findLocationOptionSectionLabel, "findLocationOptionSectionLabel"));
			softAssert.assertTrue(verifyElementExists(logger, findLocationSearchField, "findLocationSearchField"));
			softAssert.assertTrue(
					verifyElementExists(logger, findLocationSearchFieldLabel, "findLocationSearchFieldLabel"));
			softAssert.assertTrue(verifyElementExists(logger, findLocationSubLabel, "findLocationSubLabel"));
			softAssert.assertAll();


	}

	@Test(priority = 2, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void bannerImageIsVisible(String testUrl) {
		skipNonExistingComponent(testUrl);

			//
			 mydriver.get(testUrl);
			try {
			 mydriver.get(mydriver.findElement(By.xpath("//*[@class=\"pluv2-img-container__medium-img\"]"))
						.getAttribute("src"));

			}

			catch (Exception e) {
				fail("BROKEN IMAGE LINK");

			}


	}

	@Test(priority = 3, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void intakeFormTypographyCheck(String testUrl) {

		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			//
			 mydriver.get(testUrl);
			scrollToElement(mydriver, searchCardHeader, logger);

			hardAssert.assertEquals(getFontSize(searchCardHeader), "36px");
			hardAssert.assertEquals(getFontSize(searchHelperLabel), "15px");
			hardAssert.assertEquals(getFontSize(topSearchFieldLabel), "15px");


	}

	@Test(priority = 4, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void intakeFormBlankFormSubmitCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		
			
			 mydriver.get(testUrl);
			searchButton.click();
			scrollToElement(mydriver, searchCardHeader, logger);
			searchField.click();
			searchField.click();
			System.out.println(errorValidationMsg.getText() + "\n" + getFontSize(errorValidationMsg));
			hardAssert.assertTrue(verifyElementExists(logger, errorValidationMsg, "errorValidationMsg"));

			softAssert.assertEquals(errorValidationMsg.getText(), errorValidationMsgText);
			softAssert.assertEquals(getFontSize(errorValidationMsg), "15px");
			softAssert.assertAll();

	}

	@Test(enabled = false, priority = 5,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void intakeFormSearchPinCodeFormSubmitCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		
			

			 mydriver.get(testUrl);
			scrollToElement(mydriver, searchHelperLabel, logger);
			String input = searchHelperLabel.getText().split("or ")[1];
			scrollToElement(mydriver, searchCardHeader, logger);
			searchField.click();
			searchField.click();
			searchField.sendKeys(input);
			selectRadius.click();
			selectByOptionName(logger, selectRadius, "20 miles");
			if (mainSectionCheckbox.isSelected()) {
				searchButton.click();
			} else {
				mainSectionCheckbox.click();
				searchButton.click();
			}
			scrollToElement(mydriver, resultsPageHeader, logger);


	}

	@Test(priority = 41, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void findProviderSearchLogicForRadiusCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			getBaseSerpPage(testUrl);

			scrollToElement(mydriver, selectRadius, logger);
			selectRadius(50);
			searchButton.click();
			scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@class=\"pluv2-serp__heading\"]")), logger);
			try {
				scrollToElement(mydriver, paginationNext, logger);
				while (2 > 0) {
					paginationNext.click();
				}
			} catch (Exception e) {

			}
			int a = mydriver.findElements(By.xpath("//*[@class=\"pluv2-team__distance\"]")).size();
			double distanceFromCurrentLoc = Double.parseDouble(mydriver
					.findElements(By.xpath("//*[@class=\"pluv2-team__distance\"]")).get(a - 1).getText().split(" ")[0]);
			hardAssert.assertTrue(distanceFromCurrentLoc < 50);


	}

	@Test(description = "2 Char length search", priority = 40, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void findProviderSearchLogicFor2CharLengthCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			ArrayList<Character> searchInput = new ArrayList<>();

			searchInput.add('s');
			searchInput.add('a');

			//String testUrl = intakeFormUrl;
			 mydriver.get(testUrl);
			try {
				scrollToElement(mydriver, moreSearchDD, logger);
				moreSearchDD.click();
				pleaseWait(2, logger);
			} catch (Exception e) {
				scrollToElement(mydriver, fewSearchDD, logger);
				fewSearchDD.click();
				pleaseWait(2, logger);
			}
			scrollToElement(mydriver, searchField, logger);
			searchField.click();
			searchField.click();
			searchField.sendKeys("06032");
			softAssert.assertTrue(findProviderMainLabelRB.isSelected());
			String input = "";
			for (char i : searchInput) {
				input = input + i;
			}
			findProviderSearchField.sendKeys(input);
			pleaseWait(2, logger);
			boolean result = true;
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@id=\"suggestion-list-providers\"]")), logger);
				logger.fatal("Search suggestions are visible even with 2 char length input");
				result = false;

			} catch (Exception e) {
				logger.info("No search suggestion with 2 char length");
			}
			searchButton.click();
			scrollToElement(mydriver, resultsPageHeader, logger);
			hardAssert.assertTrue(
					mydriver.findElement(By.xpath("//*[@class=\"pluv2-results__no-results\"]")).isDisplayed());

			if (result == false) {
				fail("Search suggestions are visible even with 2 char length input");
			}

	}

	@Test(description = "3 char length search", priority = 6, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void findProviderSearchLogicWith3CharLengthCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			String provName = "";
			ArrayList<Character> searchInput = new ArrayList<>();
			ArrayList<Character> searchResultName = new ArrayList<>();
			searchInput.add('s');
			searchInput.add('a');
			searchInput.add('m');

			//String testUrl = intakeFormUrl;
			 mydriver.get(testUrl);
			try {
				scrollToElement(mydriver, moreSearchDD, logger);
				moreSearchDD.click();
				pleaseWait(2, logger);
			} catch (Exception e) {
				scrollToElement(mydriver, fewSearchDD, logger);
				fewSearchDD.click();
				pleaseWait(2, logger);
			}
			scrollToElement(mydriver, searchHelperLabel, logger);
			String pinInput = searchHelperLabel.getText().split("or ")[1];
			scrollToElement(mydriver, searchField, logger);
			searchField.click();

			searchField.click();
			searchField.sendKeys(pinInput);
			softAssert.assertTrue(findProviderMainLabelRB.isSelected());
			String input = "";
			for (char i : searchInput) {
				input = input + i;
			}
			findProviderSearchField.sendKeys(input);
			pleaseWait(2, logger);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@id=\"suggestion-list-providers\"]")), logger);
				List<WebElement> suggestions = mydriver
						.findElements(By.xpath("//*[contains(@id,\"item-providers-name-\")]"));
				for (WebElement suggestion : suggestions) {

					provName = provName + suggestion.getText() + ", ";
				}
				logger.info("Provider search suggestions were: " + provName);

			} catch (Exception e) {
				logger.fatal("SEARCH SUGGESTIONS WERE MISSING!");
			}
			searchButton.click();
			scrollToElement(mydriver, resultsPageHeader, logger);
			mydriver.findElement(By.xpath("//*[@id=\"search-providers-name\"]")).clear();
			selectRadius(100);
			searchButton.click();


	} /*
		 * Provider Search Results
		 * 
		 * For these tests, "findProviderSubmitCheck" method must set flag as true
		 * 
		 */

	@Test(enabled = false, priority = 7,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerSearchResultElementsVisibilityCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			getBaseSerpPage(testUrl);
			scrollToElement(mydriver, providerTab, logger);
			softAssert.assertTrue(verifyElementExists(logger, providerTab, "providerTab"));
			System.out.println(
					providerTab.getAttribute("aria-selected") + "\n" + locationTab.getAttribute("aria-selected"));
			pleaseWait(1, logger);
			softAssert.assertTrue(providerTab.getAttribute("aria-selected").equalsIgnoreCase("true"));
			softAssert.assertTrue(locationTab.getAttribute("aria-selected").equalsIgnoreCase("false"));
			softAssert.assertTrue(verifyElementExists(logger, resultsPageHeader, "resultsPageHeader"));
			softAssert.assertTrue(verifyElementExists(logger, providerTabDesc, "providerTabDesc"));
			softAssert.assertTrue(verifyElementExists(logger, locationTabDesc, "locationTabDesc"));
			softAssert.assertTrue(verifyElementExists(logger, resultSort, "resultSort"));
			List<WebElement> filters = mydriver
					.findElements(By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]"));
			availableFilters = new ArrayList<>();
			for (WebElement filter : filters) {

				noOfFilters++;
				availableFilters
						.add("(//*[@class=\"pluv2-filters__category pluv2-accordion\"])[" + noOfFilters + "]//button");

			}

			softAssert.assertAll();


	}

	@Test(enabled = false, priority = 8,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void mainChecboxFiltrationsCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			getBaseSerpPage(testUrl);
			scrollToElement(mydriver, providerTab, logger);

			List<WebElement> totalProviderCards = mydriver
					.findElements(By.xpath("(//*[@class=\"pluv2-team__member\"]//h3)"));
			List<WebElement> totalProviderCardsMainFiltration = mydriver
					.findElements(By.xpath("//*[@class=\"pluv2-team__status\"]"));
			softAssert.assertEquals(totalProviderCardsMainFiltration.size(), totalProviderCards.size());
			softAssert.assertAll();

	}

	@Test(enabled = false, priority = 9,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerSearchResultFiltrationCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			currentDomain = currentDomain + "[" + testUrl + "]";
			getBaseSerpPage(testUrl);
			scrollToElement(mydriver, providerTab, logger);

			for (int i = 0; i < availableFilters.size(); i++) {
				js.executeScript("arguments[0].scrollIntoView();",
						mydriver.findElement(By.xpath(availableFilters.get(i))));
				mydriver.findElement(By.xpath(availableFilters.get(i))).click();
				int z = i + 1;
				List<WebElement> option = mydriver
						.findElements(By.xpath("((//*[@class=\"pluv2-filters__content\"])[" + z + "]//label)//span"));
				for (int j = 0; j < option.size(); j++) {
					System.out.println(mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id"));
					js.executeScript("arguments[0].scrollIntoView();",
							mydriver.findElement(By.xpath(availableFilters.get(i))));
					option = mydriver.findElements(
							By.xpath("((//*[@class=\"pluv2-filters__content\"])[" + z + "]//label)//span"));
					System.out.println(option.get(j).getText());
					if (mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id")
							.equals("accordion-gender")) {
						// gender test case
						int w = j + 1;
						int cardsChecked = 1;
						if (mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("aria-expanded")
								.equals("false")) {
							scrollToElement(mydriver, mydriver.findElement(By.xpath(availableFilters.get(i))), logger);
							mydriver.findElement(By.xpath(availableFilters.get(i))).click();
							pleaseWait(3, logger);
						}
						if (w < 3) {
							scrollToElement(mydriver, option.get(w), logger);

							option.get(w).click();
							String optionLabel = option.get(w).getText();
							pleaseWait(4, logger);

							List<WebElement> providerWithSameGender = mydriver
									.findElements(By.xpath(resultProviderCardNames));
							for (int t = 0; t <= providerWithSameGender.size(); t++) {
								if (cardsChecked < 2) {
									providerWithSameGender = mydriver.findElements(By.xpath(resultProviderCardNames));
									scrollToElement(mydriver, providerWithSameGender.get(t), logger);
									providerWithSameGender.get(t).click();

									getVisibility(mydriver,
											mydriver.findElement(By.xpath("//*[@class=\"pluv2-main__heading\"]")), 20);
									providerDetailsUrl = mydriver.getCurrentUrl();
									String gender = mydriver.findElement(By.xpath(
											"//*[@class=\"pluv2-gender__heading js-toggle-mobile-accordion\"]//following-sibling::div//div"))
											.getText();
									if (gender.equalsIgnoreCase(optionLabel)) {
										mydriver.navigate().back();

									} else {
										hardAssert.assertEquals(gender, optionLabel);
									}
									cardsChecked++;
								}

							}
							option = mydriver.findElements(
									By.xpath("((//*[@class=\"pluv2-filters__content\"])[" + z + "]//label)//span"));
							getVisibility(mydriver, providerTab, 10);
							System.out.println(mydriver.findElement(By.xpath(availableFilters.get(i))).getText());
							resultFilterGender.click();
							scrollToElement(mydriver, option.get(w), logger);
							System.out.println(option.get(w).getText());

						}
					}

					if (mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id")
							.equals("accordion-specialties")) {
						// speciality test case
						scrollToElement(mydriver, option.get(j), logger);

						option.get(j).click();
						hardAssert.assertEquals(mydriver.findElements(By.xpath(resultProviderCardSpecialities)).size(),
								mydriver.findElements(By.xpath(resultProviderCardNames)).size());
						option.get(j).click();

					}
					if (mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id")
							.equals("accordion-languages")) {

						int cardChecked = 1;
						ArrayList<String> bilingual;
						scrollToElement(mydriver, option.get(j), logger);
						option.get(j).click();
						String optionLabel = option.get(j).getText();
						pleaseWait(2, logger);

						List<WebElement> providerWithSameLang = mydriver
								.findElements(By.xpath(resultProviderCardNames));
						for (int p = 0; p < providerWithSameLang.size() && cardChecked < 5; p++) {

							providerWithSameLang = mydriver.findElements(By.xpath(resultProviderCardNames));
							scrollToElement(mydriver, providerWithSameLang.get(p), logger);
							option = mydriver.findElements(
									By.xpath("((//*[@class=\"pluv2-filters__content\"])[" + z + "]//label)//span"));
							logger.info("Result Filtration: Checking language [ " + optionLabel + " ] : "
									+ providerWithSameLang.get(p).getText());
							providerWithSameLang.get(p).click();
							scrollToElement(mydriver,
									mydriver.findElement(By.xpath("//*[@class=\"pluv2-main__heading\"]")), logger);
							providerDetailsUrl = mydriver.getCurrentUrl();
							List<WebElement> language = mydriver.findElements(By.xpath(
									"//*[@class=\"pluv2-languages__heading js-toggle-mobile-accordion\"]//following-sibling::div//div//strong"));
							bilingual = new ArrayList<>();
							for (WebElement a : language) {
								bilingual.add(a.getText());
							}
							if (bilingual.contains(optionLabel)) {
								mydriver.navigate().back();
							} else {
								hardAssert.assertEquals(bilingual, optionLabel);
							}

							cardChecked++;
							bilingual = null;

						}
						option = mydriver.findElements(By.xpath(languageOptionLabels));
						getVisibility(mydriver, providerTab, 10);
						mydriver.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")).click();
						cardChecked = 1;
						resultFilterLanguages.click();
						option = mydriver.findElements(
								By.xpath("((//*[@class=\"pluv2-filters__content\"])[" + z + "]//label)//span"));
						scrollToElement(mydriver, option.get(j), logger);

					}
				}

				try {
					scrollToElement(mydriver,
							mydriver.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")), logger);
					mydriver.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")).click();
				} catch (NoSuchElementException e) {

				}
			}


	}

	@Test(enabled = false, priority = 10,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void resultTagsCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			currentDomain = currentDomain + "[" + testUrl + "]";
			int tags = 0;

			getBaseSerpPage(testUrl);
			int gen = 0;
			////////////////////////////////////////////////////////////////////////////////

			getVisibility(mydriver, providerTab, 10);
			for (int i = 0; i < availableFilters.size(); i++) {
				js.executeScript("arguments[0].scrollIntoView();",
						mydriver.findElement(By.xpath(availableFilters.get(i))));
				mydriver.findElement(By.xpath(availableFilters.get(i))).click();
				int z = i + 1;
				List<WebElement> option = mydriver
						.findElements(By.xpath("((//*[@class=\"pluv2-filters__content\"])[" + z + "]//label)//span"));

				for (int j = 0; j < option.size(); j++) {
					System.out.println(mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id"));
					scrollToElement(mydriver, option.get(j), logger);

					option = mydriver.findElements(
							By.xpath("((//*[@class=\"pluv2-filters__content\"])[" + z + "]//label)//span"));
					System.out.println(option.get(j).getText());
					if (mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id")
							.equals("accordion-gender")) {
						// gender test case
						int w = j + 1;

						if (gen == 0) {
							scrollToElement(mydriver, option.get(w), logger);
							logger.info(option.get(j).getText());
							option.get(w).click();

							tags++;
							gen++;
						}
					}

					if (mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id")
							.equals("accordion-specialties")) {
						// speciality test case
						scrollToElement(mydriver, option.get(j), logger);
						logger.info(option.get(j).getText());
						option.get(j).click();
						tags++;

					}
					if (mydriver.findElement(By.xpath(availableFilters.get(i))).getAttribute("id")
							.equals("accordion-languages")) {
						// language test case
						System.out.println("Language");
						logger.info(option.get(j).getText());
						option.get(j).click();

						tags++;
					}

				}

			}

			scrollToElement(mydriver, mydriver.findElements(By.xpath(resultTags)).get(0), logger);
			int noOfTags = mydriver.findElements(By.xpath(resultTags)).size();
			hardAssert.assertEquals(noOfTags, tags);


	}

	@Test(enabled = false, priority = 11,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerCardDetailsCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
//		for (String intakeFormUrl : intakeFormUrls) {
			currentDomain = currentDomain + "[" + testUrl + "]";
			getBaseSerpPage(testUrl);
			int i = 1;
			String address;
			String distance;
			String imageContainer;
			String speciality;

			List<WebElement> providerCards = mydriver.findElements(By.xpath(resultProviderCardNames));

			for (WebElement card : providerCards) {
				getActions(mydriver).moveToElement(card).build().perform();
				address = "(//*[@class=\"pluv2-team__address\"])[" + i + "]";
				distance = "(//*[@class=\"pluv2-team__distance\"])[" + i + "]";
				imageContainer = "(//*[@class=\"pluv2-team__photo pluv2__col pluv2__flex justify-content-end\"])[" + i
						+ "]";
				speciality = "(//*[@class=\"pluv2-team__specialties\"])[" + i + "]";
				focusElement(mydriver, card.findElement(By.xpath(address)));
				focusElement(mydriver, card.findElement(By.xpath(distance)));
				focusElement(mydriver, card.findElement(By.xpath(imageContainer)));
				focusElement(mydriver, card.findElement(By.xpath(speciality)));
				i++;
			}

	}

	@Test(priority = 12, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void paginationCheck(String testUrl) {
		skipNonExistingComponent(testUrl);
//
			currentDomain = currentDomain + "[" + testUrl + "]";
			getBaseSerpPage(testUrl);
			scrollToElement(mydriver, resultsPageHeader, logger);
			selectByOptionName(logger, selectRadius, "100 miles");
			searchButton.click();
			pleaseWait(3, logger);
			WebElement totalResultsText = mydriver
					.findElement(By.xpath("//*[@class=\"pluv2-results__info pluv2__col\"]//span"));

			scrollToElement(mydriver, totalResultsText, logger);
			String noOfResult = totalResultsText.getText().split("returned")[1].split(" ")[1].trim();
			int ab = Integer.parseInt(noOfResult);
			int expPages = ab / 10;
			if (expPages != 0) {
				scrollToElement(mydriver,
						mydriver.findElements(By.xpath("//*[@class=\"pluv2-pagination__pagi\"]")).get(0), logger);
				int actualPages = 1;
				if (expPages != 0) {
					if (ab % 10 != 0) {
						expPages = expPages + 1;

					} else if (ab % 10 == 0) {

					}

					for (int i = 0; i < expPages; i++) {
						try {
							paginationNext.click();
						} catch (Exception e) {
							logger.info("User is on the last page");
						}
						actualPages++;
					}
					hardAssert.assertEquals(actualPages, expPages);
					paginationNext.click();
					scrollToElement(mydriver, paginationPrev, logger);
					paginationPrev.click();

				}
			} else {
				throw new SkipException(
						"No pagination section available as the no. of results are lest than what is expected");
			}


	}

	@Test(priority = 13, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void resultSortCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getBaseSerpPage(testUrl);
			scrollToElement(mydriver, resultSort, logger);
			selectByOptionName(logger, resultSort, "Distance");
			double distance = Double.parseDouble(
					mydriver.findElements(By.xpath(resultProviderCardDistances)).get(0).getText().split(" ")[0]);
			double distance_1 = Double.parseDouble(
					mydriver.findElements(By.xpath(resultProviderCardDistances)).get(1).getText().split(" ")[0]);
			double distance_2 = Double.parseDouble(
					mydriver.findElements(By.xpath(resultProviderCardDistances)).get(2).getText().split(" ")[0]);
			hardAssert.assertTrue(distance <= distance_1);
			hardAssert.assertTrue(distance_1 <= distance_2);
			hardAssert.assertTrue(distance <= distance_2);

			scrollToElement(mydriver, resultSort, logger);
			selectByOptionName(logger, resultSort, "Alphabetical A-Z");
			char[] value = mydriver.findElements(By.xpath(resultProviderCardNames)).get(0).getText().substring(0, 2)
					.toCharArray();
			char[] value1 = mydriver.findElements(By.xpath(resultProviderCardNames)).get(1).getText().substring(0, 2)
					.toCharArray();
			char[] value2 = mydriver.findElements(By.xpath(resultProviderCardNames)).get(2).getText().substring(0, 2)
					.toCharArray();
			int name = Character.getNumericValue(value[0]) + Character.getNumericValue(value[1]);// +Character.getNumericValue(value[2]);
			int name_1 = Character.getNumericValue(value1[0]) + Character.getNumericValue(value1[1]);// +Character.getNumericValue(value1[2]);
			int name_2 = Character.getNumericValue(value2[0]) + Character.getNumericValue(value2[1]);// +Character.getNumericValue(value2[2]);
			hardAssert.assertTrue(name <= name_1);
			hardAssert.assertTrue(name_1 <= name_2);


	}

	@Test(priority = 14, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void findLocationFromIntakeForm(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
//			
			 mydriver.get(testUrl);
			searchField.click();
			searchField.click();
			searchField.sendKeys("06032");
			moreSearchDD.click();
			scrollToElement(mydriver, findLocationMainLabelRB, logger);
			findLocationMainLabelRB.click();
			findLocationSearchField.sendKeys("pla");
			scrollToElement(mydriver, searchButton, logger);
			searchButton.click();
			List<WebElement> cards = mydriver.findElements(By.xpath(resultProviderCardNames));
			hardAssert.assertEquals(locationTab.getAttribute("aria-selected").toUpperCase(), "TRUE");
			// for (WebElement card : cards) {
			// softAssert.assertEquals(card.getText().substring(0, 3), "Pla");
			//
			// }

	}

	/*
	 * 
	 * Location Results pages
	 * 
	 */
	@Test(priority = 15, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})

	public void locationSearchResultFiltrationCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
//			
			 mydriver.get(testUrl);
			searchField.click();
			searchField.click();
			searchField.sendKeys("06032");
			moreSearchDD.click();
			scrollToElement(mydriver, findLocationMainLabelRB, logger);
			findLocationMainLabelRB.click();
			findLocationSearchField.sendKeys("plain");
			scrollToElement(mydriver, searchButton, logger);
			searchButton.click();
			List<WebElement> options;
			pleaseWait(4, logger);
			List<WebElement> filters = mydriver
					.findElements(By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button"));
			int cardsChecked = 1;

			for (int j = 0; j < filters.size(); j++) {
				WebElement filter = mydriver
						.findElements(By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button"))
						.get(j);

				if (filter.getAttribute("id").equals("accordion-location-types")) {

					options = mydriver
							.findElements(By.xpath("//*[@class=\"pluv2-filters__location-types \"]//label//span"));
					for (int p = 0; p < options.size(); p++) {
						if (filter.getAttribute("aria-expanded").equals("false")) {
							scrollToElement(mydriver, filter, logger);
							filter.click();
							pleaseWait(1, logger);
						}
						WebElement option = mydriver
								.findElements(By.xpath("//*[@class=\"pluv2-filters__location-types \"]//label//span"))
								.get(p);
						scrollToElement(mydriver, option, logger);
						option.click();
						String optionLabel = option.getText();
						List<WebElement> locationCards = mydriver.findElements(By.xpath(resultLocationCardNames));
						for (int i = 0; i < locationCards.size(); i++) {
							WebElement locationCard = mydriver.findElements(By.xpath(resultLocationCardNames)).get(i);
							if (cardsChecked < 5) {
								scrollToElement(mydriver, locationCard, logger);
								logger.info("Location result filtration check : Location type [ " + optionLabel
										+ " ] - " + locationCard.getText());
								locationCard.click();
								getVisibility(mydriver, mydriver.findElement(By.xpath("//*[@class=\"pluv2-heading\"]")),
										10);

								hardAssert.assertEquals(mydriver
										.findElement(By.xpath("//*[@class=\"pluv2-location-type pluv2-section\"]"))
										.getText(), optionLabel);
								mydriver.navigate().back();
								locationCard = mydriver.findElements(By.xpath(resultLocationCardNames)).get(i);
								getVisibility(mydriver, locationCard, 10);
								cardsChecked++;
							}
						}
						cardsChecked = 1;
						filter = mydriver
								.findElements(
										By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button"))
								.get(j);
						try {
							scrollToElement(mydriver, mydriver
									.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")), logger);
							mydriver.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")).click();
						} catch (Exception e) {

						}
					}

					filter = mydriver
							.findElements(By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button"))
							.get(j);
				}

				if (filter.getAttribute("id").equals("accordion-location-services")) {

					options = mydriver
							.findElements(By.xpath("//*[@class=\"pluv2-filters__location-services \"]//label//span"));
					for (int z = 0; z < options.size(); z++) {
						if (filter.getAttribute("aria-expanded").equals("false")) {
							scrollToElement(mydriver, filter, logger);
							filter.click();
							pleaseWait(1, logger);
						}
						WebElement option = mydriver
								.findElements(
										By.xpath("//*[@class=\"pluv2-filters__location-services \"]//label//span"))
								.get(z);
						scrollToElement(mydriver, option, logger);
						option.click();
						String optionLabel = option.getText();
						List<WebElement> locationCards = mydriver.findElements(By.xpath(resultLocationCardNames));
						for (int i = 0; i < locationCards.size(); i++) {
							WebElement locationCard = mydriver.findElements(By.xpath(resultLocationCardNames)).get(i);
							if (cardsChecked < 5) {
								scrollToElement(mydriver, locationCard, logger);
								logger.info("Location result filtration check : Service type [ " + optionLabel + " ] - "
										+ locationCard.getText());
								locationCard.click();

								getVisibility(mydriver, mydriver.findElement(By.xpath("//*[@class=\"pluv2-heading\"]")),
										10);
								List<WebElement> services = mydriver
										.findElements(By.xpath("//*[@class=\"pluv2-services__list\"]//li//strong"));
								String servicesProvided = "";
								for (WebElement service : services) {
									servicesProvided = servicesProvided + ", " + service.getText();
								}
								hardAssert.assertTrue(servicesProvided.contains(optionLabel));
								mydriver.navigate().back();
								locationCard = mydriver.findElements(By.xpath(resultLocationCardNames)).get(i);
								getVisibility(mydriver, locationCard, 10);
								cardsChecked++;
							}
						}
						cardsChecked = 1;
						filter = mydriver
								.findElements(
										By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button"))
								.get(j);
						try {
							scrollToElement(mydriver, mydriver
									.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")), logger);
							mydriver.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")).click();
							filter = mydriver
									.findElements(
											By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button"))
									.get(j);
						} catch (Exception e) {

						}
						filter = mydriver
								.findElements(
										By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button"))
								.get(j);
					}
				}

				try {
					scrollToElement(mydriver,
							mydriver.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")), logger);
					mydriver.findElement(By.xpath("//*[@class=\"js-clear-pills pluv2-pills__clear\"]")).click();
				} catch (Exception e) {

				}
			}


	}

	@Test(priority = 16, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationResultsMapSpotLightCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getBaseSerpPage(testUrl);
			boolean status = true;

			int verified = 1;
			scrollToElement(mydriver, locationTab, logger);
			locationTab.click();
			mydriver.findElements(By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button")).get(0)
					.click();
			pleaseWait(1, logger);
			mydriver.findElements(By.xpath("//*[@class=\"pluv2-checkbox__label\"]//span")).get(0).click();
			if (verifyElementExists(logger, mydriver.findElements(By.xpath(spotLights)).get(0), "spotLights")) {
				List<WebElement> spotlights = mydriver.findElements(By.xpath(spotLights));

				for (WebElement spotlight : spotlights) {
					if (verified != 0) {
						try {

							spotlight.click();
							status = verifyElementExists(logger, mapSpolightPopup, "mapSpolightPopup");
							if (status == true) {
								String actualMapLink = mapSpolightPopup.getAttribute("href");
								System.out.println(actualMapLink);
								String locationName = mapSpolightPopup.getText().substring(0, 10);

								List<WebElement> locationCards = mydriver
										.findElements(By.xpath(resultLocationCardNames));
								for (WebElement locationCard : locationCards) {
									String findRequiredCard = locationCard.getText().substring(0, 10);
									if (locationName == findRequiredCard) {
										locationCard.click();
										pleaseWait(2, logger);
										String expectedMapLink = mydriver
												.findElement(By.xpath("//*[@class=\"pluv2-address__link\"]//a"))
												.getAttribute("href");
										hardAssert.assertEquals(actualMapLink, expectedMapLink);

									}
								}
								verified = 0;
							}

						} catch (Exception e) {
							verified = 1;
						}
					}
				}
			}


	}

	@Test(priority = 17, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationResultsMapClusterCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getBaseSerpPage(testUrl);
			boolean status = false;

			scrollToElement(mydriver, locationTab, logger);
			locationTab.click();
			mydriver.findElements(By.xpath("//*[@class=\"pluv2-filters__category pluv2-accordion\"]//button")).get(0)
					.click();
			pleaseWait(1, logger);
			mydriver.findElements(By.xpath("//*[@class=\"pluv2-checkbox__label\"]//span")).get(0).click();
			status = verifyElementExists(logger, mydriver.findElements(By.xpath(clusterIcons)).get(0), "clusterIcons");
			mydriver.findElements(By.xpath(clusterIcons));
			if (status == true) {
				mydriver.findElements(By.xpath(clusterIcons)).get(0).click();
				try {
					mydriver.findElement(By.xpath(clusterIcons)).isDisplayed();
					pleaseWait(2, logger);
					logger.error("Cluster Icon is still visible");
					fail("Cluster Icon is still visible");
				} catch (Exception e) {

				}
			}


	}

	@Test(priority = 18, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})

	public void locationCardDetailsCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getBaseSerpPage(testUrl);
			locationTab.click();
			int i = 1;
			int j = 1;
			String address;
			String distance;
			String locationName;
			String contact;
			String locationStatus; // closed or opened
			String locationMoreButton;
			String schedule;
			boolean scheduleAvailable = true;
			List<WebElement> locationrCards = mydriver.findElements(By.xpath(resultLocationCardNames));

			for (WebElement card : locationrCards) {

				scrollToElement(mydriver, card, logger);
				address = "(//*[@class=\"pluv2-locations__card is--clickable\"]//*[@class=\"pluv2__flex\"][2]//div//*[@class=\"pluv2__col\"])["
						+ j + "]";// odd
				j++;
				distance = "(//*[@class=\"pluv2-locations__distance\"]//em)[" + i + "]";
				locationName = "(//*[@class=\"pluv2-locations__card-header pluv2__flex\"]//h3)[" + i + "]";
				contact = "(//*[@class=\"pluv2-locations__card is--clickable\"]//*[@class=\"pluv2__flex\"][2]//div//*[@class=\"pluv2__col\"]//ul)["
						+ i + "]";// even
				locationStatus = "(//*[@id=\"operation-accordion\"]//i//following-sibling::span[1])[" + i + "]"; // closed
				locationMoreButton = "(//*[@class=\"pluv2-operation__more pluv2__col\"]//span[1])[" + i + "]";
				schedule = "(//*[@id=\"operation-time\"])[" + i + "]";
				String currentDay = "((//*[@id=\"operation-time\"])[" + i + "]//div//div[1])[1]";
				String currentTiming = "((//*[@id=\"operation-time\"])[" + i + "]//div//div[2])[1]";
				logger.info("Location Name: " + card.findElement(By.xpath(locationName)).getText()
						+ "\n\t\t\t\t  Address: " + card.findElement(By.xpath(address)).getText()
						+ "\n\t\t\t\t  Distance from current location: "
						+ card.findElement(By.xpath(distance)).getText() + "\n\t\t\t\t  Contact Detail: "
						+ card.findElement(By.xpath(contact)).getText() + "\n\t\t\t\t  Current Status [Open/Closed]: "
						+ card.findElement(By.xpath(locationStatus)).getText() + "\n\t\t\t\t  Operation-Timings: "
						+ card.findElement(By.xpath(currentDay)).getAttribute("innerHTML").trim() + " - "
						+ card.findElement(By.xpath(currentTiming)).getAttribute("innerHTML").trim());

				focusElement(mydriver, card.findElement(By.xpath(address)));
				focusElement(mydriver, card.findElement(By.xpath(distance)));
				focusElement(mydriver, card.findElement(By.xpath(locationName)));
				focusElement(mydriver, card.findElement(By.xpath(contact)));

				try {
					if (scheduleAvailable == true) {
						mydriver.findElement(By.xpath(locationMoreButton)).click();

						focusElement(mydriver, card.findElement(By.xpath(locationStatus)));
						focusElement(mydriver, card.findElement(By.xpath(locationMoreButton)));
						focusElement(mydriver, card.findElement(By.xpath(schedule)));
					}
				} catch (Exception e) {
					scheduleAvailable = false;
					logger.info("Operation Time-table is not available");
				}
				if (scheduleAvailable == true) {
					logger.info("Location Name: " + card.findElement(By.xpath(locationName)).getText()
							+ "\n\t\t\t\t  Address: " + card.findElement(By.xpath(address)).getText()
							+ "\n\t\t\t\t  Distance from current location: "
							+ card.findElement(By.xpath(distance)).getText() + "\n\t\t\t\t  Contact Detail: "
							+ card.findElement(By.xpath(contact)).getText()
							+ "\n\t\t\t\t  Current Status [Open/Closed]: "
							+ card.findElement(By.xpath(locationStatus)).getText() + "\n\t\t\t\t  Operation-Timings: "
							+ card.findElement(By.xpath(currentDay)).getAttribute("innerHTML").trim() + " - "
							+ card.findElement(By.xpath(currentTiming)).getAttribute("innerHTML").trim());
				} else {
					logger.info("Location Name: " + card.findElement(By.xpath(locationName)).getText()
							+ "\n\t\t\t\t  Address: " + card.findElement(By.xpath(address)).getText()
							+ "\n\t\t\t\t  Distance from current location: "
							+ card.findElement(By.xpath(distance)).getText() + "\n\t\t\t\t  Contact Detail: "
							+ card.findElement(By.xpath(contact)).getText());
				}
				i++;
				j++;
			}


	}

	/*
	 * Location Details
	 */

	@Test(priority = 19, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailPageMapAndRelatedLinksCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			softAssert.assertTrue(verifyElementExists(logger, locationMap, "locationMap"));
			softAssert.assertTrue(verifyElementExists(logger, locationLink, "locationLink"));
			softAssert.assertTrue(verifyElementExists(logger, locationAddress, "locationAddress"));
			logger.info("Location Address: " + locationAddress.getText());
			logger.info("Map link: " + locationLink.getAttribute("href"));


	}

	@Test(priority = 20, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailPageCareTeamCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			List<WebElement> teamMembers = null;

			int i = 0;
			try {
				teamMembers = mydriver.findElements(By.xpath(careTeamCards));

			} catch (Exception e) {
				throw new SkipException("No Team Members");
			}
			if (teamMembers != null) {
				for (WebElement teamMember : teamMembers) {
					if (i < teamMembers.size() - 1) {
						logger.info(mydriver.findElements(By.xpath(careTeamNames)).get(i).getText());
						i++;
					}
				}

			}

	}

	@Test(priority = 21, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageAddressAvailablityCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			scrollToElement(mydriver, locationAddress, logger);
			logger.info("Current Location address: " + locationAddress.getText());


	}

	@Test(priority = 22, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageNameHeaderCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			scrollToElement(mydriver, locationHeader, logger);
			if (locationHeader.getText().isEmpty()) {
				logger.fatal("Location name is empty");
				
			}

			logger.info("Location under test: " + locationHeader.getText());

	}

	@Test(priority = 23, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageContactDetails(String testUrl) {
		skipNonExistingComponent(testUrl);
		//for (String intakeFormUrl : intakeFormUrls) {
			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {
				scrollToElement(mydriver,
						mydriver.findElement(By.xpath("//*[@class=\"pluv2-contact pluv2-section\"]")), logger);
			} catch (Exception e) {
				throw new SkipException("Contact Section is not there for this location");
			}
			List<WebElement> contactOptions = mydriver.findElements(By.xpath(locationContactOptions));
			for (WebElement contactOption : contactOptions) {
				logger.info("Contact options available: " + contactOption.getText());

			}

	}

	@Test(priority = 24, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageServicesProvidedCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {
				scrollToElement(mydriver,
						mydriver.findElement(By.xpath("//*[@class=\"pluv2-services pluv2-section\"]")), logger);
			} catch (Exception e) {
				throw new SkipException("Services Section is not there for this location");
			}
			List<WebElement> services = mydriver.findElements(By.xpath(locationServices));
			for (WebElement service : services) {
				logger.info("Services available: " + service.getText());

			}


	}

	@Test(priority = 25, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageSpecialitiesCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {
				scrollToElement(mydriver,
						mydriver.findElement(By.xpath("//*[@class=\"pluv2-specialties pluv2-section\"]")), logger);
			} catch (Exception e) {
				throw new SkipException("Specialities Section is not there for this location");
			}

			List<WebElement> specialities = mydriver.findElements(By.xpath(locationSpecialities));
			for (WebElement speciality : specialities) {
				logger.info("Specialities available: " + speciality.getText());

			}


	}

	@Test(priority = 26, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageLanguagesSpokenCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {

				mydriver.findElement(By.xpath("\\*[@class=\"pluv2-location-languages pluv2-section\"]")).getText();
			} catch (Exception e) {
				throw new SkipException("Language Section is not there for this location");
			}
			List<WebElement> languages = mydriver.findElements(By.xpath(locationLanguages));
			for (WebElement language : languages) {
				logger.info("Languages available: " + language.getText());

			}

	}

	@Test(priority = 27, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageParkingFacilitiesCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {
				scrollToElement(mydriver,
						mydriver.findElement(By.xpath("//*[@class=\"pluv2-parking pluv2-section\"]")), logger);
			} catch (Exception e) {
				throw new SkipException("Parking Section is not there for this location");
			}

			scrollToElement(mydriver, locationParkingDetail, logger);
			logger.info("Parking details: " + locationParkingDetail.getText());


	}

	@Test(priority = 28, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageDescriptionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, mydriver
						.findElement(By.xpath("//*[@class=\"pluv2-about-us pluv2-section pluv2-about-us__tablet\"]")), logger);
			} catch (Exception e) {
				throw new SkipException("About Section is not there for this location");
			}

			List<WebElement> aboutparas = mydriver.findElements(By.xpath(locationAboutParas));
			if (aboutparas.size() == 0) {
				fail("Description section do not have any description");
				logger.error("Description section do not have any description");
				;
			}
			for (WebElement aboutpara : aboutparas) {
				logger.info("Location Description : " + aboutpara.getText());

			}

	}

	@Test(priority = 29, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPageTagLineCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			WebElement tagline;
			try {
				scrollToElement(mydriver,
						mydriver.findElement(By.xpath("//*[@class=\"pluv2-location-tagline pluv2-section\"]")), logger);
				tagline = mydriver.findElement(By.xpath("//*[@class=\"pluv2-location-tagline pluv2-section\"]"));
			} catch (Exception e) {
				throw new SkipException("Tag line Section is not there for this location");
			}
			if (tagline.getText().isEmpty()) {
				fail("Tag line is missing");
				logger.error("Tag line is missing");
			}
			logger.info("Location tagline: " + tagline.getText());


	}

	@Test(priority = 30, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsPagePagination(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@aria-label=\"results pagination\"]")), logger);
			} catch (Exception e) {
				throw new SkipException("Not sufficient care team members to check pagination");
			}
			hardAssert.assertTrue(verifyElementExists(logger, paginationNext, "paginationNext button"));
			paginationNext.click();
			hardAssert.assertTrue(verifyElementExists(logger, paginationPrev, "paginationPrev button"));
			paginationPrev.click();

	}

	@Test(priority = 31, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void locationDetailsNetworkMembershipsCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getLocationDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, networkMembershipSection, logger);
			} catch (Exception e) {
				throw new SkipException("No Network membership section");
			}
			softAssert.assertTrue(verifyElementExists(logger, networkMembershipSubHeader,
					"networkMembershipSubHeader [participates in:]"));
			List<WebElement> memberships = mydriver.findElements(By.xpath(locationNetworkMemberships));
			String mem = "";
			for (WebElement membership : memberships) {
				mem = mem + ", " + membership.getText();

			}
			logger.info("This location has follwoing memberships: " + mem);

	}

	/*
	 * Provider details page
	 */
	@Test(priority = 32, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsMandatoryElementCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getProviderDetailsPage(testUrl);

			scrollToElement(mydriver, providerNameDetail, logger);
			softAssert.assertTrue(verifyElementExists(logger, providerNameDetail, "providerNameDetail"));
			scrollToElement(mydriver, providerSpecialityDetail, logger);
			softAssert.assertTrue(verifyElementExists(logger, providerSpecialityDetail, "providerSpecialityDetail"));
			scrollToElement(mydriver, providerMainCheckConditionDetail, logger);
			softAssert.assertTrue(
					verifyElementExists(logger, providerMainCheckConditionDetail, "providerMainCheckConditionDetail"));
			scrollToElement(mydriver, providerLocationSectionDetail, logger);
			softAssert
					.assertTrue(verifyElementExists(logger, providerLocationSectionDetail, "providerLocationSection"));
			softAssert.assertAll();


	}

	@Test(priority = 33, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsBoardCertificationSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getProviderDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, providerBoardCertSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("Board Certification Section is not available for this Doctor");
			}
			List<WebElement> certifications = mydriver.findElements(By.xpath(providerBoardCertListDetails));
			String label = "";
			for (WebElement certification : certifications) {
				if (certification.getText().isEmpty() || certification.getText() == null) {
					logger.fatal("Certification labels are null or empty");
					fail("Certification labels are null or empty");
				} else {
					label = label + ", " + certification.getText();

				}
			}
			logger.info("Current Certifications: " + label);


	}

	@Test(priority = 42, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsOverviewSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			String heads = "";
			String para = "";
			//String testUrl = providerDetailsUrl;
			 mydriver.get(testUrl);
			try {
				scrollToElement(mydriver, providerOverviewSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("Overview Section is not available for this Doctor");
			}
			List<WebElement> subHeadings = mydriver.findElements(By.xpath(providerOverviewSubHeadingDetails));
			List<WebElement> subParas = mydriver.findElements(By.xpath(providerOverviewSubSectionParaDetails));
			for (WebElement subHeading : subHeadings) {
				if (subHeading.getText().isEmpty()) {
					fail("Overview heading is found empty");
					logger.fatal("Overview heading is found empty");
				} else {
					heads = heads + "\n" + subHeading.getText();
				}
			}
			for (int i = 0; i < subParas.size(); i++) {
				if (subParas.get(i).getText().isEmpty()) {
					fail("Overview para is found empty");
					logger.fatal("Overview para is found empty");
				} else {
					para = para + "\n" + subParas.get(i).getText();
				}
			}
			logger.info("Headers : " + heads + "\n\nParas: " + para);
			para = "";
			try {
				scrollToElement(mydriver, providerBioExpandButtonDetail, logger);
			} catch (Exception e) {
				logger.warn("Bio expand button is not available");
			}
			Point location = providerBioExpandButtonDetail.getLocation();
			providerBioExpandButtonDetail.click();
			softAssert.assertEquals(providerBioExpandButtonDetail.getLocation(), location);
			List<WebElement> expandedPara = mydriver.findElements(By.xpath(providerOverviewExpandedParaDetails));
			for (WebElement expPara : expandedPara) {
				para = para + "\n__\n" + expPara.getText();
			}
			logger.info("Info Under expanded bio section: " + para);
			softAssert.assertAll();

	}

	@Test(priority = 34, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsQualificationSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			String heads = "";
			String para = "";

			getProviderDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, providerQualificationSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("Qualification Section is not available for this Doctor");
			}
			try {
				scrollToElement(mydriver, providerQualificationExpandButtonDetail, logger);
			} catch (Exception e) {
				logger.warn("Qualification expand button is not available");
			}
			Point location = providerQualificationExpandButtonDetail.getLocation();

			providerQualificationExpandButtonDetail.click();
			softAssert.assertEquals(providerQualificationExpandButtonDetail.getLocation().x, location.x);

			scrollToElement(mydriver, providerQualificationSectionDetail, logger);
			List<WebElement> subHeadings = mydriver.findElements(By.xpath(providerQualificationSubHeadingsDetails));
			List<WebElement> subParas = mydriver.findElements(By.xpath(providerQualificationParaDetails));
			for (WebElement subHeading : subHeadings) {
				if (subHeading.getText().isEmpty()) {
					fail("Qualification label is found empty");
					logger.fatal("Qualification label is found empty");
				} else {
					heads = heads + "\n" + subHeading.getText();
				}
			}
			for (int i = 0; i < subParas.size(); i++) {
				if (subParas.get(i).getText().isEmpty()) {
					fail("Qualification para is found empty");
					logger.fatal("Qualification para is found empty");
				} else {
					para = para + "\n" + subParas.get(i).getText();
				}
			}
			logger.info("Headers : " + heads + "\n\nParas: " + para);
			para = "";

			softAssert.assertAll();


	}

	@Test(priority = 35, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsAcceptedHealthPlanSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			String plans = "";

			getProviderDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, providerHealthPlanSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("Health Plan Section is not available for this Doctor");
			}
			plans = plans + "\n" + providerHealthPlanSectionHeaderDetail.getText() + "\n";
			List<WebElement> healthPlans = mydriver.findElements(By.xpath(providerHealthPlanListDetails));
			for (WebElement healthPlan : healthPlans) {
				if (healthPlan.getText().isEmpty()) {
					fail("Health plans found to be empty");
					logger.fatal("Health plans found to be empty");
				} else {
					plans = plans + "\n" + healthPlan.getText();
				}
			}
			logger.info("Health plans accepted by current doctor: " + plans);

	}

	@Test(priority = 36, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsNetworkMembershipSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			String member = "";

			getProviderDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, providerNetworkMemberSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("Network Membership Section is not available for this Doctor");
			}

			member = "\n" + member + providerNetworkSectionHeaderDetail.getText() + "\n";
			List<WebElement> memberships = mydriver.findElements(By.xpath(providerNetworkMembershipListDetails));
			for (WebElement membership : memberships) {
				if (membership.getText().isEmpty()) {
					fail("Membership found to be empty");
					logger.fatal("Membership found to be empty");
				} else {
					member = member + "\n" + membership.getText();
				}
			}
			logger.info("Membership of current doctor: " + member);


	}

	@Test(priority = 37, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsHospitalAffiliationSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			String hosAff = "";

			getProviderDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, providerHospAffiliationSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("Hospital affiliation Section is not available for this Doctor");
			}

			hosAff = "\n" + hosAff + providerHospAffiliationSectionHeaderDetail.getText() + "\n";
			List<WebElement> affiliations = mydriver.findElements(By.xpath(providerHospAffiliationListSectionDetail));
			for (WebElement affiliation : affiliations) {
				if (affiliation.getText().isEmpty()) {
					fail("Affiliations found to be empty");
					logger.fatal("Affiliations found to be empty");
				} else {
					hosAff = hosAff + "\n" + affiliation.getText();
				}
			}
			logger.info("Affiliations of current doctor: " + hosAff);


	}

	@Test(priority = 38, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsLanguageSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			String lang = "";

			//
			getProviderDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, providerLanguageSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("Language Section is not available for this Doctor");
			}

			lang = "\n" + lang + mydriver.findElement(By.xpath("//*[@class=\"pluv2-main__heading\"]")).getText() + "\n";
			List<WebElement> languages = mydriver.findElements(By.xpath(providerLanguagesListDetail));
			for (WebElement language : languages) {
				if (language.getText().isEmpty()) {
					fail("Language found to be empty");
					logger.fatal("Language found to be empty");
				} else {
					lang = lang + "\n" + language.getText();
				}
			}
			logger.info("language of current doctor: " + lang);


	}

	@Test(priority = 39, enabled = false,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsAvatarSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getProviderDetailsPage(testUrl);
			try {
				scrollToElement(mydriver, providerImageDetail, logger);
			} catch (Exception e) {
				throw new SkipException("The docter is not having any image");
			}

			hardAssert.assertTrue(verifyElementExists(logger, providerImageDetail, "Doctor's Image"));
			focusElement(mydriver, providerImageDetail);

	}

	@Test(priority = 43, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pluv2-intake"})
	public void providerDetailsCtaLinksSectionCheck(String testUrl) {
		skipNonExistingComponent(testUrl);

			currentDomain = currentDomain + "[" + testUrl + "]";
			getProviderDetailsPage(testUrl);
			String expectedWindowHandle = mydriver.getWindowHandle();
			System.out.println(expectedWindowHandle);
			try {
				scrollToElement(mydriver, providerLinksSectionDetail, logger);
			} catch (Exception e) {
				throw new SkipException("CTA Links Section is not available for this Doctor");
			}

			List<WebElement> links = mydriver.findElements(By.xpath(providerLinksSectionListDetail));
			for (WebElement link : links) {
				try {
					if (link.getAttribute("target").equals("_blank")) {
						link.click();
						pleaseWait(5, logger);
						String currentWindowHandle = mydriver.getWindowHandle();
						System.out.println(currentWindowHandle);
						hardAssert.assertNotEquals(currentWindowHandle, expectedWindowHandle);
						mydriver.switchTo().defaultContent();
						links = mydriver.findElements(By.xpath(providerLinksSectionListDetail));
					}
				} catch (Exception e) {
					link.click();
					pleaseWait(5, logger);
					hardAssert.assertEquals(mydriver.getWindowHandle(), expectedWindowHandle);
					mydriver.navigate().back();
					links = mydriver.findElements(By.xpath(providerLinksSectionListDetail));
				}
			}

	}
}