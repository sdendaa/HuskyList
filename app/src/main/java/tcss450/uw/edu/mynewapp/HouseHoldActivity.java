package tcss450.uw.edu.mynewapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tcss450.uw.edu.mynewapp.model.ItemContent;

public class HouseHoldActivity extends AppCompatActivity implements HouseHoldListFragment.OnHouseHoldListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HouseHoldListFragment houseHoldListFragment = new HouseHoldListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_houseHold_container, houseHoldListFragment)
                .commit();
    }
    /**
     * This method is used to communiate with the itemDetailFragment.
     *
     * @param item is the given item.
     */
    public void onHouseHoldListFragmentInteraction(ItemContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        args.putInt("ID", item.getItemID());
        args.putString("Category", item.getItemCategory());
        itemDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_houseHold_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();
    }

}
