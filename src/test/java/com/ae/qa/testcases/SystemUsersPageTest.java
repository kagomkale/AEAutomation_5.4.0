package com.ae.qa.testcases;

import org.testng.annotations.Test;

import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.SystemUsersPage;
import com.ae.qa.pages.TenantUsersPage;

public class SystemUsersPageTest extends TestBase1 {
	SystemUsersPage systemuserspage;

	public SystemUsersPageTest() {
		super();
	}


  @Test (priority=1)
  public void creatingSystemAdminTest() throws Exception {
    extentTest = extent.createTest("creatingSystemAdminTest","It will create System Admin");
    systemuserspage=new SystemUsersPage();
    systemuserspage.creatingSystemAdmin("System","Abc101","SystemAbc101@gmail.com","System_Abc101","Pune@1234","Pune@1234");
	}
  
  @Test(priority=2)
  public void validatePasswordMismatch() throws Exception {
	 extentTest = extent.createTest("validatePasswordMismatch","It will give error for Password mismatch");
	 systemuserspage=new SystemUsersPage();
	 systemuserspage.creatingSystemAdminWithWrongPswd("System","Abc101","SystemAbc101@gmail.com","System_Abc101","Pune@123","Pune@1234");
	}
	@Test(priority=3)
	 public void validateEditSystemUsers() throws Exception {
	    extentTest = extent.createTest("ValidateEditSystemUsersTest","It will create System Admin");
	    systemuserspage=new SystemUsersPage();
	    systemuserspage.EditSystemUsers("System","Abc101","SystemAbc101@gmail.com","System_Abc101","Pune@1234","Pune@1234","SystemNewAdm102@gmail.com","Mumbai@123","Mumbai@123");
		}
  
}
  