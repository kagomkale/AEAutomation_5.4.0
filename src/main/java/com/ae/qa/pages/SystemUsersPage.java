package com.ae.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase1;

public class SystemUsersPage extends TestBase1{
	public WebDriverWait wait=new WebDriverWait(driver,150);
	public WebElements webelements=new WebElements();
	public LoginPage loginpage=new LoginPage();
	
	@FindBy(xpath="//span[text()='Users']")
	WebElement usersTab;
	@FindBy(xpath="//a[text()='System Users']")
	WebElement systemUsersTab;
	@FindBy(xpath="//button[@name='add-new']")
	WebElement addBtn;
	@FindBy(xpath="//select[@id='tenantOrgCode']")
	WebElement tenantdrpdown;
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
	@FindBy(xpath="//div[contains(text(),'Passwords Mismatch!')]")
	WebElement confirmationError;
	@FindBy(xpath="//button[@name='submit']/span")
	WebElement createBtn;
	@FindBy(xpath="//span[@class='fa fa-refresh']")
	WebElement refreshBtn;
	@FindBy(xpath="//select[@id='pageSize'][1]")
	WebElement pageNumber;
	@FindBy(xpath="//button[@name='save' and @type='submit']")
	WebElement saveBtn;
	@FindBy(xpath="//div/p[contains(text(),'User updated successfully')]")
	WebElement editUserMsg;
	
	public SystemUsersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void creatingSystemAdmin( String FName, String LName,String UserMail,String UserName,String Pswd, String ConfirmPswd) throws Exception {
		//Click Users Tab
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		wait.until(ExpectedConditions.visibilityOf(usersTab));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", usersTab);
		//Click TenantUsers Tab
		wait.until(ExpectedConditions.visibilityOf(systemUsersTab));
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", systemUsersTab);
		//click add new
		wait.until(ExpectedConditions.visibilityOf(addBtn));
		JavascriptExecutor js2=(JavascriptExecutor)driver;
		js2.executeScript("arguments[0].click();", addBtn);
		log.info("started creating new system admin");
		//Start form
		Select select=new Select(tenantdrpdown);
		select.selectByValue("SYSADMIN");
		Thread.sleep(2000);
		fName.sendKeys(FName);
		Thread.sleep(2000);
		lName.sendKeys(LName);
		Thread.sleep(2000);
		userMail.sendKeys(UserMail);
		Thread.sleep(2000);
		userName.sendKeys(UserName);
		Thread.sleep(2000);
		pswd.sendKeys(Pswd);
		Thread.sleep(2000);
		confirmPswd.sendKeys(ConfirmPswd);	
		Actions action=new Actions(driver);
		action.click(createBtn).build().perform();
		webelements.clickrefreshBtn();
		Thread.sleep(2000);
		webelements.selectPageSize("40");
		for(int i=0;i<=2;i++) {
				String actual_UserName=driver.findElement(By.xpath("//table/tr/td/label[@title='"+UserName+"']")).getText();
				String expected_UserName=UserName;
				System.out.println("Actual Username:"+actual_UserName);
				System.out.println("Expected Username:"+expected_UserName);
			    Assert.assertEquals(actual_UserName, expected_UserName,"System Admin can not added in list");
				log.info("System Admin is verified and present in the webtable");
		}	
		extentTest.log(extentTest.getStatus(), "It will add new System Admin data");
	}
	public void creatingSystemAdminWithWrongPswd(String FName, String LName,String UserMail,String UserName,String Pswd, String ConfirmPswd) throws Exception {
		//Click Users Tab
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		wait.until(ExpectedConditions.visibilityOf(usersTab));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", usersTab);
		//Click TenantUsers Tab
		wait.until(ExpectedConditions.visibilityOf(systemUsersTab));
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", systemUsersTab);
		//click add new
		wait.until(ExpectedConditions.visibilityOf(addBtn));
		JavascriptExecutor js2=(JavascriptExecutor)driver;
		js2.executeScript("arguments[0].click();", addBtn);
		log.info("started creating new system admin");
		//Start form
		Select select=new Select(tenantdrpdown);
		select.selectByValue("SYSADMIN");
		Thread.sleep(2000);
		fName.sendKeys(FName);
		Thread.sleep(2000);
		lName.sendKeys(LName);
		Thread.sleep(2000);
		userMail.sendKeys(UserMail);
		Thread.sleep(2000);
		userName.sendKeys(UserName);
		Thread.sleep(2000);
		pswd.sendKeys(Pswd);
		Thread.sleep(2000);
		confirmPswd.sendKeys(ConfirmPswd);
		Thread.sleep(1000);
		JavascriptExecutor js3=(JavascriptExecutor)driver;
		js3.executeScript("arguments[0].click();", createBtn);
	    String errorMsg=confirmationError.getText();
	    System.out.println(errorMsg);
	    Assert.assertEquals(errorMsg, "User created successfully","User does not created");
	     extentTest.log(extentTest.getStatus(), "Mismatch in password"); 
	}
	
	public void EditSystemUsers(String FName, String LName,String UserMail,String UserName,String Pswd, String ConfirmPswd,String NewUserMail,String NewPswd,String NewConfirmPswd) throws Exception {
		//SystemUsersPage s1=new SystemUsersPage();
		//s1.creatingSystemAdmin(FName, LName, UserMail, UserName, Pswd, ConfirmPswd);
		//Thread.sleep(6000);
		//Click Users Tab
				loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
				wait.until(ExpectedConditions.visibilityOf(usersTab));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", usersTab);
				//Click TenantUsers Tab
				wait.until(ExpectedConditions.visibilityOf(systemUsersTab));
				JavascriptExecutor js1=(JavascriptExecutor)driver;
				js1.executeScript("arguments[0].click();", systemUsersTab);
				//click add new
				wait.until(ExpectedConditions.visibilityOf(addBtn));
				JavascriptExecutor js2=(JavascriptExecutor)driver;
				js2.executeScript("arguments[0].click();", addBtn);
				log.info("started creating new system admin");
				//Start form
				Select select=new Select(tenantdrpdown);
				select.selectByValue("SYSADMIN");
				Thread.sleep(2000);
				fName.sendKeys(FName);
				Thread.sleep(2000);
				lName.sendKeys(LName);
				Thread.sleep(2000);
				userMail.sendKeys(UserMail);
				Thread.sleep(2000);
				userName.sendKeys(UserName);
				Thread.sleep(2000);
				pswd.sendKeys(Pswd);
				Thread.sleep(2000);
				confirmPswd.sendKeys(ConfirmPswd);	
		createBtn.click();
		driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
			//	webelements.clickrefreshBtn();
			//	Thread.sleep(2000);
				//webelements.selectPageSize("40");
		driver.findElement(By.xpath("//table/tr/td/label[@title='"+UserName+"']")).click();
		System.out.println("clicking on edit users");
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
	    Assert.assertEquals(actual_EditUserMsg, expected_EditUserMsg,"System User details not edited successfully");
		log.info("System User details got edited.");
		
	}
}
