package com.test.proposify;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.JavascriptExecutor;

public class Editor_PageFlowFeeTable_TestCases {

	static WebDriver driver;

	Revamp_Login loginObj;
	Editor editObj;
	Actions builder;
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

	@Test(priority = 0, enabled = true)
	public void verifyPageFlowFeeTableDragAndDrop() throws WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
			Thread.sleep(3000);
			int tblCount = 0;
			List<WebElement> totalTableCount = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));
			tblCount = totalTableCount.size();
			if (tblCount > 0) {
				softAssert.assertTrue(tblCount > 0, "Table available");
			}

			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 1, enabled = true)
	public void verifytheRowisAddedtotheTable() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");

			Thread.sleep(3000);
			System.out.println("Yes");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			System.out.println("NO");
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			System.out.println("Before Add: " + tableRow.size());
			editObj.addRowPageFlow();
			Thread.sleep(3000);

			List<WebElement> tableRowUpdated = tableBody.findElements(By.tagName("tr"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableRowUpdated.size());
			softAssert.assertTrue(tableRowUpdated.size() > tableRow.size(), "New Row is not been Added");

			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 2, enabled = false)
	public void verify_placeHolder_FeeTitle_and_Description_is_Rebuilderd()
			throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			List<WebElement> tablebody = feeTable.findElements(By.tagName("tbody"));

			for (WebElement tb : tablebody) {
				List<WebElement> tableRow = tb.findElements(By.tagName("tr"));

				for (WebElement tr : tableRow) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (WebElement td : tabledata) {

						builder.doubleClick(td).perform();
						System.out.println("table data" + td.getText());
						softAssert.assertTrue(td.getText().contains("Fee Description"));
						editObj.displayPropertiesPanelforPageFlowTable();
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
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 3, enabled = true)
	public void verify_placeHolder_FeeTitle_and_Description_is_Back() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
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

						builder.sendKeys(td, "HI").sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE).perform();

						System.out.println(td.getText());
						softAssert.assertTrue(td.getText().contains("Fee Title"));

						break;

					}

					break;
				}
				break;
			}
			Thread.sleep(3000);
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 4, enabled = true)
	public void verifytheRowisDeleted() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
			Thread.sleep(3000);
			editObj.addRowPageFlow();
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

			Thread.sleep(3000);
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 5, enabled = true)
	public void verifytheTotalofFeeTable() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}
			editObj.changeFeeCurrency("4");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();

			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			Thread.sleep(3000);
			editObj.fillPageFlowTableRows1();

			List<WebElement> tableID = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
			System.out.println("tableID" + tableID);

			int tableDataID = 0;
			for (int i = 0; i < tableID.size(); i++) {
				System.out.println(tableID.get(i).getAttribute("data-id"));

				if (i == tableID.size() - 1) {
					tableDataID = Integer.parseInt(tableID.get(i).getAttribute("data-id"));
				}

			}
			System.out.println(tableDataID);
			WebElement FinalPageFeeTable = driver
					.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id="
							+ "'" + tableDataID + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot = FinalPageFeeTable.findElement(By.tagName("tfoot"));
			builder.doubleClick(tableFoot).perform();

			WebElement tableFooterRow = tableFoot.findElement(By.tagName("tr"));
			List<WebElement> tableFooterData = tableFooterRow.findElements(By.tagName("td"));
			int flag2 = 0;
			for (WebElement tfData : tableFooterData) {
				flag2++;
				if (flag2 == 1) {
					String total_Text = tfData.getText();
					System.out.println(total_Text);
					softAssert.assertTrue(total_Text.equals("Total"));
				}

				if (flag2 == 2) {

					DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
					decimalFormat.setGroupingUsed(true);

					decimalFormat.setGroupingSize(3);
					String amt_cal = currSel + decimalFormat.format(editObj.lastRowtamt);
					System.out.println("calculated amount" + amt_cal);
					String amt = tfData.getText() + ".00";
					System.out.println("actual amount" + amt);
					softAssert.assertTrue(amt_cal.equals(amt), "false");
				}
			}
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 6, enabled = true)
	public void verifyShowTotalhidingtheTotalValue() throws WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.fillPageFlowTableRows1();
			List<WebElement> tableID = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
			int tableDataID = 0;
			for (int i = 0; i < tableID.size(); i++) {
				System.out.println(tableID.get(i).getAttribute("data-id"));

				if (i == tableID.size() - 1) {
					tableDataID = Integer.parseInt(tableID.get(i).getAttribute("data-id"));
				}

			}
			System.out.println(tableDataID);
			WebElement FinalPageFeeTable = driver
					.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id="
							+ "'" + tableDataID + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			Thread.sleep(3000);

			WebElement tableFoot = FinalPageFeeTable.findElement(By.tagName("tfoot"));
			builder.doubleClick(tableFoot).perform();
			WebElement tableFooterRow = tableFoot.findElement(By.tagName("tr"));
			List<WebElement> tableFooterData = tableFooterRow.findElements(By.tagName("td"));
			Thread.sleep(2000);
			editObj.changeShowTotalToggleOFF();
			Thread.sleep(3000);
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
					softAssert.assertTrue(amt.equals(" "), "false");
				}
			}
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 7, enabled = true)
	public void verifyShowTotaldisplaysTotalValue() throws WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();

			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();

			editObj.fillPageFlowTableRows1();

			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
			int tableDataID_Updated = 0;
			for (int i = 0; i < tableID_New.size(); i++) {
				// System.out.println(tableID_New.get(i).getAttribute("data-id"));

				if (i == tableID_New.size() - 1) {
					tableDataID_Updated = Integer.parseInt(tableID_New.get(i).getAttribute("data-id"));
				}

			}
			// System.out.println(tableDataID_Updated);

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(
					By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));
			builder.doubleClick(tableFoot1).perform();
			editObj.changeShowTotalToggleOFF();

			Thread.sleep(2000);

			editObj.changeShowTotalToggleON();
			Thread.sleep(3000);
			WebElement tableFooterRow = tableFoot1.findElement(By.tagName("tr"));
			List<WebElement> tableFooterData = tableFooterRow.findElements(By.tagName("td"));
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
					String amt_cal = currSel + decimalFormat.format(editObj.lastRowtamt);
					String amt = tfData.getText() + ".00";

					softAssert.assertTrue(amt_cal.equals(amt), "false");
				}
			}
			Thread.sleep(3000);
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 8, enabled = true)

	public void verifytheTaxHasBeenAddedtotheTotal() throws WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();

			Thread.sleep(3000);
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			editObj.fillPageFlowTableRows1();
			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
			List<WebElement> totalTble = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));
			System.out.println(tableID_New.size());
			int tableDataID_Updated = 0;
			int id_flag = 0;
			for (WebElement tid : tableID_New) {
				id_flag++;
				if (id_flag == totalTble.size()) {
					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));
					break;
				}
			}

			System.out.println(tableDataID_Updated);

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver
					.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id="
							+ "'" + tableDataID_Updated + "'"
							+ "]//div[@class='elements']//table[@data-type='pricing']//tfoot"));

			builder.doubleClick(FinalPageFeeTable1).perform();
			editObj.addTax();
			editObj.fillTaxesPageFlowTable();
			editObj.addTax();
			editObj.fillTaxesPageFlowTable();
			editObj.taxAmountCaluclationPageFlow();
			Thread.sleep(3000);

			List<WebElement> tableFooterRow = FinalPageFeeTable1.findElements(By.tagName("tr"));

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
							String amt_cal = currSel + decimalFormat.format(editObj.lastRowtamt);
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
							softAssert.assertEquals(taxString, n, "Tax text verification error");
						}
						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							double addedTaxPercent = Integer
									.parseInt(tableFooterRow.get(i).getAttribute("data-rate-value"));
							double addedTaxValue = (addedTaxPercent * editObj.lastRowtamt) / 100;
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
							softAssert.assertTrue(amt_cal.equals(amt), "false");

						}
					}

				}
			}
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 9, enabled = true)
	public void verifytheTaxHasBeenCanceledWithDefaultValue() throws WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			// editObj.addRow();
			// editObj.addRow();
			// editObj.addRow();
			editObj.fillPageFlowTableRows1();

			editObj.addTax();
			editObj.cancelNewTaxPageFlowTable();

			Thread.sleep(3000);
			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
			List<WebElement> totalTble = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));
			System.out.println(tableID_New.size());
			int tableDataID_Updated = 0;
			int id_flag = 0;
			for (WebElement tid : tableID_New) {
				id_flag++;
				if (id_flag == totalTble.size()) {
					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));
					break;
				}
			}

			System.out.println(tableDataID_Updated);

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(
					By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));

			WebElement txRow = tableFoot1.findElement(By.cssSelector("tr[data-rate-type='tax']"));

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

					System.out.println(tdData2 + ".00");
					System.out.println(currSel + "0.00");
					softAssert.assertTrue(tdData2.equals(currSel + "0.00"), "Tax Amount not displayed as expected");
				}

			}

			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 10, enabled = true)

	public void verifytheDiscountHasBeenCanceledWithDefaultValue() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			// editObj.addRow();
			// editObj.addRow();
			// editObj.addRow();
			editObj.fillPageFlowTableRows1();

			editObj.addDiscount();
			editObj.cancelNewDiscountPageFlowTable();

			Thread.sleep(3000);
			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
			List<WebElement> totalTble = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));
			System.out.println(tableID_New.size());
			int tableDataID_Updated = 0;
			int id_flag = 0;
			for (WebElement tid : tableID_New) {
				id_flag++;
				if (id_flag == totalTble.size()) {
					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));
					break;
				}
			}

			System.out.println(tableDataID_Updated);

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(
					By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));

			WebElement txRow = tableFoot1.findElement(By.cssSelector("tr[data-rate-type='discount']"));

			List<WebElement> txData = txRow.findElements(By.tagName("td"));
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
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 11, enabled = true)

	public void verifythetaxErrorMessageisDispayed() throws WebDriverException {

		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			// WebElement curr =
			// driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			// String currSel = curr.getText();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();

			Thread.sleep(3000);

			editObj.fillPageFlowTableRows1();
			editObj.addTax();
			editObj.fillTaxesPageFlowTablewithPaameter("GST", "");

			Thread.sleep(2000);
			WebElement errorMessage = driver
					.findElement(By.xpath("//div[@data-notify='container']//span[@data-notify='message']"));

			System.out.println(errorMessage.getText());
			String errMessage = "You've missed a required field. Please make sure all required fields have values in it";
			softAssert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed");
			softAssert.assertTrue(errorMessage.getText().trim().equals(errMessage.trim()),
					"Error message is different than what expected");
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 12, enabled = true)
	public void verifytheDiscountErrorMessageisDispayed() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			// WebElement curr =
			// driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			// String currSel = curr.getText();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			Thread.sleep(3000);

			editObj.fillPageFlowTableRows1();

			editObj.addDiscount();
			editObj.fillDiscountsPageFlowTablewithPaameter("GST", "");
			Thread.sleep(3000);

			WebElement errorMessage = driver
					.findElement(By.xpath("//div[@data-notify='container']//span[@data-notify='message']"));

			System.out.println(errorMessage.getText());
			String errMessage = "You've missed a required field. Please make sure all required fields have values in it";
			softAssert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed");
			softAssert.assertTrue(errorMessage.getText().trim().equals(errMessage.trim()),
					"Error message is different than what expected");
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 13, enabled = true)
	public void verifytheexistingmultipletaxDeletedandTotalRecalculated() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			Thread.sleep(3000);
			editObj.addRowPageFlow();
			editObj.addTax();
			editObj.fillTaxesPageFlowTablewithPaameter("GST", "5");
			editObj.addTax();
			editObj.fillTaxesPageFlowTablewithPaameter("GST", "5");
			editObj.taxAmountCaluclationPageFlow();
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

			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));

			List<WebElement> totalTble = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));

			System.out.println(tableID_New.size());

			int tableDataID_Updated = 0;

			int id_flag = 0;

			for (WebElement tid : tableID_New)

			{

				id_flag++;

				if (id_flag == totalTble.size())

				{

					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));

					break;

				}

			}

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(
					By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));

			List<WebElement> txRow = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow.size(); i++) {

				String tPercent = txRow.get(i).getAttribute("data-rate-value");

				taxAmt = Double.parseDouble(tPercent);

				totalTaxPercent = taxAmt + totalTaxPercent;

			}
			totalTaxAmount = (totalTaxPercent * editObj.lastRowtamt) / 100;

			editObj.cancelExistingTaxofPageFlowTable();
			Thread.sleep(3000);
			editObj.cancelExistingTaxofPageFlowTable();

			List<WebElement> txRow_new = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow_new.size(); i++) {

				String tPercent = txRow_new.get(i).getAttribute("data-rate-value");

				double taxAmt_new = Double.parseDouble(tPercent);

				totalTaxPercent_new = taxAmt_new + totalTaxPercent_new;

			}

			totalTaxAmount_Updated = (totalTaxPercent_new * editObj.lastRowtamt) / 100;
			double taxDiff = totalTaxAmount - totalTaxAmount_Updated;

			if (taxDiff > 0) {

				double newTax = totalTaxAmount - taxDiff;
				double totalRefresh = editObj.totalAmount - totalTaxAmount;
				System.out.println(totalTaxAmount_Updated);

				System.out.println("new Tax: " + newTax);
				totalAmount = editObj.lastRowtamt + (newTax);
				System.out.println("Total: " + totalAmount);
				softAssert.assertTrue(totalAmount == totalRefresh + newTax);
			}

			else {
				System.out.println(totalTaxAmount);

				totalAmount = editObj.lastRowtamt;
				System.out.println("Total: " + totalAmount);
				System.out.println(editObj.totalAmount - totalTaxAmount);
				softAssert.assertTrue(totalAmount == editObj.totalAmount - totalTaxAmount);
			}
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 14, enabled = true)
	public void verifytheexistingSingletaxDeletedandTotalRecalculated() throws WebDriverException {

		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			// WebElement curr =
			// driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			// String currSel = curr.getText();
			Thread.sleep(3000);
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			editObj.fillPageFlowTableRows1();
			editObj.addTax();
			editObj.fillTaxesPageFlowTable();
			editObj.addTax();
			editObj.fillTaxesPageFlowTable();
			editObj.addTax();
			editObj.fillTaxesPageFlowTablewithPaameter("GST", "5");
			editObj.taxAmountCaluclationPageFlow();
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

			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));

			List<WebElement> totalTble = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));

			System.out.println(tableID_New.size());

			int tableDataID_Updated = 0;

			int id_flag = 0;

			for (WebElement tid : tableID_New)

			{

				id_flag++;

				if (id_flag == totalTble.size())

				{

					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));

					break;

				}

			}

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(
					By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));

			List<WebElement> txRow = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow.size(); i++) {

				String tPercent = txRow.get(i).getAttribute("data-rate-value");

				taxAmt = Double.parseDouble(tPercent);

				totalTaxPercent = taxAmt + totalTaxPercent;

			}
			totalTaxAmount = (totalTaxPercent * editObj.lastRowtamt) / 100;

			editObj.cancelExistingTaxofPageFlowTable();
			Thread.sleep(3000);
			editObj.cancelExistingTaxofPageFlowTable();

			List<WebElement> txRow_new = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='tax']"));

			for (int i = 0; i < txRow_new.size(); i++) {

				String tPercent = txRow_new.get(i).getAttribute("data-rate-value");

				double taxAmt_new = Double.parseDouble(tPercent);

				totalTaxPercent_new = taxAmt_new + totalTaxPercent_new;

			}

			totalTaxAmount_Updated = (totalTaxPercent_new * editObj.lastRowtamt) / 100;
			double taxDiff = totalTaxAmount - totalTaxAmount_Updated;

			if (taxDiff > 0) {

				double newTax = totalTaxAmount - taxDiff;
				double totalRefresh = editObj.totalAmount - totalTaxAmount;
				System.out.println(totalTaxAmount_Updated);

				System.out.println("new Tax: " + newTax);
				totalAmount = editObj.lastRowtamt + (newTax);
				System.out.println("Total: " + totalAmount);
				softAssert.assertTrue(totalAmount == totalRefresh + newTax);
			}

			else {
				System.out.println(totalTaxAmount);

				totalAmount = editObj.lastRowtamt;
				System.out.println("Total: " + totalAmount);
				System.out.println(editObj.totalAmount - totalTaxAmount);
				softAssert.assertTrue(totalAmount == editObj.totalAmount - totalTaxAmount);
			}
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 15, enabled = true)
	public void verifytheDiscountHasBeenDeductFromtheTotal() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			editObj.fillPageFlowTableRows1();
			editObj.addDiscount();
			editObj.fillDiscountsofPageFlowTable();
			editObj.discountAmountCaluclationPageFlow();
			Thread.sleep(3000);
			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));

			List<WebElement> totalTble = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));

			System.out.println(tableID_New.size());

			int tableDataID_Updated = 0;

			int id_flag = 0;

			for (WebElement tid : tableID_New)

			{

				id_flag++;

				if (id_flag == totalTble.size())

				{

					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));

					break;

				}

			}

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(
					By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));

			List<WebElement> tableFooterRow = tableFoot1.findElements(By.tagName("tr"));

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
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.lastRowtamt);
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
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							double addeddiscountPercent = Integer
									.parseInt(tableFooterRow.get(i).getAttribute("data-rate-value"));
							double addedDiscountValue = (addeddiscountPercent * editObj.lastRowtamt) / 100;
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
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.dtotalAmount);
							String amt = tfData.getText() + ".00";
							System.out.println(amt_cal);
							System.out.println(amt);
							softAssert.assertTrue(amt_cal.equals(amt), "false");

						}
					}

				}
			}
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 16, enabled = true)
	public void verifytheexistingmultipleDiscountDeletedandTotalRecalculated() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();

			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			editObj.fillPageFlowTableRows1();
			editObj.addDiscount();
			editObj.fillDiscountsPageFlowTablewithPaameter("GST", "5");
			editObj.addDiscount();
			editObj.fillDiscountsPageFlowTablewithPaameter("GST", "5");
			editObj.addDiscount();
			editObj.fillDiscountsPageFlowTablewithPaameter("GST", "5");
			editObj.discountAmountCaluclationPageFlow();
			Thread.sleep(3000);
			double disAmt = 0;
			double totaldisAmount = 0;
			double totaldisAmount_Updated = 0;
			double totalAmount = 0;
			double totaldisPercent = 0;
			double totaldisPercent_new = 0;
			double totaldisAmt = editObj.totalDiscAmount;
			System.out.println("Total  Discount: " + totaldisAmt);
			double totAmtAfterdis = editObj.dtotalAmount;
			System.out.println("Total amount After reducing Total discount: " + totAmtAfterdis);

			List<WebElement> tableID_New = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));

			List<WebElement> totalTble = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));

			System.out.println(tableID_New.size());

			int tableDataID_Updated = 0;

			int id_flag = 0;

			for (WebElement tid : tableID_New)

			{

				id_flag++;

				if (id_flag == totalTble.size())

				{

					tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));

					break;

				}

			}

			Thread.sleep(3000);
			WebElement FinalPageFeeTable1 = driver.findElement(
					By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));

			List<WebElement> txRow = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='discount']"));

			for (int i = 0; i < txRow.size(); i++) {

				String tPercent = txRow.get(i).getAttribute("data-rate-value");

				disAmt = Double.parseDouble(tPercent);

				totaldisPercent = disAmt + totaldisPercent;

			}
			totaldisAmount = (totaldisPercent * editObj.lastRowtamt) / 100;

			editObj.cancelExistingDiscountofPageFlowTable();
			Thread.sleep(3000);

			List<WebElement> disRow_new = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='discount']"));

			for (int i = 0; i < disRow_new.size(); i++) {

				String tPercent = disRow_new.get(i).getAttribute("data-rate-value");

				double disAmt_new = Double.parseDouble(tPercent);

				totaldisPercent_new = disAmt_new + totaldisPercent_new;

			}

			totaldisAmount_Updated = (totaldisPercent_new * editObj.lastRowtamt) / 100;
			double disDiff = totaldisAmount - totaldisAmount_Updated;

			if (disDiff > 0) {

				double newDis = totaldisAmount - disDiff;
				double totalRefresh = editObj.dtotalAmount + totaldisAmount;
				System.out.println(totaldisAmount_Updated);
				System.out.println("Exact " + editObj.totalAmount);

				System.out.println("new Discount: " + newDis);
				totalAmount = editObj.lastRowtamt - (newDis);
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
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 22)
	public void verifytherowcolorChanges() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			// WebElement curr =
			// driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			// String currSel = curr.getText();
			Thread.sleep(3000);
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();

			editObj.fillPageFlowTableRows1();

			editObj.changeRowColorPageFlowTable();

			softAssert.assertTrue(editObj.rowColor1.contains(editObj.colorStyle1));
			softAssert.assertTrue(editObj.rowColor2.contains(editObj.colorStyle2));
			softAssert.assertTrue(editObj.rowColor3.contains(editObj.colorStyle2));

			editObj.displayPropertiesPanel();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 23)
	public void verifyOptionalforClientCheckBoxAppears() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow1 = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow1.size());

			for (WebElement tr : tableRow1) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			// WebElement curr =
			// driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			// String currSel = curr.getText();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			editObj.fillPageFlowTableRows1();

			List<WebElement> pages = driver.findElements(By.xpath(
					".//*[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse']//li"));
			int flag = 0;
			int pageCount = pages.size();
			for (int k = 0; k < pageCount; k += 2) {
				flag++;
				pages.get(k).click();
				if (flag == 1) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					System.out.println(tableData.size());
					for (WebElement tdata : tableData) {
						builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();
						Thread.sleep(3000);
						editObj.optionalForClientOptionTurnON();
						WebElement optionalCheckBox = tdata.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[7]/div[3]/div[4]/div[2]/div/div/div/table/tbody/tr/td[1]/span/input"));

						builder.moveToElement(optionalCheckBox);
						System.out.println("true or flase" + optionalCheckBox.isEnabled());
						softAssert.assertTrue(optionalCheckBox.isEnabled());
						break;
					}

				}

				if (flag == 2) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					System.out.println(tableData.size());
					for (WebElement tdata : tableData) {
						builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();
						Thread.sleep(3000);
						editObj.optionalForClientOptionTurnON();
						WebElement optionalCheckBox = tdata.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[7]/div[3]/div[4]/div[2]/div/div/div/table/tbody/tr/td[1]/span/input"));

						softAssert.assertTrue(optionalCheckBox.isEnabled());
						break;
					}

				}

				if (flag == 3) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					System.out.println(tableData.size());
					for (WebElement tdata : tableData) {
						builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();
						Thread.sleep(3000);
						editObj.optionalForClientOptionTurnON();
						WebElement optionalCheckBox = tdata.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[7]/div[3]/div[4]/div[2]/div/div/div/table/tbody/tr/td[1]/span/input"));

						softAssert.assertTrue(optionalCheckBox.isEnabled());
						break;
					}

				}

			}

			Thread.sleep(4000);
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 24)
	public void verifyOptionalforClientCheckBoxDisappers() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> tableRow1 = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow1.size());

			for (WebElement tr : tableRow1) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			// WebElement curr =
			// driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			// String currSel = curr.getText();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();

			editObj.fillPageFlowTableRows1();

			List<WebElement> pages = driver.findElements(By.xpath(
					".//*[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse']//li"));
			int flag = 0;
			int pageCount = pages.size();
			for (int k = 0; k < pageCount; k += 2) {
				flag++;
				pages.get(k).click();
				if (flag == 1) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					System.out.println(tableData.size());
					for (WebElement tdata : tableData) {
						builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();
						Thread.sleep(3000);
						WebElement selCell = tableRow.findElement(By.cssSelector("td[class='fr-selected-cell']"));
						editObj.optionalForClientOptionTurnON();
						Thread.sleep(3000);
						WebElement optionalCheckBox = selCell.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[7]/div[3]/div[4]/div[2]/div/div/div/table/tbody/tr/td[1]/span/input"));
						editObj.optionalForClientOptionOFF();

						softAssert.assertFalse(optionalCheckBox.isDisplayed());
						break;
					}

				}

				if (flag == 2) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					System.out.println(tableData.size());
					for (WebElement tdata : tableData) {
						builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();
						Thread.sleep(3000);
						editObj.optionalForClientOptionTurnON();
						Thread.sleep(3000);
						WebElement selCell = tableRow.findElement(By.cssSelector("td[class='fr-selected-cell']"));
						WebElement optionalCheckBox = selCell.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[7]/div[3]/div[4]/div[2]/div/div/div/table/tbody/tr/td[1]/span/input"));
						editObj.optionalForClientOptionOFF();

						softAssert.assertFalse(optionalCheckBox.isDisplayed());
						break;
					}

				}

				if (flag == 3) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					System.out.println(tableData.size());
					for (WebElement tdata : tableData) {
						builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();
						Thread.sleep(3000);
						WebElement selCell = tableRow.findElement(By.cssSelector("td[class='fr-selected-cell']"));
						editObj.optionalForClientOptionTurnON();
						Thread.sleep(3000);
						WebElement optionalCheckBox = selCell.findElement(By.xpath(
								"//*[@id=\"pyCanvasList\"]/div[7]/div[3]/div[4]/div[2]/div/div/div/table/tbody/tr/td[1]/span/input"));
						editObj.optionalForClientOptionOFF();

						softAssert.assertFalse(optionalCheckBox.isDisplayed());
						break;
					}

				}

			}

			Thread.sleep(3000);
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 26)
	public void verifytheTotalRecalculatedOnQtyeditedinEditableOption() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> tableRow1 = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow1.size());

			for (WebElement tr : tableRow1) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();

			editObj.fillPageFlowTableRows1();

			int flg = 0;
			int flag = 0;
			int flag2 = 0;
			int f_flag = 0;
			int f_flag2 = 0;
			int flag3 = 0;
			double qty1 = 0;
			double qty2 = 0;
			String editQty1 = null;
			String editQty2 = null;
			double newPrice1 = 0;
			double newPrice2 = 0;
			String totalText1 = null;
			String totalText2 = null;
			double totalAfterEditableQty = 0;

			double totalBeforeEditableQty = editObj.lastRowtamt;
			System.out.println(totalBeforeEditableQty);
			Thread.sleep(3000);
			List<WebElement> pages = driver.findElements(By.xpath(
					".//*[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse']//li"));
			int f = 0;
			int pageCount = pages.size();
			for (int k = 0; k < pageCount; k += 2) {
				f++;
				pages.get(k).click();
				if (f == 1) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));

					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					for (WebElement tfData : tableData) {
						flg++;
						builder.doubleClick(tfData).doubleClick().click().perform();
						editObj.editableQuantityOptionTurnON();
						if (flg == 3) {
							WebElement editableTB = tfData.findElement(By.xpath(
									".//*[@class='input-group bootstrap-touchspin']//input[@class='form-control']"));
							// editObj.insertPageFlowFeeTable("6");
							builder.moveToElement(editableTB);
							editQty1 = "50";
							builder.doubleClick(editableTB).sendKeys(editQty1).perform();
							qty1 = editObj.qty;
							newPrice1 = editObj.price * (qty1 - (Double.parseDouble(editQty1)));
							break;
						}

					}

					for (int j = 0; j < pageCount; j++) {
						flag2++;
						int tableDataID = 0;
						if (flag2 == 2) {
							// pages.get(j).click();

							List<WebElement> tableID_lastRow = driver.findElements(By.xpath(
									".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));

							for (int i = 0; i < tableID_lastRow.size(); i++) {
								System.out.println(tableID_lastRow.get(i).getAttribute("data-id"));

								if (i == tableID_lastRow.size() - 1) {
									tableDataID = Integer.parseInt(tableID_lastRow.get(i).getAttribute("data-id"));
								}

							}

							Thread.sleep(3000);
							WebElement FinalPageFeeTableFooter = driver.findElement(
									By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id="
											+ "'" + tableDataID + "'"
											+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']//tfoot"));
							// WebElement tableFoot1 = FinalPageFeeTable2.findElement(By.tagName("tfoot"));
							List<WebElement> fTRow = FinalPageFeeTableFooter.findElements(By.tagName("tr"));

							for (WebElement ftr : fTRow) {

								List<WebElement> ftdata = ftr.findElements(By.tagName("td"));

								for (WebElement ftd : ftdata) {
									f_flag++;
									if (f_flag == 2) {
										builder.doubleClick(ftd).doubleClick().click().perform();
										Thread.sleep(3000);
										WebElement selTd = ftr
												.findElement(By.cssSelector("td[class='fr-selected-cell']"));
										totalText1 = selTd.getText();
										System.out.println("1 " + totalText1);
										break;
									}

								}

								break;

							}

						}

					}
				}

				if (f == 2) {

					WebElement tableID_New = driver.findElement(By.xpath(
							".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
					int tableDataID_Updated = 0;
					tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
					// System.out.println(tableID_New.get(i).getAttribute("data-id"));
					Thread.sleep(3000);
					WebElement FinalPageFeeTable1 = driver.findElement(
							By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
									+ tableDataID_Updated + "'"
									+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));
					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));

					WebElement tableRow = tableBody.findElement(By.tagName("tr"));

					List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
					for (WebElement tfData : tableData) {
						flag++;

						builder.doubleClick(tfData).doubleClick().click().perform();
						editObj.editableQuantityOptionTurnON();
						if (flag == 3) {
							WebElement editableTB = tfData.findElement(By.xpath(
									".//*[@class='input-group bootstrap-touchspin']//input[@class='form-control']"));

							editQty2 = "200";
							builder.doubleClick(editableTB).sendKeys(editQty2).perform();
							qty2 = editObj.qty;
							newPrice2 = editObj.price * ((Double.parseDouble(editQty2)) - qty2);
							break;
						}

					}
					for (int l = 0; l < pageCount; l++) {
						flag3++;
						if (flag3 == 6) {
							pages.get(l).click();
							WebElement tableID_lastRow = driver.findElement(By.xpath(
									".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
							int tableDataID_Updated1 = 0;
							tableDataID_Updated1 = Integer.parseInt(tableID_lastRow.getAttribute("data-id"));
							// System.out.println(tableID_New.get(i).getAttribute("data-id"));
							Thread.sleep(3000);
							WebElement FinalPageFeeTableFooter = driver.findElement(
									By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id="
											+ "'" + tableDataID_Updated1 + "'"
											+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']//tfoot"));
							// WebElement tableFoot1 = FinalPageFeeTable2.findElement(By.tagName("tfoot"));
							List<WebElement> fTRow1 = FinalPageFeeTableFooter.findElements(By.tagName("tr"));

							for (WebElement ftr1 : fTRow1) {

								List<WebElement> ftdata1 = ftr1.findElements(By.tagName("td"));

								for (WebElement ftd : ftdata1) {
									f_flag2++;
									if (f_flag2 == 2) {
										builder.doubleClick(ftd).doubleClick().click().perform();
										Thread.sleep(3000);
										WebElement selTd = ftr1
												.findElement(By.cssSelector("td[class='fr-selected-cell']"));
										totalText2 = selTd.getText();
										System.out.println("2 " + totalText2);
										break;
									}

								}

								break;
							}

						}
					}
				}
				if (f == 3) {
					break;
				}
			}

			if ((Double.parseDouble(editQty1)) > editObj.qty) {

				totalAfterEditableQty = editObj.lastRowtamt + newPrice1;
			}

			else if ((Double.parseDouble(editQty1)) < editObj.qty) {

				totalAfterEditableQty = totalBeforeEditableQty - newPrice1;
				DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
				decimalFormat.setGroupingUsed(true);
				decimalFormat.setGroupingUsed(true);
				decimalFormat.setGroupingSize(3);
				String amt_cal = currSel + decimalFormat.format(totalAfterEditableQty);

				System.out.println("edit1 " + amt_cal + "=" + totalText1 + ".0");
				softAssert.assertTrue(amt_cal.equals(totalText1 + ".00"), "Didn't reduce");
			}

			if ((Double.parseDouble(editQty2)) > editObj.qty) {
				// totalAfterEditableQty = editObj.lastRowtamt;
				System.out.println(totalAfterEditableQty);
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

			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

	@Test(priority = 27)
	public void verifyTheCurrencyChange() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");

			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

			Thread.sleep(3000);

			List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());

			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					break;
				}

			}

			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			String currSel = curr.getText();
			editObj.addRowPageFlow();
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();

			editObj.fillPageFlowTableRows1();

			List<WebElement> pages = driver.findElements(By.xpath(
					".//*[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse']//li"));
			int flag = 0;
			int pageCount = pages.size();
			int tableDataID = 0;
			List<WebElement> tableID_lastRow = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));

			for (int i = 0; i < tableID_lastRow.size(); i++) {
				System.out.println(tableID_lastRow.get(i).getAttribute("data-id"));

				if (i == tableID_lastRow.size() - 1) {
					tableDataID = Integer.parseInt(tableID_lastRow.get(i).getAttribute("data-id"));
				}

			}

			WebElement FinalPageFeeTable1 = driver.findElement(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'" + tableDataID + "'"
							+ "]//div[@class='elements']//div[contains(@class,'master')]//table[@data-type='pricing']"));

			for (int k = 0; k < pageCount; k++) {
				flag++;

				pages.get(k).click();

				if (flag == k + 1) {
					Thread.sleep(3000);

					WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
					// WebElement tableFoot = FinalPageFeeTable1.findElement(By.tagName("tfoot"));
					List<WebElement> tfrow = tableBody.findElements(By.tagName("tr"));
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

								softAssert.assertTrue(amt_cal1.equals(tbData.getText() + ".00"), "second");
							}
						}

					}
				}

			}
			editObj.displayPropertiesPanelforPageFlowTable();
			editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.PageFlowFeeTableSelectSection.click();
				List<WebElement> elem = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
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

}
