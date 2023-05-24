package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameAddressField;
	@FindBy(id = "input-lastname")
	private WebElement lastNameAddressField;
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	@FindBy(id = "input-telephone")
	private WebElement telephoneAddessField;
	@FindBy(id = "input-password")
	private WebElement passwordAddressField;
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordAddressField;
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement privacyPolicyCheckBoxOption;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement continueButtonOption;
	@FindBy(xpath = "(//input[@name='newsletter'])[1]")
	private WebElement newsLatterRadioButtonOption;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailAddressAlreadyRegisterWarningMessage;

	// Actions

	public void enterFirstNameAddress(String firstName) {
		firstNameAddressField.sendKeys(firstName);
	}

	public void enterLastNameAddress(String lastName) {

		lastNameAddressField.sendKeys(lastName);
	}

	public void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}

	public void enterTelephoneAddress(String telephone) {
		telephoneAddessField.sendKeys(telephone);
	}

	public void enterPasswordAddress(String password) {
		passwordAddressField.sendKeys(password);
	}

	public void enterConfirmPasswordAddress(String password) {
		confirmPasswordAddressField.sendKeys(password);
	}

	public void selectPrivacyPolicyCheckBox() {
		privacyPolicyCheckBoxOption.click();

	}

	public void clickOnContinueButtonOption() {
		continueButtonOption.click();
	}

	public void selectNewsLatterRadioButton() {
		newsLatterRadioButtonOption.click();
	}
	public String retriveEmailAddressAlreadyRegisterWarningMessage() {
		  String emailWaringMessage = emailAddressAlreadyRegisterWarningMessage.getText();
		  return emailWaringMessage;
	}

}
