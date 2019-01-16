package org.proposify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_Register 
{
	@FindBy(how=How.CSS, using="input[id='pyFirstNameTextbox']")
	public WebElement firstNameTB;
	
	@FindBy(how=How.CSS, using="input[id='pyLastNameTextbox']")
	public WebElement lastNameTB;
	
	@FindBy(how=How.CSS, using="input[id='pyEmailTextbox']")
	public WebElement emailTB;
	
	@FindBy(how=How.CSS, using="input[id='pyCompanyNameTextbox']")
	public WebElement companyNameTB;
	
	@FindBy(how=How.CSS, using="input[id='pySubdomainTextbox']")
	public WebElement domainTB;
	
	@FindBy(how=How.CSS, using="input[id='pyPasswordTextbox']")
	public WebElement passwordTB;
	
	@FindBy(how=How.CSS, using="button[id='pyRegisterAccountSubmitButton']")
	public WebElement createAccountButton;
	
	@FindBy(how=How.XPATH, using=".//*[@class='form-group has-feedback error']//p[@id='pyFirstNameTextbox-error']")
	public WebElement FirstNameErrorMessage;
	
	@FindBy(how=How.XPATH, using=".//*[@class='form-group has-feedback error']//p[@id='pyLastNameTextbox-error']")
	public WebElement LastNameErrorMessage;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//p[@id='pyEmailTextbox-error']")
	public WebElement EmailErrorMessage;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//p[@id='pyCompanyNameTextbox-error']")
	public WebElement companyErrorMessage;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//p[@id='pySubdomainTextbox-error']")
	public WebElement domainErrorMessage;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//p[@id='pyPasswordTextbox-error']")
	public WebElement passwordErrorMessage;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//label[@for='pyFirstNameTextbox']")
	public WebElement FirstNameFieldText;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//label[@for='pyLastNameTextbox']")
	public WebElement LastNameFieldText;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//label[@for='pyEmailTextbox']")
	public WebElement emailFieldText;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//label[@for='pyCompanyNameTextbox']")
	public WebElement companyFieldText;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//label[@for='pySubdomainTextbox']")
	public WebElement domainFieldText;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//div[@class='input-group']//span[@class='input-group-addon']")
	public WebElement domainFixedText;
	
	@FindBy(how=How.XPATH, using=".//*[contains(@class,'form-group has-feedback')]//label[@for='pyPasswordTextbox']")
	public WebElement passwordFieldText;
	
	@FindBy(how=How.XPATH, using=".//*[@class='col-md-12']//button[@id='pyRegisterAccountSubmitButton']")
	public WebElement creatAccountButtonText;
	
	
	//Services
	
	public void enterfirstName(String fName)
	{
		firstNameTB.sendKeys(fName);
	}
	
	public void enterLastName(String lName)
	{
		lastNameTB.sendKeys(lName);
	}
	
	public void enterEmail(String email)
	{
		emailTB.sendKeys(email);
	}
	
	public void enterCompanyName(String company)
	{
		companyNameTB.sendKeys(company);
	}
	
	public void enterDomainName(String domain)
	{
		domainTB.sendKeys(domain);
	}
	
	public void enterPassword(String password)
	{
		passwordTB.sendKeys(password);
	}
	
	public void clickCreateAccountButton()
	{
		createAccountButton.click();
	}
	
	
	
	
	
	
	

}
