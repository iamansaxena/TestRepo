package compontentPages;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Base;

public class ArticleBannerMedExpress_page extends Base {
	protected static WebDriver mydriver = null;
	Base obj = new Base();	

	@FindBy(css = "div.article-banner")
	protected static WebElement articleBanner;

	@FindBy(css = "div.article-banner div.article-banner__bg-image")
	protected static WebElement articleBannerBGimg;
	
	@FindBy(css = "div.article-banner h1.artical-banner__copy")
	protected static WebElement articleBannerText;

	public ArticleBannerMedExpress_page() {
		PageFactory.initElements(mydriver, this);
	}
	
	/**
	 * This method verify WebElements
	 * @param logger
	 * @return
	 */
	public boolean verifyElements(Logger logger) {
		boolean flag = true;
		try {
			logger.info("Verifying elements Visibility");
			new WebDriverWait(mydriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.article-banner")));
			if (articleBanner.isDisplayed()) {
				customTestLogs.get().add("Article Banner is displayed");
				logger.info("Article Banner is displayed");
			} else {
				customTestLogs.get().add("Article Banner is not displayed");
				logger.info("Article Banner is not displayed");
				flag = false;
			}

			if (articleBannerBGimg.isDisplayed()) {
				customTestLogs.get().add("Article Banner background image is displayed");
				logger.info("Article Banner background image is displayed");
			} else {
				customTestLogs.get().add("Article Banner background image is not displayed");
				logger.info("Article Banne background image is not displayed");
				flag = false;
			}

			if (articleBannerText.isDisplayed()) {
				customTestLogs.get().add("Article Banner text is displayed :" + articleBannerText.getText());
				logger.info("Article Banner text is displayed :" + articleBannerText.getText());
			} else {
				customTestLogs.get().add("Article Banner text is not displayed");
				logger.info("Article Banne text is not displayed");
				flag = false;
			}
		} catch (ElementNotVisibleException e) {
			flag = false;
			logger.info("Element is missing");
		}

		return flag;
	}
}