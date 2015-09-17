package pageObjects;

/**
 * Created with IntelliJ IDEA.
 * User: sergiuchuckmisha
 * Date: 9/16/15
 * Time: 3:37 PM
 * purpose of this class is to describe properties common to all pages such as: isOnPage method
 * it is assumed that class which implements this interface should implement pageObject pattern
 * https://code.google.com/p/selenium/wiki/PageObjects

 */
public abstract class PageBase {
	public abstract boolean isOnPage();
}
