# SeleniumTestProject
test task for interview in Luxoft
I was asked to perform a test task in process of technical interview in Luxoft company
Here is a task description:

Given:
http://dev.sencha.com/extjs/5.1.0/examples/calendar/index.html
Public ExtJS Calendar application (let's call it SUT)

Expected:
You need to implement application, that will:
1. Opens SUT in browser (HtmlUnit is preferred, but you're free to use any)
2. Creates a wholeday event with title 'Lectures' for calendar 'School'
3. Checks that this event appeared in the proper day cell

I'm reading the book "Code complete" cc2e.com
I tried to perform this task trying to controll complexity as much as possible.
Subdivide areas of responsibility.

project has following modules:
 - WebDriverFactory to create webDriver instances.
 - WebDriverHelpers to wrap work with basic WebDriver commands
 - Config in order to save some general properties like path to drivers
 - PageObjects to incapsulate structure of webPage. As input parameters takse simple data types like Boolean, String, enum, Date
 - Actions instances can work with business entities like "Event"
 - DataModels contain decomposition of business entities into simple data types
 - I tried to add some Unit tests
 
 I tried to make these modules do not intersect
 
 to run you need to setup environment variable "browser" to one of following values: CH, FF, IE, HU (Chrome, firefox, explorer and htmlUnit)
 only Chrome and Firefox work for me. Will investigate IE and CH more.
 
 To run project you may need Chrome, IE, FireFox drivers binaries.
 You can find them in these links:
https://yadi.sk/d/br9Lq7EGj99MZ for Chrome and IE drivers
https://yadi.sk/d/bxS8MeMuj99Pb for FireFox 
 and these archives should be unpacked to folder "C:/dev/programs" 
 or you can change pathes in Config class
 
 Feedbacks are very much appreciated
