package org.proposify.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangeToBasicPlan 
{
	WebDriver driver;
	@FindBy(how=How.ID, using="//input[@id='ppAccountCancelDowngradeCheckbox']//label[text()='I really do want to cancel my account and permanently delete all my data.']")
	WebElement ppAccountCancelConfirmCheckbox;
	
	@FindBy(how=How.CSS, using="input[value='0']")
	WebElement switchToBasicPlanRadioButton;
	
	@FindBy(how=How.ID, using="ppAccountCancelSubmitButton")
	WebElement BasicPlanButton;
	
	@FindBy(how=How.XPATH, using="//button[text()='Proceed']")
    WebElement proceedButton1;
	
	@FindBy(how=How.XPATH, using="//a[@class ='purpose-close']")
	WebElement purchaseCancel1;
	
	@FindBy(how=How.XPATH, using="//button[@class='button primary red purpose-continue']")
    WebElement downgradeConfirmation1;
	
	public ChangeToBasicPlan(WebDriver driver)
	{
		this.driver=driver;
	}
	
	//Services on the Basic Plan Change
	
	public void switchtoBasicShortPlan() throws InterruptedException
	{
		switchToBasicPlanRadioButton.click();
		Thread.sleep(3000);
		BasicPlanButton.click();
		Thread.sleep(3000);
		if(driver.findElements(By.xpath("//button[@class='button primary red purpose-continue']")).size()==0)
		 {
			 proceedButton1.click(); 
			 Thread.sleep(3000);
			}
		else
		{
		downgradeConfirmation1.click();
		Thread.sleep(3000);
		proceedButton1.click();
		Thread.sleep(3000);
		}
		
		
	}

}
