	package org.proposify.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Revamp_Dashboard 
{
	WebDriver driver;
    Actions builder;
	
	@FindBy(how=How.XPATH, using=".//*[@id='pyNewProposalButton']")
	public WebElement createProposalButton;
	
	@FindBy(how=How.XPATH, using=".//*[@id='pyDashboardSearchForm']//div[@class='form-group form-group-lg has-feedback-left'][1]")
	public WebElement searchProposalTextBox;
	
	@FindBy(how=How.XPATH, using=".//*[@id='pyDashboardSearchForm']//div[2]")
	public WebElement proposalSortingDropDown;
	
	@FindBy(how=How.XPATH, using=".//*[@class='svg svg-star']")
	public WebElement starButton;
	
	@FindBy(how=How.XPATH, using="//span[text()='Add Stream']")
	public WebElement addStream;
	
	@FindBy(how=How.XPATH, using=".//*[@class='input-group has-feedback']//input[@placeholder='Stream name']")
	public WebElement addStreamNameTextBox;
	
	@FindBy(how=How.XPATH, using=".//*[@class='input-group has-feedback']//span[@class='svg svg-checkmark']")
	public WebElement addStreamCheckButton;
	
	@FindBy(how=How.XPATH, using=".//*[@class='input-group has-feedback']//span[@class='svg svg-cancel']")
	public WebElement addStreamCancelButton;
	
	public By addStreameDelete= By.xpath(".//*[@class='input-group form-group has-feedback']//a[@data-condition='confirmStreamDelete']");
	
	@FindBy(how=How.XPATH, using="//span[text()='View Archives']")
	public WebElement viewArchive;
	
	@FindBy(how=How.XPATH, using=".//*[@id='pyDashboardSelectAllCheckbox']")
	public WebElement selectAllProposalCheckBox;
	
	public By editProposalHoverIcon = By.xpath(".//*[@class='btn btn-svg btn-default cinder']");
	
	public By proposalHoverSettingsOPtions = By.xpath(".//*[@class='btn btn-default btn-svg context-menu']");
	
	@FindBy(how=How.XPATH, using=".//*[@href='//dev.proposify.biz/dashboard/archive']")
	public WebElement archiveAllPropsals;
	
	@FindBy(how=How.XPATH, using=".//*[@class='svg svg-search']")
	public WebElement magnifyinLenseSearchProposal;

    public By starOnProposal= By.xpath("//span[@class='star cinder']");
    
    public By markStarredOnProposal= By.xpath(".//*[@class='star cinder starred']");
	
	@FindBy(how=How.XPATH, using=".//*[@class='panel-title text-uppercase']")
	public WebElement totalPipeLineText;
	
	@FindBy(how=How.XPATH, using=".//*[@class='streams']//ul[@class='nav text-small sortable']")
	public WebElement proposalCountIcon;
	
	@FindBy(how=How.XPATH, using=".//*[text()='Yes, delete']")
	public WebElement deleteConfirmation;
	
	
	
	
	public Revamp_Dashboard(WebDriver driver)
	{
		this.driver=driver;
		
		builder = new Actions(driver);
		
	}
	
	
	
	
	
	//Services
	
	public void goToCreateProposal()
	{
		if(createProposalButton.isDisplayed())
		{
			createProposalButton.click();
		}
		else
		{
			System.out.println("Create Proposal button not found");
		}
	}
	
	/*public void goToArchive()
	{
		if(viewArchivesLink.isDisplayed())
		{
			viewArchivesLink.click();
		}
		else
		{
			System.out.println("view Archives Link not found");
		}
		
	}*/
	
	
	public void staraProposal(int propNumber)
	{
		
			List<WebElement> proposalDraft = driver.findElements(By.xpath(".//*[@id='pyDashboardSearchResults']//ul[@class='proposals list-group no-margin sortable']"));
			int flag=0;
			for(WebElement draftArea : proposalDraft)
			{
				flag++;
				if(flag==1)
				{
				for(int i=1;i<=propNumber;i++)
		         {
				 WebElement propStar = draftArea.findElement(By.xpath("//li["+i+"]//span[@class='star  cinder']"));
                 builder.moveToElement(propStar).click(propStar).perform();
		         }
				}
				else
				{
					break;
				}
				}
				
			
		}
        
         
         
	
	
	
	public void clickStarButton()
	{
		builder.moveToElement(starButton).click().perform();
	}
	
	
	
	public void unStaraProposal(int propNumber)
	{

		List<WebElement> proposalDraft = driver.findElements(By.xpath(".//*[@id='pyDashboardSearchResults']//ul[@class='proposals list-group no-margin sortable']"));
		int flag=0;
		for(WebElement draftArea : proposalDraft)
		{
			flag++;
			if(flag==1)
			{
			for(int i=1;i<=propNumber;i++)
	         {
			 WebElement propStar = draftArea.findElement(By.xpath("//li["+i+"]//span[contains(@class,'starred')]"));
             builder.moveToElement(propStar).click(propStar).perform();
	         }
			}
			else
			{
				break;
			}
			}
	}
	
	public void clickCreateProposalButton()
	{
		createProposalButton.click();
	}
	

	public void clickViewArchive()
	{
		viewArchive.click();
	}
	
	public void addStreamFunctionality() throws InterruptedException
	{
		addStream.click();
		
		builder.moveToElement(addStreamNameTextBox).click(addStreamNameTextBox).sendKeys("New Stream").perform();
		
		builder.moveToElement(addStreamCheckButton).click(addStreamCheckButton).perform();
	}
	
	public void deleteStreamFunctionality(int streamNumber) throws InterruptedException
	{
	
		
		List<WebElement> streamTotalBeforeAdd = driver.findElements(By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id]"));
		System.out.println("Total Streams" +streamTotalBeforeAdd.size() );
		int flag=0;
		String delStreamID = null;
		for(WebElement stream:streamTotalBeforeAdd )
		{
			flag++;
			if(flag==streamNumber)
			{
				 delStreamID = stream.getAttribute("data-id");
				System.out.println(stream.getAttribute("data-id"));
				
			
		
			
			WebElement streamDeleting =  driver.findElement(By.xpath(".//*[@class='col-xs-12']//div[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id='"+delStreamID+"']"));
				
			  builder.click(streamDeleting).perform();
			  Thread.sleep(3000);
			  builder.click(streamDeleting).perform();
			
				WebElement selStreamDelete = stream.findElement((By)addStreameDelete);
				builder.click(selStreamDelete).perform();
				deleteConfirmation.click();
				break;
			}
		}
			
		}
			
	
	


}
