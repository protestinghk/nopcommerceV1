package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import org.testng.annotations.AfterClass;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	public WebDriver driver;
	public Properties prop;
	public Logger logger = LogManager.getLogger(this.getClass());
	@BeforeClass
	@Parameters("browser")
	public void setUp(String br) throws IOException
	{
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\config.properties");
		prop.load(file);
		if(br.equals("chrome"))		
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
		else if(br.equals("edge"))
		{
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	
	}
	public String randomString() 
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public String randomNum() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}	
	
}
