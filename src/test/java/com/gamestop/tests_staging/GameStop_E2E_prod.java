package com.gamestop.tests_staging;

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
import com.gamestop.workflows.Home_Stage;
import com.gamsestop.pages.HomePage_Stage;
import com.gamsestop.pages.VideoGamesPage;

public class GameStop_E2E_prod extends Base {
	
	public static Logger log = LogManager.getLogger(GameStop_E2E_stage.class.getName());
	public WebDriver driver;
	HomePage_Stage HomePage=new HomePage_Stage(driver);
	List<String> carouselLinks= new ArrayList<>();
	Home_Stage hs;
	TestUtils tu;
	public static  Map<String, List<String>> siteContent = new HashMap<>();
	String path=System.getProperty("user.dir")+"\\Testdata\\ExportExcel.xlsx";
	HashMap<String,List<String>> inputData;
	VideoGamesPage vmp=new VideoGamesPage(driver);
	
	@BeforeTest
	public void driverInitiate() throws Exception {
		log.info("Initializing  webdriver");
		
		hs=new Home_Stage();
		
		inputData=Base.readFileOnce(path, "Input Content");
		this.driver=Base.initializedriver();
		hs.getURL();
	}
	@Test
public void homePage_Validation() throws Exception {
		test = extent.createTest("HomePage", "Validating Home Page");
		Base.wait10Seconds();
		Base.WaitForListOfElementPresent(HomePage_Stage.carousals, 20);
		HomePage_Stage.getCarousals(HomePage_Stage.carousals,carouselLinks);
		log.info("Home page carousals are :"+carouselLinks.size());
		HomePage_Stage.getHrefs(HomePage_Stage.mostAnticipatedVG, HomePage_Stage.mostAnticipatedVGs, HomePage_Stage.listOfMostAntiVGs, carouselLinks);
		log.info("After adding most anticipated VGs:"+carouselLinks.size());
		HomePage_Stage.getHrefs(HomePage_Stage.topSelling,HomePage_Stage.topSellings,HomePage_Stage.listOfGames,carouselLinks);
		log.info("After adding top selleing games :" +carouselLinks.size());
		HomePage_Stage.getHrefs(HomePage_Stage.morePO, HomePage_Stage.morePOs, HomePage_Stage.listOfMPO, carouselLinks);
		log.info("After adding More Product & Features "+carouselLinks.size());
		HomePage_Stage.getHrefs(HomePage_Stage.recomdProdstitle,HomePage_Stage.recomdProdstitles,HomePage_Stage.listOfRP,carouselLinks);
		log.info("After adding Recommended Products "+carouselLinks.size());
		HomePage_Stage.getHrefs(HomePage_Stage.topSelling, HomePage_Stage.topSellings, HomePage_Stage.topCollectibles(), carouselLinks);
		log.info("After adding Top Selling collectabiles "+carouselLinks.size());
		HomePage_Stage.getHrefs(HomePage_Stage.videoGamesCStitle, HomePage_Stage.videoGamesCStitles, HomePage_Stage.listOfvideoGamesCS(), carouselLinks);
		log.info("After adding Video Games Coming Soon "+carouselLinks.size());
		log.info("total links from home page is "+carouselLinks.size());
		siteContent.put("Navigation URLs", carouselLinks);
		HomePage_Stage.exportToExcelSheet(path, carouselLinks, inputData.get("Department URL").get(0),"Output_prod");
		HomePage_Stage.getSearchElement(0, carouselLinks, path, "Output_prod");
}
	@Test
	public void videoGames_ContentValidation() throws Exception {
		test = extent.createTest("video games", "Validating  video games");
		Base.Click(vmp.categoryLink);
		Base.WaitForObjectToBeClickable(vmp.viewMore, 10);
		vmp.viewMore.click();
		Base.WaitForListOfElementPresent(vmp.allLinks, 10);
        for(int j=0;j<=vmp.allLinks.size()-1;j++) {        	
        	String linkText=Base.getText(vmp.TextofLink, j);
        	vmp.allLinks.get(j+1).click();
	       if(linkText.equals("Retro Gaming")) {
	    	   Base.Click(vmp.retroGaming);
	       }else if(linkText.equals("More Platforms")) {
	    	  Base.Click(vmp.morePlatforms);
	    	   
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
 	       int rowStart=Base.getRowCount(path,"Output_prod");
 	       VideoGamesPage.exportToExcelSheet(rowStart,path, carouselLinks, "Video Games","https://sfcc-stg.gamestop.com/video-games/"+linkText, "Output_prod");
 	       HomePage_Stage.getSearchElement(rowStart, carouselLinks, path, "Output_prod");
 	       Base.Click(vmp.categoryLink);
 	       Base.WaitForObjectToBeClickable(vmp.viewMore, 10);
 			vmp.viewMore.click();
        }

	}
	@Test
	public void accessories_validation() throws IOException {
		

		WebElement accessories=driver.findElement(By.xpath("//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Accessories']"));
	       WebDriverWait wait = new WebDriverWait(driver, 20);
	        wait.until(ExpectedConditions.elementToBeClickable(accessories));
	        accessories.click();
	        
	        WebElement viewMore=driver.findElement(By.xpath("//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]"));
	        /* WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(accessories));*/
	      
	        viewMore.click();
	       List<WebElement> allAccessories=driver.findElements(By.xpath("//ul[@class='menu-list level-2 view-more']//li/a"));
	       System.out.println(allAccessories.size());
	       //System.out.println(allAccessories);
	       for(int j=0;j<=allAccessories.size()-1;j++) {
	           
	     	   String linkText=driver.findElement(By.xpath("(//div[@class='dropdown-menu block-layout show']//li/a//span[@class='category-name '])"+"["+(j+1)+"]")).getText();
	        	
	 	       System.out.println(linkText);
	 	    
	 	       WebElement link=driver.findElement(By.xpath("(//ul[@class='menu-list level-2 view-more']//li/a)"+"["+(j+1)+"]"));
	 	       String attributeValue=link.getAttribute("href");
	 	       link.click();
	 	       List<String> breadcrumb=new ArrayList<>();
	 	       System.out.println(Base.getRowCount(path,"Output_prod"));
	        		int rowStart=Base.getRowCount(path,"Output_prod");
	 	       if(driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a")).size()>=1) {
	 	    	   List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	     			String srchelmnt = "";
	     			String breadcrumbPath = "";
	     			for(WebElement ele: brdcrmup) {
	          			String brdcrmtxt = ele.getText();
	          			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
	          		}
	     			System.out.println(breadcrumbPath);
	     			if(driver.findElements(By.xpath("(//div[@class='col']//span)[1]")).size()==1) {
	        			List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
	             		for(WebElement ele:srchElmnts) {
	             			srchelmnt = ele.getText();
	    						breadcrumb.add(srchelmnt);
	             			System.out.print("Search element is:"+srchelmnt);
	             			
	             		}
	             		}else {
	             			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
	             			for(WebElement srch: rslts) {
	             				srchelmnt = srch.getText();
	                 			System.out.println(srchelmnt);
	                 			//String[] result=srchelmnt.split(" ");
	             		}
	             		}
	          		String srch[]=srchelmnt.split(" ");
	          		 Base.exportData(path,"Output_prod", "SL NO", rowStart+1, Integer.toString(rowStart));
	         		 Base.exportData(path,"Output_prod", "Department", rowStart+1, "Accessories"+"//"+linkText);
	         		 Base.exportData(path,"Output_prod", "Department URL", rowStart+1, "https://sfcc-stg.gamestop.com/Accessories/"+linkText);
	         		 Base.exportData(path,"Output_prod", "Navigation URL", rowStart+1, attributeValue); 	        	
	          		Base.exportData(path,"Output_prod", "Breadcrumb Path", rowStart+1, breadcrumbPath);         		
	          	    Base.exportData(path,"Output_prod", "Comments", rowStart+1, srch[0]);
	          		
	 	       }
	         	 List<WebElement> crousalelements = driver.findElements(By.xpath("//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]"));
	         	 if(crousalelements.size()>=1) {
	 		 	       
	 		 			ArrayList<String> carouselLinks= new ArrayList<>();
	 		 			
	 		 	        for(int i=1;i<=crousalelements.size();i++) {
	 			 	        	String text = driver.findElement(By.xpath("(//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')])["+i+"]")).getAttribute("href");
	 	 	    	       	  // setCellData("Site Validation", "Navigation URL", i+1, text);
	 				 	    	   carouselLinks.add(text);//
	 				 			  System.out.println(text);
	 			 	       }
	  	       
	  		   System.out.println(carouselLinks);
	  		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  
	   		
	   		 JavascriptExecutor js = ((JavascriptExecutor)driver);
	   		List<WebElement> caruosals;
	   		if(driver.findElements(By.xpath("//div[@class='home-grid6']")).size()==1) {
	 			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='home-grid6']")));
	          caruosals = driver.findElements(By.xpath("//div[@class='home-grid6']//a"));
	        
	         for(WebElement ele: caruosals) {
	             String gridlnk = ele.getAttribute("href");
	            carouselLinks.add(gridlnk);
	         }
	         System.out.println("After adding the grid values :"+carouselLinks.size());
	 		}else {
	 			System.out.println("home grids are not identified");
	 		}
	  	        	if(driver.findElements(By.xpath("//div[@class='container']/h4")).size()==1) {
	  	        WebDriverWait wt1 = new WebDriverWait(driver,30);
	  	        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='container']/h4"))));
	  	       
	  	        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='container']/h4")));
	  	        
	  	        caruosals = driver.findElements(By.xpath("//div[@class='container']/h4/following-sibling::div//a"));
	  	        	  for(WebElement wel:caruosals) {
	  	        		  String href=wel.getAttribute("href");
	  	        		  carouselLinks.add(href);
	  	        		  System.out.println(href);
	  	        	  }
	  			       //System.out.println(text);
	  	        	}else if(driver.findElements(By.xpath("//div[@class='container']/h2")).size()==1) {
	  	        		 WebDriverWait wt1 = new WebDriverWait(driver,30);
	  	     	        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='container']/h2"))));
	  	     	       // JavascriptExecutor js = ((JavascriptExecutor)driver);
	  	     	        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='container']/h2")));
	  	     	        
	  	     	      caruosals = driver.findElements(By.xpath("//div[@class='container']/h2/following-sibling::div//a"));
	  	     	    
	  	        	  for(WebElement wel:caruosals) {
	  	        		  String href=wel.getAttribute("href");
	  	        		  carouselLinks.add(href);
	  	        		  System.out.println(href);
	  	       
	  	        	  }
	  	        	  
	  	     	        
	  	        	}else {
	  	        		System.out.println("Product features are not identified for this ....");
	  	        	}
	  	        	
	  	            	
	  	            	System.out.println("Total links from list:"+carouselLinks.size());
	 	            	
	 	            	 for(int k=rowStart;k<=(carouselLinks.size()+rowStart)-1;k++) {
	 		        		 Base.exportData(path,"Output_prod", "SL NO", k+1, Integer.toString(k));
	 		        		 Base.exportData(path,"Output_prod", "Department", k+1, "Accessories"+"//"+linkText);
	 		        		 Base.exportData(path,"Output_prod", "Department URL", k+1, "https://sfcc-stg.gamestop.com/Accessories/"+linkText);
	 		        		 Base.exportData(path,"Output_prod", "Navigation URL", k+1, carouselLinks.get(k-rowStart));
	 		 	        	//System.out.println("Site content updated with navigation url.");
	 		 	        }
	 	            	 for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
	 	 	        		driver.get(carouselLinks.get(i-rowStart));
	 	 	        		driver.manage().deleteAllCookies();
	 	 	        			
	 	 	        			List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	 	 	        			String srchelmnt = "";
	 	 	        			String breadcrumbPath = "";
	 	 	        			for(WebElement ele: brdcrmup) {
	 	 	            			String brdcrmtxt = ele.getText();
	 	 							
	 	 	            			//System.out.print(brdcrmtxt+" >> ");
	 	 	            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
	 	 	            			
	 	 	            		}
	 	 	            		System.out.println(breadcrumbPath);
	 	 	            	//	String srchelmnt = "";
	 	 	            		List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
	 	 	            		for(WebElement ele:srchElmnts) {
	 	 	            			srchelmnt = ele.getText();
	 	 							breadcrumb.add(srchelmnt);
	 	 	            			System.out.print("Search element is:"+srchelmnt);
	 	 	            			
	 	 	            		}
	 	 	            		String srch[]=srchelmnt.split(" ");
	 	 	            		if(driver.getCurrentUrl().contains("products")) {
	  	            				Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
	  	            				Base.exportData(path,"Output_prod", "Comments", i+1, "Product Page");
	  	            			}else
	  	            				if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
	  	            				Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, "Home");		
	  	            				Base.exportData(path,"Output_prod", "Comments", i+1, "Landing Page");
	  	            			}
	  	            			else 
	  	            				if(srchelmnt.equals("")) {
	 						       			String text1="";
	 						       			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
	 						       			for(WebElement srch1: rslts) {
	 						           			 text1 = srch1.getText();
	 						           			System.out.println(text1);
	 						       			}String result[]=text1.split(" ");
	 						           				Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
	 						           				Base.exportData(path,"Output_prod", "Comments", i+1, result[0]);
	 						           			
	  	            					}else {
	 							       			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
	 						            		
	 							       			Base.exportData(path,"Output_prod", "Comments", i+1, srch[0]);
	 						            		
	 						            		System.out.println();
	 				            		}
	  	            		
	 				 	        		}
	 	            	
	              	                 }	
	         	  //  wait.until(ExpectedConditions.elementToBeClickable(videoGames));
	         	WebElement accessories1=driver.findElement(By.xpath("//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Accessories']"));
	         	
	              accessories1.click();
	              
	              WebElement videoLink1=driver.findElement(By.xpath("//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]"));
	              		videoLink1.click();    
	           	
	       }
	       

	}
	public void toysAndCollectibiles_validation() throws IOException{

        WebElement toys=driver.findElement(By.xpath("//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Toys & Collectibles']"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(toys));
        toys.click();
        
        WebElement viewMore=driver.findElement(By.xpath("//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]"));
        viewMore.click();
       List<WebElement> alltoys=driver.findElements(By.xpath("//ul[@class='menu-list level-2 view-more']/li/a"));
       System.out.println(alltoys.size());
      
       for(int j=0;j<=alltoys.size()-1;j++) {
      
    	   String linkText=driver.findElement(By.xpath("(//ul[@class='menu-list level-2 view-more']/li/a//span[@class='category-name '])"+"["+(j+1)+"]")).getText();
       	
	       System.out.println(linkText);
	    
	       WebElement link=driver.findElement(By.xpath("(//ul[@class='menu-list level-2 view-more']/li/a)"+"["+(j+1)+"]"));
	       String attributeValue=link.getAttribute("href");
	       link.click();
	       List<String> breadcrumb=new ArrayList<>();
	      
	       System.out.println(Base.getRowCount(path,"Output_prod"));
	       int rowStart=Base.getRowCount(path,"Output_prod");
	       if(driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a")).size()>=1) {
	    	   List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	    	   String srchelmnt = "";
				String breadcrumbPath = "";
    			for(WebElement ele: brdcrmup) {
         			String brdcrmtxt = ele.getText();
         			System.out.print("starts before "+brdcrmtxt+" >> ");
         			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
         		}
    			System.out.println(breadcrumbPath);
    			if(driver.findElements(By.xpath("(//div[@class='col']//span)[1]")).size()==1) {
    			List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
         		for(WebElement ele:srchElmnts) {
         			srchelmnt = ele.getText();
						breadcrumb.add(srchelmnt);
         			System.out.print("Search element is:"+srchelmnt);
         			
         		}
         		}else {
   				 	List<WebElement> rslts1= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
	     			for(WebElement srch1: rslts1) {
	     				srchelmnt = srch1.getText();
	         			System.out.println(srchelmnt);
	     				}
	     			}
         		String srch[]=srchelmnt.split(" ");
         		 Base.exportData(path,"Output_prod", "SL NO", rowStart+1, Integer.toString(rowStart));
        		 Base.exportData(path,"Output_prod", "Department", rowStart+1, "Toys & Collectabiles"+"//"+linkText);
        		 Base.exportData(path,"Output_prod", "Department URL", rowStart+1, "https://gamestop.com/Toys & Collectabiles/"+linkText);
        		 Base.exportData(path,"Output_prod", "Navigation URL", rowStart+1, attributeValue);          		
         		 Base.exportData(path,"Output_prod", "Breadcrumb Path", rowStart+1, breadcrumbPath);         		
         	     Base.exportData(path,"Output_prod", "Comments", rowStart+1, srch[0]);
         		
	       }
        	 List<WebElement> crousalelements = driver.findElements(By.xpath("//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]"));
        	       
		 	        System.out.println("The total no of crousals are "+crousalelements.size());
		 			ArrayList<String> carouselLinks= new ArrayList<>();
		 			
		 	        for(int i=1;i<=crousalelements.size();i++) {
			 	        	String text = driver.findElement(By.xpath("(//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')])["+i+"]")).getAttribute("href");
	 	    	       	  // setCellData("Site Validation", "Navigation URL", i+1, text);
				 	    	   carouselLinks.add(text);//
				 			  System.out.println(text);
			 	       }
 	       
 		   System.out.println(carouselLinks);
 		  // WebElement prdcts = driver.findElement(By.xpath("//div[@class='container']/h4"));
 	  		 String text="";
 	  		 JavascriptExecutor js = ((JavascriptExecutor)driver);
 	  		List<WebElement> caruosals;
 	  		if(driver.findElements(By.xpath("//div[@class='home-grid6']")).size()==1) {
     			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='home-grid6']")));
     			caruosals = driver.findElements(By.xpath("//div[@class='home-grid6']//a"));
            
             for(WebElement ele: caruosals) {
                 String gridlnk = ele.getAttribute("href");
                carouselLinks.add(gridlnk);
             }
             System.out.println("After adding the grid values :"+carouselLinks.size());
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
     			System.out.println("home grids are not identified for "+linkText);
     		}	
 	  		if(driver.findElements(By.xpath("//div[@class='row key-art-carousel']")).size()==1) {
     			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='row key-art-carousel']")));
     			caruosals = driver.findElements(By.xpath("//div[@class='col-md-4 p-1 slide']//a"));
            
             for(WebElement ele: caruosals) {
                 String gridlnk = ele.getAttribute("href");
                carouselLinks.add(gridlnk);
             }
             System.out.println("After adding the grid values :"+carouselLinks.size());
     		}else {
     			System.out.println("home grids are not identified for "+linkText);
     		}	
 	  		System.out.println("Feature products starts:");
        	if(driver.findElements(By.xpath("//h4[contains(text(),'Feature')]")).size()==1) {
		        WebDriverWait wt1 = new WebDriverWait(driver,30);
		        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[contains(text(),'Feature')]"))));
		        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h4[contains(text(),'Feature')]")));
        
		        List<WebElement>     hrefs = driver.findElements(By.xpath("//h4[contains(text(),'Feature')]/following-sibling::div//a"));
     	        for(WebElement ele:hrefs) {
     	        	text=ele.getAttribute("href");
     	        	carouselLinks.add(text);
			       System.out.println(text);
     	        }
     	      }else if(driver.findElements(By.xpath("//h2[contains(text(),'Feature')]")).size()==1) {
        		 WebDriverWait wt1 = new WebDriverWait(driver,30);
     	        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Feature')]"))));
     	       // JavascriptExecutor js = ((JavascriptExecutor)driver);
     	        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[contains(text(),'Feature')]")));
	     	      // carouselLinks.add(text);
     	        List<WebElement>     hrefs = driver.findElements(By.xpath("//h2[contains(text(),'Feature')]/following-sibling::div//a"));
     	        for(WebElement ele:hrefs) {
     	        	text=ele.getAttribute("href");
     	        	carouselLinks.add(text);
			       System.out.println(text);
     	        }
     	        
        	}else {
 	 	        		System.out.println("Product features are not identified for this ....");
 	 	        	}
        	System.out.println("After adding product features : "+carouselLinks.size());
 	 	        	System.out.println("Shop by category starts :");
 	 	        	if(driver.findElements(By.xpath("//h4[text()='Shop By Category']")).size()==1) {
 	 	        		 js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h4[text()='Shop By Category']")));
 	 	 	 	        
 	 	 	 	        caruosals = driver.findElements(By.xpath("//h4[text()='Shop By Category']//following-sibling::div//a"));
 	 	 	 	        	  for(WebElement wel:caruosals) {
 	 	 	 	        		  String href=wel.getAttribute("href");
 	 	 	 	        		  carouselLinks.add(href);
 	 	 	 	        		  System.out.println(href);
 	 	 	 	        	  }
 	 	 	 			       //System.out.println(text);
 	 	 	 	        	}
 	 	        	
 	 	        		System.out.println("After adding the shop by categories :"+carouselLinks.size());
 	 	            	System.out.println("Total links from list:"+carouselLinks.size());
	            	//System.out.println(rm.getRowCount("Output_prod"));
	            	//int rowStart=rm.getRowCount("Output_prod");
	            	 for(int k=rowStart;k<=(carouselLinks.size()+rowStart)-1;k++) {
		        		 Base.exportData(path,"Output_prod", "SL NO", k+1, Integer.toString(k));
		        		 Base.exportData(path,"Output_prod", "Department", k+1, "Toys & Collectablies "+"//"+linkText);
		        		 Base.exportData(path,"Output_prod", "Department URL", k+1, "https://gamestop.com/toys-collectibles/"+linkText);
		        		 Base.exportData(path,"Output_prod", "Navigation URL", k+1, carouselLinks.get(k-rowStart));
		 	        	//System.out.println("Site content updated with navigation url.");
		 	        }
	            	 for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
	 	        		driver.get(carouselLinks.get(i-rowStart));
	 	        		driver.manage().deleteAllCookies();
	 	        			
	 	        			List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	 	        			String srchelmnt = "";
	 	        			String breadcrumbPath = "";
	 	        			
	 	            		for(WebElement ele: brdcrmup) {
	 	            			String brdcrmtxt = ele.getText();
	 							//breadcrumb.add(brdcrmtxt);
	 	            			System.out.print("After:"+brdcrmtxt+" >> ");
	 	            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
	 	            			//breadcrumbPath=breadcrumbPath.concat(">>"+brdcrmtxt);
	 	            		
	 	        			}
	 	        			System.out.println(breadcrumbPath);
	 	        			if(driver.findElements(By.xpath("(//div[@class='col']//span)[1]")).size()==1){
	 	            		List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
	 	            		for(WebElement ele:srchElmnts) {
	 	            			srchelmnt = ele.getText();
	 							breadcrumb.add(srchelmnt);
	 	            			System.out.println("Search element is:"+srchelmnt);
	 	            			
	 	            		}
	 	            			 	            		
	 	        			String srch[]=srchelmnt.split(" ");
	 	        			if(driver.getCurrentUrl().contains("products")) {
	            			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
	            			Base. exportData(path,"Output_prod", "Comments", i+1, "Product Page");
	        				System.out.println("Product Page");
	        		}if(driver.getTitle().contains("Store")) {
	        			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1,breadcrumbPath );		
	        			Base.exportData(path,"Output_prod", "Comments", i+1, "Store Page");
	        		}
	        		
	        		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
	        			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, "Home");		
	        			Base.exportData(path,"Output_prod", "Comments", i+1, "Landing Page");
	        			System.out.println("Landing Page");
	        		}
	        		else {
	        			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
		            		
	        		    Base.exportData(path,"Output_prod", "Comments", i+1, srch[0]);
		            		
		            		//System.out.println(srch[0]);
	        		}		
        	 }else {
        		 String text1="";
     			List<WebElement> rslts1= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
     			for(WebElement srch1: rslts1) {
         			 text1 = srch1.getText();
         			System.out.println(text1);
         			//System.out.println(text1);
     		}	String value[]=text1.split(" ");
     			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
     			Base.exportData(path,"Output_prod", "Comments", i+1, value[0]);
     			
        	 }
        	 }
	     
	       		driver.findElement(By.xpath("//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Toys & Collectibles']")).click();;

               driver.findElement(By.xpath("//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]")).click();;
              

       
      	}

	}
	@Test
	public void electronics_validation() throws IOException {
		

        WebElement eletronics=driver.findElement(By.xpath("//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Electronics']"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(eletronics));
        eletronics.click();
        if(driver.findElements(By.xpath("//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]")).size()==1) {
        WebElement viewMore=driver.findElement(By.xpath("//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]"));
        viewMore.click();
        }
       List<WebElement> allAccessories=driver.findElements(By.xpath("//div[@class='dropdown-menu block-layout show']//ul/li/a"));
       System.out.println(allAccessories.size());
       System.out.println(allAccessories);
       for(int j=0;j<=allAccessories.size()-1;j++) {
      
    	   String linkText=driver.findElement(By.xpath("(//div[@class='dropdown-menu block-layout show']//ul/li/a//span[@class='category-name '])"+"["+(j+1)+"]")).getText();
       	
	       System.out.println(linkText);
	    
	       WebElement link=driver.findElement(By.xpath("(//div[@class='dropdown-menu block-layout show']//ul/li/a)"+"["+(j+1)+"]"));
	       String attributeValue=link.getAttribute("href");
	       link.click();
	       List<String> breadcrumb=new ArrayList<>();
	       System.out.println(Base.getRowCount(path,"Output_prod"));
	       int rowStart=Base.getRowCount(path,"Output_prod");
	       if(driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a")).size()>=1) {
	    	   List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
    			String srchelmnt = "";
    			String breadcrumbPath = "";
    			for(WebElement ele: brdcrmup) {
         			String brdcrmtxt = ele.getText();
						
         			//System.out.print(brdcrmtxt+" >> ");
         			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
         		}
    			System.out.println(breadcrumbPath);
    			if(driver.findElements(By.xpath("(//div[@class='col']//span)[1]")).size()==1) {
    			List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
         		for(WebElement ele:srchElmnts) {
         			srchelmnt = ele.getText();
						breadcrumb.add(srchelmnt);
         			System.out.print("Search element is:"+srchelmnt);
         			
         		}
         		}else {
         			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
         			for(WebElement srch: rslts) {
         				srchelmnt = srch.getText();
             			System.out.println(srchelmnt);
             			//String[] result=srchelmnt.split(" ");
         		}
         		}
         		String srch[]=srchelmnt.split(" ");
         		 Base.exportData(path,"Output_prod", "SL NO", rowStart+1, Integer.toString(rowStart));
        		 Base.exportData(path,"Output_prod", "Department", rowStart+1, "Electronic"+"//"+linkText);
        		 Base.exportData(path,"Output_prod", "Department URL", rowStart+1, "https://gamestop.com/Electronic/"+linkText);
        		 Base.exportData(path,"Output_prod", "Navigation URL", rowStart+1, attributeValue); 	        	
         		Base.exportData(path,"Output_prod", "Breadcrumb Path", rowStart+1, breadcrumbPath);         		
         	    Base.exportData(path,"Output_prod", "Comments", rowStart+1, srch[0]);
         		
	       }
        	 List<WebElement> crousalelements = driver.findElements(By.xpath("//h1[contains(text(),'Electronics')]//ancestor::div//following-sibling::div//div[@class='slick-list draggable']//div[@role='tabpanel']//a"));
        	 
		 	        
		 	        System.out.println("The total no of crousals are "+crousalelements.size());
		 			ArrayList<String> carouselLinks= new ArrayList<>();
		 			
		 	        for(int i=1;i<=crousalelements.size();i++) {
			 	        	String text = driver.findElement(By.xpath("(//h1[contains(text(),'Electronics')]//ancestor::div//following-sibling::div//div[@class='slick-list draggable']//div[@role='tabpanel']//a)["+i+"]")).getAttribute("href");
	 	    	       	  // setCellData("Site Validation", "Navigation URL", i+1, text);
				 	    	   carouselLinks.add(text);//
				 			  System.out.println(text);
			 	       }
 	       
		 	        	System.out.println(carouselLinks);
		 		
        	 	
	            	System.out.println("Total links from list:"+carouselLinks.size());
	            	 JavascriptExecutor js = ((JavascriptExecutor)driver);
	            		List<WebElement> caruosals;
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
	            	
	            	
	            	 for(int k=rowStart;k<=(carouselLinks.size()+rowStart)-1;k++) {
		        		 Base.exportData(path,"Output_prod", "SL NO", k+1, Integer.toString(k));
		        		 Base.exportData(path,"Output_prod", "Department", k+1, "Electronics"+"//"+linkText);
		        		 Base.exportData(path,"Output_prod", "Department URL", k+1, "https://sfcc-srg.gamestop.com/Electronics/"+linkText);
		        		 Base.exportData(path,"Output_prod", "Navigation URL", k+1, carouselLinks.get(k-rowStart));
		 	        	//System.out.println("Site content updated with navigation url.");
		 	        }
	            	 for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
	 	        		driver.get(carouselLinks.get(i-rowStart));
	 	        		driver.manage().deleteAllCookies();
	 	        			
	 	        			List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	 	        			String srchelmnt = "";
	 	        			String breadcrumbPath = "";
	 	        			
	 	        			//if(brdcrmup.size()>=1||driver.findElements(By.xpath("//div[@class='col']//span[1]")).size()==1) {
	 	        			//exportData("Output_prod", "Breadcrumb Path", i+2, breadcrumbPath);
	 	        			 
	 	            		for(WebElement ele: brdcrmup) {
	 	            			String brdcrmtxt = ele.getText();
	 							
	 	            			//System.out.print(brdcrmtxt+" >> ");
	 	            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
	 	            		}
	 	            		
	 	            		System.out.println(breadcrumbPath);
	 	            	//	String srchelmnt = "";
	 	            		List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
	 	            		for(WebElement ele:srchElmnts) {
	 	            			srchelmnt = ele.getText();
	 							breadcrumb.add(srchelmnt);
	 	            			System.out.print("Search element is:"+srchelmnt);
	 	            			
	 	            		}
	 	            		
	 	            		String srch[]=srchelmnt.split(" ");
	 	            		if(driver.getCurrentUrl().contains("products")) {
 	            				Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
 	            				Base.exportData(path,"Output_prod", "Comments", i+1, "Product Page");
 	            			}else
 	            				if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
 	            				Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, "Home");		
 	            				Base.exportData(path,"Output_prod", "Comments", i+1, "Landing Page");
 	            			}
 	            			else 
 	            				if(srchelmnt.equals("")&&breadcrumbPath.length()>=1) {
						       			String text1="";
						       			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
						       			for(WebElement srch1: rslts) {
						           			 text1 = srch1.getText();
						           			System.out.println(text1);
						       			}String result[]=text1.split(" ");
						           				Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
						           				Base.exportData(path,"Output_prod", "Comments", i+1, result[0]);
						           			
 	            					}else {
							       			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
						            		
							       			Base.exportData(path,"Output_prod", "Comments", i+1, srch[0]);
						            		
						            		System.out.println();
				            		}
	            	 }
	 	        			
        	 
         	
		               //  wait.until(ExpectedConditions.elementToBeClickable(videoGames));
		            driver.findElement(By.xpath("//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Electronics']")).click();
		            	 
		                 
		                    
       		
	}
        	

	}
	@Test
	public void clothing_validations() throws IOException {
		
	    WebElement clothing=driver.findElement(By.xpath("//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Clothing']"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(clothing));
        clothing.click();
        						//For Corousal image
		//For Corousal image
        List<WebElement> crousalelements = driver.findElements(By.xpath("//div[@role='tabpanel']//a[@class='btn btn-primary']"));
       /* WebDriverWait wt = new WebDriverWait(driver,50);
        wt.until(ExpectedConditions.visibilityOfAllElements(crousalelements));
       */ System.out.println("The total no of crousals are  : "+crousalelements.size());
       
		ArrayList<String> carouselLinks= new ArrayList<>();

		for(WebElement link:crousalelements) {
			
    	   String text =link.getAttribute("href");
    	   
    	  // setCellData("Site Validation", "Navigation URL", i+1, text);
    	   carouselLinks.add(text);//
		  System.out.println(text);
       }
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
	   System.out.println(carouselLinks);
       
      List<String> breadcrumb=new ArrayList<>();	
          	//System.out.println("Printting all carousel links from list : "+carouselLinks);
        	//For Xbox and PS5:
        	System.out.println("Featured Products : ");
        	if(driver.findElements(By.xpath("//h4[text()='Featured Products']")).size()==1) {
        	//Base.scrollToElementAction( driver.findElements(By.xpath("//h2[text()='Featured Products & Offers']")));
        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//h4[text()='Featured Products']")));
        	List<WebElement> productoffers = driver.findElements(By.xpath("//div[@role='tabpanel']//a[@class='hfhover']"));
        	
        	for(WebElement ele: productoffers) {
        		String xbxlnk = ele.getAttribute("href");
        		carouselLinks.add(xbxlnk);
        		//System.out.println(xbxlnk);
        	}
        	}
        	System.out.println(carouselLinks.size());
        	List<WebElement> caruosals;
        	if(driver.findElements(By.xpath("//div[@class='row key-art-carousel']")).size()==1) {
     			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='row key-art-carousel']")));
     			caruosals = driver.findElements(By.xpath("//div[@class='col-md-4 p-1 slide']//a"));
            
             for(WebElement ele: caruosals) {
                 String gridlnk = ele.getAttribute("href");
                carouselLinks.add(gridlnk);
             }
             System.out.println("After adding the grid values :"+carouselLinks.size());
     		}else {
     			System.out.println("home grids are not identified for ");
     		}	
       		if(driver.findElements(By.xpath("//div[@class='home-grid6']")).size()==1) {
     			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='home-grid6']")));
              caruosals = driver.findElements(By.xpath("//div[@class='home-grid6']//a"));
            
             for(WebElement ele: caruosals) {
                 String gridlnk = ele.getAttribute("href");
                carouselLinks.add(gridlnk);
             }
             System.out.println("After adding the grid values :"+carouselLinks.size());
     		}else {
     			System.out.println("home grids are not identified");
     		}
        	System.out.println("Total links from list:"+carouselLinks.size());
        	siteContent.put("Navigation URLs", carouselLinks);
        	System.out.println(siteContent.get("Navigation URLs"));
        	int rowStarts=Base.getRowCount(path,"Output_prod");
        	//Base.readFileOnce(path,"Input Content");
        	 for(int j=rowStarts;j<=(carouselLinks.size()+rowStarts)-1;j++) {
        		 Base.exportData(path,"Output_prod", "SL NO", j+1, Integer.toString(j));
        		 Base.exportData(path,"Output_prod", "Department", j+1, "Clothing");
        		 Base.exportData(path,"Output_prod", "Department URL", j+1,  "https://sfcc-stg.gamestop.com/Clothing");
        		 Base.exportData(path,"Output_prod", "Navigation URL", j+1, carouselLinks.get(j-rowStarts));
 	        	//System.out.println("Site content updated with navigation url.");
 	        }
        	// inputData.get("Navigation URL").get(0);
        	 //System.out.println(getRowCount("Output Contewnt"));
        	
        	 System.out.println(carouselLinks.size());
        	 for(int i=rowStarts;i<=(carouselLinks.size()+rowStarts)-1;i++) {
        		 
        		 driver.get(carouselLinks.get(i-rowStarts));
        		driver.manage().deleteAllCookies();
        	//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        		//String title = driver.getTitle();
        		//if(title.contains("Search Results") || title.contains("PlayStation 4") ||title.contains("PC") ||title.contains("Video Games") ||title.contains("Switch") ||title.contains("Xbox")) {
        		 String breadcrumbPath = "";
	        	 String srchelmnt = "";
        			List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
        			for(WebElement ele: brdcrmup) {
            			String brdcrmtxt = ele.getText();
						//breadcrumb.add(brdcrmtxt);
            			System.out.print(brdcrmtxt+" >> ");
            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
            			//breadcrumbPath=breadcrumbPath.concat(">>"+brdcrmtxt);
            			
            		}System.out.println(breadcrumbPath);
            		List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
            		for(WebElement ele:srchElmnts) {
            			srchelmnt = ele.getText();
						breadcrumb.add(srchelmnt);
            			System.out.print("Search element is:"+srchelmnt);
            			
            		}
            		
            	
            		System.out.println();
        			String srch[]=srchelmnt.split(" ");
        			if(driver.getCurrentUrl().contains("products")) {
        			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
                    Base.exportData(path,"Output_prod", "Comments", i+1, "Product Page");
    		}
    		
    		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
    			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, "Home");		
    			Base.exportData(path,"Output_prod", "Comments", i+1, "Landing Page");
    		}
    		else if(srchelmnt.equals("")) {
    			String text1="";
    			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
    			for(WebElement srch1: rslts) {
        			 text1 = srch1.getText();
        			System.out.println(text1);
        			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
        			Base.exportData(path,"Output_prod", "Comments", i+1, text1);
    		}
    		}else {
            		Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
            		
            		Base.exportData(path,"Output_prod", "Comments", i+1, srch[0]);
            		
            		System.out.println();
    		}
		}	
                			
                			

	}
	@Test
	public void deals_validation() throws IOException {
		
		test = extent.createTest("Deals", "Validating Deals");

        driver.findElement(By.xpath("//a[@data-name='Deals']")).click();;
	       System.out.println(Base.getRowCount(path,"Output_prod"));
	       int rowStart=Base.getRowCount(path,"Output_prod");
	      
        List<WebElement> crousalelements = driver.findElements(By.xpath("//div[contains(@id,'slick-slide')]//div//a"));
       
        System.out.println("The total no of crousals are "+crousalelements.size());
		ArrayList<String> carouselLinks= new ArrayList<>();
		for(WebElement link:crousalelements) {
			String href=link.getAttribute("href");
				carouselLinks.add(href);
				System.out.println(href);
		}
		
		 JavascriptExecutor js = ((JavascriptExecutor)driver);
 	  		List<WebElement> caruosals;
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
	        		 exportData(path,"Output_prod", "SL NO", j+1, Integer.toString(j));
	        		 exportData(path,"Output_prod", "Department", j+1, "Deals Page");
	        		 exportData(path,"Output_prod", "Department URL", j+1, "https://sfcc-stg.gamestop.com/Deals");
	        		 exportData(path,"Output_prod", "Navigation URL", j+1, carouselLinks.get(j-rowStart));
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
								//breadcrumb.add(brdcrmtxt);
		            			System.out.print(brdcrmtxt+" >> ");
		            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
		            			//breadcrumbPath=breadcrumbPath.concat(">>"+brdcrmtxt);
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
		            			exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
                                    exportData(path,"Output_prod", "Comments", i+1, "Product Page");
		            		}
		            		
		            		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
		            			exportData(path,"Output_prod", "Breadcrumb Path", i+1, "Home");		
	                			exportData(path,"Output_prod", "Comments", i+1, "Landing Page");
		            		}
		            		else if(srchelmnt.equals("")) {
		            			String text1="";
	                			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
	                			for(WebElement srch1: rslts) {
	                    			 text1 = srch1.getText();
	                    			System.out.println(text1);
			            			exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
	                    			exportData(path,"Output_prod", "Comments", i+1, text1);
		            		}
		            		}else {
				            		exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
				            		
				            		exportData(path,"Output_prod", "Comments", i+1, srch[0]);
				            		
				            		System.out.println();
		            		}
}
	}

}
