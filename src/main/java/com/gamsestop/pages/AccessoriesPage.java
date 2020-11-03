package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccessoriesPage {
	
	public WebDriver driver;
	
	 public AccessoriesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Accessories']")
	public WebElement accessoriesLink;
	
	@FindBy(xpath="//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]")
	public WebElement viewMoreAcc;
	
	@FindBy(xpath="//ul[@class='menu-list level-2 view-more']//li/a")
	public List<WebElement> allAccOptions;
	
	@FindBy(xpath="(//div[@class='dropdown-menu block-layout show']//li/a//span[@class='category-name '])")
	public WebElement textOfLink;
	
	@FindBy(xpath="//ol[@class='breadcrumb']/li/a")
	public WebElement breadcrumb;
	
	@FindBy(xpath="//ol[@class='breadcrumb']/li/a")
	public List<WebElement> breadcrumPath;
	
	@FindBy(xpath="(//div[@class='col']//span)[1]")
	public List<WebElement> searchResults;
	
	@FindBy(xpath="(//div[@class='col']//span)[1]")
	public WebElement searchresults;
	
	@FindBy(xpath="(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']")
	public WebElement searchMiddle;
	
	@FindBy(xpath="//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]")
	public List<WebElement> listOfcarousals;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public WebElement homeGrid;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public List<WebElement> homeGrids;
	
	@FindBy(xpath="//div[@class='home-grid6']//a")
	public List<WebElement> listOfHomegrids;
	
	@FindBy(xpath="//h4[contains(text(),'Featured Games')]")
	public List<WebElement> featuregames;
	
	@FindBy(xpath="//h4[contains(text(),'Featured Games')]")
	public WebElement featuregame;
	
	@FindBy(xpath="//h4[contains(text(),'Featured Games')]/following-sibling::div//a")
	public List<WebElement> listOfFGs;
	
	
	@FindBy(xpath="//div[@class='container']/h4")
	public List<WebElement> productFeatures;
	
	@FindBy(xpath="//div[@class='container']/h4")
	public WebElement productFeature;
	
	@FindBy(xpath="//div[@class='container']/h4/following-sibling::div//a")
	public List<WebElement> listOfPFs;
	
	@FindBy(xpath="//div[@class='container']/h2")
	public List<WebElement> productFeatures1;
	
	@FindBy(xpath="//div[@class='container']/h2")
	public WebElement productFeature1;
	
	@FindBy(xpath="//div[@class='container']/h2/following-sibling::div//a")
	public List<WebElement> listOfPFs1;
	
}
