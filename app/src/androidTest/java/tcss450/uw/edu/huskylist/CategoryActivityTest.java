

/**
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.huskylist;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * The test class for the category activiry
 * an item add.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class CategoryActivityTest extends ActivityInstrumentationTestCase2<CategoryActivity> {
    private Solo solo;
/**
 * constructor for the categoryActivity class
 */
    public CategoryActivityTest(){
        super(CategoryActivity.class);
    }
    /**
     *the setup method which run before each test case
     *
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }
    /**
     * the teardown method run after every test case has finished
     *
     */
    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        solo.finishOpenedActivities();

    }

    /**
     * This method is test whether the category activity is started
     */
    public void testCateroryTextView(){
        boolean textFound = solo.searchText("Books");
        assertTrue("main fragment  loaded", textFound);
    }
    /**
     *this method test if click on Book textView is working or not
     */

    public void testBooksTextViewClick(){
        solo.clickOnView(solo.getView(R.id.books));
        boolean textFound = solo.searchText("List of Books");
        assertTrue("books fragment loaded", textFound);
    }
    /**
     *this method test if click on Computer textView is working or not
     */

    public void testComputersTextViewClick(){
        solo.clickOnView(solo.getView(R.id.computers));
        boolean textFound = solo.searchText("List of Computers");
        assertTrue(" Computer fragment loaded", textFound);
    }
    /**
     *This method is used to test if click on register menu is working or not
     */
    public void testRegisterMenu(){
        solo.clickOnView(solo.getView(R.id.action_register));
        boolean textFound = solo.searchText("Enter a valid email");
        assertTrue("register fragment loaded", textFound);
    }

    public void testLoginMenuNotLoggedIN(){
        solo.clickOnView(solo.getView(R.id.action_login));
        if(!solo.getCurrentActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS",
                Context.MODE_PRIVATE).getBoolean("loggedin",false)){
            boolean textFound = solo.searchText("Enter your userid");
            assertTrue("Login fragment loaded", textFound);

        }
    }
    public void testLoginMenuLoggedIn() {
        solo.clickOnView(solo.getView(R.id.action_login));
        if(solo.getCurrentActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS",
                Context.MODE_PRIVATE).getBoolean("loggedin",false)){
            boolean textFound = solo.searchText("Books");
            assertTrue("Login failed", textFound);

        }
    }
    public void testCreatAdMenuLoggedIn(){
        solo.clickOnView(solo.getView(R.id.action_create));
        if(solo.getCurrentActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS",
                Context.MODE_PRIVATE).getBoolean("loggedin",false)){
            boolean textFound = solo.searchText("Choose Category");
            assertTrue("Create fragment loaded", textFound);

        }
    }
    public void testCreatAdMenuNotLoggedIn(){
        solo.clickOnView(solo.getView(R.id.action_create));
        if(!solo.getCurrentActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS",
                Context.MODE_PRIVATE).getBoolean("loggedin",false)){
            boolean textFound = solo.searchText("Books");
            assertTrue("create fragment failed loaded", textFound);

        }
    }
    public void testLogoutMenuNotLoggedIn(){
        solo.clickOnView(solo.getView(R.id.action_logout));
        if(!solo.getCurrentActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS",
                Context.MODE_PRIVATE).getBoolean("loggedin",false)){
            boolean textFound = solo.searchText("Books");
            assertTrue("Logout fragment failed loaded", textFound);

        }
    }

    public void testLogoutMenuLoggedIn(){
        solo.clickOnView(solo.getView(R.id.action_logout));
        if(solo.getCurrentActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS",
                Context.MODE_PRIVATE).getBoolean("loggedin",false)){
            boolean textFound = solo.searchText("Enter your userid");
            assertTrue("Logout fragment loaded", textFound);

        }
    }
}
