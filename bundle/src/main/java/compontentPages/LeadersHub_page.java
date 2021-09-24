package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class LeadersHub_page extends Base {
	protected static WebDriver mydriver;
	
	protected static String leaderCard = "//*[@class=\"leadership-card\"]";
	
	protected static String leaderName = "//*[@class=\"leadership-card\"]//*[@class=\"leadership-card__name\"]";
	
	protected static String leaderTitle = "//*[@class=\"leadership-card\"]//*[@class=\"leadership-card__title\"]";
	
	protected static String viewBio = "//*[@class=\"leadership-card\"]//*[@class=\"leadership-card__cta\"]";
	
	protected static String LeaderImage = "//*[@class=\"leadership-card\"]//img";
	
	protected static String LeaderCardLink = "//*[@class=\"leadership-card\"]//a";
	
	protected static String LeaderCardHover = "//*[@class=\"leadership-card\"]";
	public LeadersHub_page() {
		PageFactory.initElements(mydriver, this);
	}
	
}
