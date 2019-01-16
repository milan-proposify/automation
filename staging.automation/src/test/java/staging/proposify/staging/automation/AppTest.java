package staging.proposify.staging.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class AppTest {

  public static final String USERNAME = "janani28";
  public static final String AUTOMATE_KEY = "9PNxtkwps39uzBKwEZfN";
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browser","Safari");
    caps.setCapability("browser_version","11.0");
    caps.setCapability("os","OS X");
    caps.setCapability("os_version","High Sierra");
    caps.setCapability("resolution","1024x768");


    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    driver.get("http://www.google.com");
    System.out.println("test");
    WebElement element = driver.findElement(By.name("q"));

    element.sendKeys("BrowserStack");
    element.submit();

    System.out.println(driver.getTitle());
    driver.quit();

  }
}

