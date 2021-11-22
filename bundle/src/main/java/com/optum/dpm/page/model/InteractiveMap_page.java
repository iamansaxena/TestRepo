package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class InteractiveMap_page extends Base {

	private String author = "Aman Saxena";
	private String tag = "InteractiveMap";
	
	protected static String categoryLabel = "//*[@class=\"interactive-map__care-category-option\"]//label";
	protected static String categoryIcon = "//*[@class=\"interactive-map__care-category-option\"]//label/img";// GEt src
	// attribute
	// and
	// store
	// it
	// for
	// assertion
	// throughout
	// the
	// page
	@FindBy(xpath = "//*[@id=\"map-region\"]")
	protected static WebElement mapSection;
	@FindBy(xpath = "//*[@class=\"interactive-map__panel-content js-cdo-content\"]//img")
	protected static WebElement sidePanelLogo;
	@FindBy(xpath = "//*[@class=\"interactive-map__panel-content js-cdo-content\"]//button")
	protected static WebElement sidePanelCloseButton;
	@FindBy(xpath = "//*[@class=\"interactive-map__panel-content js-cdo-content\"]//h2")
	protected static WebElement sidePanelTitle;
	protected static String mapLegends = "//*[contains(@style,'width: 16px; height: 16px; overflow: hidden')]/img";
	@FindBy(xpath = "//*[@class=\"interactive-map__map__controls--zoom-in js-zoom-in\"]")
	protected static WebElement mapZoomInButton;
	@FindBy(xpath = "//*[@class=\"interactive-map__map__controls--zoom-out js-zoom-out\"]")
	protected static WebElement mapZoomOutButton;
	@FindBy(xpath = "//*[@class=\"interactive-map__map__controls--reset-map js-reset-map\"]")
	protected static WebElement mapHomeButton;
	@FindBy(xpath = "(//*[@id=\"map-region\"]//*[contains(@style,\"position: relative; padding\")]/span)[2]")
	protected static WebElement mapZoomLevel;
	@FindBy(xpath = "//*[@class=\"interactive-grid\"]/p")
	protected static WebElement gridDescription;
	@FindBy(xpath = "//*[@id=\"grid-view\"]")
	protected static WebElement gridView;
	@FindBy(xpath = "//*[@id=\"list-view\"]")
	protected static WebElement listView;
	protected static String cdoButtons = "//*[@class=\"interactive-grid__cdo-button js-cdo-button\"]/img";
	protected static String columnHeader = "//*[@class=\"js-list-th\"]";
	protected static String cdoListItemName = "//*[@class=\"js-list-cdo-row\"]/td[1]"; // if
	// //*[@class=\"js-list-cdo-row\"]/td[2]
	// contains Multiple then the
	// legends in the map must be
	// more than 1
	protected static String cdoListItemState = "//*[@class=\"js-list-cdo-row\"]/td[2]";
}
