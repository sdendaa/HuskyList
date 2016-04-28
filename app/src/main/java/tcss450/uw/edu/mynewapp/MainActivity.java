package tcss450.uw.edu.mynewapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements CategoryListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        if (findViewById(R.id.fragment_container)!= null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new CategoryListFragment())
                    .commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(int position) {
//        // Capture the student fragment from the activity layout
//        CourseItemFragment courseItemFragment = (CourseItemFragment)
//                getSupportFragmentManager().findFragmentById(R.id.course_item_frag);
//
//        if (courseItemFragment != null) {
//            // If courseItem frag is available, we're in two-pane layout...
//
//            // Call a method in the student fragment to update its content
//            courseItemFragment.updateCourseItemView(position);
//
//        } else {
//            // If the frag is not available, we're in the one-pane layout and must swap frags...
//
//            // Create fragment and give it an argument for the selected student
//            // Replace whatever is in the fragment_container view with this fragment,
//            // and add the transaction to the back stack so the user can navigate back
//
//            courseItemFragment = new CourseItemFragment();
//            Bundle args = new Bundle();
//            args.putInt(CourseItemFragment.ARG_POSITION, position);
//            courseItemFragment.setArguments(args);
//            FragmentTransaction transaction = getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, courseItemFragment)
//                    .addToBackStack(null);
//
//            // Commit the transaction
//            transaction.commit();
//
//        }
    }

}
