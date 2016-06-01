/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tcss450.uw.edu.mynewapp.model.ItemContent;
/**
 * The BookListFragment is a fragment that is used to display
 * the list of books for sale.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class BookListFragment extends Fragment {
    /** This constant represents the argument column count. */
    private static final String ARG_COLUMN_COUNT = "column-count";
    /** This variable represents the column count. */
    private int mColumnCount = 1;
    /** This variable holds the fragment interaction listener */
    private OnBookListFragmentInteractionListener mListener;
    /** This variable holds the list of books for sale. */
    private List<ItemContent> mBookList;
    /** This variable holds the recycler view. */
    private RecyclerView mRecyclerView;
    /** This constant represents the book URL. */
    private static final String BOOK_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/husky.php?cmd=books";

    private SQLite mSQLite;

    /**
     * This is the BookListFragment constructor.
     */
    public BookListFragment() {
    }

    /**
     * This method is used to create a new instance of the
     * BookListFragment.
     *
     * @param columnCount is the given column count.
     * @return is the created fragment.
     */
    @SuppressWarnings("unused")
    public static BookListFragment newInstance(int columnCount) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * This method is called when the fragment is created.
     *
     * @param savedInstanceState is a bundle holding the saved state.
     */
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
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        getActivity().setTitle("Lists of Books");

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // mRecyclerView.setAdapter(new MyBookRecyclerViewAdapter(mBookList, mListener));
        }

        DownloadBookTask task1 = new DownloadBookTask();
        task1.execute(new String[]{BOOK_URL});

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            CategoryActivity my = new CategoryActivity();
            DownloadBookTask task = new DownloadBookTask();
            task.execute(new String[]{BOOK_URL});

        }
        else {
//            Toast.makeText(view.getContext(),
//                    "No network connection available. Cannot display courses",
//                    Toast.LENGTH_SHORT).show();
            if (mSQLite == null) {
                mSQLite = new SQLite(view.getContext(), "Book");
            }
            if (mBookList == null) {;
                mBookList = mSQLite.getItems();
                System.out.println(mBookList.size());
            }
            mRecyclerView.setAdapter(new MyBookRecyclerViewAdapter(mBookList, mListener));
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
        if (context instanceof OnBookListFragmentInteractionListener) {
            mListener = (OnBookListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    /**
     * This method is used to detach the fragment.
     */
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
    public interface OnBookListFragmentInteractionListener {
        /**
         * This method must be overridden by the activites that
         * contain this fragment.
         *
         * @param item is the given book content.
         */
        void onBookListFragmentInteraction(ItemContent item);
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

            System.out.println("downloading..");
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
//                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
//                        .show();
                return;
            }

            mBookList = new ArrayList<ItemContent>();
            result = ItemContent.parseItemContentJSON(result, mBookList);
            // Something wrong with the JSON returned.
            if (result != null) {
//                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
//                        .show();
                System.out.println("result is null");
                return;
            }
            System.out.println("The book list size: " + mBookList.size());
            if (!mBookList.isEmpty()) {
                mRecyclerView.setAdapter(new MyBookRecyclerViewAdapter(mBookList, mListener));

                if (mSQLite == null) {
                    mSQLite = new SQLite(getActivity(), "Book");
                }

                // Delete old data so that you can refresh the local
                // database with the network data.
                mSQLite.deleteItems();
//                Toast.makeText(getActivity().getApplicationContext(), mBookList.size(), Toast.LENGTH_LONG);
                // Also, add to the local database
                System.out.println(mBookList.size());
                for (int i=0; i<mBookList.size(); i++) {
                    ItemContent item = mBookList.get(i);
                    mSQLite.insertItem(item.getItemID(),
                            item.getSellerUserName(),
                            item.getItemTitle(),
                            item.getItemPrice(),
                            item.getmItemCondtion(),
                            item.getItemDescription(),
                            item.getSellerLocation(),
                            item.getSellerContact());
                }

            }



            // Everything is good, show the list of courses.
            if (!mBookList.isEmpty()) {
                mRecyclerView.setAdapter(new MyBookRecyclerViewAdapter(mBookList, mListener));
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
