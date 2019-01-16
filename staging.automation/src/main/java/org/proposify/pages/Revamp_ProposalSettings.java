package org.proposify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_ProposalSettings 
{
	@FindBy(how=How.CSS, using="input[id='pyProposalName']")
	WebElement proposalNameTB;
	
	@FindBy(how=How.CSS, using="input[id='pyProposalDueDate']")
	WebElement proposalDueDateTB;
	
	@FindBy(how=How.CSS, using="input[id='pyProposalClient']")
	WebElement selectClient;
	
	//@FindBy(how=How.CSS, using="input[id='pyProposalClient']")
	WebElement addClient;
	
	@FindBy(how=How.CSS, using="input[id='person_id-typeahead']")
	WebElement contactinCompany;
	
	@FindBy(how=How.CSS, using="input[id='pyProposalUserId']")
	WebElement selectLeadingUser;
	
	@FindBy(how=How.XPATH, using="//div[@class='toggle btn round btn-default off']")
	WebElement showalloptionsToggleButton;
	
	@FindBy(how=How.XPATH, using="//button[text()='Next Step']")
	WebElement nextStepButton;
	
	
	//Services
	public void proposalSettings()
	{
		proposalDueDateTB.click();
		selectClient.click();
		addClient.click();
		
	}
	

}
