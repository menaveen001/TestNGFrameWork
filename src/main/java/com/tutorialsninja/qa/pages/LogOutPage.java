package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage {
	WebDriver driver;

	public LogOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
}
	
	@FindBy(linkText = "Continue")
	private WebElement continueOption;
	
	public void clickOnContinueOption() {
		continueOption.click();
	}
} 