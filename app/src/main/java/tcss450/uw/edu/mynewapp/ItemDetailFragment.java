package tcss450.uw.edu.mynewapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import tcss450.uw.edu.mynewapp.Authenticate.RegisterActivity;
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
    private TextView mItemSellerLocationTextView;
    private TextView mItemSellerContactTextView;
    private String mEmail;
    private String mTitle;

    public static String ADS_ITEM_SELECTED = "adsItemSelected";



    public ItemDetailFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(mTitle);
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_detail, container, false);
        mItemIdTextView = (TextView) view.findViewById(R.id.item_id);
        mItemTitleTextView = (TextView) view.findViewById(R.id.item_title);
        mItemPriceTextView = (TextView) view.findViewById(R.id.item_price);
        mItemConditionTextView = (TextView) view.findViewById(R.id.item_condition);
        mItemDescriptionTextView = (TextView) view.findViewById(R.id.item_Description);
        mItemSellerLocationTextView = (TextView) view.findViewById(R.id.item_seller_location);
        mItemSellerContactTextView = (TextView) view.findViewById(R.id.item_seller_contact);

        Button contact_seller = (Button) view.findViewById(R.id.contact_seller_button);
        contact_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mEmail));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
            }
        });

//        FloatingActionButton floatingActionButton = (FloatingActionButton)
//                getActivity().findViewById(R.id.fab);
//        floatingActionButton.show();

        return view;
    }

    public void updateView(BookContent content) {
        if (content != null) {
            mItemIdTextView.setText("Item ID: "+content.getItemID());
            mItemTitleTextView.setText("Item Tilte: "+content.getItemTitle());
            mItemPriceTextView.setText(("Item Price: "+content.getItemPrice()));
            mItemConditionTextView.setText("Item condition: "+content.getmItemCondtion());
            mItemDescriptionTextView.setText("Item Description: "+content.getItemDescription());
            mItemSellerLocationTextView.setText("Seller Location: "+content.getSellerLocation());
            mItemSellerContactTextView.setText("Seller Contact: "+content.getSellerContact());
            mEmail = content.getSellerContact();
            mTitle = content.getItemTitle();
            getActivity().setTitle(mTitle);
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
