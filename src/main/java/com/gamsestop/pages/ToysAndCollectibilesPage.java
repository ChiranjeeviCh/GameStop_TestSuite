package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToysAndCollectibilesPage {

	public WebDriver driver;
	
	public ToysAndCollectibilesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Toys & Collectibles']")
	public WebElement toysAndCollLink;
	
	@FindBy(xpath="//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]")
	public WebElement viewMore;
	
	@FindBy(xpath="//ul[@class='menu-list level-2 view-more']/li/a")
	public List<WebElement> allLinks;
	
	
	@FindBy(xpath="//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]")
	public List<WebElement> listOfCarousals;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public WebElement homeGrid;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public List<WebElement> homeGrids;
	
	@FindBy(xpath="//div[@class='home-grid6']//a")
	public List<WebElement> listOfHomegrids;
	
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public WebElement homeGrid1;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public List<WebElement> homeGrids1;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']//a")
	public List<WebElement> listOfHomeGrids1;
	
	
	@FindBy(xpath="//div[@class='row key-art-carousel']")
	public WebElement rowgrid;
	
	@FindBy(xpath="//div[@class='row key-art-carousel']")
	public List<WebElement> rowgrids;
	
	@FindBy(xpath="//div[@class='col-md-4 p-1 slide']//a")
	public List<WebElement> listOfRowgrids;
	
	
	@FindBy(xpath="//h4[contains(text(),'Feature')]")
	public WebElement featurePD;
	
	@FindBy(xpath="//h4[contains(text(),'Feature')]")
	public List<WebElement> featurePDs;
	
	@FindBy(xpath="//h4[contains(text(),'Feature')]/following-sibling::div//a")
	public List<WebElement> listOfFPds;
	
	
	@FindBy(xpath="//h2[contains(text(),'Feature')]")
	public WebElement featurePD1;
	
	@FindBy(xpath="//h2[contains(text(),'Feature')]")
	public List<WebElement> featurePDs1;
	
	@FindBy(xpath="//h2[contains(text(),'Feature')]/following-sibling::div//a")
	public List<WebElement> listOfFPds1;
	
	
	@FindBy(xpath="//h4[text()='Shop By Category']")
	public WebElement shobBycate;
	
	@FindBy(xpath="//h4[text()='Shop By Category']")
	public List<WebElement> shobBycategory;
	
	@FindBy(xpath="//h4[text()='Shop By Category']//following-sibling::div//a")
	public List<WebElement> listOfcategories;
	
	
}
