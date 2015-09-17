package pageObjects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.browsers.WebDriverFactory;
import selenium.utils.DriverHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sergiuchuckmisha
 * Date: 9/16/15
 * Time: 3:03 PM
 * purpose of the class is to describe html elements on page
 * http://dev.sencha.com/extjs/5.1.0/examples/calendar/index.html
 * pageObject pattern is implemented
 */
public class CalendarPage implements iPage {
//	public class CalendarPage extends PageBase {

	/**this is a Date with which class works: adds Event and looks for added events*/
	private Date workingDate;

	public CalendarPage(Date date) {
		this.workingDate = date;
	}

	//smth like id="app-calendar-month-ev-day-20150916" is expected
	private By workingDayLocator = By.id(String.format("app-calendar-month-ev-day-%s", getCalendarSpecificDateRepresentation(workingDate))); //in case of multi-thread run of this framework this field can be different for different threads because depends on Date, so, field should be non-static
	private static By pageTitleLocator = By.xpath(".//*[@id='app-header-content']/h1");

	/**
	 * expected result is smth like 20150916 for input Date as 16 Sep 2015
	 * this method looks useful because this date representation  occurs in lot of elements like existing event of this day, more events of this day and so on
	 * was used
	 * http://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-calen
	 * http://stackoverflow.com/questions/34571/how-to-test-a-class-that-has-private-methods-fields-or-inner-classes
	 * http://stackoverflow.com/questions/4770425/how-do-i-invoke-a-private-static-method-using-reflection-java
	 */
	private static String getCalendarSpecificDateRepresentation(Date date) {

		if (null == date)//todo this crutch is required because field workingDayLocator (which depends on workingDate) is being initialised prior to constructor execution (and workingDate is initialised in constructor). So, npe occurs.
		{
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return String.valueOf(year) + (month > 9 ? String.valueOf(month) : "0" + String.valueOf(month)) + (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day));
	}

	@Override
	public boolean isOnPage() {
		return DriverHelper.isElementPresent(pageTitleLocator) && "Ext JS Calendar".equals(DriverHelper.findElement(pageTitleLocator).getText());
	}

	public void clickOnTheDay() {
		DriverHelper.click(workingDayLocator);
	}

	/**check is "+n more..." is observed for today, and if yes, expands*/
	public Boolean areSomeEventsOfTheDayAreHidden()
	{
		DriverHelper.waitUntilPageIsLoaded();
		Collection<WebElement> collectionOfMoreElementsForTheDay = WebDriverFactory.getDriver().findElements(By.xpath(String.format(".//*[@id='ext-cal-ev-more-%s']/a",getCalendarSpecificDateRepresentation(workingDate))));
		for(WebElement element: collectionOfMoreElementsForTheDay)
		{
			if(element.isDisplayed())
			{
				element.click();
				DriverHelper.waitUntilPageIsLoaded();
				return true;//looks like that one such element can be unhidden
			}
		}
		return false;//if all elements are not shown, then we conclude that all events can be seen
	}

	/**this method is applicable when initially the day has lot of events which are collapsed and can be visible after expand them with link like "+n more..."
	 * see method areSomeEventsOfTheDayAreHidden()
	 * looks like that xPath ".//div[@id = 'app-calendar-month-details-panel']//tr/td/div" works in this case*/
	public Collection<String> getAllEventsOfTheDayExpandRequiredCase() {
		Collection<WebElement> collectionOfEventElementsForTheDay = WebDriverFactory.getDriver().findElements(By.xpath(".//div[@id = 'app-calendar-month-details-panel']//tr/td/div"));
		Collection<String> result = new ArrayList<String>();
		for(WebElement element: collectionOfEventElementsForTheDay)
		{result.add(element.getText());}
		return result;
	}

	/**this method is applicable when initially the day has few events which are NOT collapsed. link like "+n more..." is absent
	 * of course, this method can not guarantee that event was added to proper day. Atm I can not find way to check this.
	 * see method areSomeEventsOfTheDayAreHidden()
	 * looks like that xPath ".//div[contains(@id,'app-calendar-month-evt-')]" is applicable in this case
	 * */
	public Collection<String> getAllVisibleEvents() {
		Collection<WebElement> collectionOfVisibleEventElementsForTheMonth = WebDriverFactory.getDriver().findElements(By.xpath(".//div[contains(@id,'app-calendar-month-evt-')]"));
		Collection<String> result = new ArrayList<String>();
		for(WebElement element: collectionOfVisibleEventElementsForTheMonth)
		{
			if(element.isDisplayed() && StringUtils.isNotEmpty(element.getText()))
			{
				result.add(element.getText());
			}
		}
		return result;  //To change body of created methods use File | Settings | File Templates.
	}
}
