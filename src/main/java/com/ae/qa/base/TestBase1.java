package com.ae.qa.base;

import org.testng.Assert;
import org.testng.ITestContext;
import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.ae.qa.pages.LoginPage;
import com.ae.qa.pages.TenantsPage;
import com.ae.qa.util.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class TestBase1 {
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log = LogManager.getLogger(TestBase1.class);
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public TestBase1(){
		try 
		{
		prop = new Properties();
		FileInputStream ip=new FileInputStream("C:\\Users\\Kalyani\\eclipse-workspace\\AutomationEdgePortal\\src\\main\\java\\com\\ae\\qa\\config\\config.properties");
		prop.load(ip);	
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@BeforeSuite
	public static void setExtent(){
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/AEExtentReport.html");
		extent = new ExtentReports();
		htmlReporter.loadXMLConfig("C:\\Users\\Kalyani\\eclipse-workspace\\AutomationEdgePortal\\src\\main\\java\\com\\ae\\qa\\ExtentReportListener\\extent-config.xml");
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Organization Name", "AE");
		extent.setSystemInfo("Environment", "QA");
		 extent.setSystemInfo("Automation Tester", "Kalyani Gomkale");
		 htmlReporter.config().setEncoding("uft-8");
		 htmlReporter.config().setDocumentTitle("AE Automation Report "); 
		                // Name of the report
		 htmlReporter.config().setReportName("AE Test Result"); 
		                // Dark Theme
		 htmlReporter.config().setTheme(Theme.DARK); 
		 }
	//This method is to capture the screenshot and return the path of the screenshot.
	 public static String getScreenShot(WebDriver driver, String screenshotName) throws Exception {
	 String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
	 TakesScreenshot ts = (TakesScreenshot) driver;
	 File source = ts.getScreenshotAs(OutputType.FILE);
	 // after execution, you could see a folder "FailedTestsScreenshots" under src folder
	 String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + "{"+dateName + "}";
	 File finalDestination = new File(destination);
	 //FileUtils.copyFile(source, finalDestination);
	 FileHandler.copy(source, finalDestination);
	// FileUtils.copyFile(source, finalDestination);
	 return destination;
	 }

	
	@BeforeMethod
	public void initialization() {
		//ITestContext context,Method method
		log.debug("Execution started");
	//	extentTest = extent.createTest(method.getName());
		String browserName = prop.getProperty("browser");
		if(browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Kalyani\\eclipse-workspace\\webdrivertraining\\test\\resources\\chromedriver_81.exe");
			driver=new ChromeDriver();
		} 
	   else if(browserName.contentEquals("Firefox")) {
 			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Kalyani\\eclipse-workspace\\webdrivertraining\\test\\resources\\geckodriver-64bit.exe");
			 driver= new FirefoxDriver();
		} 
	   else {
			System.out.println("No browser value is given");
		}
		log.info("Browser started successfully");
		//Settings
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		//Launch the URL
		driver.get(prop.getProperty("url"));
		log.info("User is navigated to AE portal");
	}
	
	 @AfterMethod
	 public void getResult(ITestResult result) throws Exception{
	 if(result.getStatus() == ITestResult.FAILURE){
	 //MarkupHelper is used to display the output in different colors
     extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	 extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	 //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
	 //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method. 
	// String Scrnshot=TakesScreenshot.captuerScreenshot(driver,"TestCaseFailed");
	 String screenshotPath = getScreenShot(driver, result.getName());
	 //To add it in the extent report 
	//Assert.fail("Test Case Failed Snapshot is below " + extentTest.addScreenCaptureFromPath(screenshotPath);
	 extentTest.addScreenCaptureFromPath(screenshotPath);
	 }
	 
	 else if(result.getStatus() == ITestResult.SKIP){
	 extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.CYAN)); 
	 extentTest.skip(result.getThrowable());
	 }
	 
	 else if(result.getStatus() == ITestResult.SUCCESS)
	 {

	extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	 }
     driver.quit();

	 }
	 
	 @AfterTest
	 public void endReport() {
		 
	 extent.flush();
	
	 }
	


}
