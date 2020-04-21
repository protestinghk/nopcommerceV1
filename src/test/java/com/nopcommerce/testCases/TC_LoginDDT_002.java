package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider = "LoginData")
	public void logintest(String user, String pwd, String exp ) throws InterruptedException
	{
		logger.info("******************starting TC_LoginDDT_002***************************");
		driver.get(prop.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);
		logger.info("******************providing DDT login details************************");
		lp.enterUsername(user);
		lp.enterPassword(pwd);
		logger.info("******************clicking on DDT login*******************************");
		lp.clickLogin();
		Thread.sleep(5000);
		logger.info("*******************validating DDT login***********************************");
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		if (exp_title.equals(act_title))
		{
			if(exp.equals("Pass"))
			{
				logger.info("***************Test Passed***************");
				lp.clickLogout();
				Assert.assertTrue(true);
			}
			else if(exp.equals("Fail"))
			{
				logger.warn("************Test Failed************");
				lp.clickLogout();
				Assert.assertTrue(false);
			}
			else if(!exp_title.equals(act_title))
			{
				if(exp.equals("Pass"))
				{
					logger.warn("***********Test Failed************");
					Assert.assertTrue(false);
				}
				else if(exp.equals("Fail"))
				{
					logger.info("************Test Passed************");
					Assert.assertTrue(true);
				}
				
			}
			logger.info("*********Finished DDT Test*************");
			
		}
			
	}
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException 
	{
		
		String path=System.getProperty("user.dir")+"/TestData/testData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");	
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
				
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{		
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1",i, j);  //1,0
			}
		}
	
		return logindata;
		
	}
	
}
