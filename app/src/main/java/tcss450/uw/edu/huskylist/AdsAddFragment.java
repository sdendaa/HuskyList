/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.huskylist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private ItemAddListener mListener;
    /** This variable holds the EditText for the item ID. */
    private EditText mSellerUserName;
//    /** This variable holds the EditText for the item ID. */
//    private EditText mItemIdEditText;
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
    //private ImageView  mItemImage;

   //public BookActivity activity;
   private static String CURRENT_URL
           = "http://cssgate.insttech.washington.edu/~sdendaa/AddBooks.php?";
    private final static String HOUSEHOLD_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddHouseHold.php?";
    private final static String CELLPHONE_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddCellPhone.php?";
    private final static String COMPUTER_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddComputer.php?";
    private final static String VEHICLE_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddVehicle.php?";
    private final static String VIDEOGAME_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddVideoGame.php?";
    private final static String BOOK_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddBooks.php?";

    private String mCategory;
    public AdsAddFragment() {
        // Required empty public constructor
       // mCategory = "books";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ads_add, container, false);
        mItemTitleEditText = (EditText) v.findViewById(R.id.add_item_title);
        mItemPriceEditText = (EditText) v.findViewById(R.id.add_item_price);
        mItemConditionEditText = (EditText) v.findViewById(R.id.add_item_condition);
        mItemDescriptionEditText = (EditText) v.findViewById(R.id.add_item_Description);
        mItemSellerLocationEditText = (EditText) v.findViewById(R.id.add_item_seller_location);
        mItemSellerContactEditText = (EditText) v.findViewById(R.id.add_item_seller_contact);



        final String[] items = new String[]{"Books", "Vehicles", "Computers", "CellPhones", "VideoGames", "HouseHoldItems"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                int theID = (int) id;
                switch (items[theID]) {
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
                        mCategory =  "Vehicles";
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

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getActivity().setTitle("Create New Ad");
        Button addAdseButton = (Button) v.findViewById(R.id.add_ads_button);
        addAdseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = buildCourseURL(v);
                mListener.addItems(url);
                ItemListFragment itemListFragment = new ItemListFragment();
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_add_container, itemListFragment);
                ft.commit();
//                Intent intent = new Intent(this, ItemDetailFragment.class);
//               // intent.putExtra("category", mCategory);
//                startActivity(intent);
            }
        });


        return v;
    }

    public interface ItemAddListener {
        void addItems(String url);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemAddListener) {
            mListener = (ItemAddListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AdsAddListener");
        }
    }
    private String buildCourseURL(View v) {
        StringBuilder sb = new StringBuilder(CURRENT_URL);
        try {
            SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS", Context.MODE_PRIVATE);
            String ID = mSharedPreferences.getString("ID", "");
            String SellerUsername = ID;
            sb.append("Seller_userName=");
            sb.append(SellerUsername);
            String ItemTitle = mItemTitleEditText.getText().toString();
            if(ItemTitle == null)
                throw new IllegalArgumentException("Title must be supplied");
            if (ItemTitle.length() < 3) {
                Toast.makeText(v.getContext(), "Title not long enough", Toast.LENGTH_LONG).show();
            }
            sb.append("&Item_title=");
            sb.append(URLEncoder.encode(ItemTitle, "UTF-8"));
            String ItemPrice = mItemPriceEditText.getText().toString();
            if(ItemPrice == null)
                throw new IllegalArgumentException("Price must be supplied");
            if (ItemTitle.length() < 1) {
                Toast.makeText(v.getContext(), "Price not long enough", Toast.LENGTH_LONG).show();
            }
            sb.append("&Item_price=");
            sb.append(URLEncoder.encode(ItemPrice, "UTF-8"));
            String ItemCondition = mItemConditionEditText.getText().toString();
            if(ItemCondition == null)
                throw new IllegalArgumentException("Condition must be supplied");
            if (ItemCondition.length() < 3) {
                Toast.makeText(v.getContext(), "Condition not long enough", Toast.LENGTH_LONG).show();
            }
            sb.append("&Item_condition=");
            sb.append(URLEncoder.encode(ItemCondition, "UTF-8"));
            String ItemDescription = mItemDescriptionEditText.getText().toString();
            if(ItemDescription == null)
                throw new IllegalArgumentException("Description must be supplied");
            if (ItemCondition.length() < 5) {
                Toast.makeText(v.getContext(), "Description not long enough", Toast.LENGTH_LONG).show();
            }
            sb.append("&Item_descriptions=");
            sb.append(URLEncoder.encode(ItemDescription, "UTF-8"));
            String SellerLocation = mItemSellerLocationEditText.getText().toString();
            if(SellerLocation == null)
                throw new IllegalArgumentException("Location must be supplied");
            if (SellerLocation.length() < 3) {
                Toast.makeText(v.getContext(), "Location not long enough", Toast.LENGTH_LONG).show();
            }
            sb.append("&Seller_location=");
            sb.append(URLEncoder.encode(SellerLocation, "UTF-8"));
            String SellerContact = mItemSellerContactEditText.getText().toString();
            if(SellerContact == null)
                throw new IllegalArgumentException("Contact must be supplied");
            if (SellerContact.length() < 5) {
                Toast.makeText(v.getContext(), "Contact not long enough", Toast.LENGTH_LONG).show();
            }
            sb.append("&Seller_contact=");
            sb.append(URLEncoder.encode(SellerContact, "UTF-8"));
            String Category = mCategory;
            sb.append("&Item_category=");
            sb.append(URLEncoder.encode(Category, "UTF-8"));
            Log.i("AdsAddFragment", sb.toString());
        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

}
