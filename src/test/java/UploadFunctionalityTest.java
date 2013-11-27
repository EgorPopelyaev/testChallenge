
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.BasePage;
import pages.MainPage;

import utils.FirefoxFactory;
import utils.HttpRequestHelper;


public class UploadFunctionalityTest {
	private final static String MAIN_PAGE = "localhost:8081";
	private final static String TITLE = "TestUpload.mp3";
	
	private FirefoxFactory firefoxFactory;
	private WebDriver driver;
	private MainPage mainPage;
	

	@Before
	public void setUp() {
		firefoxFactory = new FirefoxFactory();
		driver = firefoxFactory.createFirefoxDriver();	
		driver.get(MAIN_PAGE);
		
		mainPage = BasePage.initPage(driver, MainPage.class);
	}
	
	
	@After
	public void tearDown() {
		driver.quit();
		mainPage = null;
	}
	
	
	@Test
	public void uploadFile()  {
		File file = getMpFileToUpload();
		
		mainPage.choseFile(file.getAbsolutePath());
		
		mainPage.confirmUpload();
		mainPage.waitTillUploadISFinished(driver); 
		
		Assert.assertTrue("There is no path in status", mainPage.getStatusText().contains("saved as:"));
		Assert.assertTrue("There is now title on page", mainPage.getUploadsTitle().contains("title.mp3"));
	}
	
	@Test
	public void progressBarShouldBeDisplayedWhileUploading() {
		File file = getMpFileToUpload();
		
		mainPage.choseFile(file.getAbsolutePath());		
		mainPage.confirmUpload();
		
		Assert.assertTrue("Progress bar is not displayed", mainPage.isProgressStatutsDisplayed());		
	}
	
	@Test
	public void shoudChangeTitelWhileUploading() {
		File file = getMpFileToUpload();
				
		mainPage.choseFile(file.getAbsolutePath());		
		
		mainPage.confirmUpload();
		
		mainPage.enterTitle(TITLE);
		mainPage.waitTillUploadISFinished(driver); 
		
		Assert.assertTrue("Title was not changed!", mainPage.getUploadsTitle().contains(TITLE));
	}
	
	private File getMpFileToUpload() {
		File file = null;
		try {
			file = new File("/testChallenge/src/test/resources/Kalimba.mp3");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return file;
	}
}
