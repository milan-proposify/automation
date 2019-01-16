package org.proposify.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_Proposal_Snapshot_Page 
{
	WebDriver driver;
	Actions builder;
		
	//****Locating all available web elements and Services on the Proposify Proposal Snapshot Page****
	
	//Locate Back button
	   @FindBy(how=How.XPATH, using=".//*[text()='Back']")
	  public WebElement backButton;
	 
	 //Locate Proposal Stage Text 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-1']//p[@class='text-center no-margin text-small']")
	  public WebElement proposalCurrentStage;
	    
	 //Locate Proposal CLient Text 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-7']//h2[@class='no-margin text-default text-bold']")
	  public WebElement proposalClientNameText;
	   
	  //Locate Proposal Name Text 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-7']//p[@class='no-margin']")
	  public WebElement proposalNameText;
	   
	   //Locate Go to Proposal Settings Circle 
	   @FindBy(how=How.XPATH, using=".//*[@class='nav nav-breadcrumb text-right']//li[1]")
	  public WebElement goToproposalSettings;
	   
	   //Locate  Proposal Snapshot Circle 
	   @FindBy(how=How.XPATH, using=".//*[@class='nav nav-breadcrumb text-right']//li[2]")
	  public WebElement proposalSnapshot;
	   
	 //Locate Go to Proposal Edit Circle 
	   @FindBy(how=How.XPATH, using=".//*[@href='//proposify2.proposify.com/proposal/edit/1849")
	  public WebElement goToproposalEdit;
	   
	   //Locate Go to Proposal Send Circle 
	   @FindBy(how=How.XPATH, using=".//*[@class='nav nav-breadcrumb text-right']//li[4]")
	  public WebElement goToproposalSend;
	   
	   //Locate Go to Proposal Edit Icon 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-4 text-right']//div[@class='btn-toolbar no-float']//a[1]")
	  public WebElement goToproposalEditIcon;
	   
	 //Locate Go to Proposal Preview Icon 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-4 text-right']//div[@class='btn-toolbar no-float']//a[2]")
	  public WebElement goToproposalPreviewIcon;
	   
	 //Locate Go to Proposal Download PDF Icon 
	   @FindBy(how=How.XPATH, using=".//*[@href='https://proposify2.proposify.com/export/pdf/4Bbbbbbbbc")
	  public WebElement goToproposalDownloadPDFIcon;
	   
	 //Locate Go to Proposal Duplicate Icon 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-6 text-right']//div[@class='btn-toolbar no-float']//div[@class='btn-group'][2]//a[2]")
	  public WebElement goToproposalDuplicateIcon;
	   
	 //Locate Go to Proposal Move to Archives Icon 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-6 text-right']//div[@class='btn-toolbar no-float']//div[@class='btn-group'][2]//a[3]")
	  public WebElement goToproposalMoveToArchivesIcon;
	   
	 //Locate Go to Proposal archive Confirmation Modal Text
	   @FindBy(how=How.XPATH, using=".//*[text()='Are you sure you want to archive this proposal?']")
	  public WebElement archiveConfirmModalText;
	   
	 //Locate Go to Proposal Mark as Drop Down 
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSnapshotStatusDropdown']")
	  public WebElement proposalDropDown;
	   
	 //Locate Go to Proposal Mark as Drop Down Options
	   @FindBy(how=How.XPATH, using=".//*[@class='tt-dataset tt-dataset-all']")
	  public WebElement proposalDropDownOption;
	   
	 //Locate Go to Proposal Mark Confirmation Modal
	   @FindBy(how=How.XPATH, using=".//*[@class='modal-dialog']")
	  public WebElement confirmModal;
	   
	 //Locate Go to Proposal Mark Confirmation Modal Text
	   @FindBy(how=How.XPATH, using=".//*[text()='Are you sure you want to mark this proposal as sent?']")
	  public WebElement confirmModalText;
	   
	 //Locate Go to Proposal Mark Confirmation Modal Yes Button
	   @FindBy(how=How.XPATH, using=".//*[text()='Yes, update']")
	  public WebElement confirmModalYesButton;
	   
	   @FindBy(how=How.XPATH, using=".//*[@class='alert alert-success']")
		  public WebElement alertSuccess;
	   
	 //Locate  Confirm modal Cancel Button
	   @FindBy(how=How.XPATH, using=".//*[text()='Cancel']")
	  public WebElement confirmModalCancelButton;
	   
	 //Locate Delete Comment Confirmation Yes Button
	   @FindBy(how=How.XPATH, using=".//*[text()='Yes, delete']")
	  public WebElement confirmDeleteCommentYesButton;
	   
	  //Locate Go to Proposal Mark Confirmation Modal No Button
	   @FindBy(how=How.XPATH, using=".//*[@class='modal-dialog']//div[@class='modal-footer']//div[@class='row']//div[1]")
	  public WebElement confirmModalNoButton;
	   
	  //Locate Go to Proposal Mark Confirmation Modal Close X Button
	   @FindBy(how=How.XPATH, using=".//*[@class='modal-dialog']//div[@class='modal-header']//div[@class='row']//button[@class='close']")
	  public WebElement confirmModalCloseButton;
	   
	   //Locate Go to Proposal Mark Confirmation Modal Close X Button
	   @FindBy(how=How.XPATH, using=".//*[@class='purpose-message']")
	  public WebElement proposalMarkedConfirmationMessage;
	   
	 //Locate Settings area 
	   @FindBy(how=How.XPATH, using=".//*[@class='col-xs-6']//span[text()='Settings']")
	  public WebElement proposalSettings;
	   
	 //Locate Settings Icon
	   @FindBy(how=How.XPATH, using=".//*[@class='svg svg-edit text-default text-small']")
	  public WebElement proposalSettingsIcon;
	   
	   //Locate Contact Details area in Panel Info
	   @FindBy(how=How.XPATH, using=".//*[@class='panel-group']//div[1]//span[text()='Contact Details']")
	  public WebElement proposalContactDetailsArea;
	   
	 //Locate Contact Details in Panel Info
	   @FindBy(how=How.XPATH, using=".//*[@class='panel-group']//div[1]//div[2]")
	  public WebElement proposalContactDetails;
	   
	 //Locate Proposal Details area in Panel Info
	   @FindBy(how=How.XPATH, using=".//*[@class='panel-group']//div[2]//span[text()='Proposal Details']")
	  public WebElement proposalProposalDetailsArea;
	   
	 //Locate Proposal Details in Panel Info
	   @FindBy(how=How.XPATH, using=".//*[@class='panel-group']//div[2]//div[2]")
	  public WebElement proposalProposalDetails;
	   
	 //Locate Attachments area in Panel Info
	   @FindBy(how=How.XPATH, using=".//*[@class='panel-group']//div[3]//span[text()='Attachments']")
	  public WebElement proposalAttachmentsArea;
	   
	 //Locate Attachments Visible TO Clients Text in Panel Info
	   @FindBy(how=How.XPATH, using=".//*[@class='panel-group']//div[3]//div[@class='row']//span[text()='Visible to Clients']")
	  public WebElement proposalAttachmentsVisibleToClientOption;
	   
	 //Locate Attachments Visibility Toggle in Panel Info
	   @FindBy(how=How.XPATH, using=".//*[@class='table table-striped table-fixed text-small no-margin attachments']")
	  public WebElement proposalAttachmentsNumber;
	   
	   //Locate Comment Box
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSnapshotCommentForm']//div[@class='form-group']//textarea[@name='message']")
	  public WebElement commentBox;
	   
	   //Locate Gravatar 
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSnapshotCommentForm']//div[@class='avatar avatar-info avatar-sm']")
	   public WebElement gravatar;
	   
	   //Locate Notify Team Members Area
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSnapshotCommentForm']//footer[@class='row']//div[1]")
	   public WebElement notifyTeamMembersArea;
	   
	 //Locate Notify all Team Members Checkbox
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSnapshotCommentForm']//footer[@class='row']//div[1]//div[@class='row']//input[@class='select_all']")
	   public WebElement notifyAllTeamMembersCheckBox;
	   
	   //Locate Comment Cancel Button
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSnapshotCommentForm']//footer[@class='row']//div[2]//a")
	   public WebElement cancelCommentLink;
	   
	   //Locate Comment Submit Button
	   @FindBy(how=How.XPATH, using=".//*[@id='pyProposalSnapshotCommentForm']//footer[@class='row']//div[2]//button[@type='submit']")
	   public WebElement submitCommentButton;
	   
	   //Locate Comment Delete BIN
	   @FindBy(how=How.XPATH, using=".//*[@class='row comment comment-item reset-lineheight']//div[@class='col-xs-11']//header//span[@class='svg svg-delete text-default']")
	   public List<WebElement> deleteCommentBin;
	   
	   public Revamp_Proposal_Snapshot_Page(WebDriver driver)
		{
			this.driver=driver;
			builder= new Actions(driver);
			
		}
	   
	   
	   //****Services****
	   
	   public void clickBackButton()
	   {
		   backButton.click();
	   }
	   
	   public void clickEditProposalIcon()
	   {
		  builder.moveToElement(goToproposalEditIcon).click(goToproposalEditIcon).perform();
	   }
	   
	   public void clickPreviewProposalIcon()
	   {
		   
		   builder.moveToElement(goToproposalPreviewIcon).click(goToproposalPreviewIcon).perform();
	   }
	   
	   public void clickDownloadPDFIcon()
	   {
		   
		   builder.moveToElement(goToproposalDownloadPDFIcon).click(goToproposalDownloadPDFIcon).perform();
	   }
	   
	   public void clickProposalDuplicateIcon()
	   {

		  builder.moveToElement(goToproposalDuplicateIcon).click(goToproposalDuplicateIcon).perform();
	   }
	   
	   public void clickProposalArchiveIcon()
	   {
		  builder.moveToElement(goToproposalMoveToArchivesIcon).click(goToproposalMoveToArchivesIcon).perform();
	   }
	   
	   public void clickProposalMarkAsDropDown()
	   {
		   
		   builder.moveToElement(proposalDropDown).click(proposalDropDown).perform();
	   }
	   
	   public void unsentMarkasProposal() throws InterruptedException
	   {
		   clickProposalMarkAsDropDown();
		   proposalDropDownOption.findElement(By.xpath(".//*[@data-value='1']")).click();
		   Thread.sleep(3000);
		   confirmModalYesButton.click();
	   }
	   
	   public void sentMarkasProposal() throws InterruptedException
	   {
		   clickProposalMarkAsDropDown();
		   proposalDropDownOption.findElement(By.xpath(".//*[@data-value='2']")).click();
		   Thread.sleep(3000);
		   confirmModalYesButton.click();
		   
	   }
	   
	   public void wonMarkasProposal() throws InterruptedException
	   {
		   clickProposalMarkAsDropDown();
		   proposalDropDownOption.findElement(By.xpath(".//*[@data-value='4']")).click();
		   Thread.sleep(3000);
		   
		   confirmModalYesButton.click();
	   }
	   
	   public void lostMarkasProposal() throws InterruptedException
	   {
		   clickProposalMarkAsDropDown();
		   proposalDropDownOption.findElement(By.xpath(".//*[@data-value='5']")).click();
		   Thread.sleep(3000);
		   
		   confirmModalYesButton.click();
	   }
	   
	   public void clickSettingEditIcon()
	   {
		   
		   builder.moveToElement(proposalSettingsIcon).click(proposalSettingsIcon).perform();
		   
	   }
	   
	   public void enterComment(String commentText)
	   {
		   
		   builder.moveToElement(commentBox).click(commentBox).perform();
		   commentBox.sendKeys(commentText);
		   builder.moveToElement(submitCommentButton).click(submitCommentButton).perform();
		   
		}
	   
	   
	   public void cancelComment(String commentText)
	   {
		   
		   builder.moveToElement(commentBox).click(commentBox).perform();
		   commentBox.sendKeys(commentText);
		  
		   builder.moveToElement(cancelCommentLink).click(cancelCommentLink).perform();
		   
		}
	   
	   public void deleteComment() throws InterruptedException
	   {
		   for(WebElement delBin:deleteCommentBin )
		   {
			   
			   builder.moveToElement(delBin).click(delBin).perform();
			   Thread.sleep(3000);
			   
			   builder.moveToElement(confirmDeleteCommentYesButton).click(confirmDeleteCommentYesButton).perform();
			   
			   
		   }
		   
		}
	   
	   public void clickALLNotifyCheckbox()
	   {
		   
		   builder.moveToElement(notifyAllTeamMembersCheckBox).click(notifyAllTeamMembersCheckBox).perform();
		   
		}
	   
	   public void clickVisibleToClientsToggle()
	   {
		   List<WebElement> tRow = proposalAttachmentsNumber.findElements(By.tagName("tr"));
		   
		   for(WebElement tr : tRow)
		   {
			  List<WebElement> tdata = tr.findElements(By.tagName("td"));
			  int td_Flag=0;
			  for(WebElement td : tdata)
			  {
				  td_Flag++;
				  if(td_Flag==2)
				  {
					  WebElement tgle = td.findElement(By.xpath(".//*[@class='toggle btn toggle-sm']"));
					  builder.moveToElement(tgle).click(tgle).perform();
				  }
			  }
		   }
		   
		   
		}


	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	  
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	 
	   
	   
	   
	   
	 
	   
	   

}
