package com.nopcommerce.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass
{
	
	@Test(priority=1)
	public void searchCustomerbyEmail() throws IOException, InterruptedException
	{
		driver.get(prop.getProperty("baseURL"));
		
		LoginPage lp=new LoginPage(driver);
		lp.enterUsername(prop.getProperty("userEmail"));
		lp.enterPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		//Goto Search Page
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
			
		//Provide EMAIL in Search Page
			
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		searchcust.setEmail("victoria_victoria@nopCommerce.com");
		
		searchcust.clickSearch();
		
		
		Thread.sleep(5000);
		
		boolean status=searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	
}