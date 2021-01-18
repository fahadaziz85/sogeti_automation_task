package com.sogeti.regressiontests;

import org.testng.annotations.Test;

import com.sogeti.basetestframework.BaseClass;
import com.sogeti.pageobjects.SogetiHomePage;

public class VerifyCountryLinks extends BaseClass{
	
	@Test
	public void TestCase3() {
		SogetiHomePage homepage = new SogetiHomePage(getDriver());
		homepage.acceptCookies();
		homepage.validateCountriesLinks();
		
	}

}
