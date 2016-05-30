package tcss450.uw.edu.mynewapp;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

import tcss450.uw.edu.mynewapp.Authenticate.SignInActivity;

/**
 * Created by tola on 5/30/16.
 */
public class SignInActivityTest extends ActivityInstrumentationTestCase2<SignInActivity> {

    private Solo solo;

    public SignInActivityTest() {
        super(SignInActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();

    }

    public void testSignInFragmentLoads() {
        boolean textFound = solo.searchText("Enter your userid");
        assertTrue("Login fragment loaded", textFound);
    }

    public void testSignInWorks() {
        solo.enterText(0, "userid@yahoo.com");
        solo.enterText(1, "somepassword");
        solo.clickOnButton("SIGN IN");
        boolean worked = solo.searchText("HuskyList");
        assertTrue("Sign in worked!", worked);
        solo.clickOnView(solo.getView(R.id.action_logout));
        boolean textFound = solo.searchText("Enter your userid");
        assertTrue("Login fragment loaded", textFound);
    }

}
