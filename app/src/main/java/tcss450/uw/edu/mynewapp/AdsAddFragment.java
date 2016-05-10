/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
/**
 * The AdsAddFragment is responsible for creating new ads.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class AdsAddFragment extends Fragment {
    /** This variable holds the book add listener. */
    private BookAddListener mListener;
    /** This variable holds the EditText for the item ID. */
    private EditText mItemIdEditText;
    /** This variable holds the TextView for the item title. */
    private TextView mItemTitleEditText;
    /** This variable holds the TextView for the item price. */
    private TextView mItemPriceEditText;
    /** This variable holds the TextView for the item condition. */
    private TextView mItemConditionEditText;
    /** This variable holds the TextView for the item description. */
    private TextView mItemDescriptionEditText;
    /** This variable holds the TextView for the seller location. */
    private TextView mItemSellerLocationEditText;
    /** This variable holds the TextView for the seller contact information. */
    private TextView mItemSellerContactEditText;

    private final static String Ads_ADD_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddBooks.php?";


    public AdsAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ads_add, container, false);
        mItemIdEditText = (EditText) v.findViewById(R.id.add_item_id);
        mItemTitleEditText = (EditText) v.findViewById(R.id.add_item_title);
        mItemPriceEditText = (EditText) v.findViewById(R.id.add_item_price);
        mItemConditionEditText = (EditText) v.findViewById(R.id.add_item_condition);
        mItemDescriptionEditText = (EditText) v.findViewById(R.id.add_item_Description);
        mItemSellerLocationEditText = (EditText) v.findViewById(R.id.add_item_seller_location);
        mItemSellerContactEditText = (EditText) v.findViewById(R.id.add_item_seller_contact);

//        FloatingActionButton floatingActionButton = (FloatingActionButton)
//                getActivity().findViewById(R.id.fab);
//        floatingActionButton.hide();
        getActivity().setTitle("Create New Ads");
        Button addAdseButton = (Button) v.findViewById(R.id.add_ads_button);
        addAdseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = buildCourseURL(v);
                mListener.addBook(url);
            }
        });


        return v;
    }

    public interface BookAddListener {
        public void addBook(String url);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookAddListener) {
            mListener = (BookAddListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AdsAddListener");
        }
    }
    private String buildCourseURL(View v) {
        StringBuilder sb = new StringBuilder(Ads_ADD_URL);
        try {
            String ItemId = mItemIdEditText.getText().toString();
            sb.append("Item_id=");
            sb.append(ItemId);

            String ItemTitle = mItemTitleEditText.getText().toString();
            sb.append("&Item_Title=");
            sb.append(URLEncoder.encode(ItemTitle, "UTF-8"));

            String ItemPrice = mItemPriceEditText.getText().toString();
            sb.append("&Item_price=");
            sb.append(URLEncoder.encode(ItemPrice, "UTF-8"));

            String ItemCondition = mItemConditionEditText.getText().toString();
            sb.append("&Item_condition=");
            sb.append(URLEncoder.encode(ItemCondition, "UTF-8"));

            String ItemDescription = mItemDescriptionEditText.getText().toString();
            sb.append("&Item_descriptions=");
            sb.append(URLEncoder.encode(ItemDescription, "UTF-8"));

            String SellerLocation = mItemSellerLocationEditText.getText().toString();
            sb.append("&Seller_location=");
            sb.append(URLEncoder.encode(SellerLocation, "UTF-8"));

            String SellerContact = mItemSellerContactEditText.getText().toString();
            sb.append("&Seller_contact=");
            sb.append(URLEncoder.encode(SellerContact, "UTF-8"));

            Log.i("AdsAddFragment", sb.toString());
        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }


}
