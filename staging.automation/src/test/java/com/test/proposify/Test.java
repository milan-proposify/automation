package com.test.proposify;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test 
{

	// VIDEO_URL set to like "https://s3-ap-southeast-2.amazonaws.com/b2729248-ak68-6948-a2y8-80e7479te16a/9ag7b09j-6a38-58w2-bb01-17qw724ce46t/play.html?".
	// Find this VIDEO_URL value in your Gridlastic dashboard.
	private static final String VIDEO_URL = null; 
	private RemoteWebDriver driver;
	 Revamp_Login loginObj;
	  Editor editObj;
	  Actions builder;
	  SoftAssert softAssert;
	  

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws Exception {

		 

		// Example test environment. NOTE: Gridlastic auto scaling requires all
		// these 3 environment variables in each request.
		// see test environments for capabilities to use https://www.gridlastic.com/test-environments.html
		String platform_name = "win7";
		String browser_name = "chrome";
		String browser_version = "latest";

		// optional video recording
		String record_video = "True";

		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (platform_name.equalsIgnoreCase("win7")) {
			capabilities.setPlatform(Platform.VISTA);
		}
		if (platform_name.equalsIgnoreCase("win8")) {
			capabilities.setPlatform(Platform.WIN8);
		}
		if (platform_name.equalsIgnoreCase("win8_1")) {
			capabilities.setPlatform(Platform.WIN8_1);
		}
		if (platform_name.equalsIgnoreCase("win10")) {
			capabilities.setPlatform(Platform.WIN10);
		}
		if (platform_name.equalsIgnoreCase("linux")) {
			capabilities.setPlatform(Platform.LINUX);
		}
		capabilities.setBrowserName(browser_name);
		capabilities.setVersion(browser_version);
		String proxy_server = "rajnishparmar.gridlastic.com:8001";
		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(proxy_server).setFtpProxy(proxy_server).setSslProxy(proxy_server);
		capabilities.setCapability(CapabilityType.PROXY, proxy);

		// video record
		if (record_video.equalsIgnoreCase("True")) {
			capabilities.setCapability("video", "True"); // NOTE: "True" is a case sensitive string, not boolean.
		} else {
			capabilities.setCapability("video", "False"); // NOTE: "False" is a case sensitive string, not boolean.
		}
		
		
		
		//Chrome specifics
		if (browser_name.equalsIgnoreCase("chrome")){
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars"); // starting from Chrome 57 the info bar displays with "Chrome is being controlled by automated test software."
			// On Linux start-maximized does not expand browser window to max screen size. Always set a window size and position.
			if (platform_name.equalsIgnoreCase("linux")) {
				options.addArguments(Arrays.asList("--window-position=0,0"));
				options.addArguments(Arrays.asList("--window-size=1920,1080"));	
				} else {
				options.addArguments(Arrays.asList("--start-maximized"));
				}
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			
			} 
		
		//Firefox specifics
		if (browser_name.equalsIgnoreCase("firefox")){
				// If you are using selenium 3 and test Firefox versions below version 48
				if(Integer.parseInt(browser_version)<48){
				capabilities.setCapability("marionette", false);
				}
				
		}
	
		//replace USERNAME:ACCESS_KEY@SUBDOMAIN with your credentials found in the Gridlastic dashboard
		driver = new RemoteWebDriver(new URL("http://3l5ZltTUYEx8mtH8cQyp1RMyu2LJzIes:U9h5t0QhvdgHxOFAv4PmLsL6lmGKVK2F@RAJNISHPARMAR.gridlastic.com:80/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		// On LINUX/FIREFOX the "driver.manage().window().maximize()" option does not expand browser window to max screen size. Always set a window size.
		if (platform_name.equalsIgnoreCase("linux") && browser_name.equalsIgnoreCase("firefox")) {
			driver.manage().window().setSize(new Dimension(1920, 1080));	
		}
        

		if (record_video.equalsIgnoreCase("True")) {
			System.out.println("Test Video: " + VIDEO_URL + ((RemoteWebDriver) driver).getSessionId());
		}
	}

	/*@org.testng.annotations.Test(enabled = true)
	 public void test_site() throws Exception  { 	
        driver.get("https://dev.proposify.com");
        Thread.sleep(6000); //slow down for demo purposes
        //WebElement element = driver.findElement(By.name("q"));
       // element.sendKeys("webdriver");
        //element.submit();
       // Thread.sleep(5000);
	}*/
	@org.testng.annotations.Test(priority=0,enabled=false)
	public void verifyPageFlowFeeTableDragAndDrop() throws WebDriverException
	{
		try
		{
			loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			 editObj = PageFactory.initElements(driver,Editor.class );
			 softAssert = new SoftAssert();
			 driver.get("https://app.proposify.org");
	    Thread.sleep(7000);
	loginObj.customerLogin();
	Thread.sleep(7000);
	driver.get("https://stageqa.proposify.org/proposal/edit/500023");

	Thread.sleep(7000);
	editObj.insertPageFlowFeeTable("6");

	WebElement el = driver.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[@class='canvas selected loaded']"
			+ "//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
	Thread.sleep(3000);
	 softAssert.assertTrue(el.isDisplayed(),"Fee table is not present on the Editor" );
	 editObj.displayPropertiesPanelforPageFlowTable();
	 editObj.deleteTable();
	driver.close();
	softAssert.assertAll();
		}
		catch(Exception e)
{
System.out.println(e.getMessage());
if(editObj.phpErrorPopupOKButton1.size()>0)
{
 driver.navigate().refresh();
editObj.PageFlowFeeTableSelectSection.click();
 List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
	System.out.println(elem.size());
	for(int i=elem.size()-1;i>=0;i--)
	{
		if(i==0)
		{
	  builder.moveToElement(elem.get(i)).click().perform();
	  builder.moveToElement(elem.get(i)).click().perform();
     editObj.deletetheElement();
	String elemClass = elem.get(i).getAttribute("class");
	((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('class', '"+elemClass+ "selected deleting');", elem.get(i));
		}
		
		  builder.moveToElement(elem.get(i)).click().perform();
		  //builder.moveToElement(elem.get(i)).click().perform();
	     editObj.deletetheElement();
		String elemClass = elem.get(i).getAttribute("class");
		
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('class', '"+elemClass+ "selected deleting');", elem.get(i));
	
	
	}
	
	int  var=1;
	softAssert.assertFalse(var==1,"Fail");
   driver.close();
   softAssert.assertAll();
 
}
else
{
 //editObj.deleteTable();
 int  var=1;
	softAssert.assertFalse(var==1,"Fail");
driver.close();
softAssert.assertAll();
}

}

	
	}
	
	
	@org.testng.annotations.Test(priority=1,enabled =false)
	public void verifytheRowisAddedtotheTable() throws InterruptedException, WebDriverException
	{
		try
		{
			loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			 editObj = PageFactory.initElements(driver,Editor.class );
			 softAssert = new SoftAssert();
			 driver.get("https://app.proposify.org");
			    Thread.sleep(7000);
			loginObj.customerLogin();
			Thread.sleep(7000);
			driver.get("https://stageqa.proposify.org/proposal/edit/500023");

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
			
		    Thread.sleep(3000);
				
				List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
				System.out.println(tableRow.size());
			   
				for(WebElement tr:tableRow)
				{
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));
					
					for(WebElement td:tabledata)
					{
						builder.doubleClick(td).perform();
						
						break;
					}
					
			}
		    
		    	
			editObj.changeFeeCurrency("10");
			 builder.moveToElement(editObj.customFormatToggle).click().perform();
		        WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
		        String currSel = curr.getText();
			
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.fillPageFlowTableRows1();
			List<WebElement> tableID_New = driver.findElements(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[contains(@class,'canvas')]"));
			List<WebElement> totalTble = driver.findElements(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));
			System.out.println(tableID_New.size());
			int tableDataID_Updated = 0;
			int id_flag = 0;
			for(WebElement tid :tableID_New )
			{
				id_flag++;
				if(id_flag==totalTble.size())
				{
					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));
					break;
				}
			}
			
			System.out.println(tableDataID_Updated);
			/*for(int i=1;i<= totalTble.size();i++)
			{
				//System.out.println(tableID_New.get(i).getAttribute("data-id"));
				
				
				if(i==tableID_New.size())
				{
					tableDataID_Updated = Integer.parseInt(tableID_New.get(i).getAttribute("data-id"));
				}
				
			}*/
			//System.out.println(tableDataID_Updated);
			
			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[@data-id="+"'"+tableDataID_Updated+"'"+"]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));
			builder.doubleClick(tableFoot1).perform();
			editObj.addTax();
			editObj.fillTaxesPageFlowTable();
			editObj.addTax();
			editObj.fillTaxesPageFlowTable();
			editObj.taxAmountCaluclationPageFlow();
			Thread.sleep(3000);
			
			List<WebElement> tableFooterRow = tableFoot1.findElements(By.tagName("tr"));
		    
			int i = 0;
			for(i=0;i<tableFooterRow.size();i++ )
			{   
				if(i==tableFooterRow.size()-tableFooterRow.size())
				{ 
				   List<WebElement> tableFooterData =tableFooterRow.get(i).findElements(By.tagName("td"));
				  
			       
				   int flg=0;
			         for(WebElement tfData :tableFooterData)
			         {
			        	 flg++;
					    if(flg==1)
					   {
					   String subTotalString = tfData.getText();
					   
					   softAssert.assertTrue(subTotalString.equals("Subtotal"),"Subtotal text verification error");
					   }
					  
					   if(flg==2)
					{
						DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		                decimalFormat.setGroupingUsed(true);
		   	    	    
		   	    	    decimalFormat.setGroupingSize(3);
		   	    		String amt_cal = currSel+decimalFormat.format(editObj.lastRowtamt);
		   	    		String amt = tfData.getText();
		   	    		System.out.println(amt_cal);
		   	    		System.out.println(amt);
		   	    		softAssert.assertTrue( amt_cal.equals(amt),"Subtotal not calculated correctly");
					}
						
					}
				
				
				}
				
				
				if(i==tableFooterRow.size()-3)
				{
					 int flg=0;
					
					List<WebElement> tableFooterData =tableFooterRow.get(i).findElements(By.tagName("td"));
		    	       
					
			         for(WebElement tfData :tableFooterData)
			         {
			        	 flg++;
					  if(flg==1)
					  {
					   String taxString = tfData.getText().trim();
					   
					   System.out.println(taxString);
					   String matchValue="GST 5 %";
					   String n = matchValue.trim();
					   softAssert.assertEquals(taxString,n,"Tax text verification error");
					}
					if(flg==2)
					{
						DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		   	    	    decimalFormat.setGroupingUsed(true);
		   	    	    decimalFormat.setGroupingSize(3);
		   	    	    double addedTaxPercent = Integer.parseInt(tableFooterRow.get(i).getAttribute("data-rate-value"));
		   	    	    double addedTaxValue = (addedTaxPercent * editObj.lastRowtamt)/100;
		   	    		String amt_cal = currSel+decimalFormat.format(addedTaxValue);
		   	    		String amt = tfData.getText();
		   	    		System.out.println(amt_cal);
		   	    		System.out.println(amt);
		   	    		softAssert.assertTrue( amt_cal.equals(amt),"Tax amount not calculated correctly");
					}
					
				
					
					
				}
				}
				
			
		    	if(i==tableFooterRow.size()-1)
		    	
		    	{
		    		
		    		 int flg=0;
		    		 List<WebElement> tableFooterData =tableFooterRow.get(i).findElements(By.tagName("td"));
		    	       
			        
			         for(WebElement tfData :tableFooterData)
			         {
			        	 flg++;
					  if(flg==1)
					  {
					   String subTotalString = tfData.getText();
					   softAssert.assertTrue(subTotalString.equals("Total"),"Total text verification error");
					}
					  
					  if(flg==2)
					  {
		    	    			
		    		    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		                decimalFormat.setGroupingUsed(true);
		   	    	    
		   	    	    decimalFormat.setGroupingSize(3);
		   	    		String amt_cal = currSel+decimalFormat.format(editObj.totalAmount);
		   	    		String amt = tfData.getText();
		   	    		System.out.println(amt_cal);
		   	    		System.out.println(amt);
		   	    		softAssert.assertTrue( amt_cal.equals(amt),"false");
		    		        
		    	    		}
		    	    	}
		    	    		
		    	    	}
			}
			editObj.displayPropertiesPanelforPageFlowTable();
			 editObj.deleteTable();
			 driver.close();
			 softAssert.assertAll();
		}
		    	    
		catch(Exception e)
		{
		System.out.println(e.getMessage());
		if(editObj.phpErrorPopupOKButton1.size()>0)
		{
		driver.navigate().refresh();
		editObj.PageFlowFeeTableSelectSection.click();
		List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
		System.out.println(elem.size());
		for(int i=elem.size()-1;i>=0;i--)
		{
		if(i==0)
		{
		builder.moveToElement(elem.get(i)).click().perform();
		builder.moveToElement(elem.get(i)).click().perform();
		editObj.deletetheElement();
		String elemClass = elem.get(i).getAttribute("class");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('class', '"+elemClass+ "selected deleting');", elem.get(i));
		}

		builder.moveToElement(elem.get(i)).click().perform();
		//builder.moveToElement(elem.get(i)).click().perform();
		editObj.deletetheElement();
		String elemClass = elem.get(i).getAttribute("class");

		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('class', '"+elemClass+ "selected deleting');", elem.get(i));


		}

		int  var=1;
		softAssert.assertFalse(var==1,"Fail");
		driver.close();
		softAssert.assertAll();

		}
		else
		{

		int  var=1;
		softAssert.assertFalse(var==1,"Fail");
		driver.close();
		softAssert.assertAll();
		}

		}

}

	
	@org.testng.annotations.Test(priority=2,enabled=true)
	public void verify_placeHolder_FeeTitle_and_Description_is_Rebuilderd() throws InterruptedException, WebDriverException
	{
		try
		{
			loginObj =PageFactory.initElements(driver,Revamp_Login.class );
			 editObj = PageFactory.initElements(driver,Editor.class );
			 softAssert = new SoftAssert();
			 driver.get("https://dev.proposify.com/login");
	Thread.sleep(7000);
	loginObj.customerLogin();
	Thread.sleep(7000);
	 driver.get("https://dev.proposify.com/login");
		Thread.sleep(7000);
		loginObj.customerLogin();
	driver.get("https://dev.proposify.com/proposal/edit/1740");

	Thread.sleep(7000);
	editObj.insertPageFlowFeeTable("6");
	Thread.sleep(3000);
	WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
	List<WebElement>tablebody = feeTable.findElements(By.tagName("tbody"));
	
	for(WebElement tb:tablebody )
	{
		List<WebElement> tableRow = tb.findElements(By.tagName("tr"));
	
		for(WebElement tr:tableRow)
		{
			List<WebElement> tabledata = tr.findElements(By.tagName("td"));
			
			for(WebElement td:tabledata)
			{
				
				builder.doubleClick(td).perform();
				
				softAssert.assertTrue(td.getText()==" ");
				editObj.displayPropertiesPanelforPageFlowTable();
				editObj.deleteTable();
				 driver.close();
				 softAssert.assertAll();
				break;
				
			
			
			
		}

		}
	}
	
	
		}
	

catch(Exception e)
{
System.out.println(e.getMessage());
if(editObj.phpErrorPopupOKButton1.size()>0)
{
 driver.navigate().refresh();
editObj.PageFlowFeeTableSelectSection.click();
 List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
	System.out.println(elem.size());
	for(int i=elem.size()-1;i>=0;i--)
	{
		if(i==0)
		{
	  builder.moveToElement(elem.get(i)).click().perform();
	  builder.moveToElement(elem.get(i)).click().perform();
     editObj.deletetheElement();
	String elemClass = elem.get(i).getAttribute("class");
	((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('class', '"+elemClass+ "selected deleting');", elem.get(i));
		}
		
		  builder.moveToElement(elem.get(i)).click().perform();
		  //builder.moveToElement(elem.get(i)).click().perform();
	     editObj.deletetheElement();
		String elemClass = elem.get(i).getAttribute("class");
		
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('class', '"+elemClass+ "selected deleting');", elem.get(i));
	
	
	}
	
	int  var=1;
	softAssert.assertFalse(var==1,"Fail");
   driver.close();
   softAssert.assertAll();
 
}
else
{
// editObj.deleteTable();
 int  var=1;
	softAssert.assertFalse(var==1,"Fail");
driver.close();
softAssert.assertAll();
}

}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
	

}
