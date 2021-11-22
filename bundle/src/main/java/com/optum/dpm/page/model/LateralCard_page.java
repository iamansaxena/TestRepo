package com.optum.dpm.page.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.optum.dpm.utils.Base;
import com.optum.dpm.utils.DPMTestUtils;

public class LateralCard_page extends Base {
	protected static WebDriver mydriver;

	@FindBy(xpath = "//*[@class=\"dpl-lateral-card section\"]")
	protected static WebElement cardSection;

	@FindBy(xpath = "//*[@class=\"dpl-lateral-card section\"]//*[@class=\"dpl-lateral-card__badge\"]")
	protected static WebElement cardItemBadge;

	@FindBy(xpath = "//*[@class=\"dpl-lateral-card section\"]//h2")
	protected static WebElement cardSectionTitle;

	@FindBy(xpath = "//*[@class=\"dpl-lateral-card section\"]//*[@class=\"dpl-lateral-card__headingsmall\"]")
	protected static WebElement cardItemTitle;

	@FindBy(xpath = "//*[@class=\"dpl-lateral-card section\"]//*[@class=\"dpl-lateral-card__bodycopy\"]/p")
	protected static WebElement cardItemBody;

	@FindBy(xpath = "//*[@class=\"dpl-lateral-card section\"]//*[@class=\"dpl-lateral-card__buttons\"]/a")
	protected static WebElement cardItemButton;

	@FindBy(xpath = "//*[@class=\"dpl-lateral-card section\"]//img")
	protected static WebElement cardItemImage;

	public LateralCard_page() {
		PageFactory.initElements(mydriver, this);
	}

	protected static boolean ifFeaturedTagPresent() {
		try {
			DPMTestUtils.getWebDriverWait(mydriver, 20).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[@class=\"dpl-lateral-card section\"]//*[@class=\"dpl-lateral-card__badge\"]")));
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
