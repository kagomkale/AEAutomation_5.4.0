package com.ae.qa.pages;

import java.util.Iterator;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.Reporter;
import org.testng.Assert;

import com.ae.qa.base.TestBase1;

public class TenantUsersPage extends TestBase1 {
	public WebDriverWait wait=new WebDriverWait(driver,120);
	public WebElements webelements=new WebElements();
	public LoginPage loginpage=new LoginPage();
	
	@FindBy(xpath="//span[text()='Users']")
	WebElement usersTab;
	@FindBy(xpath="//a[text()='Tenant Users']")
	WebElement tenantUsersTab;
	@FindBy(xpath="//button[@name='add-new']")
	WebElement addBtn;
	@FindBy(xpath="//select[@id='tenantOrgCode']")
	WebElement tenantdropdown;
	@FindBy(xpath="//input[@id='fname']")
	WebElement fName;
	@FindBy(xpath="//input[@id='lname']")
	WebElement lName;
	@FindBy(xpath="//input[@id='useremail']")
	WebElement userMail;
	@FindBy(xpath="//input[@id='username']")
	WebElement userName;
	@FindBy(xpath="//input[@id='pswd']")
	WebElement pswd;
	@FindBy(xpath="//input[@id='confirmPswd']")
	WebElement confirmPswd;
	@FindBy(xpath="//select[@id='role']")
	WebElement roledropdown;
	@FindBy(xpath="//button[@name='submit']")
	WebElement createBtn;
	@FindBy(xpath="//span[@class='fa fa-refresh']")
	WebElement refreshBtn;
	@FindBy(xpath="//select[@id='pageSize'][1]")
	WebElement pageNumber;
	@FindBy(xpath="//span[@id='edit-67']")
	WebElement editBtn;
	@FindBy(xpath="//button[@name='save' and @type='submit']")
	WebElement saveBtn;
	@FindBy(xpath="//div/p[contains(text(),'User updated successfully')]")
	WebElement editUserMsg;
	public TenantUsersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void creatingTenantUser(String tenantOrgCode, String FName, String LName,String UserMail,String UserName,String Pswd, String ConfirmPswd, String RoleName) throws Exception {
		//Click Users Tab
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		wait.until(ExpectedConditions.visibilityOf(usersTab));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", usersTab);
		//Click TenantUsers Tab
		wait.until(ExpectedConditions.visibilityOf(tenantUsersTab));
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", tenantUsersTab);
		//click add new
		wait.until(ExpectedConditions.visibilityOf(addBtn));
		JavascriptExecutor js2=(JavascriptExecutor)driver;
		js2.executeScript("arguments[0].click();", addBtn);
		log.info("started creating new Tenant");
		//Start form
		//Locating the select dropdown for Tenant
		wait.until(ExpectedConditions.visibilityOf(tenantdropdown));
		JavascriptExecutor js3=(JavascriptExecutor)driver;
		js3.executeScript("window.scrollBy(0,600);", tenantdropdown);
		Select tenant=new Select(tenantdropdown);
		tenant.selectByValue(tenantOrgCode);
		fName.sendKeys(FName);
		Thread.sleep(1000);
		lName.sendKeys(LName);
		Thread.sleep(1000);
		userMail.sendKeys(UserMail);
		Thread.sleep(1000);
		userName.sendKeys(UserName);
		Thread.sleep(1000);
		pswd.sendKeys(Pswd);
		Thread.sleep(1000);
		confirmPswd.sendKeys(ConfirmPswd);
		//Thread.sleep(1000);
		//role dropdown
		roledropdown.click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	    Select select=new Select(roledropdown);
	    select.selectByVisibleText(RoleName);
	    //create button
		JavascriptExecutor js5=(JavascriptExecutor)driver;
		js5.executeScript("arguments[0].click();", createBtn);
		log.info("User is created successfully");
		Thread.sleep(6000);
		webelements.clickrefreshBtn();

		webelements.selectPageSize("50");
	    driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	    //verify whether user is present in table or not
    	for(int i=0;i<=3;i++) {
			String actual_UserName=driver.findElement(By.xpath("//table/tr/td/div[@title='"+UserName+"']")).getText();
			String expected_UserName=UserName;
			System.out.println("Actual Username:"+actual_UserName);
			System.out.println("Expected Username:"+expected_UserName);
		    Assert.assertEquals(actual_UserName, expected_UserName,"New user can not added in list");
			log.info("New user is verified and present in the webtable");
			break;
	   }	
   }
	public void EditTenantUser(String tenantOrgCode, String FName, String LName,String UserMail,String UserName,String Pswd, String ConfirmPswd, String RoleName, String NewUserMail, String NewPswd, String NewConfirmPswd) throws Exception 
	{
		//Click Users Tab
				loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
				wait.until(ExpectedConditions.visibilityOf(usersTab));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", usersTab);
				//Click TenantUsers Tab
				wait.until(ExpectedConditions.visibilityOf(tenantUsersTab));
				JavascriptExecutor js1=(JavascriptExecutor)driver;
				js1.executeScript("arguments[0].click();", tenantUsersTab);
				//click add new
				wait.until(ExpectedConditions.visibilityOf(addBtn));
				JavascriptExecutor js2=(JavascriptExecutor)driver;
				js2.executeScript("arguments[0].click();", addBtn);
				log.info("started creating new Tenant");
				//Start form
				//Locating the select dropdown for Tenant
				wait.until(ExpectedConditions.visibilityOf(tenantdropdown));
				JavascriptExecutor js3=(JavascriptExecutor)driver;
				js3.executeScript("window.scrollBy(0,600);", tenantdropdown);
				Select tenant=new Select(tenantdropdown);
				tenant.selectByValue(tenantOrgCode);
				fName.sendKeys(FName);
				Thread.sleep(1000);
				lName.sendKeys(LName);
				Thread.sleep(1000);
				userMail.sendKeys(UserMail);
				Thread.sleep(1000);
				userName.sendKeys(UserName);
				Thread.sleep(1000);
				pswd.sendKeys(Pswd);
				Thread.sleep(1000);
				confirmPswd.sendKeys(ConfirmPswd);
				//Thread.sleep(1000);
				//role dropdown
				roledropdown.click();
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			    Select select=new Select(roledropdown);
			    select.selectByVisibleText(RoleName);
			    //create button
			    createBtn.click();
			//	JavascriptExecutor js5=(JavascriptExecutor)driver;
				//js5.executeScript("arguments[0].click();", createBtn);
				log.info("User is created successfully");
			    driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
			    //verify whether user is present in table or not
		    	for(int i=0;i<=3;i++) {
					String actual_UserName=driver.findElement(By.xpath("//table/tr/td/div[@title='"+UserName+"']")).getText();
					String expected_UserName=UserName;
					System.out.println("Actual Username:"+actual_UserName);
					System.out.println("Expected Username:"+expected_UserName);
				    Assert.assertEquals(actual_UserName, expected_UserName,"New user can not added in list");
					log.info("New user is verified and present in the webtable");
					break;
			   }	
		    	editBtn.click();
		    	for(int i = 0; i < 30; i++){
					userMail.sendKeys(Keys.BACK_SPACE);
					}
				userMail.sendKeys(NewUserMail);
				for(int i = 0; i < 30; i++){
					pswd.sendKeys(Keys.BACK_SPACE);
					}
				pswd.sendKeys(NewPswd);
				for(int i = 0; i < 30; i++){
					confirmPswd.sendKeys(Keys.BACK_SPACE);
					}
				confirmPswd.sendKeys(NewConfirmPswd);
				saveBtn.click();
				String actual_EditUserMsg=editUserMsg.getText();
				String expected_EditUserMsg="User updated successfully";
				System.out.println("Actual Username:"+actual_EditUserMsg);
				System.out.println("Expected Username:"+expected_EditUserMsg);
			    Assert.assertEquals(actual_EditUserMsg, expected_EditUserMsg,"Tenant User details not edited successfully");
				log.info("Tenant User details got edited.");
	}
}
