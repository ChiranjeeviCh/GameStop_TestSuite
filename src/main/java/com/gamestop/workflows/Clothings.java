package com.gamestop.workflows;

import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;
import com.gamsestop.pages.ClothingsPage;

public class Clothings extends Base {

	public Clothings() {
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, ClothingsPage.class);
	}
}
