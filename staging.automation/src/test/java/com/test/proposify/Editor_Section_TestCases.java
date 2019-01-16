package com.test.proposify;

import static org.testng.Assert.assertFalse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Login;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Editor_Section_TestCases {
	static WebDriver driver;

	Revamp_Login loginObj;
	Editor editObj;
	Actions builder;
	SoftAssert softAssert;
	int test_Count = 0;

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


	@Test(priority = 1, enabled = true)
	public void verifyCreateSectionOptionisDisplayed() {
		try {

			softAssert.assertTrue(editObj.newSection.isDisplayed(), "Create Section Option is not Dsiaplyed");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 2, enabled = true)
	public void verifyNewSectionModalisDisplayed() {
		try {
			Thread.sleep(3000);
			editObj.newSection.click();
			Thread.sleep(3000);
			WebElement createSectionModal = driver.findElement(By.xpath(".//*[@class='modal-content']"));
			softAssert.assertTrue(createSectionModal.isDisplayed());
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 3, enabled = true)
	public void verifyNewSectionisCreated() {
		try {
			Thread.sleep(3000);

			List<WebElement> sectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int sectionCount = sectionList.size();
			System.out.println("Before adding Section: " + sectionCount);
			editObj.createNewSectionwithPageFlow("TestSection");
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(3000);
			List<WebElement> newSectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int newSectionCount = newSectionList.size();
			System.out.println("After adding Section: " + newSectionCount);
			softAssert.assertTrue(newSectionCount == sectionCount + 1, "New Section Creation Failed");
			WebElement newSectionName = driver.findElement(By.xpath(
					".//*[@id='pyPageList']//ul[@class='section-list sortable']//li//span[text()='TestSection']"));
			softAssert.assertTrue(newSectionName.isDisplayed(), "new Section is not displayed");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}

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

	@Test(priority = 4, enabled = true)
	public void verifySectionNameMandatoryError() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithPageFlow("");
			String errorString = "Please enter a section name.";
			WebElement sectionNameError = driver
					.findElement(By.xpath(".//*[@id='pySectionCreateForm']//p[@id='name-error']"));
			softAssert.assertTrue(sectionNameError.isDisplayed(), "Error Not Displayed");
			System.out.println(sectionNameError.getText());
			softAssert.assertTrue(sectionNameError.getText().equals(errorString), "Error text Doesn't match");
			
			
			
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 5, enabled = true)
	public void verifyNewSectionisCreatedWithStaticOption() {
		try {
			Thread.sleep(3000);
			List<WebElement> sectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int sectionCount = sectionList.size();
			System.out.println("Before adding Section: " + sectionCount);
			editObj.createNewSectionwithStaticPage("StaticSection");
			Thread.sleep(5000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			List<WebElement> newSectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int newSectionCount = newSectionList.size();
			System.out.println("After adding Section: " + newSectionCount);
			softAssert.assertTrue(newSectionCount == sectionCount + 1, "New Section Creation Failed");
			WebElement newSectionName = driver.findElement(By.xpath(
					".//*[@id='pyPageList']//ul[@class='section-list sortable']//li//span[text()='StaticSection']"));
			softAssert.assertTrue(newSectionName.isDisplayed(), "new Section is not displayed");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}

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

	@Test(priority = 6, enabled = true)
	public void verifyNewSectionModalisCancelled() {
		try {
			Thread.sleep(3000);
			List<WebElement> sectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int sectionCount = sectionList.size();
			System.out.println("Before adding Section: " + sectionCount);
			editObj.createNewSectionMOdalCancel("TestSection");
			Thread.sleep(3000);

			driver.navigate().refresh();
			Thread.sleep(5000);
			List<WebElement> newSectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int newSectionCount = newSectionList.size();
			System.out.println("After adding Section: " + newSectionCount);
			softAssert.assertTrue(newSectionCount == sectionCount, "New Section Creation Failed");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 7, enabled = true)
	public void verifySectionCreatedWithTag() {
		try {
			Thread.sleep(3000);
			List<WebElement> sectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int sectionCount = sectionList.size();
			System.out.println("Before adding Section: " + sectionCount);
			editObj.createNewSectionwithTag("SectionTest");
			String tagPlaceHolderText = editObj.sectionTagTextBox.getAttribute("placeholder");
			softAssert.assertTrue(tagPlaceHolderText.equals("Click to add a tag"));
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(3000);
			List<WebElement> newSectionList = driver
					.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id]"));
			int newSectionCount = newSectionList.size();
			System.out.println("After adding Section: " + newSectionCount);
			softAssert.assertTrue(newSectionCount == sectionCount + 1, "New Section Creation Failed");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 8, enabled = true)
	public void verifySectionEditOption() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithPageFlow("EditSection");
			(new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='EditSection']")));
			Thread.sleep(4000);
			editObj.editSection();
			builder.moveToElement(editObj.editSettings).click(editObj.editSettings).perform();
			softAssert.assertTrue(editObj.sectionEditForm.isDisplayed());
			WebElement editedSection = driver.findElement(By.xpath("//span[text()='EditSectionTest']"));
			softAssert.assertTrue(editedSection.isDisplayed(), "Section Name is not Edited");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 9, enabled = true)
	public void verifySectionDuplicateOption() {
		try {

			editObj.createNewSectionwithPageFlow("DuplicateSection");
			(new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='DuplicateSection']")));
			Thread.sleep(4000);
			editObj.duplicateSection();
			(new WebDriverWait(driver, 10))
					.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[text()='DuplicateSection']"), 2));
			Thread.sleep(3000);
			List<WebElement> duplicateSection = driver.findElements(By.xpath("//span[text()='DuplicateSection']"));
			softAssert.assertTrue(duplicateSection.size() == 2, "Section is not duplicated");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 10, enabled = true)
	public void verifySectionDeleteOption() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithPageFlow("DeleteSectionTest");

			editObj.deletingSection("DeleteSectionTest");

			List<WebElement> delSection = driver.findElements(By.xpath("//span[text()='DeleteSectionTest']"));

			Thread.sleep(3000);
			System.out.println(delSection.size());
			softAssert.assertTrue(delSection.size() > 0, "Section is not deleted");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 11, enabled = true)
	public void verifySectionDeleteOptionwithCancel() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithPageFlow("DeleteSectionTest");
			Thread.sleep(3000);
			editObj.deletingSectionwithCancel("DeleteSectionTest");
			Thread.sleep(3000);

			List<WebElement> delSection = driver.findElements(By.xpath("//span[text()='DeleteSectionTest']"));

			Thread.sleep(3000);
			System.out.println(delSection.size());
			softAssert.assertTrue(delSection.size() > 0, "Section is deleted");
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 12, enabled = true)
	public void verifyAddingPagestoStaticSection() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithStaticPage("DeleteSectionTest");
			Thread.sleep(3000);
			editObj.addingPagesunderStaticSection();
			Thread.sleep(3000);

			List<WebElement> addStaticPages = driver.findElements(By.xpath(
					".//*[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse sortable']//li[@class]"));

			softAssert.assertTrue(addStaticPages.size() == 6, "Added Static Pages not equal to as expected ");
			// driver.close();
			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}

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

	@Test(priority = 13, enabled = true)
	public void verifyDeletingStaticSection() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithStaticPage("DeleteSectionTest");
			Thread.sleep(4000);
			editObj.addingPagesunderStaticSection();
			editObj.deletingSection("DeleteSectionTest");
			Thread.sleep(4000);
			List<WebElement> delSection = driver.findElements(By.xpath("//span[text()='DeleteSectionTest']"));

			softAssert.assertTrue(delSection.size() == 0, "Section is not deleted");

			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 14, enabled = true)
	public void verifyDeletingStaticSectionPages() {
		try {
			Thread.sleep(3000);
			editObj.createNewSectionwithStaticPage("DeleteSectionTest");
			Thread.sleep(3000);
			editObj.addingPagesunderStaticSection();
			editObj.deletingSection("DeleteSectionTest");
			Thread.sleep(3000);
			List<WebElement> delSection = driver.findElements(By.xpath("//span[text()='DeleteSectionTest']"));

			softAssert.assertTrue(delSection.size() > 0, "Section is not deleted");

			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	@Test(priority = 15, enabled = true)
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
			List<WebElement> duplicateSection = driver.findElements(By.xpath("//span[text()='DuplicateSection']"));
			softAssert.assertTrue(duplicateSection.size() == 1, "Section is not duplicated");
			List<WebElement> duplicateSectionafterRename = driver
					.findElements(By.xpath("//span[text()='DuplicateSection2']"));
			softAssert.assertTrue(duplicateSectionafterRename.size() == 1, "Section is not Renamed");

			if (editObj.phpErrorPopupOKButton1.size() > 0) {
				softAssert.assertTrue(editObj.phpErrorPopupOKButton1.size() == 0, "You have got a PHP Error");

			}
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

	/*
	 * @AfterMethod(alwaysRun=true) public void lookforPhpError() { test_Count++;
	 * if(editObj.phpErrorPopupOKButton1.size()>0) { driver.close();
	 * Assert.assertTrue(editObj.phpErrorPopupOKButton1.size()==0,
	 * test_Count+" You have got a PHP Error");
	 * 
	 * //softAssert.assertAll();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

	@AfterClass(alwaysRun = true)
	public void tearDown() throws InterruptedException {

		Dimension d = new Dimension(1380, 1000);
		// Resize the current window to the given dimension
		driver.manage().window().setSize(d);
		loginObj = PageFactory.initElements(driver, Revamp_Login.class);
		editObj = PageFactory.initElements(driver, Editor.class);
		// softAssert = new SoftAssert();
		List<WebElement> allSections = driver.findElements(By.xpath(
				".//*[@class='section-list sortable']//li[@data-id]//a[@class='section-name']//span[@class='section-label text-small']"));
		int flag = 0;
		for (WebElement sections : allSections) {
			flag++;
			if (flag > 1 && flag <= allSections.size() - 1) {
				String sectionName = sections.getText();
				Thread.sleep(4000);
				editObj.deletingSection(sectionName);
			}

		}
		driver.close();
	}

}
