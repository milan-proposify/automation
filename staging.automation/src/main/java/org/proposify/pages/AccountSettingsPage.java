package org.proposify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountSettingsPage 
{
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/settings']")
	WebElement companyInfoLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/branding']")
	WebElement brandingPageLink;

	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/domain']")
	WebElement domainPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/billing']")
	WebElement billingInfoLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/history']")
	WebElement pastInvoicePageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/plan']")
	WebElement changePlanPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/email']")
	WebElement emailTemplatesLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/currency']")
	WebElement currencyPagelink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/preview']")
	WebElement clientPreviewLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/tags']")
	WebElement manageTagsLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/fonts']")
	WebElement fontlibraryLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/teams']")
	WebElement teamsPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/roles']")
	WebElement rolesPageLink;
	
	@FindBy(how=How.CSS, using="a[href='https://staging.proposify.biz/account/refer']")
	WebElement referralsPageLink;
	
	
	//Service on the Account Settings Page
	
	//Access the Change Plan
	
	public void goToChangePlan()
	{
		if(changePlanPageLink.isEnabled())
		{
			changePlanPageLink.click();
		}
		
		else
		{
			System.out.println("Cannot click change plan Link");
		}
		
		
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
}
