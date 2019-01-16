package com.test.proposify;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Editor;
import org.proposify.pages.ProposalsPage;
import org.proposify.pages.Revamp_Login;
import org.proposify.pages.Revamp_UserList;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserList_TestCases 
{
	static WebDriver driver;
	
	 Revamp_Login loginObj;
	 ProposalsPage dashboardObj;
	 Revamp_UserList userListObj;
	  SoftAssert softAssert;
	
	@BeforeMethod
	@Parameters ("browser")
	public void setup(String browser)
	{
		
		if(browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "//Users//rajnishparmar//Drivers//Chrome//chromedriver");
		 driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		 
		 driver.get("https://dev.proposify.biz/login");
		 //System.out.println(driver.manage().window().getSize());
			Dimension d = new Dimension(1380,1000);
			//Resize the current window to the given dimension
			driver.manage().window().setSize(d);
		 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
		 dashboardObj = PageFactory.initElements(driver,ProposalsPage.class );
		 userListObj = PageFactory.initElements(driver,Revamp_UserList.class );
		 softAssert = new SoftAssert();
		}
		
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			 
			 driver.get("https://dev.proposify.biz/login");
			 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			 dashboardObj = PageFactory.initElements(driver,ProposalsPage.class );
			 userListObj= PageFactory.initElements(driver,Revamp_UserList.class );
			 softAssert = new SoftAssert();
		
	
		}
		
		
	}
	
	@Test(priority=0)
	public void verifythe_Users_Page_Title()
	{try
	{
		loginObj.customerLogin();
		Thread.sleep(3000);
		dashboardObj.goToUsersPage();
		Thread.sleep(3000);
		String USersPageTitle = driver.getTitle();
		System.out.println(USersPageTitle);
		Thread.sleep(3000);
		softAssert.assertTrue(USersPageTitle.equals("Users"));
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			int  var=1;
			softAssert.assertFalse(var==1,"Fail");
			driver.close();
			driver.close();
			softAssert.assertAll();
		}
		
	}
	
	@Test(priority=1)
	public void verifythe_TeamsText()
	{try
	{
		loginObj.customerLogin();
		Thread.sleep(3000);
		dashboardObj.goToUsersPage();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div[class='content']")).click();
		Thread.sleep(3000);
		String teamText =userListObj.teamsText.getText();
		System.out.println(teamText);
		softAssert.assertTrue(userListObj.teamsText.isDisplayed());
		softAssert.assertTrue(teamText.equals("Teams"));
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			int  var=1;
			softAssert.assertFalse(var==1,"Fail");
			driver.close();
			driver.close();
			softAssert.assertAll();
		}
		
	}
	
	@Test(priority=2)
	public void verifythe_TeamsPageInfoText()
	{try
	{
		loginObj.customerLogin();
		Thread.sleep(3000);
		dashboardObj.goToUsersPage();
		driver.findElement(By.cssSelector("div[class='content']")).click();
		Thread.sleep(3000);
		String teamInfoText =userListObj.teamsPageInfo.getText();
		System.out.println(teamInfoText);
		Thread.sleep(3000);
		softAssert.assertTrue(userListObj.teamsPageInfo.isDisplayed());
		softAssert.assertTrue(teamInfoText.equals("Team members have their own account and can log in to work on proposals."));
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			int  var=1;
			softAssert.assertFalse(var==1,"Fail");
			driver.close();
			driver.close();
			softAssert.assertAll();
		}
		
	}
	
	@Test(priority=3)
	public void verifythe_Avatar_Text_of_AccountOwner()
	{try
	{
		loginObj.customerLogin();
		Thread.sleep(3000);
		dashboardObj.goToUsersPage();
		driver.findElement(By.cssSelector("div[class='content']")).click();
		Thread.sleep(3000);
		String avatarText =userListObj.avatarDisplay.getText();
		System.out.println(avatarText);
		Thread.sleep(3000);
		softAssert.assertTrue(userListObj.avatarDisplay.isDisplayed());
		softAssert.assertTrue(avatarText.equals("RP"));
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			int  var=1;
			softAssert.assertFalse(var==1,"Fail");
			driver.close();
			driver.close();
			softAssert.assertAll();
		}
		
	}
	
	@Test(priority=4)
	public void verifythe__AccountOwner_Text()
	{try
	{
		loginObj.customerLogin();
		Thread.sleep(3000);
		dashboardObj.goToUsersPage();
		driver.findElement(By.cssSelector("div[class='content']")).click();
		Thread.sleep(3000);
		String avatarText =userListObj.accountOwnerText.getText();
		System.out.println(avatarText);
		Thread.sleep(3000);
		softAssert.assertTrue(userListObj.accountOwnerText.isDisplayed());
		softAssert.assertTrue(avatarText.equals("Account Owner"));
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			int  var=1;
			softAssert.assertFalse(var==1,"Fail");
			driver.close();
			driver.close();
			softAssert.assertAll();
		}
		
	}
	
	
	
	
	
	

}
