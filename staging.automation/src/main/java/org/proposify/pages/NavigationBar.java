package org.proposify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavigationBar 
{
	@FindBy(how=How.XPATH, using="//a[@class='purpose-refer cinder']")
	WebElement referLink;
	
	@FindBy(how=How.CSS, using="a[href='http://support.proposify.biz']")
	WebElement helpLink;
	
	@FindBy(how=How.XPATH, using="//div[@class='dropdown-toggle']")
	WebElement UserProfileDropDown;
	
	@FindBy(how=How.XPATH, using="//a[@class='purpose-edit-user cinder']")
	WebElement editUser;
	
	@FindBy(how=How.XPATH, using="//a[@class='cinder']")
	WebElement userLogout;
	
	//services
	
	public void goToReference()
	{
		referLink.click();
	}
	
	public void goTohelpLink()
	{
		helpLink.click();
	}
	
	public void editUserProfile()
	{
		UserProfileDropDown.click();
		editUser.click();
		
	}
	
	
	public void logoutAccount()
	{
		UserProfileDropDown.click();
		userLogout.click();
	}
	
	
	
	
	
}
