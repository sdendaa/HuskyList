package tcss450.uw.edu.huskylist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import tcss450.uw.edu.huskylist.model.ItemContent;
public class ItemListFragment extends Fragment {
    /** This constant represents the argument column count. */
    private static final String ARG_COLUMN_COUNT = "column-count";
    /** This variable represents the column count. */
    private int mColumnCount = 1;
    /** This variable holds the fragment interaction listener */
    private OnItemListFragmentInteractionListener mListener;
    /** This variable holds the list of books for sale. */
    private List<ItemContent> mItemList;
    /** This variable holds the recycler view. */
    private RecyclerView mRecyclerView;
    /** This constant represents the Computer URL. */
    private static String CURRENT_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=Books";
    private final static String HOUSEHOLD_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=HouseHoldItems";
    private final static String CELLPHONE_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=CellPhones";
    private final static String COMPUTER_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=Computers";
    private final static String VEHICLE_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=Vehicles";
    private final static String VIDEOGAME_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=VideoGames";
    private final static String BOOK_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=Books";
    private String mCategory;
    private String mCategoryName;
    private ItemDB mItemDB;
    public ItemListFragment() {
    }
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
//    public ItemListFragment(String category) {
//        switch (category) {
//            case "Household Items":
//                CURRENT_URL = HOUSEHOLD_URL;
//                mCategory = "houseHoldItems";
//                break;
//            case "Cellphones":
//                CURRENT_URL = CELLPHONE_URL;
//                mCategory = "cellPhones";
//                break;
//            case "Vehicles":
//                CURRENT_URL = VEHICLE_URL;
//                mCategory =  "vehicles";
//                break;
//            case "Video Gaming":
//                CURRENT_URL = VIDEOGAME_URL;
//                mCategory = "videoGames";
//                break;
//            case "Computers":
//                CURRENT_URL = COMPUTER_URL;
//                mCategory = "computers";
//                break;
//        }
//    }
    public void setCategory(String category) {
        switch (category) {
            case "HouseHoldItems":
                CURRENT_URL = HOUSEHOLD_URL;
                mCategory = "HouseHoldItems";
                break;
            case "CellPhones":
                CURRENT_URL = CELLPHONE_URL;
                mCategory = "CellPhones";
                break;
            case "Vehicles":
                CURRENT_URL = VEHICLE_URL;
                mCategory = "Vehicles";
                break;
            case "VideoGames":
                CURRENT_URL = VIDEOGAME_URL;
                mCategory = "VideoGames";
                break;
            case "Computers":
                CURRENT_URL = COMPUTER_URL;
                mCategory = "Computers";
                break;
            case "Books":
                CURRENT_URL = BOOK_URL;
                mCategory = "Books";
                break;
        }
        mCategoryName = category;
    }
    @SuppressWarnings("unused")
    public static ItemListFragment newInstance(int columnCount) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    /**
     * This method is called when the fragment is created to
     * create the view.
     *
     * @param inflater is the given layout inflator.
     * @param container is the given view group container.
     * @param savedInstanceState is a bundle holding the saved state.
     * @return is the inflated view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        getActivity().setTitle("List of " + mCategoryName);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }
        DownloadBookTask task1 = new DownloadBookTask();
        task1.execute(new String[]{CURRENT_URL});
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            DownloadBookTask task = new DownloadBookTask();
            task.execute(new String[]{CURRENT_URL});
        }
        else {
            if (mItemDB == null) {
                mItemDB = new ItemDB(view.getContext(), mCategoryName);
            }
            if (mItemList == null) {;
                mItemList = mItemDB.getItems();
            }
            mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(mItemList, mListener));
        }
        return view;
    }
    /**
     * This method is used to attach the fragment.
     *
     * @param context is the given context.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemListFragmentInteractionListener) {
            mListener = (OnItemListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnItemListFragmentInteractionListener {
        /**
         * This method must be overridden by the activites that
         * contain this fragment.
         *
         * @param item is the given book content.
         */
        void OnItemListFragmentInteractionListener(ItemContent item);
    }
    /**
     * The DownloadBookTask is used to download books from the database.
     *
     * @author Shelema Bekele
     * @author Vladimir Smirnov
     * @version 1.0
     */
    private class DownloadBookTask extends AsyncTask<String, Void, String> {
        /**
         * This method is called after execution.
         *
         * @param result is the given result.
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
//                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
//                        .show();
                return;
            }
            mItemList = new ArrayList<ItemContent>();
            result = ItemContent.parseItemContentJSON(result, mItemList);
            // Something wrong with the JSON returned.
            if (result != null) {
//                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
//                        .show();
                return;
            }
            // Everything is good, show the list of courses.
            if (!mItemList.isEmpty()) {
                mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(mItemList, mListener));
            }
            if (!mItemList.isEmpty()) {
                mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(mItemList, mListener));
                if (mItemDB == null) {
                    mItemDB = new ItemDB(getActivity(), mCategoryName);
                }
                // Delete old data so that you can refresh the local
                // database with the network data.
                mItemDB.deleteItems();
//                Toast.makeText(getActivity().getApplicationContext(), mBookList.size(), Toast.LENGTH_LONG);
                // Also, add to the local database
                for (int i=0; i<mItemList.size(); i++) {
                    ItemContent item = mItemList.get(i);
                    System.out.println(item.getSellerUserName());
                    mItemDB.insertItem(item.getItemID(),
                            item.getSellerUserName(),
                            item.getItemTitle(),
                            item.getItemPrice(),
                            item.getmItemCondtion(),
                            item.getItemDescription(),
                            item.getSellerLocation(),
                            item.getSellerContact());
                }
            }
            if (!mItemList.isEmpty()) {
                mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(mItemList, mListener));
            }
        }
        /**
         * This method runs in the background.
         *
         * @param urls is the given URL.
         * @return is a String representing the response.
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();
                    InputStream content = urlConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }
                } catch (Exception e) {
//                    response = "Unable to download the list of courses, Reason: "
//                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }
    }
}