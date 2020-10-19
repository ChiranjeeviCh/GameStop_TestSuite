package com.gamsestop.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;

public class HomePage_Mob extends Base{

	
	public WebDriver driver;
	public HomePage_Mob(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class='home-hero1 hero-theme-light slick-slide slick-cloned']//a")
	List<WebElement> carousals;
	
	@FindBy(xpath="//h2[text()='Featured Products & Offers']")
	WebElement featurePO;
	
	@FindBy(xpath="//h2[text()='Featured Products & Offers']/parent::div/following-sibling::div//a")
	List<WebElement> prodOffers;
	
	@FindBy(xpath="//span[text()='Top Selling Games']")
	WebElement topSelling;
	
	@FindBy(xpath="//span[text()='Top Selling Games']/ancestor::div/following-sibling::div[@data-mobile-view-type='mobileCarousel-2up']//a[@role='presentation']")
	List<WebElement> listOfGames;
	
	@FindBy(xpath="//h3[contains(text(),'Recommended Products')]")
	WebElement recomdProdstitle;
	
	@FindBy(xpath="//div[@id='home1_rr']//a[@role='presentation']")
	List<WebElement> listOfRP;
	
	@FindBy(xpath="//span[text()='Top Selling Collectibles']")
	WebElement sellingtitle;
	
	@FindBy(xpath="//span[text()='Top Selling Collectibles']/ancestor::div[@class='product-content-info']//following-sibling::div//a[@role='presentation']")
	List<WebElement> topCollectibles;
	
	@FindBy(xpath="//h2[text()='Video Games Coming Soon']")
	WebElement videoGamesCStitle;
	
	@FindBy(xpath="//h2[text()='Video Games Coming Soon']/parent::div/following-sibling::div//a")
	List<WebElement> listOfvideoGamesCS;
	
	@FindBy(xpath="//ol[@class='breadcrumb']/li/a")
	List<WebElement> breadcrumbPath;
	
	@FindBy(xpath="//div[@class='col']/h1/span[1]")
	WebElement searchResults;
	
	@FindBy(xpath="(//span[contains(text(),'Results')])//parent::div[@class='result-count text-md-center col-12 col-md-auto order-md-2']")
	WebElement middleSearchResults;
	
	public WebElement middleSearchResults() {
		return middleSearchResults;
	}
	
	public WebElement searchResults() {
		return searchResults;
	}
	
	
	
	public List<WebElement> breadcrumbPath(){
		return breadcrumbPath;
	}
	
	public List<WebElement> listOfvideoGamesCS(){
		return listOfvideoGamesCS;
	}
	
	
	public WebElement videoGamesCStitle() {
		return videoGamesCStitle;
	}
	public List<WebElement> topCollectibles(){
		return topCollectibles;
	}
	
	public WebElement sellingTitle() {
		return sellingtitle;
	}
	
	public List<WebElement> listOFprods(){
		return listOfRP;
	}
	
	
	public  WebElement titleOfProds() {
		return recomdProdstitle;
	}
	
	public List<WebElement> TopGames() {
		return listOfGames;
	}
	
	public WebElement sellingGames() {
		return topSelling;
	}
	
	public List<WebElement> listofprods(){
		return prodOffers;
	}
	
	
	public WebElement featureProOff() {
		return featurePO;
	}
	
	
	public List<WebElement> listOfCarousals(){
		return carousals;
	}	
	}
