package com.nopcommerce.utilities;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		
		
		//htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/"+repName);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\"+repName);		
		htmlReporter.config().setDocumentTitle("nopCommerce Automation Report"); 
		htmlReporter.config().setReportName("nopCommerce  Functional Testing"); 
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Harish");
			
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName()); 
		test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName()); 
		
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); 
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); 

		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		try 
		{
			test.addScreenCaptureFromPath(screenshotPath);
		} 
		catch (IOException e) 
		{
				e.printStackTrace();
		} 
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	
}


/*


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener{
	
		
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest test;
		public String suiteName;
		
		public void onStart(ITestContext testContext) 
		{
			htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/myReport.html");//specify location of the report
			
			htmlReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
			htmlReporter.config().setReportName("Functional Testing"); // name of the report
			htmlReporter.config().setTheme(Theme.DARK);
			
			extent=new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host name","localhost");
			extent.setSystemInfo("Environemnt","QA");
			extent.setSystemInfo("user","pavan");
			extent.setSystemInfo("os","Windows10");
			extent.setSystemInfo("Browser name","Chrome,Firefox,IE");
			
		}
		
			
		public void onTestSuccess(ITestResult result)
		{
			//extent.createTest(result.getTestClass().getName()).createNode(result.getName()).pass("details");
			test=extent.createTest(result.getName()); // create new entry in th report
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		

		public void onTestSkipped(ITestResult result)
		{
			test=extent.createTest(result.getName()); // create new entry in th report
			test.log(Status.SKIP, "Test Case SKIIPED  IS " + result.getName());
		}

		
		public void onTestFailure(ITestResult result)
		{
			test=extent.createTest(result.getName()); // create new entry in th report
			
			test.log(Status.FAIL, "Test Case FAILED IS " + result.getName());
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // SEND LOGS TO REPORTS
							
		}
		
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}

		

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
		
	}*/
