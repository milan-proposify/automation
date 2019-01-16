package com.test.proposify;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NewTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "//Users//rajnishparmar//Drivers//Chrome//chromedriver");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		
		 Actions builder = new Actions(driver);
		driver.get("https://dev.proposify.biz/proposal/edit/1737");
		
		Thread.sleep(3000);
		driver.findElement(By.id("pyLoginEmail")).sendKeys("proposify123@gmail.com");
		driver.findElement(By.id("pyLoginPassword")).sendKeys("test12345");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Thread.sleep(3000);
		driver.get("https://dev.proposify.biz/proposal/edit/1738");
		
		Thread.sleep(3000);
		driver.findElement(By.id("pyZoomChange")).click();
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//ul[@class='dropdown-menu']//li//a[@data-zoom='1']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='section1']")).click();
	  List<WebElement> el=	driver.findElements(By.className("section-page"));
	   
	    int count = el.size();
	    
	   

	    
	    System.out.println("------");
	    driver.findElement(By.cssSelector("a[data-id='textbox']")).click();
	    
       List<WebElement> el2 = driver.findElements(By.xpath("//div[@id='pyCanvasList']//div[@class='section-group']//div[@class='canvas can-select loaded']"));
		
		int counts = el2.size();
		
		System.out.println("el2"+counts);
		
		
		
		Thread.sleep(3000);
		
		for(int i = 0;i<counts;i++)
		{
			if(i==counts-1)
			{
				System.out.println("------");
				Thread.sleep(5000);
				
				
				
				builder.moveToElement(el.get(i)).dragAndDropBy(el.get(i), el.get(i).getLocation().getX()+30, el.get(i).getLocation().getY()+30).release().perform();
				
				break;
				
			}
		}
	    
	    
	    List<WebElement> toggleArea = driver.findElements(By.className("checkbox-inline"));
		int count3 = toggleArea.size();
		
		for(int i=0;i<count3;i++)
		{
			toggleArea.get(i).click();
			break;
		}
	
          
		
		
		driver.quit();

	}

}
