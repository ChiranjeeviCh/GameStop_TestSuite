package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClothingsPage {

	public WebDriver driver;
	public ClothingsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Clothing']")
	public WebElement clothingLink;
	

	@FindBy(xpath="//div[@class='slick-list draggable']//div[@role='tabpanel']//a[contains(@class,'primary')]")
	public List<WebElement> listOfCarousals;
	
	@FindBy(xpath="//h4[text()='Featured Products']")
	public WebElement featuredProd;
	
	@FindBy(xpath="//h4[text()='Featured Products']")
	public List<WebElement> featuredProds;
	
	@FindBy(xpath="//div[@role='tabpanel']//a[@class='hfhover']")
	public List<WebElement> listOfFPs;
	
	@FindBy(xpath="//div[@class='row key-art-carousel']")
	public WebElement grid;
	
	@FindBy(xpath="//div[@class='row key-art-carousel']")
	public List<WebElement> grids;
	
	@FindBy(xpath="//div[@class='col-md-4 p-1 slide']//a")
	public List<WebElement> listOfGrids;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public WebElement grid1;
	
	@FindBy(xpath="//div[@class='home-grid6']")
	public List<WebElement> grids1;
	
	@FindBy(xpath="//div[@class='home-grid6']//a")
	public List<WebElement> llistOfGrids1;
	
	
}
