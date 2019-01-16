package com.test.proposify;

import java.util.List;
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

public class CreateAndarchieve_proposal {

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
			driver.get("https://testgdpr2.proposify.org/dashboard");

			if (!driver.getCurrentUrl().equals("https://testgdpr2.proposify.org/dashboard")) {
				driver.navigate().refresh();
				driver.get("https://testgdpr2.proposify.org/dashboard");
			}

		}

	}
	
	@Test(priority = 0, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyCreateandArchiveProposal() throws InterruptedException, WebDriverException {
		try {
			Thread.sleep(3000);
			
			WebElement ul = driver
					.findElement(By.xpath("//*[@id='pyDashboardSearchResults']/div[2]/div[2]/ul"));
			List<WebElement> list = ul.findElements(By.xpath("./li"));
			list.size();
			System.out.println("number of proposal" + list.size());
			
			WebElement newProposal = driver
					.findElement(By.xpath("//*[@id='pyNewProposalButton']"));
			builder.moveToElement(newProposal).click().perform();
			Thread.sleep(3000);
			WebElement newProposal_button = driver
					.findElement(By.xpath("//*[@id='pyTemplateArea']//a[@class='new cinder']//span[@class='svg svg-add']"));
			builder.moveToElement(newProposal_button).click().perform();
			
			WebElement proposal_name = driver
					.findElement(By.xpath("//*[@id='pyProposalSettingsName']"));
			Thread.sleep(2000);
			proposal_name.sendKeys("test");
			//builder.moveToElement(proposal_name).sendKeys("test").perform();
			
			WebElement proposal_due = driver
					.findElement(By.xpath("//*[@id='pyProposalSettingsDueDate']"));
			builder.moveToElement(proposal_due).click().perform();
	
			WebElement client = driver
					.findElement(By.xpath("//*[@id='pyProposalSettingsClient']"));
			builder.moveToElement(client).click().perform();
			WebElement client_name = driver
					.findElement(By.xpath("	//*[@id='pyProposalSettingsForm']//span[contains(text(),'Example')]"));
			builder.moveToElement(client_name).click().perform();
			
			Thread.sleep(2000);
			WebElement submit = driver
					.findElement(By.xpath("//*[@id='pyProposalSettingsForm']//button[@type = 'submit']"));
			builder.moveToElement(submit).click().perform();
			
			WebElement back = driver
					.findElement(By.xpath("//*[@id='pyEditorToolbar']/div[1]/div[1]//button"));
			builder.moveToElement(back).click().perform();
			
			WebElement pipeline = driver
					.findElement(By.xpath("//*[@id='pyEditorToolbar']//a[contains(text(),'pipeline')]"));
			builder.moveToElement(pipeline).click().perform();
			

			WebElement ul_new = driver
					.findElement(By.xpath("//*[@id='pyDashboardSearchResults']/div[2]/div[2]/ul"));
			List<WebElement> list_new = ul_new.findElements(By.xpath("./li"));
			System.out.println("new list"+list_new.size());
			softAssert.assertTrue(list_new.size()>list.size());
			
			WebElement new_proposal = driver
					.findElement(By.xpath("//*[@id='pyDashboardSearchResults']/div[2]/div[2]/ul//span[contains(text(),'test')]"));
			builder.moveToElement(new_proposal).click().perform();
			
			WebElement archive = driver
					.findElement(By.xpath("//*[@id='pyProposalBreadcrumbsArea']//a[@data-original-title='Move to Archives']"));
			builder.moveToElement(archive).click().perform();
			
			WebElement confirm = driver
					.findElement(By.xpath("/html/body/div[7]//button[contains(text(),'archive')]"));
			builder.moveToElement(confirm).click().perform();
			 
			
			
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
	
	@Test(priority = 1, retryAnalyzer = com.test.proposify.RetryAnalyzer.class)
	public void verifyCreateNewStream() throws InterruptedException, WebDriverException{
		
		WebElement addStream = driver.findElement(By.xpath("//*[@id='pyDashboardToolbarArea']//div[@class='streams']//span['svg svg-add']"));
		builder.moveToElement(addStream).click().perform();
		
		
	}
	
}
