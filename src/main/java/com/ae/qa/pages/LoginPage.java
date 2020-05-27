package com.ae.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase;
import com.ae.qa.base.TestBase1;

public class LoginPage extends TestBase1{
	public static WebDriverWait wait=new WebDriverWait(driver,120);
	
	//PageFactory
	@FindBy(xpath="//input[@id='uname']")
	static WebElement  username;
	@FindBy(xpath="//input[@id='pswd']")
	static WebElement  password;
	@FindBy(xpath="//button[@id='signin']")
	static WebElement signInBtn;
	@FindBy(xpath="//span[contains(text(),'Forgot')]")
	WebElement forgotPwsdLink;
	@FindBy(xpath="//title")
	WebElement pageTitle;
	@FindBy(xpath="//span[contains(text(),'Forgot Password')]")
	WebElement forgotpswdLink;
	@FindBy(xpath="//input[@id='userName']")
	WebElement resetUname;
	@FindBy(xpath="//button[@id='signin']")
	WebElement submitBtn;
	@FindBy(xpath="//input[@id='pswd']")
	WebElement newPswd;
	@FindBy(xpath="//input[@id='confirmPswd']")
	WebElement cnfPswd;
	@FindBy(xpath="//button[@id='reset']")
	WebElement resetBtn;
	@FindBy(xpath="//p")
	WebElement successMsg;
	@FindBy(xpath="//h2")
	WebElement titlePage;
	
	//initialize all this Object Repository
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	//Actions
	public String validateLogInPageTitle(){
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}
	//return type is object of TenantPage
	public void login(String un,String pswd) {
	username.sendKeys(un);
	password.sendKeys(pswd);
	wait.until(ExpectedConditions.visibilityOf(signInBtn));
	signInBtn.click();
	}
	
	public boolean ValidateForgotPswdLink() {
		boolean flag=false;
		if(forgotpswdLink.isDisplayed()) {
			String Actual_field=forgotpswdLink.getText();
			System.out.println(Actual_field);
		
			if(Actual_field.contentEquals("Forgot Password")) {
				flag=true;
			} else {
				flag=false;
			}
		   } 
		return flag;
	}
		public void ValidateLoginWithResetPswd() {
		forgotpswdLink.click();
		resetUname.sendKeys(prop.getProperty("username"));
		submitBtn.click();
	}
		public void ValidateResetPswd() throws Exception {
			forgotpswdLink.click();
			resetUname.sendKeys(prop.getProperty("username"));
			submitBtn.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			for(int i=1;i<=3;i++) {
				WebElement Ques=driver.findElement(By.xpath("//fieldset["+i+"]/label"));
				String verify_Ques=Ques.getText();
			//	select.selectByVisibleText(prop.getProperty("IQue"+i));
				if(verify_Ques.equalsIgnoreCase(prop.getProperty("IQue"+i))) {
				Thread.sleep(2000);
				WebElement select_Ans= driver.findElement(By.id("ans"+i+""));
				select_Ans.sendKeys(prop.getProperty("IAns"+i));
				Thread.sleep(2000);
				}
			}
			submitBtn.click();
			newPswd.sendKeys(prop.getProperty("R_password"));
			cnfPswd.sendKeys(prop.getProperty("R_password"));
			resetBtn.click();
			String actual_msg=successMsg.getText();
			System.out.println("Actual Message after restting password:"+actual_msg);
			Assert.assertEquals(actual_msg, "Password set successfully");
		}
		public void ValidateLoginWithResetPswd1() {
		LoginPage loginpage=new LoginPage();
		loginpage.login(prop.getProperty("username"),prop.getProperty("R_password"));
	    String tenantsPageTitle=titlePage.getText();
	    System.out.println("User is Login with new paswword and navigated to "+tenantsPageTitle+" page.");
	    Assert.assertEquals(tenantsPageTitle, "Tenants","User is not navigated to tenants page");
		
		}
}
