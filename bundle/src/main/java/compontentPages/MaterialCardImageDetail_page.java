package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MaterialCardImageDetail_page extends Base {
	protected static WebDriver mydriver;
	protected static String cards = "//*[contains(@class,\"material-card-image-detail\")]";
	// For cards having title use the locator without 'span' tag. Also the cards
	// would be in a loop
	/*
	 * //*[@class="material-card-image-detail-cta section"]//*[@class=
	 * "mcard__imgcta__title"]/span
	 */
	protected static String descriptions = "//*[@class=\"mcard__detail__descption\"]";
	protected static String buttonTypes = "//*[contains(@class,\"material-card-image-detail\")]/div/div[3]";
	protected static String cardImages = "//*[@class=\"mcard__image-container\"]/img";

	public MaterialCardImageDetail_page() {
//		fetchSession(MaterialCardImageDetail_page.class);
//		mydriver = LATEST_DRIVER_POOL.get(MaterialCardImageDetail_page.class.getName());
		// driverMap.put(MaterialCardImageDetail_stepDefinition.class.getName().split("\\.")[1],
		// mydriver);
		PageFactory.initElements(mydriver, this);

	}
}
