/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import tcss450.uw.edu.mynewapp.model.ItemContent;
/**
 * The BookActivity holds the BookListFragment which is used
 * to display the list of books for sale.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class BookActivity extends AppCompatActivity implements BookListFragment.OnBookListFragmentInteractionListener {

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
                .add(R.id.fragment_book_container, bookListFragment)
                .commit();

    }

    /**
     * This method is used to communiate with the itemdDetailFragment.
     *
     * @param item is the given item.
     */
    public void onBookListFragmentInteraction(ItemContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        args.putInt("ID", item.getItemID());
        args.putString("Category", item.getItemCategory());
        args.putString("Prefx", "Book");
        itemDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_book_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();

    }

}
