package tcss450.uw.edu.mynewapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;


public class UpdateAddsActivity extends AppCompatActivity {

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



    //public BookActivity activity;
    private static String CURRENT_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/AddBooks.php?";
//    private final static String HOUSEHOLD_URL
//            = "http://cssgate.insttech.washington.edu/~sdendaa/AddHouseHold.php?";
//    private final static String CELLPHONE_URL
//            = "http://cssgate.insttech.washington.edu/~sdendaa/AddCellPhone.php?";
//    private final static String COMPUTER_URL
//            = "http://cssgate.insttech.washington.edu/~sdendaa/AddComputer.php?";
//    private final static String VEHICLE_URL
//            = "http://cssgate.insttech.washington.edu/~sdendaa/AddVehicle.php?";
//    private final static String VIDEOGAME_URL
//            = "http://cssgate.insttech.washington.edu/~sdendaa/AddVideoGame.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_adds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mItemTitleEditText = (EditText) findViewById(R.id.update_item_title);
        mItemPriceEditText = (EditText) findViewById(R.id.update_item_price);
        mItemConditionEditText = (EditText) findViewById(R.id.update_item_condition);
        mItemDescriptionEditText = (EditText) findViewById(R.id.update_item_Description);
        mItemSellerLocationEditText = (EditText) findViewById(R.id.update_item_seller_location);
        mItemSellerContactEditText = (EditText) findViewById(R.id.update_item_seller_contact);
        setUp();
    }


    private void setUp() {
        Intent in = getIntent();
        Bundle infor = in.getBundleExtra("reminder");

        ItemDetailFragment idf = new ItemDetailFragment();
//       mItemTitleEditText.setText(infor.getString(idf.mItemTitleTextView.getText().toString()));

    }
    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, AddItemActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

//    /**
//     * Create the option menu
//     * @param menu is a menu
//     * @return the menu is created
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.add_menu, menu);
//        return true;
//    }


    private String buildCourseURL(View v) {
        StringBuilder sb = new StringBuilder(CURRENT_URL);
        try {
            SharedPreferences mSharedPreferences = getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS", Context.MODE_PRIVATE);
            final String ID = mSharedPreferences.getString("ID", "");
            String SellerUsername = ID;
            sb.append("Seller_userName=");
            sb.append(SellerUsername);

            String ItemTitle = mItemTitleEditText.getText().toString();
            sb.append("&Item_title=");
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
