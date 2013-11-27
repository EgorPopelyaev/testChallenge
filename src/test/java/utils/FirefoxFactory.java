package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxFactory {

	WebDriver driver;
	
	public WebDriver createFirefoxDriver() {
		driver = new FirefoxDriver();
		return driver;
	}
	
}
