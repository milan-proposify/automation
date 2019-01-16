package com.test.proposify;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.AccountSettingsPage;
import org.proposify.pages.ChangePlanPage;
import org.proposify.pages.ChangeToBasicPlan;
import org.proposify.pages.ProposalsPage;
import org.proposify.pages.ProposifyLogin;
import org.testng.annotations.BeforeSuite;


public class AfterSuiteExec 
{
	
	@BeforeSuite(enabled=true)
	public void ResetToBasic() throws InterruptedException
	{
		WebDriver driver;
		
		driver = new SafariDriver(); 
		driver.get("https://staging.proposify.biz/proposals");
		
	ProposifyLogin loginObj = PageFactory.initElements(driver,ProposifyLogin.class);
	ProposalsPage propPageObj = PageFactory.initElements(driver,ProposalsPage.class);
	AccountSettingsPage accntObj = PageFactory.initElements(driver,AccountSettingsPage.class);
	ChangePlanPage planObj = PageFactory.initElements(driver,ChangePlanPage.class);
	ChangeToBasicPlan basicObj = PageFactory.initElements(driver,ChangeToBasicPlan.class);
	loginObj.userLogin();
	Thread.sleep(3000);
	propPageObj.goToSettings();
	Thread.sleep(3000);
	accntObj.goToChangePlan();
	Thread.sleep(3000);
	planObj.seclectBasicPan();
	Thread.sleep(3000);
	basicObj.switchtoBasicShortPlan();
	driver.quit();
	}

}
