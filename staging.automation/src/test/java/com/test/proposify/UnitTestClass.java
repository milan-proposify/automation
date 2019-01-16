package com.test.proposify;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Dashboard;
import org.proposify.pages.Revamp_Login;
import org.proposify.pages.Revamp_Proposal_Settings_Page;
import org.proposify.pages.Revamp_Proposal_Snapshot_Page;
import org.proposify.pages.Revamp_Register;
import org.proposify.pages.Revamp_Template_Page;
import org.proposify.pages.Revamp_UserList;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class UnitTestClass {
	static WebDriver driver;

	Revamp_Login loginObj;
	Revamp_Register regObj;
	Revamp_Dashboard dashObj;
	Revamp_UserList userListObj;
	Revamp_Template_Page templObj;
	Revamp_Proposal_Settings_Page propSetObj;
	Revamp_Proposal_Snapshot_Page propSnapObj;

	Editor editObj;
	Actions builder;
	SoftAssert softAssert;
	


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
			driver.get("https://testgdpr2.proposify.org/dashboard");

			if (!driver.getCurrentUrl().equals("https://testgdpr2.proposify.org/dashboard")) {
				driver.navigate().refresh();
				driver.get("https://testgdpr2.proposify.org/dashboard");
			}

		}

	}
	
	public void ExtractJSLogs() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {

			String JSerr = new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();

			if (JSerr.contains("Uncaught TypeError")) {
				System.out.println(JSerr);
			}
		}
	}

	@Test(enabled = true)
	public void verifyDuplicateSectionRename() {
		try {
			
			Thread.sleep(3000);
			WebElement addStream = driver.findElement(By.xpath("//*[@id='pyDashboardToolbarArea']//div[@class='streams']//span[contains(text(),'Add Stream')]"));
			builder.moveToElement(addStream).click().perform();
			Thread.sleep(2000);
			WebElement newStream = driver.findElement(By.xpath("//*[@id='pyDashboardToolbarArea']//input[@placeholder='Stream name']"));
			newStream.clear();
			newStream.sendKeys("test");
			
			WebElement ok = driver.findElement(By.xpath("//*[@id='pyDashboardToolbarArea']//button[@type='submit']/span[@class='svg svg-checkmark']"));
			builder.moveToElement(ok).click().perform();
			
			WebElement stream_name = driver.findElement(By.xpath("//*[contains(@id,'pyDashboardStream')]//span[contains(text(),'test')]"));
			builder.moveToElement(stream_name).click().perform();
			Thread.sleep(3000);
			builder.moveToElement(stream_name).click().perform();
			
			WebElement delete_stream = driver.findElement(By.xpath("//*[contains(@id,'pyDashboardStream')]//a[@title='Delete Stream']"));
			builder.moveToElement(delete_stream).click().perform();
			
			WebElement delete_confirm = driver.findElement(By.xpath("/html/body//button[contains(text(),'Yes')]"));
			builder.moveToElement(delete_confirm).click().perform();
			
			WebElement First_proposal = driver.findElement(By.xpath("//*[@id='pyDashboardSearchResults']/div[2]/div[2]/ul/li[1]/a"));
			builder.moveToElement(First_proposal).perform();
			
			WebElement checkbox = driver.findElement(By.xpath("//*[@id='pyDashboardSearchResults']/div[2]/div[2]/ul/li[1]/div/input"));
			builder.moveToElement(checkbox).click().perform();
			driver.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());

			int var = 1;
			softAssert.assertFalse(var == 1, "Fail");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
			driver.close();
			softAssert.assertAll();

		}

	}


		public void handlePhpError() throws InterruptedException {
		if (editObj.phpErrorPopupOKButton.isDisplayed()) {
			editObj.deleteElementAfterPhpError();
			Thread.sleep(2000);
			List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
			System.out.println(elem.size());
			for (int i = elem.size() - 1; i >= 0; i--) {
				if (i == 0) {
					builder.moveToElement(elem.get(i)).click().perform();
					builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					System.out.println(elemClass + " deleting");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));
				}

				builder.moveToElement(elem.get(i)).click().perform();
				// builder.moveToElement(elem.get(i)).click().perform();
				editObj.deletetheElement();
				String elemClass = elem.get(i).getAttribute("class");
				System.out.println(elemClass + " deleting");
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

			}

		}
	}

	/*
	 * @AfterTest public void tearDown() throws InterruptedException {
	 * Thread.sleep(3000); if(editObj.phpErrorPopupOKButton.isEnabled()) {
	 * editObj.phpErrorPopupOKButton.click(); WebElement
	 * el=driver.findElement(By.xpath("//div[contains(@class,'element table')]"));
	 * builder.moveToElement(el).click(el).perform(); Thread.sleep(2000);
	 * editObj.deletetheElement(); driver.close(); }
	 * 
	 * else { if(driver.getWindowHandle().isEmpty()) driver.close(); }
	 * 
	 * 
	 * }
	 */

	@Test(enabled = false)
	public void verifytherowcolorChanges1() {
		try {
			Thread.sleep(3000);
			loginObj.customerLogin();
			Thread.sleep(3000);
			driver.get("https://dev.proposify.biz/proposal/edit/1740");

			Thread.sleep(3000);
			editObj.FeeTableDragandDrop("2");

			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();

			editObj.fillTableRows();
			editObj.changeFeeCurrency("18");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
			WebElement tableFooterRow = tableFoot.findElement(By.tagName("tr"));
			List<WebElement> tableFooterData = tableFooterRow.findElements(By.tagName("td"));
			int flag2 = 0;
			for (WebElement tfData : tableFooterData) {
				flag2++;
				if (flag2 == 1) {
					String total_Text = tfData.getText();
					System.out.println(total_Text);
					AssertJUnit.assertTrue(total_Text.equals("Total"));
				}

				if (flag2 == 2) {

					DecimalFormat decimalFormat = new DecimalFormat("#.00");
					decimalFormat.setGroupingUsed(true);
					decimalFormat.setGroupingSize(3);
					String amt_cal = currSel + decimalFormat.format(editObj.tamt);
					String amt = tfData.getText();
					AssertJUnit.assertTrue(amt_cal.equals(amt));
				}
			}
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			editObj.deleteTable();
			int var = 1;
			AssertJUnit.assertFalse(null, var == 1);
			driver.close();
			softAssert.assertAll();

		}

	}

}
