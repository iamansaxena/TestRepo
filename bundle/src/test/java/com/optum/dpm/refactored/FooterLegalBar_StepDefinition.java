package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.FooterLegalBar_page;

import core.CustomDataProvider;

public class FooterLegalBar_StepDefinition extends FooterLegalBar_page {
	
	private static final Logger logger = LogManager.getLogger(FooterLegalBar_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void blankLegalNoticeLabelsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(legalNoticeLink)), logger);
		List<WebElement> legalNoticeLabels = mydriver.findElements(By.xpath(legalNoticeLink));
		for (WebElement label : legalNoticeLabels) {
			hardAssert.assertFalse(label.getAttribute("innerText").isEmpty());
		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void legalNoticeRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(legalNoticeLink)), logger);
		List<WebElement> legalNoticeLabels = mydriver.findElements(By.xpath(legalNoticeLink));
		int i = getRandomInteger(legalNoticeLabels.size(), 0);
		String expLink = legalNoticeLabels.get(i).getAttribute("href");
		String handle = mydriver.getWindowHandle();
		legalNoticeLabels.get(i).click();
		pleaseWait(6, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void privacyLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(privacyLink)), logger);
		List<WebElement> privacyLabels = mydriver.findElements(By.xpath(privacyLink));
		for (WebElement ele : privacyLabels) {
			hardAssert.assertFalse(ele.getAttribute("innerText").isEmpty());
		}

		int i = getRandomInteger(privacyLabels.size(), 0);
		String expLink = privacyLabels.get(i).getAttribute("href");
		String handle = mydriver.getWindowHandle();
		privacyLabels.get(i).click();
		pleaseWait(6, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void blankCopyrightTextCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, copyrightText, logger);
		hardAssert.assertFalse(copyrightText.getAttribute("innerText").isEmpty());
	}

}
