package tcss450.uw.edu.mynewapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import tcss450.uw.edu.mynewapp.model.BookContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment {
    private TextView mItemIdTextView;
    private TextView mItemTitleTextView;
    private TextView mItemPriceTextView;
    private TextView mItemConditionTextView;
    private TextView mItemDescriptionTextView;
    private TextView mItemCategoryTextView;
    private TextView mItemSalerLocationTextView;
    public static String ADS_ITEM_SELECTED = "adsItemSelected";



    public ItemDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_detail, container, false);
        mItemIdTextView = (TextView) view.findViewById(R.id.item_id);
        mItemTitleTextView = (TextView) view.findViewById(R.id.item_title);
        mItemPriceTextView = (TextView) view.findViewById(R.id.item_price);
        mItemConditionTextView = (TextView) view.findViewById(R.id.item_condition);
        mItemDescriptionTextView = (TextView) view.findViewById(R.id.item_Description);
        mItemCategoryTextView = (TextView) view.findViewById(R.id.item_Category);
        mItemSalerLocationTextView = (TextView) view.findViewById(R.id.item_saler_location);

//        FloatingActionButton floatingActionButton = (FloatingActionButton)
//                getActivity().findViewById(R.id.fab);
//        floatingActionButton.show();

        return view;
    }

    public void updateView(BookContent content) {
        if (content != null) {
            mItemIdTextView.setText(content.getItemID());
            mItemTitleTextView.setText(content.getItemTitle());
            mItemPriceTextView.setText(new Double(content.getItemPrice()).toString());
            mItemConditionTextView.setText(content.getmItemCondtion());
            mItemDescriptionTextView.setText(content.getItemDescription());
            mItemCategoryTextView.setText(content.getItemCategory());
            mItemSalerLocationTextView.setText(content.getSalerLocation());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateView((BookContent) args.getSerializable(ADS_ITEM_SELECTED));
        }
    }


}
