package com.gamestop.tests;

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

public class GameStop_Mobile_Prod extends Base {
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
		this.driver=Base.initialiazinDriverForMobile();
		hs.getURL();
		
	}
	
	@Test
	public void homepage_mob_Validation() {
	    
    	//For Corousal image
        List<WebElement> crousalelements = driver.findElements(By.xpath("//div[@class='home-hero1 hero-theme-light slick-slide slick-cloned']//a"));
       /* WebDriverWait wt = new WebDriverWait(driver,50);
        wt.until(ExpectedConditions.visibilityOfAllElements(crousalelements));
       */ System.out.println("The total no of crousals are  : "+crousalelements.size());
       
		ArrayList<String> carouselLinks= new ArrayList<>();

		for(WebElement link:crousalelements) {
			
    	   String text =link.getAttribute("href");
    	   
    	  // setCellData("Site Validation", "Navigation URL", i+1, text);
    	   carouselLinks.add(text);//
		 // System.out.println(text);
       }
		System.out.println("Printting all carousel links : "+carouselLinks);
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
	  // System.out.println(carouselLinks);
	   
       List<String> breadcrumb=new ArrayList<>();
          	//For Feature Products & Offers:
        	System.out.println("Featured Products & Offers starts : ");
        	//Base.scrollToElementAction( driver.findElements(By.xpath("//h2[text()='Featured Products & Offers']")));
        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//h2[text()='Featured Products & Offers']")));
        	List<WebElement> productoffers = driver.findElements(By.xpath("//h2[text()='Featured Products & Offers']/parent::div/following-sibling::div//a"));
        	System.out.println(productoffers.size());
        	for(WebElement ele: productoffers) {
        		String prodLink = ele.getAttribute("href");
        		carouselLinks.add(prodLink);
        	System.out.println(prodLink);
        	}
        	System.out.println(carouselLinks.size());
        	  System.out.println("To selling games Starts :");
   	       js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[text()='Top Selling Games']")));
           	List<WebElement> topGames = driver.findElements(By.xpath("//span[text()='Top Selling Games']/ancestor::div/following-sibling::div[@data-mobile-view-type='mobileCarousel-2up']//a[@role='presentation']"));
           	System.out.println(topGames.size());
       
   	      
   	       // WebElement topSell = driver.findElement(By.xpath("//span[text()='Top Selling Games']"));
   	        WebDriverWait wt1 = new WebDriverWait(driver,30);
   	        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Top Selling Games']"))));
   	        //JavascriptExecutor js = ((JavascriptExecutor)driver);
   	        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[text()='Top Selling Games']")));
   	       // String text = prdcts.getText();
   	       // carouselLinks.add(text);
   	       // System.out.println(text)
   	            	for(WebElement game:topGames) {
   	            		String ftrdlnks = game.getAttribute("href");
   	            		carouselLinks.add(ftrdlnks);
   	            		
   	            		
   	            		//System.out.println(ftrdlnks);
   	            	}
   	
   	            	System.out.println("More Product & Features Starts:");
					
			        wt1.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//h2[contains(text(),'More Products & Offers')]"))));
		        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//h2[contains(text(),'More Products & Offers')]")));
		            	List<WebElement>element1 = driver.findElements(By.xpath("//h2[contains(text(),'More Products & Offers')]/parent::div//following-sibling::div//a"));
		            	System.out.println("More Products & Offers Links are: "+element1.size());
		            	for(WebElement ele:element1) {
		            		String href = ele.getAttribute("href");
		            		carouselLinks.add(href);
		            	//	System.out.println(href);
		            	}
		            	System.out.println("After More Product & Features :"+carouselLinks.size());	
		        
	   	
        	System.out.println("Recommended Products starts :");
        	WebDriverWait wt = new WebDriverWait(driver,50);
	        wt.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//h3[contains(text(),'Recommended Products')]"))));
        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//h3[contains(text(),'Recommended Products')]")));
            	List<WebElement>recommanded = driver.findElements(By.xpath("//div[@id='home1_rr']//div[@class='image-container']//a"));
            	System.out.println("Recommended Products starts are "+recommanded.size());
            	for(WebElement ele:recommanded) {
            		String href = ele.getAttribute("href");
            		carouselLinks.add(href);
            	//	System.out.println(href);
            	}
        	//Top Selling Games
        	System.out.println("Top Selling collectabiles starts here :");
        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[text()='Top Selling Collectibles']")));
        	List<WebElement> games = driver.findElements(By.xpath("//span[text()='Top Selling Games']/ancestor::div/following-sibling::div[@data-mobile-view-type='mobileCarousel-2up']//div[@class='image-container']//a"));
        	
        	for(WebElement ele: games) {
        		String prodLink = ele.getAttribute("href");
        		carouselLinks.add(prodLink);
        	System.out.println(prodLink);
        	}
        	
        	System.out.println(carouselLinks.size());
        	System.out.println("Video Games Coming Soon starts :");
        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//h2[text()='Video Games Coming Soon']")));
        	List<WebElement> videogames = driver.findElements(By.xpath("//h2[text()='Video Games Coming Soon']/parent::div/following-sibling::div//a"));
        	System.out.println("No. of Video Games Coming Soon are "+videogames.size());
        	for(WebElement ele:videogames) {
        		String href = ele.getAttribute("href");
        		carouselLinks.add(href);
        	//	System.out.println(href);
        	}
    	
        	System.out.println(carouselLinks.size());
        	
            	
            System.out.println("Total links from list:"+carouselLinks.size());
        	siteContent.put("Navigation URLs", carouselLinks);
        	System.out.println(siteContent.get("Navigation URLs"));
        	
        	//Base.readFileOnce(path,"Input Content");
        	 for(int j=0;j<=carouselLinks.size()-1;j++) {
        		 Base.exportData(path,"Output_mob_prod", "SL NO", j+2, Integer.toString(j+1));
        		 Base.exportData(path,"Output_mob_prod", "Department", j+2, "HOME PAGE");
        		 Base.exportData(path,"Output_mob_prod", "Department URL", j+2, inputData.get("Department URL").get(1));
        		 Base.exportData(path,"Output_mob_prod", "Navigation URL", j+2, carouselLinks.get(j));
 	        	//System.out.println("Site content updated with navigation url.");
 	        }
        	// inputData.get("Navigation URL").get(0);
        	 //System.out.println(getRowCount("Output Contewnt"));
        	 System.out.println(carouselLinks.size());
        	 for(int i=0;i<=carouselLinks.size()-1;i++) {
        		 if("".equals(carouselLinks.get(i))) {
        			 System.out.println("link is not identified for Image");
        		 }else {
        		 driver.get(carouselLinks.get(i));
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
						breadcrumb.add(srchelmnt);
            			System.out.print("Search element is:"+srchelmnt);
            			
            		}
            		String srch[]=srchelmnt.split(" ");
            		
            		if(driver.getCurrentUrl().contains("products")) {
        				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+2, breadcrumbPath);
                        Base.exportData(path,"Output_mob_prod", "Comments", i+2, "Product Page");
        		}
            		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
            			Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+2, "Home");		
            			Base.exportData(path,"Output_mob_prod", "Comments", i+2, "Landing Page");
            		}
            		else if(srchelmnt.equals("")) {
            			String text1="";
            			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
            			for(WebElement srch1: rslts) {
                			 text1 = srch1.getText();
                			System.out.println(text1);
            			}
                			if(text1.equals("")) {
                				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+2, breadcrumbPath);		
                    			Base.exportData(path,"Output_mob_prod", "Comments", i+2, "StorePage");
                			}else {
		            			Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+2, breadcrumbPath);		
	                			Base.exportData(path,"Output_mob_prod", "Comments", i+2, text1);
                			}
            		}
            		
            		else {
		            		Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+2, breadcrumbPath);
		            		
		            		Base.exportData(path,"Output_mob_prod", "Comments", i+2, srch[0]);
		            		
		            		System.out.println();
            		}/*List<WebElement> contents=driver.findElements(By.xpath("//h3/following-sibling::p"));
                		String content="";
                		if(contents.size()>=1) {
                			content=
                		}*/
                	/*	Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+2, "Home");
                		Base.exportData(path,"Output_mob_prod", "Comments", i+2, "Landing Page");*/
	            		
                		//
                		String text1="";
                			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
                			for(WebElement srch1: rslts) {
                    			 text1 = srch1.getText();
                    			System.out.println(text1);
                    			String values[]=text1.split(" ");
                    			Base.exportData(path,"Output_mob_prod", "Comments", i+2, values[0]);
                    			
        		}   		
        		}	
                			
                			
	}

	

	}
	@Test
	public void videoGames_validation() throws Exception {

		 WebElement toggle=driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']"));
	        WebDriverWait wait = new WebDriverWait(driver,30);
	        wait.until(ExpectedConditions.elementToBeClickable(toggle));
	        toggle.click();
	        
	        WebElement videoGames=driver.findElement(By.xpath("//a[@data-name='Video Games']"));
	        WebDriverWait wait1 = new WebDriverWait(driver, 10);
	        wait1.until(ExpectedConditions.elementToBeClickable(videoGames));
	        videoGames.click();
	        
	        /*WebElement viewMore=driver.findElement(By.xpath("//div[@class='view-more-option']/span[contains(text(),'View More')]"));
	        viewMore.click();*/
	        
	       List<WebElement> alllinks=driver.findElements(By.xpath("(//a[@data-name='Video Games'])[2]//parent::div/following-sibling::div//a[@data-level='2']"));
	       System.out.println(alllinks.size());
	      // System.out.println(alllinks);
    
	       JavascriptExecutor js = ((JavascriptExecutor)driver);
	       
	       for(int j=8;j<=alllinks.size()-1;j++) {
	     	String linkText=driver.findElement(By.xpath("(//span[text()='Video Games']/ancestor::div[@class='dropdown-item top-category']/following-sibling::div//ul[@class='menu-list level-2']//span[@class='category-name '])"+"["+(j+1)+"]")).getText();
       	//=alllinks.get(j).getText();
	       System.out.println(linkText);
	     //  alllinks.get(j).click();
	       WebElement link=driver.findElement(By.xpath("(//a[@data-name='Video Games']//following-sibling::div//ul[@class='menu-list level-2']/li/a)"+"["+(j+1)+"]"));
	       
	       String attributeValue=link.getAttribute("href");
	       link.click();
	            
	     // driver.get(alllinks.get(j).getAttribute("href"));
	       List<String> breadcrumb=new ArrayList<>();
	       System.out.println(Base.getRowCount(path,"Output_mob_prod"));
      		int rowStart=Base.getRowCount(path,"Output_mob_prod");
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
   			List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
        		for(WebElement ele:srchElmnts) {
        			srchelmnt = ele.getText();
						breadcrumb.add(srchelmnt);
        			System.out.print("Search element is:"+srchelmnt);
        			
        		}
        		String srch[]=srchelmnt.split(" ");
        		 Base.exportData(path,"Output_mob_prod", "SL NO", rowStart+1, Integer.toString(rowStart));
       		 Base.exportData(path,"Output_mob_prod", "Department", rowStart+1, "Video Games"+"//"+linkText);
       		 Base.exportData(path,"Output_mob_prod", "Department URL", rowStart+1, "https://gamestop.com/video-games/"+linkText);
       		 Base.exportData(path,"Output_mob_prod", "Navigation URL", rowStart+1, attributeValue); 	        	
        		Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", rowStart+1, breadcrumbPath);         		
        	    Base.exportData(path,"Output_mob_prod", "Comments", rowStart+1, srch[0]);
        		
	       }
       	
	       ArrayList<String> carouselLinks= new ArrayList<>();
	       if(driver.findElements(By.xpath("//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]")).size()>=1) {
	        	 List<WebElement> crousalelements = driver.findElements(By.xpath("//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]"));
	 	        System.out.println("The total no of crousals are "+crousalelements.size());
	 			
	 	        for(int i=1;i<=crousalelements.size();i++) {
	 	    	   String text = driver.findElement(By.xpath("(//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')])["+i+"]")).getAttribute("href");
	 	    	   
	 	    	  // setCellData("Site Validation", "Navigation URL", i+1, text);
	 	    	   carouselLinks.add(text);//
	 			  System.out.println(text);
	 	        }
		       
		   System.out.println(carouselLinks.size());
	       }else {
	    	   String breadcrumbPath = "";
   			List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
   			
   			if(brdcrmup.size()>=1) {
       		for(WebElement ele: brdcrmup) {
       			String brdcrmtxt = ele.getText();
					//breadcrumb.add(brdcrmtxt);
       			System.out.print(brdcrmtxt+" >> ");
       			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
       			//breadcrumbPath=breadcrumbPath.concat(">>"+brdcrmtxt);
       			
       		}
       		String text1="";
	       			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
	       			for(WebElement srch1: rslts) {
	           			 text1 = srch1.getText();
	           			System.out.println(text1);
	       			}
   			}
       		System.out.println(breadcrumbPath);
       		
	       }
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  
		  //For Featured Products and offers
	       // WebElement prdcts = driver.findElement(By.xpath("//div[@class='container']/h4"));
		 String text="";
		List<WebElement> caruosals;
		if(driver.findElements(By.xpath("//h3[contains(text(),'Learn from the Best')]")).size()==1){
	    	   WebDriverWait wt = new WebDriverWait(driver,50);
		        wt.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//h3[text()='Learn from the Best']"))));
	        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//h3[text()='Learn from the Best']")));
	            
	        	caruosals = driver.findElements(By.xpath("//h3[text()='Learn from the Best']/following-sibling::div//div[contains(@class,'col-md-4')]//a[@class='esportstilea']"));
	    	     System.out.println("The total no of crousals are "+caruosals.size());
	 			
	 	        for(int i=1;i<=caruosals.size();i++) {
	 	    	    text = driver.findElement(By.xpath("(//h3[text()='Learn from the Best']/following-sibling::div//div[contains(@class,'col-md-4')]//a[@class='esportstilea'])["+i+"]")).getAttribute("href");
	 	    	   
	 	    	  // setCellData("Site Validation", "Navigation URL", i+1, text);
	 	    	   carouselLinks.add(text);//
	 			  System.out.println(text);
	 	        }
	       }else {
	    	   System.out.println("Learn from the Best not identified");
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
		if(driver.findElements(By.xpath("//h4[contains(text(),'Feature')]")).size()==1) {
	        WebDriverWait wt1 = new WebDriverWait(driver,30);
	        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[contains(text(),'Feature')]"))));
	       // JavascriptExecutor js = ((JavascriptExecutor)driver);
	        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h4[contains(text(),'Feature')]")));
	        
	        caruosals = driver.findElements(By.xpath("//h4[contains(text(),'Feature')]/following-sibling::div//a"));
	        	  for(WebElement wel:caruosals) {
	        		  String href=wel.getAttribute("href");
	        		  carouselLinks.add(href);
	        		  System.out.println(href);
	        	  }
			       //System.out.println(text);
	        	}else if(driver.findElements(By.xpath("//h2[contains(text(),'Feature')]")).size()==1) {
	        		 WebDriverWait wt1 = new WebDriverWait(driver,30);
	     	        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Feature')]"))));
	     	       // JavascriptExecutor js = ((JavascriptExecutor)driver);
	     	        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[contains(text(),'Feature')]")));
	     	        
	     	      caruosals = driver.findElements(By.xpath("//h2[contains(text(),'Feature')]/following-sibling::div//a"));
	     	    
	        	  for(WebElement wel:caruosals) {
	        		  String href=wel.getAttribute("href");
	        		  carouselLinks.add(href);
	        		  System.out.println(href);
	       
	        	   }
	        	  }else if(driver.findElements(By.xpath("//h4[contains(text(),'Featured Games')]")).size()==1) {
	        		 WebDriverWait wt1 = new WebDriverWait(driver,30);
		     	        wt1.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[contains(text(),'Featured Games')]"))));
		     	       // JavascriptExecutor js = ((JavascriptExecutor)driver);
		     	        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h4[contains(text(),'Featured Games')]")));
			     	      // carouselLinks.add(text);
		     	        List<WebElement>     hrefs = driver.findElements(By.xpath("//h4[contains(text(),'Featured Games')]/following-sibling::div//a"));
		     	        for(WebElement ele:hrefs) {
		     	        	text=ele.getAttribute("href");
		     	        	carouselLinks.add(text);
					       System.out.println(text);
		     	        }
		     	        
		        	}
	     	    else {
	        		System.out.println("Product features are not identified for this ....");
	        	}
	        	System.out.println(carouselLinks.size());
	       //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	        	if(driver.findElements(By.xpath("//div[@class='console-grid3']//a")).size()==1) {
	        	//Recommended products 
	        	System.out.println("Grids  starts :");
	        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[@class='console-grid3']")));
	            	List<WebElement>element1 = driver.findElements(By.xpath("//div[@class='console-grid3']//a"));
	            	System.out.println("grids added to carousals are "+element1.size());
	            	for(WebElement ele:element1) {
	            		String href = ele.getAttribute("href");
	            		carouselLinks.add(href);
	            	
	            	}
	        	}else {
	        		System.out.println("grids  are not identified............");
	        	}
	        	
	            	System.out.println("Total links from list:"+carouselLinks.size());
	          //  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	            
	            	
	            	 for(int k=rowStart;k<=(carouselLinks.size()+rowStart)-1;k++) {
		        		 Base.exportData(path,"Output_mob_prod", "SL NO", k+1, Integer.toString(k));
		        		 Base.exportData(path,"Output_mob_prod", "Department", k+1, "Video Games"+"//"+linkText);
		        		 Base.exportData(path,"Output_mob_prod", "Department URL", k+1, "https://gamestop.com/video-games/"+linkText);
		        		 Base.exportData(path,"Output_mob_prod", "Navigation URL", k+1, carouselLinks.get(k-rowStart));
		 	        	//System.out.println("Site content updated with navigation url.");
		 	        }
	            	 //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	       
	            	 //get the links from list and find the breadcrumb path and results
	       	 
	            	 for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
	 	        		driver.get(carouselLinks.get(i-rowStart));
	 	        		driver.manage().deleteAllCookies();
	 	        	
	 	        		 String breadcrumbPath = "";
	 	        			List<WebElement> brdcrmup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	 	        			
	 	        			if(brdcrmup.size()>=1) {
	 	            		for(WebElement ele: brdcrmup) {
	 	            			String brdcrmtxt = ele.getText();
	 							//breadcrumb.add(brdcrmtxt);
	 	            			System.out.print(brdcrmtxt+" >> ");
	 	            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
	 	            			//breadcrumbPath=breadcrumbPath.concat(">>"+brdcrmtxt);
	 	            			
	 	            		}
	 	        			}
	 	            		System.out.println(breadcrumbPath);
	 	            		String srchelmnt = "";
	 	            		List<WebElement> srchElmnts =driver.findElements(By.xpath("//span[@class='pageResults']"));
	 	            		for(WebElement ele:srchElmnts) {
	 	            			srchelmnt = ele.getText();
	 							breadcrumb.add(srchelmnt);
	 	            			System.out.print("Search element is:"+srchelmnt);
	 	            			
	 	            		}
	 	        			
	 	            		String srch[]=srchelmnt.split(" ");
	 	            		if(driver.getCurrentUrl().contains("products")) {
	 	            				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
	 	            				Base.exportData(path,"Output_mob_prod", "Comments", i+1, "Product Page");
	 	            			}else
	 	            				if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
	 	            				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, "Home");		
	 	            				Base.exportData(path,"Output_mob_prod", "Comments", i+1, "Landing Page");
	 	            			}
	 	            			else 
	 	            				if(srchelmnt.equals("")) {
							       			String text1="";
							       			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
							       			for(WebElement srch1: rslts) {
							           			 text1 = srch1.getText();
							           			System.out.println(text1);
							       			}String result[]=text1.split(" ");
							           				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
							           				Base.exportData(path,"Output_mob_prod", "Comments", i+1, result[0]);
							           			
	 	            					}else {
								       			Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
							            		
								       			Base.exportData(path,"Output_mob_prod", "Comments", i+1, srch[0]);
							            		
							            		System.out.println();
					            		}
	 	            		}
				
			
	            	 driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']")).click();;
	                 
	                 
	                 driver.findElement(By.xpath("//a[@data-name='Video Games']")).click();
	                 Base.wait10Seconds();
	            	 }
	}
	@Test
	public void accessories_validation() throws Exception {

		WebElement toggle=driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']"));
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(toggle));
        toggle.click();
        driver.findElement(By.xpath("(//a[@data-name='Video Games']/parent::li/following-sibling::li//a[@data-name='Accessories'])[1]")).click();
        
        List<WebElement> allAccessories=driver.findElements(By.xpath("//a[@data-name='Accessories']/parent::div[@class='dropdown-item top-category']/following-sibling::div//ul[@class='menu-list level-2']//a"));
        System.out.println(allAccessories.size());
       // System.out.println(allAccessories);
        for(int j=0;j<=allAccessories.size()-1;j++) {
       
     	   String linkText=driver.findElement(By.xpath("(//a[@data-name='Accessories']/parent::div[@class='dropdown-item top-category']/following-sibling::div//ul[@class='menu-list level-2']//a//span[@class='category-name '])"+"["+(j+1)+"]")).getText();
        	
 	       System.out.println(linkText);
 	    
 	       WebElement link=driver.findElement(By.xpath("(//a[@data-name='Accessories']/parent::div[@class='dropdown-item top-category']/following-sibling::div//ul[@class='menu-list level-2']//a)"+"["+(j+1)+"]"));
 	       String attributeValue=link.getAttribute("href");
 	       link.click();
 	       List<String> breadcrumb=new ArrayList<>();
 	       System.out.println(Base.getRowCount(path,"Output_mob_prod"));
        		int rowStart=Base.getRowCount(path,"Output_mob_prod");
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
          		 Base.exportData(path,"Output_mob_stag", "SL NO", rowStart+1, Integer.toString(rowStart));
         		 Base.exportData(path,"Output_mob_prod", "Department", rowStart+1, "Accessories"+"//"+linkText);
         		 Base.exportData(path,"Output_mob_prod", "Department URL", rowStart+1, "https://gamestop.com/Accessories/"+linkText);
         		 Base.exportData(path,"Output_mob_prod", "Navigation URL", rowStart+1, attributeValue); 	        	
          		Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", rowStart+1, breadcrumbPath);         		
          	    Base.exportData(path,"Output_mob_prod", "Comments", rowStart+1, srch[0]);
          		
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
 		        		 Base.exportData(path,"Output_mob_prod", "SL NO", k+1, Integer.toString(k));
 		        		 Base.exportData(path,"Output_mob_prod", "Department", k+1, "Accessories"+"//"+linkText);
 		        		 Base.exportData(path,"Output_mob_prod", "Department URL", k+1, "https://gamestop.com/Accessories/"+linkText);
 		        		 Base.exportData(path,"Output_mob_prod", "Navigation URL", k+1, carouselLinks.get(k-rowStart));
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
  	            				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
  	            				Base.exportData(path,"Output_mob_prod", "Comments", i+1, "Product Page");
  	            			}else
  	            				if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
  	            				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, "Home");		
  	            				Base.exportData(path,"Output_mob_prod", "Comments", i+1, "Landing Page");
  	            			}
  	            			else 
  	            				if(srchelmnt.equals("")) {
 						       			String text1="";
 						       			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
 						       			for(WebElement srch1: rslts) {
 						           			 text1 = srch1.getText();
 						           			System.out.println(text1);
 						       			}String result[]=text1.split(" ");
 						           				Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
 						           				Base.exportData(path,"Output_mob_prod", "Comments", i+1, result[0]);
 						           			
  	            					}else {
 							       			Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
 						            		
 							       			Base.exportData(path,"Output_mob_prod", "Comments", i+1, srch[0]);
 						            		
 						            		System.out.println();
 				            		}
  	            		
 				 	        		}
 	            	 }
        
		               //  wait.until(ExpectedConditions.elementToBeClickable(videoGames));
        	 driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']")).click();
           
             
             driver.findElement(By.xpath("(//a[@data-name='Video Games']/parent::li/following-sibling::li//a[@data-name='Accessories'])[1]")).click();
            
		                 Base.wait10Seconds();	
		                 }	
	}
	@Test
	public void toysAndCollectibiles() throws Exception {

		WebElement toggle=driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']"));
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(toggle));
        toggle.click();
        
        WebElement toysAndCollec=driver.findElement(By.xpath("(//a[@data-name='Video Games']/parent::li/following-sibling::li//a[@data-name='Toys & Collectibles'])[1]"));
      
        toysAndCollec.click();
         List<WebElement> alltoys=driver.findElements(By.xpath("//a[@data-name='Toys & Collectibles']/parent::div[@class='dropdown-item top-category']/following-sibling::div//ul[@class='menu-list level-2']//a"));
       System.out.println(alltoys.size());
       
       for(int j=0;j<=alltoys.size()-1;j++) {
      
    	   String linkText=driver.findElement(By.xpath("(//a[@data-name='Toys & Collectibles']/parent::div[@class='dropdown-item top-category']/following-sibling::div//ul[@class='menu-list level-2']//a//span[@class='category-name '])"+"["+(j+1)+"]")).getText();
       	
	       System.out.println(linkText);
	    
	       WebElement link=driver.findElement(By.xpath("(//a[@data-name='Toys & Collectibles']/parent::div[@class='dropdown-item top-category']/following-sibling::div//ul[@class='menu-list level-2']//a)"+"["+(j+1)+"]"));
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
         			System.out.print(brdcrmtxt+" >> ");
         			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
         		}
    			System.out.println(breadcrumbPath);
    			if(driver.findElements(By.xpath("(//div[@class='col']//span)[1]")).size()>=1) {
    				
    			
    			List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
         		for(WebElement ele:srchElmnts) {
         			srchelmnt = ele.getText();
						//breadcrumb.add(srchelmnt);
         			System.out.print("Search element is:"+srchelmnt);
         			
         		}
         		}else {
         			//String text1="";
        			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
        			for(WebElement srch1: rslts) {
        				srchelmnt = srch1.getText();
            			System.out.println(srchelmnt);
            			//System.out.println(text1);
         		}
         		}
         		String srch[]=srchelmnt.split(" ");
         		 Base.exportData(path,"Output_prod", "SL NO", rowStart+1, Integer.toString(rowStart));
        		 Base.exportData(path,"Output_prod", "Department", rowStart+1, "Toys & Collectabiles"+"//"+linkText);
        		 Base.exportData(path,"Output_prod", "Department URL", rowStart+1, "https://sfcc-stg.gamestop.com/Accessories/"+linkText);
        		 Base.exportData(path,"Output_prod", "Navigation URL", rowStart+1, attributeValue); 	        	
         		 Base.exportData(path,"Output_prod", "Breadcrumb Path", rowStart+1, breadcrumbPath);         		
         	     Base.exportData(path,"Output_prod", "Comments", rowStart+1, srch[0]);
         		
	       }
	       List<WebElement> crousalelements  ;
      	 if(driver.findElements(By.xpath("//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]")).size()>=1) {
      		crousalelements=driver.findElements(By.xpath("//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]"));
		 	        System.out.println("The total no of crousals are "+crousalelements.size());
		 			ArrayList<String> carouselLinks= new ArrayList<>();
		 			
		 	        for(int i=1;i<=crousalelements.size();i++) {
			 	        	String text = driver.findElement(By.xpath("(//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')])["+i+"]")).getAttribute("href");
	 	    	       	  // setCellData("Site Validation", "Navigation URL", i+1, text);
				 	    	   carouselLinks.add(text);//
				 			  System.out.println(text);
			 	       }
	       
		   System.out.println(carouselLinks.size());
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
 	 		}else {
 	 			System.out.println("home grids are not identified");
 	 		}
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
 	  		if(driver.findElements(By.xpath("//h4[text()='Shop By Category']/parent::div")).size()==1) {
 	  			
 	  			caruosals = driver.findElements(By.xpath("//h4[text()='Shop By Category']/parent::div//a"));
 	 	        
 	 	         for(WebElement ele: caruosals) {
 	 	             String gridlnk = ele.getAttribute("href");
 	 	            carouselLinks.add(gridlnk);
 	 	         }
 	 	         System.out.println("After adding category grid values :"+carouselLinks.size());
 	 	 		}else {
 	 	 			System.out.println("Category grids are not identified");
 	 	 		}	
 	 	            	System.out.println("Total links from list:"+carouselLinks.size());
	            	//System.out.println(Base.getRowCount("Output_prod"));
	            	//int rowStart=Base.getRowCount("Output_prod");
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
	 	            			System.out.print(brdcrmtxt+" >> ");
	 	            			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
	 	            			//breadcrumbPath=breadcrumbPath.concat(">>"+brdcrmtxt);
	 	            		}
	 	        			
	 	        			System.out.println("Breadcrumb path :"+breadcrumbPath);
	 	        			if(driver.findElements(By.xpath("(//div[@class='col']//span)[1]")).size()==1){
	 	            		List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
	 	            		for(WebElement ele:srchElmnts) {
	 	            			srchelmnt = ele.getText();
	 							breadcrumb.add(srchelmnt);
	 	            			System.out.println("Search element is:"+srchelmnt);
	 	            			
	 	            		}
	 	            		}
	 	            		String srch[]=srchelmnt.split(" ");
	 	            		if(driver.getCurrentUrl().contains("products")) {
			            			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
			            			Base. exportData(path,"Output_prod", "Comments", i+1, "Product Page");
			        				System.out.println("Product Page");
	 	            		}else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
	        					Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, "Home");		
	        					Base.exportData(path,"Output_prod", "Comments", i+1, "Landing Page");
	        					System.out.println("Landing Page");
	 	            		}
	 	            			else if(srchelmnt.equals("")) {
			        			String text1="";
			        			List<WebElement> rslts1= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
			        			for(WebElement srch1: rslts1) {
			            			 text1 = srch1.getText();
			            			System.out.println(text1);
			            			//System.out.println(text1);
					        				}	String value[]=text1.split(" ");
			        			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
		            			Base.exportData(path,"Output_prod", "Comments", i+1, value[0]);
            			
			        		}else {
			        			Base.exportData(path,"Output_prod", "Breadcrumb Path", i+1, breadcrumbPath);
				            		
			        		    Base.exportData(path,"Output_prod", "Comments", i+1, srch[0]);
				            		
				            		System.out.println(srch[0]);
			        		}	
			        			
			                			
			 	            		
        	 }
        	 }
      	driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']")).click();
      	driver.findElement(By.xpath("(//a[@data-name='Video Games']/parent::li/following-sibling::li//a[@data-name='Toys & Collectibles'])[1]")).click();;
    
       
          Base.wait10Seconds();	
        	 }
        	 


	}
	@Test
	public void clothing_validation() throws Exception {
		WebElement toggle=driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']"));
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(toggle));
        toggle.click();
        
        WebElement clothings=driver.findElement(By.xpath("(//a[@data-name='Video Games']/parent::li/following-sibling::li//a[@data-name='Clothing'])[1]"));
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        wait1.until(ExpectedConditions.elementToBeClickable(clothings));
        clothings.click();
       
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
          	System.out.println("Printting all carousel links from list : "+carouselLinks);
        	//For Xbox and PS5:
        	System.out.println("Featured Products : ");
        	//Base.scrollToElementAction( driver.findElements(By.xpath("//h2[text()='Featured Products & Offers']")));
        	js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//h4[text()='Featured Products']")));
        	List<WebElement> productoffers = driver.findElements(By.xpath("//div[@role='tabpanel']//a[@class='hfhover']"));
        	
        	for(WebElement ele: productoffers) {
        		String xbxlnk = ele.getAttribute("href");
        		carouselLinks.add(xbxlnk);
        		//System.out.println(xbxlnk);
        	}
        	System.out.println(carouselLinks.size());
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
        	System.out.println("Total links from list:"+carouselLinks.size());
        	siteContent.put("Navigation URLs", carouselLinks);
        	System.out.println(siteContent.get("Navigation URLs"));
        	int rowStarts=Base.getRowCount(path,"Output_mob_prod");
        	//Base.readFileOnce(path,"Input Content");
        	 for(int j=rowStarts;j<=(carouselLinks.size()+rowStarts)-1;j++) {
        		 Base.exportData(path,"Output_mob_prod", "SL NO", j+1, Integer.toString(j));
        		 Base.exportData(path,"Output_mob_prod", "Department", j+1, "Clothing");
        		 Base.exportData(path,"Output_mob_prod", "Department URL", j+1,  "https://gamestop.com/Clothing");
        		 Base.exportData(path,"Output_mob_prod", "Navigation URL", j+1, carouselLinks.get(j-rowStarts));
 	        	//System.out.println("Site content updated with navigation url.");
 	        }
        	// inputData.get("Navigation URL").get(0);
        	 //System.out.println(getRowCount("Output Contewnt"));
        	
        	 System.out.println(carouselLinks.size());
        	 for(int i=rowStarts;i<=(carouselLinks.size()+rowStarts)-1;i++) {
        		 
        		 driver.get(carouselLinks.get(i-rowStarts));
        		driver.manage().deleteAllCookies();
        	
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
        			Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
                    Base.exportData(path,"Output_mob_prod", "Comments", i+1, "Product Page");
    		}
    		
    		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
    			Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, "Home");		
    			Base.exportData(path,"Output_mob_prod", "Comments", i+1, "Landing Page");
    		}
    		else if(srchelmnt.equals("")) {
    			String text1="";
    			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
    			for(WebElement srch1: rslts) {
        			 text1 = srch1.getText();
        			System.out.println(text1);
        			Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
        			Base.exportData(path,"Output_mob_prod", "Comments", i+1, text1);
    		}
    		}else {
            		Base.exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
            		
            		Base.exportData(path,"Output_mob_prod", "Comments", i+1, srch[0]);
            		
            		System.out.println();
    		}
		}	

	}
	@Test
	public void deals_validation() throws Exception {

		WebElement toggle=driver.findElement(By.xpath("//div[@class='header-mobile-menu col-3']/button[@aria-label='Toggle navigation']"));
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(toggle));
        toggle.click();
        
        WebElement deals=driver.findElement(By.xpath("(//a[@data-name='Video Games']/parent::li/following-sibling::li//a[@data-name='Deals'])[1]"));
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        wait1.until(ExpectedConditions.elementToBeClickable(deals));
        deals.click();
       
	       System.out.println(Base.getRowCount(path,"Output_mob_prod"));
	       int rowStart=Base.getRowCount(path,"Output_mob_prod");
	      
        List<WebElement> crousalelements = driver.findElements(By.xpath("//div[contains(@id,'slick-slide')]//div//a"));
       
        System.out.println("The total no of crousals are "+crousalelements.size());
		ArrayList<String> carouselLinks= new ArrayList<>();
		for(WebElement link:crousalelements) {
			String href=link.getAttribute("href");
		carouselLinks.add(href);
		System.out.println(href);
		}
		List<WebElement> caruosals;
		JavascriptExecutor js=(JavascriptExecutor)driver;
        if(driver.findElements(By.xpath("//div[@class='container mt-5 p-0']")).size()==1) {
        	js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='container mt-5 p-0']")));
 	          caruosals = driver.findElements(By.xpath("//div[@class='container mt-5 p-0']//a"));
 	        
 	          
 	         for(WebElement ele: caruosals) {
 	             String gridlnk = ele.getAttribute("href");
 	            carouselLinks.add(gridlnk);
 	         }
 	         System.out.println("After deal of the day :"+carouselLinks.size());
 	 		}else {
 	 			System.out.println("deals of the day not identified");
 	 		}
        		
		
		System.out.println("Total links from list:"+carouselLinks.size());
	        	//Base.readFileOnce(path,"Input Content");
	        	 for(int j=rowStart;j<=(carouselLinks.size()+rowStart)-1;j++) {
	        		 exportData(path,"Output_mob_prod", "SL NO", j+1, Integer.toString(j));
	        		 exportData(path,"Output_mob_prod", "Department", j+1, "Deals Page");
	        		 exportData(path,"Output_mob_prod", "Department URL", j+1, "https://gamestop.com/Deals");
	        		 exportData(path,"Output_mob_prod", "Navigation URL", j+1, carouselLinks.get(j-rowStart));
	 	        	//System.out.println("Site content updated with navigation url.");
	 	        }
	        	 
	        	 
	        	 if(driver.findElements(By.id("productSelect")).size()==1){
           			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("productSelect")));
	                  List<WebElement>  options = driver.findElements(By.xpath("//select[@id='productSelect']/option"));
	                
	                 for(WebElement ele: options) {
	                	 Base.wait5Seconds();
	                	 ele.click();
	                	 Base.wait5Seconds();
	                      
	                	 if(driver.findElements(By.xpath("//div[@class='product-grid-tile-wrapper']")).size()>=1) {
	                		 exportData(path,"Output_mob_prod", "SL NO", rowStart+1, Integer.toString(rowStart));
			        		 exportData(path,"Output_mob_prod", "Department", rowStart+1, "Deals Page");
			        		 exportData(path,"Output_mob_prod", "Department URL", rowStart+1, "https://gamestop.com/Deals");
			        		 exportData(path,"Output_mob_prod", "Navigation URL", rowStart+1, ele.getText());
	                		 exportData(path,"Output_mob_prod", "Breadcrumb Path", rowStart+1, "DealsPage");		
	                		exportData(path,"Output_mob_prod", "Comments", rowStart+1, "Deals appeared");
	                	 }else {
	                		 exportData(path,"Output_mob_prod", "SL NO", rowStart+1, Integer.toString(rowStart));
			        		 exportData(path,"Output_mob_prod", "Department",rowStart+1, "Deals Page");
			        		 exportData(path,"Output_mob_prod", "Department URL", rowStart+1, "https://gamestop.com/Deals");
			        		 exportData(path,"Output_mob_prod", "Navigation URL", rowStart+1, ele.getText());
	                		 exportData(path,"Output_mob_prod", "Breadcrumb Path", rowStart+1, "DealsPage");		
		                		exportData(path,"Output_mob_prod", "Comments", rowStart+1, "Deals Not present");
	                	 }
	                    
	                 }
	                 
	                 System.out.println("After selection of category"+carouselLinks.size());
           		}else {
           			System.out.println("select is not identified");
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
		            			exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
                                    exportData(path,"Output_mob_prod", "Comments", i+1, "Product Page");
		            		}
		            		
		            		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
		            			exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, "Home");		
	                			exportData(path,"Output_mob_prod", "Comments", i+1, "Landing Page");
		            		}
		            		else if(srchelmnt.equals("")) {
		            			String text1="";
	                			List<WebElement> rslts= driver.findElements(By.xpath("(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']"));
	                			for(WebElement srch1: rslts) {
	                    			 text1 = srch1.getText();
	                    			System.out.println(text1);
			            			exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);		
	                    			exportData(path,"Output_mob_prod", "Comments", i+1, text1);
		            		}
		            		}else {
				            		exportData(path,"Output_mob_prod", "Breadcrumb Path", i+1, breadcrumbPath);
				            		
				            		exportData(path,"Output_mob_prod", "Comments", i+1, srch[0]);
				            		
				            		System.out.println();
		            		}
}

	}

}
