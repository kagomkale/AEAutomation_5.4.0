package com.ae.qa.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase;
import com.ae.qa.base.TestBase1;

public class TenantsPage extends TestBase1 {
	public WebDriverWait wait=new WebDriverWait(driver,180);
	public LoginPage loginpage=new LoginPage();
	public WebElements webelements=new WebElements();

	@FindBy(xpath="//div/h2[@class='page-title inline-item']")
	WebElement tenantsPageTitle;
	@FindBy(xpath="//span[(text()='Tenants')]")
	WebElement tenantsTab;
	@FindBy(xpath="//button[@name='add-tenant']")
	WebElement addTenantBtn;
	@CacheLookup
	@FindBy(xpath="//input[@id='tenantName']")
	WebElement tenantName;
	@FindBy(xpath="//div[@class='group-form-input']/textarea[@id='description']")
	WebElement descriptionOfTenant;
	@FindBy(xpath="//input[@id='orgCode']")
	WebElement organizationCode;
	@FindBy(xpath="//button[@name='submit']")
	WebElement createBtn;
	@FindBy(xpath="//button[@name='cancel']")
	WebElement cancelBtn;
	@FindBy(xpath="//p[@class='alert-message-text']")
	WebElement alertMessage;
	@FindBy(xpath="//table/tr[3]/td[5]/span")
	WebElement licenseStatus;
	
	public TenantsPage() {
		PageFactory.initElements(driver, this);
	}
	//Actions
	public String validateTenantsPageTitle() throws InterruptedException {
		// TODO Auto-generated method stub
		if(tenantsPageTitle.isDisplayed()) {
			return tenantsPageTitle.getText();
		}
		else {
		Thread.sleep(3000);
		return tenantsPageTitle.getText();
		}
	}
	public void addNewTenants(String tName,String tDescription,String orgCode) throws Exception {
		loginpage.login(prop.getProperty("username"),prop.getProperty("password")); 
		log.info("User log in Successfully");
		//click Tenants Tab
		wait.until(ExpectedConditions.visibilityOf(tenantsTab));
		JavascriptExecutor js_tenant=(JavascriptExecutor)driver;
		js_tenant.executeScript("arguments[0].click();", tenantsTab);
		//Click add Tenant button
		wait.until(ExpectedConditions.visibilityOf(addTenantBtn));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", addTenantBtn);	
		//Fill details like Tenant Name, Description and Organization code
		wait.until(ExpectedConditions.visibilityOf(tenantName));
		tenantName.sendKeys(tName);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(descriptionOfTenant));
		descriptionOfTenant.sendKeys(tDescription);
		wait.until(ExpectedConditions.visibilityOf(organizationCode));
		organizationCode.sendKeys(orgCode);
		//Create button
		wait.until(ExpectedConditions.visibilityOf(createBtn));
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", createBtn);
		//do advance search
		//Verify Success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg=alertMessage.getText();
		System.out.println("Success message: "+actual_successMsg);
		String expected_successMsg="Tenant created successfully";
		Assert.assertEquals(actual_successMsg, expected_successMsg,"Tenant not created successfully");
		
		//refresh the page
		webelements.clickrefreshBtn();
		webelements.AdvanceSearchField("name","eq",tName);
		webelements.ExtraAdvanceSearch("orgCode","eq",orgCode);
		//Verify data in table now
		 String actual_TenantName=driver.findElement(By.xpath("//table[@class='ae-table table table-bordered table-hover table-striped top-margin-lg']/tr/td[text()='"
				  +tName+"']")).getText(); 
		 String expected_TenantName=tName;
		 Assert.assertEquals(actual_TenantName,expected_TenantName,"Tenant can not added in list");
		 log.info("New Tenant is present in the table-Validated successfully");
		 
	}

	public void addNewTenantsWithDuplicateOrgCode(String ttName,String ttDescription,String oorgCode) throws Exception {
		loginpage.login(prop.getProperty("username"),prop.getProperty("password")); 
		log.info("User log in Successfully");
		//click Tenants Tab
		wait.until(ExpectedConditions.visibilityOf(tenantsTab));
		JavascriptExecutor js_tenant=(JavascriptExecutor)driver;
		js_tenant.executeScript("arguments[0].click();", tenantsTab);
		//Click add Tenant button
		wait.until(ExpectedConditions.visibilityOf(addTenantBtn));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", addTenantBtn);	
		//Fill details like Tenant Name, Description and Organization code
		wait.until(ExpectedConditions.visibilityOf(tenantName));
		tenantName.sendKeys(ttName);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(descriptionOfTenant));
		descriptionOfTenant.sendKeys(ttDescription);
		wait.until(ExpectedConditions.visibilityOf(organizationCode));
		organizationCode.sendKeys(oorgCode);
		//Create button
		wait.until(ExpectedConditions.visibilityOf(createBtn));
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", createBtn);
		//Verify Success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_failureMsg=alertMessage.getText();
		System.out.println("Failure message: "+actual_failureMsg);
		String expected_Msg="Tenant created successfully";
		//String expected_failureMsg="Duplicate orgcode ["+orgCode.toUpperCase()+"].";
		Assert.assertEquals(actual_failureMsg, expected_Msg,"New Usernot created due to duplicate orgcode. Please enter user with unique orgcode.");
		extentTest.log(extentTest.getStatus(), "Duplicate User can not be created.");
	}
	
	public String createUniqueName() {
		Date date=new Date();
		SimpleDateFormat date1= new SimpleDateFormat("dd-mm-yyyy_hh-mm-ss");
		return date1.format(date);
	}

	/*	
		//do advance search
		JavascriptExecutor js2=(JavascriptExecutor)driver;
		js2.executeScript("arguments[0].click();",advanceSearch);
		//Select name from dropdown
		wait.until(ExpectedConditions.elementToBeClickable(columnDropdown));
		Select select = new Select(columnDropdown);
		select.selectByValue("name");
		//COMAPRATOR
		wait.until(ExpectedConditions.elementToBeClickable(comparatorDropdown));
		System.out.println("Comparator1 for tenant name");
		Select select_compare = new Select(comparatorDropdown);
		select_compare.selectByValue("eq");
		//Give tenant name and add filter
		valueTxtbox.sendKeys(tName);
		wait.until(ExpectedConditions.elementToBeClickable(addFilterBtn));
		JavascriptExecutor js3=(JavascriptExecutor)driver;
		js3.executeScript("arguments[0].click();", addFilterBtn);
		//select "orgcode" from dropdown
		Thread.sleep(3000);
	//	wait.until(ExpectedConditions.elementToBeClickable(columnDropdown));
		Select select_orgcode = new Select(columnDropdown);
				select.selectByValue("orgCode");
		//COMAPRATOR
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.elementToBeClickable(comparatorDropdown));
		System.out.println("Comparator2 for org code");
		Select select_compareOrgCode = new Select(comparatorDropdown);
		select_compare.selectByValue("eq");

		//Give tenant name and add filter
		valueTxtbox.sendKeys(orgCode);
		wait.until(ExpectedConditions.elementToBeClickable(addFilterBtn));
		JavascriptExecutor js4=(JavascriptExecutor)driver;
		js4.executeScript("arguments[0].click();", addFilterBtn);*/
		
	public void verifyLicenseInfo() {
		loginpage.login(prop.getProperty("username"),prop.getProperty("password")); 
		log.info("User log in Successfully");
		//click Tenants Tab
		wait.until(ExpectedConditions.visibilityOf(tenantsTab));
		JavascriptExecutor js_tenant=(JavascriptExecutor)driver;
		js_tenant.executeScript("arguments[0].click();", tenantsTab);
		String actual_licenseStatus=licenseStatus.getText();
		System.out.println("The License Status for System Admin is "+actual_licenseStatus);
		String expected_licenseStatus="NA";
		Assert.assertEquals(actual_licenseStatus, expected_licenseStatus,"License Status could not match.");
		
	} 
}	
	
