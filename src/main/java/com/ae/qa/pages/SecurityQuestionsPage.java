package com.ae.qa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase1;

public class SecurityQuestionsPage extends TestBase1{
	public WebDriverWait wait=new WebDriverWait(driver,250);
	public WebElements webelements=new WebElements();
	public LoginPage loginpage=new LoginPage();
	
@FindBy(xpath="//button[@name='save' and @type='submit']")
WebElement saveBtn;
@FindBy(xpath="//span[contains(text(),'Settings')]")
WebElement SettingsTab;
@FindBy(xpath="//a[contains(text(),'Security Questions')]")
WebElement securityQuesTab;
@FindBy(xpath="//input[@id='pswd']")
WebElement pswd;
@FindBy(xpath="//div/p[contains(text(),'Security questions updated successfully')]")
WebElement success_msg;
@FindBy(xpath="//div/p[contains(text(),'Security questions set successfully')]")
WebElement success_Setmsg;
@FindBy(xpath="//button[@name='skip']")
WebElement skipBtn;
@FindBy(xpath="//div/h2[contains(text(),'Tenants')]")
WebElement tenantPageTitle;
	
	public SecurityQuestionsPage() {
		PageFactory.initElements(driver, this);
	}

	public void validateSecurityQues() throws Exception {
		loginpage.login(prop.getProperty("username_woSecurity"),prop.getProperty("password_woSecurity"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		for(int i=1;i<=3;i++) {
		Select select=new Select(driver.findElement(By.xpath("//select[@id='que"+i+"']")));
		select.selectByVisibleText(prop.getProperty("IQue"+i));
		Thread.sleep(2000);
		 WebElement select_Ans= driver.findElement(By.id("ans"+i+""));
	     select_Ans.sendKeys(prop.getProperty("IAns"+i));
	     Thread.sleep(2000);
		}
		saveBtn.click();
		String actual_successMsg=success_Setmsg.getText();
		System.out.println("Actual message:"+actual_successMsg);
		Assert.assertEquals(actual_successMsg, "Security questions set successfully");
		
	}

	public void validateUpdatingSecurityQues() throws Exception {
	//Click Users Tab
	loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
	driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.visibilityOf(SettingsTab));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", SettingsTab);
	securityQuesTab.click();
	driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		for(int i=1;i<=3;i++) {
			Select select=new Select(driver.findElement(By.xpath("//select[@id='que"+i+"']")));
			select.selectByVisibleText(prop.getProperty("IUQue"+i));
			Thread.sleep(2000);
			WebElement select_Ans= driver.findElement(By.id("ans"+i+""));
			select_Ans.sendKeys(prop.getProperty("IUAns"+i));
			Thread.sleep(2000);
		}
		pswd.sendKeys(prop.getProperty("password"));
	saveBtn.click();
	//Thread.sleep(20000);
	String actual_successMsg=success_msg.getText();
	System.out.println("Actual message:"+actual_successMsg);
	Assert.assertEquals(actual_successMsg, "Security questions updated successfully");
	}
	
	public void validateSkipQues() {
		loginpage.login(prop.getProperty("username_woSecurity"),prop.getProperty("password_woSecurity"));
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		skipBtn.click();
		String actual_pageTitle=tenantPageTitle.getText();
		System.out.println("User navigated to "+actual_pageTitle+"Tab");
		Assert.assertEquals(actual_pageTitle, "Tenants","User is not navigated to tenants tab");	
	}
	public void validateSkipThenSetQues() throws Exception {
		loginpage.login(prop.getProperty("username_woSecurity1"),prop.getProperty("password_woSecurity1"));
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(2000);
		skipBtn.click();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(SettingsTab));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", SettingsTab);
		securityQuesTab.click();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			for(int i=1;i<=3;i++) {
				Select select=new Select(driver.findElement(By.xpath("//select[@id='que"+i+"']")));
				select.selectByVisibleText(prop.getProperty("IQue"+i));
				Thread.sleep(2000);
				WebElement select_Ans= driver.findElement(By.id("ans"+i+""));
				select_Ans.sendKeys(prop.getProperty("IAns"+i));
				Thread.sleep(2000);
			}
			//pswd.sendKeys(prop.getProperty("password"));
		saveBtn.click();
		//Thread.sleep(10000);
		String actual_successMsg=success_Setmsg.getText();
		System.out.println("Actual message:"+actual_successMsg);
		Assert.assertEquals(actual_successMsg, "Security questions set successfully");
		
	}
}
