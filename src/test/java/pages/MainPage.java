package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage extends BasePage{
	
	@FindBy(id = "title")
	private WebElement titleInputField;
	
	@FindBy(id = "file")
	private WebElement browseButton;
	
	@FindBy(id = "upload_form_submit")
	private WebElement uploadButton;
	
	@FindBy(id = "progress")
	private WebElement progressBar;
	
	@FindBy(id = "uploads")
	private WebElement uploadsLink;

	private WebDriver driver;
	
	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	 public void  clickOnBrowseButton() {
		 browseButton.click();		 
	 }
	 
	 public void confirmUpload() {
		 uploadButton.click();		 
	 }
	 
	 public void enterTitle(String title) {
		 titleInputField.clear();
		 titleInputField.sendKeys(title);
	 }
	 
	 public void choseFile (String path) {
		 browseButton.sendKeys(path);
	 }
	 
	 public String getStatusText() {
		 return progressBar.getText();
	 }
	 
	 public String getUploadsTitle() {
		 String text = uploadsLink.getText();
		 return text;
	 }
	 
	 public String getTitleFromInputField() {
		 String text = titleInputField.getText();
		 return text;
	 }
	 
	 public void waitTillUploadISFinished(WebDriver driver) {
		 new WebDriverWait(driver, 10).
		 until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@id='progress']/p"), "saved as:"));
	 }
	 
	 public boolean isProgressStatutsDisplayed() {
		 boolean status = progressBar.getAttribute("style").contains("background-color:");
		 return status;
	 }

}
