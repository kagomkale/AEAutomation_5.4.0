package com.ae.qa.pagesTenantAdmin;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase1;
import com.ae.qa.base.TestBase2;
import com.ae.qa.pages.LoginPage;
import com.aventstack.extentreports.Status;

public class HomePageTA extends TestBase1{
	public LoginPage loginpage = new LoginPage();
	public WebDriverWait wait=new WebDriverWait(driver,120);
	@FindBy(xpath="//input[@id='oldpswd']")
	WebElement oldPswd;
	@FindBy(xpath="//input[@id='newpswd']")
	WebElement newPswd;
	@FindBy(xpath="//input[@id='confirmpswd']")
	WebElement newConfirmPswd;
	@FindBy(xpath="//button[text()='Change']")
	WebElement changeBtn;
	
	@FindBy(xpath="//*[@id='step-license']/h4")
	WebElement uploadLicense;
	@FindBy(xpath="//*[@id='uploadModal']/div/div/form/div[1]/fieldset/div/label")
	@CacheLookup
	WebElement chooseFile;
	@FindBy(xpath="//button[@name='upload']")
	WebElement uploadBtn;
	@FindBy(xpath="//*[@id='uploadModal']/div/div/form/div[1]/fieldset/div/label/input")
	WebElement chooseFileFromDesktop;
	@FindBy(xpath="//*[@id='step-license']/h4")
	WebElement licenseBox;
	@FindBy(xpath="//h4[@title='VALID']")
	WebElement validLicenseBox;
	
	
	
	public HomePageTA() {
		PageFactory.initElements(driver, this);
	}
	/*public void setNewPassword(String newPassword) {
		loginpage.login(prop.getProperty("username_TA"),prop.getProperty("password_TA")); 
		oldPswd.sendKeys(prop.getProperty("password_TA"));
		newPswd.sendKeys(newPassword);
		newConfirmPswd.sendKeys(newPassword);
		JavascriptExecutor js_change=(JavascriptExecutor)driver;
		js_change.executeScript("arguments[0].click();", changeBtn);
	}*/
	public void validateUploadLicenseNewUser(String newPassword) throws Exception {
		loginpage.login(prop.getProperty("username_TA"),prop.getProperty("password_TA")); 
		oldPswd.sendKeys(prop.getProperty("password_TA"));
		newPswd.sendKeys(newPassword);
		newConfirmPswd.sendKeys(newPassword);
		//wait.until(ExpectedConditions.elementToBeClickable(changeBtn));
		JavascriptExecutor js_change=(JavascriptExecutor)driver;
		js_change.executeScript("arguments[0].click();", changeBtn);
		loginpage.login(prop.getProperty("username_TA"),newPassword); 
		//Click on License upload box
		wait.until(ExpectedConditions.visibilityOf(uploadLicense));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", uploadLicense);
		//Choose file box
		
	//	wait.until(ExpectedConditions.elementToBeClickable(chooseFile));
		//log.info("choose file is displayed");
	
     	//driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	 // JavascriptExecutor js1=(JavascriptExecutor)driver;
	//	js1.executeAsyncScript("arguments[0].click();", chooseFile);
		//chooseFile.click();
		//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		//Thread.sleep(1000);
	//	JavascriptExecutor js1=(JavascriptExecutor)driver;
	//	js1.executeScript("document.", args)
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(chooseFile));
		chooseFileFromDesktop.sendKeys("C:\\Users\\Kalyani\\Downloads\\AE_Automation_License_UAT.lic");
		// Runtime.getRuntime().exec("E:\\AutoIT\\LicenseUpload1.exe");
		// String fileUpload_actual=driver.findElement(By.xpath("//*[contains(text(),'License.lic')]")).getText();
		 //String fileUpload_expected="PriyaLicense.lic";
		 //Assert.assertEquals(fileUpload_actual,fileUpload_expected,"File is not uploaded");
		 //driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		// JavascriptExecutor js2=(JavascriptExecutor)driver; 
		 //js2.executeScript("arguments[0].click();",uploadBtn); 
	
		 wait.until(ExpectedConditions.elementToBeClickable(uploadBtn));
		// Thread.sleep(2000);
		 uploadBtn.click();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(licenseBox.getText().contentEquals("Upload Your license")) {
				System.out.println(licenseBox.getText());
				extentTest.fail("Test case failed-not uploaded");
			} else {
				String actualBox=validLicenseBox.getAttribute("title");
				System.out.println(actualBox);
				String expectedBox="VALID";
				extentTest.log(extentTest.getStatus(), "License is uploaded successfully");
			}
		 //System.out.println("License is uploaded successfully");
		 //License with License id[] is already present
	}
	public void validateUploadLicense(String TName, String Password) throws Exception {
		loginpage.login(TName,Password);
	
		
		wait.until(ExpectedConditions.visibilityOf(uploadLicense));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", uploadLicense);
		//Choose file box
		
	//	wait.until(ExpectedConditions.elementToBeClickable(chooseFile));
		//log.info("choose file is displayed");
	
     	//driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	 // JavascriptExecutor js1=(JavascriptExecutor)driver;
	//	js1.executeAsyncScript("arguments[0].click();", chooseFile);
		//chooseFile.click();
		//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		//Thread.sleep(1000);
	//	JavascriptExecutor js1=(JavascriptExecutor)driver;
	//	js1.executeScript("document.", args)
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(chooseFile));
		chooseFileFromDesktop.sendKeys("C:\\Users\\Kalyani\\Downloads\\AE_Automation_License_UAT.lic");
		// Runtime.getRuntime().exec("E:\\AutoIT\\LicenseUpload1.exe");
		// String fileUpload_actual=driver.findElement(By.xpath("//*[contains(text(),'License.lic')]")).getText();
		 //String fileUpload_expected="PriyaLicense.lic";
		 //Assert.assertEquals(fileUpload_actual,fileUpload_expected,"File is not uploaded");
		 //driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		// JavascriptExecutor js2=(JavascriptExecutor)driver; 
		 //js2.executeScript("arguments[0].click();",uploadBtn); 
	
		 wait.until(ExpectedConditions.elementToBeClickable(uploadBtn));
		// Thread.sleep(2000);
		 uploadBtn.click();
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 String licenseBoxText=licenseBox.getText();
			System.out.println(licenseBox.getText());
			if(licenseBoxText.equals("Upload Your license")) {
				System.out.println(licenseBox.getText());
				extentTest.log(Status.FAIL,"License not uploaded");
			//	extentTest.log(extentTest.getStatus(), "License not uploaded");
			} else {
				String actualBox=validLicenseBox.getAttribute("title");
				System.out.println(actualBox);
				String expectedBox="VALID";
				extentTest.log(Status.PASS, "License is uploaded successfully");
			}
		// System.out.println("License is not uploaded");
		 //License with License id[] is already present
	}
	public void validateActivateLicense(String TtName, String Ppassword) {
	loginpage.login(TtName,Ppassword);
	
	
	wait.until(ExpectedConditions.visibilityOf(uploadLicense));
	String licenseBoxText=licenseBox.getText();
	System.out.println(licenseBox.getText());
	if(licenseBoxText.equals("Upload Your ny license")) {
		//System.out.println(licenseBox.getText());
		extentTest.log(Status.PASS,"Test case passed");
	} else {
		System.out.println("xyz");
		Assert.assertEquals("Upload Your license", "Upload Your ny license","fail");
		extentTest.log(Status.FAIL,"Test case failed");
		extentTest.log(extentTest.getStatus(),"No");
		
		
	}
	
	}
	
}
