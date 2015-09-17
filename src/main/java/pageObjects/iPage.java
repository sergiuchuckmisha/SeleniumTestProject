package pageObjects;

/**
 * Created with IntelliJ IDEA.
 * User: serhiymy
 * Date: 9/16/15
 * Time: 4:53 PM
 * purpose of this interface is to mark PageObject pattern
 * contains one static method: isOnPage()
 * generally speaking, I do not see any reasons for PageObject to has non-static fields/methods
 */
public interface iPage {
	public boolean isOnPage();
}
