package com.ae.qa.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ae.qa.base.TestBase1;
import com.ae.qa.pages.SecurityQuestionsPage;
import com.ae.qa.util.TestUtil;


public class SecurityQuestionsPageTest extends TestBase1 {
	SecurityQuestionsPage securityquestionspage;

	public SecurityQuestionsPageTest() {
		super();
	}
	/*@DataProvider()
	public Object[][] getSecurityQuestionData() {
		Object data[][]= TestUtil.getTestData("SetSecurityQues");
		return data;
	}*/
	
   @Test(priority=2)
  public void validateSecurityQuesTest() throws Exception {
    extentTest = extent.createTest("validateSecurityQuesTest","It will set security questions first time");
    securityquestionspage=new SecurityQuestionsPage();
    securityquestionspage.validateSecurityQues();
	}
	@Test(priority=3)
	  public void validateUpdatingSecurityQuesTest() throws Exception {
	    extentTest = extent.createTest("validateUpdatingSecurityQuesTest","It will update the security questions");
	    securityquestionspage=new SecurityQuestionsPage();
	    securityquestionspage.validateUpdatingSecurityQues();
	    extentTest.log(extentTest.getStatus(),"It will update the security questions");
		}
	@Test(priority=1)
	  public void validateSkipQuesTest() throws Exception {
	    extentTest = extent.createTest("validateSkipQuesTest","It will skip the security questions");
	    securityquestionspage=new SecurityQuestionsPage();
	    securityquestionspage.validateSkipQues();
	    extentTest.log(extentTest.getStatus(),"It will skip the security questions");
		}
	@Test
	  public void validateSkipThenSetQuesTest() throws Exception {
	    extentTest = extent.createTest("validateSkipThenSetQuesTest","It will skip first the security questions and then set");
	    securityquestionspage=new SecurityQuestionsPage();
	    securityquestionspage.validateSkipThenSetQues();
	    extentTest.log(extentTest.getStatus(),"It will first skip the security questions and then set");
		}

}
