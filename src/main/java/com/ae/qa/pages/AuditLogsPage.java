package com.ae.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ae.qa.base.TestBase1;

public class AuditLogsPage extends TestBase1{
	public WebDriverWait wait=new WebDriverWait(driver,150);
	public WebElements webelements=new WebElements();
	public LoginPage loginpage=new LoginPage();
	
	@FindBy(xpath="//span[contains(text(),'Logs')]")
	@CacheLookup
	WebElement logsTab;
	@FindBy(xpath="//button[@name='download-logs']")
	WebElement downloadLogsBtn;
	@FindBy(xpath="//button[@id='downloadBtn']")
	WebElement downloadBtn;
	@FindBy(xpath="//div/p[contains(text(),'Audit Logs download started')]")
	WebElement successMsg;
	@FindBy(xpath="//span[@class='mul-dorpdown-button']")
	WebElement showColumnDrpdown;
	@FindBy(xpath="//span[@class='mul-checkmark']")
	WebElement selectAllCheckBox;
	@FindBy(xpath="//span[@class='mul-dorpdown-button']/div")
	WebElement columnCount;
	public AuditLogsPage() {
		PageFactory.initElements(driver, this);
	}
	public void downloadingAuditLogs() throws Exception {
		//Click Users Tab
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		logsTab.click();
		downloadLogsBtn.click();
		Thread.sleep(2000);
		downloadBtn.click();
		
		System.out.println("Download Button is clicked");
		String actual_successMsg=successMsg.getText();
		System.out.println("Actual success msg: "+actual_successMsg);
		String expected_successMsg="Audit Logs download started";
		System.out.println("Expected success msg: "+expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg,"Download does not started.");
		Thread.sleep(15000);
		//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	public void checkColumnsInAuditLogs() throws Exception {
		//Click Users Tab
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		logsTab.click();
		Thread.sleep(2000);
		showColumnDrpdown.click();
		Thread.sleep(5000);
		if(!selectAllCheckBox.isSelected())
		{
			selectAllCheckBox.click();
			System.out.println("Clicked on Select All CheckBox");
		} else {
			System.out.println("Select All Checkbox is already selected");
		}
		String sizeOfColumn=columnCount.getText();
		int sizeOfColumnsInt=Integer.valueOf(sizeOfColumn);
		System.out.println("Selected count of No. of colums "+sizeOfColumnsInt);
		List <WebElement> TotalColumn =driver.findElements(By.xpath("//tr[@class='header-caption-row bg-primary']/th"));
		System.out.println("Total No of columns in table: "+TotalColumn.size());
		Assert.assertEquals(sizeOfColumnsInt, TotalColumn.size(),"All columns not selected");
		log.info("All Column got displayed in records after Checking select All checkbox.");
	}
	public void deselectAllInAuditLogs() throws Exception {
		//Click Users Tab
		loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		logsTab.click();
		Thread.sleep(2000);
		showColumnDrpdown.click();
		Thread.sleep(5000);
		if(selectAllCheckBox.isSelected())
		{
			selectAllCheckBox.click();
			System.out.println("Clicked on Select All and resulted into deselected all columns.");
		} else {
			for(int i=1;i<=2;i++) {
				selectAllCheckBox.click();
			}
			System.out.println("Select All Checkbox is not already selected, Hence selected twice to deselect it.");
			
		}
		String sizeOfColumn=columnCount.getText();
		int sizeOfColumnsInt=Integer.valueOf(sizeOfColumn);
		System.out.println("Selected count of No. of colums "+sizeOfColumnsInt);
		List <WebElement> TotalColumn =driver.findElements(By.xpath("//tr[@class='header-caption-row bg-primary']/th"));
		System.out.println("Total No of columns in table: "+TotalColumn.size());
		List <String> value= new ArrayList <String>();
		for(int i=0;i<TotalColumn.size();i++) {
			//String Field=TotalColumn.get(i).getText();
			System.out.println("Field of Columns: "+TotalColumn.get(i).getText());
			value.add(TotalColumn.get(i).getText());
		}
		if(value.contains("Entity") || value.contains("Object Id")) {
			System.out.println("Column Value found");
			Assert.assertEquals(sizeOfColumnsInt, TotalColumn.size(),"All columns are not deselected. ");
			log.info("All Options got deselected except Entity and ObjectId.");
		} 
		else 
		{
			System.out.println("Column value not found");
			Assert.assertTrue(false);
		}	
	}
	
 public void verifySpecificColumn() throws Exception {
	//Click Users Tab
			loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
			logsTab.click();
			Thread.sleep(2000);
			showColumnDrpdown.click();
			Thread.sleep(5000);
			//select 2 more columns 
			driver.findElement(By.xpath("//li/div/a/span[text()='Source']")).click();
			driver.findElement(By.xpath("//li/div/a/span[text()='Description']")).click();
			String sizeOfColumn=columnCount.getText();
			int sizeOfColumnsInt=Integer.valueOf(sizeOfColumn);
			System.out.println("Selected count of No. of colums "+sizeOfColumnsInt);
			List <WebElement> TotalColumn =driver.findElements(By.xpath("//tr[@class='header-caption-row bg-primary']/th"));
			System.out.println("Total No of columns in table: "+TotalColumn.size());
			List <String> value= new ArrayList <String>();
			for(int i=0;i<TotalColumn.size();i++) {
				//String Field=TotalColumn.get(i).getText();
				System.out.println("Field of Columns: "+TotalColumn.get(i).getText());
				value.add(TotalColumn.get(i).getText());
			}
			if(value.contains("Entity") || value.contains("Object Id") || value.contains("Description")|| value.contains("Source")) {
				System.out.println("Column Value found");
				Assert.assertEquals(sizeOfColumnsInt, TotalColumn.size(),"All columns selected not found in table.");
				log.info("All columns selected found in table with Entity and ObjectId.");
			} 
			else 
			{
				System.out.println("Column value not found");
				Assert.assertTrue(false);
			}
 }	
}
