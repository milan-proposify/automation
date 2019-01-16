package org.proposify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PipelinePage {
	
	@FindBy(how=How.XPATH, using="a[@id='ppProposalTourCreateButton']//label[text()='Create a proposal']")
	WebElement createProposalButton;
	
	@FindBy(how=How.ID, using="ppProposalTourContinueButton")
	WebElement givemeTourButton;
	
	@FindBy(how=How.XPATH, using="a[@href='https://staging.proposify.biz/archives']//span[text()='View Archives']")
	WebElement viewArchiveLink;
	
	
	//Services on the Pipeline Page.
	
	public void goToTour()
	{
		givemeTourButton.click();
		
	}
	
	public void createProposal()
	{
		createProposalButton.click();
		
	}
	
	public void viewArchive()
	{
		viewArchiveLink.click();
	}
	
	

}
