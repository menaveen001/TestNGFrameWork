package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LogOutPage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LogoutTest extends Base {

	public LogoutTest() {
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
		loginPage = homePage.selectLoginOption();

	}
	
	@Test(priority = 1, dataProvider = "vailidTestData")
	public void VerifyLoggingoutbyselectingLogoutoptionfromMyAccountdropmenu(String emailText, String passwordText) {
		
		loginPage.enterEmailAddress(emailText);
		loginPage.enterPassword(passwordText);
		accountPage = loginPage.clickOnLoginButtonOption();
		homePage.selectLogOutOption();
		LogOutPage logoutPage = new LogOutPage(driver);
		logoutPage.clickOnContinueOption();
	
	}
	
	@DataProvider(name = "vailidTestData")
	public Object[][] getTestData() {
		Object[][] data = Utilities.getTestDataFromExcelFile("Login");
		return data;
	}
}

