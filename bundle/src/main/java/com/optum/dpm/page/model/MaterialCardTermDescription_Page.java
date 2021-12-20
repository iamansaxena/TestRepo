package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class MaterialCardTermDescription_Page extends Base{
	private String author = "Sai Tummala";
	private String tag = "MaterialCardTermDescription";
	protected static String mCards = "//*[@class=\"material-card-term-descption section\"]";
	protected static String mCardTitle= "//*[@class=\"mcard__thumb cell col-4 col-5--tablet\"]//h4";
	protected static String mDescription= "//*[@class=\"mcard__content col-7 col-6--tablet\"]/p";
	protected static String mButton = "//*[@class=\"mcard__content col-7 col-6--tablet\"]/a[@class='button button--reverse mcard__term__descbtn']";
}
