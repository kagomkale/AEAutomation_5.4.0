package com.ae.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.pages.LoginPage;
import com.ae.qa.pages.LoginPage1;

public class LoginPageTest1 extends TestBase{
	LoginPage1 loginpage1;

	//WebDriver driver;
	//to initialize the prop files in TestBase class
	//super keyword to refer immediate parent class object
   public LoginPageTest1() {
		super();
   }

	
	 @BeforeMethod
	  public void setUp() { 
	   initialization(); 
	   //all the methods in the login page class are non-static that's why object creation is important.
	   loginpage1=new LoginPage1(); 
	   }
	 
   
	
	@Test 
	public void logInTest() { 
	loginpage1.login(prop.getProperty("username"),prop.getProperty("password")); 
	log.info("User Login in AE successfully"); 
	
	}
	 
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}


}
