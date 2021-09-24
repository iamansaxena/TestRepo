package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class AdaptiveImage_page extends Base{

	protected static WebDriver mydriver;

	protected static String adaptiveImages = "(//*[@class=\"adaptiveimage image parbase section\"])[1]/div[@data-picture]/div[@data-src]";

	public AdaptiveImage_page() {
	PageFactory.initElements(mydriver, this);
	}
}
