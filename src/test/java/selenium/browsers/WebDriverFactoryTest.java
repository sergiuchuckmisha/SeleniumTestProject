package selenium.browsers;

import base.SeleniumBaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static selenium.browsers.WebDriverFactory.browsers;


/**
 * Created with IntelliJ IDEA.
 * User: serhiymy
 * Date: 9/16/15
 * Time: 6:40 PM
 * purpose of the class is to contain unit tests for WebDriverFactory class
 */
public class WebDriverFactoryTest  extends SeleniumBaseTest {

	@Test
	public void testEnumBrowsers()
	{
		try {
			assert browsers.FireFox.equals(browsers.getBrowserByLabel("FF"));
			assert browsers.HtmlUnit.equals(browsers.getBrowserByLabel("HU"));
			assert browsers.Chrome.equals(browsers.getBrowserByLabel("CH"));
			assert browsers.InternetExplorer.equals(browsers.getBrowserByLabel("IE"));
		} catch (Exception e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**purpose of the test is to start driver, open google and check for presence of a search field*/
	@Test
	public void testFireFoxBrowserCreation()
	{
		try {
			Method method = WebDriverFactory.class.getDeclaredMethod("getDriver", browsers.class);
			method.setAccessible(true);
			method.invoke(null,browsers.FireFox);

			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver());
			WebDriverFactory.getDriver().get("http://www.google.com");
			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver().findElement(By.name("q")));

		} catch ( NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	//todo find way to replace following 4 tests which differ only by browser type with one parametrized test

	/**purpose of the test is to start driver, open google and check for presence of a search field*/
	@Test
	public void testChromeBrowserCreation()
	{
		try {
			Method method = WebDriverFactory.class.getDeclaredMethod("getDriver", browsers.class);
			method.setAccessible(true);
			method.invoke(null,browsers.Chrome);

			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver());
			WebDriverFactory.getDriver().get("http://www.google.com");
			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver().findElement(By.name("q")));

		} catch ( NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**purpose of the test is to start driver, open google and check for presence of a search field
	 * todo is ignored because InternetExplorer does not work*/
	@Ignore
	@Test
	public void testIECreation()
	{
		try {
			Method method = WebDriverFactory.class.getDeclaredMethod("getDriver", browsers.class);
			method.setAccessible(true);
			method.invoke(null,browsers.InternetExplorer);

			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver());
			WebDriverFactory.getDriver().get("http://www.google.com");
			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver().findElement(By.name("q")));

		} catch ( NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}


	/**purpose of the test is to start driver, open google and check for presence of a search field
	 * todo is ignored because HtmlUnit does not work*/
	@Ignore
	@Test
	public void testHtmlUnitCreation()
	{
		try {
			Method method = WebDriverFactory.class.getDeclaredMethod("getDriver", browsers.class);
			method.setAccessible(true);
			method.invoke(null,browsers.HtmlUnit);

			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver());
			WebDriverFactory.getDriver().get("http://www.google.com");
			org.junit.Assert.assertNotNull(WebDriverFactory.getDriver().findElement(By.name("q")));

		} catch ( NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}


