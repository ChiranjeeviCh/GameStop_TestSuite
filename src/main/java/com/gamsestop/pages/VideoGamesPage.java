package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;

public class VideoGamesPage  {
	
	public  WebDriver driver;
	
	public VideoGamesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Video Games']")
	public WebElement categoryLink;
	
	@FindBy(xpath="//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]")
	public WebElement viewMore;
	
	@FindBy(xpath="//ul[@class='menu-list level-2 view-more']/li/a")
	public List<WebElement> allLinks;
	
	@FindBy(xpath="(//ul[@class='menu-list level-2 view-more']/li/a//span[@class='category-name '])")
	public WebElement TextofLink;
	
	@FindBy(xpath="//ul[@class='menu-list level-2 view-more']/li/a")
	public WebElement link;
	
	@FindBy(xpath="//a[@data-name='Retro Gaming']")
	public WebElement retroGaming;
	
	@FindBy(xpath="//a[@data-name='More Platforms']")
	public WebElement morePlatforms;
	
	@FindBy(xpath="//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]")
	public	List<WebElement> listOfCarousals;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public WebElement homeGrid;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public List<WebElement> homeGrids;
	
	@FindBy(xpath="//div[@class='home-grid6']//a")
	public List<WebElement> listOfHomegrids;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public WebElement collectiontabs;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public List<WebElement> tabsContent;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']//a")
	public List<WebElement> listOfCollectiontabs;
	
	@FindBy(xpath="//h4[text()='Best Sellers']")
	public WebElement sellerTile;
	
	@FindBy(xpath="//h4[text()='Best Sellers']")
	public List<WebElement> bestSellers;
	
	@FindBy(xpath="//h4[text()='Best Sellers']/following-sibling::div//a")
	public List<WebElement> listOfBestSellers;
	
	@FindBy(xpath="//h3[contains(text(),'Learn from the Best')]")
	public WebElement learnFromBest;
	
	@FindBy(xpath="//h3[contains(text(),'Learn from the Best')]")
	public List<WebElement> learnFromBests;
	
	@FindBy(xpath="//h3[text()='Learn from the Best']/following-sibling::div//div[contains(@class,'col-md-4')]//a[@class='esportstilea']")
	public List<WebElement> listOfBests;
	
	
	@FindBy(xpath="//h4[contains(text(),'Feature')]")
	public WebElement featureProduct;
	
	@FindBy(xpath="//h4[contains(text(),'Feature')]")
	public List<WebElement> featureProducts;
	
	@FindBy(xpath="//h2[contains(text(),'Feature')]")
	public WebElement featureProduct1;
	
	@FindBy(xpath="//h2[contains(text(),'Feature')]")
	public List<WebElement> featureProducts1;
	
	@FindBy(xpath="//h4[contains(text(),'Feature')]/following-sibling::div//a")
	public List<WebElement> listOfhrefFeature;
	
	@FindBy(xpath="//h2[contains(text(),'Feature')]/following-sibling::div//a")
	public List<WebElement> listOfhrefFeature1;
	
	@FindBy(xpath="//h4[contains(text(),'Featured Games')]")
	public WebElement featureGame;
	
	@FindBy(xpath="//h4[contains(text(),'Featured Games')]")
	public List<WebElement> featureGames;
	
	@FindBy(xpath="//h4[contains(text(),'Featured Games')]/following-sibling::div//a")
	public List<WebElement> featureGamesHrefs;
	
	@FindBy(xpath="//div[@class='console-grid3']")
	public WebElement gridOfConsole;
	
	@FindBy(xpath="//div[@class='console-grid3']")
	public List<WebElement> gridOfConsoles;
	
	@FindBy(xpath="//div[@class='console-grid3']//a")
	public
	List<WebElement> listOfgrids;
	
	@FindBy(xpath="//h4[text()='Shop by Genre']")
	public WebElement shopBygen;
	
	@FindBy(xpath="//h4[text()='Shop by Genre']")
	public List<WebElement> shopBygens;
	
	@FindBy(xpath="//h4[text()='Shop by Genre']//following-sibling::div//a")
	public List<WebElement> shopGeners;
	
	
	@FindBy(xpath="//h4[text()='Shop Accessories']")
	public WebElement shopAcces;
	
	@FindBy(xpath="//h4[text()='Shop Accessories']")
	public List<WebElement> shopAccessories;
	
	@FindBy(xpath="//h4[text()='Shop Accessories']/following-sibling::div//a")
	public List<WebElement> accessoriesHrefs;
	
	@FindBy(xpath="//ol[@class='breadcrumb']/li/a")
	public List<WebElement> breadcrumPath;
	
	@FindBy(xpath="(//div[@class='col']//span)[1]")
	public WebElement searchResults;
	
	
	public static void exportToExcelSheet(int rowstart,String path,List<String> carouselLinks,String DepartmentName,String deptURL,String sheetName) {
		for(int j=rowstart;j<=(carouselLinks.size()+rowstart)-1;j++) {
   		 Base.exportData(path,sheetName, "SL NO", j+2, Integer.toString(j+1));
   		 Base.exportData(path,sheetName, "Department", j+2, DepartmentName);
   		 Base.exportData(path,sheetName, "Department URL", j+2, deptURL);
   		 Base.exportData(path,sheetName, "Navigation URL", j+2, carouselLinks.get(j-rowstart));
        	//System.out.println("Site content updated with navigation url.");
        }
	}

}
