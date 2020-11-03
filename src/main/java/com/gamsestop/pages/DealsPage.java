package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DealsPage {
	
	public WebDriver driver;
	
	public DealsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@data-name='Deals']")
	public WebElement dealsLink;
	
	@FindBy(xpath="//div[contains(@id,'slick-slide')]//div//a")
	public List<WebElement> listOfCarousals;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public WebElement homeGrid;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public List<WebElement> homeGrids;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']//a")
	public List<WebElement> listofGrids;
	
	@FindBy(xpath="//div[@class='container mt-5 p-0']")
	public WebElement homegrid1;
	
	@FindBy(xpath="//div[@class='container mt-5 p-0']")
	public List<WebElement> homegrids1;
	
	@FindBy(xpath="//div[@class='container mt-5 p-0']//a")
	public List<WebElement> listOfGrids1;
	
	
	
	
	
	
	

}
