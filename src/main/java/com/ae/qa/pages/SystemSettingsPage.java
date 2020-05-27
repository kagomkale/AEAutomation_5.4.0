package com.ae.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase1;
import com.aventstack.extentreports.model.Log;

public class SystemSettingsPage extends TestBase1 {
	public LoginPage loginpage=new LoginPage();
	public static WebDriverWait wait=new WebDriverWait(driver,120);
	@FindBy(xpath="//span[(text()='Settings')]")
	WebElement settingsTab;
	@FindBy(xpath="//button[@name='add-new']")
	WebElement configureBtn;
	@FindBy(xpath="//button[@name='dropdown-selector']")
	WebElement configureDropdown;
	@FindBy(xpath="//button[@aria-expanded='false']/b")
	WebElement httpProtocolBtn;
	@FindBy(xpath="//span[(text()='http://')]")
	WebElement httpOption;
	@FindBy(xpath="//span[(text()='https://')]")
	WebElement httpsOption;
	@FindBy(xpath="//input[@id='serverUrl']")
	WebElement serverUrl;
	@FindBy(xpath="//input[@id='drServerUrl']")
	WebElement drServerUrl;
	@FindBy(id="cleanupOldReqHours")
	WebElement cleanUpRequest;
	@FindBy(xpath="//button[@name='verify']")
	WebElement verifyUrlBtn;
	@FindBy(xpath="//button[@name='save' and @type='button']")
	WebElement saveBtn;
	@FindBy(xpath="//button[@name='cancel' and @type='button']")
	WebElement cancelBtn;
	@FindBy(xpath="//div[@class='alert alert-success ae-alert ae-success-alert place-alert']")
	WebElement SuccessMsgBox;
	@FindBy(xpath="//div[@class='alert ae-alert place-alert alert-danger ae-danger-alert']")
	WebElement failMsgBox;
	@FindBy(xpath="//span[contains(text(),'DR Site')]")
	WebElement drSite;
	
	public SystemSettingsPage() {
	//	this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void validateServerUrl() throws Exception {
		//issue here is we cannot clear the text in url as well as hours
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		wait.until(ExpectedConditions.visibilityOf(settingsTab));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", settingsTab);
		System.out.println("Settings tab clicked");
		wait.until(ExpectedConditions.visibilityOf(configureBtn));
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", configureBtn);
		System.out.println("configure");
		log.info("Started configuring server url");
		httpProtocolBtn.click();
		Thread.sleep(1000);
		httpOption.click();
		for(int i = 0; i < 15; i++){
			serverUrl.sendKeys(Keys.BACK_SPACE);
			}
			serverUrl.sendKeys(prop.getProperty("serverURL"));
		
		Thread.sleep(2000);
	 if(verifyUrlBtn.isDisplayed()) { 
			 verifyUrlBtn.click(); 
			 log.info("Verify button is clicked");
			 wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
			 saveBtn.click();
			 Thread.sleep(1000);
			 String actual_success_msg=driver.findElement(By.xpath("//div/p[contains(text(),'System settings saved successfully')]")).getText();
			 String expected_success_msg="System settings saved successfully";
			 System.out.println("actual success msg is: " +actual_success_msg);
			 Assert.assertEquals(actual_success_msg, expected_success_msg,"System settings are not configured.");
			 extentTest.log(extentTest.getStatus(), "System Settings saved");
	 }
	 else {
		 System.out.println("System settings not configured");
		 extentTest.fail("System settings not configured");
	 }
	}
	 public void EditCleanUpRequest(int cleanUpRequestHour) throws Exception {
			loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
			wait.until(ExpectedConditions.visibilityOf(settingsTab));
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", settingsTab);
			System.out.println("Settings tab clicked");
			wait.until(ExpectedConditions.elementToBeClickable(configureBtn));
			JavascriptExecutor js1=(JavascriptExecutor)driver;
			js1.executeScript("arguments[0].click();", configureBtn);
			System.out.println("configure");
			log.info("Started configuring server url");
			httpProtocolBtn.click();
			Thread.sleep(1000);
			httpOption.click();
			for(int i = 0; i < 15; i++){
				serverUrl.sendKeys(Keys.BACK_SPACE);
				}
				serverUrl.sendKeys(prop.getProperty("serverURL"));
			
			Thread.sleep(2000);
			for(int i = 0; i < 5; i++){
			cleanUpRequest.sendKeys(Keys.BACK_SPACE);
			}
			cleanUpRequest.sendKeys(String.valueOf(cleanUpRequestHour));
		
		
		 if(verifyUrlBtn.isDisplayed()) { 
				 verifyUrlBtn.click(); 
				 log.info("Verify button is clicked");
				 wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
				 saveBtn.click();
				 Thread.sleep(1000);
				 String actual_success_msg=driver.findElement(By.xpath("//div/p[contains(text(),'System settings saved successfully')]")).getText();
				 String expected_success_msg="System settings saved successfully";
				 System.out.println("actual success msg is: " +actual_success_msg);
				 Assert.assertEquals(actual_success_msg, expected_success_msg,"System settings are not configured.");
				 extentTest.log(extentTest.getStatus(), "System Settings saved");
		 }
		 else {
			 System.out.println("System settings not configured");
			 extentTest.fail("System settings not configured");
		 }
	}
	 public void ValidateDRServerUrl(int cleanUpRequestHour) throws Exception {
			loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
			wait.until(ExpectedConditions.visibilityOf(settingsTab));
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", settingsTab);
			System.out.println("Settings tab clicked");
			wait.until(ExpectedConditions.elementToBeClickable(configureDropdown));
			JavascriptExecutor js1=(JavascriptExecutor)driver;
			js1.executeScript("arguments[0].click();", configureDropdown);
			System.out.println("configure dropdown selected");
			drSite.click();
		    log.info("Started configuring DR server url");
			httpProtocolBtn.click();
			Thread.sleep(1000);
			httpOption.click();
			for(int i = 0; i < 15; i++){
				drServerUrl.sendKeys(Keys.BACK_SPACE);
				}
				drServerUrl.sendKeys(prop.getProperty("DRServerURL"));
			
			Thread.sleep(2000);
			for(int i = 0; i < 5; i++){
			cleanUpRequest.sendKeys(Keys.BACK_SPACE);
			}
			cleanUpRequest.sendKeys(String.valueOf(cleanUpRequestHour));
		 if(verifyUrlBtn.isDisplayed()) { 
				 verifyUrlBtn.click(); 
				 log.info("Verify button is clicked");
				 wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
				 saveBtn.click();
				 Thread.sleep(1000);
				 String actual_success_msg=driver.findElement(By.xpath("//div/p[contains(text(),'System settings saved successfully')]")).getText();
				 String expected_success_msg="System settings saved successfully";
				 System.out.println("actual success msg is: " +actual_success_msg);
				 Assert.assertEquals(actual_success_msg, expected_success_msg,"System settings are not configured.");
				 extentTest.log(extentTest.getStatus(), "System Settings saved");
		 }
		 else {
			 System.out.println("System settings not configured");
			 extentTest.fail("System settings not configured");
		 }
	}
}