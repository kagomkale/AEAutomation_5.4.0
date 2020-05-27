package com.ae.qa.testcases;

import org.testng.annotations.Test;

import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.LoginPage;
import com.ae.qa.pages.SystemSettingsPage;
import com.aventstack.extentreports.Status;


public class SystemSettingsPageTest extends TestBase1 {
	SystemSettingsPage systemsettingspage;

	public SystemSettingsPageTest() {
			super();
		}
	
	
	@Test(priority=1, alwaysRun=true, groups= {"TenantAdminPrerequisite"})
	 public void validateServerUrlTest() throws Exception {
		extentTest = extent.createTest("validateServerUrlTest","This will configure server Url");
		systemsettingspage=new SystemSettingsPage();
		systemsettingspage.validateServerUrl();
	  }
	@Test(priority=2, alwaysRun=true, groups= {"TenantAdminPrerequisite"})
	 public void validateEditCleanUpRequest() throws Exception {
		extentTest = extent.createTest("validateEditCleanUpRequest","This will edit cleaning up Request Hours");
		systemsettingspage=new SystemSettingsPage();
		systemsettingspage.EditCleanUpRequest(2);
	  }
	@Test(priority=3, alwaysRun=true, groups= {"TenantAdminPrerequisite"})
	 public void ValidateDRServerUrlTest() throws Exception {
		extentTest = extent.createTest("ValidateDRServerUrlTest","This will edit cleaning up Request Hours");
		systemsettingspage=new SystemSettingsPage();
		systemsettingspage.ValidateDRServerUrl(2);
	  }
	
	

}
