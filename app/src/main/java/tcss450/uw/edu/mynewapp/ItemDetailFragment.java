/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import tcss450.uw.edu.mynewapp.model.ItemContent;
/**
 * The ItemDetailFragment is a fragment that represents
 * an item add.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class ItemDetailFragment extends Fragment {
    /** This variable is a TextView that holds the item ID. */
    private TextView mSellerUserNameTextView;
    /** This variable is a TextView that holds the item title. */
    public TextView mItemTitleTextView;
    /** This variable is a TextView that holds the item price. */
    private TextView mItemPriceTextView;
    /** This variable is a TextView that holds the item condition. */
    private TextView mItemConditionTextView;
    /** This variable is a TextView that holds the item description. */
    private TextView mItemDescriptionTextView;
    /** This variable is a TextView that holds the seller location. */
    private TextView mItemSellerLocationTextView;
    /** This variable is a TextView that holds the seller contact information. */
    private TextView mItemSellerContactTextView;
    /** This variable is a String that holds the given email. */
    private String mEmail;
    /** This variable is a String that holds the given password. */
    private String mTitle;
    /** This constant is a String that represents the item selected. */
    public static String ADS_ITEM_SELECTED = "adsItemSelected";
    public String title;
    /**
     * This is the ItemDetailFragment constructor.
     */
    public ItemDetailFragment() {
        // Required empty public constructor
    }

    /**
     * This method is called when the fragment is created and is used
     * to create the view.
     *
     * @param inflater is the given layout inflater.
     * @param container is the given view group container.
     * @param savedInstanceState is a bundle that holds the saved state.
     * @return is the inflated view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(mTitle);
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_detail, container, false);
        //mSellerUserNameTextView = (TextView) view.findViewById(R.id.add_Seller_userName);
        mItemTitleTextView = (TextView) view.findViewById(R.id.item_title);
        mItemPriceTextView = (TextView) view.findViewById(R.id.item_price);
        mItemConditionTextView = (TextView) view.findViewById(R.id.item_condition);
        mItemDescriptionTextView = (TextView) view.findViewById(R.id.item_Description);
        mItemSellerLocationTextView = (TextView) view.findViewById(R.id.item_seller_location);
        mItemSellerContactTextView = (TextView) view.findViewById(R.id.item_seller_contact);


        Button contact_seller = (Button) view.findViewById(R.id.contact_seller_button);
        contact_seller.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mEmail));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, mTitle);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
            }
        });

        Button edit = (Button) view.findViewById(R.id.edit_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), UpdateAddsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     * This method is used to update the view.
     *
     * @param content is the given BookContent.
     */
    public void updateView(ItemContent content) {
        if (content != null) {
           // mSellerUserNameTextView.setText("Seller USerName: "+content.getSellerUserName());
            mItemTitleTextView.setText("Item Tilte: "+content.getItemTitle());
            title = content.getItemTitle();
            mItemPriceTextView.setText(("Item Price: "+content.getItemPrice()));
            mItemConditionTextView.setText("Item condition: "+content.getmItemCondtion());
            mItemDescriptionTextView.setText("Item Description: "+content.getItemDescription());
            mItemSellerLocationTextView.setText("Seller Location: "+content.getSellerLocation());
            mItemSellerContactTextView.setText("Seller Contact: "+content.getSellerContact());
           // mIemImageView.setImageBitmap(content.getItemImage());
            mEmail = content.getSellerContact();
            mTitle = content.getItemTitle();
            getActivity().setTitle(mTitle);
        }
    }

    /**
     * This method is used when the fragment starts.
     */
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
            updateView((ItemContent) args.getSerializable(ADS_ITEM_SELECTED));
        }
    }
}
