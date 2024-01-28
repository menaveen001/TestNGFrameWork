package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	public LoginTest() {
		super();
	}

	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;
	String inValidEmailText;
	String inValidPasswordText;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();

	}

	@Test(priority = 1, dataProvider = "vailidTestData")
	public void verifyLoginWithValidCredentials(String emailText, String passwordText) {

		loginPage.enterEmailAddress(emailText);
		loginPage.enterPassword(passwordText);
		accountPage = loginPage.clickOnLoginButtonOption();

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit Your Account Information option is not displayed");
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		inValidEmailText = dataProp.getProperty("InVailidEmail");
		inValidPasswordText = dataProp.getProperty("InVailidPassword");

		loginPage.enterEmailAddress(inValidEmailText);
		loginPage.enterPassword(inValidPasswordText);
		loginPage.clickOnLoginButtonOption();

		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWaringMessageText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedMessage), "Expected Warning message not displayed");
	}

	@Test(priority = 3, dataProvider = "vailidTestData")
	public void verifyLoginWithValidEmailAndInvalidPassword(String emailText, String passwordText) {

		inValidPasswordText = dataProp.getProperty("InVailidPassword");
		
		loginPage.enterEmailAddress(emailText);
		loginPage.enterPassword(inValidPasswordText);
		loginPage.clickOnLoginButtonOption();

		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWaringMessageText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedMessage), "Expected Warning message not displayed");
	}

	@Test(priority = 4, dataProvider = "vailidTestData")
	public void verifyLoginWithInvalidEmailAndValidPassword(String emailText, String passwordText) {

		inValidEmailText = dataProp.getProperty("InVailidEmail");
		loginPage.enterEmailAddress(inValidEmailText);
		loginPage.enterPassword(passwordText);
		loginPage.clickOnLoginButtonOption();

		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWaringMessageText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedMessage), "Expected Warning message not displayed");
	}

	@Test(priority = 5, dataProvider = "vailidTestData")
	public void verifyLoginWithOnlyValidEmailSkippingPassword(String emailText, String passwordText) throws InterruptedException {

		loginPage.enterEmailAddress(emailText);
		Thread.sleep(2000);
		loginPage.clickOnLoginButtonOption();

		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWaringMessageText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedMessage), "Expected Warning message not displayed");
	}

	@DataProvider(name = "vailidTestData")
	public Object[][] getTestData() {
		Object[][] data = Utilities.getTestDataFromExcelFile("Login");
		return data;
	}

	@AfterMethod
	// For closing the browser
	public void tearDown() {
		driver.quit();

	}

}
