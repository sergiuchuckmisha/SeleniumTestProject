package pageObjects;

import data.models.EventDataModel;
import org.openqa.selenium.By;
import selenium.utils.DriverHelper;

/**
 * Created with IntelliJ IDEA.
 * User: serhiymy
 * Date: 9/16/15
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddCalendarEventPage implements iPage  {

	private static By pageTitleLocator = By.xpath(".//div[contains(@id,'eventeditwindow')]//div[contains(@id,'title') and text() = 'Add Event']");

	private static By titleLocator = By.xpath(".//div[contains(@id,'eventeditwindow')]//input[contains(@id,'textfield')]");
	private static By allDayCheckBoxLocator = By.xpath(".//div[contains(@id,'eventeditwindow')]//input[contains(@id,'checkbox')]");

	/**arrow to expand selectCalendarType list*/
	private static By calendarSelectorLocator = By.xpath(".//div[contains(@id,'eventeditwindow')]//div[contains(@id,'calendarpicker') and contains(@id,'trigger-picker')]");

	private static By saveButtonLocator = By.xpath(".//div[contains(@id,'eventeditwindow')]//span[contains(@id,'button') and text() = 'Save']");

	public void pressSave()
	{
		DriverHelper.click(saveButtonLocator);
	}

	/**in fact I can't find way to understand is this checkBox selected. So will assume that by default it is unchecked*/
	public void clickAllDayCheckBox()
	{
		DriverHelper.click(allDayCheckBoxLocator);
	}

	public void fillTitle(String str)
	{
		DriverHelper.type(titleLocator, str);
	}

	/**1st: click on arrow
	 * 2nd: click on required calendar type
	 * 3th: try to check result*/
	public void selectCalendarType(EventDataModel.CalendarTypes calendarType)
	{
		DriverHelper.click(calendarSelectorLocator);
		DriverHelper.click(By.xpath(String.format(".//div[@class = 'ext-cal-picker-icon']/../../div[text() = '%s']",calendarType.label)));
		//todo check selected calendar value. using FireBug I can not find selected value
	}

	@Override
	public boolean isOnPage() {
		return DriverHelper.isElementPresent(pageTitleLocator);
	}
}
