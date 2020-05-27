package com.ae.qa.testcases;

import org.testng.annotations.Test;


import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.AuditLogsPage;

public class AuditLogsPageTest extends TestBase1{
	AuditLogsPage auditlogspage;
	
	public AuditLogsPageTest() {
		super();
	}

   @Test(priority=1)
	    public void validatedownloadingAuditLogs() throws Exception {
	    extentTest = extent.createTest("validatedownloadingAuditLogs","It will download logs");
	    auditlogspage=new AuditLogsPage();
	    auditlogspage.downloadingAuditLogs();
	    extentTest.log(extentTest.getStatus(), "Audit Logs downloading started successfully");
	  }
	@Test(priority=2)
	public void validatecheckColumnsInAuditLogs() throws Exception {
	    extentTest = extent.createTest("validatecheckColumnsInAuditLogs","It will check select all columns");
	    auditlogspage=new AuditLogsPage();
	    auditlogspage.checkColumnsInAuditLogs();
	    extentTest.log(extentTest.getStatus(), "Select All options checked and verified in table successfully");	
	}
	@Test(priority=3)
	public void validatedeselectAllInAuditLogs() throws Exception {
		 extentTest = extent.createTest("validatedeselectAllInAuditLogs","It will check select all columns");
		    auditlogspage=new AuditLogsPage();
		    auditlogspage.deselectAllInAuditLogs();
		    extentTest.log(extentTest.getStatus(), "Deselect All options checked and verified in table successfully");
	}
	@Test(priority=4,dependsOnMethods= {"validatedeselectAllInAuditLogs"})
	public void verifySpecificColumnTest() throws Exception {
		 extentTest = extent.createTest("verifySpecificColumnTest","It will check specific columns in the table");
		    auditlogspage=new AuditLogsPage();
		    auditlogspage.verifySpecificColumn();
		    extentTest.log(extentTest.getStatus(), "It will check specific columns in the tabl");
	}
	
}
