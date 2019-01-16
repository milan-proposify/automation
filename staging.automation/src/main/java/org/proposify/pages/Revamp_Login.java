package org.proposify.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Revamp_Login 
{
	 final WebDriver driver;
	 Actions builder;
	@FindBy(how=How.ID, using="pyLoginEmail")
	public WebElement emailIDLoginTB;
	
	@FindBy(how=How.CSS, using="input[id='pyLoginPassword']")
	public WebElement passwordLoginTB;
	
	@FindBy(how=How.CSS, using="button[type='submit']")
	public WebElement loginButton;
	
	@FindBy(how=How.CSS, using="a[href='https://app.proposify.org/forgot-password']")
	public WebElement forgotPasswordlink;
	
	@FindBy(how=How.CSS, using="a[title='Register']")
	public WebElement createAccountLink;
	
	@FindBy(how=How.CSS, using="a[href='https://app.proposify.org/login/auth/google']")
	public WebElement gmailLogin;
	
	@FindBy(how=How.CSS, using="a[href='https://app.proposify.org/login/auth/linkedin")
	public WebElement linkedinLogin;
	
	@FindBy(how=How.CSS, using="a[href='https://app.proposify.org/login/auth/facebook")
	public WebElement facebookLogin;
	
	@FindBy(how=How.CSS, using="a[href='https://www.proposify.com/blog/sales-tools-every-inbound-team-needs")
	public WebElement continueReadingLink;
	
	public Revamp_Login(WebDriver driver)
	{
		this.driver = driver;
        builder = new Actions(this.driver);
	 
	}
	
	//Services
	
	public void customerLogin()
	{
		emailIDLoginTB.sendKeys("milan+22@proposify.com");
		passwordLoginTB.sendKeys("milan1992");
		builder.moveToElement(loginButton).click(loginButton).perform();
	}
	
	public void goToForgotPassword()
	{
		forgotPasswordlink.click();
	}
	
	public void goToCreateAccount()
	{
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		 //wait.until(ExpectedConditions.stalenessOf(createAccountLink));
		createAccountLink.click();
	}
	
	public void  loginUsingGmail()
	{
		gmailLogin.click();
		
	}
	
	public void loginUsingLinkedin()
	{
		linkedinLogin.click();
	}
	
	public void facebookLogin()
	{
		facebookLogin.click();
		
	}
	
	public void goToBlog()
	{
		continueReadingLink.click();
		
	}
	
	
	
	
	
	
	
	
	

}
