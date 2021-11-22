package com.optum.dpm.page.model;

import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class Sharebar_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "Sharebar";
	
@FindBy(xpath="//a[contains(@class,\"linkedin\")]")
protected static WebElement linkedinLink;

@FindBy(xpath="//a[contains(@class,\"facebook\")]")
protected static WebElement facebookLink;

@FindBy(xpath="//a[contains(@class,\"twittershare\")]")
protected static WebElement twitterLink;

@FindBy(xpath="//a[contains(@class,\"pinterest\")]")
protected static WebElement pinterestLink;

@FindBy(xpath="//a[contains(@class,\"email\")]")
protected static WebElement emailShareLink;

@FindBy(xpath="//*[contains(@class,\"share-bar\")]/*[@class=\"row\"]")
protected static WebElement sharebarContainer;

public void getBackToBaseTab(String baseTabHandle) {
	Iterator<String> it = mydriver.getWindowHandles().iterator();
	while(it.hasNext()) {
		String currentTab = it.next();
		if(!currentTab.equals(baseTabHandle)) {
			mydriver.switchTo().window(currentTab).close();;
		}
	}
	mydriver.switchTo().window(mydriver.getWindowHandles().iterator().next());

}
/*public static void scrollToElementWithoutWait(WebDriver mydriver, WebElement element) {
	((JavascriptExecutor) mydriver).executeScript("arguments[0].scrollIntoView(true);", element);

	focusElement(mydriver, element);
}*/

}
