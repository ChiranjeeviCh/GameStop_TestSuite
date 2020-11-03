package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EletronicsPage {

	public WebDriver driver;
	public EletronicsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='nav navbar-nav level-1']/li/a[@data-name='Electronics']")
	public WebElement electronicLink;
	
	@FindBy(xpath="//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]")
	public WebElement viewMore;
	
	@FindBy(xpath="//div[@class='dropdown-menu block-layout show']//img[contains(@class,'icon expand-arrow')]")
	public List<WebElement> viewMores;
	
	@FindBy(xpath="//div[@class='dropdown-menu block-layout show']//ul/li/a")
	public List<WebElement> allEletronics;
	
	@FindBy(xpath="//h1[contains(text(),'Electronics')]//ancestor::div//following-sibling::div//div[@class='slick-list draggable']//div[@role='tabpanel']//a")
	public List<WebElement> carousals;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public WebElement homegrid;
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']")
	public List<WebElement> homegrids;
	
	
	@FindBy(xpath="//div[@class='collection-tabs4 content-width']//a")
	public List<WebElement> listOfGids;
	
	
}
