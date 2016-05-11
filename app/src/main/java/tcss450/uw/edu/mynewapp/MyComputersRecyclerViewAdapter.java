package tcss450.uw.edu.mynewapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcss450.uw.edu.mynewapp.ComputersListFragment.OnComputerListFragmentInteractionListener;
import tcss450.uw.edu.mynewapp.model.ItemContent;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnComputerListFragmentInteractionListener}.
 *
 */
public class MyComputersRecyclerViewAdapter extends RecyclerView.Adapter<MyComputersRecyclerViewAdapter.ViewHolder> {

    /** This variable is used to hold the list of BookContent. */
    private final List<ItemContent> mValues;
    /** This variable is used to hold the fragment interaction listener */
    private final OnComputerListFragmentInteractionListener mListener;

    /**
     * This is the BookRecyclerViewAdapter constructor.
     *
     * @param items is the given list of BookContent.
     * @param listener is the given fragment interaction listener.
     */
    public MyComputersRecyclerViewAdapter(List<ItemContent> items, OnComputerListFragmentInteractionListener listener) {
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
                .inflate(R.layout.fragment_computers, parent, false);
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
                    mListener.onComputerListFragmentInteraction(holder.mItem);
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
        public ItemContent mItem;

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
