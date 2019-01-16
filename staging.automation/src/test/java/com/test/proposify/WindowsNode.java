package com.test.proposify;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowsNode 
{
	WebDriver driver ;
	
	@BeforeMethod()
	public void setUp() throws MalformedURLException
	{
	
	 DesiredCapabilities caps = DesiredCapabilities.edge();
	 
	 driver =new RemoteWebDriver(new URL("http://192.168.190.1:5555/wd/hub"), caps);
	}
	
	@Test
	public void newTest()
	{
		driver.get("https://www.google.ca");
	}
	
	

}
