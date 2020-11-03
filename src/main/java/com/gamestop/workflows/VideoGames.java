package com.gamestop.workflows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;
import com.gamsestop.pages.VideoGamesPage;

public class VideoGames extends Base{

	public WebDriver driver;
	VideoGamesPage vmp=new VideoGamesPage(driver);
	public VideoGames(WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		vmp=PageFactory.initElements(driver, VideoGamesPage.class);
	}

	public void clickOnElement(WebElement element) throws Exception {
		Base.WaitForElementPresent(element, 10);
		element.click();
}
}
