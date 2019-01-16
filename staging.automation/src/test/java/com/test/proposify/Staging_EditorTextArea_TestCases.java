package com.test.proposify;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Staging_EditorTextArea_TestCases
{
	static WebDriver driver;
	
	  Revamp_Login loginObj;
	  Editor editObj;
	  SoftAssert softAssert;
	
	@BeforeMethod
	@Parameters ("browser")
	public void setup(String browser) throws InterruptedException
	{
		
		if(browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "//Users//rajnishparmar//Drivers//Chrome//chromedriver");
		ChromeOptions options = new ChromeOptions();
		 options.addArguments("--start-fullscreen");
		 driver = new ChromeDriver(options);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		 
		 driver.get("https://app.proposify.org/login");
		 if(!driver.getCurrentUrl().equals("https://app.proposify.org/login"))
		 {
			 driver.navigate().to("https://app.proposify.org/login");
		 }
		
		 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
		 editObj = PageFactory.initElements(driver,Editor.class );
		 softAssert = new SoftAssert();
		 Thread.sleep(3000);
		 loginObj.customerLogin();
		 driver.get("https://stageqa.proposify.org/proposal/edit/500023");
		 
		
		 if(!driver.getCurrentUrl().equals("https://stageqa.proposify.org/proposal/edit/500023"))
		 {
			 driver.navigate().refresh();
			 driver.get("https://stageqa.proposify.org/proposal/edit/500023");
		 }
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 
		}
		
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
			 
			 driver.get("https://dev.proposify.com/login");
			 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			 editObj = PageFactory.initElements(driver,Editor.class );
			 softAssert = new SoftAssert();
		
	
		}
		
		
	}
	
	
	
	/*@AfterTest
	public void tearDown() throws InterruptedException, WebDriverException
	{
		Thread.sleep(4000);
		driver.quit();
	}*/
	


	
	@Test(priority=0,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTextAreaDragandDrop() throws InterruptedException, WebDriverException
	{
		try
		{
		 editObj.dragTextArea();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		softAssert.assertTrue(el.isDisplayed(),"Text Area is not present on the Editor" );
		editObj.deletetheElement();
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		
		
	}
	
	@Test(priority=1,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTextBoxWidthProperty() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.enterTextboxWidthProperty("200");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected')]"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		softAssert.assertTrue(el.getAttribute("style").contains("width: "+editObj.width.getAttribute("value")+"px;"),"TextArea with width "+editObj.width.getAttribute("value")+ "is not been adjusted");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.deletetheElement();
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		
		
		}
	
	@Test(priority=2,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTextBoxHeightProperty() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.enterTextboxHeightProperty("200");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		softAssert.assertTrue(el.getAttribute("style").contains("height: "+editObj.height.getAttribute("value")+"px;"),"TextArea with height "+editObj.height.getAttribute("value")+ "is not been adjusted");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.deletetheElement();
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		}

	
	@Test(priority=3,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTextBoxCornersProperty() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.enterTextboxCornerProperty("20");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		softAssert.assertTrue(el.getAttribute("style").contains("border-radius: "+editObj.textBoxcorners.getAttribute("value")+"px;"),"TextArea with Corners "+editObj.textBoxcorners.getAttribute("value")+ "is not been adjusted");
		editObj.deletetheElement();
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		}
	
	@Test(priority=4,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTextBoxRotateProperty() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.enterTextboxRotateProperty("20");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
		softAssert.assertTrue(el.getAttribute("style").contains("transform: rotate("+editObj.rotate.getAttribute("value")+"deg);"),"TextArea with Rotate "+editObj.rotate.getAttribute("value")+ "is not been adjusted");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.deletetheElement();
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		}
	
	@Test(priority=5,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheOpacityProperty() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		editObj.movetheSlider();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
		softAssert.assertTrue(el.getAttribute("style").
				contains("opacity: "+editObj.Opacityslider.getAttribute("aria-valuenow")+";"),"TextArea with Opacity "+editObj.Opacityslider.getAttribute("aria-valuenow")+ "is not been adjusted");
		editObj.deletetheElement();
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		
		
	}
	
	/*@Test(priority=0)
	public void verifytheBorderSizePerimieter()
	{
		
		
	}
	

	@Test(priority=0)
	public void verifytheBorderColorPerimieter()
	{
		
		
	}
	
	@Test(priority=0)
	public void verifytheBorderStylePerimeter()
	{
		
	}*/
	
	@Test(priority=7,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheDuplicateofTextArea() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'draggable selected')]"));
		 String elem = eld.getAttribute("class");
				String arry[] =  new String[3];
				String arry2[] = new String[2];
				String elemID=null;
				 arry =  elem.split(" ");
				
				 
				 for(int i=0;i<3;i++)
				 {
					 String strng = arry[i];
					 System.out.println(strng);
					 if(i==2)
					 {
						 arry2 = arry[i].split("_");
						 for(int j=0;j<2;j++)
						 {
							 if(j==1)
							 {
								 elemID = arry2[j];
								 break;
							 }
						 }
						 break;
					 }
					 
				 }
		//String eleID = eld.getAttribute("class").substring(24, 29);

		editObj.duplicateTheElement();

		int dup = Integer.parseInt(elemID)+1;
		softAssert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'element textbox textbox_"+dup+"')]")).isDisplayed(), "duplicate Text Area is not created" );
		Thread.sleep(3000);

		editObj.deletetheElement();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]")).click();
		editObj.deletetheElement();
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
	}



	

	@Test(priority=6,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheDeleteofTextArea() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
        WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'draggable selected')]"));
        String elem = eld.getAttribute("class");
  		String arry[] =  new String[3];
  		String arry2[] = new String[2];
  		String elemID=null;
  		 arry =  elem.split(" ");
  		
  		 
  		 for(int i=0;i<3;i++)
  		 {
  			 String strng = arry[i];
  			 System.out.println(strng);
  			 if(i==2)
  			 {
  				 arry2 = arry[i].split("_");
  				 for(int j=0;j<2;j++)
  				 {
  					 if(j==1)
  					 {
  						 elemID = arry2[j];
  						 break;
  					 }
  				 }
  				 break;
  			 }
  			 
  		 }
		//String eleID2 = eld.getAttribute("class").substring(24, 29);

		editObj.deletetheElement();
		Thread.sleep(3000);
		List<WebElement> NewOrigList = driver.findElements(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]"));
		
		softAssert.assertTrue(NewOrigList.size()==0, "Text area not deleted");
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		
		
	}
	
	@Test(priority=8,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifydeleteofOrginalTextAreaElementafterduplicate() throws InterruptedException, WebDriverException
	{
		try
		{
		editObj.dragTextArea();
		WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'draggable selected')]"));
		  String elem = eld.getAttribute("class");
	  		String arry[] =  new String[3];
	  		String arry2[] = new String[2];
	  		String elemID=null;
	  		 arry =  elem.split(" ");
	  		
	  		 
	  		 for(int i=0;i<3;i++)
	  		 {
	  			 String strng = arry[i];
	  			 System.out.println(strng);
	  			 if(i==2)
	  			 {
	  				 arry2 = arry[i].split("_");
	  				 for(int j=0;j<2;j++)
	  				 {
	  					 if(j==1)
	  					 {
	  						 elemID = arry2[j];
	  						 break;
	  					 }
	  				 }
	  				 break;
	  			 }
	  			 
	  		 }
		//String eleID = eld.getAttribute("class").substring(24, 29);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		editObj.duplicateTheElement();
		
		WebElement eld2 = driver.findElement(By.xpath("//div[contains(@class,'draggable selected')]"));
		String elem2 = eld2.getAttribute("class");
		String dupArry[] =  new String[3];
		String dupArry2[] = new String[2];
		String elemID2=null;
		dupArry =  elem2.split(" ");
		
		 
		 for(int i=0;i<3;i++)
		 {
			 String strng = dupArry[i];
			 System.out.println(strng);
			 if(i==2)
			 {
				 arry2 = dupArry[i].split("_");
				 for(int j=0;j<2;j++)
				 {
					 if(j==1)
					 {
						 elemID2 = dupArry2[j];
						 break;
					 }
				 }
				 break;
			 }
			 
		 }
		//String eleID2 = eld2.getAttribute("class").substring(24, 29);
		editObj.deletetheElement();
		Thread.sleep(3000);
		List<WebElement> newDupList=driver.findElements(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID2+"')]"));
		int dupListCountUpdate= newDupList.size();
		Assert.assertTrue(dupListCountUpdate==0,"Duplicate Text area not deleted");	
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement orgiElement = driver.findElement(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]"));


		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		orgiElement.click();
		editObj.deletetheElement();
		Thread.sleep(3000);
		List<WebElement> NewOrigList=driver.findElements(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]"));
		int orgiListCountUpdate= NewOrigList.size();
		softAssert.assertTrue(orgiListCountUpdate==0,"Original Text area not deleted");
		driver.close();
		softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			 
			 int  var=1;
				softAssert.assertFalse(var==1,"Fail");
			driver.close();
			softAssert.assertAll();
			
		}
		}
	
	
	
	
	
	
	
	
	

}
