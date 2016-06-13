/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.huskylist;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import tcss450.uw.edu.huskylist.model.ItemContent;
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

    private int mID;
    private String Prefix;

    private String mCategory;
    public String mUserName;


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
        final ItemDetailFragment that = this;


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
        Button delete = (Button) view.findViewById(R.id.delete_button);
        Button edit = (Button) view.findViewById(R.id.edit_button);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
//                mListener.update(mItemTitleTextView.getText().toString(), mItemPriceTextView.getText().toString(), mItemConditionTextView.getText().toString(),
//                        mItemDescriptionTextView.getText().toString(), mItemSellerLocationTextView.getText().toString(), mItemSellerContactTextView.getText().toString());
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS", Context.MODE_PRIVATE);
                String result = sharedPreferences.getString("ID", "");
                System.out.println("LoginName "+ result);
                System.out.println("UserName " + mUserName);
                System.out.println("Item Category " + mCategory);
                if(result.equalsIgnoreCase(mUserName)){
               // if (result.equals(mItemSellerContactTextView.getText().toString().substring(mItemSellerContactTextView.getText().toString().indexOf(':') + 2, mItemSellerContactTextView.getText().toString().length()))) {
                    Intent intent = new Intent(getActivity(), UpdateAddsActivity.class);
                    intent.putExtra("Title", mItemTitleTextView.getText().toString());
                    intent.putExtra("Price", mItemPriceTextView.getText().toString());
                    intent.putExtra("Condition", mItemConditionTextView.getText().toString());
                    intent.putExtra("Description", mItemDescriptionTextView.getText().toString());
                    intent.putExtra("Location", mItemSellerLocationTextView.getText().toString());
                    intent.putExtra("Contact", mItemSellerContactTextView.getText().toString());
                    intent.putExtra("category", mCategory);
                    intent.putExtra("Email", mEmail);
                    intent.putExtra("ID", mID);
                    intent.putExtra("userName", mUserName);
                    intent.putExtra("Prefix", Prefix);
//                    System.out.println("User login "+ result);
//                    System.out.println("Seller user Name "+ mUserName);

                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), " You forget to login or It must be somebody's ad, you can't edit it", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS", Context.MODE_PRIVATE);
                String result = sharedPreferences.getString("ID", "");
                System.out.println("LoginName "+ result);
                System.out.println("UserName " + mUserName);
                System.out.println("Item Category " + mCategory);

                if (result.equalsIgnoreCase(mUserName)) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("Alert Dialog");
                    alertDialogBuilder.setMessage("Are you sure, Do you want to delete this " + mItemTitleTextView.getText().toString() + "?");
                    alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            DeleteTask task = new DeleteTask();
                            String URL = "http://cssgate.insttech.washington.edu/~sdendaa/deleteHusky.php?cmd=" + mCategory + "&Item_id=" + mID;
                            System.out.println(URL);
                            task.execute(URL);
//                            Intent intent = new Intent(getActivity(), ItemActivity.class);
//                            intent.putExtra("category", mCategory);
//                            startActivity(intent);
                           Toast.makeText(getActivity().getApplicationContext(), "Item has been deleted.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), CategoryActivity.class);
                            startActivity(intent);
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                        }
                    });
                    //Create and display an alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "You forget to login or It must be somebody's ad, you can't delete it", Toast.LENGTH_LONG).show();
                }
            }
        });

//        if(result.equalsIgnoreCase("sdendaa@uw.edu")){
//            delete.setVisibility(view.VISIBLE);
//            edit.setVisibility(view.VISIBLE);
//        }else{
//            delete.setVisibility(view.INVISIBLE);
//            edit.setVisibility(view.INVISIBLE);
//        }

        return view;
    }

    private class DeleteTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

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
                    String s;
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    System.out.println(e);

                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

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


            Bundle bundle = this.getArguments();
            mID = bundle.getInt("ID");
            mCategory = bundle.getString("category");
            Prefix = bundle.getString("Prefx");
            mUserName = bundle.getString("userName");


        }
    }
}
