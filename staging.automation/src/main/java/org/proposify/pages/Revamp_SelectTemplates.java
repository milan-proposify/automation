package org.proposify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_SelectTemplates 
{
	//@FindBy(how=How.XPATH, using="//span[text()='Create a proposal']")
	WebElement savedTemplateTab;
	
	//@FindBy(how=How.XPATH, using="//span[text()='Create a proposal']")
	WebElement templateGalleryTab;
	
	//@FindBy(how=How.XPATH, using="//span[text()='Create a proposal']")
	WebElement templateSelected;
	
	//@FindBy(how=How.XPATH, using="//span[text()='Create a proposal']")
	WebElement templateAccessSettings;
	
	//@FindBy(how=How.XPATH, using="//span[text()='Create a proposal']")
	WebElement clickTemplateSettings;
	
	//@FindBy(how=How.XPATH, using="//span[text()='Create a proposal']")
	WebElement clickTemplateDuplicate;
	
	//@FindBy(how=How.XPATH, using="//span[text()='Create a proposal']")
	WebElement clickTemplateDelete;
	
	//Services
	
	public void goToSaveTemplates()
	{
		if(savedTemplateTab.isDisplayed())
		{
			savedTemplateTab.click();
		}
		else
		{
			System.out.println("saved Template Tab not found");
		}
	}
	
	public void goToTemplateGallery()
	{
		if(templateGalleryTab.isDisplayed())
		{
			templateGalleryTab.click();
		}
		else
		{
			System.out.println("template Gallery Tab not found");
		}
		
	}
	
	public void selectTemplate()
	{
		templateSelected.click();
		
	}
	
	public void goToTemplateSetting()
	{
		templateAccessSettings.click();
	}
	
	public void TemplateSettings()
	{
		clickTemplateSettings.click();
	}
	
	public void TemplateDuplicate()
	{
		clickTemplateSettings.click();
	}
	
	public void TemplateDelete()
	{
		clickTemplateDelete.click();
	}

}
