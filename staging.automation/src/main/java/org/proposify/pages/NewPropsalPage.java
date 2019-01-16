package org.proposify.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;



public class NewPropsalPage 
{
	

	WebDriver driver;
	
	@FindBy(how=How.ID, using="ppProposalClientSelect-button")
	WebElement selectCompany;
	
	@FindBy(how=How.XPATH, using="//li[text()='+ Add New Client']")
	WebElement addNwClient;
	
	@FindBy(how=How.ID, using="ppProposalPersonIdSelect-button")
	WebElement selectPersoninTheOrganizations;
	
	@FindBy(how=How.ID, using="ppProposalDueDateTextbox")
	WebElement dueDateTextBox;
	
	//@FindBy(how=How.ID, using="ui-datepicker-div")
	//WebElement calendarOpen;
	
	@FindBy(how=How.XPATH, using="//*[@id='ppAddCompanyForm']//input[@id='ppCompanyFirstNameTextbox']")
	WebElement clientFirstName;
	
	@FindBy(how=How.ID, using="ppCompanyLastNameTextbox")
	WebElement clientLastName;
	
	@FindBy(how=How.XPATH, using="//span[text()='Select company']")
	WebElement selectNewCompany;
	
	@FindBy(how=How.CSS, using="input[name='new-company']")
	WebElement newCompanyName;
	
	@FindBy(how=How.XPATH, using="//button[@class='button other']")
	WebElement clickNewCompanyButton;
	
	@FindBy(how=How.ID, using="ppCompanyEmailTextbox")
	WebElement clientEmail;
	
	@FindBy(how=How.CSS, using="button[type='submit']")
	WebElement addClientButton;
	
	@FindBy(how=How.CLASS_NAME, using="purpose-cancel")
	WebElement cancelLink;
	
	@FindBy(how=How.XPATH, using="//a[@class='arrow']//span[text()='Next Step']")
	WebElement nextStepbutton;
	
	@FindBy(how=How.XPATH, using="button[text()='Add Contact']")
	WebElement addPersonButton;
	
	@FindBy(how=How.XPATH, using="//li[text()='+ Add New Person']")
	WebElement addNwPerson;
	
	@FindBy(how=How.ID, using="ppCompanyFirstNameTextbox")
	WebElement newClientFirstName;
	
	public NewPropsalPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	
	//Services on the New Proposal Page
	
	public void enterBasicDetails() throws InterruptedException
	{
		

		selectCompany.click();
		addNwClient.click();
		Thread.sleep(3000);
		clientFirstName.sendKeys("Johny");
		clientLastName.sendKeys("Bravo");
		selectNewCompany.click();
		newCompanyName.sendKeys("wayne industries");
		clickNewCompanyButton.click();
		clientEmail.sendKeys("proposify123+Test@gmail.com");
		addClientButton.click();
		dueDateTextBox.click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(3000);
		List<WebElement> date_nodes=driver.findElements(By.xpath("//div[@id='ui-datepicker-div']//table[@class='ui-datepicker-calendar']//tbody//tr//td[@data-handler='selectDay']//a[@class='ui-state-default']"));
		
			for(WebElement d:date_nodes)
			{
				if(d.getText().equals("1"))
				{
				 d.click();
				}
			}
			
			Thread.sleep(3000);
			selectPersoninTheOrganizations.click();
			addNwPerson.click();
			newClientFirstName.sendKeys("Tim");
			clientLastName.sendKeys("Hortons");
			clientEmail.sendKeys("proposify123+tims@gmail.com");
			addPersonButton.click();
			nextStepbutton.click();
			
		
		
		
       //Select personSelection = new Select(selectPersoninTheOrganizations);
		
       // personSelection.selectByVisibleText("+ Add New Person");
		
		
		
		
		
				
		
		/*Select newCompanyselection = new Select(selectNewCompany);
		newCompanyselection.selectByValue(arg0);*/
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
