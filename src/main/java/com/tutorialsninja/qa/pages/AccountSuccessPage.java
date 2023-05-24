package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;			
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	WebDriver driver;
public AccountSuccessPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement accountCreatedSuccessMessage;

//Actions
public String retriveAccountCreatedSuccessMessage() {
	String successMessage = accountCreatedSuccessMessage.getText();
	return successMessage;
}
}
