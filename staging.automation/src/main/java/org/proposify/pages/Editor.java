package org.proposify.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Driver;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Editor {
	final WebDriver driver;
	Actions builder;

	@FindBy(how = How.XPATH, using = ".//*[@class='tool-list']//li[2]//a[@data-id='textbox']")
	public WebElement textIcon;

	@FindBy(how = How.CSS, using = "a[data-id='image']")
	public WebElement imageIcon;

	@FindBy(how = How.CSS, using = "a[data-id='video']")
	public WebElement videoIcon;

	@FindBy(how = How.CSS, using = "a[data-id='shape']")
	public WebElement shapeIcon;
	
	@FindBy(how = How.CSS, using = "a[data-id='path']")
	public WebElement pathIcon;

	@FindBy(how = How.CSS, using = "a[data-id='table']")
	public WebElement tableIcon;

	@FindBy(how = How.CSS, using = "a[data-id='signature']")
	public WebElement signatureIcon;

	@FindBy(how = How.XPATH, using = "//div[@class='section-group']")
	public WebElement canvasArea;

	@FindBy(how = How.XPATH, using = "//button[text()='Ok']")
	public WebElement button;

	@FindBy(how = How.NAME, using = "video_url")
	public WebElement videoUrlTB;

	@FindBy(how = How.NAME, using = "width")
	public WebElement width;

	@FindBy(how = How.NAME, using = "height")
	public WebElement height;

	@FindBy(how = How.NAME, using = "rotate")
	public WebElement rotate;

	@FindBy(how = How.XPATH, using = "//label[@class='btn btn-default active toggle-off']")
	public WebElement borderToggle;

	@FindBy(how = How.XPATH, using = "//button[text()='Update']")
	public WebElement updateButton;

	@FindBy(how = How.XPATH, using = "//span[text()='TextBoxSection']")
	public WebElement TextBoxselectSection;

	@FindBy(how = How.XPATH, using = "//span[text()='VideoSection']")
	public WebElement VideoselectSection;

	@FindBy(how = How.XPATH, using = "//span[text()='ShapeSection']")
	public WebElement ShapeselectSection;
	
	@FindBy(how = How.XPATH, using = "//span[text()='LineSection']")
	public WebElement LineSection;
	
	@FindBy(how = How.XPATH, using = "//span[text()='StandAloneContentTable']")
	public WebElement StandAloneContentTable;

	@FindBy(how = How.XPATH, using = "//span[text()='StandAloneFeeTableSection']")
	public WebElement StandAloneTableSelectSection;

	@FindBy(how = How.XPATH, using = "//span[text()='PageFlowFeeTableSection']")
	public WebElement PageFlowFeeTableSelectSection;

	@FindBy(how = How.XPATH, using = "//span[text()='TestSection']")
	public WebElement TestSection;
	
	@FindBy(how = How.CSS, using = "a[href='#page2']")
	public WebElement page;

	@FindBy(how = How.XPATH, using = ".//*[@id='pyPageList']//div[@class='col-xs-6 text-center']")
	public WebElement newSection;

	@FindBy(how = How.NAME, using = "name")
	public WebElement newSectionName;

	@FindBy(how = How.XPATH, using = "//label[text()='Pages are created as content is added']")
	public WebElement pageFlowOption;

	@FindBy(how = How.XPATH, using = "//label[text()='Pages are manually created as needed']")
	public WebElement staticPageOption;

	@FindBy(how = How.XPATH, using = "//button[text()='Ok']")
	public WebElement okButton;

	@FindBy(how = How.XPATH, using = ".//*[@class='modal-footer ']//button[@class='btn-lg btn-default btn btn-block']")
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='pySectionCreateForm']")
	public WebElement createSectionModal;

	@FindBy(how = How.XPATH, using = ".//*[@class='modal-footer ']//button[@class='btn-lg btn-primary btn btn-block']")
	public WebElement createSectionSaveButton;
	
	@FindBy(how = How.XPATH, using = ".//*[@class='modal-footer ']//button[@class='btn-lg btn-primary btn']")
	public WebElement editSectionSaveButton;

	@FindBy(how = How.XPATH, using = ".//*[@class='modal-footer ']//button[@class='btn-lg btn-default btn btn-block']")
	public WebElement createSectionCancelButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='pySectionCreateForm']//div[4]//input[@placeholder]")
	public WebElement sectionTagTextBox;

	@FindBy(how = How.XPATH, using = "//span[@class='svg svg-cog']")
	public WebElement sectionSettings;

	@FindBy(how = How.XPATH, using = ".//*[text()='Delete']")
	public WebElement deleteOption;

	@FindBy(how = How.XPATH, using = "//li[@class='modal confirm modal-small draggable in']//h3")
	public WebElement deleteModal;

	@FindBy(how = How.XPATH, using = ".//*[text()='Edit Settings']")
	public WebElement editSettings;

	@FindBy(how = How.XPATH, using = ".//*[@id='pySectionSettingsForm']")
	public WebElement sectionEditForm;

	@FindBy(how = How.XPATH, using = ".//*[@id='pySectionSettingsForm']//input[@type='text']")
	public WebElement sectionEditFormTextBox;

	@FindBy(how = How.XPATH, using = ".//*[@id='pySectionSettingsForm']//span[@class='twitter-typeahead']//input[@type='text'][2]")
	public WebElement sectionEditTagTextBox;

	@FindBy(how = How.XPATH, using = ".//*[text()='Duplicate']")
	public WebElement duplicateOption;

	@FindBy(how = How.XPATH, using = "//li[@class='context-menu-item']//span[text()='Permissions']")
	public WebElement permissonOption;

	@FindBy(how = How.CSS, using = "input[name='border_radius']")
	public WebElement textBoxcorners;

	@FindBy(how = How.CSS, using = "input[name='border-radius']")
	public WebElement corners;

	@FindBy(how = How.XPATH, using = "//div[@class='slider-handle min-slider-handle round']")
	public WebElement Opacityslider;

	@FindBy(how = How.XPATH, using = "//div[@class='tooltip-inner']//span[@class='svg svg-delete']")
	public WebElement binDelete;

	@FindBy(how = How.XPATH, using = "//*[@id=\"pyTableCreate\"]/div/div[2]/div[2]/div/label[1]")
	public WebElement feeTable;

	@FindBy(how = How.CSS, using = "input[value='content']")
	public WebElement contentTable;

	@FindBy(how = How.XPATH, using = "//*[@id=\"tableMenuRowAdd-11\"]/span[@class='svg svg-add']")
	public WebElement addRow;

	@FindBy(how = How.XPATH, using = ".//*[contains(@class,'btn btn-default btn-block btn-xs addtax')]")
	public WebElement showTotalToggle;

	@FindBy(how = How.XPATH, using = ".//*[@class='col-xs-7']//div[@class='input-group-btn']")
	public WebElement rowColorChange;

	@FindBy(how = How.XPATH, using = ".//*[@class='panel-collapse collapse']//div[@class='col-xs-6']//div[@class='input-group input-group-xxs']")
	public WebElement borderColorChange;

	@FindBy(how = How.XPATH, using = ".//*[@name='currency_id-typeahead']")
	public WebElement changeCurrency;

	@FindBy(how = How.XPATH, using = ".//*[@class='list-unstyled pane-buttons']//li[3]//a[@class='btn btn-default btn-block btn-xs btn-svg btn-svg-border cinder']")
	public WebElement deleteTableButton;

	@FindBy(how = How.XPATH, using = "//*[@id='fee']//button[@type='submit']")
	public WebElement insertTable;

	@FindBy(how = How.XPATH, using = ".//*[@class='modal-content']//div[@class='modal-footer ']//div[@class='row']//div[@class='col-xs-12']//button[@class='btn-primary btn btn-block']")
	public WebElement phpErrorPopupOKButton;

	@FindBy(how = How.XPATH, using = ".//*[text()='An error has occurred. Please refresh the page, if the problem persists please contact ']")
	public List<WebElement> phpErrorPopupOKButton1;

	@FindBy(how = How.XPATH, using = "//*[@id=\"pyShapeProperties\"]//input[@type=\"checkbox\"]")
	public WebElement elementsBorderToggle;

	@FindBy(how = How.XPATH, using = ".//*[@class='input-group  bootstrap-touchspin bootstrap-touchspin-injected']//input[@name='border-width']")
	public WebElement borderSize;

	@FindBy(how = How.XPATH, using = ".//*[@name='border-style-typeahead']")
	public WebElement borderStyle;

	@FindBy(how = How.XPATH, using = ".//*[@class='form-group']//input[@name='name']")
	public WebElement sectionName;

	@FindBy(how = How.XPATH, using = ".//*[@class='btn-group btn-group-justified select']//input[@value='0']")
	public WebElement sectionTypePageFlow;

	@FindBy(how = How.XPATH, using = ".//*[@class='twitter-typeahead']//input[@placeholder='Click to add a tag']")
	public WebElement sectionTag;

	@FindBy(how = How.XPATH, using = "//*[@id='pyPricingTableProperties']//input[@name='custom_currency_format']")
	public WebElement customFormatToggle;

	@FindBy(how = How.XPATH, using = ".//*[@id='pyPageSettingsProperties']//div[8]//a")
	public WebElement pageSettingCancelLink;

	@FindBy(how = How.XPATH, using = ".//*[@class='section-list sortable']//li[@class='selected']//input[@name='name']")
	public WebElement sectionRenameTB;

	@FindBy(how = How.XPATH, using = ".//*[@class='section-list sortable']//li[@class='selected']//button[@type='submit']")
	public WebElement sectionRenameDoneButton;

	@FindBy(how = How.XPATH, using = ".//*[@class='section-list sortable']//li[@class='selected']//span[@class='svg svg-cancel']")
	public WebElement sectionRenameCross;

	@FindBy(how = How.XPATH, using = ".//*[@id='pyPricingTableProperties']")
	public WebElement tablePropoertiesPane;

	public Editor(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(this.driver);

	}

	// Services

	public void createNewSectionwithPageFlow(String nameSection) throws InterruptedException {
		newSection.click();
		Thread.sleep(3000);
		sectionName.sendKeys(nameSection);
		createSectionSaveButton.click();

	}

	public void createNewSectionMOdalCancel(String nameSection) throws InterruptedException {
		newSection.click();
		Thread.sleep(3000);
		sectionName.sendKeys(nameSection);
		createSectionCancelButton.click();

	}

	public void createNewSectionwithTag(String nameSection) throws InterruptedException {
		newSection.click();
		Thread.sleep(3000);
		sectionName.sendKeys(nameSection);
		sectionTagTextBox.click();
		sectionTagTextBox.sendKeys("SectionTagTest");
		createSectionSaveButton.click();

	}

	public void createNewSectionwithStaticPage(String nameSection) {
		newSection.click();
		sectionName.sendKeys(nameSection);
		staticPageOption.click();
		createSectionSaveButton.click();

	}

	public void addingPagesunderStaticSection() {
		WebElement addPageSign = driver.findElement(By.xpath("//*[@id='pyPageList']/ul/li[2]/ul/li[2]/a/span[contains(@class,'svg svg-add')]"));
		addPageSign.click();
		addPageSign.click();
		addPageSign.click();
		addPageSign.click();
		addPageSign.click();

	}

	public void deletingSection(String deleteSectionName) throws InterruptedException
	{
		//builder.moveToElement(driver.findElement(By.xpath("//span[text()='section1']"))).perform()
		builder.moveToElement(driver.findElement(By.xpath("//span[text()='"+deleteSectionName+"']"))).perform();
		
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[10]//span[@class='svg svg-delete']"))).click().perform();
		WebElement deleteConfirm = driver.findElement(By.xpath("/html/body//button[@class='btn btn-lg btn-danger btn-block']"));
		builder.moveToElement(deleteConfirm).click().perform();
		Thread.sleep(4000);
		//okButton.click();
		
	}

	public void deletingSectionwithCancel(String deleteSectionName) throws InterruptedException {
		// builder.moveToElement(driver.findElement(By.xpath("//span[text()='section1']"))).perform()
		//builder.moveToElement(driver.findElement(By.xpath("//span[text()=" + "TestSection"))).perform();
		
		builder.moveToElement(driver.findElement(By.xpath("//span[text()= 'TestSection'"))).perform();
		builder.moveToElement(driver.findElement(By.xpath(
				".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.click(driver.findElement(By.xpath(
						".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.perform();
		builder.moveToElement(deleteOption).click(deleteOption).perform();
		
		Thread.sleep(3000);
		cancelButton.click();

	}

	public void deletingStaticPages() throws InterruptedException {
		int pgFlag = 0;

		List<WebElement> statPages = driver.findElements(By.xpath(
				".//*[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse sortable']//li[@class]"));
		// WebElement contextDelete = driver.findElement(By.xpath(".//*[text))
		for (WebElement page : statPages) {
			pgFlag++;
			if (pgFlag < statPages.size()) {
				builder.moveToElement(page).contextClick(page).click(deleteOption).perform();
				Thread.sleep(4000);
				okButton.click();
			}

		}

	}

	public void duplicateSection() {
		// builder.moveToElement(driver.findElement(By.xpath("//span[text()='section1']"))).perform();
		builder.moveToElement(driver.findElement(By.xpath("//span[text()='DuplicateSection']"))).perform();
		builder.moveToElement(driver.findElement(By.xpath(
				".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.click(driver.findElement(By.xpath(
						".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.perform();
		builder.moveToElement(duplicateOption).click(duplicateOption).perform();
		// createSectionSaveButton.click();

	}

	public void deleteSection() {
		// builder.moveToElement(driver.findElement(By.xpath("//span[text()='section1']"))).perform();
		builder.moveToElement(driver.findElement(By.xpath("//span[text()='DuplicateSection']"))).perform();
		builder.moveToElement(driver.findElement(By.xpath(
				".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.click(driver.findElement(By.xpath(
						".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.perform();
		builder.moveToElement(deleteOption).click(deleteOption).perform();
		okButton.click();
		// createSectionSaveButton.click();

	}

	public void deleteLastSection() throws InterruptedException {
		// builder.moveToElement(driver.findElement(By.xpath("//span[text()='section1']"))).perform();
		builder.moveToElement(driver.findElement(By.xpath("//span[text()='DuplicateSection2']"))).perform();
		
		builder.moveToElement(driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[10]//span[@class='svg svg-delete']"))).click().perform();
		WebElement deleteConfirm = driver.findElement(By.xpath("/html/body//button[@class='btn btn-lg btn-danger btn-block']"));
		Thread.sleep(2000);
		builder.moveToElement(deleteConfirm).click().perform();
		

	}

	
	public void renamingSectionAfterDuplicateSection() {

		List<WebElement> sectionwithSameName = driver.findElements(By.xpath("//span[text()='DuplicateSection']"));
		int secFlag = 0;
		for (WebElement dupSection : sectionwithSameName) {
			secFlag++;

			if (secFlag == 2) {
			//	builder.moveToElement(dupSection).doubleClick(dupSection).perform();
			//	builder.doubleClick(sectionRenameTB).perform();
				sectionRenameTB.clear();
				sectionRenameTB.sendKeys("DuplicateSection2");
				sectionRenameDoneButton.click();
			}

		}
		// createSectionSaveButton.click();

	}

	public void CancelRenamingSectionAfterDuplicateSection() {
		List<WebElement> sectionwithSameName = driver.findElements(By.xpath("//span[text()='DuplicateSection']"));
		int secFlag = 0;
		for (WebElement dupSection : sectionwithSameName) {
			secFlag++;

			if (secFlag == 2) {
				builder.moveToElement(dupSection).doubleClick(dupSection).perform();
				builder.doubleClick(sectionRenameTB).perform();
				sectionRenameTB.sendKeys("DuplicateSection2");
				sectionRenameCross.click();
			}

		}
		// createSectionSaveButton.click();

	}

	public void editSection() throws InterruptedException {
		builder.moveToElement(driver.findElement(By.xpath("//span[text()='EditSection']"))).perform();
		Thread.sleep(3000);
		builder.moveToElement(driver.findElement(By.xpath(
				".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.click(driver.findElement(By.xpath(
						".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@data-id][2]//span[@class='svg svg-more']")))
				.perform();
		builder.moveToElement(editSettings).click(editSettings).perform();
		builder.doubleClick(sectionEditFormTextBox).perform();
		sectionEditFormTextBox.sendKeys("EditSectionTest");
		sectionEditTagTextBox.click();
		sectionEditTagTextBox.sendKeys("NewChangedTag");
		Thread.sleep(3000);
		editSectionSaveButton.click();

	}
	
	public void insertContent() throws InterruptedException {
		 Thread.sleep(3000);
         WebElement el = driver.findElement(By.xpath("//span[text()='TextBoxSection']"));
         el.click();
         Thread.sleep(3000);
         WebElement currentTextbox = driver
                 .findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[2]/div/div/div/p"));
         builder.moveToElement(currentTextbox).doubleClick().build().perform();
         builder.moveToElement(currentTextbox).doubleClick().build().perform();
		builder.moveToElement(currentTextbox).sendKeys("Test").build().perform();
	
		
	}
	
	
	public void insertLine() throws InterruptedException {
		LineSection.click();
		//Linesetup();
		Thread.sleep(3000);
		WebElement master = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[5]/div[2]/div[4]/div[contains(@class,'master')]"));
		pathIcon.click();
		builder.moveToElement(master).click(master).perform();
	
		
	}
	
	public void enterLineWidthProperty(String Width) throws InterruptedException {
		Thread.sleep(5000);
		builder.doubleClick(width).perform();
		Thread.sleep(3000);
		width.sendKeys(Width);
		width.sendKeys(Keys.RETURN);
	}
	
	public void enterLineThicknessProperty(String Thickness) throws InterruptedException {
		Thread.sleep(5000);
		builder.doubleClick(height).perform();
		Thread.sleep(3000);
		height.sendKeys(Thickness);
		height.sendKeys(Keys.RETURN);
	}
	
	public void enterLineCornersProperty(String Corners) throws InterruptedException {
		Thread.sleep(5000);
		builder.doubleClick(corners).perform();
		Thread.sleep(3000);
		corners.sendKeys(Corners);
		corners.sendKeys(Keys.RETURN);
	}
	
	public void enterLineRotatePropoerty(String Rotate) throws InterruptedException {
		Thread.sleep(3000);
		builder.doubleClick(rotate).perform();
		rotate.sendKeys(Rotate);
		rotate.sendKeys(Keys.RETURN);
	}
	
	public void insertContentTable() throws InterruptedException {
		StandAloneContentTable.click();
		Thread.sleep(2000);
		WebElement master = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[6]/div[2]/div[4]/div[contains(@class,'master')]"));
		tableIcon.click();
		WebElement contentTable = driver.findElement(By.xpath("//*[@id='pyTableCreate']//div[contains(@class,'btn-group btn-group-justified btn-group-editor btn-group-xs')]/label[2]"));
		builder.moveToElement(contentTable).click(contentTable).perform();
		Thread.sleep(3000);
		builder.moveToElement(master).click(master).perform();
	}
	
	public void insertPageFlowContentTable() throws InterruptedException {
		StandAloneContentTable.click();
		Thread.sleep(2000);
		WebElement master = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[6]/div[2]/div[4]/div[2]/div/div/div/p"));
		builder.moveToElement(master).doubleClick().build().perform();
		builder.moveToElement(master).doubleClick().build().perform();
		tableIcon.click();
		WebElement contentTable = driver.findElement(By.xpath("//*[@id='pyTableCreate']//div[contains(@class,'btn-group btn-group-justified btn-group-editor btn-group-xs')]/label[2]"));
		builder.moveToElement(contentTable).click(contentTable).perform();
		Thread.sleep(3000);	
		
		WebElement Insert = driver.findElement(By.xpath("//*[@id='content']//button"));
		//Thread.sleep(3000);
		builder.moveToElement(Insert).click().build().perform();
		//Thread.sleep(3000);
	}

	
	
	public void insertTextArea() throws InterruptedException {
		TextBoxselectSection.click();
		textAreaPageSetUp();
		Thread.sleep(3000);
		WebElement master = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[2]/div[2]/div[4]/div[contains(@class,'master')]"));
		textIcon.click();
		builder.moveToElement(master).click(master).perform();
	
		
	}
	
	public boolean textAreaPageSetUp() {
		try {
			int txtCount = 0;
			List<WebElement> totalTableCount = driver.findElements(By.xpath(".//*[contains(@class,'selection-helper draggable multiple')]"));

			System.out.println(totalTableCount.size() + " textbox Found");
			for (WebElement tab : totalTableCount) {
				txtCount++;
				System.out.println("Deleting text area " + txtCount);

				if (tab.isDisplayed()) {
					builder.moveToElement(tab).perform();
					builder.click(tab).perform();
					deletetheElement();

				}
			}
		} catch (Exception NoSuchElementException) {
			return false;
		}
		return true;

	}
	
	public void dragTextArea() throws InterruptedException {
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		builder.moveToElement(TextBoxselectSection).click(TextBoxselectSection).perform();
		Thread.sleep(3000);
		builder.moveToElement(TextBoxselectSection).click(TextBoxselectSection).perform();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		List<WebElement> pages = driver.findElements(By.className("section-page"));
		int pagFlag = 0;
		for (WebElement elmPage : pages) {
			pagFlag++;

			if (pagFlag == 4) {

				builder.moveToElement(elmPage).click(elmPage).perform();
				break;
			}
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// List<WebElement> el =
		// driver.findElements(By.xpath("//div[@class='section-group']"));

		// List <WebElement> el =driver.findElements(By.xpath("//div[@class='canvas
		// can-select loaded']"));
		List<WebElement> el = driver.findElements(By.xpath(
				".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[4]//div[@class='canvas selected loaded']//div[@class='elements']//div[contains(@class,'master')]"));

		int count = el.size();
		System.out.println(count);

		for (int i = 0; i < count; i++) {
			if (el.get(i).isDisplayed()) {
				System.out.println("----");
				Thread.sleep(3000);
				// System.out.println(textIcon.size());
				// textIcon.click();

				builder.moveToElement(textIcon).click(textIcon).perform();

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				builder.moveToElement(el.get(i))
						.dragAndDropBy(el.get(i), el.get(i).getLocation().getX(), el.get(i).getLocation().getY())
						.perform();

				WebElement eld = driver.findElement(By.xpath(
						".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[4]//div[@class='canvas selected loaded']//div[@class='elements']//div[contains(@class,'draggable selected')]"));

				((JavascriptExecutor) driver).executeScript(
						"arguments[0].setAttribute('style', 'left: 164px; top: 118px; width: 200px; height: 205px;');",
						eld);

				System.out.println("----");
				Thread.sleep(3000);

				WebElement canvasClick = driver.findElement(
						By.xpath(".//*[@id='pyCanvasList']//div[4]//div[@class='canvas selected loaded']"));

				builder.click(canvasClick).perform();

				WebElement selectionHolder = driver.findElement(By.xpath(
						".//*[@id='pyCanvasList']//div[4]//div[@class='canvas selected loaded']//div[@class='elements']//div[2]"));

				builder.moveToElement(selectionHolder).click(selectionHolder).perform();

				// builder.moveToElement(selectionHolder).click(selectionHolder).perform();
				break;

			}
		}
	}

	public void enterTextboxWidthProperty(String Width) throws InterruptedException {
		Thread.sleep(5000);
		builder.doubleClick(width).perform();
		Thread.sleep(3000);
		width.sendKeys(Width);
		width.sendKeys(Keys.RETURN);
	}

	public void enterTextboxHeightProperty(String Height) throws InterruptedException {
		Thread.sleep(3000);
		builder.doubleClick(height).perform();
		height.sendKeys(Height);
		width.sendKeys(Keys.RETURN);

	}

	public void enterTextboxCornerProperty(String Corner) throws InterruptedException {
		Thread.sleep(3000);
		builder.doubleClick(textBoxcorners).perform();
		textBoxcorners.sendKeys(Corner);
		textBoxcorners.sendKeys(Keys.RETURN);

	}

	public void enterCornerProperty() throws InterruptedException {
		Thread.sleep(3000);
		builder.doubleClick(corners).perform();
		corners.sendKeys("50");
		corners.sendKeys(Keys.RETURN);

	}

	public void enterTextboxRotateProperty(String Rotate) throws InterruptedException {
		Thread.sleep(3000);
		builder.doubleClick(rotate).perform();
		rotate.sendKeys(Rotate);
		rotate.sendKeys(Keys.RETURN);

	}

	public void movetheSlider() throws InterruptedException {
		Thread.sleep(3000);
		int width = Opacityslider.getSize().getWidth();
		builder.clickAndHold(Opacityslider)
				.dragAndDropBy(driver.findElement(By.className("slider-selection")), ((width * -15) / 100), 0)
				.release();
		builder.build().perform();

	}

	public void deletetheElement() {

		List<WebElement> el = driver
				.findElements(By.xpath("//div[@class='tooltip-inner']//span[@class='svg svg-delete']"));

		for (int i = 0; i < el.size(); i++) {
			if (el.get(i).isDisplayed()) {
				el.get(i).click();
			}
		}
	}

	public void deleteElementAfterPhpError() {
		phpErrorPopupOKButton.click();

		// driver.navigate().refresh();
		pageSettingCancelLink.click();
		driver.navigate().refresh();

		StandAloneTableSelectSection.click();

	}

	public void deleteTextAreaAfterPhpError() {
		phpErrorPopupOKButton.click();

		pageSettingCancelLink.click();

		driver.navigate().refresh();

	}

	public void videoDragandDrop() throws InterruptedException
	{
		Thread.sleep(3000);
		VideoselectSection.click();
		//Actions action = new Actions(driver).doubleClick(VideoselectSection);
		Thread.sleep(3000);
		videoPageSetUp();
        
        WebElement master = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[3]/div[2]/div[4]/div[2]"));
        videoIcon.click();
        Thread.sleep(3000);
        builder.moveToElement(master).doubleClick().perform();
			
	}

	public void insertShape() throws InterruptedException {
		ShapeselectSection.click();
		Thread.sleep(3000);
		System.out.println("starting delete");
		shapesPageSetUp();
		System.out.println("after delete");
		Thread.sleep(3000);
		WebElement master = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[4]/div[2]/div[4]/div[contains(@class,'master')]"));
		shapeIcon.click();
		Thread.sleep(3000);
		builder.moveToElement(master).click().perform();
		
	}
	public void shapeDragandDrop() throws InterruptedException {
		ShapeselectSection.click();
		Thread.sleep(3000);
		shapesPageSetUp();
		List<WebElement> pages = driver.findElements(By.className("section-page"));

		int pageCount = pages.size();
		for (int i = 0; i < pageCount; i++) {
			if (pages.get(i).isDisplayed()) {
				pages.get(i).click();
				break;
			}
		}

		Thread.sleep(5000);
		// List<WebElement> el =
		// driver.findElements(By.xpath("//div[@class='section-group']"));

		List<WebElement> el = driver.findElements(By.xpath(
				".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[4]//div[@class='canvas selected loaded']//div[@class='elements']//div[contains(@class,'master')]"));

		int count = el.size();
		for (int i = 0; i < count; i++) {
			if (el.get(i).isDisplayed()) {
				Thread.sleep(3000);
				shapeIcon.click();

				Thread.sleep(3000);
				builder.moveToElement(el.get(i))
						.dragAndDropBy(el.get(i), el.get(i).getLocation().getX(), el.get(i).getLocation().getY())
						.release().perform();

				WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'stretch draggable selected')]"));
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].setAttribute('style', 'left: 303px; top: 117px; z-index: 505; width: 300px; height: 225px;');",
						eld);

				WebElement canvasClick = driver.findElement(
						By.xpath(".//*[@id='pyCanvasList']//div[6]//div[@class='canvas selected loaded']"));
				builder.click(canvasClick).perform();
				WebElement selectionHolder = driver.findElement(By.xpath(
						".//*[@id='pyCanvasList']//div[6]//div[@class='canvas selected loaded']//div[@class='elements']//div[contains(@class,'element shape')]"));

				builder.moveToElement(selectionHolder).click(selectionHolder).click(selectionHolder).perform();

				break;

			}
		}
	}

	public void clickBorderToggle() {
		elementsBorderToggle.click();

	}

	public boolean videoPageSetUp() {
		try {
			List<WebElement> totalVideoCount = driver.findElements(By.xpath(".//*[contains(@class,'element video')]"));
			System.out.println(totalVideoCount.size());
			int f = totalVideoCount.size();
			for (WebElement tab : totalVideoCount) {
				System.out.println(f);

				if (f != 0) {

					builder.moveToElement(tab).perform();
					builder.click(tab).perform();
					deletetheElement();
					f--;
				}
			}
		} catch (Exception NoSuchElementException) {
			return false;
		}
		return true;

	}

	public boolean shapesPageSetUp() {
		try {
			List<WebElement> totalTableCount = driver.findElements(By.xpath(".//*[contains(@class,'element shape')]"));
			System.out.println(totalTableCount.size());
			for (WebElement tab : totalTableCount) {
				
				if(tab.isDisplayed()) {
				builder.moveToElement(tab).perform();
				builder.click(tab).perform();
				deletetheElement();
				}
			}
		} catch (Exception NoSuchElementException) {
			return false;
		}
		return true;

	}

	public boolean feeTablePageSetUp() {
		try {
			int tblCount = 0;
			List<WebElement> totalTableCount = driver.findElements(By.xpath(".//*[contains(@class,'element table')]"));

			System.out.println(totalTableCount.size() + " Tables Found");
			for (WebElement tab : totalTableCount) {
				tblCount++;
				System.out.println("Deleting Table " + tblCount);

				if (tab.isDisplayed()) {
					builder.moveToElement(tab).perform();
					builder.click(tab).perform();
					deletetheElement();

				}
			}
		} catch (Exception NoSuchElementException) {
			return false;
		}
		return true;

	}

	public boolean PageFlowfeeTablePageSetUp() {
		try {
			List<WebElement> totalTableCount = driver.findElements(By.xpath(".//*[@data-type='pricing']"));
			System.out.println(totalTableCount.size());

			for (WebElement tab : totalTableCount) {
				WebElement tableFoot = tab.findElement(By.tagName("thead"));
				List<WebElement> tfrow = tableFoot.findElements(By.tagName("th"));

				if (tab.isDisplayed()) {
					for (WebElement ft : tfrow) {
						builder.moveToElement(ft).click(ft).click(ft).click(ft).perform();
						builder.moveToElement(deleteTableButton).click(deleteTableButton).perform();
					}

				}
			}
		} catch (Exception NoSuchElementException) {
			return false;
		}
		return true;

	}
	

	public void displayPropertiesPanelforPageFlowTable() {
		WebElement inlineEditor = driver
				.findElement(By.xpath("//*[@id='pyCanvasList']/div[9]/div[2]/div[4]/div/div"));
		builder.moveToElement(inlineEditor).perform();
		builder.click(inlineEditor).perform();
		
		
		WebElement FirstCell = driver.findElement(By.xpath("//*[@id='pyCanvasList']//table/tbody/tr/td[1]"));
		builder.moveToElement(FirstCell).doubleClick().perform();
		builder.moveToElement(FirstCell).doubleClick().perform();
		
//		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
//		WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
//		List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));
//
//		for (WebElement tr : tableRow) {
//			List<WebElement> tabledata = tr.findElements(By.tagName("td"));
//
//			for (WebElement td : tabledata) {
//				builder.doubleClick(td).perform();
//				builder.doubleClick(td).perform();
//
//				break;
//			}
//			break;
//		}

	}

	public void displayPropertiesPanel() {
		WebElement inlineEditor = driver
				.findElement(By.xpath("//*[@id='pyCanvasList']/div[7]/div[2]/div[4]/div[contains(@class,'master')]"));
		//*[@id="pyCanvasList"]/div[5]/div[2]/div[4]/div[3]/div[@class='editor fr-box gray-theme fr-basic fr-top']
		//*[@class='editor fr-box gray-theme fr-basic fr-top']
		builder.moveToElement(inlineEditor).perform();
		builder.click(inlineEditor).perform();
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tablebody = feeTable.findElement(By.tagName("tbody"));
		List<WebElement> tableRow = tablebody.findElements(By.tagName("tr"));

		for (WebElement tr : tableRow) {
			List<WebElement> tabledata = tr.findElements(By.tagName("td"));

			for (WebElement td : tabledata) {
				builder.doubleClick(td).perform();
				builder.click(td).perform();

				break;
			}
			break;

		}
	}

	public void insertTable(String tabletype) throws InterruptedException {

		StandAloneTableSelectSection.click();
		Thread.sleep(3000);
		WebElement el = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[7]/div[2]/div[4]/div[contains(@class,'master')]"));	
		tableIcon.click();
			
		feeTable.click();
		Thread.sleep(3000);
		selectRowType(tabletype);
		WebElement insert = driver.findElement(By.xpath("//*[@id='fee']//button[@type='submit']"));
		builder.moveToElement(insert).click().build().perform();	
		//displayPropertiesPanel();
		//System.out.println("after display propoerties");
		}
	

	public void FeeTableDragandDrop(String typeRow) throws InterruptedException
	{
	
	StandAloneTableSelectSection.click();
	System.out.println("page 4");
	Thread.sleep(3000);
	
	//List<WebElement> pages = driver.findElements(By.className("section-page"));
	
    /*int pageCount = pages.size();
    for(int i=0;i<pageCount;i++)
    {
    	if(pages.get(i).isDisplayed())
    	{
        pages.get(i).click();
    	break;
    	}
    }*/

    Thread.sleep(5000);
	//List<WebElement> el = driver.findElements(By.xpath("//div[@class='section-group']"));
    
    List <WebElement> el =driver.findElements(By.xpath("//*[@id='pyCanvasList']/div[5]/div[2]/div[4]/div[2]"));
	
	int count = el.size();
	
	for(int i = 0;i<count;i++)
	{
		if(el.get(i).isDisplayed())
		{
			feeTablePageSetUp();
			Thread.sleep(3000);
			tableIcon.click();
		int x_Cord =	el.get(i).getLocation().x;
		int y_Cord =	el.get(i).getLocation().y;
		
			Thread.sleep(3000);
			feeTable.click();
			Thread.sleep(3000);
			selectRowType(typeRow);
			builder.moveToElement(el.get(i)).dragAndDropBy(el.get(i),x_Cord,y_Cord).perform();
		
			WebElement eld = driver.findElement(By.xpath("//div[contains(@class,'selection-helper draggable multiple')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 66px; top: 135px; width: 621px; height: 205px;');", eld);

			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 37px; top: 176px; width: 680px; height: 218px;');", eld);

			
			WebElement canvasClick = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[1]/div[2]"));
			builder.click(canvasClick).perform();
			
			WebElement frElement = driver.findElement(By.xpath(".//*[contains(@class,'element table')]//div[contains(@class,'fr-element')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('class', 'fr-element fr-view fr-disabled');", frElement);
			
			
			WebElement selectionHolder = driver.findElement(By.xpath(".//*[contains(@class,'element table')]"));
			builder.moveToElement(selectionHolder).click(selectionHolder).perform();
			builder.moveToElement(selectionHolder).click(selectionHolder).perform();
			
			
			
			System.out.println(
					"table dropped");
			displayPropertiesPanel();
			
		
			break;
			
		}
	}
	}

	public void selectRowType(String rowType) {
		WebElement selRT = driver.findElement(By.cssSelector("input[name='table_format-typeahead']"));
		System.out.println("table type list");
		selRT.click();

		WebElement tableRowType = driver.findElement(
				By.xpath(".//*[@class='twitter-typeahead']//div[@class='tt-dataset tt-dataset-all']//div[@data-value='"
						+ rowType + "']//span[@class='role-label']"));
		tableRowType.click();
		System.out.println("tabel type selected");

	}
	
	public void addRowPageFlow() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTableRow = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tablebody = feeTableRow.findElements(By.tagName("tbody"));
		for (WebElement row : tablebody) {
			int flg = 0;
			List<WebElement> tableRow = row.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				flg++;
				if (flg == tableRow.size()) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (int i = 0; i < tabledata.size(); i++) {
						if (i == 3) {
							
							builder.doubleClick(tabledata.get(i)).perform();
							WebElement addRow = tabledata.get(i).findElement(
									By.xpath("//*[contains(@id,'tableMenuRowAdd')]/span[@class='svg svg-add']"));		
							//JavascriptExecutor js = (JavascriptExecutor) driver;
							//js.executeScript("window.scrollBy(0,-200)");

							builder.moveToElement(addRow).click().perform();
							break;

						}
					}

				}

			}

		}
	}

	public void addRowContentTable() throws InterruptedException {
		Thread.sleep(3000);
		WebElement contentTableRow = driver.findElement(By.cssSelector("table[data-type='content']"));
		List<WebElement> tablebody = contentTableRow.findElements(By.tagName("tbody"));
		for (WebElement row : tablebody) {
			int flg = 0;
			List<WebElement> tableRow = row.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				flg++;
				if (flg == tableRow.size()) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (int i = 0; i < tabledata.size(); i++) {
						if (i == 2) {
							builder.doubleClick(tabledata.get(i)).perform();
							//builder.doubleClick(tabledata.get(i)).perform();
							System.out.println("after double click" + tabledata.size());
							WebElement addRow = driver.findElement(
									By.xpath("//*[@data-cmd='tableMenuRowAdd']/span[@class='svg svg-add']"));
							Thread.sleep(3000);
							//builder.sendKeys(Keys.TAB).click().perform();
							builder.moveToElement(addRow).click().perform();
							break;

						}
					}

				}

			}

			//displayPropertiesPanel();

		}
	}


	public void addColumnContentTable() throws InterruptedException {
		Thread.sleep(3000);
		WebElement contentTableRow = driver.findElement(By.cssSelector("table[data-type='content']"));
		List<WebElement> tablebody = contentTableRow.findElements(By.tagName("tbody"));
		for (WebElement row : tablebody) {
			int flg = 0;
			List<WebElement> tableRow = row.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				flg++;
				if (flg == tableRow.size()) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (int i = 0; i < tabledata.size(); i++) {
						if (i == 2) {
							builder.doubleClick(tabledata.get(i)).perform();
							//builder.doubleClick(tabledata.get(i)).perform();
							System.out.println("after double click" + tabledata.size());
							WebElement addColoumn = driver.findElement(
									By.xpath("//*[@data-cmd='tableMenuColumnAdd']/span[@class='svg svg-add']"));
							Thread.sleep(3000);
							//builder.sendKeys(Keys.TAB).click().perform();
							builder.moveToElement(addColoumn).click().perform();
							break;

						}
					}

				}

			}

			//displayPropertiesPanel();

		}
	}
	
	
	
	public void deleteRowContentTable() throws InterruptedException {
		Thread.sleep(3000);
		WebElement ContentTableRow = driver.findElement(By.cssSelector("table[data-type='content']"));
		List<WebElement> tablebody = ContentTableRow.findElements(By.tagName("tbody"));
		for (WebElement row : tablebody) {
			int flg = 0;
			List<WebElement> tableRow = row.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				flg++;
				if (flg == tableRow.size()) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (int i = 0; i < tabledata.size(); i++) {
						if (i == 2) {
							builder.doubleClick(tabledata.get(i)).perform();
							//builder.doubleClick(tabledata.get(i)).perform();
							System.out.println("after double click" + tabledata.size());
							WebElement addRow = driver.findElement(
									By.xpath("//*[@data-cmd='tableMenuRowRemove']/span[@class='svg svg-delete']"));
							Thread.sleep(3000);
							//builder.sendKeys(Keys.TAB).click().perform();
							builder.moveToElement(addRow).click().perform();
							break;

						}
					}

				}

			}

			//displayPropertiesPanel();

		}
	}
	
	public void deleteColumnContentTable() throws InterruptedException {
		Thread.sleep(3000);
		WebElement ContentTableRow = driver.findElement(By.cssSelector("table[data-type='content']"));
		List<WebElement> tablebody = ContentTableRow.findElements(By.tagName("tbody"));
		for (WebElement row : tablebody) {
			int flg = 0;
			List<WebElement> tableRow = row.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				flg++;
				if (flg == tableRow.size()) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (int i = 0; i < tabledata.size(); i++) {
						if (i == 2) {
							builder.doubleClick(tabledata.get(i)).perform();
							//builder.doubleClick(tabledata.get(i)).perform();
							System.out.println("after double click" + tabledata.size());
							WebElement addRow = driver.findElement(
									By.xpath("//*[@data-cmd='tableMenuColumnRemove']/span[@class='svg svg-delete']"));
							Thread.sleep(3000);
							builder.moveToElement(addRow).click().perform();
							break;

						}
					}

				}

			}

			//displayPropertiesPanel();

		}
	}
	
	public void addRowFeeTable() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTableRow = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tablebody = feeTableRow.findElements(By.tagName("tbody"));
		for (WebElement row : tablebody) {
			int flg = 0;
			List<WebElement> tableRow = row.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				flg++;
				if (flg == tableRow.size()) {
					List<WebElement> tabledata = tr.findElements(By.tagName("td"));

					for (int i = 0; i < tabledata.size(); i++) {
						if (i == 3) {
							builder.doubleClick(tabledata.get(i)).perform();
							WebElement addRow = tabledata.get(i).findElement(
									By.xpath("//*[contains(@id,'tableMenuRowAdd')]/span[@class='svg svg-add']"));
							builder.moveToElement(addRow).click().perform();
							break;

						}
					}

				}

			}

			displayPropertiesPanel();

		}
	}

	public void deleteRow() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTableRow = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tablebody = feeTableRow.findElements(By.tagName("tbody"));
		for (WebElement row : tablebody) {
			List<WebElement> tableRow = row.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));

				for (int i = 0; i < tabledata.size(); i++) {
					if (i == 3) {
						builder.doubleClick(tabledata.get(i)).perform();
						WebElement addRow = tabledata.get(i).findElement(
								By.xpath("//*[contains(@id,'tableMenuRowRemove')]/span[@class='svg svg-delete']"));
						builder.moveToElement(addRow).click().perform();
						break;

					}

				}

			}

			WebElement addRow = driver
					.findElement(By.xpath("//div[@class='rowmenu']//span[@class='svg svg-delete delete-row']"));

			builder.moveToElement(addRow).click().perform();

		}
	}

	public double price = 0;
	public int priceint = 0;
	public double qty = 0;
	public double totalAmount = 0;
	public double total_Amount2 = 0;
	public double totalTaxAmount = 0;
	public double totalTaxPercent = 0;
	public double tamt = 0;
	public double taxAmt = 0;
	public double discAmt = 0;
	public double totalDiscPercent = 0;
	public double totalDiscAmount = 0;
	public double dtotalAmount = 0;
	public double checkboxPrice = 0;
	public double checkboxQty = 0;
	public double checkboxTotal = 0;
	public double checkboxTotalAmt = 0;
	List<WebElement> cb;

	public void fillTableRows() throws InterruptedException {
		//Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tablebody = feeTable.findElements(By.tagName("tbody"));
		WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
		for (WebElement tb : tablebody) {
			List<WebElement> tableRow = tb.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));
				int flag = 0;
				for (WebElement td : tabledata) {
					builder.doubleClick(td).doubleClick(td).perform();
					flag++;
					if (flag == 1) {
						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));
						cb = tr.findElements(By.xpath(
								"//td[@class='fr-selected-cell']//span[@style='display: inline;']//input[@type='checkbox'][@data-checked='true']"));

						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).click(selcell).sendKeys("New Fee 1").perform();
						}

					}

					if (flag == 2) {
						// move.doubleClick(td).perform();

						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));
						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).click().sendKeys("100").perform();
							priceint = Integer.parseInt(selcell.getText());
							System.out.println("int price " + priceint);
							price = Double.parseDouble(selcell.getText());

							if (cb.size() > 0) {
								checkboxPrice = Double.parseDouble(selcell.getText());

							}

							else {
								checkboxPrice = 0;
							}

						}

					}

					if (flag == 3) {

						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));

						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).click().sendKeys("100").perform();
							qty = Double.parseDouble(selcell.getText());

							if (cb.size() > 0) {
								checkboxQty = Double.parseDouble(selcell.getText());

							}

							else {
								checkboxQty = 0;
							}

						}

						//Thread.sleep(3000);
						tableFoot.click();

					}

				}

				total_Amount2 = price * qty;
				checkboxTotal = checkboxPrice * checkboxQty;

				tamt = tamt + total_Amount2;
				checkboxTotalAmt = checkboxTotalAmt + checkboxTotal;

			}

		}
		displayPropertiesPanel();

	}

	
	public void fillPageFlowfeeTableRows() throws InterruptedException {
		//Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tablebody = feeTable.findElements(By.tagName("tbody"));
		WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
		for (WebElement tb : tablebody) {
			List<WebElement> tableRow = tb.findElements(By.tagName("tr"));
			for (WebElement tr : tableRow) {
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));
				int flag = 0;
				for (WebElement td : tabledata) {
					builder.doubleClick(td).doubleClick(td).perform();
					flag++;
					if (flag == 1) {
						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));
						cb = tr.findElements(By.xpath(
								"//td[@class='fr-selected-cell']//span[@style='display: inline;']//input[@type='checkbox'][@data-checked='true']"));

						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).click(selcell).sendKeys("New Fee 1").perform();
						}

					}

					if (flag == 2) {
						// move.doubleClick(td).perform();

						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));
						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).click().sendKeys("100").perform();
							priceint = Integer.parseInt(selcell.getText());
							System.out.println("int price " + priceint);
							price = Double.parseDouble(selcell.getText());

							if (cb.size() > 0) {
								checkboxPrice = Double.parseDouble(selcell.getText());

							}

							else {
								checkboxPrice = 0;
							}

						}

					}

					if (flag == 3) {

						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));

						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).click().sendKeys("100").perform();
							qty = Double.parseDouble(selcell.getText());

							if (cb.size() > 0) {
								checkboxQty = Double.parseDouble(selcell.getText());

							}

							else {
								checkboxQty = 0;
							}

						}

						//Thread.sleep(3000);
						tableFoot.click();

					}

				}

				total_Amount2 = price * qty;
				checkboxTotal = checkboxPrice * checkboxQty;

				tamt = tamt + total_Amount2;
				checkboxTotalAmt = checkboxTotalAmt + checkboxTotal;

			}

		}
		displayPropertiesPanelforPageFlowTable();

	}

	
	// WebElement feeTable =
	// driver.findElement(By.cssSelector("table[data-type='pricing']"));

	public void taxAmountCaluclation() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

		List<WebElement> txRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='tax']"));
		// System.out.println(txRow.size());

		for (int i = 0; i < txRow.size(); i++) {

			String tPercent = txRow.get(i).getAttribute("data-rate-value");

			taxAmt = Integer.parseInt(tPercent);

			totalTaxPercent = taxAmt + totalTaxPercent;

		}

		totalTaxAmount = (totalTaxPercent * tamt) / 100;
		// System.out.println(totalTaxAmount);

		totalAmount = tamt + totalTaxAmount;

		// System.out.println(totalAmount);

	}

	public void taxAmountCaluclationPageFlow() throws InterruptedException {
		Thread.sleep(3000);
//
//		List<WebElement> tableID_New = driver.findElements(
//				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[contains(@class,'canvas')]"));
//		List<WebElement> totalTble = driver.findElements(By.xpath(
//				"//*[@id='pyCanvasList']//table"));
//		System.out.println(tableID_New.size());
//		int tableDataID_Updated = 0;
//		int id_flag = 0;
//		for (WebElement tid : tableID_New) {
//			id_flag++;
//			if (id_flag == totalTble.size()) {
//				tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));
//				break;
//			}
//		}
//		
//		System.out.println("total amount and Tax cal...");
//
//		Thread.sleep(3000);
//		WebElement FinalPageFeeTable1 = driver
//				.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
//						+ tableDataID_Updated + "'"+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
//		
		WebElement FinalPageFeeTable1 = driver
				.findElement(By.xpath("//*[@id='pyCanvasList']//table/tfoot"));
		
		//WebElement tableFoot = FinalPageFeeTable1.findElement(By.tagName("tfoot"));

		List<WebElement> txRow = FinalPageFeeTable1.findElements(By.cssSelector("tr[data-rate-type='tax']"));
		// System.out.println(txRow.size());

		for (int i = 0; i < txRow.size(); i++) {

			String tPercent = txRow.get(i).getAttribute("data-rate-value");

			taxAmt = Integer.parseInt(tPercent);

			totalTaxPercent = taxAmt + totalTaxPercent;

		}

		totalTaxAmount = (totalTaxPercent * lastRowtamt) / 100;
		
		totalAmount = lastRowtamt + totalTaxAmount;

	}

	public void discountAmountCaluclation() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

		List<WebElement> txRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='discount']"));
		// System.out.println(txRow.size());

		for (int i = 0; i < txRow.size(); i++) {

			String tPercent = txRow.get(i).getAttribute("data-rate-value");

			discAmt = Integer.parseInt(tPercent);

			totalDiscPercent = discAmt + totalDiscPercent;

		}

		totalDiscAmount = (totalDiscPercent * tamt) / 100;
		dtotalAmount = tamt - totalDiscAmount;

		System.out.println("total discount"+totalDiscAmount);
		System.out.println("after discount total " + dtotalAmount);

	}

	// return totalTaxAmount;

	public void discountAmountCaluclationPageFlow() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[contains(@class,'canvas')]"));

		List<WebElement> totalTble = driver.findElements(By.xpath(
				".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));

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
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));

		List<WebElement> txRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='discount']"));
		// System.out.println(txRow.size());

		for (int i = 0; i < txRow.size(); i++) {

			String tPercent = txRow.get(i).getAttribute("data-rate-value");

			discAmt = Integer.parseInt(tPercent);

			totalDiscPercent = discAmt + totalDiscPercent;

		}

		totalDiscAmount = (totalDiscPercent * lastRowtamt) / 100;
		// System.out.println(totalTaxAmount);

		dtotalAmount = lastRowtamt - totalDiscAmount;

		// System.out.println(totalAmount);

	}

	public void changeShowTotalToggleOFF() throws InterruptedException {
		WebElement showTotaltoggle = driver.findElement(By.xpath(
				"//*[@id='pyPricingTableProperties']//input[@name='show_total']"));
		builder.moveToElement(showTotaltoggle).click().perform();
		/*
		 * WebElement elm =
		 * driver.findElement(By.xpath("//div[@class='toggle btn btn-primary round']"));
		 * List<WebElement> el = elm.findElements(By.tagName("div"));
		 * 
		 * for(WebElement stEl : el) { Thread.sleep(3000);
		 * 
		 * List<WebElement> el2 = stEl.findElements(By.tagName("label"));
		 * 
		 * for(WebElement st:el2) { builder.moveToElement(st).click().perform(); break;
		 * } break; }
		 */
	}

	public void changeShowTotalToggleON() throws InterruptedException {

		WebElement showTotaltoggle = driver.findElement(By.xpath(
				".//*[@id='pyPricingTableProperties']//div[@class='row'][4]//div[@class='col-xs-12']//div[@class='checkbox-inline']"
						+ "//label[@class='toggle btn toggle-sm']//span[@class='labels']//span[@class='off']"));
		builder.moveToElement(showTotaltoggle).click().perform();

		// WebElement elm =
		// driver.findElement(By.cssSelector("input[name='show_total']"));

		/*
		 * List<WebElement> elm = driver.findElements(By.
		 * xpath("//div[@class='toggle btn btn-default off round']")); int flag2 = 0;
		 * System.out.println(elm.size()); for(WebElement toggle: elm) { flag2++;
		 * 
		 * if(flag2==4) {
		 * 
		 * List<WebElement> el = toggle.findElements(By.tagName("div")); int flag=0;
		 * 
		 * 
		 * System.out.println(el.size()); for(WebElement stEl : el) {
		 * Thread.sleep(3000);
		 * 
		 * List<WebElement> el2 = stEl.findElements(By.
		 * xpath("//label[@class='btn btn-default active toggle-off']"));
		 * System.out.println(el2.size()); for(WebElement st:el2) { flag++;
		 * 
		 * if(flag==4) {
		 * 
		 * Thread.sleep(3000); builder.moveToElement(st).click().perform();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * } } }
		 */

	}

	public void addTax() {
		driver.findElement(By.xpath(".//*[@class='btn btn-default btn-block btn-xs addtax']")).click();
	}

	public void fillTaxeswithParameter(String tax, String percentage) throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tableFoot = feeTable.findElements(By.tagName("tfoot"));
		// List<WebElement> tablefootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {

				int i = 2;
				WebElement taxName = footTD.findElement(
						By.xpath("//*[@id='pyCanvasList']//table/tfoot/tr[ '"+i+"']/td[1]/form//input[@placeholder='Tax']"));
				taxName.sendKeys(tax);

				WebElement taxPercent = footTD.findElement(
						By.xpath("//*[@id='pyCanvasList']//table/tfoot/tr[ '"+i+"']/td[1]/form//input[@placeholder='0%']"));
				taxPercent.sendKeys(percentage);

				WebElement taxUpdateButton = footTD.findElement(
						By.xpath("//*[@id='pyCanvasList']//table/tfoot/tr['"+i+"']/td[1]//button[@type='submit']"));
				taxUpdateButton.click();
				i = i++;

			}
		}

	}

	public void fillTaxes() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tableFoot = feeTable.findElements(By.tagName("tfoot"));
		// List<WebElement> tablefootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for(WebElement footTD :txRow)
	        {
	         
           			 
	        	WebElement taxName =  footTD.findElement(By.cssSelector("input[placeholder='Tax']"));
	    		   taxName.sendKeys("GST");
	    		   
	    		   WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
	    		   taxPercent.sendKeys("5");
	    		   
	    		   WebElement taxUpdateButton = footTD.findElement(By.xpath(".//*[@class='submit btn btn-xs btn-success']"));
	    		   taxUpdateButton.click();
    
	        }
		}

	}
	
	public void fillTaxesPageFlowTablewithPaameter(String tax,String percentage) throws InterruptedException {
		Thread.sleep(3000);
//		List<WebElement> tableID_New = driver.findElements(
//				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[contains(@class,'canvas')]"));
//		List<WebElement> totalTble = driver.findElements(By.xpath(
//				".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[contains(@class,'canvas')]//div[@class='elements']//table[@data-type='pricing']"));
//		System.out.println(tableID_New.size());
//		int tableDataID_Updated = 0;
//		int id_flag = 0;
//		for (WebElement tid : tableID_New) {
//			id_flag++;
//			if (id_flag == totalTble.size()) {
//				tableDataID_Updated = Integer.parseInt(tid.getAttribute("data-id"));
//				break;
//			}
//		}
//
//		System.out.println(tableDataID_Updated);
//
//		
//		System.out.println("Tax cal...");
//
//		Thread.sleep(3000);
//		WebElement FinalPageFeeTable1 = driver
//			.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
//						+ tableDataID_Updated + "'"
//						+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
		
		WebElement FinalPageFeeTable1 = driver
				.findElement(By.xpath("//*[@id='pyCanvasList']//table"));
		
		List<WebElement> tableFoot = driver.findElements(By.tagName("tfoot"));
		System.out.println("table footer size"+tableFoot.size());
		
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {
				
				
				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Tax']"));
				taxName.sendKeys(tax);

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys(percentage);

				WebElement taxUpdateButton = footTD
						.findElement(By.xpath(".//*[@type='submit']"));
				builder.moveToElement(taxUpdateButton).click().build().perform();
			

			}
		}

	}

	public void fillTaxesPageFlowTable() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
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

		
		System.out.println("Tax cal...");

		Thread.sleep(3000);
		WebElement FinalPageFeeTable1 = driver
			.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
						+ tableDataID_Updated + "'"
						+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
		
		List<WebElement> tableFoot = FinalPageFeeTable1.findElements(By.tagName("tfoot"));
		
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {
				
				
				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Tax']"));
				taxName.sendKeys("GST");

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys("5");

				WebElement taxUpdateButton = footTD
						.findElement(By.xpath(".//*[@class='submit btn btn-xs btn-success']"));
				taxUpdateButton.click();
			

			}
		}

	}

	public void addDiscount() {
		driver.findElement(By.xpath(".//*[@class='btn btn-default btn-block btn-xs adddiscount']")).click();
	}

	public void fillDiscountsofPageFlowTable() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
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

		Thread.sleep(3000);
		WebElement FinalPageFeeTable1 = driver
				.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
						+ tableDataID_Updated + "'"
						+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
		List<WebElement> tableFoot = FinalPageFeeTable1.findElements(By.tagName("tfoot"));
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {

				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Discount']"));
				taxName.sendKeys("Discount");

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys("5");

				WebElement taxUpdateButton = footTD
						.findElement(By.xpath(".//*[@class='submit btn btn-xs btn-success']"));
				taxUpdateButton.click();

			}
		}

	}
	
	public void fillDiscountsPageFlowTablewithPaameter(String tax,String percentage) throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
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
			.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
						+ tableDataID_Updated + "'"
						+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
		
		List<WebElement> tableFoot = FinalPageFeeTable1.findElements(By.tagName("tfoot"));
		
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {
				
				
				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Discount']"));
				taxName.sendKeys(tax);

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys(percentage);

				WebElement taxUpdateButton = footTD
						.findElement(By.xpath(".//*[@class='submit btn btn-xs btn-success']"));
				taxUpdateButton.click();
			

			}
		}

	}


	public void fillDiscountswithParameter(String Discount, String Percentage) throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tableFoot = feeTable.findElements(By.tagName("tfoot"));
		// List<WebElement> tablefootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {

				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Discount']"));
				taxName.sendKeys(Discount);

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys(Percentage);

				WebElement taxUpdateButton = footTD
						.findElement(By.xpath(".//*[@class='submit btn btn-xs btn-success']"));
				taxUpdateButton.click();

			}
		}

	}

	public void fillDiscounts() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tableFoot = feeTable.findElements(By.tagName("tfoot"));
		// List<WebElement> tablefootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {

				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Discount']"));
				taxName.sendKeys("Discount");

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys("5");

				WebElement taxUpdateButton = footTD
						.findElement(By.xpath(".//*[@class='submit btn btn-xs btn-success']"));
				taxUpdateButton.click();

			}
		}

	}

	public void cancelNewTax() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tableFoot = feeTable.findElements(By.tagName("tfoot"));
		// List<WebElement> tablefootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {

				int i = 2;
				WebElement taxName = footTD.findElement(
						By.xpath("//*[@id=\"pyCanvasList\"]/div[5]/div[2]/div[4]/div[3]/div/div/div[1]/table/tfoot/tr['"
								+ i + "']/td[1]/form/span[1]/input"));
				taxName.sendKeys("GST");

				WebElement taxPercent = footTD.findElement(
						By.xpath("//*[@id=\"pyCanvasList\"]/div[5]/div[2]/div[4]/div[3]/div/div/div[1]/table/tfoot/tr['"
								+ i + "']/td[1]/form/span[2]/input"));
				taxPercent.sendKeys("5");

				WebElement taxCanceleButton = footTD.findElement(By.xpath(
						"//*[@id=\"pyCanvasList\"]/div[5]/div[2]/div[4]/div[3]/div/div/div/table/tfoot/tr[2]/td[1]/form/span[4]/button"));
				taxCanceleButton.click();
				i = i++;

			}
		}
	}

	public void cancelNewTaxPageFlowTable() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
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
				.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
							+ tableDataID_Updated + "'"
							+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
			
			List<WebElement> tableFoot = FinalPageFeeTable1.findElements(By.tagName("tfoot"));
		
		for (WebElement footRow : tableFoot) {

			
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			for (WebElement footTD : txRow) {

				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Tax']"));
				taxName.sendKeys("GST");

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys("5");

				WebElement taxCanceleButton = footTD
						.findElement(By.xpath(".//*[@class='cancel btn-plain text-danger']"));
				taxCanceleButton.click();

			}
		}
	}

	public void cancelNewDiscount() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		List<WebElement> tableFoot = feeTable.findElements(By.tagName("tfoot"));
		// List<WebElement> tablefootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFoot) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {

				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Discount']"));
				taxName.sendKeys("GST");

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys("5");

				WebElement taxCanceleButton = footTD
						.findElement(By.xpath(".//*[@class='cancel btn-plain text-danger']"));
				taxCanceleButton.click();

			}
		}
	}

	public void cancelNewDiscountPageFlowTable() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));

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
		WebElement FinalPageFeeTable1 = driver
				.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
						+ tableDataID_Updated + "'"
						+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
		List<WebElement> tableFoot1 = FinalPageFeeTable1.findElements(By.tagName("tfoot"));
		for (WebElement footRow : tableFoot1) {

			// System.out.println("---");
			List<WebElement> txRow = footRow.findElements(By.cssSelector("tr[class='fr-selected-row']"));

			// System.out.println(txRow.size());
			for (WebElement footTD : txRow) {

				WebElement taxName = footTD.findElement(By.cssSelector("input[placeholder='Discount']"));
				taxName.sendKeys("GST");

				WebElement taxPercent = footTD.findElement(By.cssSelector("input[placeholder='0%']"));
				taxPercent.sendKeys("5");

				WebElement taxCanceleButton = footTD
						.findElement(By.xpath(".//*[@class='cancel btn-plain text-danger']"));
				taxCanceleButton.click();

			}
		}
	}

	public void cancelExistingTax() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
		List<WebElement> tableFootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFootRow) {
			footRow.click();
			break;
		}

		List<WebElement> txRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='tax']"));

		for (WebElement footTD : txRow) {

			builder.moveToElement(footTD).doubleClick().perform();

			WebElement delRow = driver
					.findElement(By.xpath("//div[@class='rowmenu']//span[@class='svg svg-delete delete-row']"));

			builder.moveToElement(delRow).click().perform();
			break;

		}

	}

	public void cancelExistingTaxofPageFlowTable() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
		int tableDataID_Updated = 0;
		for (int i = 0; i < tableID_New.size(); i++) {
			// System.out.println(tableID_New.get(i).getAttribute("data-id"));

			if (i == tableID_New.size() - 1) {
				tableDataID_Updated = Integer.parseInt(tableID_New.get(i).getAttribute("data-id"));
			}

		}
		// System.out.println(tableDataID_Updated);

		Thread.sleep(3000);
		WebElement FinalPageFeeTable1 = driver
				.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
						+ tableDataID_Updated + "'"
						+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
		WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));
		List<WebElement> tableFootRow = tableFoot1.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFootRow) {
			footRow.click();
			break;
		}

		List<WebElement> txRow = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='tax']"));

		for (WebElement footTD : txRow) {

			builder.moveToElement(footTD).doubleClick().perform();

			WebElement delRow = driver
					.findElement(By.xpath("//div[@class='rowmenu']//span[@class='svg svg-delete delete-row']"));

			builder.moveToElement(delRow).click().perform();
			break;

		}

	}

	public void cancelExistingDiscount() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tableFoot = feeTable.findElement(By.tagName("tfoot"));
		List<WebElement> tableFootRow = tableFoot.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFootRow) {
			footRow.click();
			break;
		}
		List<WebElement> disRow = tableFoot.findElements(By.cssSelector("tr[data-rate-type='discount']"));

		for (WebElement footTD : disRow) {

			builder.moveToElement(footTD).doubleClick().perform();

			WebElement delRow = driver
					.findElement(By.xpath("//div[@class='rowmenu']//span[@class='svg svg-delete delete-row']"));

			builder.moveToElement(delRow).click().perform();
			break;

		}
	}

	public void cancelExistingDiscountofPageFlowTable() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tableID_New = driver.findElements(
				By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas')]"));
		int tableDataID_Updated = 0;
		for (int i = 0; i < tableID_New.size(); i++) {
			// System.out.println(tableID_New.get(i).getAttribute("data-id"));

			if (i == tableID_New.size() - 1) {
				tableDataID_Updated = Integer.parseInt(tableID_New.get(i).getAttribute("data-id"));
			}

		}
		// System.out.println(tableDataID_Updated);

		Thread.sleep(3000);
		WebElement FinalPageFeeTable1 = driver
				.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[@data-id=" + "'"
						+ tableDataID_Updated + "'"
						+ "]//div[@class='elements']//div[contains(@class,'selected editing')]//table[@data-type='pricing']"));
		WebElement tableFoot1 = FinalPageFeeTable1.findElement(By.tagName("tfoot"));
		List<WebElement> tableFootRow = tableFoot1.findElements(By.tagName("tr"));
		for (WebElement footRow : tableFootRow) {
			footRow.click();
			break;
		}
		List<WebElement> disRow = tableFoot1.findElements(By.cssSelector("tr[data-rate-type='discount']"));

		for (WebElement footTD : disRow) {

			builder.moveToElement(footTD).doubleClick().perform();

			WebElement delRow = driver
					.findElement(By.xpath("//div[@class='rowmenu']//span[@class='svg svg-delete delete-row']"));

			builder.moveToElement(delRow).click().perform();
			break;

		}
	}
	
	public void LineChangeColor() throws InterruptedException {

		WebElement el = driver.findElement(By.xpath("//*[@id='pyPathProperties']//div[contains(@class,'color')]"));
		builder.moveToElement(el).click(el).perform();
		
		WebElement overlay = driver.findElement(By.xpath("//*[@class='colpick colpick_full']//div[contains(@class,'overlay2')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);
		Thread.sleep(3000);
		
		WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));

		builder.moveToElement(innerlay).doubleClick(innerlay).perform();

		WebElement colorBar1 = driver
				.findElement(By.xpath("//*[@class='colpick colpick_full']/div[2]/div[@class='colpick_hue_arrs']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 61px;');", colorBar1);
//
//		WebElement colorBar = driver
//				.findElement(By.xpath("//*[@class='colpick colpick_full']/div[2]//div[@class='colpick_hue_rarr']"));
//		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 106px;');", colorBar);

		builder.moveToElement(colorBar1).release(colorBar1).perform();

		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);

		builder.moveToElement(innerlay).click(innerlay).perform();

		WebElement okColor = driver
				.findElement(By.xpath("//*[@class='colpick colpick_full']/div[@class='colpick_submit']"));
		okColor.click();
	
		
	}
	
	

	public void TextAreaChangeBorderColour() throws InterruptedException {
	
		builder.moveToElement(borderColorChange).click(borderColorChange).perform();
		Thread.sleep(3000);

		WebElement overlay = driver.findElement(By.xpath(
				".//*[@class='colpick colpick_full'][2]//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);
		Thread.sleep(3000);

		WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));

		builder.moveToElement(innerlay).doubleClick(innerlay).perform();

		WebElement colorBar1 = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full'][2]//div[@class='colpick_hue_arrs']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 40px;');", colorBar1);

		WebElement colorBar = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full'][2]//div[@class='colpick_hue_arrs']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 106px;');", colorBar);

		builder.moveToElement(colorBar1).clickAndHold(colorBar1).release(colorBar).perform();

		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);

		builder.moveToElement(innerlay).click(innerlay).perform();

		WebElement okColor = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full']//div[@class='colpick_submit']"));
		okColor.click();
	
	
	}

	
	
	public String shapeBorderColorStyle1 = null;
	public String shapeBorderColorStyle2 = null;
	public Select newShapeBorderType;

	public void shapeChangeBorderColour(String borderTypeStyle) throws InterruptedException {
		builder.doubleClick(borderSize).sendKeys("16").perform();

		builder.moveToElement(borderColorChange).click(borderColorChange).perform();
		Thread.sleep(3000);

		WebElement overlay = driver.findElement(By.xpath(
				".//*[@class='colpick colpick_full'][2]//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);
		Thread.sleep(3000);

		WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));

		builder.moveToElement(innerlay).doubleClick(innerlay).perform();

		WebElement colorBar1 = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full'][2]//div[@class='colpick_hue_arrs']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 40px;');", colorBar1);

		WebElement colorBar = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full'][2]//div[@class='colpick_hue_arrs']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 106px;');", colorBar);

		builder.moveToElement(colorBar1).clickAndHold(colorBar1).release(colorBar).perform();

		Thread.sleep(3000);
		// WebElement overlay
		// =driver.findElement(By.xpath(".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);

		Thread.sleep(3000);
		// WebElement innerlay
		// =overlay.findElement(By.className("colpick_selector_inner"));
		builder.moveToElement(innerlay).click(innerlay).perform();

		WebElement okColor = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full'][2]//div[@class='colpick_submit']"));
		okColor.click();

		WebElement colorPick = driver.findElement(By.xpath(".//*[@class='colpick colpick_full'][2]"));
		WebElement newColour = colorPick.findElement(By.className("colpick_new_color"));

		shapeBorderColorStyle1 = newColour.getAttribute("style");

		System.out.println(shapeBorderColorStyle1);

		// newShapeBorderType = new Select(borderStyle);

		borderStyle.click();
		WebElement styleType = driver.findElement(
				By.xpath(".//*[@class='twitter-typeahead']//div[@class='tt-dataset tt-dataset-all']//div[@data-value='"
						+ borderTypeStyle + "']//span[@class='role-label']"));
		styleType.click();

	}

	public String videoBorderColorStyle1 = null;
	public String videoBorderColorStyle2 = null;
	public Select newBorderType;

	public void videoChangeBorderColour(String borderTypeStyle) throws InterruptedException {
		builder.doubleClick(borderSize).sendKeys("10").perform();

		builder.moveToElement(rowColorChange).click(rowColorChange).perform();
		Thread.sleep(3000);

		WebElement overlay = driver.findElement(By.xpath(
				".//*[@class='colpick colpick_full']//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);
		Thread.sleep(3000);

		WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));

		builder.moveToElement(innerlay).doubleClick(innerlay).perform();

		WebElement colorBar1 = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full']//div[@class='colpick_hue_arrs']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 40px;');", colorBar1);

		WebElement colorBar = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full']//div[@class='colpick_hue_arrs']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 106px;');", colorBar);

		builder.moveToElement(colorBar1).clickAndHold(colorBar1).release(colorBar).perform();

		Thread.sleep(3000);
		// WebElement overlay
		// =driver.findElement(By.xpath(".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');",
				overlay);

		Thread.sleep(3000);
		// WebElement innerlay
		// =overlay.findElement(By.className("colpick_selector_inner"));
		builder.moveToElement(innerlay).click(innerlay).perform();

		WebElement okColor = driver
				.findElement(By.xpath(".//*[@class='colpick colpick_full']//div[@class='colpick_submit']"));
		okColor.click();

		WebElement colorPick = driver.findElement(By.xpath(".//*[@class='colpick colpick_full']"));
		WebElement newColour = colorPick.findElement(By.className("colpick_new_color"));

		videoBorderColorStyle1 = newColour.getAttribute("style");

		System.out.println(videoBorderColorStyle1);

		borderStyle.click();
		WebElement styleType = driver.findElement(
				By.xpath(".//*[@class='twitter-typeahead']//div[@class='tt-dataset tt-dataset-all']//div[@data-value='"
						+ borderTypeStyle + "']"));
		styleType.click();

	}

	public String colorStyle1 = null;
	public String colorStyle2 = null;

	public void changeRowColor() throws InterruptedException {
		Thread.sleep(3000);
		WebElement feeTable = driver.findElement(By.cssSelector("table[data-type='pricing']"));
		WebElement tablebody = feeTable.findElement(By.tagName("tbody"));

		List<WebElement> trow = tablebody.findElements(By.tagName("tr"));
		int flag = 0;
		for (int i = 0; i < trow.size(); i = +2) {
			flag++;
			List<WebElement> tdata = trow.get(i).findElements(By.tagName("td"));
			for (WebElement td : tdata) {
				if (flag == 1) {

					builder.doubleClick(td).perform();
					Thread.sleep(3000);
					builder.moveToElement(rowColorChange).click(rowColorChange).perform();
					Thread.sleep(3000);
					WebElement overlay = driver.findElement(By.xpath(
							".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
					((JavascriptExecutor) driver)
							.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
					Thread.sleep(3000);
					WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));
					builder.click(innerlay).perform();
					WebElement colorBar1 = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 40px;');",
							colorBar1);

					WebElement colorBar = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 106px;');",
							colorBar);

					builder.clickAndHold(colorBar1).release(colorBar).perform();
					Thread.sleep(3000);
					// WebElement overlay
					// =driver.findElement(By.xpath(".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
					((JavascriptExecutor) driver)
							.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
					Thread.sleep(3000);
					// WebElement innerlay
					// =overlay.findElement(By.className("colpick_selector_inner"));
					builder.click(innerlay).perform();
					WebElement okColor = driver.findElement(By.className("colpick_submit"));
					okColor.click();

					WebElement colorPick = driver.findElement(By.xpath(".//*[@class='colpick colpick_full']"));
					WebElement newColour = colorPick.findElement(By.className("colpick_new_color"));

					colorStyle1 = newColour.getAttribute("style");

					System.out.println(colorStyle1);

				}

				if (flag == 2) {

					builder.doubleClick(td).perform();
					Thread.sleep(3000);
					builder.click(td).perform();
					rowColorChange.click();
					Thread.sleep(3000);
					WebElement overlay = driver.findElement(By.xpath(
							".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
					((JavascriptExecutor) driver)
							.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
					Thread.sleep(3000);
					WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));
					builder.click(innerlay).perform();
					WebElement colorBar1 = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 26px;');",
							colorBar1);

					WebElement colorBar = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 60px;');",
							colorBar);

					builder.clickAndHold(colorBar1).release(colorBar).perform();
					Thread.sleep(3000);
					// WebElement overlay
					// =driver.findElement(By.xpath(".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
					((JavascriptExecutor) driver)
							.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
					Thread.sleep(3000);
					// WebElement innerlay
					// =overlay.findElement(By.className("colpick_selector_inner"));
					builder.click(innerlay).perform();
					WebElement okColor = driver.findElement(By.className("colpick_submit"));
					okColor.click();

					WebElement colorPick = driver.findElement(By.xpath(".//*[@class='colpick colpick_full']"));
					WebElement newColour = colorPick.findElement(By.className("colpick_new_color"));

					colorStyle2 = newColour.getAttribute("style");

					System.out.println(colorStyle2);

				}
				break;

			}

			if (flag == 3) {
				break;
			}
		}

	}

	public String rowColor1 = null;
	public String rowColor2 = null;
	public String rowColor3 = null;

	public void changeRowColorPageFlowTable() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> pages = driver.findElements(By.xpath(
				".//*[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse']//li"));
		int flag = 0;
		int pageCount = pages.size();
		for (int k = 0; k < pageCount; k += 2) {
			flag++;
			pages.get(k).click();
			if (flag == 1) {

				WebElement tableID_New = driver.findElement(By.xpath(
						".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[9]//div[contains(@class,'canvas loaded selected')]"));
				int tableDataID_Updated = 0;
				tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
				// System.out.println(tableID_New.get(i).getAttribute("data-id"));
				Thread.sleep(3000);
				WebElement FinalPageFeeTable1 = driver
						.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div//div[@data-id="
								+ "'" + tableDataID_Updated + "'"
								+ "]//div[@class='elements']//div[contains(@class,'master draggable')]//table[@data-type='pricing']"));
				WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
				WebElement tableRow = tableBody.findElement(By.tagName("tr"));

				List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
				System.out.println(tableData.size());
				for (WebElement tdata : tableData) {
					builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();

					break;
				}

				Thread.sleep(3000);
				builder.moveToElement(rowColorChange).click(rowColorChange).perform();
				Thread.sleep(3000);
				WebElement overlay = driver.findElement(By.xpath(
						".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
				((JavascriptExecutor) driver)
						.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
				Thread.sleep(3000);
				WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));
				builder.click(innerlay).perform();
				WebElement colorBar1 = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 40px;');",
						colorBar1);

				WebElement colorBar = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 106px;');",
						colorBar);

				builder.clickAndHold(colorBar1).release(colorBar).perform();
				Thread.sleep(3000);
				// WebElement overlay
				// =driver.findElement(By.xpath(".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
				((JavascriptExecutor) driver)
						.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
				Thread.sleep(3000);
				// WebElement innerlay
				// =overlay.findElement(By.className("colpick_selector_inner"));
				builder.click(innerlay).perform();
				WebElement okColor = driver.findElement(By.className("colpick_submit"));
				okColor.click();

				for (WebElement tdata : tableData) {
					rowColor1 = tdata.getAttribute("style");
					System.out.println(rowColor1);

					break;
				}

				WebElement colorPick = driver.findElement(By.xpath(".//*[@class='colpick colpick_full']"));
				WebElement newColour = colorPick.findElement(By.className("colpick_new_color"));

				colorStyle1 = newColour.getAttribute("style");

				System.out.println(colorStyle1);

			}

			if (flag == 2) {

				Thread.sleep(3000);
				WebElement tableID_New = driver.findElement(By.xpath(
						".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[7]//div[contains(@class,'canvas loaded selected')]"));
				int tableDataID_Updated = 0;
				tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
				// System.out.println(tableID_New.get(i).getAttribute("data-id"));
				Thread.sleep(3000);
				WebElement FinalPageFeeTable1 = driver
						.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[@data-id="
								+ "'" + tableDataID_Updated + "'"
								+ "]//div[@class='elements']//div[contains(@class,'master draggable')]//table[@data-type='pricing']"));
				WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
				WebElement tableRow = tableBody.findElement(By.tagName("tr"));

				List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
				System.out.println(tableData.size());
				for (WebElement tdata : tableData) {
					builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();

					break;
				}
				builder.moveToElement(rowColorChange).click(rowColorChange).perform();
				Thread.sleep(3000);
				WebElement overlay = driver.findElement(By.xpath(
						".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
				((JavascriptExecutor) driver)
						.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
				Thread.sleep(3000);
				WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));
				builder.click(innerlay).perform();
				WebElement colorBar1 = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 26px;');",
						colorBar1);

				WebElement colorBar = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 60px;');",
						colorBar);

				builder.clickAndHold(colorBar1).release(colorBar).perform();
				Thread.sleep(3000);
				// WebElement overlay
				// =driver.findElement(By.xpath(".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
				((JavascriptExecutor) driver)
						.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
				Thread.sleep(3000);
				// WebElement innerlay
				// =overlay.findElement(By.className("colpick_selector_inner"));
				builder.click(innerlay).perform();
				WebElement okColor = driver.findElement(By.className("colpick_submit"));
				okColor.click();
				for (WebElement tdata : tableData) {
					rowColor2 = tdata.getAttribute("style");
					System.out.println(rowColor2);

					break;
				}

				WebElement colorPick = driver.findElement(By.xpath(".//*[@class='colpick colpick_full']"));
				WebElement newColour = colorPick.findElement(By.className("colpick_new_color"));

				colorStyle2 = newColour.getAttribute("style");

				System.out.println(colorStyle2);

			}

			if (flag == 3) {
				Thread.sleep(3000);

				WebElement tableID_New = driver.findElement(By.xpath(
						".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[contains(@class,'canvas loaded selected')]"));
				int tableDataID_Updated = 0;
				tableDataID_Updated = Integer.parseInt(tableID_New.getAttribute("data-id"));
				// System.out.println(tableID_New.get(i).getAttribute("data-id"));
				Thread.sleep(3000);
				WebElement FinalPageFeeTable1 = driver
						.findElement(By.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[@data-id="
								+ "'" + tableDataID_Updated + "'"
								+ "]//div[@class='elements']//div[contains(@class,'master draggable')]//table[@data-type='pricing']"));
				WebElement tableBody = FinalPageFeeTable1.findElement(By.tagName("tbody"));
				WebElement tableRow = tableBody.findElement(By.tagName("tr"));

				List<WebElement> tableData = tableRow.findElements(By.tagName("td"));
				System.out.println(tableData.size());
				for (WebElement tdata : tableData) {
					builder.moveToElement(tdata).doubleClick(tdata).doubleClick(tdata).click().perform();

					break;
				}
				builder.moveToElement(rowColorChange).click(rowColorChange).perform();
				Thread.sleep(3000);
				WebElement overlay = driver.findElement(By.xpath(
						".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
				((JavascriptExecutor) driver)
						.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
				Thread.sleep(3000);
				WebElement innerlay = overlay.findElement(By.className("colpick_selector_inner"));
				builder.click(innerlay).perform();
				WebElement colorBar1 = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 26px;');",
						colorBar1);

				WebElement colorBar = driver.findElement(By.cssSelector("div[class='colpick_hue_arrs']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: 60px;');",
						colorBar);

				builder.clickAndHold(colorBar1).release(colorBar).perform();
				Thread.sleep(3000);
				// WebElement overlay
				// =driver.findElement(By.xpath(".//div[@class='colpick_color']//div[@class='colpick_color_overlay2']//div[@class='colpick_selector_outer']"));
				((JavascriptExecutor) driver)
						.executeScript("arguments[0].setAttribute('style', 'left: 132px; top: 56px;');", overlay);
				Thread.sleep(3000);
				// WebElement innerlay
				// =overlay.findElement(By.className("colpick_selector_inner"));
				builder.click(innerlay).perform();
				WebElement okColor = driver.findElement(By.className("colpick_submit"));
				okColor.click();
				for (WebElement tdata : tableData) {
					rowColor3 = tdata.getAttribute("style");
					System.out.println(rowColor3);

					break;
				}

				WebElement colorPick = driver.findElement(By.xpath(".//*[@class='colpick colpick_full']"));
				WebElement newColour = colorPick.findElement(By.className("colpick_new_color"));

				colorStyle2 = newColour.getAttribute("style");

				System.out.println(colorStyle2);

			}

			if (flag == 4) {
				break;
			}
		}

	}

	public void optionalForClientOptionTurnON() throws InterruptedException {

		WebElement elm = driver.findElement(By.xpath(
				"//*[@id='pyPricingTableProperties']//input[@name='opt_for_clients']"));
		builder.moveToElement(elm).click().build().perform();

	}

	public void optionalForClientOptionOFF() throws InterruptedException {

		WebElement elm = driver.findElement(By.xpath(
				"//*[@id='pyPricingTableProperties']//input[@name='opt_for_clients']"));
		builder.moveToElement(elm).click().build().perform();

	}

	public void editableQuantityOptionTurnON() throws InterruptedException {

		WebElement elm = driver.findElement(By.xpath(
				"//*[@class=\"row row-selection-only\"]//div[4]/div[@class='col-xs-12']/div[@class=\"checkbox-inline\"]//label[@class='toggle btn toggle-sm']//span//span[@class='off']"));
		elm.click();

	}

	public void editableQuantityOptionOptionOFF() throws InterruptedException {

		WebElement elm = driver.findElement(By.xpath(
				".//*[@class='row row-selection-only']//div[4]//div[@class='col-xs-12']//div[@class='checkbox-inline']//div[@class='toggle btn toggle-sm']"
						+ "//span[@class='labels']//span[@class='on']"));
		elm.click();

	}


	public void duplicateTheElement() throws InterruptedException {
		List<WebElement> el = driver
				.findElements(By.xpath("//div[@class='tooltip-inner']//span[@class='svg svg-cog']"));
		for (int i = 0; i < el.size(); i++) {
			if (el.get(i).isDisplayed()) {
				el.get(i).click();

			}
		}
		List<WebElement> el3 = driver.findElements(By.xpath(
				"//ul[contains(@class,'context-menu-list context-menu-root')]//li[2]//span[text()='Duplicate']"));
		int flag = 0;
		System.out.println("Size " + el3.size());
		for (int k = 0; k < el3.size(); k++) {

			if (flag == 1) {

				builder.moveToElement(el3.get(k)).perform();
				Thread.sleep(3000);
				builder.click(el3.get(k)).perform();

			}
			flag++;
		}
	}

	// public Select selNewCurrency;
	// public String currencyNameCopy;
	public void changeFeeCurrency(String currencyName) {
		System.out.println("currencyName" + currencyName);
		while (!tablePropoertiesPane.isDisplayed()) {
			builder.moveToElement(driver.findElement(By.xpath(".//*[contains(@class,'element table')]"))).perform();
			builder.click(driver.findElement(By.xpath(".//*[contains(@class,'element table')]"))).perform();

		}
		changeCurrency.click();
		WebElement countryCurrency = driver.findElement(
				By.xpath(".//*[@class='twitter-typeahead']//div[@class='tt-dataset tt-dataset-all']//div[@data-value='"
						+ currencyName + "']//span[@class='role-label']"));

		countryCurrency.click();

		// selNewCurrency.selectByVisibleText(currencyNameCopy);
		// WebElement = driver.findElement(By.)
	}

	public void deleteTable() {
		deleteTableButton.click();
	}

	public void insertPageFlowFeeTable(String rowType) throws InterruptedException {
		
		PageFlowFeeTableSelectSection.click();
		Thread.sleep(3000);
		//PageFlowfeeTablePageSetUp();
		System.out.println("page flow fee table setup");
		WebElement master = driver.findElement(By.xpath("//*[@id='pyCanvasList']/div[9]/div[2]/div[4]/div[2]/div/div/div"));
		builder.moveToElement(master).doubleClick().build().perform();
		builder.moveToElement(master).doubleClick().build().perform();
		Thread.sleep(3000);
		System.out.println("done");
		tableIcon.click();
		feeTable.click();
		selectRowType(rowType); // [contains(@class,'draggable resizable rotatable selected')]"

		insertTable.click();

	}

	public void fillPageFlowTableRows() throws InterruptedException {
		Thread.sleep(3000);
		// List<WebElement> sectionPage =
		// driver.findElements(By.xpath(".//*[@id='pyPageList']//ul[@class='section-list
		// sortable']//li[@class='selected']//ul[@class='page-list
		// collapse']//li[@class='selected']"));

		List<WebElement> pages = driver.findElements(By.className("section-page"));

		int pageCount = pages.size();
		for (int i = 0; i < pageCount; i++) {
			if (pages.get(i).isDisplayed()) {
				pages.get(i).click();
				break;
			}
		}

		/*WebElement feeTable = driver.findElement(By
				.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[@class='canvas selected loaded']"
						+ "//div[@class='elements']//div[contains(@class,'selected editing')]"
						+ "//div[@class='editor fr-box fr-inline']//div[@class='fr-wrapper']//div[@class='fr-element fr-view']//table[@data-type='pricing']"));*/
		
		WebElement feeTable = driver.findElement(By
				.xpath("//*[@id=\"pyCanvasList\"]/div[7]/div[2]/div[4]/div[2]/div/div/div/table"));
		
		
		List<WebElement> tablebody = feeTable.findElements(By.tagName("tbody"));

		for (WebElement tb : tablebody) {

			List<WebElement> tableRow = tb.findElements(By.tagName("tr"));

			for (WebElement tr : tableRow) {
				//List<WebElement> sectionPages = driver.findElements(By.xpath(
				//		".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse']//li[@class='selected']"));

				//int pageCounts = sectionPages.size();
				for (int i = 0; i < pageCount;) {
					if (i == pageCount - 1) {
						pages.get(i).click();

					}
					break;
				}
				List<WebElement> tabledata = tr.findElements(By.tagName("td"));
				int flag = 0;

				for (WebElement td : tabledata) {
					builder.doubleClick(td).perform();

					flag++;
					if (flag == 1) {

						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));
						cb = tr.findElements(By.xpath(
								"//td[@class='fr-selected-cell']//span[@style='display: inline;']//input[@type='checkbox'][@data-checked='true']"));

						for (WebElement selcell : selectedTD) {

							builder.moveToElement(selcell).click()
									.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit."
											+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.")
									.perform();

						}

					}

					if (flag == 2) {
						// move.doubleClick(td).perform();

						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));

						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).doubleClick().click().sendKeys("100").perform();
							price = Double.parseDouble(selcell.getText());

							if (cb.size() > 0) {
								checkboxPrice = Double.parseDouble(selcell.getText());

							}

							else {
								checkboxPrice = 0;
							}

						}

					}

					if (flag == 3) {

						List<WebElement> selectedTD = tr.findElements(By.cssSelector("td[class='fr-selected-cell']"));

						for (WebElement selcell : selectedTD) {
							builder.moveToElement(selcell).click().sendKeys("100").perform();
							qty = Double.parseDouble(selcell.getText());

							if (cb.size() > 0) {
								checkboxQty = Double.parseDouble(selcell.getText());

							}

							else {
								checkboxQty = 0;
							}

						}

						Thread.sleep(3000);
						// WebElement canvas =
						// driver.findElement(By.xpath(".//*[@class='section-group']//div[@class='canvas
						// can-select loaded']//div[contains(@class,'master draggable resizable
						// rotatable')]//div[@class='editor fr-box fr-inline']"));
						// builder.click(canvas).perform();

					}

				}

				total_Amount2 = price * qty;
				checkboxTotal = checkboxPrice * checkboxQty;

				tamt = tamt + total_Amount2;
				checkboxTotalAmt = checkboxTotalAmt + checkboxTotal;

			}

		}
	}

	public double lastRowTotalAmount = 0;
	public double lastRowtamt = 0;
	public double lastRowCheckAmount = 0;
	public double lastRowCheckTotalAmt = 0;

	public void fillPageFlowTableRows1() throws InterruptedException {
		Thread.sleep(3000);
		
		/*WebElement feeTable = driver.findElement(By
				.xpath(".//*[@id='pyCanvasPane']//div[@id='pyCanvasList']//div[8]//div[@class='canvas selected loaded']"
						+ "//div[@class='elements']//div[contains(@class,'selected editing')]"
						+ "//div[@class='editor fr-box gray-theme fr-basic fr-top']//div[@class='fr-wrapper']//div[@class='fr-element fr-view']//table[@data-type='pricing']"));*/
		
		WebElement feeTable = driver.findElement(By
				.xpath("//*[@id='pyCanvasList']/div[9]/div[2]/div[4]/div[2]/div/div/div/table"));
		
		List<WebElement> tablebody = feeTable.findElements(By.tagName("tbody"));
		//WebElement tfooter = feeTable.findElement(By.tagName("tfoot"));

		for (WebElement tb : tablebody) {
			/*
			 * List<WebElement> sectionPages = driver.findElements(By.
			 * xpath(".//*[@id='pyPageList']//ul[@class='section-list sortable']//li[@class='selected']//ul[@class='page-list collapse']//li[@class='selected']"
			 * ));
			 * 
			 * int pageCounts = sectionPages.size(); for(int i=0;i<pageCounts;i++) {
			 * if(i==pageCounts-1) { sectionPages.get(i).click();
			 * 
			 * 
			 * 
			 * } break; }
			 */

			List<WebElement> tableRow = tb.findElements(By.tagName("tr"));

			for (int i = 0; i < tableRow.size(); i++) {
				if (i != tableRow.size() - 1) {

					List<WebElement> tabledata = tableRow.get(i).findElements(By.tagName("td"));
					int flag = 0;

					for (WebElement td : tabledata) {

						builder.doubleClick(td).doubleClick().click(td).perform();

						flag++;
						if (flag == 1) {

							List<WebElement> selectedTD = tableRow.get(i)
									.findElements(By.cssSelector("td[class='fr-selected-cell']"));
							cb = tableRow.get(i).findElements(By.xpath(
									"//td[@class='fr-selected-cell']//span[@style='display: inline;']//input[@type='checkbox'][@data-checked='true']"));

							for (WebElement selcell : selectedTD) {
								
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.")
										.perform();
								Thread.sleep(3000);
								
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("When an unknown printer took a galley of type and scrambled it to make a type specimen book")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("It has survived not only five centuries,but also the leap into electronic typesetting")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("Contrary to popular belief, Lorem Ipsum is not simply random text.")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia,")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage,")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("and going through the cites of the word in classical literature, discovered the undoubtable source.")
										.perform();
								Thread.sleep(3000);
								builder.moveToElement(selcell).click(selcell)
										.sendKeys("Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum")
										.perform();
								Thread.sleep(3000);

								/*
								 * builder.moveToElement(selcell).click().
								 * sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
								 * "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n"
								 * ).sendKeys(Keys.ENTER).perform();
								 */
							}

						}

						if (flag == 2) {
							// move.doubleClick(td).perform();

							List<WebElement> selectedTD = tableRow.get(i)
									.findElements(By.cssSelector("td[class='fr-selected-cell']"));

							for (WebElement selcell : selectedTD) {
								builder.moveToElement(selcell).doubleClick().click().sendKeys("100").perform();
								price = Double.parseDouble(selcell.getText());

								if (cb.size() > 0) {
									checkboxPrice = Double.parseDouble(selcell.getText());

								}

								else {
									checkboxPrice = 0;
								}

							}

						}

						if (flag == 3) {

							List<WebElement> selectedTD = tableRow.get(i)
									.findElements(By.cssSelector("td[class='fr-selected-cell']"));

							for (WebElement selcell : selectedTD) {
								builder.moveToElement(selcell).click().sendKeys("100").perform();
								qty = Double.parseDouble(selcell.getText());

								if (cb.size() > 0) {
									checkboxQty = Double.parseDouble(selcell.getText());

								}

								else {
									checkboxQty = 0;
								}

							}

							Thread.sleep(3000);
							// WebElement canvas =
							// driver.findElement(By.xpath(".//*[@class='section-group']//div[@class='canvas
							// can-select loaded']//div[contains(@class,'master draggable resizable
							// rotatable')]//div[@class='editor fr-box fr-inline']"));
							// builder.click(canvas).perform();

						}
					}

					total_Amount2 = price * qty;
					checkboxTotal = checkboxPrice * checkboxQty;

					tamt = tamt + total_Amount2;
					checkboxTotalAmt = checkboxTotalAmt + checkboxTotal;
				}

				if (i == tableRow.size() - 1) {
					List<WebElement> tabledata = tableRow.get(i).findElements(By.tagName("td"));
					int flag = 0;

					for (WebElement td : tabledata) {
						builder.doubleClick(td).doubleClick().click(td).perform();

						flag++;
						if (flag == 1) {

							List<WebElement> selectedTD = tableRow.get(i)
									.findElements(By.cssSelector("td[class='fr-selected-cell']"));
							cb = tableRow.get(i).findElements(By.xpath(
									"//td[@class='fr-selected-cell']//span[@style='display: inline;']//input[@type='checkbox'][@data-checked='true']"));

							for (WebElement selcell : selectedTD) {

								builder.moveToElement(selcell).click()
										.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n"
												+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n"
												+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n"
												+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n"
												+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n")
										.perform();
							}

						}

						if (flag == 2) {
							// move.doubleClick(td).perform();

							List<WebElement> selectedTD = tableRow.get(i)
									.findElements(By.cssSelector("td[class='fr-selected-cell']"));

							for (WebElement selcell : selectedTD) {
								builder.moveToElement(selcell).doubleClick().click().sendKeys("100").perform();
								price = Double.parseDouble(selcell.getText());

								if (cb.size() > 0) {
									checkboxPrice = Double.parseDouble(selcell.getText());

								}

								else {
									checkboxPrice = 0;
								}

							}

						}

						if (flag == 3) {

							List<WebElement> selectedTD = tableRow.get(i)
									.findElements(By.cssSelector("td[class='fr-selected-cell']"));

							for (WebElement selcell : selectedTD) {
								builder.moveToElement(selcell).click().sendKeys("100").perform();
								qty = Double.parseDouble(selcell.getText());

								if (cb.size() > 0) {
									checkboxQty = Double.parseDouble(selcell.getText());

								}

								else {
									checkboxQty = 0;
								}

							}

							Thread.sleep(3000);
							// WebElement canvas =
							// driver.findElement(By.xpath(".//*[@class='section-group']//div[@class='canvas
							// can-select loaded']//div[contains(@class,'master draggable resizable
							// rotatable')]//div[@class='editor fr-box fr-inline']"));
							// builder.click(canvas).perform();

						}
					}

					lastRowTotalAmount = price * qty;
					lastRowCheckAmount = checkboxPrice * checkboxQty;

					lastRowtamt = tamt + lastRowTotalAmount;
					lastRowCheckTotalAmt = checkboxTotalAmt + checkboxTotal;
					System.out.println("Beofre final row " + checkboxTotalAmt);
					displayPropertiesPanel();
				}

			}

			break;

		}

	}

	
	public static String getTextFromTextFile() throws IOException {
	      //FilePath
	    // String sFilePath = "C:\\Users\\Janani\\Desktop\\test.txt";
	      //Creating FileReader object
	      //FileReader fr = null;
	      //Creating BufferedReader object
	      File file = new File("C:\\Users\\Janani\\Desktop\\test.txt");
	      
	      BufferedReader br = new BufferedReader(new FileReader(file));
	     
	      String st;
	      String text;
	      while ((st = br.readLine()) != null)
	        System.out.println(st);
	      text = st;

	      System.out.println("Before return"+text);
	        return text;
	       }
	
}
