package com.sogeti.basetestframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/fahadaziz/Downloads/Softwares/chromedriver/chromedriver"); //  
		driver = new ChromeDriver();
		driver.get("http://www.sogeti.com");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public WebDriver getDriver() {
        return driver;
    }
}
