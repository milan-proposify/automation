package com.test.proposify;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.testng.asserts.SoftAssert;

public class SanityTests {

	public void setup(String browser) throws InterruptedException {

		ChromeDriver driver;
		
		Actions builder;
		Revamp_Login loginObj;
		Editor editObj;
		SoftAssert softAssert;
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "//Users//Milan//Downloads//chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-fullscreen");
			driver = new ChromeDriver(options);
			builder = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://dev.proposify.com");

			loginObj = PageFactory.initElements(driver, Revamp_Login.class);
			editObj = PageFactory.initElements(driver, Editor.class);
			softAssert = new SoftAssert();
			loginObj.customerLogin();
			driver.get("https://milan0077.proposify.com/proposal/edit/1751");

			if (!driver.getCurrentUrl().equals("https://milan0077.proposify.com/proposal/edit/1751")) {
				driver.navigate().refresh();
				driver.get("https://milan0077.proposify.com/proposal/edit/1751");
			}

		}


	}

}
