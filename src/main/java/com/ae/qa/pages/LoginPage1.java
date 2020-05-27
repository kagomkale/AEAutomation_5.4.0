package com.ae.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ae.qa.base.TestBase;

public class LoginPage1 extends TestBase{
	public static WebDriverWait wait=new WebDriverWait(driver,120);
	//PageFactory
	//locating techniques
	//name,class,tagname,css selector,linkedtext,partial link text,xpath
	//driver.findElement(By.xpath=//input[@id='uname']")
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
	
	//initialize all this Object Repository
	public LoginPage1() {
		PageFactory.initElements(driver, this);
	}

	//Actions

	//return type is object of TenantPage
	public void login(String un,String pswd) {
	username.sendKeys(un);
	//username.sendKeys("AshnaAjmire");
	password.sendKeys(pswd);
	//password.sendKeys("Pune@1234")
	wait.until(ExpectedConditions.visibilityOf(signInBtn));
	signInBtn.click();
	
	
	}

}
