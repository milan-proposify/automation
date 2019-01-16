package org.proposify.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangePlanPage 
{
	WebDriver driver;
	
	@FindBy(how=How.CSS, using="label[for='ppBillingPlanAnnual']")
	WebElement annualPlansSelection;
	
	@FindBy(how=How.CSS, using="label[for='ppBillingPlanMonthly']")
	WebElement monthlyPlansSelection;
	
	// annual plan upgrade
	@FindBy(how=How.XPATH, using="//a[@class='button thin wide purpose-submit upgrade py-short-250-2017']")
	WebElement twofiftyPlan;
	
	@FindBy(how=How.XPATH, using="//a[@class='purpose-submit downgrade py-short-250-2017']//span[text()='Downgrade']")
	WebElement DowngradetwofiftyAnnual;
	
	@FindBy(how=How.XPATH, using="//a[@class='button thin wide purpose-submit upgrade py-grande-500-2016']")
	WebElement FivehundredPlan;
	
	@FindBy(how=How.XPATH, using="//a[@class='purpose-submit downgrade py-grande-500-2016']//span[text()='Downgrade']")
	WebElement DowngradefivehundredAnnual;
	
	@FindBy(how=How.XPATH, using="//a[@class='button thin wide purpose-submit upgrade py-venti-1000-2016']")
	WebElement thousandPlan;
	
	//monthly plan
	@FindBy(how=How.XPATH, using="//a[@class='purpose-submit downgrade py-short-25-2017']")
	WebElement twentyfiveMonthly;
	
	@FindBy(how=How.XPATH, using="//a[@class='purpose-submit downgrade py-short-25-2017']//span[text()='Downgrade']")
	WebElement DowngradetwentyfiveMonthly;
	
	@FindBy(how=How.XPATH, using="//a[@class='button thin wide purpose-submit upgrade py-grande-50-2016']")
	WebElement fiftyMonthly;
	
	@FindBy(how=How.XPATH, using="//a[@class='purpose-submit downgrade py-grande-50-2016']//span[text()='Downgrade']")
	WebElement DowngradefiftyMonthly;
	
	@FindBy(how=How.XPATH, using="//a[@class='button thin wide purpose-submit upgrade py-venti-100-2016']//span[text()='Downgrade']")
	WebElement hundredMonthly;
	
	@FindBy(how=How.XPATH, using="//a[@class='purpose-submit downgrade py-venti-100-2016']//span[text()='Downgrade']")
	WebElement DowngradehundredMonthly;
	
	@FindBy(how=How.XPATH, using="//button[text()='Proceed']")
    WebElement proceedButton;
	
	@FindBy(how=How.XPATH, using="//a[@class ='purpose-close']")
	WebElement purchaseCancel;
	
	@FindBy(how=How.XPATH, using="//span[text()='Downgrade']")
    WebElement downgradeButton;
	
	@FindBy(how=How.XPATH, using="//button[@class='button primary red purpose-continue']")
    WebElement downgradeConfirmation;
	
	@FindBy(how=How.ID, using="ppAccountPlanQACancelButton")
    WebElement basicPlanLink;
	
	@FindBy(how=How.ID, using="ppAccountPlanEnterpriseContactButton")
    WebElement trentaPlanLink;
	
	
	
	public ChangePlanPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	//Annual Plan Subscription
	public void plan250peryearUpgrade() throws InterruptedException
	{
		if(annualPlansSelection.isSelected() != true)
		{
		annualPlansSelection.click();
		}
		twofiftyPlan.click();
		Thread.sleep(3000);
		proceedButton.click();
				
	}
	
	public void plan500peryearUpgrade() throws InterruptedException
	{
		if(annualPlansSelection.isSelected() != true)
		{
		annualPlansSelection.click();
		}
		FivehundredPlan.click();
		Thread.sleep(3000);
		proceedButton.click();
				
	}
	
	public void plan1000peryearClick() throws InterruptedException
	{
		if(annualPlansSelection.isSelected() != true)
		{
		annualPlansSelection.click();
		}
		thousandPlan.click();
		Thread.sleep(3000);
		proceedButton.click();
				
	}
	
	//Monthly Plan
	public void plan25permonthUpgrade() throws InterruptedException
	{
		if(monthlyPlansSelection.isSelected() != true)
		{
			monthlyPlansSelection.click();
		}
	    
	    Thread.sleep(3000);
	    twentyfiveMonthly.click();
	    Thread.sleep(3000);
	    proceedButton.click();
	    
     }
	
	public void plan50permonthUpgrade() throws InterruptedException
	{
		if(monthlyPlansSelection.isSelected() != true)
		{
			monthlyPlansSelection.click();
		}
	    
	    Thread.sleep(3000);
	    fiftyMonthly.click();
	    Thread.sleep(3000);
	    proceedButton.click();
	    
     }
	
	public void plan100permonthUpgrade() throws InterruptedException
	{
		if(monthlyPlansSelection.isSelected() != true)
		{
			monthlyPlansSelection.click();
		}
	    
	    Thread.sleep(3000);
	    hundredMonthly.click();
	    Thread.sleep(3000);
	    proceedButton.click();
	    
     }
	
	
	public void plan250peryearDowngrade() throws InterruptedException
	{
		if(annualPlansSelection.isSelected() != true)
		{
		annualPlansSelection.click();
		}
		Thread.sleep(3000);
	    
	    DowngradetwofiftyAnnual.click();
	    Thread.sleep(3000);
	    
	 if(driver.findElements(By.xpath("//button[@class='button primary red purpose-continue']")).size()==0)
	 {
		 proceedButton.click(); 
		 Thread.sleep(3000);
		}
	 else {
	 Thread.sleep(3000);
	 downgradeConfirmation.click();
	 proceedButton.click(); 
     Thread.sleep(3000);
	 }
	    	
}
	
	public void plan500peryearDowngrade() throws InterruptedException
	{
		if(annualPlansSelection.isSelected() != true)
		{
		annualPlansSelection.click();
		}
		Thread.sleep(3000);
	    
	   DowngradefivehundredAnnual.click();
	    Thread.sleep(3000);
	    
	 if(driver.findElements(By.xpath("//button[@class='button primary red purpose-continue']")).size()==0)
	 {
		 proceedButton.click(); 
		 Thread.sleep(3000);
	  }
	 else {
	 Thread.sleep(3000);
	 downgradeConfirmation.click();
	 proceedButton.click(); 
     Thread.sleep(3000);
	 }
	    	
    }
	
	public void plan25permonthDowngrade() throws InterruptedException
	{
		if(monthlyPlansSelection.isSelected() != true)
		{
			monthlyPlansSelection.click();
		}
		Thread.sleep(3000);
	    
	    DowngradetwentyfiveMonthly.click();
	    Thread.sleep(3000);
	    
	 if(driver.findElements(By.xpath("//button[@class='button primary red purpose-continue']")).size()==0)
	 {
		 proceedButton.click(); 
		 Thread.sleep(3000);
		}
	 else{
	 Thread.sleep(3000);
	 downgradeConfirmation.click();
	 proceedButton.click(); 
     Thread.sleep(3000);
	 }
	    	
    }
	
	public void plan50permonthDowngrade() throws InterruptedException
	{
		if(monthlyPlansSelection.isSelected() != true)
		{
			monthlyPlansSelection.click();
		}
		Thread.sleep(3000);
	    
	    DowngradefiftyMonthly.click();
	    Thread.sleep(3000);
	    
	 if(driver.findElements(By.xpath("//button[@class='button primary red purpose-continue']")).size()==0)
	 {
		 proceedButton.click(); 
		 Thread.sleep(3000);
		}
	 
	 else{
	 Thread.sleep(3000);
	 downgradeConfirmation.click();
	 proceedButton.click(); 
     Thread.sleep(3000);
	 }
    }
	
	public void plan100permonthDowngrade() throws InterruptedException
	{
		if(monthlyPlansSelection.isSelected() != true)
		{
			monthlyPlansSelection.click();
		}
		Thread.sleep(3000);
	    
	    DowngradehundredMonthly.click();
	    Thread.sleep(3000);
	    
	 if(driver.findElements(By.xpath("//button[@class='button primary red purpose-continue']")).size()==0)
	 {
		 proceedButton.click(); 
		 Thread.sleep(3000);
		}
	 else {
	 Thread.sleep(3000);
	 downgradeConfirmation.click();
	 proceedButton.click(); 
     Thread.sleep(3000);
	 }
	    	
    }
	
	public void seclectBasicPan()
	{
		basicPlanLink.click();
	}
	
	public void applyTrentaPlan()
	{
		trentaPlanLink.click();
	}
	
	
	
	
	
	
	

}
