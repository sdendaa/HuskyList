package tcss450.uw.edu.mynewapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tcss450.uw.edu.mynewapp.model.ItemContent;

public class VideoGameActivity extends AppCompatActivity implements VideoGameListFragment.OnVideoGameListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        VideoGameListFragment videoGameListFragment = new VideoGameListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_videoGame_container, videoGameListFragment)
                .commit();

    }
    /**
     * This method is used to communiate with the itemDetailFragment.
     *
     * @param item is the given item.
     */
    public void onVideoGameListFragmentInteraction(ItemContent item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ItemDetailFragment.ADS_ITEM_SELECTED, item);
        args.putInt("ID", item.getItemID());
        args.putString("Category", item.getItemCategory());
        itemDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_videoGame_container, itemDetailFragment)
                .addToBackStack(null)
                .commit();
    }


}
