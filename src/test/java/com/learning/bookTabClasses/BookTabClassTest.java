package com.learning.bookTabClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.learning.BookTabObjects.BookTabObjectTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BookTabClassTest {

	public WebDriver driver;

	FileInputStream fin = null;

	ExtentReports extent;
	ExtentTest logger;
	ExtentTest logger1;

	@Test
	@BeforeClass
	@Parameters({ "driverProperty", "driverPropertyValue" })
	public void loadSpiceJetHome(String driverProperty, String driverPropertyValue) {

		System.out.println("Inside loadSpiceJetHome() Method - Before Class");

		System.setProperty(driverProperty, driverPropertyValue);

		driver = new ChromeDriver();

		// driver.get("https://www.spicejet.com/");

		Properties properties = new Properties();

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/extent_report.html", true);
		extent.addSystemInfo("Host", "Spicejet");
		extent.addSystemInfo("Username", "Suraksha");
		extent.loadConfig(new File(System.getenv("user.dir") + "\\extent-config.xml"));

		try {
			fin = new FileInputStream("webAppProperties.properties");
			properties.load(fin);
			driver.get(properties.getProperty("spicejet.url"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void bookFlights() {

		System.out.println("Inside bookFlights() Method");

		BookTabObjectTest bookFlight = new BookTabObjectTest(driver);

		bookFlight.getDepatureCity().click();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div#dropdownGroup1>div>ul:nth-child(1)>li:nth-child(7)")));
		
		logger = new ExtentTest("Test1", "To check for the Depature City");
		extent.startTest("Check the depature City");
		Assert.assertTrue(driver.findElement(By.cssSelector("div#dropdownGroup1>div>ul:nth-child(1)>li:nth-child(7)")).getText().contains("BLR"));
		logger.log(LogStatus.PASS, "The Selected Depature City is Benagaluru");

		driver.findElement(By.cssSelector("div#dropdownGroup1>div>ul:nth-child(1)>li:nth-child(7)")).click();
		
		

		//driver.findElement(By.xpath(
				//"//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']/table/tbody/tr[2]/td[2]/div[3]/div[@id='dropdownGroup1']/div/ul[3]/li[12]"))
				//.click();
		
		System.out.println("post jira");
		System.out.println("post jira1");
		System.out.println("post jira2");
		
		System.out.println("post jir3");
		System.out.println("post jira1");
		System.out.println("post jira2");
		

	}
	
	@AfterMethod
    public void setTestResult(ITestResult result) throws IOException {


        if (result.getStatus() == ITestResult.FAILURE) {
        	logger.log(LogStatus.FAIL, result.getName());
            logger.log(LogStatus.FAIL,result.getThrowable());
            //test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
        	logger.log(LogStatus.PASS, result.getName());
            //test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
        } else if (result.getStatus() == ITestResult.SKIP) {
        	logger.log(LogStatus.SKIP, result.getName() + " has been skipped");
        }
        
        extent.endTest(logger);
		extent.flush();
		extent.close();
      }
	
	@AfterMethod
	public void toLog() {
		
	}

}