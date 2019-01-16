package org.proposify.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_Proposal_Settings_Page 
{
	WebDriver driver;
	Actions builder;
		
	//****Locating all available web elements and Services on the Proposify Proposal Settings Page****
	
	
	 //Locate Proposal Name Text Box
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSettingsName']")
	  public WebElement proposalNameTB;
	   
	//Locate Proposal Due Date Text Box
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSettingsDueDate']")
	   public  WebElement dueDateTB;
	   
	 //Locate Select Client drop down
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSettingsClient']")
	   public WebElement selectClientDropDown;
	   
	 //Locate Client list
	   @FindBy(how=How.XPATH, using=".//*[@class='tt-dataset tt-dataset-all']")
	   public WebElement searchList;
	   
	 //Locate Select main Contact drop down
	   @FindBy(how=How.XPATH, using=".//*[@name='person_id-typeahead']")
	   public  WebElement mainContactDropDown;
	   
	   //Locate Select who is leading the Proposal drop down
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSettingsUserId']")
	   public WebElement leadingtheProposalDropDown;
	   
	   //Locate show all options toggle
	   @FindBy(how=How.XPATH, using=".//*[@class='toggle btn toggle-sm']//span[@class='labels']//span[@class='handle']")
	   public WebElement showAllOptionsToggle;
	   
	   
	   //Locate Select Streams drop down
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSettingsStreamIdSelect']")
	   public WebElement selectStreamDropDown;
	   
	 //Locate Proposal Number Text Box
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSettingsNumber']")
	   public WebElement proposalNumber;
	   
	 //Locate tag this Proposal
	   @FindBy(how=How.XPATH, using=".//*[@placeholder='Click to add a tag']")
	   public WebElement tagThisProposal;
	   
	 //Locate ProposalSettings noted Text box
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSettingsNotes']")
	   public WebElement notesTB;
	   
	 //Locate Next Step Button
	   @FindBy(how=How.XPATH, using=".//*[@class='btn btn-primary btn-arrow btn-lg']")
	   public WebElement nextStepSubmitButton;
	   
	   
	   public Revamp_Proposal_Settings_Page(WebDriver driver)
		{
			this.driver=driver;
			builder= new Actions(this.driver);
		}
	   //Services 
	   
	   public void proposalName(String name)
	   {
		   proposalNameTB.click();
		   proposalNameTB.sendKeys(name);
	   }
	   
	   public void enterProposalDueDate()
	   {
		  dueDateTB.click();
		  WebElement calendr = driver.findElement(By.className("table-condensed"));
		  WebElement clendrHead = calendr.findElement(By.tagName("thead"));
		  List<WebElement> changeMonth= clendrHead.findElements(By.tagName("tr"));
		  int f=0;
		  for(WebElement trow: changeMonth)
		  {
			  f++;
			  if(f==1)
			  {
				 List<WebElement> nextMonth = trow.findElements(By.tagName("th"));
				 int f2=0;
				 for(WebElement clickMonth :nextMonth )
				 {
					 f2++;
					 if(f2==3)
					 {
						 clickMonth.findElement(By.xpath(".//*[@title='Next Month']")).click();
					 }
					 
				 }
				  
			  }
			  
			 
		  }
		  
		  WebElement daySelect = calendr.findElement(By.tagName("tbody"));
		  
		  List<WebElement> changeDay= daySelect.findElements(By.tagName("tr"));
				  
		  int dayF = 0;
		  for(WebElement daysTr :changeDay )
		  {
			  dayF++;
			  if(dayF==2)
			  {
				  daysTr.findElement(By.xpath(".//*[text()='8']")).click();
				  
				  
			  }
			  
		  }
		  
		  
		  
	   }
	   
	   public void selectClient(String companyName)
	   {
		   selectClientDropDown.click();
		   selectClientDropDown.sendKeys(companyName);
		  WebElement searchResult = searchList.findElement(By.xpath(".//*[text()='"+companyName+"']"));
		  searchResult.click();
		   
		   
	   }
	   
	   public void clickShowAllOptionsToggle()
	   {
		 // builder.moveToElement(showAllOptionsToggle).click(showAllOptionsToggle).click(showAllOptionsToggle).perform();
		   showAllOptionsToggle.click();
	   }
	   
	   public void selectStreams(String streamName)
	   {
		   selectStreamDropDown.click();
		   selectStreamDropDown.sendKeys(streamName);
		    selectStreamDropDown.sendKeys(Keys.DOWN);
		    selectStreamDropDown.sendKeys(Keys.ENTER);
		  // WebElement searchResult = searchList.findElement(By.xpath(".//*[text()='"+streamName+"']"));
		   //searchResult.click();
		   
	   }
	   
	   public void enterProposalTags(String tagName)
	   {
		  builder.moveToElement(tagThisProposal).doubleClick(tagThisProposal).perform();
		   tagThisProposal.sendKeys(tagName);
		   builder.sendKeys(Keys.ENTER).perform();
	   }
	   
	   public void clickNextStepButton()
	   {
		  builder.moveToElement(nextStepSubmitButton).click(nextStepSubmitButton).perform(); 
		   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   

}
