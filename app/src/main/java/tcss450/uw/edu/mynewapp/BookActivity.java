/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tcss450.uw.edu.mynewapp.model.BookContent;
/**
 * The BookActivity holds the BookListFragment which is used
 * to display the list of books for sale.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class BookActivity extends AppCompatActivity implements BookListFragment.OnListFragmentInteractionListener{

    /**
     * This method is started when the activity is created.
     *
     * @param savedInstanceState is the given bundle holding the saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BookListFragment bookListFragment = new BookListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, bookListFragment)
                .commit();

    }

    /**
     * This method is used to communiate with the itemDetailFragment.
     *
     * @param item is the given item.
     */
    public void onListFragmentInteraction(BookContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        itemDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();

    }

}
