package compontentPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class CampaignCarousel_page extends Base {
	protected static WebDriver mydriver;
	@FindBy(xpath = "//*[@data-type=\"prev\"]/i")
	protected static WebElement prevButton;
	@FindBy(xpath = "//*[@class=\"dpl-carousel\"]")
	protected static WebElement carouselSection;
	
	@FindBy(xpath = "//*[@data-type=\"next\"]/i")
	protected static WebElement nextButton;
	protected static String slideLinks = "//*[@aria-hidden=\"false\"]/following-sibling::a";
	protected static String slideBullets = "//*[@role=\"tablist\"]/button";
	@FindBy(xpath = "(//*[@id=\"dpl-caption-1\"])[1]")
	protected static WebElement firstSlide;
	@FindBy(xpath = "(//*[@id=\"dpl-caption-5\"])[2]")
	protected static WebElement fifthSlide;
	public CampaignCarousel_page() {
		PageFactory.initElements(mydriver, this);
	}
	protected static void pauseCaraousel() {
		Base obj = new Base();
		try {
			if(mydriver.findElement(By.xpath("(//*[contains(@class,\"pause\")])[1]")).getAttribute("class").contains("playing"))
			mydriver.findElement(By.xpath("(//*[contains(@class,\"pause\")])[1]")).click();
			obj.pleaseWait(2, logger);
		}catch (Exception e) {
			
		}
	}
}
