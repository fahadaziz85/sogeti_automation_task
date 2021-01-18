package com.sogeti.regressiontests;

import org.testng.annotations.Test;

import com.sogeti.basetestframework.BaseClass;
import com.sogeti.pageobjects.SogetiHomePage;
public class SogetiServicesTestCase1 extends BaseClass {
	
	@Test
	public void TestCase1() {
		
		SogetiHomePage homepage = new SogetiHomePage(getDriver());
		homepage.acceptCookies();
		homepage.verifyServices();
		homepage.verifyAutomationPage();
		homepage.verifyLinksIsSelected();
	}

}