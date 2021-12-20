package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class LeadersHub_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "LeadersHub";
	
	protected static String leaderCard = "//*[@class=\"leadership-card\"]";
	
	protected static String leaderName = "//*[@class=\"leadership-card\"]//*[@class=\"leadership-card__name\"]";
	
	protected static String leaderTitle = "//*[@class=\"leadership-card\"]//*[@class=\"leadership-card__title\"]";
	
	protected static String viewBio = "//*[@class=\"leadership-card\"]//*[@class=\"leadership-card__cta\"]";
	
	protected static String LeaderImage = "//*[@class=\"leadership-card\"]//img";
	
	protected static String LeaderCardLink = "//*[@class=\"leadership-card\"]//a";
	
	protected static String LeaderCardHover = "//*[@class=\"leadership-card\"]";
}
