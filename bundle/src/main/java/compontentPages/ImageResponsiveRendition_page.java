package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ImageResponsiveRendition_page extends Base {
	protected static WebDriver mydriver;
	@FindBy(xpath="//*[@class=\"image-responsive-rendition section\"]//a")
	protected static WebElement link;
	
	protected static String images = "(//*[@class=\"image-responsive-rendition section\"]//picture)[1]//source";
	public ImageResponsiveRendition_page() {
		PageFactory.initElements(mydriver, this);
	}
}
