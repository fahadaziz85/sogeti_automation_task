package com.sogeti.regressiontests;

import org.testng.annotations.Test;

import com.sogeti.basetestframework.BaseClass;
import com.sogeti.pageobjects.SogetiHomePage;

public class ContactUsForm extends BaseClass{
	
	@Test
	public void TestCase2() {
		SogetiHomePage homepage = new SogetiHomePage(getDriver());
		homepage.acceptCookies();
		homepage.verifyServices();
		homepage.scrollDownPage();
		homepage.fillContactForm();
		homepage.verifyThankyouMessage();
	}
	
}
