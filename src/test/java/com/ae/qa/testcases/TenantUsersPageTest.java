package com.ae.qa.testcases;

import org.testng.annotations.Test;

import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.SystemSettingsPage;
import com.ae.qa.pages.TenantUsersPage;

public class TenantUsersPageTest extends TestBase1{
	TenantUsersPage tenantuserspage;

	public TenantUsersPageTest() {
		super();
	}


@Test (priority=1, alwaysRun=true, groups= {"TenantAdminPrerequisite"})
 public void creatingTenantUserTest() throws Exception {
    extentTest = extent.createTest("creatingTenantAdminTest","It will create Tenant Admin");
	tenantuserspage=new TenantUsersPage();
	tenantuserspage.creatingTenantUser("106","Xyz","Abc","XyzAbc@gmail.com","Xyz_Abc","Pune@1234","Pune@1234","Tenant Admin");
	//tenantuserspage.creatingTenantAdmin("2", "Activity", "Monitor0", "ActivityMonitor0@gmail.com", "Activity_Monitor0", "Pune@1234", "Pune@1234","Activity Monitor");
	extentTest.log(extentTest.getStatus(), "It will add new Tenant User data");
  
  }
@Test (priority=1, alwaysRun=true, groups= {"TenantAdminPrerequisite"})
public void EditTenantUserTest() throws Exception {
   extentTest = extent.createTest("editingTenantUserTest","It will create Tenant Admin");
	tenantuserspage=new TenantUsersPage();
	tenantuserspage.EditTenantUser("106","Xyz","Abc","XyzAbc@gmail.com","Xyz_Abc","Pune@1234","Pune@1234","TenantXYZ101","TenantXYZ@gmail.com","Mumbai@123","Mumbai@123");
	//tenantuserspage.creatingTenantAdmin("2", "Activity", "Monitor0", "ActivityMonitor0@gmail.com", "Activity_Monitor0", "Pune@1234", "Pune@1234","Activity Monitor");
	extentTest.log(extentTest.getStatus(), "It will edit Tenant User details");
 
 }

}
