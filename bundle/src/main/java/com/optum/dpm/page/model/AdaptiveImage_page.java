package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class AdaptiveImage_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "AdaptiveImage";
	protected static String adaptiveImages = "(//*[@class=\"adaptiveimage image parbase section\"])[1]/div[@data-picture]/div[@data-src]";
}
