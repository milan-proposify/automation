package com.test.proposify;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Editor_Regression {

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
	public void verifyContentWritten() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			editObj.insertContent();
			WebElement Text = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[3]/div/div/div/p"));
			softAssert.assertTrue(Text.isEnabled(), "Text Area is not present on the Editor");
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
	public void verifyContentBold() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement bold = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'bold')]"));
			bold.click();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement BoldText = driver.findElement(By.xpath("//*[@id='pyCanvasList']//p/strong"));
			softAssert.assertTrue(BoldText.isEnabled(), "Bold not applied");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

			softAssert.assertAll();
			driver.close();

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

	@Test(priority = 2, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyContentItalics() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement itlaics = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'italic')]"));
			itlaics.click();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement affectedText = driver.findElement(By.xpath("//*[@id='pyCanvasList']//p/em"));
			softAssert.assertTrue(affectedText.isEnabled(), "Italics not applied");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

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

	@Test(priority = 3, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyContentUnderline() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement underlined = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'underline')]"));
			underlined.click();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement affectedText = driver.findElement(By.xpath("//*[@id='pyCanvasList']//p/u"));
			softAssert.assertTrue(affectedText.isEnabled(), "not underlined");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

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

	@Test(priority = 4, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyContentStrikeThrough() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement strike = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'strikeThrough')]"));
			strike.click();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement affectedText = driver.findElement(By.xpath("//*[@id='pyCanvasList']//p/s"));
			softAssert.assertTrue(affectedText.isEnabled(), "not strikeThrough");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

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

	@Test(priority = 5, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyFontSize() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement font_dropdown = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'fontSize')]"));
			font_dropdown.click();
			WebElement font_value = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class,'fr-disabled'))]//div[contains(@id, 'dropdown-menu-fontSize')]//ul//a[contains(@title,'40')]"));
			Thread.sleep(3000);
			builder.moveToElement(font_value).click().build().perform();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement currentTextboxtext = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p/span"));

			Thread.sleep(3000);

			softAssert.assertTrue(
					currentTextboxtext.getAttribute("style").contains(font_value.getAttribute("data-param1")),
					"Text Area is not present on the Editor");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 6, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyParagraphStyle() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement paragraph_dropdown = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'paragraphStyleCustom')]"));
			paragraph_dropdown.click();
			WebElement font_value = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class,'fr-disabled'))]//div[contains(@id, 'dropdown-menu-paragraphStyleCustom')]//ul//a[contains(@title,'Heading 4')]"));
			Thread.sleep(3000);
			builder.moveToElement(font_value).click().build().perform();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement currentTextboxtext = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));

			Thread.sleep(3000);
			System.out.println("fontsize" + currentTextboxtext.getAttribute("class"));
			System.out.println("getattribute" + font_value.getAttribute("data-param1"));

			// WebElement BoldText =
			// driver.findElement(By.xpath("//*[@id='pyCanvasList']//p/strong"));
			softAssert.assertTrue(
					currentTextboxtext.getAttribute("class").contains(font_value.getAttribute("data-param1")),
					"Text Area is not present on the Editor");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 7, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifylineHeight() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement lineHeight_dropdown = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'lineHeight')]"));
			lineHeight_dropdown.click();
			WebElement lineHeight_value = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class,'fr-disabled'))]//div[contains(@id, 'dropdown-menu-lineHeight')]//ul//a[@data-param1='150%']"));
			Thread.sleep(3000);
			builder.moveToElement(lineHeight_value).click().build().perform();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement currentTextboxtext = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));

			Thread.sleep(3000);
			System.out.println("fontsize" + currentTextboxtext.getAttribute("style"));
			System.out.println("getattribute" + lineHeight_value.getAttribute("data-param1"));

			// WebElement BoldText =
			// driver.findElement(By.xpath("//*[@id='pyCanvasList']//p/strong"));
			softAssert.assertTrue(
					currentTextboxtext.getAttribute("style").contains(lineHeight_value.getAttribute("data-param1")),
					"Text Area is not present on the Editor");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 8, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTextAlignment() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);
			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			WebElement lineHeight_dropdown = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class, 'fr-disabled'))]//button[contains(@data-cmd, 'align')]"));
			lineHeight_dropdown.click();
			WebElement lineHeight_value = driver.findElement(By.xpath(
					"//*[@id='pyFroalaToolbar']/div[not(contains(@class,'fr-disabled'))]//div[contains(@id, 'align')]//ul//a[@data-param1='center']"));
			Thread.sleep(3000);
			builder.moveToElement(lineHeight_value).click().build().perform();
			builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();

			WebElement currentTextboxtext = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));

			Thread.sleep(3000);
			System.out.println("fontsize" + currentTextboxtext.getAttribute("style"));
			System.out.println("getattribute" + lineHeight_value.getAttribute("data-param1"));

			// WebElement BoldText =
			// driver.findElement(By.xpath("//*[@id='pyCanvasList']//p/strong"));
			softAssert.assertTrue(
					currentTextboxtext.getAttribute("style").contains(lineHeight_value.getAttribute("data-param1")),
					"Text Area is not present on the Editor");

			List<CharSequence> keyWithModifiers = new ArrayList<CharSequence>();
			keyWithModifiers.add(Keys.CONTROL);
			keyWithModifiers.add("a");
			String ctrlA = Keys.chord(keyWithModifiers);
			builder.sendKeys(ctrlA).perform();
			builder.sendKeys(Keys.BACK_SPACE).perform();

			softAssert.assertAll();
			driver.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 9, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyPageFlow() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
			el.click();
			Thread.sleep(3000);

			WebElement currentTextbox = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
			builder.moveToElement(currentTextbox).doubleClick().build().perform();
			builder.moveToElement(currentTextbox).doubleClick().build().perform();

			builder.moveToElement(currentTextbox).sendKeys(
					"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
					.build().perform();
			builder.sendKeys(Keys.ENTER).perform();
			Thread.sleep(3000);
			// builder.sendKeys("Lorem Ipsum is simply dummy text of the printing and
			// typesetting industry. Lorem Ipsum has been the industry's standard dummy text
			// ever since the 1500s, when an unknown printer took a galley of type and
			// scrambled it to make a type specimen book. It has survived not only five
			// centuries, but also the leap into electronic typesetting, remaining
			// essentially unchanged. It was popularised in the 1960s with the release of
			// Letraset sheets containing Lorem Ipsum passages, and more recently with
			// desktop publishing software like Aldus PageMaker including versions of Lorem
			// Ipsum.").build().perform();
			for (int i = 0; i < 30; i++) {
				builder.sendKeys(
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
						.build().perform();
				Thread.sleep(3000);
				builder.sendKeys(Keys.ENTER).perform();
				Thread.sleep(3000);
			}

			WebElement ul = driver
					.findElement(By.xpath("//*[@id='pyPageList']/ul/li[2]/ul[contains(@class,'page-list')]"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			list.size();
			System.out.println("number of pages" + list.size());

			// WebElement BoldText =
			// driver.findElement(By.xpath("//*[@id='pyCanvasList']//p"));
			softAssert.assertTrue(list.size() >= 5, "Text Area is not present on the Editor");
			softAssert.assertAll();
			new Actions(driver).sendKeys(Keys.BACK_SPACE).perform();
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

	@Test(priority = 10, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void insertContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			softAssert.assertTrue(el.isDisplayed(), "content table not avialable");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 11, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowisAddedtotheContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='content']"));
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
			List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			System.out.println("Before Add: " + tableRow.size());
			editObj.addRowContentTable();
			Thread.sleep(3000);
			List<WebElement> tableRowUpdated = tableBody.findElements(By.tagName("tr"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableRowUpdated.size());
			softAssert.assertTrue(tableRowUpdated.size() > tableRow.size(), "New Row is not been Added");
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 12, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowisDeletedFromtheContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='content']"));
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
			List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			System.out.println("Before Add: " + tableRow.size());
			editObj.deleteRowContentTable();
			Thread.sleep(3000);
			List<WebElement> tableRowUpdated = tableBody.findElements(By.tagName("tr"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableRowUpdated.size());
			softAssert.assertTrue(tableRowUpdated.size() < tableRow.size(), "Row has not been deleted");
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 13, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheCellpaddingContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.doubleClick(FirstCell).perform();
			Thread.sleep(3000);
			WebElement padding = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='custom_padding']"));
			builder.moveToElement(padding).click().perform();
			WebElement padding_top = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_top']"));
			WebElement padding_bottom = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_bottom']"));
			WebElement padding_right = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_right']"));
			WebElement padding_left = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_left']"));

			padding_top.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_top.sendKeys(Keys.ENTER);
			padding_bottom.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_bottom.sendKeys(Keys.ENTER);
			padding_right.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_right.sendKeys(Keys.ENTER);
			padding_left.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_left.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			FirstCell.getAttribute("style");
			System.out.println("style of the cell" + FirstCell.getAttribute("style"));
			softAssert.assertTrue(FirstCell.getAttribute("style").contains("10"), "Padding value is not set");

			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 14, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheVariableInsertionContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			Thread.sleep(3000);
			WebElement Variable = driver.findElement(By.xpath("//*[@id='pyContentTableProperties']//span/a"));
			Thread.sleep(3000);
			builder.moveToElement(Variable).click().perform();

			WebElement Search = driver.findElement(By.xpath("//*[@id='pyVariableList']//input[@name='keywords']"));

			builder.moveToElement(Search).sendKeys("name").build().perform();
			builder.moveToElement(FirstCell).click().perform();

			WebElement AddVariable = driver
					.findElement(By.xpath("//*[@id='variableCompany']/a[@data-name='{company_name}']/span"));
			builder.moveToElement(AddVariable).click().perform();

			WebElement CloseVariabelPane = driver
					.findElement(By.xpath("//*[@id='pyVariableList']//span[@class='svg svg-arw-back text-default']"));
			builder.moveToElement(CloseVariabelPane).click().perform();
			
			WebElement VariableinTable = driver.findElement(By.xpath(
					"//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]//span[@data-placeholder='{company_name}']"));

			softAssert.assertTrue(VariableinTable.isEnabled(), "Variable not added");
			Thread.sleep(2000);
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 15, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheSnippetInsertionContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			Thread.sleep(3000);
			WebElement Snippets = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//span[contains(text(),'Snippets')]"));
			Thread.sleep(3000);
			builder.moveToElement(Snippets).click().perform();
			Thread.sleep(3000);
			builder.moveToElement(FirstCell).click().perform();

			WebElement AddSnippet = driver.findElement(By.xpath("//*[@id='pySnippetSearchResults']/div/a[2]/span"));
			builder.moveToElement(AddSnippet).click().perform();

			WebElement CloseSnippetPane = driver
					.findElement(By.xpath("//*[@id='pySnippetList']//span[@class='svg svg-arw-back text-default']"));
			builder.moveToElement(CloseSnippetPane).click().perform();
			
			WebElement SnippetinTable = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/p"));

			softAssert.assertTrue(SnippetinTable.isEnabled(), "Variable not added");
			Thread.sleep(2000);
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 16, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowTypeContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();

			WebElement rowType = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//input[contains(@class,'form-control input-xs dropdown')]"));
			builder.moveToElement(rowType).click().build().perform();

			WebElement selectRowType = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//div[@class='tt-dataset tt-dataset-all']/div[@data-value=0]/span[2]"));
			builder.moveToElement(selectRowType).click().build().perform();

			WebElement firstRow = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]"));
			firstRow.getAttribute("class");

			softAssert.assertTrue(firstRow.getAttribute("class").contains("header"));
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 17, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowBorderColorContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			WebElement bottom_border = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='custom_border']"));
			builder.moveToElement(bottom_border).click().build().perform();

			WebElement bottom_border_value = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='border_thickness']"));
			bottom_border_value.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			bottom_border_value.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			WebElement color_picker = driver.findElement(
					By.xpath("//*[@id='pyContentTableProperties']//div[@class='input-group input-group-xxs']//button"));
			builder.moveToElement(color_picker).click().build().perform();

			Thread.sleep(3000);
			WebElement hex_field = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_hex_field']"));
			builder.moveToElement(hex_field).click().build().perform();
			builder.sendKeys(Keys.chord(Keys.CONTROL, "a"), "dbb8db");
			builder.sendKeys(Keys.ENTER);
			builder.build().perform();

			Thread.sleep(3000);
			WebElement OK = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_submit']"));
			builder.moveToElement(OK).click().build().perform();

			builder.click(FirstCell).perform();
			FirstCell.getAttribute("style");
			softAssert.assertTrue(FirstCell.getAttribute("style").contains("border-bottom-width: 10px"),
					"No border changes detected");

			// dbb8db
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 18, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowBorderColorTypeStyleContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));
			WebElement FirstParagraph = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/p"));
			builder.click(FirstCell).perform();

			WebElement style_table = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//span[contains(text(),'Style Table')]"));
			builder.moveToElement(style_table).click().build().perform();

			WebElement style_options = driver
					.findElement(By.xpath("//*[@id='pyTableStylesProperties']//a[contains(text(),'Header')]"));
			builder.moveToElement(style_options).click().build().perform();

			WebElement bottom_border = driver
					.findElement(By.xpath("//*[@id='pyTableHeader']//input[@name= 'th_custom_border']"));
			builder.moveToElement(bottom_border).click().build().perform();

			Thread.sleep(3000);
			WebElement color_picker = driver.findElement(
					By.xpath("//*[@id='pyTableHeader']/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/button"));
			builder.moveToElement(color_picker).click().build().perform();

			Thread.sleep(3000);
			WebElement hex_field = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_hex_field']"));
			builder.moveToElement(hex_field).click().build().perform();
			builder.sendKeys(Keys.chord(Keys.CONTROL, "a"), "dbb8db");
			builder.sendKeys(Keys.ENTER);
			builder.build().perform();

			Thread.sleep(3000);
			WebElement OK = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_submit']"));
			builder.moveToElement(OK).click().build().perform();

			WebElement bottom_border_value = driver
					.findElement(By.xpath("//*[@id='pyTableHeader']/div[2]/div[2]/div[2]/div/div/div[2]/div/input"));
			bottom_border_value.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			bottom_border_value.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			WebElement submit = driver
					.findElement(By.xpath("//*[@id='pyTableStylesProperties']//button[@type='submit']"));
			builder.moveToElement(submit).click().build().perform();

			Thread.sleep(2000);

			builder.click(FirstCell).perform();
			FirstParagraph.getAttribute("class");

			softAssert.assertTrue(FirstCell.getAttribute("style").contains("border-bottom-width: 10px"),
					"No border changes detected");

			// dbb8db
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 19, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyPaddingTypeStyleContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertContentTable();
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, -200, -200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));
			// WebElement FirstParagraph =
			// driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/p"));
			builder.click(FirstCell).perform();

			WebElement style_table = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//span[contains(text(),'Style Table')]"));
			builder.moveToElement(style_table).click().build().perform();

			WebElement style_options = driver
					.findElement(By.xpath("//*[@id='pyTableStylesProperties']//a[contains(text(),'Body')]"));
			builder.moveToElement(style_options).click().build().perform();

			// WebElement padding =
			// driver.findElement(By.xpath("//*[@id='pyTableHeader']//input[@name='th_custom_padding']"));
			// builder.moveToElement(padding).click().perform();

			Thread.sleep(3000);

			WebElement padding_top = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_top']"));
			WebElement padding_bottom = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_bottom']"));
			WebElement padding_right = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_right']"));
			WebElement padding_left = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_left']"));

			padding_top.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_top.sendKeys(Keys.TAB);
			padding_bottom.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_bottom.sendKeys(Keys.TAB);
			padding_right.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_right.sendKeys(Keys.TAB);
			padding_left.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_left.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			FirstCell.getAttribute("style");
			System.out.println("style of the cell" + FirstCell.getAttribute("style"));
			softAssert.assertTrue(FirstCell.getAttribute("style").contains("10"), "Padding value is not set");
			builder.moveToElement(FirstCell).click().build().perform();

			// dbb8db
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 20, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyInsertionofPageflowContentTable() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowContentTable();
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table"));
			softAssert.assertTrue(el.isEnabled(), "Content table not avialble");
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table"));
			builder.moveToElement(FirstCell).click().build().perform();
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 21, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowAddedtoPageflowContentTable() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowContentTable();
			Thread.sleep(3000);
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table"));
			builder.moveToElement(FirstCell).click().build().perform();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='content']"));
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
			List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			System.out.println("Before Add: " + tableRow.size());
			editObj.addRowContentTable();
			Thread.sleep(3000);
			List<WebElement> tableRowUpdated = tableBody.findElements(By.tagName("tr"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableRowUpdated.size());
			softAssert.assertTrue(tableRowUpdated.size() > tableRow.size(), "New Row is not been Added");
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 22, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowDeletedFromPageflowContentTable() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowContentTable();
			Thread.sleep(3000);
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table"));
			builder.moveToElement(FirstCell).click().build().perform();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='content']"));
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
			List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			System.out.println("Before Add: " + tableRow.size());
			editObj.deleteRowContentTable();
			Thread.sleep(3000);
			List<WebElement> tableRowUpdated = tableBody.findElements(By.tagName("tr"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableRowUpdated.size());
			softAssert.assertTrue(tableRowUpdated.size() < tableRow.size(), "New Row is not been Added");
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 23, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheColumnAddedToPageflowContentTable() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowContentTable();
			Thread.sleep(3000);
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table"));
			builder.moveToElement(FirstCell).click().build().perform();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='content']"));
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
			// List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			List<WebElement> tableCol = tableBody.findElements(By.tagName("td"));
			System.out.println("Before Add: " + tableCol.size());
			editObj.addColumnContentTable();
			Thread.sleep(3000);
			List<WebElement> tableColUpdated = tableBody.findElements(By.tagName("td"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableColUpdated.size());
			softAssert.assertTrue(tableColUpdated.size() > tableCol.size(), "New Column is not been Added");
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 24, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheColumnDeletedFromPageflowContentTable() throws InterruptedException, WebDriverException {
		try {

			editObj.insertPageFlowContentTable();
			Thread.sleep(3000);
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table"));
			builder.moveToElement(FirstCell).click().build().perform();
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='content']"));
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
			// List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			List<WebElement> tableCol = tableBody.findElements(By.tagName("td"));
			System.out.println("Before Add: " + tableCol.size());
			editObj.deleteColumnContentTable();
			Thread.sleep(3000);
			List<WebElement> tableColUpdated = tableBody.findElements(By.tagName("td"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableColUpdated.size());
			softAssert.assertTrue(tableColUpdated.size() < tableCol.size(), "Column has not been deleted");
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 25, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowTypePageFlowContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowContentTable();
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));
			builder.click(FirstCell).perform();

			WebElement rowType = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//input[contains(@class,'form-control input-xs dropdown')]"));
			builder.moveToElement(rowType).click().build().perform();

			WebElement selectRowType = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//div[@class='tt-dataset tt-dataset-all']/div[@data-value=0]/span[2]"));
			builder.moveToElement(selectRowType).click().build().perform();
			Thread.sleep(3000);

			WebElement firstRow = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]"));
			firstRow.getAttribute("class");

			softAssert.assertTrue(firstRow.getAttribute("class").contains("header"));
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 26, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheCellpaddingPageFlowContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowContentTable();

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.doubleClick(FirstCell).perform();
			Thread.sleep(3000);
			WebElement padding = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='custom_padding']"));
			builder.moveToElement(padding).click().perform();
			WebElement padding_top = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_top']"));
			WebElement padding_bottom = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_bottom']"));
			WebElement padding_right = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_right']"));
			WebElement padding_left = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='padding_left']"));

			padding_top.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_top.sendKeys(Keys.ENTER);
			padding_bottom.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_bottom.sendKeys(Keys.ENTER);
			padding_right.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_right.sendKeys(Keys.ENTER);
			padding_left.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_left.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			FirstCell.getAttribute("style");
			System.out.println("style of the cell" + FirstCell.getAttribute("style"));
			softAssert.assertTrue(FirstCell.getAttribute("style").contains("10"), "Padding value is not set");

			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 27, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowBorderColorPageFlowContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowContentTable();

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			WebElement bottom_border = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='custom_border']"));
			builder.moveToElement(bottom_border).click().build().perform();

			WebElement bottom_border_value = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//input[@name='border_thickness']"));
			bottom_border_value.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			bottom_border_value.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			WebElement color_picker = driver.findElement(
					By.xpath("//*[@id='pyContentTableProperties']//div[@class='input-group input-group-xxs']//button"));
			builder.moveToElement(color_picker).click().build().perform();

			Thread.sleep(3000);
			WebElement hex_field = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_hex_field']"));
			builder.moveToElement(hex_field).click().build().perform();
			builder.sendKeys(Keys.chord(Keys.CONTROL, "a"), "dbb8db");
			builder.sendKeys(Keys.ENTER);
			builder.build().perform();

			Thread.sleep(3000);
			WebElement OK = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_submit']"));
			builder.moveToElement(OK).click().build().perform();

			builder.click(FirstCell).perform();
			FirstCell.getAttribute("style");
			softAssert.assertTrue(FirstCell.getAttribute("style").contains("border-bottom-width: 10px"),
					"No border changes detected");

			// dbb8db
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 28, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheSnippetInsertionPageflowContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowContentTable();

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			Thread.sleep(3000);
			WebElement Snippets = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//span[contains(text(),'Snippets')]"));
			Thread.sleep(3000);
			builder.moveToElement(Snippets).click().perform();
			Thread.sleep(3000);
			builder.moveToElement(FirstCell).click().perform();

			WebElement AddSnippet = driver.findElement(By.xpath("//*[@id='pySnippetSearchResults']/div/a[2]/span"));
			builder.moveToElement(AddSnippet).click().perform();

			WebElement SnippetinTable = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/p"));

			softAssert.assertTrue(SnippetinTable.isEnabled(), "Variable not added");
			Thread.sleep(2000);
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 29, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheVariableInsertionPageFlowContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowContentTable();

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			Thread.sleep(3000);
			WebElement Variable = driver.findElement(By.xpath("//*[@id='pyContentTableProperties']//span/a"));
			Thread.sleep(3000);
			builder.moveToElement(Variable).click().perform();

			WebElement Search = driver.findElement(By.xpath("//*[@id='pyVariableList']//input[@name='keywords']"));

			builder.moveToElement(Search).sendKeys("name").build().perform();
			builder.moveToElement(FirstCell).click().perform();

			WebElement AddVariable = driver
					.findElement(By.xpath("//*[@id='variableCompany']/a[@data-name='{company_name}']/span"));
			builder.moveToElement(AddVariable).click().perform();

			WebElement VariableinTable = driver.findElement(By.xpath(
					"//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]//span[@data-placeholder='{company_name}']"));

			softAssert.assertTrue(VariableinTable.isEnabled(), "Variable not added");
			Thread.sleep(2000);
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test(priority = 30, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowBorderColorTypeStylePageFlowContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowContentTable();
			
			
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));
			WebElement FirstParagraph = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/p"));
			builder.click(FirstCell).perform();

			WebElement style_table = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//span[contains(text(),'Style Table')]"));
			builder.moveToElement(style_table).click().build().perform();

			WebElement style_options = driver
					.findElement(By.xpath("//*[@id='pyTableStylesProperties']//a[contains(text(),'Header')]"));
			builder.moveToElement(style_options).click().build().perform();

			WebElement bottom_border = driver
					.findElement(By.xpath("//*[@id='pyTableHeader']//input[@name= 'th_custom_border']"));
			builder.moveToElement(bottom_border).click().build().perform();

			Thread.sleep(3000);
			WebElement color_picker = driver.findElement(
					By.xpath("//*[@id='pyTableHeader']/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/button"));
			builder.moveToElement(color_picker).click().build().perform();

			Thread.sleep(3000);
			WebElement hex_field = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_hex_field']"));
			builder.moveToElement(hex_field).click().build().perform();
			builder.sendKeys(Keys.chord(Keys.CONTROL, "a"), "dbb8db");
			builder.sendKeys(Keys.ENTER);
			builder.build().perform();

			Thread.sleep(3000);
			WebElement OK = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_submit']"));
			builder.moveToElement(OK).click().build().perform();

			WebElement bottom_border_value = driver
					.findElement(By.xpath("//*[@id='pyTableHeader']/div[2]/div[2]/div[2]/div/div/div[2]/div/input"));
			bottom_border_value.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			bottom_border_value.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			//builder.click(FirstCell).perform();
			//FirstParagraph.getAttribute("class");

			//softAssert.assertTrue(FirstCell.getAttribute("style").contains("border-bottom-width: 10px"),
				//	"No border changes detected");

			// dbb8db
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 31, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyPaddingTypeStylePageFlowContentTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowContentTable();
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));
			builder.click(FirstCell).perform();

			WebElement style_table = driver
					.findElement(By.xpath("//*[@id='pyContentTableProperties']//span[contains(text(),'Style Table')]"));
			builder.moveToElement(style_table).click().build().perform();

			WebElement style_options = driver
					.findElement(By.xpath("//*[@id='pyTableStylesProperties']//a[contains(text(),'Body')]"));
			builder.moveToElement(style_options).click().build().perform();

			Thread.sleep(3000);

			WebElement padding_top = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_top']"));
			WebElement padding_bottom = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_bottom']"));
			WebElement padding_right = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_right']"));
			WebElement padding_left = driver
					.findElement(By.xpath("//*[@id='pyTableBody']//input[@name='tb_padding_left']"));

			padding_top.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_top.sendKeys(Keys.TAB);
			padding_bottom.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_bottom.sendKeys(Keys.TAB);
			padding_right.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_right.sendKeys(Keys.TAB);
			padding_left.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			padding_left.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			WebElement FirstTd = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));
			
			builder.moveToElement(FirstTd).click().build().perform();
			FirstTd.getAttribute("style");
			System.out.println("style of the cell" + FirstTd.getAttribute("style"));
			softAssert.assertTrue(FirstTd.getAttribute("style").contains("10"), "Padding value is not set");
		//	builder.moveToElement(FirstTd).click().build().perform();

			// dbb8db
			WebElement deleteTable = driver.findElement(By.xpath(
					"//*[@id='pyContentTableProperties']//a[contains(@class,'btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder')]/span[@class='svg svg-delete pull-left']"));
			builder.moveToElement(deleteTable).click().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 32, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheVideoDragandDrop() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.videoDragandDrop();

			Thread.sleep(3000);
			WebElement el = driver
					.findElement(By.xpath("//div[contains(@class,'selection-helper draggable multiple' )]"));
			softAssert.assertTrue(el.isDisplayed(), "Video Area is not present on the Editor");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				driver.navigate().refresh();
				editObj.VideoselectSection.click();

				List<WebElement> elem = driver.findElements(By.xpath(".//*[contains(@class,'element video')]"));
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
	
	@Test(priority = 33, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheFeeTableDragandDrop() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			softAssert.assertTrue(el.isDisplayed(), "content table not avialable");
			builder.dragAndDropBy(el, 200, 200).build().perform();
			softAssert.assertTrue(el.isDisplayed(),"table not present");
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

	}

	@Test(priority = 34, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowisAddedtotheTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			softAssert.assertTrue(el.isDisplayed(), "content table not avialable");
			builder.dragAndDropBy(el, 200, 200).build().perform();
			
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
			
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

	}
	
	@Test(priority = 35, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTheCurrencyChange() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			//Thread.sleep(3000);
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			softAssert.assertTrue(el.isDisplayed(), "content table not avialable");
			builder.dragAndDropBy(el, 200, 200).build().perform();
			
			editObj.addRowFeeTable();

			editObj.fillTableRows();
			editObj.displayPropertiesPanel();
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			Thread.sleep(2000);
			String currSel = curr.getText();
			System.out.println("currency" + currSel);
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
						System.out.println("made up price"+ amt_cal1);
						System.out.println("table data" + tbData.getText());
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
			System.out.println("before delete");
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			
			softAssert.assertAll();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	}
	
	@Test(priority = 36, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyShowTotalhidingtheTotalValue() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");

			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			softAssert.assertTrue(el.isDisplayed(), "content table not avialable");
			builder.dragAndDropBy(el, 200, 200).build().perform();
			
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
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test(priority = 37, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyOptionalforClientCheckBoxAppears() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			//softAssert.assertTrue(el.isDisplayed(), "content table not avialable");
			builder.dragAndDropBy(el, 200, 200).build().perform();
			
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
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				//	driver.findElement(By.xpath(".//*[@class='col-xs-7']//div[@class='input-group-btn']")));
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
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

	}
	
	@Test(priority = 38, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyEditableQuantity() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			//softAssert.assertTrue(el.isDisplayed(), "content table not avialable");
			builder.dragAndDropBy(el, 200, 200).build().perform();
			
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
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				//	driver.findElement(By.xpath(".//*[@class='col-xs-7']//div[@class='input-group-btn']")));
			Thread.sleep(3000);
			WebElement editable = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//input[@name='editable_qty']"));
			builder.moveToElement(editable).click().build().perform();

			for (int i = 0; i < trow.size();) {
				System.out.println("---");
				List<WebElement> tdata = trow.get(i).findElements(By.cssSelector("td[class='fr-selected-cell']"));
				for (WebElement td : tdata) {
					System.out.println("---");
					WebElement optionalCheckBox = td.findElement(By.xpath(
							"//*[@id='pyCanvasList']//table/tbody/tr/td[3]/div/div/input"));

					softAssert.assertTrue(optionalCheckBox.isDisplayed(),"optional checkbox not displayed");

				}
				break;

			}
			Thread.sleep(4000);
			editObj.displayPropertiesPanel();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 39, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheTaxHasBeenAddedtotheTotal() throws WebDriverException {
		try {

			Thread.sleep(3000); 
			editObj.insertTable("2");
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			builder.dragAndDropBy(el, 200, 200).build().perform();
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.fillTableRows();
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			Thread.sleep(2000);
			String currSel = curr.getText();
			System.out.println("currSel" + currSel);
			editObj.addTax();
			editObj.fillTaxeswithParameter("GST","5");
			Thread.sleep(3000);
			editObj.addTax();
			editObj.fillTaxeswithParameter("tax2","15");
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
							System.out.println("subtotal string" +subTotalString);
							softAssert.assertTrue(subTotalString.equals("Subtotal"),
									"Subtotal text verification error");
						}

						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.tamt);
							String amt = tfData.getText() + ".00";
							System.out.println("made up value "+amt_cal);
							System.out.println("actual value "+amt);
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
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			//editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 40, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheDiscountHasBeenAddedtotheTotal() throws WebDriverException {
		try {

			Thread.sleep(3000); 
			editObj.insertTable("2");
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			builder.dragAndDropBy(el, 200, 200).build().perform();
			Thread.sleep(3000);
			editObj.addRowFeeTable();
			editObj.fillTableRows();
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			Thread.sleep(2000);
			String currSel = curr.getText();
			System.out.println("currSel" + currSel);
			editObj.addDiscount();
			editObj.fillDiscountswithParameter("Discount","5");
			Thread.sleep(3000);
			editObj.addDiscount();
			editObj.fillDiscountswithParameter("Discount 2","15");
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
							System.out.println("subtotal string" +subTotalString);
							softAssert.assertTrue(subTotalString.equals("Subtotal"),
									"Subtotal text verification error");
						}

						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.tamt);
							String amt = tfData.getText() + ".00";
							System.out.println("made up value "+amt_cal);
							System.out.println("actual value "+amt);
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
							String matchValue = "Discount 5%";
							String n = matchValue.trim();
							System.out.println("n value :" + n);
							softAssert.assertEquals(taxString, n, "Discount text verification error");
						}
						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							double addedTaxPercent = Integer
									.parseInt(tableFooterRow.get(i).getAttribute("data-rate-value"));
							double addedTaxValue = (addedTaxPercent * editObj.tamt) / 100;
							String amt_cal = "-"+ currSel + decimalFormat.format(addedTaxValue);
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
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			//editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 41, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyImportFee() throws WebDriverException {
		try {

			 	Thread.sleep(3000);
	            editObj.insertTable("2");
	            WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
	            builder.dragAndDropBy(el, 200, 200).build().perform();
	            Thread.sleep(3000);
	            
	        
	            WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
	        	WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
	        	List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
	        	System.out.println("Before Add: " + tableRow.size());
	            
	            WebElement importFees = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-condition='openFeeLibrary']"));
	            builder.moveToElement(importFees).click().build().perform();
	            
	            WebElement fee1 = driver.findElement(By.xpath("//*[@id='pyFeeLibrarySearchResults']/ul/li[1]/div/div[1]/input[@name='fee[]']"));
	            builder.moveToElement(fee1).click().build().perform();
	            
	            WebElement AddtoTable = driver.findElement(By.xpath("//*[@id='pyFeeLibraryPane']//a[contains(text(),'Add to Table')]"));
	            builder.moveToElement(AddtoTable).click().build().perform();
	            
	            WebElement closeLibrary = driver.findElement(By.xpath("//*[@id='pyFeeLibraryPane']//a[@data-callback='closeFeeLibrary']"));
	            builder.moveToElement(closeLibrary).click().build().perform();
	            
	            WebElement feeTableupdated = driver.findElement(By.cssSelector("table[data-type='pricing']"));
	        	WebElement tableBodyupdated = feeTableupdated.findElement(By.tagName("tbody"));
	            List<WebElement> tableRowUpdated = tableBodyupdated.findElements(By.tagName("tr"));
	        	System.out.println("After Add: " + tableRowUpdated.size());
	        
	        	softAssert.assertTrue(tableRowUpdated.size() > tableRow.size(), "New Row is not been Added");           
	            editObj.displayPropertiesPanel();
	            WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
	            builder.moveToElement(delete).click().build().perform();
	            //editObj.deleteTable();
	            driver.close();
	            softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 42, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyImportVariableFeeTable() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, 200, 200).build().perform();
			// WebElement ContentTable =
			// driver.findElement(By.cssSelector("table[data-type='content']"));

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			Thread.sleep(3000);
			WebElement Variable = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//span/a"));
			Thread.sleep(3000);
			builder.moveToElement(Variable).click().perform();

		WebElement Search = driver.findElement(By.xpath("//*[@id='pyVariableList']//input[@name='keywords']"));

			builder.moveToElement(Search).sendKeys("name").build().perform();
			builder.moveToElement(FirstCell).click().perform();

			WebElement AddVariable = driver
					.findElement(By.xpath("//*[@id='variableCompany']/a[@data-name='{company_name}']/span"));
			builder.moveToElement(AddVariable).click().perform();

			Thread.sleep(3000);
			
			WebElement CloseVariabelPane = driver
					.findElement(By.xpath("//*[@id='pyVariableList']//span[@class='svg svg-arw-back text-default']"));
			builder.moveToElement(CloseVariabelPane).click().perform();

			
			WebElement VariableinTable = driver.findElement(By.xpath(
					"//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]//span[@data-placeholder='{company_name}']"));

			
			
			softAssert.assertTrue(VariableinTable.isEnabled(), "Variable not added");
			Thread.sleep(2000);
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 43, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyImportSnippetFeeTable() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertTable("2");
			WebElement el = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
			Thread.sleep(3000);
			el.getLocation();
			builder.dragAndDropBy(el, 200, 200).build().perform();
			

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			Thread.sleep(3000);
			WebElement Snippet = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-success='bindSnippetLibraryEventListeners']"));
			Thread.sleep(3000);
			builder.moveToElement(Snippet).click().perform();
			
//			builder.moveToElement(FirstCell).click().perform();

			WebElement AddSnippet = driver.findElement(By.xpath("//*[@id='pySnippetSearchResults']/div/a[2]/span"));
			builder.moveToElement(AddSnippet).click().perform();

			WebElement CloseSnippetPane = driver
					.findElement(By.xpath("//*[@id='pySnippetList']//span[@class='svg svg-arw-back text-default']"));
			builder.moveToElement(CloseSnippetPane).click().perform();
			
			WebElement SnippetinTable = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/p"));

			softAssert.assertTrue(SnippetinTable.isEnabled(), "Snippet not added");

			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 44, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
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
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			//editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 45, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyAddRowPageFlowFeeTable() throws WebDriverException {
		try {

			editObj.insertPageFlowFeeTable("6");
			Thread.sleep(3000);
			WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
			System.out.println("NO");
			WebElement tableBody = feeTable.findElement(By.tagName("tbody"));

			List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
			System.out.println("Before Add: " + tableRow.size());
			editObj.addRowPageFlow();
			
			List<WebElement> tableRowUpdated = tableBody.findElements(By.tagName("tr"));
			Thread.sleep(3000);
			System.out.println("After Add: " + tableRowUpdated.size());
			softAssert.assertTrue(tableRowUpdated.size() > tableRow.size(), "New Row is not been Added");
			
			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			//editObj.deleteTable();
			driver.close();
			softAssert.assertAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 46, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
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
			System.out.println("currency"+ currSel);
			Thread.sleep(3000);
			editObj.addRowPageFlow();
			Thread.sleep(3000);
			editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
			// editObj.addRowPageFlow();
		
			editObj.fillPageFlowTableRows1();

			List<WebElement> tableID = driver.findElements(By.xpath(
					".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[contains(@class,'canvas')]"));
			System.out.println("tableID" + tableID);

			int tableDataID = 0;
			for (int i = 0; i < tableID.size(); i++) {
				System.out.println(tableID.get(i).getAttribute("data-id"));

				if (i == tableID.size() - 1) {
					tableDataID = Integer.parseInt(tableID.get(i).getAttribute("data-id"));
				}

			}
			System.out.println(tableDataID);
//			WebElement FinalPageFeeTable = driver
//					.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[@data-id="
//							+ "'" + tableDataID + "'" + "]//div[@class='elements']//table[@data-type='pricing']"));
			
			
			WebElement FinalPageFeeTable = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tfoot"));
			//WebElement tableFoot = FinalPageFeeTable.findElement(By.tagName("tfoot"));
			builder.doubleClick(FinalPageFeeTable).perform();

			WebElement tableFooterRow = FinalPageFeeTable.findElement(By.tagName("tr"));
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
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			//editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 47, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyOptionalforClientCheckBoxAppearsPageFlow() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			Thread.sleep(3000);		
			editObj.addRowPageFlow();
			editObj.optionalForClientOptionTurnON();
			WebElement optionalCheckBox = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[9]//table/tbody/tr/td[1]/span/input"));

			softAssert.assertTrue(optionalCheckBox.isEnabled());


			Thread.sleep(4000);
			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
		//	editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 48, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyEditableqtyAppearsPageFlow() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			Thread.sleep(3000);		
			editObj.addRowPageFlow();
			
			WebElement editable = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//input[@name='editable_qty']"));
			builder.moveToElement(editable).click().build().perform();

			WebElement editableqty = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[9]//table/tbody/tr/td[1]/span/input"));

			softAssert.assertTrue(editableqty.isEnabled());


			Thread.sleep(4000);
			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
		//	editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 49, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyTaxforPageFlowFeeTable() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("6");
			Thread.sleep(3000);		
			editObj.addRowPageFlow();
			editObj.fillPageFlowfeeTableRows();
			
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			Thread.sleep(2000);
			String currSel = curr.getText();
			
			editObj.addTax();
			editObj.fillTaxesPageFlowTablewithPaameter("GST", "5");
			editObj.addTax();
			editObj.fillTaxesPageFlowTablewithPaameter("tax2", "15");
			editObj.taxAmountCaluclation();
			
			
			WebElement FinalPageFeeTable1 = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tfoot"));
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
							String amt_cal = currSel + decimalFormat.format(editObj.tamt);
							String amt = tfData.getText() + ".00";
							System.out.println("Subtotal made"+amt_cal);
							System.out.println("subtotal actual"+amt);
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
							double addedTaxValue = (addedTaxPercent * editObj.tamt) / 100;
							String amt_cal = currSel + decimalFormat.format(addedTaxValue);
							String amt = tfData.getText() + ".00";
							System.out.println("first tax value with currency "+amt_cal);
							System.out.println("first tax value actually"+amt);
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

			

			Thread.sleep(4000);
			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
		//	editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 50, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheDiscountHasBeenAddedtotheTotalPageFlow() throws WebDriverException {
		try {

			Thread.sleep(3000); 
			editObj.insertPageFlowFeeTable("2");
			Thread.sleep(3000);
			editObj.addRowPageFlow();
			editObj.fillPageFlowfeeTableRows();
			editObj.changeFeeCurrency("10");
			builder.moveToElement(editObj.customFormatToggle).click().perform();
			WebElement curr = driver.findElement(By.xpath(".//*[@class='target']//span[@class='currency']"));
			Thread.sleep(2000);
			String currSel = curr.getText();
			System.out.println("currSel" + currSel);
			editObj.addDiscount();
			editObj.fillDiscountswithParameter("Discount","5");
			Thread.sleep(3000);
			editObj.addDiscount();
			editObj.fillDiscountswithParameter("Discount 2","15");
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
							System.out.println("subtotal string" +subTotalString);
							softAssert.assertTrue(subTotalString.equals("Subtotal"),
									"Subtotal text verification error");
						}

						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							String amt_cal = currSel + decimalFormat.format(editObj.tamt);
							String amt = tfData.getText() + ".00";
							System.out.println("made up value "+amt_cal);
							System.out.println("actual value "+amt);
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
							String matchValue = "Discount 5%";
							String n = matchValue.trim();
							System.out.println("n value :" + n);
							softAssert.assertEquals(taxString, n, "Discount text verification error");
						}
						if (flg == 2) {
							DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
							decimalFormat.setGroupingUsed(true);
							decimalFormat.setGroupingSize(3);
							double addedTaxPercent = Integer
									.parseInt(tableFooterRow.get(i).getAttribute("data-rate-value"));
							double addedTaxValue = (addedTaxPercent * editObj.tamt) / 100;
							String amt_cal = "-"+ currSel + decimalFormat.format(addedTaxValue);
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
			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			//editObj.deleteTable();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 51, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyShowTotalhidingtheTotalValuePageFlow() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("2");
			editObj.displayPropertiesPanelforPageFlowTable();
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
			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 52, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyImportFeePageFlow() throws WebDriverException {
		try {

			 	Thread.sleep(3000);
	            editObj.insertPageFlowFeeTable("2"); 
	            editObj.displayPropertiesPanelforPageFlowTable();
	            WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
	        	WebElement tableBody = feeTable.findElement(By.tagName("tbody"));
	        	List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
	        	System.out.println("Before Add: " + tableRow.size());
	            
	            WebElement importFees = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-condition='openFeeLibrary']"));
	            builder.moveToElement(importFees).click().build().perform();
	            
	            WebElement fee1 = driver.findElement(By.xpath("//*[@id='pyFeeLibrarySearchResults']/ul/li[1]/div/div[1]/input[@name='fee[]']"));
	            builder.moveToElement(fee1).click().build().perform();
	            
	            WebElement AddtoTable = driver.findElement(By.xpath("//*[@id='pyFeeLibraryPane']//a[contains(text(),'Add to Table')]"));
	            builder.moveToElement(AddtoTable).click().build().perform();
	            
	            WebElement closeLibrary = driver.findElement(By.xpath("//*[@id='pyFeeLibraryPane']//a[@data-callback='closeFeeLibrary']"));
	            builder.moveToElement(closeLibrary).click().build().perform();
	            
	            WebElement feeTableupdated = driver.findElement(By.cssSelector("table[data-type='pricing']"));
	        	WebElement tableBodyupdated = feeTableupdated.findElement(By.tagName("tbody"));
	            List<WebElement> tableRowUpdated = tableBodyupdated.findElements(By.tagName("tr"));
	        	System.out.println("After Add: " + tableRowUpdated.size());
	        
	        	softAssert.assertTrue(tableRowUpdated.size() > tableRow.size(), "New Row is not been Added");           
	            editObj.displayPropertiesPanelforPageFlowTable();
	            WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
	            builder.moveToElement(delete).click().build().perform();
	            //editObj.deleteTable();
	            driver.close();
	            softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 53, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyImportVariablePageFlowFeeTable() throws WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("2");
			
			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			Thread.sleep(3000);
			WebElement Variable = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//span/a"));
			Thread.sleep(3000);
			builder.moveToElement(Variable).click().perform();

		WebElement Search = driver.findElement(By.xpath("//*[@id='pyVariableList']//input[@name='keywords']"));

			builder.moveToElement(Search).sendKeys("name").build().perform();
			builder.moveToElement(FirstCell).click().perform();

			WebElement AddVariable = driver
					.findElement(By.xpath("//*[@id='variableCompany']/a[@data-name='{company_name}']/span"));
			builder.moveToElement(AddVariable).click().perform();

			Thread.sleep(3000);
			
			WebElement CloseVariabelPane = driver
					.findElement(By.xpath("//*[@id='pyVariableList']//span[@class='svg svg-arw-back text-default']"));
			builder.moveToElement(CloseVariabelPane).click().perform();

			
			WebElement VariableinTable = driver.findElement(By.xpath(
					"//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]//span[@data-placeholder='{company_name}']"));

			
			
			softAssert.assertTrue(VariableinTable.isEnabled(), "Variable not added");
			Thread.sleep(2000);
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 54, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyImportSnippetPageFlowFeeTable() throws WebDriverException {
		try {

			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			editObj.insertPageFlowFeeTable("2");

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			
			WebElement Snippet = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-success='bindSnippetLibraryEventListeners']"));
			js.executeScript("arguments[0].scrollIntoView();", Snippet);
			builder.moveToElement(Snippet).click().perform();

			Thread.sleep(3000);
			WebElement AddSnippet = driver.findElement(By.xpath("//*[@id='pySnippetSearchResults']/div/a[2]/span"));
			builder.moveToElement(AddSnippet).click().perform();

			WebElement CloseSnippetPane = driver
					.findElement(By.xpath("//*[@id='pySnippetList']//span[@class='svg svg-arw-back text-default']"));
			builder.moveToElement(CloseSnippetPane).click().perform();
			
			WebElement SnippetinTable = driver
					.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]/p"));

			softAssert.assertTrue(SnippetinTable.isEnabled(), "Snippet not added");

			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 55, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifytheRowBorderColorPageFlowFeeTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("2");

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			WebElement bottom_border = driver
					.findElement(By.xpath("//*[@id='pyPricingTableProperties']//input[@name='custom_border']"));
			builder.moveToElement(bottom_border).click().build().perform();

			WebElement bottom_border_value = driver
					.findElement(By.xpath("//*[@id='pyPricingTableProperties']//input[@name='border_thickness']"));
			bottom_border_value.sendKeys(Keys.chord(Keys.CONTROL, "a"), "10");
			bottom_border_value.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			WebElement color_picker = driver.findElement(
					By.xpath("//*[@id='pyPricingTableProperties']//div[@class='input-group input-group-xxs']//button"));
			builder.moveToElement(color_picker).click().build().perform();

			Thread.sleep(3000);
			WebElement hex_field = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_hex_field']"));
			builder.moveToElement(hex_field).click().build().perform();
			builder.sendKeys(Keys.chord(Keys.CONTROL, "a"), "dbb8db");
			builder.sendKeys(Keys.ENTER);
			builder.build().perform();

			Thread.sleep(3000);
			WebElement OK = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_submit']"));
			builder.moveToElement(OK).click().build().perform();

			builder.click(FirstCell).perform();
			FirstCell.getAttribute("style");
			softAssert.assertTrue(FirstCell.getAttribute("style").contains("border-bottom-width: 10px"),
					"No border changes detected");

			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 56, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void changetheRowColorPageFlowFeeTable() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.insertPageFlowFeeTable("2");

			WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]/td[1]"));

			builder.click(FirstCell).perform();
			
			WebElement color_picker = driver.findElement(
					By.xpath("//*[@id='pyPricingTableProperties']//div[@class='color form-control input-xs']"));
			builder.moveToElement(color_picker).click().build().perform();

			Thread.sleep(3000);
			WebElement hex_field = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_hex_field']"));
			builder.moveToElement(hex_field).click().build().perform();
			builder.sendKeys(Keys.chord(Keys.CONTROL, "a"), "dbb8db");
			builder.sendKeys(Keys.ENTER);
			builder.build().perform();

			Thread.sleep(3000);
			WebElement OK = driver.findElement(By.xpath(
					"//*[@class='colpick colpick_full'][contains(@style,'display')]//div[@class='colpick_submit']"));
			builder.moveToElement(OK).click().build().perform();

			WebElement FirstRow= driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr[1]"));
			FirstRow.getAttribute("data-custom-background-color");
			softAssert.assertTrue(FirstCell.getAttribute("style").contains("1"),
					"No border changes detected");

			editObj.displayPropertiesPanelforPageFlowTable();
			WebElement delete = driver.findElement(By.xpath("//*[@id='pyPricingTableProperties']//a[@data-callback='handleTableDeleteButtonClick']"));
			builder.moveToElement(delete).click().build().perform();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 56, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyVideoWidthProperty() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.videoDragandDrop();
			Thread.sleep(3000);
			editObj.enterTextboxWidthProperty("500");
			Thread.sleep(3000);
			WebElement el = driver
					.findElement(By.xpath("//div[contains(@class,'selection-helper draggable multiple')]"));
			softAssert.assertTrue(
					el.getAttribute("style").contains("width: " + editObj.width.getAttribute("value") + "px;"),
					"VideoArea with width " + editObj.width.getAttribute("value") + "is not been adjusted");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test(priority = 57, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyVideoRotateProperty() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.videoDragandDrop();
			Thread.sleep(3000);
			editObj.enterTextboxRotateProperty("20");
			Thread.sleep(3000);
			WebElement el = driver
					.findElement(By.xpath("//div[contains(@class,'selection-helper draggable multiple' )]"));
			softAssert.assertTrue(
					el.getAttribute("style")
							.contains("transform: rotate(" + editObj.rotate.getAttribute("value") + "deg);"),
					"Video with Rotate " + editObj.rotate.getAttribute("value") + "is not been adjusted");
			softAssert.assertTrue(editObj.rotate.getAttribute("name").equals("rotate"));
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 58, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 59, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyVideoThumbnail() throws InterruptedException, WebDriverException {
		try {

			Thread.sleep(3000);
			editObj.videoDragandDrop();
			// click on upload thumbnail from video propoerties
			WebElement thumbbutton = driver
					.findElement(By.xpath("//*[@id='pyVideoProperties']//a[contains(@class,'btn-svg-border cinder')]"));
			thumbbutton.click();
			// hover over image so that insert thumb button is displayed
			Actions builder = new Actions(driver);
			builder.moveToElement(driver
					.findElement(By.xpath("//*[@id='pyImageLibrarySearchResults']/div[contains(@class,'image')]")))
					.build().perform();
			Thread.sleep(3000);
			// Click on insert thumb button
			driver.findElement(By.xpath("//*[@id='pyImageLibrarySearchResults']/div[1]//span[@class='btn-label']"))
					.click();
			// click on top right close button to close image library
			driver.findElement(By.xpath("//*[@id='pyImageLibraryPane']//button[@class='close']")).click();

			WebElement thumbnailDisplayed = driver.findElement(By.xpath(
					"//*[@id='pyVideoProperties']//div[@class='square']/div[contains(@class,'square-content')]"));
			softAssert.assertTrue(thumbnailDisplayed.isDisplayed(), "Video Thumbnail is not present on the Editor");
			editObj.deletetheElement();
			driver.close();
			softAssert.assertAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(priority = 60, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyNewSectionisCreated() {
		try {
			Thread.sleep(3000);

			editObj.PageFlowFeeTableSelectSection.click();
			List<WebElement> sectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			
			int sectionCount = sectionList.size();
			System.out.println("Before adding Section: " + sectionCount);
			editObj.createNewSectionwithPageFlow("TestSection");
			Thread.sleep(3000);
			//driver.navigate().refresh();
			Thread.sleep(3000);
			List<WebElement> newSectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int newSectionCount = newSectionList.size();
			System.out.println("After adding Section: " + newSectionCount);
			softAssert.assertTrue(newSectionCount == sectionCount + 1, "New Section Creation Failed");
			WebElement newSectionName = driver.findElement(By.xpath(
					".//*[@id='pyPageList']//ul[@class='section-list sortable']//li//span[text()='TestSection']"));
			softAssert.assertTrue(newSectionName.isDisplayed(), "new Section is not displayed");
			Thread.sleep(3000);
			editObj.deletingSection("TestSection");
			driver.close();
			softAssert.assertAll();
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

	@Test(priority = 61, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyDuplicateSectionRename() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithPageFlow("DuplicateSection");
			(new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='DuplicateSection']")));
			Thread.sleep(4000);
			editObj.duplicateSection();
			(new WebDriverWait(driver, 10))
					.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[text()='DuplicateSection']"), 2));
			Thread.sleep(3000);
			editObj.renamingSectionAfterDuplicateSection();
			List<WebElement> duplicateSectionafterRename = driver
					.findElements(By.xpath("//span[text()='DuplicateSection2']"));
			
			softAssert.assertTrue(duplicateSectionafterRename.size() == 1, "Section is not Renamed");
			
			Thread.sleep(2000);
			editObj.deleteSection();
			Thread.sleep(2000);
			editObj.deleteLastSection();
			driver.close();
			softAssert.assertAll();
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

	
}
