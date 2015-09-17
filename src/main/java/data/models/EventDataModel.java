package data.models;

/**
 * Created with IntelliJ IDEA.
 * User: sergiuchuckmisha
 * Date: 9/16/15
 * Time: 8:31 PM
 * purpose of the class is to describe model of Event business entity
 * it contains Title string, is whole day marker and Calendar single selection
 */
public class EventDataModel {
	private String title;
	private Boolean isWholeDay;
	private CalendarTypes calendarType;

	public enum CalendarTypes
	{
		Home("Home"), Work("Work"), School("School");

		public final String label;

		CalendarTypes(String str) {
			label =  str;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsWholeDay() {
		return isWholeDay;
	}

	public void setIsWholeDay(Boolean wholeDay) {
		isWholeDay = wholeDay;
	}

	public CalendarTypes getCalendarType() {
		return calendarType;
	}

	public void setCalendarType(CalendarTypes calendarType) {
		this.calendarType = calendarType;
	}
}
