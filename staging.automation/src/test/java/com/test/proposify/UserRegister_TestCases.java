package com.test.proposify;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.proposify.pages.Revamp_Register;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserRegister_TestCases
{

	static WebDriver driver;
	
	 Revamp_Login loginObj;
	 Revamp_Register regObj;
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
		 driver.get("https://dev.proposify.com/login");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("incognito");
		 capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
		 
		  builder= new Actions(driver);
		 //builder.keyDown(Keys.CONTROL).keyDown(Keys.COMMAND).sendKeys("F").keyUp(Keys.CONTROL).keyUp(Keys.COMMAND).perform();
		 
	     // Dimension d = new Dimension(1680,1050);
		//Resize the current window to the given dimension
		//driver.manage().window().setSize(d);
		 
		
			 
		 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
		 regObj = PageFactory.initElements(driver,Revamp_Register.class );
		 softAssert = new SoftAssert();
		}
		
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			 
			 driver.get("https://dev.proposify.com/login");
			 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			 regObj = PageFactory.initElements(driver,Revamp_Register.class );
			 softAssert = new SoftAssert();
		
	
		}
		
		
	}
	
	@Test(priority=0)
	public void verify_User_is_Able_to_Register_with_All_Valid_Details() throws InterruptedException
	{
		
		try
		{
		loginObj.goToCreateAccount();
		Thread.sleep(3000);
		softAssert.assertTrue(driver.getTitle().equals("Register"),"Register Page Title Error");
		regObj.enterfirstName("tim");
		regObj.enterLastName("Ross");
		final String randomEmail = randomEmail();
		regObj.enterEmail(randomEmail);
		regObj.enterCompanyName("TR");
		final String randomDomain = getSaltString();
		regObj.enterDomainName(randomDomain);
		regObj.enterPassword("proposify123");
		regObj.clickCreateAccountButton();
		Thread.sleep(3000);
		String welcomePage = "TR | Proposals";
		System.out.println(welcomePage);
		System.out.println(driver.getTitle());
		softAssert.assertTrue(driver.getTitle().equals(welcomePage),"Welcome Page Title Error");
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			driver.close();
			int  var=1;
			Assert.assertFalse(var==1,"Fail");
			
			
		}



		}


		private static String randomEmail() 
		{
		return "random-" + UUID.randomUUID().toString() + "@yahoo.com";
		}

		private String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 4) { // length of the random string.
		int index = (int) (rnd.nextFloat() * SALTCHARS.length());
		salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

		}
		
		
		
		@Test(priority=1)
		public void verify_First_Name_Missing_Error_Message() throws InterruptedException
		{
			
			try
			{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterLastName("Ross");
			final String randomEmail = randomEmail();
			regObj.enterEmail(randomEmail);
			regObj.enterCompanyName("TR");
			final String randomDomain = getSaltString();
			regObj.enterDomainName(randomDomain);
			regObj.enterPassword("proposify123");
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.FirstNameErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.FirstNameErrorMessage.isDisplayed(),"First Name Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("Please enter your first name."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}


}
		

		@Test(priority=2)
		public void verify_Last_Name_Missing_Error_Message() throws InterruptedException
		{
			
			try
			{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterfirstName("Tim");
			final String randomEmail = randomEmail();
			regObj.enterEmail(randomEmail);
			regObj.enterCompanyName("TR");
			final String randomDomain = getSaltString();
			regObj.enterDomainName(randomDomain);
			regObj.enterPassword("proposify123");
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.LastNameErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.LastNameErrorMessage.isDisplayed(),"last Name Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("Please enter your last name."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}


}

		
		@Test(priority=3)
		public void verify_email_Missing_Error_Message() throws InterruptedException
		{
			
			try
			{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterfirstName("Tim");
			regObj.enterLastName("Ross");
			//final String randomEmail = randomEmail();
			//regObj.enterEmail(randomEmail);
			regObj.enterCompanyName("TR");
			final String randomDomain = getSaltString();
			regObj.enterDomainName(randomDomain);
			regObj.enterPassword("proposify123");
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.EmailErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.EmailErrorMessage.isDisplayed(),"Email Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("Please enter your email address."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}

}

		@Test(priority=4)
		public void verify_Duplicate_Email_Error_Message() throws InterruptedException
		{
			
			try
			{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterfirstName("Tim");
			regObj.enterLastName("Ross");
			//final String randomEmail = randomEmail();
			regObj.enterEmail("tim@gmail.com");
			regObj.enterCompanyName("TR");
			final String randomDomain = getSaltString();
			regObj.enterDomainName(randomDomain);
			regObj.enterPassword("proposify123");
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.EmailErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.EmailErrorMessage.isDisplayed(),"Duplicate Email Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("Email address already in use."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}



}
		
		
		
		@Test(priority=5)
		public void verify_Company_Error_Message() throws InterruptedException
		{
			
			try
			{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterfirstName("Tim");
			regObj.enterLastName("Ross");
			final String randomEmail = randomEmail();
			regObj.enterEmail(randomEmail);
			final String randomDomain = getSaltString();
			regObj.enterDomainName(randomDomain);
			regObj.enterPassword("proposify123");
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.companyErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.companyErrorMessage.isDisplayed(),"Company Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("Please enter the name of your company."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}


}

		
		@Test(priority=5)
		public void verify_Domain_Error_Message() throws InterruptedException
		{
			try
			{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterfirstName("Tim");
			regObj.enterLastName("Ross");
			final String randomEmail = randomEmail();
			regObj.enterEmail(randomEmail);
			regObj.enterCompanyName("TR");
			//final String randomDomain = getSaltString();
			//regObj.enterDomainName(randomDomain);
			regObj.enterPassword("proposify123");
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.domainErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.domainErrorMessage.isDisplayed(),"Domain Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("Please enter your branded domain."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}

	}
		
		

		@Test(priority=6)
		public void verify_Duplicate_Domain_Error_Message() throws InterruptedException
		{
			try
			{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterfirstName("Tim");
			regObj.enterLastName("Ross");
			final String randomEmail = randomEmail();
			regObj.enterEmail(randomEmail);
			regObj.enterCompanyName("TR");
			//final String randomDomain = getSaltString();
			regObj.enterDomainName("proposify123");
			regObj.enterPassword("proposify123");
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.domainErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.domainErrorMessage.isDisplayed(),"Duplicate Domain Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("That subdomain is already in use. Please use another."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}

}
		
		
		@Test(priority=7)
		public void verify_Password_Error_Message() throws InterruptedException
		{try
		{
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			regObj.enterfirstName("Tim");
			regObj.enterLastName("Ross");
			final String randomEmail = randomEmail();
			regObj.enterEmail(randomEmail);
			regObj.enterCompanyName("TR");
			final String randomDomain = getSaltString();
			regObj.enterDomainName(randomDomain);
			
			regObj.clickCreateAccountButton();
			Thread.sleep(3000);
			String errMessage =regObj.passwordErrorMessage.getText();
			System.out.println(errMessage);
			softAssert.assertTrue(regObj.passwordErrorMessage.isDisplayed(),"Password Error Message not Displayed");
			softAssert.assertTrue(errMessage.equals("Please enter a password."),"Error Message text Incorrect");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}

}
		
		@Test(priority=7)
		public void verify_All_Field_Texts() throws InterruptedException
		{
			try
		  {
			loginObj.goToCreateAccount();
			Thread.sleep(3000);
			String fName_text= regObj.FirstNameFieldText.getText();
			System.out.println("First Name Text: " +fName_text );
			softAssert.assertTrue(regObj.FirstNameFieldText.isDisplayed(),"First Name Text not Displayed");
			softAssert.assertTrue(fName_text.equals("First name")," First Name text Incorrect");
			
			String lName_text= regObj.LastNameFieldText.getText();
			System.out.println("Last Name Text: " +lName_text );
			softAssert.assertTrue(regObj.LastNameFieldText.isDisplayed(),"Last Name Text not Displayed");
			softAssert.assertTrue(lName_text.equals("Last name"),"Last Name text Incorrect");
			
			String email_text= regObj.emailFieldText.getText();
			System.out.println("Email Text: " +email_text );
			softAssert.assertTrue(regObj.emailFieldText.isDisplayed(),"email Text not Displayed");
			softAssert.assertTrue(email_text.equals("Your email address"),"email  text Incorrect");
			
			String company_text= regObj.companyFieldText.getText();
			System.out.println("Company Text: " +company_text );
			softAssert.assertTrue(regObj.companyFieldText.isDisplayed(),"Company Text not Displayed");
			softAssert.assertTrue(company_text.equals("Your company name"),"Company text Incorrect");
			
			String domain_text= regObj.domainFieldText.getText();
			System.out.println("Domain Text: " +domain_text );
			softAssert.assertTrue(regObj.domainFieldText.isDisplayed(),"Domain Text not Displayed");
			softAssert.assertTrue(domain_text.equals("Your Proposify URL"),"Domain text Incorrect");
			
			String domain_Fix_text= regObj.domainFixedText.getText();
			System.out.println("Domain Fix Text: " +domain_Fix_text );
			softAssert.assertTrue(regObj.domainFieldText.isDisplayed(),"Domain Fix Text not Displayed");
			softAssert.assertTrue(domain_Fix_text.equals(".proposify.com"),"Domain text Incorrect");
			
			String password_text= regObj.passwordFieldText.getText();
			System.out.println("Password Text: " +password_text );
			softAssert.assertTrue(regObj.passwordFieldText.isDisplayed(),"Password Text not Displayed");
			softAssert.assertTrue(password_text.equals("Your Password (At least 8 characters long)"),"Password text Incorrect");
			
			String create_Account_Button_text= regObj.creatAccountButtonText.getText();
			System.out.println("Creat Account Button Text: " +create_Account_Button_text );
			softAssert.assertTrue(regObj.creatAccountButtonText.isDisplayed(),"Create  account Button Text not Displayed");
			softAssert.assertTrue(create_Account_Button_text.equals("Create my account"),"Password text Incorrect");
			
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				driver.close();
				int  var=1;
				Assert.assertFalse(var==1,"Fail");
				
				
			}

}
		
		
}
	
	
	
	
	
	
	
	