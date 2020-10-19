package com.gamestop.workflows;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;
import com.gamestop.resources.ReusableMethods;
import com.gamestop.resources.TestUtils;
import com.gamsestop.pages.HomePage_Stage;


public class Home_Stage extends Base {
	
	HomePage_Stage hm=new HomePage_Stage(driver);
	ReusableMethods rm=new ReusableMethods();
	public static  Map<String, List<String>> siteContent = new HashMap<>();
	TestUtils ts=new TestUtils();
	JavascriptExecutor js = ((JavascriptExecutor)driver);
	public static Logger log = LogManager.getLogger(Home_Stage.class.getName());
	
   List<String> breadcrumb=new ArrayList<>();
	public Home_Stage() {
		
				PageFactory.initElements(driver, this);
				PageFactory.initElements(driver, HomePage_Stage.class);
	}
	static String path=System.getProperty("user.dir")+"\\Testdata\\ExportExcel.xlsx";
	HashMap<String,List<String>> inputData;
	
	public void selectingDate() throws Exception {
		inputData=Base.readFileOnce(path, "Input Content");
		String username =inputData.get("Username").get(0);
		String password =inputData.get("Password").get(0);
		driver.get("https://"+username+":"+password+"@"+"sfcc-stg.gamestop.com/");
		driver.get(inputData.get("Department URL").get(0));
		log.info("Successfully luanch URL"+inputData.get("Department URL").get(0));
		Base.switchToFrame("DW-SFToolkit");
		log.info("switch to frame");
		HomePage_Stage hp=new HomePage_Stage(driver);
		hp.stagingPlus1Day();
		log.info("Moved to +1 day");
		/*hm.validatePageTitle();
		hm.validateLogo();*/
	}
	public void getURL() {
		driver.get(inputData.get("Department URL").get(1));
		log.info("Successfully luanch URL"+inputData.get("Department URL").get(1));
	}
	public void selectingDateForMobile() {
		
	}
	public void login_GameStop() {
		try {
			PageFactory.initElements(driver, HomePage_Stage.class);
			if(!driver.getCurrentUrl().contains("Consoles"))
			{
				log.info("Inside the login session block");
			
			}
			else 
			{
				log.info("Logged in on the new session");
			}
		}catch(Exception e) {
			e.printStackTrace();
			//test.log(Status.FAIL, "StackTrace Result: " + Thread.currentThread().getStackTrace());
			assertTrue(false);
		}
	
}
	public void login(String uname, String pwd,String env){
		  String URL = "http://" + uname + ":" + pwd + "@" + env;
		  driver.get(URL);
		}
	List<String> carouselLinks= new ArrayList<>();
	public void homepageContent() throws Exception {
		Base.wait10Seconds();
		Base.WaitForListOfElementPresent(HomePage_Stage.carousals, 10);
		//System.out.println(hm.carousals.size());
		getHrefs(HomePage_Stage.carousals, carouselLinks);
		
		log.info("Home page carousals"+carouselLinks.size());
		System.out.println("Featured Products & Offers starts : ");
		Base.WaitForElementPresent(HomePage_Stage.featureProOff(), 10);
		if(HomePage_Stage.featureProOff().isDisplayed()) {
			Base.scrollToElement(HomePage_Stage.featureProOff());
			Base.WaitForElementPresent(HomePage_Stage.featurePO, 10);
			getHrefs(hm.listofprods(), carouselLinks);
			
		}
		log.info("After adding feature products and offers :"+carouselLinks.size());
		 System.out.println("To selling games Starts :");
		 Base.WaitForElementPresent(HomePage_Stage.topSelling, 10);
		 if(HomePage_Stage.topSelling.isDisplayed()) {
			 Base.scrollToElement(HomePage_Stage.topSelling);
			 Base.WaitForElementPresent(HomePage_Stage.topSelling, 10);
			 getHrefs(HomePage_Stage.listOfGames, carouselLinks);
		 }
		 log.info("After adding top selleing games :" +carouselLinks.size());
		
		 System.out.println("More Product & Features Starts:");
		 if(HomePage_Stage.morePO.isDisplayed()) {
			 Base.WaitForElementPresent(HomePage_Stage.morePO, 10);
			 Base.scrollToElement(HomePage_Stage.morePO);
			 getHrefs(HomePage_Stage.listOfMPO, carouselLinks);
		 }
		 log.info("After adding More Product & Features "+carouselLinks.size());
		 System.out.println("Recommended Products starts :");
		 if(HomePage_Stage.recomdProdstitle.isDisplayed()) {
			 Base.WaitForElementPresent(HomePage_Stage.recomdProdstitle, 10);
			 Base.scrollToElement(HomePage_Stage.recomdProdstitle);
			 getHrefs(HomePage_Stage.listOfMPO, carouselLinks);
		 }
		 log.info("After adding Recommended Products "+carouselLinks.size());
		 System.out.println("Top Selling collectabiles starts here :");
		 if(HomePage_Stage.topSelling.isDisplayed()) {
			 Base.WaitForElementPresent(HomePage_Stage.topSelling, 10);
			 Base.scrollToElement(HomePage_Stage.topSelling);
			 getHrefs(HomePage_Stage.topCollectibles(), carouselLinks);
		 }
		 log.info("After adding Top Selling collectabiles "+carouselLinks.size());
		 System.out.println("Video Games Coming Soon starts :");
		 if(HomePage_Stage.videoGamesCStitle.isDisplayed()) {
			 Base.WaitForElementPresent(HomePage_Stage.videoGamesCStitle, 10);
			 Base.scrollToElement(HomePage_Stage.videoGamesCStitle);
			 getHrefs(HomePage_Stage.listOfvideoGamesCS(), carouselLinks);
		 }
		 log.info("After adding Video Games Coming Soon "+carouselLinks.size());
		 log.info("total links from home page is "+carouselLinks.size());
		 System.out.println("Total links from Home page on staging :"+carouselLinks.size());
		 
		 siteContent.put("Navigation URLs", carouselLinks);
		 exportToExcelSheet(path, carouselLinks, inputData.get("Department URL").get(0),"Output_stage");
		 int rowStart=Base.getRowCount(path,"Output_stage");
		 getSearchElement(rowStart, carouselLinks, path, "Ouput_stage");
	}	            			
	        		   		

	public static void getHrefs(List<WebElement> listOfEle,List<String> addToList) throws Exception {
		Base.WaitForListOfElementPresent(listOfEle,10);
		for(WebElement ele:listOfEle) {
			String href=ele.getAttribute("href");
			addToList.add(href);
			
		}
		
	}
	
	public void exportToExcelSheet(String path,List<String> carouselLinks,String deptURL,String sheetName) {
		for(int j=0;j<=carouselLinks.size()-1;j++) {
   		 Base.exportData(path,sheetName, "SL NO", j+2, Integer.toString(j+1));
   		 Base.exportData(path,sheetName, "Department", j+2, "HOME PAGE");
   		 Base.exportData(path,sheetName, "Department URL", j+2, deptURL);
   		 Base.exportData(path,sheetName, "Navigation URL", j+2, carouselLinks.get(j));
        	//System.out.println("Site content updated with navigation url.");
        }
	}
		public void getSearchElement( int rowStart,List<String> carouselLinks, String path,String sheetName) {
			for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
	        		driver.get(carouselLinks.get(i-rowStart));
	        		driver.manage().deleteAllCookies();
	        		 String breadcrumbPath = "";
	        		String srchelmnt = "";
	        			List<WebElement> breadcrumb = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	        			for(WebElement ele: breadcrumb) {
	            			String brdcrumbtext = ele.getText();
							//breadcrumb.add(brdcBasetxt);
	            			System.out.print(brdcrumbtext+" >> ");
	            			breadcrumbPath=breadcrumbPath+" >> "+brdcrumbtext;
	            		}
	            		
	            		WebElement srchElmnts =driver.findElement(By.xpath("(//div[@class='col']//span)[1]"));
	            		srchelmnt=srchElmnts.getText();
	            		String srch[]=srchelmnt.split(" ");
	            		if(driver.getCurrentUrl().contains("products")) {
	            			Base.exportData(path,sheetName, "Breadcrumb Path", i+1, breadcrumbPath);
	            			Base.exportData(path,sheetName, "Comments", i+1, "Product Page");
        		}
        		
        		else if(breadcrumbPath.equals("")&& srchelmnt.equals("")) {
        			Base.exportData(path,sheetName, "Breadcrumb Path", i+1, "Home");		
        			Base.exportData(path,sheetName, "Comments", i+1, "Landing Page");
        		}
        		else if(srchelmnt.equals("")) {
        			String text1=HomePage_Stage.middleSearchResults.getText();
        			
            			if(driver.getTitle().contains("Store")) {
            				Base.exportData(path,sheetName, "Breadcrumb Path", i+1, breadcrumbPath);		
            				Base.exportData(path,sheetName, "Comments", i+1, "StorePage");
            			}else {
            			Base.exportData(path,sheetName, "Breadcrumb Path", i+1, breadcrumbPath);		
            			Base.exportData(path,sheetName, "Comments", i+1, text1);
            			}
        		}
        		
        		else {
        			Base.exportData(path,sheetName, "Breadcrumb Path", i+1, breadcrumbPath);
	            		
        			Base.exportData(path,sheetName, "Comments", i+1, srch[0]);
	            		
	            		System.out.println();
        		}          	 }
        	

			
		}
		
     	 

	}

