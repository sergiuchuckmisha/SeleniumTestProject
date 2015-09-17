package e2e;

import actions.AddCalendarEventActions;
import actions.CalendarActions;
import base.SeleniumBaseTest;
import data.models.EventDataModel;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sergiuchuckmisha
 * Date: 9/16/15
 * Time: 3:01 PM
 * purpose of the class is to contain relatively "long" scenarios like adding some event to the calendar
 */
public class CalendarTest extends SeleniumBaseTest {

	private static final CalendarActions calendarActions = new CalendarActions(new Date());//work with today
	private static final AddCalendarEventActions addCalendarEventActions = new AddCalendarEventActions();
	EventDataModel eventDataModel;

	@Before
	public void initData()
	{
		eventDataModel = new EventDataModel();
		eventDataModel.setTitle("Lectures");
		eventDataModel.setIsWholeDay(true);
		eventDataModel.setCalendarType(EventDataModel.CalendarTypes.School);
	}

	/**
	 *  * purpose of the method is to check following scenario:
	 * 1. Opens SUT in browser (HtmlUnit is preferred, but you're free to use any)
	 * 2. Creates a wholeday event with title 'Lectures' for calendar 'School'
	 * 3. Checks that this event appeared in the proper day cell
	 * */
	@Test
	public void test() {
//		DriverHelper.navigateToCertainUrl(Config.requiredUrl);
//		DriverHelper.waitUntilPageIsLoaded();
		calendarActions.navigateTo(CalendarActions.class).clickOnTodayDay();
		addCalendarEventActions.navigateTo(AddCalendarEventActions.class).addEvent(eventDataModel);

		String allEventsOfTheDay = String.valueOf(calendarActions.navigateTo(CalendarActions.class).getAllEventsOfTheTodayDay());

		System.out.println(allEventsOfTheDay);
		org.junit.Assert.assertTrue(allEventsOfTheDay.contains(eventDataModel.getTitle()));
	}

}
