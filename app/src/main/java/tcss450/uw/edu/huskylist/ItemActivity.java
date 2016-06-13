package tcss450.uw.edu.huskylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import tcss450.uw.edu.huskylist.model.ItemContent;
public class ItemActivity extends AppCompatActivity implements ItemListFragment.OnItemListFragmentInteractionListener{
    private String mCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Intent intent = getIntent();
//        mCategory = intent.getStringExtra("category");
        Bundle args = getIntent().getExtras();
        mCategory = args.getString("category");
        System.out.println("Category test: " + mCategory);
        ItemListFragment itemListFragment = new ItemListFragment();
        itemListFragment.setCategory(mCategory);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_item_container, itemListFragment)
                .commit();
    }
    public void setCategory(String category) {
        mCategory = category;
    }
    /**
     * This method is used to communiate with the itemDetailFragment.
     *
     * @param item is the given item.
     */
    public void OnItemListFragmentInteractionListener(ItemContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        args.putInt("ID", item.getItemID());

        args.putString("category", item.getItemCategory());
        args.putString("userName", item.getSellerUserName());
        itemDetailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_item_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    public void onStart() {
        super.onStart();


    }
}

