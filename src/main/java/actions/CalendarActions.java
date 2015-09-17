package actions;

import config.Config;
import pageObjects.CalendarPage;
import selenium.utils.DriverHelper;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sergiuchuckmisha
 * Date: 9/16/15
 * Time: 3:11 PM
 * purpose of this class is to describe business logic actions that can be performed on Calender page
 */
public class CalendarActions extends ActionsBase<CalendarPage> {

	/**purpose of the constructor is to initialize page field*/
	public CalendarActions(Date workingDate)
	{
		super();
		page = new CalendarPage(workingDate);//in this case we
	}

	/**default constructor may be required*/
	public CalendarActions()
	{
		new CalendarActions(new Date());
	}


	@Override
	public void navigateTo() {
		if(!page.isOnPage())
		DriverHelper.navigateToCertainUrl(Config.requiredUrl);
	}

	public CalendarActions clickOnTodayDay()
	{
		page.clickOnTheDay();
		return this;
	}

	/**purpose of the method is to collect and return data about all events of today
	 * need to consider case when there are lot of events and some of them are collapsed and expand message like "+9more" is observed
	 * unfortunately I can not get logic of Calendar application, and can't find reliable locators*/
	public Collection<String> getAllEventsOfTheTodayDay() {
		//check is "+n more..." is observed
		if (page.areSomeEventsOfTheDayAreHidden())
		{
			return page.getAllEventsOfTheDayExpandRequiredCase();
		}
		else
		{
			return page.getAllVisibleEvents();//todo return only events for the day
		}
	}

	/**check is "+n more..." is observed for today*/
	public Boolean areSomeEventsOfTheDayAreHidden()
	{
		return page.areSomeEventsOfTheDayAreHidden();
	}

	//todo click on custom day. will require moving by months/years

//	public Collection<String>  getAllEventsOfTheDay


}
