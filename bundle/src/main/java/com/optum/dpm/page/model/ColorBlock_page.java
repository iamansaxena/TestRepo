package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class ColorBlock_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "ColorBlock";

	protected static String title="//*[@class=\"color-block section\"]//*[contains(@class,\"section-inner img\")]//h2/a";
	protected static String videoButton="//*[@class=\"banner__main-hero--cta\"]/button";
	protected static String blockSection="//*[@class=\"color-block section\"]";
	protected static String linkButton="//*[contains(@class,\"section-inner img\")]/div/a";
}
