package com.ae.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.LoginPage;
import com.ae.qa.pages.TenantsPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginPageTest extends TestBase1{
LoginPage loginpage;

	//WebDriver driver;
	//to initialize the prop files in TestBase class
   public LoginPageTest() {
		super();
   }

	
	/* @BeforeMethod
	  public void setUp() { 
	   initialization(); 
	   //all the methods in the login page class are non-static that's why object creation is important.
	   loginpage=new LoginPage(); 
	   }
	 
   
	@Test(priority=1)
	public void loginPageTitleTest() {
	extent.createTest("loginPageTitleTest","It will validate title of AE portal");
	loginpage=new LoginPage();
	String actual_Title = loginpage.validateLogInPageTitle();
	System.out.println(actual_Title);
	Assert.assertEquals(actual_Title, "AutomationEdge");
	//log.info("AE title validated");
    extentTest.log(extentTest.getStatus(), "Title validation");
		
	}
	
	@Test(priority=2) 
	public void logInTest() { 
	extent.createTest("logInTest","It will validate login of user");  
	loginpage=new LoginPage();
	loginpage.login(prop.getProperty("username"),prop.getProperty("password")); 
	//log.info("User Login in AE successfully"); 
	extentTest.log(extentTest.getStatus(), "User sign-in");
	}*/
   @Test(priority=1)
   public void validateForgotPswdTest() {
	   extentTest = extent.createTest("validateForgotPswdTest","This will validate forgot password link"); 
	   loginpage=new LoginPage();
	   boolean flag=loginpage.ValidateForgotPswdLink();
	   if(flag==true) {
		   System.out.println("Passed");
		   extentTest.log(Status.PASS, "passed");
		//  Assert.assertEquals(true, true);
	   } else {
		   System.out.println("Fail");
		   extentTest.log(Status.FAIL, "failed");
	   } 
   }
   @Test(priority=2)
   public void ValidateResetPswdTest() throws Exception {
	   extentTest = extent.createTest("ValidateResetPswd","This will reset password after answering security ques"); 
	   loginpage=new LoginPage();
		loginpage.ValidateResetPswd();
		extentTest.log(extentTest.getStatus(), "Reset password after answering security ques");
   }
   @Test(priority=3, dependsOnMethods= {"ValidateResetPswdTest"})
   public void ValidateLoginWithResetPswdTest() throws Exception {
	   extentTest = extent.createTest("ValidateLoginWithResetPswdTest","This will reset password after answering security ques"); 
	   loginpage=new LoginPage();
		loginpage.ValidateLoginWithResetPswd1();
		extentTest.log(extentTest.getStatus(), "Reset password after answering security ques");
   }
}