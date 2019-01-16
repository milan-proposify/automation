package org.proposify.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ClientsPage 
{
	WebDriver driver;
	@FindBy(how=How.XPATH, using="//legend//ul[@class='toolbar']//a[@class='purpose-companydelete']")
	WebElement deleteCompany;
	
	//@FindBy(how=How.CSS, using="//button[@type='button']//span[text()='Ok']")
	@FindBy(how=How.XPATH, using="//div[@class='ui-dialog-buttonset']//button[@type='button']//span[text()='Ok']")
	WebElement deleteOKButton;
	
	public ClientsPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	
	public void deleteCompanyAccount() throws InterruptedException
	{
		List<WebElement> el = driver.findElements(By.xpath("//legend//ul[@class='toolbar']//a[@class='purpose-companydelete']"));
		List<WebElement> el2 = driver.findElements(By.cssSelector("a[class='purpose-persondelete']"));//("//legend//ul[@class='toolbar']//a[@class='purpose-companydelete']"));
		//Actions builder= new Actions(driver);
		int countOfCompany =el.size();
		int countOFPerson = el2.size();
	
		for(int i=0;i<countOfCompany;i++)
		{
			try
			{
			driver.findElement(By.cssSelector("a[class='purpose-companydelete']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[@type='button']//span[text()='Ok']")).click();
			driver.navigate().refresh();
			}
			catch(ElementNotVisibleException e)
			{
				break;
			}
			
		}
				
		
		for(int j=0;j<countOFPerson;j++)
				{
					try
					{
					driver.findElement(By.cssSelector("a[class='purpose-persondelete']")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']//button[@type='button']//span[text()='Ok']")).click();
					driver.navigate().refresh();
					}
					catch(ElementNotVisibleException e)
					{
						break;
					}
					
				}

		}
		
		
	
	
	}



