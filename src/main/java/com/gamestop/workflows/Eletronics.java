package com.gamestop.workflows;

import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;
import com.gamsestop.pages.EletronicsPage;

public class Eletronics extends Base{
	public Eletronics() {
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, EletronicsPage.class);
	}

}
