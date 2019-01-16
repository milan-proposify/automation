package com.test.proposify;

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
import org.proposify.pages.Editor;
import org.proposify.pages.Revamp_Dashboard;
import org.proposify.pages.Revamp_Login;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Dashboard_TestCases {
	static WebDriver driver;
	Actions builder;
	Revamp_Login loginObj;
	Revamp_Dashboard dash;
	SoftAssert softAssert;
	Editor editObj;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) throws InterruptedException {

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
			// driver.get("https://milan0077.proposify.com/proposal/edit/1751");
			//
			// if
			// (!driver.getCurrentUrl().equals("https://milan0077.proposify.com/proposal/edit/1751"))
			// {
			// driver.navigate().refresh();
			// driver.get("https://milan0077.proposify.com/proposal/edit/1751");
			// }

		}

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "//Users//Milan//Downloads//geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--start-fullscreen");
			driver = new FirefoxDriver(options);
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

		else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			builder = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://staging2.proposify.org");
			loginObj = PageFactory.initElements(driver, Revamp_Login.class);
			editObj = PageFactory.initElements(driver, Editor.class);
			softAssert = new SoftAssert();

		}

	}

	@Test(priority = 0)
	public void verifyUIelementsOnDashBoard() throws InterruptedException {
		try {
			softAssert.assertTrue(dash.searchProposalTextBox.isDisplayed(),
					"Proposal Search Text Box is not displayed");
			softAssert.assertTrue(dash.magnifyinLenseSearchProposal.isDisplayed(),
					"Magnifying Lense inside  Proposal Search Text Box is not displayed ");
			softAssert.assertTrue(dash.selectAllProposalCheckBox.isDisplayed(),
					"All Proposal CheckBox is not Displayed");
			softAssert.assertTrue(dash.proposalSortingDropDown.isDisplayed(),
					"Proposal Sorting DropDown is not Displayed");
			softAssert.assertTrue(dash.starButton.isDisplayed(), "Star button is not displayed");
			softAssert.assertTrue(dash.viewArchive.isDisplayed(), "View Archive Image is not Displayed");
			softAssert.assertTrue(dash.addStream.isDisplayed(), "Add Stream is not Displayed");
			softAssert.assertTrue(dash.createProposalButton.isDisplayed(), "Create Proposal Button is not Displayed");
			// System.out.println(dash.totalPipeLineText.getText());
			softAssert.assertTrue(dash.totalPipeLineText.getText().equals("TOTAL PIPELINE"), "New");
			Thread.sleep(3000);
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

	@Test(priority = 1)
	public void verifyHoverElementsOnTheProposalStrip() throws InterruptedException {
		try {

			WebElement firstProposalUnderDraft = driver
					.findElement(By.xpath(".//*[@class='proposals list-group no-margin sortable']//li[1]"));
			builder.moveToElement(firstProposalUnderDraft).perform();
			Thread.sleep(3000);
			WebElement propEdit = firstProposalUnderDraft
					.findElement(By.xpath(".//*[@class='btn btn-svg btn-default cinder']"));
			WebElement propCogIcon = firstProposalUnderDraft
					.findElement(By.xpath(".//*[@class='btn btn-default btn-svg context-menu']"));
			softAssert.assertTrue(propEdit.isDisplayed());
			softAssert.assertTrue(propCogIcon.isDisplayed());

			Thread.sleep(3000);
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

	@Test(priority = 2)
	public void verifyProposalBeingStarred() throws InterruptedException {
		try {

			dash.staraProposal(3);

			List<WebElement> proposalDraft = driver.findElements(By.xpath(
					".//*[@id='pyDashboardSearchResults']//ul[@class='proposals list-group no-margin sortable']"));
			int flag = 0;
			for (WebElement draftArea : proposalDraft) {
				flag++;
				if (flag == 1) {
					for (int i = 1; i <= 3; i++) {
						WebElement propStar = draftArea
								.findElement(By.xpath("//li[" + i + "]//span[contains(@class,'starred')]"));
						softAssert.assertTrue(propStar.isDisplayed(), "Proposal Star not marked");
					}
				} else {
					break;
				}
			}

			Thread.sleep(3000);
			dash.unStaraProposal(3);
			driver.close();
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			int var = 1;
			softAssert.assertFalse(var == 1, "Fail");
			driver.close();
			softAssert.assertAll();

		}

	}


	@Test(priority = 3)
	public void verifyCreateProposalButtonFunctionality() throws InterruptedException {
		try {
			Thread.sleep(3000);
			dash.clickCreateProposalButton();
			Thread.sleep(3000);
			softAssert.assertTrue(driver.getCurrentUrl().equals("https://proposify2.proposify.com/proposal/templates"),
					"Not redirected to the Tempated Page");
			softAssert.assertTrue(driver.getTitle().equals("Templates"), "Incorrect Page Title");
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

	@Test(priority = 6)
	public void verifyViewArchive() throws InterruptedException {
		try {

			dash.clickViewArchive();
			Thread.sleep(2000);
			softAssert.assertTrue(driver.getCurrentUrl().equals("https://proposify2.proposify.com/archive"));
			softAssert.assertTrue(driver.getTitle().equals("Proposal Archives"));
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

	@Test(priority = 7)
	public void verifyAddStream() throws InterruptedException {
		try {

			List<WebElement> streamTotalBeforeAdd = driver.findElements(
					By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id]"));
			System.out.println("Before Stream Add " + streamTotalBeforeAdd.size());
			dash.addStreamFunctionality();
			Thread.sleep(3000);
			List<WebElement> streamTotalAfterAdd = driver.findElements(
					By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id]"));
			System.out.println("After Stream Add " + streamTotalAfterAdd.size());

			softAssert.assertTrue(streamTotalAfterAdd.size() == streamTotalBeforeAdd.size() + 1);

			dash.deleteStreamFunctionality(2);
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

	@Test(priority = 8)
	public void verifyDeleteStream() throws InterruptedException {
		try

		{

			List<WebElement> streamTotalBeforeDelete = driver.findElements(
					By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id]"));
			System.out.println("Before Stream Delete " + streamTotalBeforeDelete.size());
			dash.addStreamFunctionality();
			Thread.sleep(3000);
			dash.deleteStreamFunctionality(2);
			Thread.sleep(3000);
			List<WebElement> streamTotalAfterDelete = driver.findElements(
					By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id]"));
			System.out.println("After Stream Delete " + streamTotalAfterDelete.size());

			softAssert.assertTrue(streamTotalAfterDelete.size() == streamTotalBeforeDelete.size());

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

	@Test(priority = 9)
	public void verifyMessageAfterFiveStream() throws InterruptedException {
		try {
			dash.addStreamFunctionality();
			dash.addStreamFunctionality();
			dash.addStreamFunctionality();
			dash.addStreamFunctionality();

			WebElement stopAddStream = driver.findElement(By.xpath(
					".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@class='pull-left add stream']"));
			builder.moveToElement(stopAddStream).click(stopAddStream).perform();
			Thread.sleep(2000);

			WebElement noAddStreamToolTipMessage = driver.findElement(
					By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@aria-describedby]"));
			List<WebElement> streamTotalBeforeDelete = driver.findElements(
					By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id]"));
			System.out.println("Before Stream Add " + streamTotalBeforeDelete.size());
			dash.addStreamFunctionality();
			Thread.sleep(3000);
			dash.addStreamFunctionality();
			Thread.sleep(3000);
			dash.deleteStreamFunctionality(2);
			List<WebElement> streamTotalAfterDelete = driver.findElements(
					By.xpath(".//*[@class='streams']//ul[@class='nav text-small sortable']//li[@data-id]"));
			System.out.println("After Stream Add " + streamTotalAfterDelete.size());

			softAssert.assertTrue(streamTotalAfterDelete.size() == streamTotalBeforeDelete.size() + 1);

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
