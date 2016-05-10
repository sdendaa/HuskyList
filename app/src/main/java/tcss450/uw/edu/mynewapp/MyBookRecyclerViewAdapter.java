/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcss450.uw.edu.mynewapp.BookListFragment.OnListFragmentInteractionListener;
import tcss450.uw.edu.mynewapp.model.BookContent;

import java.util.List;
/**
 * The MyBookRecyclerViewAdapter is the RecyclerView used
 * to display the list of books for sale.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class MyBookRecyclerViewAdapter extends RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder> {
    /** This variable is used to hold the list of BookContent. */
    private final List<BookContent> mValues;
    /** This variable is used to hold the fragment interaction listener */
    private final OnListFragmentInteractionListener mListener;

    /**
     * This is the BookRecyclerViewAdapter constructor.
     *
     * @param items is the given list of BookContent.
     * @param listener is the given fragment interaction listener.
     */
    public MyBookRecyclerViewAdapter(List<BookContent> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    /**
     * This method is used to create the view holder.
     *
     * @param parent is the given view group parent.
     * @param viewType is the view type.
     * @return is the inflated view.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_book, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method binds the view holder.
     *
     * @param holder is the given view holder.
     * @param position is the given position.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getItemTitle());
        holder.mContentView.setText(mValues.get(position).getItemPrice());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /**
     * This method returns the item count.
     *
     * @return is the item count.
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * The ViewHolder class is used to represent a book.
     *
     * @author Shelema Bekele
     * @author Vladimir Smirnov
     * @version 1.0
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        /** This variable holds the view. */
        public final View mView;
        /** This variable holds TextView that holds the ID. */
        public final TextView mIdView;
        /** This variable holds TextView that holds the content. */
        public final TextView mContentView;
        /** This variable holds the item. */
        public BookContent mItem;

        /**
         * This is the ViewHolder constructor.
         *
         * @param view is the given view.
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        /**
         * This method is used to represent the book as a String.
         *
         * @return is the String.
         */
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
