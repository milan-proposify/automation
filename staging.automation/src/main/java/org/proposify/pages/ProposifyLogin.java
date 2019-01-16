package org.proposify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProposifyLogin 
{
	
	WebDriver driver;
	
	
	
	//Locating all available web elements on the Login Page
	
	@FindBy(how=How.CSS, using="img[src='/assets/images/proposify_logo_new.png']")
	WebElement porposifyLogo;
	
	@FindBy(how=How.ID, using="ppEmailTextbox")
    WebElement loginmailTb;
	
	@FindBy(how=How.ID, using="ppPasswordTextbox")
    WebElement loginpasswordTb;
	
	@FindBy(how=How.XPATH, using="//span[text()='Login']")
    WebElement loginButton;
	
	@FindBy(how=How.CSS, using="a[title='Forgot Password']")
    WebElement forgotpasswordLink;
	
	@FindBy(how=How.CSS, using="a[title='Register']")
    WebElement createAccountLink;
	
	public ProposifyLogin(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	//Services on the Login page.
	
	//Login Service
	public void userLogin()
	{
		if(loginmailTb.isDisplayed())
		{
		loginmailTb.sendKeys("proposify123@gmail.com");
		}
		
		else
		{
			System.out.println("Login email Text Box not displayed");
		}
		
		if(loginmailTb.isDisplayed())
		{
		loginpasswordTb.sendKeys("test12345");
		}
		
		else
		{
			System.out.println("Login Password Text Box not displayed");
		}
		
        if (loginButton.isDisplayed() && loginButton.isEnabled())
        {
		loginButton.click();
        }
        else
        {
        	System.out.println("Login Button is not either present or clickable");
        	
        }
		
	}
	
	//Forgot Password service
	
	public void forgotPassword()
	{
		if(forgotpasswordLink.isDisplayed())
		{
			forgotpasswordLink.click();
		}
		else
		{
			System.out.println("Forgot Password Link not present");
		}
	}
		
		//Create an account service
		
		public void createAccount()
		{
			if(createAccountLink.isDisplayed())
			{
				createAccountLink.click();
			}
			else
			{
				System.out.println("Create Account Link not present");
			}
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	


