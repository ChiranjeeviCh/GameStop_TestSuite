package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.gamestop.resources.Base;
import com.gamestop.resources.ReusableMethods;

public class HomePage_Stage {
	public static WebDriver driver;
	ReusableMethods rm=new ReusableMethods();
	CustomDatePage cd=new CustomDatePage();
	 public HomePage_Stage(WebDriver driver) {
		HomePage_Stage.driver=driver;
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, CustomDatePage.class);

	}
	 
	@FindBy(xpath="//img[@alt='GameStop Homepage']")
	public static WebElement logoOfCompany;
	
	@FindBy(xpath="//div[@class='home-hero1 hero-theme-light slick-slide slick-cloned']//a")
	public static  List<WebElement> carousals;
	
	@FindBy(xpath="//span[contains(text(),'Deal of the Day')]")
	public static WebElement dealsOftheDay;
	
	@FindBy(xpath="//span[contains(text(),'Deal of the Day')]")
	public static List<WebElement> dealsDay;
	
	@FindBy(xpath="//span[contains(text(),'Deal of the Day')]/ancestor::div[@class='product-content-info']/following-sibling::div//div[contains(@class,'product grid-tile')]//a[@role='presentation']")
	public static List<WebElement> listOfDeals;
	
	
	@FindBy(xpath="//div[@class='col-12 blade-image-container']")
	public static WebElement	bladeimage;
	
	@FindBy(xpath="//div[@class='col-12 blade-image-container']")
	public static List<WebElement> bladeimages;
	
	@FindBy(xpath="//div[@class='col-12 blade-image-container']//a")
	public static List<WebElement> blades;
	
	@FindBy(xpath="//h2[text()='Most Anticipated Video Games']")
	public static WebElement mostAnticipatedVG;
	
	@FindBy(xpath="//h2[text()='Most Anticipated Video Games']")
	public static List<WebElement> mostAnticipatedVGs;
	
	@FindBy(xpath="//h2[text()='Most Anticipated Video Games']/parent::div/following-sibling::div//a")
	public static List<WebElement> listOfMostAntiVGs;
	
			
	@FindBy(xpath="//h2[text()='Featured Products & Offers']")
	public static  WebElement featurePO;
	
	@FindBy(xpath="//h2[contains(text(),'Accessories')]")
	public static WebElement access;
	
	@FindBy(xpath="//h2[contains(text(),'Accessories')]")
	public static List<WebElement> hotaccess;
	
	@FindBy(xpath="//h2[contains(text(),'Accessories')]/parent::div/following-sibling::div//a")
	public static List<WebElement> listOfHotacces;
	
	@FindBy(xpath="//h2[text()='Collectibles Offers']")
	public static WebElement collectibleTitle;
	
	@FindBy(xpath="//h2[text()='Collectibles Offers']")
	public static List<WebElement> collectibles;
	
	@FindBy(xpath="//h2[text()='Collectibles Offers']/parent::div/following-sibling::div//a")
	public static List<WebElement> listOfCollectibles;
	
	
	
	@FindBy(xpath="//h2[text()='Featured Products & Offers']/parent::div/following-sibling::div//a")
	public  List<WebElement> prodOffers;
	
	@FindBy(xpath="//h2[contains(text(),'More Products & Offers')]")
	public static WebElement morePO;
	
	@FindBy(xpath="//h2[contains(text(),'More Products & Offers')]")
	public static List<WebElement> morePOs;
	
	@FindBy(xpath="//h2[contains(text(),'More Products & Offers')]/parent::div//following-sibling::div//a")
	public static List<WebElement> listOfMPO;
	
	
	@FindBy(xpath="//span[contains(text(),'Top Selling Video Games')]")
	public static  WebElement topSelling;
	
	
	@FindBy(xpath="//span[contains(text(),'Top Selling Video Games')]")
	public static  List<WebElement> topSellings;
	
	@FindBy(xpath="//span[contains(text(),'Top Selling Video Games')]/ancestor::div/following-sibling::div[@data-mobile-view-type='mobileCarousel-2up']//a[@role='presentation']")
	public static  List<WebElement> listOfGames;
	
	@FindBy(xpath="//h3[contains(text(),'Recommended Products')]")
	public static  WebElement recomdProdstitle;
	
	@FindBy(xpath="//h3[contains(text(),'Recommended Products')]")
	public static  List<WebElement> recomdProdstitles;
	
	@FindBy(xpath="//div[@id='home1_rr']//a[@role='presentation']")
	public static  List<WebElement> listOfRP;
	
	@FindBy(xpath="//span[contains(text(),'Top Selling Collectibles')]")
	public  WebElement sellingtitle;
	
	@FindBy(xpath="//span[contains(text(),'Top Selling Collectibles')]/ancestor::div[@class='product-content-info']//following-sibling::div//a[@role='presentation']")
	public static  List<WebElement> topCollectibles;
	
	@FindBy(xpath="//h2[text()='Video Games Coming Soon']")
	public static  WebElement videoGamesCStitle;
	
	@FindBy(xpath="//h2[text()='Video Games Coming Soon']")
	public static  List<WebElement> videoGamesCStitles;
	
	@FindBy(xpath="//h2[text()='Video Games Coming Soon']/parent::div/following-sibling::div//a")
	public static  List<WebElement> listOfvideoGamesCS;
	
	@FindBy(xpath="//ol[@class='breadcrumb']/li/a")
	public static  List<WebElement> breadcrumb;
	
	@FindBy(xpath="(//div[@class='col']//span)[1]")
	public static List<WebElement> searchResults;
	
	@FindBy(xpath="(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']")
	public static  WebElement middleSearchResults;
	
	public WebElement middleSearchResults() {
		return middleSearchResults;
	}
	
	public static List<WebElement> searchResults() {
		return searchResults;
	}
	
	
	
	public static List<WebElement> breadcrumbPath(){
		return breadcrumb;
	}
	
	public static List<WebElement> listOfvideoGamesCS(){
		return listOfvideoGamesCS;
	}
	
	
	public WebElement videoGamesCStitle() {
		return videoGamesCStitle;
	}
	public static List<WebElement> topCollectibles(){
		return topCollectibles;
	}
	
	public WebElement sellingTitle() {
		return sellingtitle;
	}
	
	public List<WebElement> listOFprods(){
		return listOfRP;
	}
	
	
	public  WebElement titleOfProds() {
		return recomdProdstitle;
	}
	
	public List<WebElement> TopGames() {
		return listOfGames;
	}
	
	public WebElement sellingGames() {
		return topSelling;
	}
	
	public List<WebElement> listofprods(){
		return prodOffers;
	}
	
	
	public static WebElement featureProOff() {
		return featurePO;
	}
	
	
	public List<WebElement> listOfCarousals(){
		return carousals;
	}
	
	
	public void stagingPlus1Day() throws Exception {
		Base.WaitForElementPresent(cd.settings(), 10);
		cd.settings().click();
		Base.WaitForElementPresent(cd.sitePriview, 5);
		cd.sitePriview.click();
		Base.switchToFrame("dwControlPanel");
		//driver.switchTo().frame("dwControlPanel");
		System.out.println("we are in inside subframe");
		Base.wait2Seconds();
		Base.WaitForElementPresent(cd.datePicker, 2);
		cd.datePicker.click();
		driver.switchTo().parentFrame();
		System.out.println("come out of from subframe");
		cd.dateSelect.click();
		//TestUtils.selectNextDay(cd.calenderDates,1);
		//dateSelect.click();
		Base.wait5Seconds();
		Base.switchToFrame("dwControlPanel");
		Base.WaitForElementPresent(cd.customerOptions(), 5);
		rm.getValuefromdropDown(cd.customerOptions(), "Everyone");
		cd.okButton.click();
		Base.wait5Seconds();
		
	}
	public void validatePageTitle() {
		Assert.assertEquals(driver.getTitle(), "Consoles, Collectibles, Video Games and VR | GameStop");
		
	}
	
	public void validateLogo() {
		Assert.assertTrue(Base.checkIfElementPresent(logoOfCompany));
		
	}
	public static void getCarousals(List<WebElement> listOfLinks,List<String> addToList) throws Exception {
		Base.WaitForListOfElementPresent(listOfLinks, 10);
		for(WebElement ele:listOfLinks) {
			String href=ele.getAttribute("href");
			addToList.add(href);
			
		}
		
	}
	public static void getHrefs(WebElement element,List<WebElement> elements,List<WebElement> listOfLinks,List<String> addToList) throws Exception {
		try {
		if(elements.size()==1) {
			Base.scrollToElement(element);
			Base.WaitForListOfElementPresent(listOfLinks, 10);
			for(WebElement ele:listOfLinks) {
				String href=ele.getAttribute("href");
				addToList.add(href);
				
			}
			}else {
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exportToExcelSheet(String path,List<String> carouselLinks,String deptURL,String sheetName) {
		for(int j=0;j<=carouselLinks.size()-1;j++) {
   		 Base.exportData(path,sheetName, "SL NO", j+2, Integer.toString(j+1));
   		 Base.exportData(path,sheetName, "Department", j+2, "HOME PAGE");
   		 Base.exportData(path,sheetName, "Department URL", j+2, deptURL);
   		 Base.exportData(path,sheetName, "Navigation URL", j+2, carouselLinks.get(j));
        	
        }
	}
	
	
	public static void getSearchElement( int rowStart,List<String> carouselLinks, String path,String sheetName) throws Exception {
		for(int i=rowStart;i<=carouselLinks.size()-1;i++) {
        		driver.get(carouselLinks.get(i-rowStart));
        		driver.manage().deleteAllCookies();
        		 String breadcrumbPath = "";
        		String srchelmnt = "";
        			for(WebElement ele: breadcrumbPath()) {
            			String brdcrumbtext = ele.getText();
            			System.out.print(brdcrumbtext+" >> ");
            		}
        		Base.wait5Seconds();
        		for(WebElement ele:searchResults) {
        			srchelmnt = ele.getText();
        			
        		}
        		srchelmnt=srchelmnt.replaceAll("\\s", " ");
        		String srch[]=srchelmnt.split(" ");
            		
            		//String srch[]=srchelmnt.split(" ");
            		if(driver.getCurrentUrl().contains("products")) {
            			Base.exportData(path,sheetName, "Breadcrumb Path", i+2, breadcrumbPath);
            			Base.exportData(path,sheetName, "Comments", i+2, "Product Page");
    		}
    		
    		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
    			Base.exportData(path,sheetName, "Breadcrumb Path", i+2, "Home");		
    			Base.exportData(path,sheetName, "Comments", i+2, "Landing Page");
    		}
    		else if(srchelmnt.equals("")) {
    			String text1=middleSearchResults.getText();
    			
        			if(driver.getTitle().contains("Store")) {
        				Base.exportData(path,sheetName, "Breadcrumb Path", i+2, breadcrumbPath);		
        				Base.exportData(path,sheetName, "Comments", i+2, "StorePage");
        			}else {
        			Base.exportData(path,sheetName, "Breadcrumb Path", i+2, breadcrumbPath);		
        			Base.exportData(path,sheetName, "Comments", i+2, text1);
        			}
    		}
    		
    		else {
    			Base.exportData(path,sheetName, "Breadcrumb Path", i+2, breadcrumbPath);
            		
    			Base.exportData(path,sheetName, "Comments", i+2, srch[0]);
            		
            		System.out.println();
    		}          	 }
    	

		
	}
	
 	 

}
