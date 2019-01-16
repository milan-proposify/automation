package com.test.proposify;


import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.proposify.pages.Revamp_Register;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SeleniumGrid 
{
	WebDriver driver ;
	final String USERNAME = "rajnish27";
    final String AUTOMATE_KEY = "qhDniGzB1bRhtqKRcLrC";
   final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
   //final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@ondemand.saucelabs.com/wd/hub";
		  
		  Revamp_Login loginObj;
		  Revamp_Register regObj;
		  Editor editObj;
		  Actions builder;
		  SoftAssert softAssert;
		
		@BeforeMethod
		@org.testng.annotations.Parameters(value={"os","os_version","browser","browser_version"})
		  public void setUp(String os,String osversion,String browser, String browserVersion) throws Exception {
		    DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("resolution", "1600x1200");
			caps.setCapability("browserstack.debug", "true");
		     caps.setCapability("build", "First build");
		     caps.setCapability("name", "Selenium Test Example");
		     caps.setCapability("browserstack.local", "true");
			caps.setCapability("acceptSslCerts", "true");
			caps.setCapability("record_video", "true");
		    caps.setCapability("os_version", "10");
			caps.setBrowserName("edge");
			caps.setJavascriptEnabled(true);
            caps.setJavascriptEnabled(true);
			caps.setAcceptInsecureCerts(true);
			
			 caps.setCapability("os", os);
			 caps.setCapability("os_version",osversion);
			 caps.setCapability("browser", browser);
			 caps.setCapability("browser_version", browserVersion);

		    driver = new RemoteWebDriver(new URL(URL), caps);
			 builder = new Actions(driver);
			 loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			 editObj = PageFactory.initElements(driver,Editor.class );
			 regObj = PageFactory.initElements(driver,Revamp_Register.class );
			 softAssert = new SoftAssert();
			 driver.manage().window().maximize();
			 //driver.get("http://dev.proposify.biz");
			driver.get("https://dev.proposify.biz/login"); //https://dev.proposify.biz/register
			 //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			 
			}
			
		
		
		
		@Test(priority=0)
		public void verifyTextAreaDragandDrop() throws InterruptedException, WebDriverException
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			loginObj.customerLogin();
			driver.get("https://dev.proposify.biz/proposal/edit/1740");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				 editObj.deletetheElement();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
			
			
		}
		
		@Test(priority=1)
		public void verifyTextBoxWidthProperty() throws InterruptedException, WebDriverException
		{
			try
			{
				
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				 editObj.deletetheElement();
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
			
			
			}
		
		@Test(priority=2)
		public void verifyTextBoxHeightProperty() throws InterruptedException, WebDriverException
		{
			try
			{
			
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				 editObj.deletetheElement();
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
			}

		
		@Test(priority=3)
		public void verifyTextBoxCornersProperty() throws InterruptedException, WebDriverException
		{
			try
			{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			editObj.dragTextArea();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			editObj.enterTextboxCornerProperty("20");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			softAssert.assertTrue(el.getAttribute("style").contains("border-radius: "+editObj.corners.getAttribute("value")+"px;"),"TextArea with Corners "+editObj.corners.getAttribute("value")+ "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				 editObj.deletetheElement();
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
			}
		
		@Test(priority=4)
		public void verifyTextBoxRotateProperty() throws InterruptedException, WebDriverException
		{
			try
			{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				 editObj.deletetheElement();
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
			}
		
		@Test(priority=5)
		public void verifytheOpacityProperty() throws InterruptedException, WebDriverException
		{
			try
			{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				 editObj.deletetheElement();
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
		
		@Test(priority=7)
		public void verifytheDuplicateofTextArea() throws InterruptedException, WebDriverException
		{
			try
			{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			
			editObj.deletetheElement();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]")).click();
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				 editObj.deletetheElement();
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
		}



		

		@Test(priority=6)
		public void verifytheDeleteofTextArea() throws InterruptedException, WebDriverException
		{
			try
			{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			editObj.deletetheElement();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			List<WebElement> NewOrigList = driver.findElements(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]"));
			
			softAssert.assertTrue(NewOrigList.size()==0, "Text area not deleted");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				 editObj.deletetheElement();
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
			
			
		}
		
		@Test(priority=8)
		public void verifydeleteofOrginalTextAreaElementafterduplicate() throws InterruptedException, WebDriverException
		{
			try
			{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			loginObj.customerLogin();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			List<WebElement> newDupList=driver.findElements(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID2+"')]"));
			int dupListCountUpdate= newDupList.size();
			Assert.assertTrue(dupListCountUpdate==0,"Duplicate Text area not deleted");	
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement orgiElement = driver.findElement(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]"));


			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			orgiElement.click();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			editObj.deletetheElement();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			List<WebElement> NewOrigList=driver.findElements(By.xpath("//div[contains(@class,'element textbox textbox_"+elemID+"')]"));
			int orgiListCountUpdate= NewOrigList.size();
			softAssert.assertTrue(orgiListCountUpdate==0,"Original Text area not deleted");
			driver.close();
			softAssert.assertAll();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				 editObj.deletetheElement();
				 int  var=1;
					softAssert.assertFalse(var==1,"Fail");
				driver.close();
				softAssert.assertAll();
				
			}
			}
		
		
		
		
		
		

			
			
			
			
			
			

			
	}
		
		
		
		
		
		
		
		