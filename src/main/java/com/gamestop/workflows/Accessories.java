package com.gamestop.workflows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;
import com.gamsestop.pages.AccessoriesPage;

public class Accessories extends Base{
	
	public WebDriver driver;
	AccessoriesPage acc=new AccessoriesPage(driver);
	public Accessories(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		acc=PageFactory.initElements(driver, AccessoriesPage.class);
	}
	
	public void getResultsAndexportToExcel(String path,int rowStart,String sheetname,String linkText,String attributeValue) {
		String srchelmnt = "";
			String breadcrumbPath = "";  
		if(acc.breadcrumPath.size()>=1) {
     			for(WebElement ele: acc.breadcrumPath) {
          			String brdcrmtxt = ele.getText();
          			breadcrumbPath=breadcrumbPath+" >> "+brdcrmtxt;
          		}
     			System.out.println(breadcrumbPath);
     			if(acc.searchResults.size()==1) {
        			
             			srchelmnt = acc.searchresults.getText();
             		
             		}else {
             				srchelmnt = acc.searchMiddle.getText();
                 			
             		}
          		String srch[]=srchelmnt.split(" ");
          		 Base.exportData(path,sheetname, "SL NO", rowStart+1, Integer.toString(rowStart));
         		 Base.exportData(path,sheetname, "Department", rowStart+1, "Accessories"+"//"+linkText);
         		 Base.exportData(path,sheetname, "Department URL", rowStart+1, "https://sfcc-stg.gamestop.com/Accessories/"+linkText);
         		 Base.exportData(path,sheetname, "Navigation URL", rowStart+1, attributeValue); 	        	
          		Base.exportData(path,sheetname, "Breadcrumb Path", rowStart+1, breadcrumbPath);         		
          	    Base.exportData(path,sheetname, "Comments", rowStart+1, srch[0]);
          		
 	       
	}
	
	
	}
	}

