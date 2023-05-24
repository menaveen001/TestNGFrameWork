package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		dataProp = new Properties();
		File dataFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream dfis = new FileInputStream(dataFile);
			dataProp.load(dfis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public WebDriver initializeBrowserAndOpenApplication(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("IE")) {

			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLECIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
