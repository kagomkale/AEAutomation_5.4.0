package com.ae.qa.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase1;

public class Trail extends TestBase1{
	public LoginPage loginpage;
	public WebElements webelements=new WebElements();
	public Trail() {
		super();
	}
@Test
	public void trialmethod(String tName,String tDescription,String orgCode) throws Exception {
	extentTest = extent.createTest("validateAddNewTenant","It will add user");
	loginpage=new LoginPage();
		loginpage.login(prop.getProperty("username"),prop.getProperty("password")); 
		log.info("User log in Successfully");
		webelements.clickrefreshBtn();
	}
}
