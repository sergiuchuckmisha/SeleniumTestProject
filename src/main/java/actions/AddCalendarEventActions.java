package actions;

import data.models.EventDataModel;
import pageObjects.AddCalendarEventPage;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: sergiuchuckmisha
 * Date: 9/16/15
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddCalendarEventActions  extends ActionsBase<AddCalendarEventPage> {

	/**purpose of the constructor is to initialize page field*/
	public AddCalendarEventActions()
	{
		super();
		page = new AddCalendarEventPage();
	}

	/**in order to get to add event page we need:
	 * 1st: get CalendarPage
	 * 2nd: click on some day*/
	@Override
	public void navigateTo() {
		if(!page.isOnPage())
		{
			(Logger.getLogger(AddCalendarEventActions.class.toString())).severe("lighting-rod code is executed. Attention is required");
			(new CalendarActions()).navigateTo(CalendarActions.class).clickOnTodayDay(); //in fact this line of code is not expected to be executed
		}
		if(!page.isOnPage())
		{throw new IllegalStateException("Add Calendar Event page is not observed. Smth is wrong.");}
	}

	public void addEvent(EventDataModel eventDataModel)
	{
		page.fillTitle(eventDataModel.getTitle());
		if(eventDataModel.getIsWholeDay())
		{page.clickAllDayCheckBox();}
		page.selectCalendarType(eventDataModel.getCalendarType());
		page.pressSave();
	}
}
