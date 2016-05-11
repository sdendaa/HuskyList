package tcss450.uw.edu.mynewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import tcss450.uw.edu.mynewapp.model.ItemContent;

public class ComputerActivity extends AppCompatActivity implements ComputersListFragment.OnComputerListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ComputersListFragment computerListFragment = new ComputersListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_computer_container, computerListFragment)
                .commit();
    }
    /**
     * This method is used to communiate with the itemDetailFragment.
     *
     * @param item is the given item.
     */
    public void onComputerListFragmentInteraction(ItemContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        itemDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_computer_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();
    }

}
