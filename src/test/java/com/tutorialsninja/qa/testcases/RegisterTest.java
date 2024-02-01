package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	public WebDriver driver;
	HomePage homePage;
	RegisterPage register;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {

		super();
	}

	@BeforeMethod
	//for lunching the browser
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectRegisterOptin();

	}

	@Test(priority = 1, dataProvider = "registerAccountData")
	public void verifyRegisteringAnAccountWithMandatoryFields(String firstNameText, String lastNameText,
			String emailText, String telphoneText, String passwordText) {
		register = new RegisterPage(driver);
		register.enterFirstNameAddress(firstNameText);
		register.enterLastNameAddress(lastNameText);
		register.enterEmailAddress(System.currentTimeMillis() + emailText);
		register.enterTelephoneAddress(telphoneText);
		register.enterPasswordAddress(passwordText);
		register.enterConfirmPasswordAddress(passwordText);
		register.selectPrivacyPolicyCheckBox();
		register.clickOnContinueButtonOption();
		accountSuccessPage = new AccountSuccessPage(driver);

		String actualSuccessHeading = accountSuccessPage.retriveAccountCreatedSuccessMessage();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!",
				"Account Success page is note displayed");

	}

	@Test(priority = 2, dataProvider = "registerAccountData")
	public void verifyRegisteringAccountByProvidingAllFields(String firstNameText, String lastNameText,
			String emailText, String telphoneText, String passwordText) {

		register = new RegisterPage(driver);
		register.enterFirstNameAddress(firstNameText);
		register.enterLastNameAddress(lastNameText);
		register.enterEmailAddress(System.currentTimeMillis() + emailText);
		register.enterTelephoneAddress(telphoneText);
		register.enterPasswordAddress(passwordText);
		register.enterConfirmPasswordAddress(passwordText);
		register.selectNewsLatterRadioButton();
		register.selectPrivacyPolicyCheckBox();
		register.clickOnContinueButtonOption();
		accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccessPage.retriveAccountCreatedSuccessMessage();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!",
				"Account Success page is note displayed");

	}

	@Test(priority = 3, dataProvider = "registerAccountData")
	public void VerifyRegisteringAccountWithExistingEmailAddress(String firstNameText, String lastNameText,
			String emailText, String telphoneText, String passwordText) {

		register = new RegisterPage(driver);
		register.enterFirstNameAddress(firstNameText);
		register.enterLastNameAddress(lastNameText);
		register.enterEmailAddress(emailText);
		register.enterTelephoneAddress(telphoneText);
		register.enterPasswordAddress(passwordText);
		register.enterConfirmPasswordAddress(passwordText);
		register.selectNewsLatterRadioButton();
		register.selectPrivacyPolicyCheckBox();
		register.clickOnContinueButtonOption();

		String actualWarning = register.retriveEmailAddressAlreadyRegisterWarningMessage();
		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),
				"Warning message is not Displyed");

	}

	@DataProvider(name = "registerAccountData")
	public Object[][] getRegisterAccountData() {
		Object[][] data = Utilities.getTestDataFromExcelFile("Register");
		return data;
	}

	@AfterMethod
	// for closing the browser
	public void tearDown() {
		driver.quit();
	}

}
