package org.proposify.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_Template_Page 
{
   WebDriver driver;
   Actions builder;
	
//****Locating all available web elements on the Proposify Template Page****
   
   //Locate start from scratch Template
   @FindBy(how=How.XPATH, using=".//*[@id='pyTemplateArea']//div[1]")
	WebElement startFromScratchTemplate;
   
   //Locate saved Template
   @FindBy(how=How.XPATH, using=".//*[@id='pyTemplateArea']//div[2]")
	WebElement savedTemplate;
   
   //Locate Saved Template Preview Button
   @FindBy(how=How.XPATH, using = ".//*[text()='Preview']")
   WebElement previewHoverButton;
   
  //Locate Saved Template Use this Button
 //Locate Saved Template Preview Button
   @FindBy(how=How.XPATH, using = ".//*[text()='Use This']")
   WebElement useThisHoverButton;
   
   //Locate saved Template Tab
   @FindBy(how=How.XPATH, using=".//*[@class='nav nav-tabs']//li[1]")
	WebElement savedTemplateTab;
   
   //Locate Gallery Template Tab
   @FindBy(how=How.XPATH, using=".//*[@class='nav nav-tabs']//li[2]")
	WebElement galleryTemplateTab;
   
   public Revamp_Template_Page(WebDriver driver)
	{
		this.driver=driver;
		builder= new Actions(this.driver);
	}
   
   

//Services of the Proposify Template Page
   
   public void clickStartFromScratchTemplate()
   {
	   startFromScratchTemplate.click();
	   
   }
   
   public void clickSavedTemplate()
   {
	   savedTemplate.click();
	   
   }
   
   
   public void clickSavedTemplatePreviewButton()
   {
	   builder.moveToElement(savedTemplate).perform();
	   previewHoverButton.click();
	   
	   
   }
   
   
   public void clickSavedTemplateUseThisButton()
   {
	   builder.moveToElement(savedTemplate).perform();
	   useThisHoverButton.click();
	   
	   
   }
   
   public void clickSavedTemplateTab()
   {
	   savedTemplateTab.click();
	   
	   
   }
   
   public void clickTemplateGalleryTab()
   {
	   galleryTemplateTab.click();
	   
	   
   }
   
   
   
   
   

}
