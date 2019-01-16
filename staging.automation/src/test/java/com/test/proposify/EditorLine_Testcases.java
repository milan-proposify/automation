package com.test.proposify;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditorLine_Testcases {

	static WebDriver driver;
	Revamp_Login loginObj;
	Editor editObj;
	SoftAssert softAssert;
	Actions builder;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) throws InterruptedException {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Janani\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--start-fullscreen");
			driver = new ChromeDriver(options);
			builder = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://app.proposify.org");

			loginObj = PageFactory.initElements(driver, Revamp_Login.class);
			editObj = PageFactory.initElements(driver, Editor.class);
			softAssert = new SoftAssert();
			loginObj.customerLogin();
			driver.get("https://testgdpr2.proposify.org/proposal/edit/1226360");

			if (!driver.getCurrentUrl().equals("https://testgdpr2.proposify.org/proposal/edit/1226360")) {
				driver.navigate().refresh();
				driver.get("https://testgdpr2.proposify.org/proposal/edit/1226360");
			}

		}

	}

	@Test(priority = 0, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyLineDragandDrop() throws InterruptedException, WebDriverException {
		try {
			editObj.insertLine();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable' )]"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			softAssert.assertTrue(el.isEnabled(), "Line is not present on the Editor");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int var = 1;
			softAssert.assertFalse(var == 1, "Fail");
			driver.close();
			softAssert.assertAll();

		}

	}

	@Test(priority = 1, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyLineWidthProperty() throws InterruptedException, WebDriverException {
		try {
			editObj.insertLine();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			editObj.enterLineWidthProperty("300");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
			Thread.sleep(3000);
			softAssert.assertTrue(
					el.getAttribute("style").contains("width: " + editObj.width.getAttribute("value") + "px;"),
					"TextArea with width " + editObj.width.getAttribute("value") + "is not been adjusted");

			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			int var = 1;
			softAssert.assertFalse(var == 1, "Fail");
			driver.close();
			softAssert.assertAll();

		}
	}

	@Test(priority = 2, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyLineThicknessProperty() throws InterruptedException, WebDriverException {
		try {
			editObj.insertLine();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			editObj.enterLineThicknessProperty("5");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'draggable selected' )]"));
			Thread.sleep(3000);
			softAssert.assertTrue(
					el.getAttribute("style").contains("height: " + editObj.height.getAttribute("value") + "px;"),
					"TextArea with height " + editObj.height.getAttribute("value") + "is not been adjusted");

			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			int var = 1;
			softAssert.assertFalse(var == 1, "Fail");
			driver.close();
			softAssert.assertAll();

		}
	}

	@Test(priority = 3, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyLineCornersProperty() throws InterruptedException, WebDriverException {
		try {
			editObj.insertLine();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			editObj.enterLineCornersProperty("15");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement el = driver
					.findElement(By.xpath("//div[contains(@class,'stretch draggable selected' )]//p[@class='path']"));
			Thread.sleep(3000);
			softAssert.assertTrue(
					el.getAttribute("style")
							.contains("border-radius: " + editObj.corners.getAttribute("value") + "px;"),
					"TextArea with Corners " + editObj.corners.getAttribute("value") + "is not been adjusted");

			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			int var = 1;
			softAssert.assertFalse(var == 1, "Fail");
			driver.close();
			softAssert.assertAll();

		}
	}

	@Test(priority = 4, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyLineRotateProperty() throws InterruptedException, WebDriverException {
		try {

			editObj.insertLine();
			Thread.sleep(3000);
			// editObj.enterTextboxRotateProperty("200");
			editObj.enterLineRotatePropoerty("300");
			// Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected' )]"));
			Thread.sleep(3000);
			softAssert.assertTrue(
					el.getAttribute("style")
							.contains("transform: rotate(" + editObj.rotate.getAttribute("value") + "deg);"),
					"Line with Rotate " + editObj.rotate.getAttribute("value") + "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 5, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheOpacityProperty() throws InterruptedException, WebDriverException {
		try {

			editObj.insertLine();
			Thread.sleep(3000);
			editObj.movetheSlider();
			Thread.sleep(3000);
			WebElement el = driver
					.findElement(By.xpath("//div[contains(@class,'stretch draggable selected' )]//p[@class='path']"));
			System.out.println(el.getAttribute("style"));
			softAssert.assertTrue(
					el.getAttribute("style")
							.contains("opacity: " + editObj.Opacityslider.getAttribute("aria-valuenow") + ";"),
					"Line with Opacity " + editObj.Opacityslider.getAttribute("aria-valuenow")
							+ "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Test(priority = 6, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheLineColorProperty() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			editObj.insertLine();
			editObj.LineChangeColor();
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());

			int var = 1;
			softAssert.assertFalse(var == 1, "Fail");
			driver.close();
			softAssert.assertAll();

		}
	}

}
