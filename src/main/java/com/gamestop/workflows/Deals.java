package com.gamestop.workflows;

import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;
import com.gamsestop.pages.DealsPage;

public class Deals extends Base{
	
	public Deals() {
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, DealsPage.class);
	}

}
