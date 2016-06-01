package tcss450.uw.edu.mynewapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tcss450.uw.edu.mynewapp.model.ItemContent;

public class CellPhoneActivity extends AppCompatActivity implements CellPhoneListFragment.OnCellPhoneListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CellPhoneListFragment cellPhoneListFragment = new CellPhoneListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_cellPhone_container, cellPhoneListFragment)
                .commit();

    }

    /**
     * This method is used to communiate with the itemDetailFragment.
     *
     * @param item is the given item.
     */
    public void onCellPhoneListFragmentInteraction(ItemContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        args.putInt("ID", item.getItemID());
        args.putString("Category", item.getItemCategory());
        itemDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_cellPhone_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();
    }


}
