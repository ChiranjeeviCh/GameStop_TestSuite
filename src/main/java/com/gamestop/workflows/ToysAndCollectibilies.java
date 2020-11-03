package com.gamestop.workflows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;
import com.gamsestop.pages.ToysAndCollectibilesPage;

public class ToysAndCollectibilies extends Base{
	public WebDriver driver;
	
	ToysAndCollectibilesPage tac=new ToysAndCollectibilesPage(driver);
	public ToysAndCollectibilies(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		tac=PageFactory.initElements(driver, ToysAndCollectibilesPage.class);
	}
	
public void clickOnElement(WebElement element) {
	Actions a = new Actions(driver);
	a.moveToElement(element).click().perform();
}

}
