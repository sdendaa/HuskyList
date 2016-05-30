package tcss450.uw.edu.mynewapp;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;


/**
 * Created by tola on 5/30/16.
 */
public class BookActivityTest extends
        ActivityInstrumentationTestCase2<CategoryActivity> {

    private Solo solo;

    public BookActivityTest() {
        super(CategoryActivity.class);
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

//    public void testBookList() {
//        boolean fragmentLoaded = solo.searchText("Lists of Books");
//        assertTrue("Book List fragment loaded", fragmentLoaded);
//    }
//
//    public void testBookDetail() {
//        solo.clickInRecyclerView(0);
//        boolean foundBookDetail = solo.searchText("data base");
//        assertTrue("Book Detail fragment loaded", foundBookDetail);
//        solo.goBack();
//        boolean foundBookList = solo.searchText("Lists of Books");
//        assertTrue("Back to List works!", foundBookList);
//    }

    public void testAddWorks() {
        solo.clickOnView(solo.getView(R.id.create_newAds_button));
        boolean textFound = solo.searchText("Create New Ads");
        assertTrue("Add a Book fragment loaded", textFound);
    }

    public void testBookAddButton() {
        solo.clickOnView(solo.getView(R.id.create_newAds_button));
        solo.enterText(0, "I am a book title");
        solo.enterText(1, "98.98");
        solo.enterText(2, "used");
        solo.enterText(3, "I am a book description");
        solo.enterText(4, "UWT is seller location");
        solo.enterText(5, "emailme@gmail.com");
        solo.clickOnView(solo.getView(R.id.add_ads_button));
        boolean textFound = solo.searchText("Create New Ads");
        assertTrue("Book add success", textFound);
        solo.goBack();
        solo.clickOnView(solo.getView(R.id.action_logout));
        boolean textFound1 = solo.searchText("Enter your userid");
        assertTrue("Login fragment loaded", textFound1);
    }

//    public void testLogout() {
//        solo.clickOnButton("LOGOUT");
//        boolean textFound = solo.searchText("Enter your userid");
//        assertTrue("Login fragment loaded", textFound);
//    }
}
