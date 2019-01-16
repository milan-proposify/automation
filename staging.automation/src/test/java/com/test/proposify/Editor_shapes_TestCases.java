package com.test.proposify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
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

public class Editor_shapes_TestCases {
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
			options.addArguments("--start-fullscreen");
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


	@Test(priority = 0, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyShapeDragandDrop() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertShape();

			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch' )]"));
			softAssert.assertTrue(el.isDisplayed(), "Shape is not present on the Editor");
			Thread.sleep(3000);
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();

						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();

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
	public void verifyShapeWidthProperty() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertShape();
			Thread.sleep(3000);
			editObj.enterTextboxWidthProperty("200");
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			softAssert.assertTrue(
					el.getAttribute("style").contains("width: " + editObj.width.getAttribute("value") + "px;"),
					"Shape with width " + editObj.width.getAttribute("value") + "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
				System.out.println(elem.size());
				for (int i = elem.size() - 1; i >= 0; i--) {
					if (i == 0) {
						builder.moveToElement(elem.get(i)).click().perform();
						builder.moveToElement(elem.get(i)).click().perform();

						String elemClass = elem.get(i).getAttribute("class");
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].setAttribute('class', '" + elemClass + "selected deleting');",
								elem.get(i));
					}

					builder.moveToElement(elem.get(i)).click().perform();
					// builder.moveToElement(elem.get(i)).click().perform();

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
	public void verifyShapeHeightProperty() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertShape();
			Thread.sleep(3000);
			editObj.enterTextboxWidthProperty("200");
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			softAssert.assertTrue(
					el.getAttribute("style").contains("width: " + editObj.width.getAttribute("value") + "px;"),
					"Shape with width " + editObj.width.getAttribute("value") + "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

	@Test(priority = 3, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyShapeCornersProperty() throws InterruptedException, WebDriverException {
		try {

			editObj.insertShape();
			Thread.sleep(3000);
			editObj.enterCornerProperty();
			Thread.sleep(3000);
			WebElement el = driver
					.findElement(By.xpath("//div[contains(@class,'stretch draggable selected' )]//p[@class='shape']"));
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
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

	@Test(priority = 4, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyShpaeBoxRotateProperty() throws InterruptedException, WebDriverException {
		try {

			editObj.insertShape();
			Thread.sleep(3000);
			editObj.enterTextboxRotateProperty("20");
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected' )]"));
			softAssert.assertTrue(
					el.getAttribute("style")
							.contains("transform: rotate(" + editObj.rotate.getAttribute("value") + "deg);"),
					"TextArea with Rotate " + editObj.rotate.getAttribute("value") + "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

	@Test(priority = 5, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheOpacityProperty() throws InterruptedException, WebDriverException {
		try {

			editObj.insertShape();
			Thread.sleep(3000);
			editObj.movetheSlider();
			Thread.sleep(3000);
			WebElement el = driver
					.findElement(By.xpath("//div[contains(@class,'stretch draggable selected' )]//p[@class='shape']"));
			System.out.println(el.getAttribute("style"));
			softAssert.assertTrue(
					el.getAttribute("style")
							.contains("opacity: " + editObj.Opacityslider.getAttribute("aria-valuenow") + ";"),
					"TextArea with Opacity " + editObj.Opacityslider.getAttribute("aria-valuenow")
							+ "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

	@Test(priority = 7, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheDuplicateofShapeArea() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertShape();
			Thread.sleep(3000);
			WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			String elem = eld.getAttribute("class");
			String arry[] = new String[3];
			String arry2[] = new String[2];
			String elemID = null;
			arry = elem.split(" ");

			for (int i = 0; i < 3; i++) {
				String strng = arry[i];
				System.out.println(strng);
				if (i == 2) {
					arry2 = arry[i].split("_");
					for (int j = 0; j < 2; j++) {
						if (j == 1) {
							elemID = arry2[j];
							break;
						}
					}
					break;
				}

			}
			// String eleID = eld.getAttribute("class").substring(20, 24);

			editObj.duplicateTheElement();

			int dup = Integer.parseInt(elemID) + 1;
			softAssert.assertTrue(driver
					.findElement(By.xpath("//div[contains(@class,'element shape shape_" + dup + "')]")).isDisplayed(),
					"duplicate Shape Area is not created");
			editObj.deletetheElement();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@class,'element shape shape_" + elemID + "')]")).click();
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

	@Test(priority = 6, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheDeleteofShapeArea() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertShape();
			WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			String elem = eld.getAttribute("class");
			String arry[] = new String[3];
			String arry2[] = new String[2];
			String elemID = null;
			arry = elem.split(" ");

			for (int i = 0; i < 3; i++) {
				String strng = arry[i];
				System.out.println(strng);
				if (i == 2) {
					arry2 = arry[i].split("_");
					for (int j = 0; j < 2; j++) {
						if (j == 1) {
							elemID = arry2[j];
							break;
						}
					}
					break;
				}

			}
			// String eleID2 = eld.getAttribute("class").substring(20, 24);
			Thread.sleep(3000);
			editObj.deletetheElement();
			Thread.sleep(3000);
			List<WebElement> NewOrigList = driver
					.findElements(By.xpath("//div[contains(@class,'element shape shape_" + elemID + "')]"));

			softAssert.assertTrue(NewOrigList.size() == 0, "Shape area not deleted");
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

	@Test(priority = 8, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifydeleteofOrginalShapeAreaElementafterduplicate() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertShape();
			WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			String elem = eld.getAttribute("class");
			String arry[] = new String[3];
			String arry2[] = new String[2];
			String elemID = null;
			arry = elem.split(" ");

			for (int i = 0; i < 3; i++) {
				String strng = arry[i];
				System.out.println(strng);
				if (i == 2) {
					arry2 = arry[i].split("_");
					for (int j = 0; j < 2; j++) {
						if (j == 1) {
							elemID = arry2[j];
							break;
						}
					}
					break;
				}

			}
			// String eleID = eld.getAttribute("class").substring(20, 24);

			Thread.sleep(3000);

			editObj.duplicateTheElement();
			WebElement eld2 = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			String elem2 = eld2.getAttribute("class");
			String dupArry[] = new String[3];
			String dupArry2[] = new String[2];
			String elemID2 = null;
			dupArry = elem2.split(" ");

			for (int i = 0; i < 3; i++) {
				String strng = dupArry[i];
				System.out.println(strng);
				if (i == 2) {
					arry2 = dupArry[i].split("_");
					for (int j = 0; j < 2; j++) {
						if (j == 1) {
							elemID2 = dupArry2[j];
							break;
						}
					}
					break;
				}

			}
			// String eleID2 = eld2.getAttribute("class").substring(20, 24);
			editObj.deletetheElement();
			Thread.sleep(3000);
			List<WebElement> newDupList = driver
					.findElements(By.xpath("//div[contains(@class,'element shape shape_" + elemID2 + "')]"));
			int dupListCountUpdate = newDupList.size();
			Assert.assertTrue(dupListCountUpdate == 0, "Duplicate video area not deleted");

			Thread.sleep(3000);
			WebElement orgiElement = driver
					.findElement(By.xpath("//div[contains(@class,'element shape shape_" + elemID + "')]"));

			Thread.sleep(3000);
			orgiElement.click();

			Thread.sleep(3000);
			editObj.deletetheElement();
			Thread.sleep(3000);
			List<WebElement> NewOrigList = driver
					.findElements(By.xpath("//div[contains(@class,'element shape shape_" + elemID + "')]"));
			int orgiListCountUpdate = NewOrigList.size();
			softAssert.assertTrue(orgiListCountUpdate == 0, "Original video area not deleted");
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

	@Test(priority = 9, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyBorderhasbeenAddedtoShape() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertShape();
			WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			String elem = eld.getAttribute("class");

			String arry[] = new String[3];
			String arry2[] = new String[2];
			String elemShapeID = null;
			arry = elem.split(" ");

			for (int i = 0; i < 3; i++) {
				String strng = arry[i];
				System.out.println(strng);
				if (i == 2) {
					arry2 = arry[i].split("_");
					for (int j = 0; j < 2; j++) {
						if (j == 1) {
							elemShapeID = arry2[j];
							break;
						}
					}
					break;
				}

			}
			// String eleID = eld.getAttribute("class").substring(20, 23);

			Thread.sleep(3000);
			editObj.clickBorderToggle();
			editObj.shapeChangeBorderColour("double");
			WebElement newDupList = driver.findElement(
					By.xpath("//div[contains(@class,'element shape shape_" + elemShapeID + "')]//p[@class='shape']"));
			System.out.println(elemShapeID);
			String borderConfirm = newDupList.getAttribute("style");
			System.out.println(borderConfirm);
			String border = editObj.shapeBorderColorStyle1.substring(18, 34);
			System.out.println(border);
			// System.out.println("border-color:"+editObj.colorStyle1.substring(18, 33));
			// WebElement borderTypeElement=
			// editObj.newShapeBorderType.getFirstSelectedOption();
			String borderTypeValue = editObj.borderStyle.getAttribute("placeholder");
			System.out.println(borderTypeValue);
			System.out.println("border: " + editObj.borderSize.getAttribute("value") + "px "
					+ borderTypeValue.toLowerCase() + " " + border + ";");
			softAssert.assertTrue(borderConfirm.contains("border: " + editObj.borderSize.getAttribute("value") + "px "
					+ borderTypeValue.toLowerCase() + " " + border + ";"));

			Thread.sleep(3000);
			editObj.deletetheElement();
			Thread.sleep(3000);

			driver.close();
			softAssert.assertAll();
		}  catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.ShapeselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
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

}
