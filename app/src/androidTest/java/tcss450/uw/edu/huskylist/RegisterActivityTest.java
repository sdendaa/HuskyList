/**
 * HuskyList App
 * Authors: Vladimir Smirnov and Shelema Bekele
 */
package tcss450.uw.edu.huskylist;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.Random;

import tcss450.uw.edu.huskylist.authenticate.RegisterActivity;

/**
 * The test class for the Register activity
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class RegisterActivityTest extends
        ActivityInstrumentationTestCase2<RegisterActivity> {

    private Solo solo;

    /**
     * constructor for the categoryActivity class
     */

    public RegisterActivityTest() {
        super(RegisterActivity.class);
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
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();

    }

    /**
     * this method is used to test if register fragment is started
     */
    public void testRegisterFragmentLoads() {
        boolean textFound = solo.searchText("Enter a valid email");
        assertTrue("Login fragment loaded", textFound);
    }

    /**
     * this method is to test whether the register is working
     */
    public void testRegisterWork() {
        //solo.clickOnView(solo.getView(R.id.action_register));
        solo.searchText("Enter a valid email");
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("user");
        sb.append((random.nextInt(99) + 1));
        sb.append((random.nextInt(99) + 1));
        sb.append("@yahoo.com");
        String userName = sb.toString();
        String password = "password" + (random.nextInt(99) + 1) + (random.nextInt(99) + 1);
        solo.enterText(0, userName);
        solo.enterText(1, password);
        solo.clickOnButton("Register");
        boolean textFound = solo.searchText("Books");
        assertTrue("Registered is successfully", textFound);
    }
}
