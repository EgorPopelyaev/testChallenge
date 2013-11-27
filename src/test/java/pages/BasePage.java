package pages;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePage {
	private static final int ELEMENTS_ALLOCATING_TIMEOUT = 10;
	
	protected WebDriver webDriver;

	  public BasePage(WebDriver webDriver) {
	    this.webDriver = webDriver;
	  }


	  public static <P extends BasePage> P initPage(WebDriver driver, Class<P> pageClass) {
	    P page = createNewPageInstance(driver, pageClass);
	    PageFactory.initElements(new AjaxElementLocatorFactory(driver, ELEMENTS_ALLOCATING_TIMEOUT), page);
	    return page;
	  }

	  private static <P extends BasePage> P createNewPageInstance(final WebDriver driver, Class<P> pageClass) {
	    try {
	      return pageClass.getConstructor(WebDriver.class).newInstance(driver);
	    } catch (IllegalArgumentException e) {
	      throw new IllegalArgumentException(e);
	    } catch (SecurityException e) {
	      throw new SecurityException(e);
	    } catch (IllegalAccessException e) {
	      throw new WebDriverException(e);
	    } catch (InvocationTargetException e) {
	      throw new WebDriverException(e);
	    } catch (NoSuchMethodException e) {
	      throw new WebDriverException(e);
	    } catch (InstantiationException e) {
	      throw new WebDriverException(e);
	    }
	  }


}
