package com.gamestop.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gamestop.resources.Base;
import com.gamestop.resources.TestUtils;
import com.gamestop.workflows.Accessories;
import com.gamestop.workflows.Clothings;
import com.gamestop.workflows.Deals;
import com.gamestop.workflows.Eletronics;
import com.gamestop.workflows.Home_Stage;
import com.gamestop.workflows.ToysAndCollectibilies;
import com.gamestop.workflows.VideoGames;
import com.gamsestop.pages.AccessoriesPage;
import com.gamsestop.pages.ClothingsPage;
import com.gamsestop.pages.DealsPage;
import com.gamsestop.pages.EletronicsPage;
import com.gamsestop.pages.HomePage_Stage;
import com.gamsestop.pages.ToysAndCollectibilesPage;
import com.gamsestop.pages.VideoGamesPage;
import com.gamsestop.pages.VideoGames_HomePage;

public class GameStop_E2E_stage extends Base{
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(GameStop_E2E_stage.class.getName());
	HomePage_Stage HomePage=new HomePage_Stage(driver);
	List<String> carouselLinks= new ArrayList<>();	
	Home_Stage hs=new Home_Stage();
	TestUtils tu;
	public static  Map<String, List<String>> siteContent = new HashMap<>();
	String path=System.getProperty("user.dir")+"\\Testdata\\ExportExcel.xlsx";
	HashMap<String,List<String>> inputData;
	VideoGames vg;
	VideoGamesPage vmp=new VideoGamesPage(driver);
	VideoGames_HomePage vgh=new VideoGames_HomePage(driver);
	AccessoriesPage acc=new AccessoriesPage(driver);;
	Accessories acs;
	ToysAndCollectibilesPage tsc=new ToysAndCollectibilesPage(driver);
	ToysAndCollectibilies ts;
	EletronicsPage es=new EletronicsPage(driver);
	Eletronics elts;
	ClothingsPage clt=new ClothingsPage(driver);
	Clothings cs;
	DealsPage dp=new DealsPage(driver);
	Deals ds;
	
	
	@BeforeTest
	public void driverInitiate() throws Exception {
		log.info("Initializing  webdriver");
		this.driver=initializedriver();
		acs=new Accessories(driver);
		ts=new ToysAndCollectibilies(driver);
		elts=new Eletronics();
		cs=new Clothings();
		ds=new Deals();
		inputData=Base.readFileOnce(path, "Input Content");
		hs.selectingDate();
	}

	
	//@Test(priority=1)
	public void homePage() throws Exception {
		test = extent.createTest("HomePage", "Validating Home Page");
		
		Base.wait10Seconds();
		Base.WaitForListOfElementPresent(HomePage_Stage.carousals, 20);//waititng for the carousals 
		HomePage_Stage.getCarousals(HomePage_Stage.carousals,carouselLinks);//get the carousals
		
		log.info("Home page carousals are :"+carouselLinks.size());
		
		//get the hrefs of deals  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.dealsOftheDay,HomePage_Stage.dealsDay, HomePage_Stage.listOfDeals, carouselLinks);
		log.info("After adding Deals of the day:"+carouselLinks.size());
		
		//get the hrefs of hot new accessories  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.access, HomePage_Stage.hotaccess, HomePage_Stage.listOfHotacces, carouselLinks);
		log.info("After adding hot new accessories :"+carouselLinks.size());
		
		//get the hrefs of most anticipated video games  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.mostAnticipatedVG, HomePage_Stage.mostAnticipatedVGs, HomePage_Stage.listOfMostAntiVGs, carouselLinks);
		log.info("After adding most anticipated VGs:"+carouselLinks.size());
		
		//get the hrefs of top selling video games  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.topSelling,HomePage_Stage.topSellings,HomePage_Stage.listOfGames,carouselLinks);
		log.info("After adding top selleing games :" +carouselLinks.size());
		
		//get the hrefs of blade image  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.bladeimage, HomePage_Stage.bladeimages, HomePage_Stage.blades, carouselLinks);
		log.info("After adding blade images:"+carouselLinks.size());
		
		//get the hrefs of collectibles offers  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.collectibleTitle, HomePage_Stage.collectibles, HomePage_Stage.listOfCollectibles, carouselLinks);
		log.info("After adding Collectible offers:"+carouselLinks.size());
		
		//get the hrefs of more products and offers  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.morePO, HomePage_Stage.morePOs, HomePage_Stage.listOfMPO, carouselLinks);
		log.info("After adding More Product & Features "+carouselLinks.size());
		
		//get the hrefs of recommanded products  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.recomdProdstitle,HomePage_Stage.recomdProdstitles,HomePage_Stage.listOfRP,carouselLinks);
		log.info("After adding Recommended Products "+carouselLinks.size());
		
		//get the hrefs of top selling collectibles  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.topSelling, HomePage_Stage.topSellings, HomePage_Stage.topCollectibles(), carouselLinks);
		log.info("After adding Top Selling collectabiles "+carouselLinks.size());
		
		//get the hrefs of top selling video games  and added to the list
		HomePage_Stage.getHrefs(HomePage_Stage.videoGamesCStitle, HomePage_Stage.videoGamesCStitles, HomePage_Stage.listOfvideoGamesCS(), carouselLinks);
		log.info("After adding Video Games Coming Soon "+carouselLinks.size());
		
		//get the size of the all links from Homepage
		log.info("total links from home page is "+carouselLinks.size());
		
		//adding all links into map
		siteContent.put("Navigation URLs", carouselLinks);
		
		HomePage_Stage.exportToExcelSheet(path, carouselLinks, inputData.get("Department URL").get(0),"Output_stage");
	
		//int rowStart=Base.getRowCount(path,"Output_stage");
		HomePage_Stage.getSearchElement(0, carouselLinks, path, "Output_stage");
		 

	}
	@Test(priority=2)	
	public void videoGames_homepage() throws Exception {
		vg=new VideoGames(driver);
		test = extent.createTest("video games homepage ", "Validating  video games homepage");
		Base.WaitForElementPresent(vgh.videoGamesLink, 10);
		vgh.getHrefAndNavigate(vgh.videoGamesLink);	
		HomePage_Stage.getHrefs(vgh.homeGrid, vgh.homeGrids, vgh.listOfHomegrids, carouselLinks);
		log.info("After adding the grid values :"+carouselLinks.size());
		HomePage_Stage.getHrefs(vgh.videoGamesPlaatform, vgh.videoGamesPlaatforms, vgh.videoGamesgrids, carouselLinks);
		
		  int rowStart=Base.getRowCount(path,"Output_stage"); 	  
		  
		  VideoGamesPage.exportToExcelSheet(rowStart,path, carouselLinks, "VideoGames HomePage ","https://sfcc-stg.gamestop.com/video-games", "Output_stage");
		  
		  HomePage_Stage.getSearchElement(rowStart, carouselLinks, path, "Output_stage");
		}
	@Test(priority=3)
	public void videoGames() throws Exception {
		test = extent.createTest("video games", "Validating  video games");
		Base.WaitForElementPresent(vmp.categoryLink, 10);
		//Base.JavaScriptClick(vmp.categoryLink);
		vg.clickOnElement(vmp.categoryLink);
		//vmp.categoryLink.click();
		Base.WaitForObjectToBeClickable(vmp.viewMore, 10);
		vmp.viewMore.click();
		Base.WaitForListOfElementPresent(vmp.allLinks, 10);
        for(int j=0;j<=vmp.allLinks.size()-1;j++) {        	
        	String linkText=Base.getText(vmp.TextofLink, j);
	       vmp.allLinks.get(j+1).click();
	       if(linkText.equals("Retro Gaming")) {
	    	  vmp.retroGaming.click();
	       }else if(linkText.equals("More Platforms")) {
	    	   vmp.morePlatforms.click();
	    	   
	       }
	       Base.WaitForListOfElementPresent(vmp.listOfCarousals, 20);
	       HomePage_Stage.getCarousals(vmp.listOfCarousals, carouselLinks);
	       HomePage_Stage.getHrefs(vmp.homeGrid, vmp.homeGrids, vmp.listOfHomegrids, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.collectiontabs, vmp.tabsContent, vmp.listOfCollectiontabs, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.sellerTile, vmp.bestSellers, vmp.listOfBestSellers, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.learnFromBest, vmp.learnFromBests, vmp.listOfBests, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.featureProduct, vmp.featureProducts, vmp.listOfhrefFeature, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.featureProduct1, vmp.featureProducts1, vmp.listOfhrefFeature1, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.featureGame, vmp.featureGames, vmp.featureGamesHrefs, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.gridOfConsole, vmp.gridOfConsoles, vmp.listOfgrids, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.shopBygen, vmp.shopBygens, vmp.shopGeners, carouselLinks);
 	       HomePage_Stage.getHrefs(vmp.shopAcces, vmp.shopAccessories, vmp.accessoriesHrefs, carouselLinks); 	       
 	       int rowStart=Base.getRowCount(path,"Output_stage"); 	       
 	       VideoGamesPage.exportToExcelSheet(rowStart,path, carouselLinks, "Video Games","https://sfcc-stg.gamestop.com/video-games/"+linkText, "Output_stage");
 	       HomePage_Stage.getSearchElement(rowStart, carouselLinks, path, "Output_stage");
 	      Base.Click(vmp.categoryLink);
 			//Base.JavaScriptClick(vmp.categoryLink);
 			Base.WaitForObjectToBeClickable(vmp.viewMore, 10);
 			vmp.viewMore.click();
        }
 		 
	      	}
	
	@Test(priority=3)
	public void accessories() throws Exception {
		test = extent.createTest("Accessories", "Validating accessories");
		Base.WaitForElementPresent(acc.accessoriesLink, 10);
		acc.accessoriesLink.click();
		Base.WaitForElementPresent(acc.viewMoreAcc, 10);
		acc.viewMoreAcc.click();
		Base.WaitForListOfElementPresent(acc.allAccOptions, 10);
		for(int j=0;j<=acc.allAccOptions.size()-1;j++) {
     	   String linkText=Base.getText(acc.textOfLink, j);        	
 	      
 	       acc.allAccOptions.get(j+1).click();
 	       String attributeValue=acc.allAccOptions.get(j+1).getAttribute("href");
 	     
 	       System.out.println(Base.getRowCount(path,"Output_stage"));
        		int rowStart=Base.getRowCount(path,"Output_stage");
        	acs.getResultsAndexportToExcel(path,rowStart,"Output_satge",linkText, attributeValue);	
        	
        	HomePage_Stage.getCarousals(acc.listOfcarousals, carouselLinks);	
        	
        	HomePage_Stage.getHrefs(acc.homeGrid, acc.homeGrids, acc.listOfHomegrids, carouselLinks);
 	       
        	HomePage_Stage.getHrefs(acc.featuregame, acc.featuregames, acc.listOfFGs,carouselLinks);
        	
        	VideoGamesPage.exportToExcelSheet(rowStart, path, carouselLinks, "Accessories"+linkText, "https://sfcc-stg.gamestop.com/Accessories/"+linkText, "Output_stage");
  		
   		            	
  	         HomePage_Stage.getSearchElement(rowStart, carouselLinks, path, "Output_stage");        
       }
	}
	@Test(priority=4)
	public void toysAndCollectiblies() throws Exception {
		test = extent.createTest("ToysAndCollectiblies", "Validating toys & collectiblies");
		Base.WaitForElementPresent(tsc.toysAndCollLink, 10);
		ts.clickOnElement(tsc.toysAndCollLink);
		Base.WaitForElementPresent(tsc.viewMore, 5);
		ts.clickOnElement(tsc.viewMore);
        
		Base.WaitForListOfElementPresent(tsc.allLinks,10);
       for(int j=0;j<=tsc.allLinks.size()-1;j++) {
      
    	  String linkText=Base.getText(acc.textOfLink, j);   

	       acc.allAccOptions.get(j+1).click();
	       String attributeValue=acc.allAccOptions.get(j+1).getAttribute("href");
	     
	       System.out.println(Base.getRowCount(path,"Output_stage"));
       		int rowStart=Base.getRowCount(path,"Output_stage");
	       acs.getResultsAndexportToExcel(path,rowStart,"Output_satge",linkText, attributeValue);	
	       
	       HomePage_Stage.getCarousals(tsc.listOfCarousals, carouselLinks);
 	  		HomePage_Stage.getHrefs(tsc.homeGrid, tsc.homeGrids, tsc.listOfHomegrids, carouselLinks);
 	  		
 	  		HomePage_Stage.getHrefs(tsc.homeGrid1, tsc.homeGrids1, tsc.listOfHomeGrids1, carouselLinks);
 	  		
 	  		HomePage_Stage.getHrefs(tsc.rowgrid, tsc.rowgrids, tsc.listOfRowgrids, carouselLinks);
 	  		
 	  		HomePage_Stage.getHrefs(tsc.featurePD, tsc.featurePDs, tsc.listOfFPds, carouselLinks);
 	  		
 	  		HomePage_Stage.getHrefs(tsc.featurePD1, tsc.featurePDs1, tsc.listOfFPds1, carouselLinks);
 	  		
 	  		HomePage_Stage.getHrefs(tsc.shobBycate, tsc.shobBycategory, tsc.listOfcategories, carouselLinks);
 	  	 
 	  		int rowStart1=Base.getRowCount(path,"Output_stage");
 	  		
 	  		VideoGamesPage.exportToExcelSheet(rowStart1, path, carouselLinks, "Toys & Collectibiles"+linkText, "https://sfcc-stg.gamestop.com/toys-collectibles/"+linkText, "Output_stage");
	            	
 	  		HomePage_Stage.getSearchElement(rowStart1, carouselLinks, path, "Output_stage");   
	            	       	}
 
	}
	@Test(priority=5)
	public void electronics() throws Exception {
		test = extent.createTest("Electronics", "Validating eletronics");

		Base.WaitForElementPresent(es.electronicLink, 10);
		ts.clickOnElement(es.electronicLink);
		if(es.viewMores.size()==1) {
			Base.WaitForElementPresent(es.viewMore, 10);
			es.viewMore.click();
		}
       for(int j=0;j<=es.allEletronics.size()-1;j++) {
    	   String linkText=Base.getText(acc.textOfLink, j);   

	       acc.allAccOptions.get(j+1).click();
	       String attributeValue=acc.allAccOptions.get(j+1).getAttribute("href");
	       System.out.println(Base.getRowCount(path,"Output_stage"));
	       int rowStart=Base.getRowCount(path,"Output_stage");
	       acs.getResultsAndexportToExcel(path,rowStart,"Output_satge",linkText, attributeValue);
	       HomePage_Stage.getCarousals(es.carousals, carouselLinks);
	       HomePage_Stage.getHrefs(es.homegrid, es.homegrids, es.listOfGids, carouselLinks);
	       int rowStart1=Base.getRowCount(path,"Output_stage");    	
	         VideoGamesPage.exportToExcelSheet(rowStart1, path, carouselLinks, "Eletronics"+linkText,"https://sfcc-stg.gamestop.com/electronics/"+linkText, "Output_stage");   	
	         HomePage_Stage.getSearchElement(rowStart1, carouselLinks, path, "Output_stage");   	 
	            		}
        	

	}
	@Test(priority=6)
	public void clothing() throws Exception {
		test = extent.createTest("Clothing", "Validating  clothings");

		Base.WaitForElementPresent(clt.clothingLink, 10);
		ts.clickOnElement(clt.clothingLink);
		
      HomePage_Stage.getCarousals(clt.listOfCarousals, carouselLinks);
      HomePage_Stage.getHrefs(clt.featuredProd, clt.featuredProds, clt.listOfFPs, carouselLinks);
      HomePage_Stage.getHrefs(clt.grid, clt.grids, clt.listOfGrids, carouselLinks);
      HomePage_Stage.getHrefs(clt.grid1, clt.grids1, clt.llistOfGrids1, carouselLinks);
      
      int rowStart1=Base.getRowCount(path,"Output_stage");    	
      
      VideoGamesPage.exportToExcelSheet(rowStart1, path, carouselLinks, "Clothings","https://sfcc-stg.gamestop.com/Clothings", "Output_stage");
      
      HomePage_Stage.getSearchElement(rowStart1, carouselLinks, path, "Output_stage");  
	}
	@Test(priority=7)
	public void deals() throws Exception {
		test = extent.createTest("Deals", "Validating Deals");

		Base.WaitForElementPresent(dp.dealsLink, 10);
	       
	       int rowStart=Base.getRowCount(path,"Output_stage");
	       HomePage_Stage.getCarousals(dp.listOfCarousals, carouselLinks);
	       HomePage_Stage.getHrefs(dp.homeGrid, dp.homeGrids, dp.listofGrids, carouselLinks);
	       HomePage_Stage.getHrefs(dp.homegrid1, dp.homegrids1, dp.listOfGrids1, carouselLinks);
	       VideoGamesPage.exportToExcelSheet(rowStart, path, carouselLinks, "Deals", "https://sfcc-stg.gamestop.com/Deals", "Output_stage");
	       HomePage_Stage.getSearchElement(rowStart, carouselLinks, path, "Output_stage");
        		
	}
	
	@Test(priority=8)
	public void dealsOfTheDay() throws IOException{

	       System.out.println(Base.getRowCount(path,"Output_stage"));
	       int rowStart=Base.getRowCount(path,"Output_stage");
	      
    
	       driver.get("https://sfcc-stg.gamestop.com/deals/deal-of-the-day");
	       JavascriptExecutor js = ((JavascriptExecutor)driver);
	  		List<WebElement> caruosals;   List<WebElement> crousalelements = driver.findElements(By.xpath("//div[contains(@id,'slick-slide')]//div//a"));
	       
	        System.out.println("The total no of crousals are "+crousalelements.size());
			ArrayList<String> carouselLinks= new ArrayList<>();
			for(WebElement link:crousalelements) {
				String href=link.getAttribute("href");
					carouselLinks.add(href);
					System.out.println(href);
			}
	 	  		if(driver.findElements(By.xpath("//div[@class='collection-tabs4 content-width']")).size()==1) {
	     			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='collection-tabs4 content-width']")));
	     			caruosals = driver.findElements(By.xpath("//div[@class='collection-tabs4 content-width']//a"));
	            
	             for(WebElement ele: caruosals) {
	                 String gridlnk = ele.getAttribute("href");
	                carouselLinks.add(gridlnk);
	             }
	             System.out.println("After adding the grid values :"+carouselLinks.size());
	     		}else {
	     			System.out.println("home grids are not identified");
	     		}
	        		System.out.println("Total links from list:"+carouselLinks.size());
		        	//Base.readFileOnce(path,"Input Content");
		        	 for(int j=rowStart;j<=(carouselLinks.size()+rowStart)-1;j++) {
		        		 Base.exportData(path,"Output_stage", "SL NO", j+1, Integer.toString(j));
		        		 Base.exportData(path,"Output_stage", "Department", j+1, "Deals of the Day Page");
		        		 Base.exportData(path,"Output_stage", "Department URL", j+1, "https://sfcc-stg.gamestop.com/Deals of the Day");
		        		 Base.exportData(path,"Output_stage", "Navigation URL", j+1, carouselLinks.get(j-rowStart));
		 	        	//System.out.println("Site content updated with navigation url.");
		 	        }
		        	
		 	        if(driver.findElements(By.xpath("//div[@class='container mt-5 p-0']")).size()==1) {
		 	        	js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='container mt-5 p-0']//a")));
		 	 	          caruosals = driver.findElements(By.xpath("//div[@class='container mt-5 p-0']//a"));
		 	 	        
		 	 	         for(WebElement ele: caruosals) {
		 	 	             String gridlnk = ele.getAttribute("href");
		 	 	            carouselLinks.add(gridlnk);
		 	 	         }
		 	 	         System.out.println("After adding the grid values :"+carouselLinks.size());
		 	 	 		}else {
		 	 	 			System.out.println("home grids are not identified");
		 	 	 		}
		 	        		
	        		 for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
			        		driver.get(carouselLinks.get(i-rowStart));
			        		driver.manage().deleteAllCookies();
			        	List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));			        		
			        			 String breadcrumbPath = "";
			            		for(WebElement ele: brdcrmup) {
			            			String brdcrmtxt = ele.getText();
			            			System.out.print(brdcrmtxt+" >> ");
			            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
			            			
			            		}
			            		
			            		System.out.println(breadcrumbPath);
			            		String srchelmnt = "";
			            		List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
			            		for(WebElement ele:srchElmnts) {
			            			srchelmnt = ele.getText();
			            			System.out.print("Search element is:"+srchelmnt);
			            			
			            		}
			            		String srch[]=srchelmnt.split(" ");
			            		if(driver.getCurrentUrl().contains("products")) {
			            			Base.exportData(path,"Output_stage", "Breadcrumb Path", i+1, breadcrumbPath);
	                                    Base.exportData(path,"Output_stage", "Comments", i+1, "Product Page");
			            		}
			            		
			            		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
			            			Base.exportData(path,"Output_stage", "Breadcrumb Path", i+1, "Home");		
		                			Base.exportData(path,"Output_stage", "Comments", i+1, "Landing Page");
			            		}
			            		else if(srchelmnt.equals("")) {
			            			String text1="";
		                			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
		                			for(WebElement srch1: rslts) {
		                    			 text1 = srch1.getText();
		                    			System.out.println(text1);
				            			Base.exportData(path,"Output_stage", "Breadcrumb Path", i+1, breadcrumbPath);		
		                    			Base.exportData(path,"Output_stage", "Comments", i+1, text1);
			            		}
			            		}else {
					            		Base.exportData(path,"Output_stage", "Breadcrumb Path", i+1, breadcrumbPath);
					            		
					            		Base.exportData(path,"Output_stage", "Comments", i+1, srch[0]);
					            		
					            		System.out.println();
			            		}
}

	}
	@Test(priority=8)
	public void giftCards() throws IOException {
		test = extent.createTest("GiftCards", "validating  giftcards");

        //To click on Gift Cards
        WebElement deals_Page = driver.findElement(By.xpath("//a[@data-name='Gift Cards']"));
        String href = deals_Page.getAttribute("href");
        System.out.println(href);
        WebDriverWait wt = new WebDriverWait(driver,15);
        wt.until(ExpectedConditions.elementToBeClickable(deals_Page)).click();

        List<WebElement> crousalelements = driver.findElements(By.xpath("(//ul[@class='slick-dots'])[1]/li/button"));
       
        System.out.println("The total no of crousals are "+crousalelements.size());
		ArrayList<String> carouselLinks= new ArrayList<>();
		carouselLinks.add(href);
        
	   
//	   //For Grid icons::
//	   
//	   ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
//	   ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
//	   List<WebElement> gridelmnt = driver.findElements(By.xpath("//h2[@role='presentation']/ancestor::a"));
//       
//	  for(WebElement ele: gridelmnt) {
//		  String gridlnk = ele.getAttribute("href");
//		  carouselLinks.add(gridlnk);
//	  }
		 System.out.println(Base.getRowCount(path,"Output Content"));
	       int rowStart=Base.getRowCount(path,"Output Content");
	      
//	   
        		System.out.println("Total links from list:"+carouselLinks.size());
	        	//Base.readFileOnce(path,"Input Content");
	        	 for(int j=rowStart;j<=(carouselLinks.size()+rowStart)+1;j++) {
	        		 exportData(path,"Output Content", "SL NO", j, Integer.toString(j-rowStart));
	        		 exportData(path,"Output Content", "Department", j, "GiftCards");
	        		 exportData(path,"Output Content", "Department URL", j, "https://sfcc-stg.gamestop.com/giftcards/");
	        		 exportData(path,"Output Content", "Navigation URL", j, carouselLinks.get(j-rowStart));
	 	        	//System.out.println("Site content updated with navigation url.");
	 	        }
        		
        		 for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
		        		driver.get(carouselLinks.get(i-rowStart));
		        		driver.manage().deleteAllCookies();
		        	List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));			        		
		        			 String breadcrumbPath = "";
		            		for(WebElement ele: brdcrmup) {
		            			String brdcrmtxt = ele.getText();
								//breadcrumb.add(brdcrmtxt);
		            			System.out.print(brdcrmtxt+" >> ");
		            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
		            			//breadcrumbPath=breadcrumbPath.concat(">>"+brdcrmtxt);
		            		}
		            		
		            		System.out.println(breadcrumbPath);
		            		String srchelmnt = "";
		            		List<WebElement> srchElmnts =driver.findElements(By.xpath("//div[@class='col']/h1/span[1]"));
		            		for(WebElement ele:srchElmnts) {
		            			srchelmnt = ele.getText();
		            			System.out.print("Search element is:"+srchelmnt);
		            			
		            		}
		            		String srch[]=srchelmnt.split(" ");
		            		if(driver.getCurrentUrl().contains("products")) {
		            			exportData(path,"Output Content", "Breadcrumb Path", i, breadcrumbPath);
                                    exportData(path,"Output Content", "Comments", i, "Product Page");
		            		}
		            		
		            		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
		            			exportData(path,"Output Content", "Breadcrumb Path", i, "Home");		
	                			exportData(path,"Output Content", "Comments", i, "Landing Page");
		            		}
		            		else if(srchelmnt.equals("")) {
		            			String text1="";
	                			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
	                			for(WebElement srch1: rslts) {
	                    			 text1 = srch1.getText();
	                    			System.out.println(text1);
			            			exportData(path,"Output Content", "Breadcrumb Path", i, breadcrumbPath);		
	                    			exportData(path,"Output Content", "Comments", i, text1);
		            		}
		            		}else {
				            		exportData(path,"Output Content", "Breadcrumb Path", i, breadcrumbPath);
				            		
				            		exportData(path,"Output Content", "Comments", i, srch[0]);
				            		
				            		System.out.println();
		            		}
}

	}
}
