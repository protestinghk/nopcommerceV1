package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;


public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws IOException
	{
		logger.info("************starting TC_LoginTest_001****************");
		driver.get(prop.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);
		logger.info("********providing logging details*******");
		lp.enterUsername(prop.getProperty("userEmail"));
		lp.enterPassword(prop.getProperty("password"));
		logger.info("************clicking on the login button****************");
		lp.clickLogin();
		
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		logger.info("************login validation****************");
		if (exp_title.equals(act_title))
		{
			logger.info("************login test passed****************");
			Assert.assertTrue(true);
			
		}
		else
		{
			logger.error("************login test failed****************");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
		}
		
		lp.clickLogout();
	}
}
