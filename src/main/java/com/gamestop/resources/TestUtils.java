package com.gamestop.resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.gamsestop.pages.HomePage_Stage;

public class TestUtils {
	public WebDriver driver;
	public static long PAGE_LOAD_TIMEOUT=30;
	public static long IMPLICIT_TIME=30;
	HomePage_Stage hm=new HomePage_Stage(driver);
	public static void selectNextDay(WebElement ele, int day) {
		DateFormat dateat2 = new SimpleDateFormat("dd"); 
        Date date2 = new Date();
        String today = dateat2.format(date2); 
        System.out.println(today);
        int currentDay=Integer.parseInt(today);
       
        int nextDate=currentDay+day;
        System.out.println(nextDate);
       String nextDay=String.valueOf(nextDate);
        //find the calendar
       // WebElement dateWidget = driver.findElement(By.id("dp-calendar"));  
        List<WebElement> columns=ele.findElements(By.tagName("span"));  

        //comparing the text of cell with today's date and clicking it.
        for (WebElement cell : columns) {
       
           if (cell.getText().equals(nextDay)) {
           
              cell.click();
              break;
           }
        }
	}
	public void getHrefs(List<WebElement> listOfEle,List<String> addToList) {
		for(WebElement ele:listOfEle) {
			String href=ele.getAttribute("href");
			addToList.add(href);
			
		}
		
	}
	
	public void exportToExcelSheet(String path,List<String> carouselLinks,String deptURL) {
		for(int j=0;j<=carouselLinks.size()-1;j++) {
   		 Base.exportData(path,"Output_stage", "SL NO", j+2, Integer.toString(j+1));
   		 Base.exportData(path,"Output_stage", "Department", j+2, "HOME PAGE");
   		 Base.exportData(path,"Output_stage", "Department URL", j+2, deptURL);
   		 Base.exportData(path,"Output_stage", "Navigation URL", j+2, carouselLinks.get(j));
        	//System.out.println("Site content updated with navigation url.");
        }
	}
		public void getSearchElement( int rowStart,List<String> carouselLinks, String path,String sheetName) {
			List<String> breadcrumb=new ArrayList<>();
			for(int i=rowStart;i<=(carouselLinks.size()+rowStart)-1;i++) {
	        		driver.get(carouselLinks.get(i-rowStart));
	        		driver.manage().deleteAllCookies();
	        	
	        		 String breadcrumbPath = "";
	        		String srchelmnt = "";
	        			List<WebElement> brdcBaseup = driver.findElements(By.xpath("//ol[@class='breadcrumb']/li/a"));
	        			
	            		for(WebElement ele: brdcBaseup) {
	            			String brdcBasetxt = ele.getText();
							breadcrumbPath=breadcrumbPath+" >> "+brdcBasetxt;
	            			
	            			
	            		}
	            		
	            		System.out.println(breadcrumbPath);
	            		
	            		List<WebElement> srchElmnts =driver.findElements(By.xpath("(//div[@class='col']//span)[1]"));
	            		for(WebElement ele:srchElmnts) {
	            			srchelmnt = ele.getText();
							breadcrumb.add(srchelmnt);
	            			System.out.print("Search element is:"+srchelmnt);
	            			
	            		}
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
	
