package com.test.proposify;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.proposify.pages.Revamp_Dashboard;
import org.proposify.pages.Revamp_Login;
import org.proposify.pages.Revamp_Proposal_Settings_Page;
import org.proposify.pages.Revamp_Register;
import org.proposify.pages.Revamp_Template_Page;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Proposal_Settings_Test_Cases 
{
	static WebDriver driver;
	
	 Revamp_Login loginObj;
	 Revamp_Dashboard dashObj;
	 Revamp_Template_Page templObj;
	 Revamp_Proposal_Settings_Page propSetObj;
	 
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
		 
		 softAssert = new SoftAssert();
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
	public void createProposalUsingStartfromScratch() throws InterruptedException
	{
		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickStartFromScratchTemplate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.proposalName("First Proposal");
		propSetObj.enterProposalDueDate();
		propSetObj.selectClient("New Company");
		//jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
	    jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickNextStepButton();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.titleContains("Editor"));
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/proposal/edit/"));
		driver.close();
		softAssert.assertAll();
		
		
		
	}
	
	@Test(priority=1)
	public void createProposalUsingSavedTemplate() throws InterruptedException
	{
		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickSavedTemplate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.enterProposalDueDate();
		propSetObj.selectClient("New Company");
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickNextStepButton();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.titleContains("Editor"));
		softAssert.assertTrue(driver.getCurrentUrl().contains("https://proposify2.proposify.com/proposal/edit/"));
		driver.close();
		softAssert.assertAll();
		
		
		
	}
	
	
	@Test(priority=2)
	public void verifyMandatoryProposalNameErrorForStartFromScratchProposal() throws InterruptedException
	{
		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickStartFromScratchTemplate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.enterProposalDueDate();
		propSetObj.selectClient("New Company");
		//jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickNextStepButton();
		WebElement mandError = driver.findElement(By.xpath(".//*[text()='You must provide a name for this proposal']"));
		softAssert.assertTrue(mandError.isDisplayed());
		driver.close();
		softAssert.assertAll();
		
		
		
	}
	
	@Test(priority=3)
	public void verifyMandatoryProposalNameErrorForSavedTemplateProposal() throws InterruptedException
	{

		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickSavedTemplate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.proposalNameTB.clear();
		propSetObj.enterProposalDueDate();
		propSetObj.selectClient("New Company");
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickNextStepButton();
		WebElement mandError = driver.findElement(By.xpath(".//*[text()='You must provide a name for this proposal']"));
		softAssert.assertTrue(mandError.isDisplayed());
		driver.close();
		softAssert.assertAll();
		
		
		
	}
	
	@Test(priority=4)
	public void verifyMandatoryDueDateErrorForStartFromScratchProposal() throws InterruptedException
	{
		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickStartFromScratchTemplate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.proposalName("First Proposal");
		//propSetObj.enterProposalDueDate();
		propSetObj.selectClient("New Company");
		//jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickNextStepButton();
		WebElement mandError = driver.findElement(By.xpath(".//*[text()='You must select the due date for this proposal']"));
		softAssert.assertTrue(mandError.isDisplayed());
		driver.close();
		softAssert.assertAll();
		
		
		
	}
	
	@Test(priority=5)
	public void verifyMandatoryDueDateErrorForSavedTemplateProposal() throws InterruptedException
	{

		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickSavedTemplate();
		//propSetObj.enterProposalDueDate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.selectClient("New Company");
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
	    jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickNextStepButton();
		WebElement mandError = driver.findElement(By.xpath(".//*[text()='You must select the due date for this proposal']"));
		softAssert.assertTrue(mandError.isDisplayed());
		driver.close();
		softAssert.assertAll();
		
		
		
	}
	
	
	@Test(priority=6)
	public void verifyMandatorySelectClientForStartFromScratchProposal() throws InterruptedException
	{

		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickStartFromScratchTemplate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.proposalName("First Proposal");
		propSetObj.enterProposalDueDate();
		//propSetObj.selectClient("New Company");
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
	    propSetObj.clickNextStepButton();
		WebElement mandError = driver.findElement(By.xpath(".//*[text()='You must select the client for this proposal']"));
		softAssert.assertTrue(mandError.isDisplayed());
		driver.close();
	    softAssert.assertAll();
		
		
		
	}
	
	@Test(priority=7)
	public void verifyMandatorySelectClientForSavedProposal() throws InterruptedException
	{

		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickSavedTemplate();
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,-250)", "");
		propSetObj.enterProposalDueDate();
		//propSetObj.selectClient("New Company");
		jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.selectStreams(" QNew Stream");
		propSetObj.enterProposalTags("New");
		Thread.sleep(3000);
		//jse.executeScript("window.scrollBy(0,250)", "");
		propSetObj.clickNextStepButton();
		WebElement mandError = driver.findElement(By.xpath(".//*[text()='You must select the client for this proposal']"));
		softAssert.assertTrue(mandError.isDisplayed());
		driver.close();
	    softAssert.assertAll();
		
		
		
	}
	
	
	@Test(priority=8)
	public void verifyFieldTextforStartFromScratchProposalSettingsFields() throws InterruptedException
	{

		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickStartFromScratchTemplate();
		//jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Proposal name']")).isDisplayed(), "Proposal Name Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Proposal due date']")).isDisplayed(),"Proposal due Date field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='What client is this proposal for?']")).isDisplayed(),"Client Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Who is leading the proposal?']")).isDisplayed(),"Lead Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Show All Options']")).isDisplayed(),"Show All option Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Stream']")).isDisplayed(),"Stream Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Proposal number']")).isDisplayed(),"Proposal number Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Tag this proposal']")).isDisplayed(),"Tag this Proposal Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='File attachments']")).isDisplayed(),"File attachments Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Notes (Will not be visible to your client)']")).isDisplayed(),"Notes Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Next Step']")).isDisplayed(),"Next Step Field not displayed");
	    driver.close();
		softAssert.assertAll();
	    
		
		
		
	}
	
	@Test(priority=9)
	public void verifyFieldTextforSavedTemplateProposalSettingsFields() throws InterruptedException
	{

		loginObj.customerLogin();
		dashObj.goToCreateProposal();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		templObj.clickSavedTemplate();
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		propSetObj.clickShowAllOptionsToggle();
		jse.executeScript("window.scrollBy(0,250)", "");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Proposal name']")).isDisplayed(), "Proposal Name Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Proposal due date']")).isDisplayed(),"Proposal due Date field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='What client is this proposal for?']")).isDisplayed(),"Client Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Who is leading the proposal?']")).isDisplayed(),"Lead Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Show All Options']")).isDisplayed(),"Show All option Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Stream']")).isDisplayed(),"Stream Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Proposal number']")).isDisplayed(),"Proposal number Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Tag this proposal']")).isDisplayed(),"Tag this Proposal Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='File attachments']")).isDisplayed(),"File attachments Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Notes (Will not be visible to your client)']")).isDisplayed(),"Notes Field not displayed");
		softAssert.assertTrue(driver.findElement(By.xpath(".//*[text()='Next Step']")).isDisplayed(),"Next Step Field not displayed");
	    driver.close();
		softAssert.assertAll();
	    
		
		
		
	}
	
	
	

}
