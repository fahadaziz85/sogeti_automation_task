package com.sogeti.pageobjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.sogeti.basetestframework.BasePage;

public class SogetiHomePage extends BasePage {

	// Constructor
	public SogetiHomePage(WebDriver driver) {
		super(driver);
	}
	// UI Elements
	@FindBy(className = "level2")
	private WebElement servicesLink;
	@FindBy(linkText = "Automation")
	private WebElement automationLink;
	@FindBy(className = "acceptCookie")
	private WebElement cookies_accept;
	
	// Contact Us Form Elements
	@FindBy(className = "FormTextbox__Input")
	private WebElement firstName;
	//Cuntry selector
	@FindBy(className = "FormSelection")
	private WebElement selectCountry;
	// Checkbox
	@FindBy(className = "FormChoice__Input--Checkbox")
	private WebElement iAgreeCheckBox;
	// Submit Button
	@FindBy(name = "submit")
	private WebElement submitBtn;

	public void acceptCookies() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.elementToBeClickable(cookies_accept)).click();
	}

	public void verifyServices() {

		// Mouse hover on Serices link
		mouseHover(driver, servicesLink);
		boolean servicesLinkIsSelected = servicesLink.getAttribute("class").contains("selected");
		System.out.println("servicesLinkIsSelected before click: " + servicesLinkIsSelected);

		// identifying Automation link parent element
		WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;",
				automationLink);

		// you verify that this is automation link
		Assert.assertEquals(parent.getAttribute("innerHTML").contains("Automation"), true,
				"This is not Automation Link");

		// at this moment automationLinkIsSelected should be false as it is not selected
		boolean automationLinkIsSelected = parent.getAttribute("class").contains("selected");
		System.out.println("automationLinkIsSelected: " + automationLinkIsSelected);

		Assert.assertEquals(automationLinkIsSelected, false, "Automation Link is already selected");
		automationLink.click();

	}

	public void verifyAutomationPage() {
		String expectedTitle = "Automation";
		String actualTitle = driver.getTitle();
		// Verify page title
		Assert.assertEquals(actualTitle, expectedTitle, "Title does not matched!");
		WebElement pageHeading = driver.findElement(By.className("page-heading"));
		boolean actualHeading = pageHeading.getAttribute("innerHTML").contains("Automation");
		// Verify page heading
		Assert.assertEquals(actualHeading, true, "Page Heading is not Automation");

	}

	public void verifyLinksIsSelected() {
		mouseHover(driver, servicesLink);
		boolean servicesLinkIsSelected = servicesLink.getAttribute("class").contains("selected");
		System.out.println("servicesLinkIsSelected: " + servicesLinkIsSelected);
		WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;",
				automationLink);
		Assert.assertEquals(parent.getAttribute("innerHTML").contains("Automation"), true,
				"This is not Automation Link");
		boolean automationLinkIsSelected = parent.getAttribute("class").contains("selected");
		System.out.println("automationLinkIsSelected: " + automationLinkIsSelected);

		Assert.assertEquals(servicesLinkIsSelected, true, "Services Link is not selected");
		Assert.assertEquals(automationLinkIsSelected, true, "Automation Link is not selected");

	}
	
	public void scrollDownPage() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3700)");

	}
	
	public void fillContactForm() {
		
		String givenName = getSaltString(10);
		firstName.sendKeys(givenName);
		String surName = getSaltString(10);
		String email = getSaltString(12) + "@gmx.de";
		String phoneNr = getSaltString(7);
		String messageBox = getSaltString(30);
		
		firstName.sendKeys(givenName);
		firstName.sendKeys(Keys.TAB);
		
		driver.switchTo().activeElement().sendKeys(surName);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		
		driver.switchTo().activeElement().sendKeys(email);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		
		driver.switchTo().activeElement().sendKeys(phoneNr);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		
		Select drpCountry = new Select(selectCountry.findElement(By.tagName("select")));
		drpCountry.selectByVisibleText("Germany");
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		
		driver.switchTo().activeElement().sendKeys(messageBox);
		
		// checking the state of checkbox if selected or not
		System.out.println("The checkbox is selection state is- " + iAgreeCheckBox.isSelected());
		
		// clicking on I agree check box
		iAgreeCheckBox.click();
		
		//submitting the form with submit button
		submitBtn.click();
		
		
		
	}
	
	public void verifyThankyouMessage() {
		driver.switchTo().defaultContent();
		String thankyouMessage= "<p>Thank you for contacting us.</p>";
		waitForElementToAppear(By.className("Form__Status__Message"));
		System.out.println("Success Message is visible after waiting");
		WebElement successMessage = driver.findElement(By.className("Form__Success__Message"));
		//System.out.println("This is message"+successMessage.getAttribute("innerHTML"));
		boolean messageDisplayed = successMessage.getAttribute("innerHTML").equals(thankyouMessage);
		Assert.assertEquals(messageDisplayed, true, "Thank you message is not displayed or not correct");
		
	}
	
	protected String getSaltString(int strLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < strLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
