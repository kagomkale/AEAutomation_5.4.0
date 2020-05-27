package com.ae.qa.testcases;

import org.testng.annotations.Test;

import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.InformationPage;
import com.ae.qa.pages.TenantUsersPage;

public class InformationPageTest extends TestBase1 {
	InformationPage informationpage;

	public InformationPageTest() {
		super();
	}

	@Test (priority=1, alwaysRun=true)
	 public void validateLastLoginTest() throws Exception {
     extentTest = extent.createTest("validateLastLoginTest","It should check Last Login details");
	 informationpage=new InformationPage();
	 informationpage.validateLastLogin();
 }
    @Test (priority=2, alwaysRun=true)
     public void validateViewProfileTest() throws Exception {
     extentTest = extent.createTest("validateViewProfileTest","It should check Profile Details");
	 informationpage=new InformationPage();
	 informationpage.validateViewProfile();
	 extentTest.log(extentTest.getStatus(), "Profile Details");
 }
    @Test (priority=3, alwaysRun=true)
     public void validateAboutTest() throws Exception {
     extentTest = extent.createTest("validateAboutTest","About AE details");
	 informationpage=new InformationPage();
	 informationpage.validateAbout();
	 extentTest.log(extentTest.getStatus(), "About AE Details");
 }
	@Test (priority=4, alwaysRun=true)
	public void validateChangePasswordTest() throws Exception {
	   extentTest = extent.createTest("validateChangePasswordTest","Change password of user");
		 informationpage=new InformationPage();
		 informationpage.validateChangePassword();
		 extentTest.log(extentTest.getStatus(), "Change password of user");
	 }
}
