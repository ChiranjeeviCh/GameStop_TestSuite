package com.gamsestop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gamestop.resources.Base;

public class CustomDatePage  extends Base{

		
	 public CustomDatePage() {
			PageFactory.initElements(driver, this);
			}
		 
		
		@FindBy(xpath="//div[@id='dw-sf-control']")
		private	WebElement settings;
		
		@FindBy(css="a[id='ext-gen43']")
		public WebElement sitePriview;
		
		@FindBy(xpath="//img[@id='datePickerButton']")
		public WebElement datePicker;
		
		@FindBy(id="ext-gen2225")
		public WebElement calenderDates;
		
		@FindBy(xpath="(//td[@title='Today']//following-sibling::td/a)[1]")
		public WebElement dateSelect;
		
		@FindBy(id="controlsCustomerGroup")
		private WebElement optionsToSelect;
		
		
		@FindBy(xpath="//input[@id='controlsOk']")
		public WebElement okButton;
		

		public WebElement clickOnOk() {
			return okButton;
		}
		
		public WebElement customerOptions() {
			return optionsToSelect;
		}
		
		
		public WebElement dateSelector() {
			return dateSelect;
		}
		
		
		public WebElement datePicker() {
			return datePicker;
		}
		
		public WebElement sitepriview() {
			return sitePriview;
		}
		public WebElement settings() {
			return settings;
		}

		
}
