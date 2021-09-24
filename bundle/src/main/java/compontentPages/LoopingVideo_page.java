package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class LoopingVideo_page extends Base{
protected static WebDriver mydriver;
	@FindBy(xpath = "//*[contains(@id,\"loop-video-tag\")]")
	protected static WebElement videoFrame ;
	
	
	@FindBy(xpath = "//button[contains(@id,\"js-loop-pause-play\")]")
	protected static WebElement playPauseButton ;
	
	protected static String text = "//*[contains(@class,\"large-text \")]";
	public LoopingVideo_page() {
		PageFactory.initElements(mydriver, this);
	}
}
