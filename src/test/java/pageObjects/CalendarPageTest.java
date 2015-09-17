package pageObjects;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: serhiymy
 * Date: 9/16/15
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarPageTest {

	/**expected result is smth like 20150916 for input Date as 16 Sep 2015
	 * was used:
	 * http://stackoverflow.com/questions/3977925/java-convert-string-eest-included-into-date
	 * http://stackoverflow.com/questions/4770425/how-do-i-invoke-a-private-static-method-using-reflection-java
	 * http://stackoverflow.com/questions/34571/how-to-test-a-class-that-has-private-methods-fields-or-inner-classes
	 * */
	@Test
	public void checkDateIdGeneration(){
		Date date;
		String dateString = "Wed Sep 16 18:00:15 EEST 2015";
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
		try {
			Method method = CalendarPage.class.getDeclaredMethod("getCalendarSpecificDateRepresentation", Date.class);
			method.setAccessible(true);
			date = format.parse(dateString);
//			org.junit.Assert.assertEquals(getCalendarDayIdFromCertainDate(date),"app-calendar-month-ev-day-20150916"); //when test was in class being tested
			org.junit.Assert.assertEquals(method.invoke(null,date),"20150916");
		} catch (ParseException | NoSuchMethodException | IllegalAccessException |InvocationTargetException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
