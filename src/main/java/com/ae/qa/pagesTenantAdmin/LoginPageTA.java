package com.ae.qa.pagesTenantAdmin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ae.qa.base.TestBase1;
import com.ae.qa.base.TestBase2;
import com.ae.qa.pages.TenantsPage;

public class LoginPageTA extends TestBase1{
	public static WebDriverWait wait=new WebDriverWait(driver,120);
	//PageFactory
		@FindBy(xpath="//input[@id='uname']")
		WebElement username;
		@FindBy(xpath="//input[@id='pswd']")
		WebElement password;
		@FindBy(xpath="//button[@id='signin']")
		WebElement signInBtn;
		@FindBy(xpath="//span[contains(text(),'Forgot')]")
		WebElement forgotPwsdLink;
		@FindBy(xpath="//title")
		WebElement pageTitle;
		
		//initialize all this Object Repositary
		public LoginPageTA() {
			PageFactory.initElements(driver, this);
		}

		//Actions
		//return type is object of TenantPage
		public void login(String un,String pswd) {
			username.sendKeys(un);
			password.sendKeys(pswd);
			wait.until(ExpectedConditions.visibilityOf(signInBtn));
			signInBtn.click();
			
			
			}
		

}
