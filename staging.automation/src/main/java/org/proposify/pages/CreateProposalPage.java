package org.proposify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateProposalPage 
{
	WebDriver driver;
	@FindBy(how=How.XPATH, using="//div[@class='images']//img[@src='/assets/images/no-thumb-landscape.gif']")
	WebElement templateArea;
	
	@FindBy(how=How.CSS, using="a[class*='arrow uppercase']")
	WebElement useThisTemplate;
	
	@FindBy(how=How.XPATH, using="//a[@class='active purpose-past']//span[text()='Saved']")
	WebElement savedProposals;
	
	@FindBy(how=How.XPATH, using="//a[@class='purpose-new']//span[text()='New Proposal']")
	WebElement newProposal ;
	
	public CreateProposalPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	
	//Service on the Create Proposals page
	
	public void useExistingTemplate()
	{
		Actions builder= new Actions(driver);
		
		builder.moveToElement(templateArea).perform();
		useThisTemplate.click();
		
		
	}
	
	
	public void goToSavedProposals()
	{
		savedProposals.click();
		
	}
	
	public void goToNewProposals()
	{
		newProposal.click();
	}


}
