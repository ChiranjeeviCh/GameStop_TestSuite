package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.gamestop.resources.Base;

public class VideoGames_HomePage {
	public  WebDriver driver;
	
	public VideoGames_HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//a[@data-name='Video Games']")
	public WebElement videoGamesLink;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public WebElement homeGrid;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public List<WebElement> homeGrids;
	
	@FindBy(xpath="//div[@class='home-grid6']//a") 
	public List<WebElement> listOfHomegrids;
	
	@FindBy(xpath="//h3[text()='Video Games By Platform']")
	public WebElement videoGamesPlaatform;
	
	@FindBy(xpath="//h3[text()='Video Games By Platform']")
	public List<WebElement> videoGamesPlaatforms;
	
	@FindBy(xpath="//div[@class='container my-4']//div[@class='row']//h3")
	public List<WebElement> videoGamesgrids;
	
	
	
	public void getHrefAndNavigate(WebElement element) throws Exception {
		 
	       /* WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.elementToBeClickable(element));*/
		Base.WaitForElementPresent(element, 10);
		String href=element.getAttribute("href");
	        driver.get(href);
	}
	public void gethrefsOfVG(WebElement element,List<WebElement> elements,List<String> carouselLinks) {
		if(elements.size()==1) {
  			Base.scrollToElement(element);
  			for(WebElement ele:elements) {
  				String gridText=ele.getAttribute("innerHTML");
  				System.out.println(gridText);
  				List<WebElement> gridlinks=driver.findElements(By.xpath("//h3[text()='Video Games By Platform']//following-sibling::div//h3[text()='"+gridText+"']/parent::a/following-sibling::div//a"));
  				 for(WebElement gridlink: gridlinks) {
  					String gridlnk = gridlink.getAttribute("href");
  					System.out.println(gridlnk);
		            carouselLinks.add(gridlnk);
  				 }
  				
  			}
  		
	}
	}
}
