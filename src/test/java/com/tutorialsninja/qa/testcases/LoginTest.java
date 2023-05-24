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

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();

	}

	@Test(priority = 1, dataProvider = "vailidTestData")
	public void verifyLoginWithValidCredentials(String emailText, String passwordText) {

		loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(emailText);
		loginPage.enterPassword(passwordText);
		AccountPage accountPage = loginPage.clickOnLoginButtonOption();

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit Your Account Information option is not displayed");
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		String emailText = dataProp.getProperty("InVailidEmail");
		String passwordText = dataProp.getProperty("InVailidPassword");

		loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(emailText);
		loginPage.enterPassword(passwordText);
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
	public void tearDown() {
		driver.quit();

	}

}
