package org.proposify.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_UserList 
{
	final WebDriver driver;
	 Actions builder;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyModule']//div[@id='pyUserPage']//header//div[@class='row vbottom']//div[@class='col-xs-8']//h1")
	 public WebElement teamsText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyModule']//div[@id='pyUserPage']//header//div[@class='row vbottom']//div[@class='col-xs-8']//p")
	 public WebElement teamsPageInfo;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyModule']//div[@id='pyUserPage']//header//div[@class='row vbottom']//div[@class='col-xs-4 text-right']//a[@href='//dev.proposify.biz/user/invite']")
	 public WebElement inviteUserButton;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row vcenter']//div[@class='col-xs-12']//div[@class='row']//div[@class='col-xs-12'][1]//p")
	 public WebElement companyNameText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row vcenter']//div[@class='col-xs-12']//div[@class='row']//div[@class='col-xs-12'][2]//h2")
	 public WebElement noTeamsText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row vcenter']//div[@class='col-xs-12']//div[@class='row']//div[@class='col-xs-12'][2]//h2//small")
	 public WebElement numberofUsers;
	 
	 @FindBy(how=How.XPATH, using=".//*[@class='avatar avatar-lg']//span")
	 public WebElement avatarDisplay;
	 
	 @FindBy(how=How.XPATH, using=".//*[@class='avatar-label']//span[@class='label label-default active']")
	 public WebElement accountOwnerText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@class='no-overflow']//p")
	 public WebElement accountOwnerNameText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@class='no-overflow']//h5")
	 public WebElement accountOwnerProposalCountText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][1]//div[@class='tile-content']//div[@class='menu-right show-hover text-center']//h5[1]")
	 public WebElement lastSeenTextonHover;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][1]//div[@class='tile-content']//div[@class='menu-right show-hover text-center']//h5[2]")
	 public WebElement lastSeenTimeText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][2]//div[@class='avatar avatar-lg']//span")
	 public WebElement invitedUserAvatarDisplay;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][2]//div[@class='avatar-label']//span[@class='label label-default active']")
	 public WebElement invitedUserRoleText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][2]//div[@class='text-center']//div[@class='row']//div[@class='no-overflow']//p")
	 public WebElement invitedUserNameText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][2]//div[@class='text-center']//div[@class='row']//div[@class='no-overflow']//h5")
	 public WebElement invitedUserProposalCountText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][2]//div[@class='tile-content']//div[@class='menu-right show-hover text-center']//h5[1]")
	 public WebElement invitedUserlastSeenTextonHover;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][2]//div[@class='tile-content']//div[@class='menu-right show-hover text-center']//h5[2]")
	 public WebElement invitedUserlastSeenTimeText;
	 
	 @FindBy(how=How.XPATH, using=".//*[@id='pyUserArea']//div[@class='well well-lg'][1]//div[@class='row']//div[@class='col-xs-2'][2]//div[@class='tile-content']//div[@class='menu-right show-hover text-center']//a[text()='Delete'")
	 public WebElement invitedUserDeleteLink;
	 
		
	 public Revamp_UserList(WebDriver driver)
	   {
			this.driver= driver;
			builder = new Actions(this.driver);
		   
		}
	 
	 //Services
	 public void clicktheInviteUSerButton()
	 {
		 inviteUserButton.click();
		 
	 }
	 
	 public void hoverMosuseOverAccountOwnerProfile()
	 {
		 builder.moveToElement(avatarDisplay).perform();
	 }
	 
	 public void hoverMosuseOverInvitedUserProfile()
	 {
		 builder.moveToElement(invitedUserAvatarDisplay).perform();
	 }
	 
	 public void clickAccountOwnerProfile()
	 {
		 avatarDisplay.click();
	 }
	 
	 public void clickInvitedUserProfile()
	 {
		 invitedUserAvatarDisplay.click();
	 }
	 
	 public void deleteinvitedUser()
	 {
		 builder.moveToElement(invitedUserAvatarDisplay).perform();
		 
		 invitedUserDeleteLink.click();
		 
	 }
	 
	 public void uploadAvatar(String filepath) throws InterruptedException
	 {
		
			//String filepath = "/Users/rajnishparmar/Downloads/14jJPXpX.png";
			

			WebElement upl = driver.findElement(By.xpath(".//*[@class='col-xs-9 no-padding']//button[@class='btn btn-default btn-sm btn-avatar-upload']"));
			upl.click();
			Thread.sleep(3000);
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(filepath);
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@class='footer complete']//button]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@class='btn-group']//[@class='btn btn-primary btn-lg']")).click();
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	 
	 

}
