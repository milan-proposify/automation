package com.test.proposify;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Revamp_Dashboard;
import org.proposify.pages.Revamp_Login;
import org.proposify.pages.Revamp_Proposal_Settings_Page;
import org.proposify.pages.Revamp_Proposal_Snapshot_Page;
import org.proposify.pages.Revamp_Template_Page;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Proposal_SnapShot_Test_Cases 
{
	static WebDriver driver;
	
	 Revamp_Login loginObj;
	 Revamp_Dashboard dashObj;
	 Revamp_Template_Page templObj;
	 Revamp_Proposal_Settings_Page propSetObj;
	 Revamp_Proposal_Snapshot_Page  propSnapObj;
	 
	 Actions builder;
	  SoftAssert softAssert;
	
	@BeforeMethod
	@Parameters ("browser")
	public void setup(String browser)
	{
		
		if(browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "//Users//rajnishparmar//Drivers//Chrome//chromedriver");
		ChromeOptions options = new ChromeOptions();
		 options.addArguments("--start-fullscreen");
		 driver = new ChromeDriver(options);
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("incognito");
		 capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
		 
		  builder= new Actions(driver);
		 //builder.keyDown(Keys.CONTROL).keyDown(Keys.COMMAND).sendKeys("F").keyUp(Keys.CONTROL).keyUp(Keys.COMMAND).perform();
		 driver.get("https://dev.proposify.com/login");
	      
			 
		 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
		 templObj = PageFactory.initElements(driver,Revamp_Template_Page.class );
		 dashObj = PageFactory.initElements(driver,Revamp_Dashboard.class );
		 propSetObj = PageFactory.initElements(driver,Revamp_Proposal_Settings_Page.class );
		 propSnapObj = PageFactory.initElements(driver, Revamp_Proposal_Snapshot_Page.class );
		 
		 softAssert = new SoftAssert();
		 
		 loginObj.customerLogin();
		 driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		}
		
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			 
			 driver.get("https://dev.proposify.com/login");
			 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			// regObj = PageFactory.initElements(driver,Revamp_Register.class );
			 softAssert = new SoftAssert();
		
	
		}
		
		
	}
	
	
	@Test(priority=0)
	public void verifyBackButtonWorking() throws InterruptedException
	{
		try
		{
		Thread.sleep(3000);
		propSnapObj.clickBackButton();
		Thread.sleep(4000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/dashboard"));
		driver.close();
		softAssert.assertAll();
		}

		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		
	}
	
	@Test(priority=1)
	public void verifySnapShotElementsText() throws InterruptedException
	{
		try
		{
			Thread.sleep(4000);
		//driver.get("https://proposify2.proposify.com/proposal/settings/edit/1849");
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSnapObj.clickSettingEditIcon();
		 String proposal_Name =  propSetObj.proposalNameTB.getAttribute("value");	
		 String proposal_Due_Date = propSetObj.dueDateTB.getAttribute("value");
		 String client  = propSetObj.selectClientDropDown.getAttribute("placeholder");
		 String contact =propSetObj.mainContactDropDown.getAttribute("placeholder");
		 String proposal_Leader = propSetObj.leadingtheProposalDropDown.getAttribute("placeholder");
		 String proposal_Number = propSetObj.proposalNumber.getAttribute("value");
		 System.out.println(proposal_Name+","+proposal_Due_Date+","+client+","+contact+","+proposal_Leader+","+proposal_Number );
		 driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		 System.out.println(propSnapObj.proposalClientNameText.getText());
		 softAssert.assertTrue(propSnapObj.proposalClientNameText.getText().equalsIgnoreCase(client));
		 System.out.println(propSnapObj.proposalNameText.getText());
		 softAssert.assertTrue(propSnapObj.proposalNameText.getText().contains(proposal_Name));
		 System.out.println(propSnapObj.proposalSettings.getText());
		 softAssert.assertTrue(propSnapObj.proposalSettings.getText().contains("Settings"),"not found");
		 System.out.println(propSnapObj.proposalContactDetailsArea.getText());
		 softAssert.assertTrue(propSnapObj.proposalContactDetailsArea.getText().contains("Contact Details"),"not found");
		 System.out.println(propSnapObj.proposalContactDetails.getText());
		 softAssert.assertTrue(propSnapObj.proposalContactDetails.getText().contains(contact),"not found");
		 System.out.println(propSnapObj.proposalProposalDetailsArea.getText());
		 softAssert.assertTrue(propSnapObj.proposalProposalDetailsArea.getText().contains("Proposal Details"),"not found");
		 System.out.println(propSnapObj.proposalProposalDetails.getText());
		 softAssert.assertTrue(propSnapObj.proposalProposalDetails.getText().contains(proposal_Number),"not found");
		 softAssert.assertTrue(propSnapObj.proposalProposalDetails.getText().contains(proposal_Leader),"not found");
		 driver.close();
		 softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}


	
		
	}
	
	@Test(priority=2)
	public void verifyEditProposalIconWorking() throws InterruptedException
	{
		try
		{
		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.clickEditProposalIcon();
		Thread.sleep(4000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/proposal/edit/1849"));
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		
	
		
}

	
	
	@Test(priority=2)
	public void verifyPreviewProposalButtonWorking() throws InterruptedException
	{
		try
		{

		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.clickPreviewProposalIcon();
		for(String winHandle : driver.getWindowHandles() )
		{
			driver.switchTo().window(winHandle);
		}
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(4000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/preview/4Bbbbbbbbc"));
		driver.close();
		for(String winHandle : driver.getWindowHandles() )
		{
			driver.switchTo().window(winHandle);
		}
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
	
		
	}
	

	/*@Test(priority=2)
	public void verifyDownloadPDFProposalIconWorking() throws InterruptedException
	{
	   
		driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.clickDownloadPDFIcon();
		for(String winHandle : driver.getWindowHandles() )
		{
			driver.switchTo().window(winHandle);
		}
		System.out.println(driver.getCurrentUrl());
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/export/pdf/4Bbbbbbbbc"));
		driver.close();
	    softAssert.assertAll();
	
		
	}*/
	
	@Test(priority=4)
	public void verifyDuplicateProposalIconWorking() throws InterruptedException
	{
		try
		{

		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.clickProposalDuplicateIcon();
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(4000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/proposal/settings"));
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
	
		
	}
	
	@Test(priority=5)
	public void verifyMoveToArchiveIconWorking() throws InterruptedException
	{
		try
		
		{
		

		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
			propSnapObj.clickProposalArchiveIcon();
			Thread.sleep(2000);
			softAssert.assertTrue(propSnapObj.archiveConfirmModalText.isDisplayed());
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
	
		
	}
	

	@Test(priority=6)
	public void verifyMarkProposalSent() throws InterruptedException
	{
		
      try
      {
		driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.sentMarkasProposal();
		Thread.sleep(2000);
		softAssert.assertTrue(propSnapObj.confirmModalText.isDisplayed());
		driver.close();
		softAssert.assertAll();
      }
      catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
	
		
	}
	
	@Test(priority=7)
	public void verifyMarkProposalUnSent() throws InterruptedException
	{
		try
		{

		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
			propSnapObj.unsentMarkasProposal();
			Thread.sleep(2000);
			softAssert.assertTrue(propSnapObj.alertSuccess.isDisplayed());
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
			}
				
	
		
	}
	

	@Test(priority=8)
	public void verifyMarkProposalWon() throws InterruptedException
	{
		
        try
        {
		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.wonMarkasProposal();
		Thread.sleep(2000);
		softAssert.assertTrue(propSnapObj.alertSuccess.isDisplayed());
		driver.close();
		softAssert.assertAll();
        }
        catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
	
		
	}
	

	@Test(priority=9)
	public void verifyProposalSettingsEdit() throws InterruptedException
	{
		
       try
       {
		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		softAssert.assertTrue(propSnapObj.proposalSettings.isDisplayed());
		propSnapObj.clickSettingEditIcon();
		Thread.sleep(4000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/proposal/settings/edit/1849"));
		driver.close();
		softAssert.assertAll();
       }
       catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		
	}
	
	@Test(priority=10)
	public void verifySnapshotComment() throws InterruptedException
	{
		
        try
        {
		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.enterComment("First Comment");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[@class='row comment comment-item reset-lineheight']//div[@class='col-xs-11']//p[@class='text-small text-dark']")).isDisplayed());
		driver.close();
		softAssert.assertAll();
        }catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
	
	}
	
	@Test(priority=11)
	public void verifySnapshotDeleteComment() throws InterruptedException
	{
		try
		{

		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.deleteComment();
		Thread.sleep(3000);
		List<WebElement> totComment = driver.findElements(By.xpath(".//*[@class='row comment comment-item reset-lineheight']"));
		softAssert.assertTrue(totComment.size()==0);
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
	
	}
	
	@Test(priority=12)
	public void verifySnapshotDeleteCommentCancel() throws InterruptedException
	{
		try
		{
		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.cancelComment("ni");
		Thread.sleep(3000);
		softAssert.assertFalse(propSnapObj.cancelCommentLink.isDisplayed());
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		

	
	}
	
	
	@Test(priority=13,enabled=false)
	public void verifySnapshotAttachmentsToggle() throws InterruptedException
	{
		try
		{
		//driver.get("https://proposify2.proposify.com/proposal/snapshot/1849");
		propSnapObj.clickVisibleToClientsToggle();
		driver.navigate().refresh();
		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		

	
	}
	
	
	
	
	
	
	
	
	
	

}
