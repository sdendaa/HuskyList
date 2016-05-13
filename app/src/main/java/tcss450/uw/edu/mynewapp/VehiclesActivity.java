package tcss450.uw.edu.mynewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import tcss450.uw.edu.mynewapp.model.ItemContent;

public class VehiclesActivity extends AppCompatActivity implements VehiclesListFragment.OnVehiclesListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        VehiclesListFragment vehiclesListFragment = new VehiclesListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_vehicles_container, vehiclesListFragment)
                .commit();
    }

    /**
     * This method is used to communiate with the itemDetailFragment.
     *
     * @param item is the given item.
     */
    public void onVehiclesListFragmentInteraction(ItemContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        itemDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_vehicles_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();
    }

}
