package com.test.proposify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Editor_FeeTable_TestCAses {
	static WebDriver driver;
	Actions builder;
	Revamp_Login loginObj;
	Editor editObj;
	SoftAssert softAssert;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) throws InterruptedException {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janani\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--start-fullscreen");
			driver = new ChromeDriver(options);
			builder = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://app.proposify.org");

			loginObj = PageFactory.initElements(driver, Revamp_Login.class);
			editObj = PageFactory.initElements(driver, Editor.class);
			softAssert = new SoftAssert();
			loginObj.customerLogin();
			driver.get("https://milan779.proposify.org/proposal/edit/1226356");

			if (!driver.getCurrentUrl().equals("https://milan779.proposify.org/proposal/edit/1226356")) {
				driver.navigate().refresh();
				driver.get("https://milan779.proposify.org/proposal/edit/1226356");
			}

		}

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "//Users//Milan//Downloads//geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--start-fullscreen");
			driver = new FirefoxDriver(options);
			builder = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://app.proposify.org");

			loginObj = PageFactory.initElements(driver, Revamp_Login.class);
			editObj = PageFactory.initElements(driver, Editor.class);
			softAssert = new SoftAssert();
			loginObj.customerLogin();
			driver.get("https://milan779.proposify.org/proposal/edit/1226356");

			if (!driver.getCurrentUrl().equals("https://milan779.proposify.org/proposal/edit/1226356")) {
				driver.navigate().refresh();
				driver.get("https://milan779.proposify.org/proposal/edit/1226356");
			}

		}

		else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			builder = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://dev.proposify.com");
			loginObj = PageFactory.initElements(driver, Revamp_Login.class);
			editObj = PageFactory.initElements(driver, Editor.class);
			softAssert = new SoftAssert();

		}

	}


	/*
	 * @AfterTest public void tearDown() throws InterruptedException {
	 * Thread.sleep(3000);
	 * 
	 * editObj.phpErrorPopupOKButton.click(); WebElement
	 * el=driver.findElement(By.xpath("//div[contains(@class,'element table')]"));
	 * builder.moveToElement(el).click(el).perform(); Thread.sleep(2000);
	 * editObj.deletetheElement();
	 * 
	 * 
	 * }
	 */

	@Test(priority = 0) // ,retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheFeeTableDragandDrop() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			System.out.println("first checkpoint");
			int tblCount = 0;
			List<WebElement> totalTableCount = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
			tblCount = totalTableCount.size();
			if (tblCount > 0) {
				softAssert.assertTrue(tblCount > 0, "Table available");
			}

			// softAssert.assertTrue(el.isDisplayed(), "Fee table is not present on the
			// Editor");
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 1, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowisAddedtotheTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			WebElement tablePropertiesPane = driver.findElement(By.xpath(".//*[@id='pyPricingTableProperties']"));
			while (!tablePropertiesPane.isDisplayed()) {
				WebElement selectionHolder = driver.findElement(By.xpath(".//*[contains(@class,'element table')]"));
				builder.moveToElement(selectionHolder).click(selectionHolder).perform();

			}
			Thread.sleep(3000);
			System.out.println("Yes");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			System.out.println("NO");
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			System.out.println("Before Add: " + tableRow.size());
			editObj.addRowFeeTable();
			Thread.sleep(3000);

			List<WebElement> tableRowUpdated = tableBody.findElements(By.tagName("tr"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableRowUpdated.size());
			softAssert.assertTrue(tableRowUpdated.size() > tableRow.size(), "New Row is not been Added");

			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");

						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 2, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTheCurrencyChange() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("6");
			Thread.sleep(3000);

			editObj.addRowFeeTable();

			editObj.fillTableRows();
			editObj.displayPropertiesPanel();
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
			List<WebElement> tfrow = tablebody.findElements(By.tagName("tr"));
			int flg = 0;

			for (WebElement tr : tfrow) {
				String amtcalText;
				List<WebElement> tableData = tr.findElements(By.tagName("td"));

				for (WebElement tbData : tableData) {
					flg++;
					if (flg == 2) {
						DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
						decimalFormat1.setGroupingUsed(true);
						decimalFormat1.setGroupingSize(3);
						String amt_cal1 = currSel + decimalFormat1.format(editObj.price);
						System.out.println(amt_cal1);
						System.out.println(tbData.getText());
						amtcalText = tbData.getText() + ".00";
						softAssert.assertTrue(amtcalText.equals(amt_cal1), "first");
					}

					if (flg == 4) {
						DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
						decimalFormat1.setGroupingUsed(true);
						decimalFormat1.setGroupingSize(3);
						String amt_cal1 = currSel + decimalFormat1.format(editObj.price * editObj.qty);
						amtcalText = tbData.getText() + ".00";
						softAssert.assertTrue(amtcalText.equals(amt_cal1), "second");
					}
				}

			}

			WebElement tfRow = tableFoot.findElement(By.tagName("tr"));
			List<WebElement> tfData = tfRow.findElements(By.tagName("td"));
			int flg2 = 0;
			for (WebElement tfTD : tfData) {

				flg2++;

				if (flg2 == 2) {
					DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
					decimalFormat1.setGroupingUsed(true);
					decimalFormat1.setGroupingSize(3);
					String amt_cal1 = currSel + decimalFormat1.format(editObj.tamt);
					String amtcalText = tfTD.getText() + ".00";
					softAssert.assertTrue(amtcalText.equals(amt_cal1), "third");
				}

			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 3, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verify_placeHolder_FeeTitle_and_Description_is_Removed()
			throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> tablebody = feeTable.findElements(By.tagName("tbody"));

			for (WebElement tb : tablebody) {
				List<WebElement> tableRow = tb.findElements(By.tagName("tr"));

				for (WebElement tr : tableRow) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (WebElement td : tabledata) {

						WebElement feeTitle = driver.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[5]/div[2]/div[4]/div[3]/div/div/div/table/tbody/tr/td[1]/p[1]/strong/span"));
						builder.moveToElement(feeTitle);
						builder.click(feeTitle);
						builder.sendKeys("hi");
						builder.perform();
						softAssert.assertTrue(td.getText().contains("hi"));

						WebElement feeDescription = driver.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[5]/div[2]/div[4]/div[3]/div/div/div/table/tbody/tr/td[1]/p[2]/span"));
						builder.moveToElement(feeDescription);
						builder.click(feeDescription);
						builder.sendKeys("hi");
						builder.perform();

						System.out.println("first assert done");
						editObj.displayPropertiesPanel();
						editObj.deleteTable();
						driver.close();
						softAssert.assertAll();
						break;

					}

				}
			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 4, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verify_placeHolder_FeeTitle_and_Description_is_Back() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> tablebody = feeTable.findElements(By.tagName("tbody"));

			for (WebElement tb : tablebody) {
				List<WebElement> tableRow = tb.findElements(By.tagName("tr"));

				for (WebElement tr : tableRow) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (WebElement td : tabledata) {
						System.out.println("entered1");
						builder.doubleClick(td).perform();
						Thread.sleep(3000);

						Thread.sleep(3000);

						builder.sendKeys(td, "HI").sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).perform();

						System.out.println(td.getText());
						softAssert.assertTrue(td.getText().contains("Fee Description"));

						break;

					}

					break;
				}
				break;
			}
			Thread.sleep(3000);
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 5, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheRowisDeleted() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			Thread.sleep(3000);
			editObj.deleteRow();
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> tableBody = feeTable.findElements(By.tagName("tbody"));
			for (WebElement tr : tableBody) {
				List<WebElement> tableRow = tr.findElements(By.tagName("tr"));
				Thread.sleep(3000);
				softAssert.assertTrue(tableRow.size() == 1, "New Row is not been deleted");
				break;
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 30, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyTableisDuplicatedUsingSettingsIcon() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			WebElement eld = driver
					.findElement(By.xpath("//div[contains(@class,'selection-helper draggable multiple')]"));
			builder.moveToElement(eld);
			editObj.duplicateTheElement();

			int tblCount = 0;
			List<WebElement> totalTableCount = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
			tblCount = totalTableCount.size();
			if (tblCount > 0) {
				System.out.println();
				softAssert.assertTrue(tblCount > 0, "Table duplicated");
				editObj.deletetheElement();
			}

			editObj.feeTablePageSetUp();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 7, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheTotalofFeeTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");

			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();

			editObj.fillTableRows();
			WebElement tablePropertiesPane = driver.findElement(By.xpath(".//*[@id='pyPricingTableProperties']"));
			while (!tablePropertiesPane.isDisplayed()) {
				WebElement selectionHolder = driver.findElement(By.xpath(".//*[contains(@class,'element table')]"));
				builder.moveToElement(selectionHolder).click(selectionHolder).perform();

			}

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
					System.out.println("total text" + total_Text);
					softAssert.assertTrue(total_Text.equals("Total"));
				}

				if (flag2 == 2) {

					DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
					decimalFormat.setGroupingUsed(true);
					decimalFormat.setGroupingSize(3);
					String amt_cal = currSel + decimalFormat.format(editObj.tamt);
					String amt = tfData.getText() + ".00";
					softAssert.assertTrue(amt_cal.equals(amt), "true");
				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 8, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })

	public void verifyShowTotalhidingtheTotalValue() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");

			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
			WebElement tableFooterRow = tableFoot.findElement(By.tagName("tr"));
			List<WebElement> tableFooterData = tableFooterRow.findElements(By.tagName("td"));

			Thread.sleep(2000);
			editObj.changeShowTotalToggleOFF();

			int flag2 = 0;
			for (WebElement tfData : tableFooterData) {
				flag2++;
				if (flag2 == 1) {
					String total_Text = tfData.getText();
					System.out.println(total_Text);
					softAssert.assertTrue(total_Text.equals(" "));

				}

				if (flag2 == 2) {

					String amt = tfData.getText();
					System.out.println(amt);
					softAssert.assertTrue(amt.equals(" "), "true");
				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 9, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyShowTotaldisplaysTotalValue() throws WebDriverException {

		try {

			Thread.sleep(3000);
			editObj.insertTable("2");

			Thread.sleep(3000);
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
			/*
			 * int flag = 0; for(WebElement tfData1 :tableFooterData) { flag++; if(flag==1)
			 * { builder.doubleClick(tfData1).perform(); break; } }
			 */

			editObj.changeShowTotalToggleOFF();

			Thread.sleep(2000);

			editObj.changeShowTotalToggleON();
			Thread.sleep(3000);

			int flag2 = 0;
			for (WebElement tfData : tableFooterData) {
				flag2++;
				if (flag2 == 1) {
					String total_Text = tfData.getText();
					softAssert.assertTrue(total_Text.equals("Total"));
				}

				if (flag2 == 2) {

					DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
					decimalFormat.setGroupingUsed(true);
					decimalFormat.setGroupingSize(3);
					String amt_cal = currSel + decimalFormat.format(editObj.tamt);
					String amt = tfData.getText() + ".00";

					softAssert.assertTrue(amt_cal.equals(amt), "true");
				}
			}
			Thread.sleep(3000);
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 10, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheTaxHasBeenAddedtotheTotal() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("6");

			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.fillTableRows();
			editObj.changeFeeCurrency("18");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addTax();
			editObj.fillTaxes();
			editObj.addTax();
			editObj.fillTaxes();
			editObj.taxAmountCaluclation();
			Thread.sleep(3000);

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
			List<WebElement> tableFooterRow = tableFoot.findElements(By.tagName("tr"));

			int i = 0;
			for (i = 0; i < tableFooterRow.size(); i++) {
				if (i == tableFooterRow.size() - tableFooterRow.size()) {
					List<WebElement> tableFooterData = tableFooterRow.get(i).findElements(By.tagName("td"));

					int flg = 0;
					for (WebElement tfData : tableFooterData) {
						flg++;
						if (flg == 1) {
							String subTotalString = tfData.getText();

							softAssert.assertTrue(subTotalString.equals("Subtotal"),
									"Subtotal text verification error");
						}

						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.tamt);
							String amt = tfData.getText() + ".00";
							System.out.println(amt_cal);
							System.out.println(amt);
							softAssert.assertTrue(amt_cal.equals(amt), "Subtotal not calculated correctly");
						}

					}

				}

				if (i == tableFooterRow.size() - 3) {
					int flg = 0;

					List<WebElement> tableFooterData = tableFooterRow.get(i).findElements(By.tagName("td"));

					for (WebElement tfData : tableFooterData) {
						flg++;
						if (flg == 1) {
							String taxString = tfData.getText().trim();

							System.out.println(taxString);
							String matchValue = "GST 5%";
							String n = matchValue.trim();
							System.out.println("n value :" + n);
							softAssert.assertEquals(taxString, n, "Tax text verification error");
						}
						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							double addedTaxPercent = Integer
									.parseInt(tableFooterRow.get(i).getAttribute("data-rate-value"));
							double addedTaxValue = (addedTaxPercent * editObj.tamt) / 100;
							String amt_cal = currSel + decimalFormat.format(addedTaxValue);
							String amt = tfData.getText() + ".00";
							System.out.println(amt_cal);
							System.out.println(amt);
							softAssert.assertTrue(amt_cal.equals(amt), "Tax amount not calculated correctly");
						}

					}
				}

				if (i == tableFooterRow.size() - 1)

				{

					int flg = 0;
					List<WebElement> tableFooterData = tableFooterRow.get(i).findElements(By.tagName("td"));

					for (WebElement tfData : tableFooterData) {
						flg++;
						if (flg == 1) {
							String subTotalString = tfData.getText();
							softAssert.assertTrue(subTotalString.equals("Total"), "Total text verification error");
						}

						if (flg == 2) {

							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.totalAmount);
							String amt = tfData.getText() + ".00";
							System.out.println(amt_cal);
							System.out.println(amt);
							softAssert.assertTrue(amt_cal.equals(amt), "true");

						}
					}

				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}
	}

	@Test(priority = 11, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })

	public void verifytheTaxHasBeenCanceledWithDefaultValue() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			editObj.fillTableRows();

			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addTax();
			editObj.cancelNewTax();
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			WebElement txRow = tableFoot.findElement(By.cssSelector("tr[data-rate-type='tax']"));

			List<WebElement> txData = txRow.findElements(By.tagName("td"));
			System.out.println("Size " + txData.size());
			int flg = 0;
			for (WebElement tfData : txData) {
				flg++;

				if (flg == 1) {
					String tdData1 = tfData.getText();
					System.out.println(tdData1);
					softAssert.assertTrue(tdData1.trim().equals("Tax 0%"), "Tax with 0% not added");
				}
				if (flg == 2) {

					String tdData2 = tfData.getText() + ".00";

					System.out.println(tdData2);
					softAssert.assertTrue(tdData2.equals(currSel + "0.00"), "Tax Amount not displayed as expected");
				}

			}
			editObj.displayPropertiesPanel();

			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 12, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheDiscountHasBeenCanceledWithDefaultValue() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");

			Thread.sleep(3000);
			editObj.fillTableRows();
			System.out.println("fill table rows done");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addDiscount();
			System.out.println("add discount done");
			editObj.cancelNewDiscount();
			System.out.println("cancel discount done");
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			WebElement disRow = tableFoot.findElement(By.cssSelector("tr[data-rate-type='discount']"));
			List<WebElement> txData = disRow.findElements(By.tagName("td"));
			System.out.println("Size " + txData.size());
			int flg = 0;
			for (WebElement tfData : txData) {
				flg++;

				if (flg == 1) {
					String tdData1 = tfData.getText();
					System.out.println(tdData1);
					softAssert.assertTrue(tdData1.trim().equals("Discount 0 %"), "Discount with 0% not added");
				}
				if (flg == 2) {

					String tdData2 = tfData.getText() + ".00";

					System.out.println(tdData2);
					softAssert.assertTrue(tdData2.equals("-" + currSel + "0.00"),
							"Discount Amount not displayed as expected");
				}

			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 13, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifythetaxErrorMessageisDispayed() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("6");

			Thread.sleep(3000);
			editObj.fillTableRows();
			editObj.addTax();
			editObj.fillTaxeswithParameter("GST", "");
			System.out.println("after parametered method");
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			List<WebElement> filltaxRow = tableFoot.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			for (WebElement txRow : filltaxRow) {
				WebElement taxUpdateButton = txRow
						.findElement(By.xpath(".//*[@class='submit btn btn-xs btn-success']"));
				taxUpdateButton.click();
			}
			Thread.sleep(3000);
			WebElement errorMessage = driver
					.findElement(By.xpath("//div[@data-notify='container']//span[@data-notify='message']"));

			System.out.println(errorMessage.getText());
			String errMessage = "You've missed a required field. Please make sure all required fields have values in it";
			softAssert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed");
			softAssert.assertTrue(errorMessage.getText().trim().equals(errMessage.trim()),
					"Error message is different than what expected");
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 14, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheDiscountErrorMessageisDispayed() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.FeeTableDragandDrop("6");

			Thread.sleep(3000);
			editObj.fillTableRows();
			editObj.addDiscount();
			editObj.fillDiscountswithParameter("GST", "");
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			Thread.sleep(3000);
			WebElement errorMessage = driver
					.findElement(By.xpath("//div[@data-notify='container']//span[@data-notify='message']"));

			System.out.println(errorMessage.getText());
			String errMessage = "You've missed a required field. Please make sure all required fields have values in it";
			softAssert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed");
			softAssert.assertTrue(errorMessage.getText().trim().equals(errMessage.trim()),
					"Error message is different than what expected");
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 15, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheexistingmultipletaxDeletedandTotalRecalculated() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.fillTableRows();
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			// WebElement curr =
			// driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			// String currSel = curr.getText();
			editObj.addTax();
			editObj.fillTaxes();
			editObj.addTax();
			editObj.fillTaxes();
			editObj.addTax();
			editObj.fillTaxes();
			editObj.taxAmountCaluclation();
			Thread.sleep(3000);
			double taxAmt = 0;
			double totalTaxPercent = 0;
			double totalTaxAmount = 0;
			double totalTaxAmount_Updated = 0;
			double totalAmount = 0;
			double totalTaxPercent_new = 0;
			double totalTaxAmt = editObj.totalTaxAmount;
			System.out.println("Total  Tax: " + totalTaxAmt);
			double totAmtAfterTax = editObj.totalAmount;
			System.out.println("Total amount After adding Total Tax: " + totAmtAfterTax);

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			List<WebElement> txRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow.size(); i++) {

				String tPercent = txRow.get(i).getAttribute("data-rate-value");

				taxAmt = Double.parseDouble(tPercent);

				totalTaxPercent = taxAmt + totalTaxPercent;

			}
			totalTaxAmount = (totalTaxPercent * editObj.tamt) / 100;

			editObj.cancelExistingTax();
			Thread.sleep(3000);
			editObj.cancelExistingTax();

			List<WebElement> txRow_new = tableFoot.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow_new.size(); i++) {

				String tPercent = txRow_new.get(i).getAttribute("data-rate-value");

				double taxAmt_new = Double.parseDouble(tPercent);

				totalTaxPercent_new = taxAmt_new + totalTaxPercent_new;

			}

			totalTaxAmount_Updated = (totalTaxPercent_new * editObj.tamt) / 100;
			double taxDiff = totalTaxAmount - totalTaxAmount_Updated;

			if (taxDiff > 0) {

				double newTax = totalTaxAmount - taxDiff;
				double totalRefresh = editObj.totalAmount - totalTaxAmount;
				System.out.println(totalTaxAmount_Updated);

				System.out.println("new Tax: " + newTax);
				totalAmount = editObj.tamt + (newTax);
				System.out.println("Total: " + totalAmount);
				softAssert.assertTrue(totalAmount == totalRefresh + newTax);
			}

			else {
				System.out.println(totalTaxAmount);

				totalAmount = editObj.tamt;
				System.out.println("Total: " + totalAmount);
				System.out.println(editObj.totalAmount - totalTaxAmount);
				softAssert.assertTrue(totalAmount == editObj.totalAmount - totalTaxAmount);
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 16, enabled = true, dependsOnMethods = { "verifytheFeeTableDragandDrop",
			"verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheexistingSingletaxDeletedandTotalRecalculated() throws WebDriverException

	{
		try {

			editObj.insertTable("2");
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.fillTableRows();
			editObj.addTax();
			editObj.fillTaxes();
			editObj.taxAmountCaluclation();
			Thread.sleep(3000);
			double taxAmt = 0;
			double totalTaxPercent = 0;
			double totalTaxAmount = 0;
			double totalTaxAmount_Updated = 0;
			double totalAmount = 0;
			double totalTaxPercent_new = 0;
			double totalTaxAmt = editObj.totalTaxAmount;
			System.out.println("Total  Tax: " + totalTaxAmt);
			double totAmtAfterTax = editObj.totalAmount;
			System.out.println("Total amount After adding Total Tax: " + totAmtAfterTax);

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			List<WebElement> txRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow.size(); i++) {

				String tPercent = txRow.get(i).getAttribute("data-rate-value");

				taxAmt = Double.parseDouble(tPercent);

				totalTaxPercent = taxAmt + totalTaxPercent;

			}
			totalTaxAmount = (totalTaxPercent * editObj.tamt) / 100;

			editObj.cancelExistingTax();
			Thread.sleep(3000);
			editObj.cancelExistingTax();

			List<WebElement> txRow_new = tableFoot.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow_new.size(); i++) {

				String tPercent = txRow_new.get(i).getAttribute("data-rate-value");

				double taxAmt_new = Double.parseDouble(tPercent);

				totalTaxPercent_new = taxAmt_new + totalTaxPercent_new;

			}

			totalTaxAmount_Updated = (totalTaxPercent_new * editObj.tamt) / 100;
			double taxDiff = totalTaxAmount - totalTaxAmount_Updated;

			if (taxDiff > 0) {

				double newTax = totalTaxAmount - taxDiff;
				double totalRefresh = editObj.totalAmount - totalTaxAmount;
				System.out.println(totalTaxAmount_Updated);

				System.out.println("new Tax: " + newTax);
				totalAmount = editObj.tamt + (newTax);
				System.out.println("Total: " + totalAmount);
				softAssert.assertTrue(totalAmount == totalRefresh + newTax);
			}

			else {
				System.out.println(totalTaxAmount);

				totalAmount = editObj.tamt;
				System.out.println("Total: " + totalAmount);
				System.out.println(editObj.totalAmount - totalTaxAmount);
				softAssert.assertTrue(totalAmount == editObj.totalAmount - totalTaxAmount);
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 17, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheDiscountHasBeenDeductFromtheTotal() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");

			Thread.sleep(2000);
			editObj.fillTableRows();
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addDiscount();
			editObj.fillDiscounts();
			editObj.discountAmountCaluclation();
			Thread.sleep(3000);

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
			List<WebElement> tableFooterRow = tableFoot.findElements(By.tagName("tr"));

			int i = 0;
			for (i = 0; i < tableFooterRow.size(); i++) {
				if (i == tableFooterRow.size() - tableFooterRow.size()) {
					List<WebElement> tableFooterData = tableFooterRow.get(i).findElements(By.tagName("td"));

					int flg = 0;
					for (WebElement tfData : tableFooterData) {
						flg++;
						if (flg == 1) {
							String subTotalString = tfData.getText();

							Assert.assertTrue(subTotalString.equals("Subtotal"), "Subtotal text verification error");
						}

						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.tamt);
							String amt = tfData.getText() + ".00";
							System.out.println(amt_cal);
							System.out.println(amt);
							softAssert.assertTrue(amt_cal.equals(amt), "Subtotal not calculated correctly");
						}

					}

				}

				if (i == tableFooterRow.size() - 2) {
					int flg = 0;

					List<WebElement> tableFooterData = tableFooterRow.get(i).findElements(By.tagName("td"));

					for (WebElement tfData : tableFooterData) {
						flg++;
						if (flg == 1) {
							String discountString = tfData.getText().trim();

							System.out.println(discountString);
							String matchValue = "Discount 5%";
							String n = matchValue.trim();
							softAssert.assertEquals(discountString, n, "Discount text verification error");
						}
						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							double addeddiscountPercent = Integer
									.parseInt(tableFooterRow.get(i).getAttribute("data-rate-value"));
							double addedDiscountValue = (addeddiscountPercent * editObj.tamt) / 100;
							String amt_cal = "-" + currSel + decimalFormat.format(addedDiscountValue);
							String amt = tfData.getText() + ".00";
							System.out.println(amt_cal);
							System.out.println(amt);
							softAssert.assertTrue(amt_cal.equals(amt), "Discount amount not calculated correctly");
						}

					}
				}

				if (i == tableFooterRow.size() - 1)

				{

					int flg = 0;
					List<WebElement> tableFooterData = tableFooterRow.get(i).findElements(By.tagName("td"));

					for (WebElement tfData : tableFooterData) {
						flg++;
						if (flg == 1) {
							String subTotalString = tfData.getText();
							softAssert.assertTrue(subTotalString.equals("Total"), "Total text verification error");
						}

						if (flg == 2) {

							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.dtotalAmount);
							String amt = tfData.getText() + ".00";
							System.out.println(amt_cal);
							System.out.println(amt);
							softAssert.assertTrue(amt_cal.equals(amt), "true");

						}
					}

				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 18, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheexistingmultipleDiscountDeletedandTotalRecalculated() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.fillTableRows();
			editObj.addDiscount();
			editObj.fillDiscountswithParameter("GST", "5");
			editObj.addDiscount();
			editObj.fillDiscounts();
			editObj.discountAmountCaluclation();
			Thread.sleep(3000);
			double disAmt = 0;
			// double dis=0;
			double totaldisAmount = 0;
			double totaldisAmount_Updated = 0;
			double totalAmount = 0;
			double totaldisPercent = 0;
			double totaldisPercent_new = 0;
			double totaldisAmt = editObj.totalDiscAmount;
			System.out.println("Total  Discount: " + totaldisAmt);
			double totAmtAfterdis = editObj.dtotalAmount;
			System.out.println("Total amount After reducing Total discount: " + totAmtAfterdis);

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			List<WebElement> txRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='discount']"));

			for (int i = 0; i < txRow.size(); i++) {

				String tPercent = txRow.get(i).getAttribute("data-rate-value");

				disAmt = Double.parseDouble(tPercent);

				totaldisPercent = disAmt + totaldisPercent;

			}
			totaldisAmount = (totaldisPercent * editObj.tamt) / 100;

			editObj.cancelExistingDiscount();
			Thread.sleep(3000);

			List<WebElement> disRow_new = tableFoot.findElements(By.cssSelector("tr[data-rate-type='discount']"));

			for (int i = 0; i < disRow_new.size(); i++) {

				String tPercent = disRow_new.get(i).getAttribute("data-rate-value");

				double disAmt_new = Double.parseDouble(tPercent);

				totaldisPercent_new = disAmt_new + totaldisPercent_new;

			}

			totaldisAmount_Updated = (totaldisPercent_new * editObj.tamt) / 100;
			double disDiff = totaldisAmount - totaldisAmount_Updated;

			if (disDiff > 0) {

				double newDis = totaldisAmount - disDiff;
				double totalRefresh = editObj.dtotalAmount + totaldisAmount;
				System.out.println(totaldisAmount_Updated);
				System.out.println("Exact " + editObj.totalAmount);

				System.out.println("new Discount: " + newDis);
				totalAmount = editObj.tamt - (newDis);
				System.out.println("Total: " + totalAmount);
				System.out.println("TotalRefresh: " + totalRefresh);
				softAssert.assertTrue(totalAmount == totalRefresh - newDis);
			}

			else {
				System.out.println(totaldisAmount);

				totalAmount = editObj.tamt;
				System.out.println("Total: " + totalAmount);
				System.out.println(editObj.totalAmount + totaldisAmount);
				softAssert.assertTrue(totalAmount == editObj.totalAmount + totaldisAmount);
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 19, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyUnitperQtyRowType() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			String tdCol_1Text = null;
			String tdCol_2Text = null;
			String tdCol_3Text = null;
			String tdCol_4Text = null;
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> feetableheade = feeTable.findElements(By.tagName("thead"));

			for (WebElement theader : feetableheade) {
				List<WebElement> trow = theader.findElements(By.tagName("tr"));

				for (WebElement tr : trow) {
					List<WebElement> tdata = tr.findElements(By.tagName("th"));
					int flg = 0;
					for (WebElement td : tdata) {
						flg++;

						if (flg == 1) {
							tdCol_1Text = td.getText();
							System.out.println(tdCol_1Text);
							softAssert.assertTrue(tdCol_1Text.equals("Description"));
						}

						if (flg == 2) {
							tdCol_2Text = td.getText();
							System.out.println(tdCol_2Text);
							softAssert.assertTrue(tdCol_2Text.equals("Unit"));
						}

						if (flg == 3) {
							tdCol_3Text = td.getText();
							System.out.println(tdCol_3Text);
							softAssert.assertTrue(tdCol_3Text.equals("Qty"));
						}

						if (flg == 4) {
							tdCol_4Text = td.getText();
							System.out.println(tdCol_4Text);
							softAssert.assertTrue(tdCol_4Text.equals("Price"));
						}
					}
				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 20, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyFixedRowType() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("1");

			Thread.sleep(3000);
			String tdCol_1Text = null;
			String tdCol_2Text = null;
			String tdCol_3Text = null;
			String tdCol_4Text = null;

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> feetableheade = feeTable.findElements(By.tagName("thead"));

			for (WebElement theader : feetableheade) {
				List<WebElement> trow = theader.findElements(By.tagName("tr"));

				for (WebElement tr : trow) {
					List<WebElement> tdata = tr.findElements(By.tagName("th"));
					int flg = 0;
					for (WebElement td : tdata) {
						flg++;

						if (flg == 1) {
							tdCol_1Text = td.getText();
							System.out.println(tdCol_1Text);
							softAssert.assertTrue(tdCol_1Text.equalsIgnoreCase("Description"));
						}
						if (flg == 2) {
							tdCol_2Text = td.getText();
							System.out.println(tdCol_2Text);
							softAssert.assertTrue(tdCol_2Text.equals(""));
						}

						if (flg == 3) {
							tdCol_3Text = td.getText().trim();
							System.out.println(tdCol_3Text);
							softAssert.assertTrue(tdCol_3Text.equals(""));
						}

						if (flg == 4) {
							tdCol_4Text = td.getText();
							System.out.println(tdCol_4Text);
							softAssert.assertTrue(tdCol_4Text.equals("Price"));
						}

					}
				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 21, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyHourlyRowType() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("5");
			editObj.changeFeeCurrency("10");
			editObj.fillTableRows();
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();

			Thread.sleep(3000);
			String tdCol_1Text = null;
			String tdCol_2Text = null;
			String tdCol_3Text = null;
			String tdCol_4Text = null;
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> feetableheade = feeTable.findElements(By.tagName("thead"));

			for (WebElement theader : feetableheade) {
				List<WebElement> trow = theader.findElements(By.tagName("tr"));

				for (WebElement tr : trow) {
					List<WebElement> tdata = tr.findElements(By.tagName("th"));
					int flg = 0;
					for (WebElement td : tdata) {
						flg++;

						if (flg == 1) {
							tdCol_1Text = td.getText();
							System.out.println(tdCol_1Text);
							softAssert.assertTrue(tdCol_1Text.equalsIgnoreCase("Description"));
						}

						if (flg == 2) {
							tdCol_2Text = td.getText();
							System.out.println(tdCol_2Text);
							softAssert.assertTrue(tdCol_2Text.equalsIgnoreCase("Unit"));
						}
						if (flg == 3) {
							tdCol_3Text = td.getText();
							System.out.println(tdCol_3Text);
							softAssert.assertTrue(tdCol_3Text.equalsIgnoreCase("Hours"));
						}

						if (flg == 4) {
							tdCol_4Text = td.getText();
							System.out.println(tdCol_4Text);
							softAssert.assertTrue(tdCol_4Text.equalsIgnoreCase("Price"));
						}

					}
				}
			}
			WebElement tbody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> trow = tbody.findElements(By.tagName("tr"));
			int flag = 0;
			for (WebElement tr : trow) {
				List<WebElement> tdata = tr.findElements(By.tagName("td"));

				for (WebElement td : tdata) {
					flag++;
					if (flag == 2) {

						String enteredUnit = currSel + (editObj.priceint) + " / hour";
						System.out.println(enteredUnit);
						System.out.println(td.getText());
						softAssert.assertTrue(enteredUnit.equals(td.getText()), "new");

					}
				}
			}
			Thread.sleep(3000);
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 22, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyMonthlyRowType() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("3");

			editObj.fillTableRows();
			// editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			Thread.sleep(3000);
			String tdCol_1Text = null;
			String tdCol_2Text = null;
			String tdCol_3Text = null;
			String tdCol_4Text = null;
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> feetableheade = feeTable.findElements(By.tagName("thead"));

			for (WebElement theader : feetableheade) {
				List<WebElement> trow = theader.findElements(By.tagName("tr"));

				for (WebElement tr : trow) {
					List<WebElement> tdata = tr.findElements(By.tagName("th"));
					int flg = 0;
					for (WebElement td : tdata) {
						flg++;

						if (flg == 1) {
							tdCol_1Text = td.getText();
							System.out.println(tdCol_1Text);
							softAssert.assertTrue(tdCol_1Text.equalsIgnoreCase("Description"));
						}

						if (flg == 2) {
							tdCol_2Text = td.getText();
							System.out.println(tdCol_2Text);
							softAssert.assertTrue(tdCol_2Text.equalsIgnoreCase("Unit"));
						}
						if (flg == 3) {
							tdCol_3Text = td.getText();
							System.out.println(tdCol_3Text);
							softAssert.assertTrue(tdCol_3Text.equalsIgnoreCase("Months"));
						}

						if (flg == 4) {
							tdCol_4Text = td.getText();
							System.out.println(tdCol_4Text);
							softAssert.assertTrue(tdCol_4Text.equalsIgnoreCase("Price"));
						}

					}
				}
			}
			WebElement tbody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> trow = tbody.findElements(By.tagName("tr"));
			int flag = 0;
			for (WebElement tr : trow) {
				List<WebElement> tdata = tr.findElements(By.tagName("td"));

				for (WebElement td : tdata) {
					flag++;
					if (flag == 2) {
						String enteredUnit = currSel + (editObj.priceint) + " / Month";
						System.out.println(enteredUnit);
						System.out.println(td.getText().trim());
						softAssert.assertTrue(enteredUnit.trim().equalsIgnoreCase(td.getText().trim()));

					}
				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 23, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyAnnualRowType() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("4");

			editObj.fillTableRows();
			// editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			Thread.sleep(3000);
			String tdCol_1Text = null;
			String tdCol_2Text = null;
			String tdCol_3Text = null;
			String tdCol_4Text = null;
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> feetableheade = feeTable.findElements(By.tagName("thead"));

			for (WebElement theader : feetableheade) {
				List<WebElement> trow = theader.findElements(By.tagName("tr"));

				for (WebElement tr : trow) {
					List<WebElement> tdata = tr.findElements(By.tagName("th"));
					int flg = 0;
					for (WebElement td : tdata) {
						flg++;

						if (flg == 1) {
							tdCol_1Text = td.getText();
							System.out.println(tdCol_1Text);
							softAssert.assertTrue(tdCol_1Text.equalsIgnoreCase("Description"));
						}

						if (flg == 2) {
							tdCol_2Text = td.getText();
							System.out.println(tdCol_2Text);
							softAssert.assertTrue(tdCol_2Text.equalsIgnoreCase("Unit"));
						}
						if (flg == 3) {
							tdCol_3Text = td.getText();
							System.out.println(tdCol_3Text);
							softAssert.assertTrue(tdCol_3Text.equalsIgnoreCase("Years"));
						}

						if (flg == 4) {
							tdCol_4Text = td.getText();
							System.out.println(tdCol_4Text);
							softAssert.assertTrue(tdCol_4Text.equalsIgnoreCase("Price"));
						}

					}
				}

			}
			WebElement tbody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> trow = tbody.findElements(By.tagName("tr"));
			int flag = 0;
			for (WebElement tr : trow) {
				List<WebElement> tdata = tr.findElements(By.tagName("td"));

				for (WebElement td : tdata) {
					flag++;
					if (flag == 2) {
						String enteredUnit = currSel + (editObj.priceint) + " / Year";
						System.out.println(enteredUnit);
						System.out.println(td.getText().trim());
						softAssert.assertTrue(enteredUnit.trim().equalsIgnoreCase(td.getText().trim()));

					}
				}
			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 24, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytherowcolorChanges() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			Thread.sleep(3000);
			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}
				break;

			}

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
					driver.findElement(By.xpath(".//*[@class='col-xs-7']//div[@class='input-group-btn']")));
			editObj.changeRowColor();
			Thread.sleep(3000);

			List<WebElement> trow = tablebody.findElements(By.tagName("tr"));
			int flag = 0;
			for (int i = 0; i < trow.size(); i = +2) {
				flag++;
				List<WebElement> tdata = trow.get(i).findElements(By.tagName("td"));
				for (WebElement td : tdata) {
					if (flag == 1) {

						String rowColor = td.getAttribute("style");
						softAssert.assertTrue(rowColor.contains(editObj.colorStyle1));

					}

					if (flag == 2) {

						String rowColor = td.getAttribute("style");
						softAssert.assertTrue(rowColor.contains(editObj.colorStyle2));

					}
					break;
				}

				if (flag == 3) {
					break;
				}

			}
			editObj.displayPropertiesPanel();

			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 25, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyOptionalforClientCheckBoxAppears() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.fillTableRows();

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> trow = tablebody.findElements(By.tagName("tr"));
			// int flag=0;
			for (int i = 0; i < trow.size();) { // flag++;
				List<WebElement> tdata = trow.get(i).findElements(By.tagName("td"));
				for (WebElement td : tdata) {
					builder.doubleClick(td).perform();
					break;

				}

				break;

			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
					driver.findElement(By.xpath(".//*[@class='col-xs-7']//div[@class='input-group-btn']")));
			Thread.sleep(3000);
			editObj.optionalForClientOptionTurnON();

			for (int i = 0; i < trow.size();) {
				System.out.println("---");
				List<WebElement> tdata = trow.get(i).findElements(By.cssSelector("td[class='fr-selected-cell']"));
				for (WebElement td : tdata) {
					System.out.println("---");
					WebElement optionalCheckBox = td.findElement(By.xpath(
							"//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/span/input"));

					softAssert.assertTrue(optionalCheckBox.isDisplayed(),"optional checkbox not displayed");

				}
				break;

			}
			Thread.sleep(4000);
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 26, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })

	public void verifyOptionalforClientCheckBoxDisappers() throws WebDriverException {

		try {

			Thread.sleep(3000);
			editObj.insertTable("6");
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.fillTableRows();

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> trow = tablebody.findElements(By.tagName("tr"));
			// int flag=0;
			for (int i = 0; i < trow.size();) { // flag++;
				List<WebElement> tdata = trow.get(i).findElements(By.tagName("td"));
				for (WebElement td : tdata) {
					builder.doubleClick(td).perform();
					break;

				}

				break;

			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
					driver.findElement(By.xpath(".//*[@class='col-xs-7']//div[@class='input-group-btn']")));
			Thread.sleep(3000);
			editObj.optionalForClientOptionTurnON();
			Thread.sleep(3000);
			editObj.optionalForClientOptionOFF();

			for (int i = 0; i < trow.size();) {
				System.out.println("---");
				List<WebElement> tdata = trow.get(i).findElements(By.cssSelector("td[class='fr-selected-cell']"));
				for (WebElement td : tdata) {
					System.out.println("---");
					List<WebElement> optionalCheckBox = td.findElements(By.xpath(
							"//*[@id=\"pyCanvasList\"]/div[5]/div[2]/div[4]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/span/input"));

					for (WebElement tot : optionalCheckBox) {
						softAssert.assertFalse(tot.isDisplayed());
					}
				}
				break;

			}
			Thread.sleep(4000);
			// editObj.feeTablePageSetUp();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 27, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheTotalRecalculatedOnoptionalfeeCheckandUncheck() throws WebDriverException {
		try {
			String currSel = null;

			Thread.sleep(3000);
			editObj.insertTable("6");

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
			Thread.sleep(3000);
			List<WebElement> trow = tablebody.findElements(By.tagName("tr"));
			// int flag=0;
			for (int i = 0; i < trow.size();) { // flag++;
				List<WebElement> tdata = trow.get(i).findElements(By.tagName("td"));
				for (WebElement td : tdata) {
					builder.doubleClick(td).perform();
					editObj.changeFeeCurrency("10");
					builder.moveToElement(editObj.customFormatToggle).click().perform();
					WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
					currSel = curr.getText();
					break;

				}

				break;

			}
			editObj.addRowFeeTable();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
					driver.findElement(By.xpath(".//*[@class='col-xs-7']//div[@class='input-group-btn']")));
			Thread.sleep(4000);
			editObj.optionalForClientOptionTurnON();
			editObj.addRowFeeTable();
			Thread.sleep(4000);
			editObj.optionalForClientOptionOFF();
			editObj.addRowFeeTable();
			Thread.sleep(4000);
			editObj.optionalForClientOptionTurnON();
			editObj.fillTableRows();

			Thread.sleep(3000);

			clickCB();
			int flg = 0;
			double totalBeforeCheckFee = editObj.tamt - editObj.checkboxTotalAmt;
			List<WebElement> tfrow = tableFoot.findElements(By.tagName("tr"));
			for (WebElement tr : tfrow) {
				List<WebElement> tableFooterData = tr.findElements(By.tagName("td"));

				for (WebElement tfData : tableFooterData) {
					flg++;
					if (flg == 2) {
						DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
						decimalFormat.setGroupingUsed(true);
						decimalFormat.setGroupingSize(3);
						String amt_cal = currSel + decimalFormat.format(totalBeforeCheckFee);
						String amt = tfData.getText();
						System.out.println(amt_cal);
						System.out.println(amt);
						softAssert.assertTrue(amt_cal.equals(amt + ".00"), "true");

						clickCB();
						double totalAfterCheckFee = totalBeforeCheckFee + editObj.checkboxTotalAmt;
						System.out.println(totalAfterCheckFee);

						DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
						decimalFormat1.setGroupingUsed(true);
						decimalFormat1.setGroupingSize(3);
						String amt_cal1 = currSel + decimalFormat1.format(totalAfterCheckFee);
						String amt1 = tfData.getText();
						System.out.println(amt_cal1);
						System.out.println(amt1);
						softAssert.assertTrue(amt_cal.equals(amt + ".00"), "true");

					}
				}

			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	public void clickCB() throws InterruptedException {
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
		// WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

		List<WebElement> trow = tablebody.findElements(By.tagName("tr"));
		for (int i = 0; i < trow.size();) {
			List<WebElement> tdata = trow.get(i).findElements(By.tagName("td"));
			for (WebElement td : tdata) {
				List<WebElement> optionalCheckBox = td
						.findElements(By.xpath("//span[@style='display: inline;']//input[@type='checkbox']"));
				System.out.println(optionalCheckBox.size());

				for (WebElement cb : optionalCheckBox) {
					Thread.sleep(3000);
					cb.click();
				}
				break;
			}

			break;

		}
	}

	@Test(priority = 28, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifytheTotalRecalculatedOnQtyeditedinEditableOption() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("6");

			editObj.addRowFeeTable();
			editObj.addRowFeeTable();
			editObj.changeFeeCurrency("10");
			editObj.fillTableRows();

			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();

			double totalBeforeEditableQty = editObj.tamt;
			System.out.println(totalBeforeEditableQty);
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

			int flg = 0;
			int flag = 0;
			int flag2 = 0;
			int f_flag = 0;
			int f_flag2 = 0;
			double qty1 = 0;
			double qty2 = 0;
			String editQty1 = null;
			String editQty2 = null;
			double newPrice1 = 0;
			double newPrice2 = 0;
			String totalText1 = null;
			String totalText2 = null;
			double totalAfterEditableQty = 0;
			List<WebElement> tfrow = tablebody.findElements(By.tagName("tr"));
			for (WebElement tr : tfrow) {
				flg++;
				List<WebElement> tableData = tr.findElements(By.tagName("td"));
				for (WebElement tfData : tableData) {

					if (flg == 2) {

						flag++;
						if (flag == 3) {
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver
									.findElement(By.xpath(".//*[@class='col-xs-7']//div[@class='input-group-btn']")));
							builder.doubleClick(tfData).perform();
							editObj.editableQuantityOptionTurnON();
							WebElement editableTB = tfData.findElement(By.xpath(
									".//*[@class='input-group bootstrap-touchspin']//input[@class='form-control']"));

							editQty1 = "50";
							builder.doubleClick(editableTB).sendKeys(editQty1).perform();
							qty1 = editObj.qty;
							newPrice1 = editObj.price * (qty1 - (Double.parseDouble(editQty1)));

						}
						if (flag == 4) {
							builder.click(tfData).perform();
							tableFoot.click();

							Thread.sleep(4000);
							List<WebElement> fTRow = tableFoot.findElements(By.tagName("tr"));

							for (WebElement ftr : fTRow) {

								List<WebElement> ftdata = ftr.findElements(By.tagName("td"));

								for (WebElement ftd : ftdata) {
									f_flag++;
									if (f_flag == 2) {
										builder.doubleClick(ftd).perform();
										Thread.sleep(3000);
										WebElement selTd = ftr
												.findElement(By.cssSelector("td[class='fr-selected-cell']"));
										totalText1 = selTd.getText();
										System.out.println("1 " + totalText1);
									}

								}

							}
						}

					}

					if (flg == 3) {

						flag2++;

						if (flag2 == 3) {
							builder.doubleClick(tfData).perform();

							editObj.editableQuantityOptionTurnON();
							WebElement editableTB = tfData.findElement(By.xpath(
									".//*[@class='input-group bootstrap-touchspin']//input[@class='form-control']"));

							editQty2 = "200";

							builder.doubleClick(editableTB).sendKeys(editQty2).perform();
							qty2 = editObj.qty;
							newPrice2 = editObj.price * ((Double.parseDouble(editQty2)) - qty2);
						}

						if (flag2 == 4) {
							builder.click(tfData).perform();
							tableFoot.click();
							List<WebElement> fTRow = tableFoot.findElements(By.tagName("tr"));

							for (WebElement ftr : fTRow) {

								List<WebElement> ftdata = ftr.findElements(By.tagName("td"));

								for (WebElement ftd : ftdata) {
									f_flag2++;
									if (f_flag2 == 2) {
										builder.doubleClick(ftd).perform();
										Thread.sleep(3000);
										WebElement selTd = ftr
												.findElement(By.cssSelector("td[class='fr-selected-cell']"));
										totalText2 = selTd.getText();
										System.out.println("2 " + totalText2);
									}
								}

							}
						}
					}

				}

			}

			if ((Double.parseDouble(editQty1)) > editObj.qty) {

				totalAfterEditableQty = editObj.tamt + newPrice1;
			}

			else if ((Double.parseDouble(editQty1)) < editObj.qty) {

				totalAfterEditableQty = editObj.tamt - newPrice1;
				DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
				decimalFormat.setGroupingUsed(true);
				decimalFormat.setGroupingSize(3);
				String amt_cal = currSel + decimalFormat.format(totalAfterEditableQty);

				System.out.println("edit1 " + totalAfterEditableQty);
				softAssert.assertTrue(amt_cal.equals(totalText1 + ".00"), "Didn't reduce");
			}

			if ((Double.parseDouble(editQty2)) > editObj.qty) {

				totalAfterEditableQty = totalAfterEditableQty + newPrice2;
				DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
				decimalFormat.setGroupingUsed(true);
				decimalFormat.setGroupingSize(3);
				String amt_cal = currSel + decimalFormat.format(totalAfterEditableQty);
				System.out.println("edit2 " + totalAfterEditableQty);
				softAssert.assertTrue(amt_cal.equals(totalText2 + ".00"), "Didn't increase");

			}

			else if ((Double.parseDouble(editQty2)) < editObj.qty) {

				totalAfterEditableQty = totalAfterEditableQty - newPrice2;

			}
			editObj.displayPropertiesPanel();

			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();
						editObj.deletetheElement();
						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

	@Test(priority = 29, enabled = true, retryAnalyzer = com.test.proposify.RetryAnalyzer.class, dependsOnMethods = {
			"verifytheFeeTableDragandDrop", "verifytheRowisAddedtotheTable", "verifyTheCurrencyChange" })
	public void verifyTheCurrencyChangeAfterFillingTable() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("6");
			Thread.sleep(3000);

			editObj.addRowFeeTable();
			editObj.changeFeeCurrency("10");
			editObj.fillTableRows();

			editObj.changeFeeCurrency("4");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
			WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
			List<WebElement> tfrow = tablebody.findElements(By.tagName("tr"));
			int flg = 0;
			for (WebElement tr : tfrow) {

				List<WebElement> tableData = tr.findElements(By.tagName("td"));
				for (WebElement tbData : tableData) {
					flg++;
					if (flg == 2) {
						DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
						decimalFormat1.setGroupingUsed(true);
						decimalFormat1.setGroupingSize(3);
						String amt_cal1 = currSel + decimalFormat1.format(editObj.price);
						System.out.println(amt_cal1);
						System.out.println(tbData.getText());
						softAssert.assertTrue(amt_cal1.equals(tbData.getText() + ".00"), "first");
					}

					if (flg == 4) {
						DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
						decimalFormat1.setGroupingUsed(true);
						decimalFormat1.setGroupingSize(3);
						String amt_cal1 = currSel + decimalFormat1.format(editObj.price * editObj.qty);
						System.out.println(amt_cal1);

						System.out.println(amt_cal1);
						softAssert.assertTrue(amt_cal1.equals(tbData.getText() + ".00"), "second");
					}
				}

			}

			WebElement tfRow = tableFoot.findElement(By.tagName("tr"));
			List<WebElement> tfData = tfRow.findElements(By.tagName("td"));
			int flg2 = 0;
			for (WebElement tfTD : tfData) {
				flg2++;

				if (flg2 == 2) {
					DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
					decimalFormat1.setGroupingUsed(true);
					decimalFormat1.setGroupingSize(3);
					String amt_cal1 = currSel + decimalFormat1.format(editObj.tamt);
					System.out.println(amt_cal1);
					System.out.println(tfTD.getText());
					softAssert.assertTrue(amt_cal1.equals(tfTD.getText() + ".00"), "third");
				}

			}
			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.StandAloneTableSelectSection.click();
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
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();
					editObj.deletetheElement();
					String elemClass = elem.get(i).getAttribute("class");
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');", elem.get(i));

				}

				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();

			} else {
				// editObj.deleteTable();
				int var = 1;
				softAssert.assertFalse(var == 1, "Fail");
				driver.close();
				softAssert.assertAll();
			}

		}

	}

}
