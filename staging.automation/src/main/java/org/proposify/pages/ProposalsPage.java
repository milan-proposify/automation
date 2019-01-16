package org.proposify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProposalsPage 
{
	final WebDriver driver;
	Actions builder;
	@FindBy(how=How.CSS, using="a[href='https://dev.proposify.biz/proposals']")
	WebElement pipelinePageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://dev.proposify.biz/library']")
	WebElement libraryPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://dev.proposify.biz/templates']")
	WebElement templatesPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://dev.proposify.biz/reports']")
	WebElement metricsPageLink;
	
	@FindBy(how=How.XPATH, using=".//*[@class='navbar navbar-left']//ul[@class='nav navbar-header']//li//a[@href='//dev.proposify.biz/user']")
	WebElement usersPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://dev.proposify.biz/clients']")
	WebElement clientsPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://dev.proposify.biz/resources']")
	WebElement resourcesPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://dev.proposify.biz/account']")
	WebElement accountSettingPageLink;
	
	 public ProposalsPage(WebDriver driver)
	   {
			this.driver= driver;
			builder = new Actions(this.driver);
		   
		}
	
	//Services on the Proposals Page.
	
	//User's Pipeline section
	
	public void goToPipeline()
	{
		if(pipelinePageLink.isDisplayed())
		{
			pipelinePageLink.click();
		}
		
		else
		{
			System.out.println("can't find the Pipeline Page link");
		}
		
	}
	
	//Accessing the library section
	
	public void goToLibrary()
	{
		if(libraryPageLink.isDisplayed())
		{
			libraryPageLink.click();
		}
		
		else
		{
			System.out.println("can't find the Library page link");
		}
	}
	
	//Accessing the templates section
	
		public void goToTemplates()
		{
			if(templatesPageLink.isDisplayed())
			{
				templatesPageLink.click();
			}
			
			else
			{
				System.out.println("can't find the Templates page link");
			}
		}
		
		//Accessing the Metrics section
		
			public void goToMetrics()
			{
				if(metricsPageLink.isDisplayed())
				{
					metricsPageLink.click();
				}
				
				else
				{
					System.out.println("can't find the Metrics page link");
				}
			}
			
			//Accessing the Users section
			
			public void goToUsersPage()
			{
				if(usersPageLink.isDisplayed())
				{
					//builder.moveToElement(usersPageLink).click().perform();
					usersPageLink.click();
				}
				
				else
				{
					System.out.println("can't find the Users page link");
				}
			}
			
			//Accessing the Clients section
			
			public void goToClientsPage()
			{
				if(clientsPageLink.isDisplayed())
				{
					clientsPageLink.click();
				}
				
				else
				{
					System.out.println("can't find the Clients page link");
				}
			}
			
			//Accessing the resources section
			
			public void goToTResources()
			{
				if(resourcesPageLink.isDisplayed())
				{
					resourcesPageLink.click();
				}
				
				else
				{
					System.out.println("can't find the Resources page link");
				}
			}
	
			
			//Accessing the Settings section
			
			public void goToSettings()
			{
				if(accountSettingPageLink.isDisplayed())
				{
					accountSettingPageLink.click();
				}
				
				else
				{
					System.out.println("can't find the Settings page link");
				}
			}
	
}
