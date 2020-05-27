package com.ae.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase;
import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.LoginPage;
import com.ae.qa.pages.TenantsPage;
import com.ae.qa.util.TestUtil;

public class TenantsPageTest extends TestBase1 {
    LoginPage loginpage;
    TenantsPage tenantspage;
 //   String sheetName="TenantsDetails";

	public TenantsPageTest() {
		super();
	}
	
	/* We will use this if we need to extract data of Tenant from excel
	 * @DataProvider public Object[][] getTenantsTestData() { Object
	 * data[][]=TestUtil.getTestData(sheetName); return data; }
	 * 
	 * @Test(priority=2, dataProvider="getTenantsTestData") public void
	 * validateAddNewTenant(String tname,String tdescription, String torgcode) {
	 * tenantspage.addNewTenants(tname,tdescription,torgcode);
	 * log.info("New Tenant created successfully");
	 * 
	 * }
	 */
	
	//Give same data to both test so that in first one it will create user and in second flow it gives error as duplicate orgcode 
		@Test(priority=1)
	 public void validateAddNewTenant() throws Exception {
	     extentTest = extent.createTest("validateAddNewTenant","It will add user");
		 tenantspage=new TenantsPage();
		 tenantspage.addNewTenants("Tenant_User999 "+tenantspage.createUniqueName(),"Tenant999","TU999");
		// tenantspage.addNewTenants("bgd1"+tenantspage.createUniqueName(),"bgd_client1","bgd1");
	    extentTest.log(extentTest.getStatus(), "User added successfully");
	  
	  }
	 @Test (priority=2, alwaysRun=true)
	 public void validateaddNewTenantsWithDuplicateOrgCode() throws Exception {
	     extentTest = extent.createTest("validateAddNewTenantsWithDuplicateOrgCode","It will verify if we can add user with duplicate code");
		 tenantspage=new TenantsPage();
		 tenantspage.addNewTenantsWithDuplicateOrgCode("Tenant_User999","Tenant999","TU999"); 
	  }
		@Test(priority=1)
		public void verifyLicenseInfoTest() throws Exception {
	     extentTest = extent.createTest("verifyLicenseInfoTest","It validates the license status of Sysadmin");
		 tenantspage=new TenantsPage();
		 tenantspage.verifyLicenseInfo();
	     extentTest.log(extentTest.getStatus(), "It validates the license status of Sysadmin");
	  
	  }
}
