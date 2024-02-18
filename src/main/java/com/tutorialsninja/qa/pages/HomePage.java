package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
    @FindBy(linkText = "Logout")
    private WebElement logoutOption;
	
	//Actions
	public void clickOnMyAccount() {
		
		myAccountDropMenu.click();
	}
	public LoginPage selectLoginOption() {
		
		loginOption.click();
		return new LoginPage(driver);
	}
	public void selectRegisterOptin() {
		
		registerOption.click();
	}
	
	public void selectLogOutOption() {
		logoutOption.click();
	}
}
