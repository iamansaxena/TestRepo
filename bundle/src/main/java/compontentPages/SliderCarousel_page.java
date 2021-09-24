package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class SliderCarousel_page extends Base{
	protected static WebDriver mydriver;

	protected static String cards = "//*[@class=\"slider section\"]//*[contains(@class,\"cardnumber\")]";

	protected static String cardLinks = "//*[@class=\"slider section\"]//ul//li//a";

	protected static String cardTitles = "//*[@class=\"slider section\"]//ul//div[contains(@class,\"container__title\")]/span";

	protected static String cardImages = "//*[@class=\"slider section\"]//ul//div[contains(@class,\"container__image\")]/img";

	@FindBy(xpath = "//button[contains(@class,\"slider__next\")]")
	protected static WebElement nextButton;

	@FindBy(xpath = "//button[contains(@class,\"slider__pre\")]")
	protected static WebElement prevButton;

	@FindBy(xpath = "//*[@class=\"slider__title__seemorbtn\"]/a")
	protected static WebElement mainButton;
	
	public SliderCarousel_page() {
		PageFactory.initElements(mydriver, this);
	}
}
