package com.ae.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase1;
import com.aventstack.extentreports.Status;

public class InformationPage extends TestBase1{
	public WebDriverWait wait=new WebDriverWait(driver,120);
	public WebElements webelements=new WebElements();
	public LoginPage loginpage=new LoginPage();
	
	@FindBy(xpath="//div[@class='login-username text-truncate']/label")
	WebElement UserNameTab;
	@FindBy(xpath="//a[contains(text(),'Last Login : ')]")
	WebElement lastLogin;
	@FindBy(xpath="//a[@class='dropdown-item simple-text'][2]")
	WebElement lastLoginTime;
	@FindBy(xpath="//span[contains(text(),'View Profile')]")
	WebElement profileTab;
	@FindBy(xpath="//a[@name='about']")
	WebElement aboutTab;
	@FindBy(xpath="//span[@id='change-pswd']")
	WebElement changePswdTab;
	@FindBy(xpath="//input[@id='oldpswd']")
	WebElement oldPswd;
	@FindBy(xpath="//input[@id='newpswd']")
	WebElement newPswd;
	@FindBy(xpath="//input[@id='confirmpswd']")
	WebElement newConfirmPswd;
	@FindBy(xpath="//button[text()='Change']")
	WebElement changeBtn;
	@FindBy(xpath="//div/p[contains(text(),'Password updated successfully')]")
	WebElement success_msg;
	
	public InformationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void validateLastLogin() throws Exception {
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(UserNameTab));
		UserNameTab.click();
		Thread.sleep(2000);
		if(lastLogin.isDisplayed()) {
			String actual_title=lastLogin.getText();
			Assert.assertEquals(actual_title, "Last Login :","Last Login is not visible");
			System.out.println(actual_title+ " is visible");
			String actual_LoginTime=lastLoginTime.getText();
			System.out.println(actual_LoginTime);
			if(actual_LoginTime!=null) {
				System.out.println("Last login time displayed successfully");
				extentTest.log(Status.PASS, "Last login time displayed successfully");
			} else {
				extentTest.log(Status.FAIL, "Last login time not displayed");
			}
		}	
	}
	
	public void validateViewProfile() throws Exception {
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(UserNameTab));
		UserNameTab.click();
		Thread.sleep(2000);
		profileTab.click();
		List <WebElement>Table_content =driver.findElements(By.xpath("//table[@class='profile-table']/tbody/tr/td[1]"));
	//	ArrayList<String> Table_content=new ArrayList<String>(); //table[@class='version-container']/tr/td[1]
		ArrayList<String> Expected_content=new ArrayList<String>(); 
		Expected_content.add("Name");
		Expected_content.add("Email Id");
		Expected_content.add("Username");
		Expected_content.add("Role");
		Expected_content.add("Organization Code");
		Expected_content.add("Language");
		System.out.println(Expected_content);
		Thread.sleep(3000);
		int a=0,b=0;
			for(int j=0;j<Table_content.size();j++) {
				if(Expected_content.contains(Table_content.get(j).getText())) {
					
					System.out.println("Exist:"+Table_content.get(j).getText());
					a=1;
				} else {
					System.out.println("Not Exist:"+Table_content.get(j).getText());
					b=1;
				}
			}
			if (a==1 && b==0) {
					extentTest.log(Status.PASS,"All details in Profile exist");
					}
					else if(b==1) {
						extentTest.log(Status.FAIL,"All details in Profile not exist");
					}
	}
	public void validateAbout() throws Exception {
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(UserNameTab));
		UserNameTab.click();
		Thread.sleep(2000);
		aboutTab.click();
		List <WebElement>Table_content =driver.findElements(By.xpath("//table[@class='version-container']/tr/td[1]"));
	//	ArrayList<String> Table_content=new ArrayList<String>(); //table[@class='version-container']/tr/td[1]
		ArrayList<String> Expected_content=new ArrayList<String>(); 
		Expected_content.add("UI Version");
		Expected_content.add("Server Version");
		Expected_content.add("Process Studio Framework Version");
		Expected_content.add("Supported Process Studio Framework Versions");
		System.out.println(Expected_content);
		Thread.sleep(3000);
			for(int j=0;j<Table_content.size();j++) {
				if(Expected_content.contains(Table_content.get(j).getText())) {
					System.out.println("Exist:"+Table_content.get(j).getText());
				} else {
					System.out.println("Not Exist:"+Table_content.get(j).getText());
				}
			}
	}
	public void validateChangePassword() throws Exception {
		loginpage.login(prop.getProperty("user_change"),prop.getProperty("user_oldpassword"));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(UserNameTab));
		UserNameTab.click();
		Thread.sleep(2000);
		changePswdTab.click();
		oldPswd.sendKeys(prop.getProperty("user_oldpassword"));
		Thread.sleep(1000);
		newPswd.sendKeys(prop.getProperty("user_newpassword"));
		Thread.sleep(1000);
		newConfirmPswd.sendKeys(prop.getProperty("user_newpassword"));
		changeBtn.click();
		Thread.sleep(3000);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String Actual_successMsg=success_msg.getText();
		System.out.println("Success Message after changing password: "+Actual_successMsg);
		String Expected_successMsg="Password updated successfully";
		Assert.assertEquals(Actual_successMsg, Expected_successMsg,"Password not changed");
	}

	
}
