package tcss450.uw.edu.mynewapp;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private String Category;

    private String Email;

    private String ID;
    private String Prefix;



    //public BookActivity activity;
    private static String CURRENT_URL
            = "http://cssgate.insttech.washington.edu/~sdendaa/updateHusky.php?cmd=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_adds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mItemTitleEditText = (EditText) findViewById(R.id.update_item_titleEdit);
        mItemPriceEditText = (EditText) findViewById(R.id.update_item_priceEdit);
        mItemConditionEditText = (EditText) findViewById(R.id.update_item_condEdit);
        mItemDescriptionEditText = (EditText) findViewById(R.id.update_item_descEdit);
        mItemSellerLocationEditText = (EditText) findViewById(R.id.update_item_sellerLocEdit);
        mItemSellerContactEditText = (EditText) findViewById(R.id.update_item_sellerContEdit);
        final UpdateAddsActivity that = this;
        Button addButton = (Button) findViewById(R.id.save_update_button);

        if(addButton != null)
            addButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method is called when the button is clicked.
                 *
                 * @param view is the given view.
                 */
                @Override
                public void onClick(View view) {
                    ReminderTask task = new ReminderTask();
                    task.execute(new String[] { buildURL().toString() });
                    Intent i = new Intent(that, BookActivity.class);
                    startActivity(i);
                }
                String category = Prefix + "ListFragment.class";

            });

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();

        String title = extras.getString("Title");
        String price = extras.getString("Price");
        String condition = extras.getString("Condition");
        String description = extras.getString("Description");
        String location = extras.getString("Location");
        String contact = extras.getString("Contact");
        Prefix = extras.getString("Prefx");

        String titleHint = title.substring(0, title.indexOf(':') + 1);
        String titleValue = title.substring(title.indexOf(':') + 1);

        String priceHint = price.substring(0, price.indexOf(':') + 1);
        String priceValue = price.substring(price.indexOf(':') + 1);

        String conditionHint = condition.substring(0, condition.indexOf(':') + 1);
        String conditionValue = condition.substring(condition.indexOf(':') + 1);

        String descriptionHint = description.substring(0, description.indexOf(':') + 1);
        String descriptionValue = description.substring(description.indexOf(':') + 1);

        String locationHint = location.substring(0, location.indexOf(':') + 1);
        String locationValue = location.substring(location.indexOf(':') + 1);

        String contactHint = contact.substring(0, contact.indexOf(':') + 1);
        String contactValue = contact.substring(contact.indexOf(':') + 1);


        mItemTitleEditText.setHint(titleHint);
        mItemPriceEditText.setHint(priceHint);
        mItemConditionEditText.setHint(conditionHint);
        mItemDescriptionEditText.setHint(descriptionHint);
        mItemSellerLocationEditText.setHint(locationHint);
        mItemSellerContactEditText.setHint(contactHint);

        mItemTitleEditText.setText(titleValue);
        mItemPriceEditText.setText(priceValue);
        mItemConditionEditText.setText(conditionValue);
        mItemDescriptionEditText.setText(descriptionValue);
        mItemSellerLocationEditText.setText(locationValue);
        mItemSellerContactEditText.setText(contactValue);

        Category = extras.getString("Category");
        Email = extras.getString("Email");
        int id = extras.getInt("ID");
        ID = id + "";
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, CategoryActivity.class);
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


    private String buildURL() {
        StringBuilder sb = new StringBuilder(CURRENT_URL);
        //    http://cssgate.insttech.washington.edu/~sdendaa/updateHusky.php?cmd=books&Seller_userName=v@gmail.com&Item_title=database&Item_price=kfjdjskfkvmd&Item_condition=kckkdjdjfkv&Item_descriptions=kfjdkfkgkg&Seller_location=fjdjfkfkgkkf&Seller_contact=jfjdjdjfjfjgjjf&Item_category=books&Item_id=8
        try {
            sb.append(Category);

            sb.append("&Seller_userName=");
            sb.append(URLEncoder.encode(Email, "UTF-8"));

            sb.append("&Item_title=");
            sb.append(URLEncoder.encode(mItemTitleEditText.getText().toString(), "UTF-8"));

            sb.append("&Item_price=");
            sb.append(URLEncoder.encode(mItemPriceEditText.getText().toString(), "UTF-8"));

            sb.append("&Item_condition=");
            sb.append(URLEncoder.encode(mItemConditionEditText.getText().toString(), "UTF-8"));

            sb.append("&Item_descriptions=");
            sb.append(URLEncoder.encode(mItemDescriptionEditText.getText().toString(), "UTF-8"));

            sb.append("&Seller_location=");
            sb.append(URLEncoder.encode(mItemSellerLocationEditText.getText().toString(), "UTF-8"));

            sb.append("&Seller_contact=");
            sb.append(URLEncoder.encode(mItemSellerContactEditText.getText().toString(), "UTF-8"));

            sb.append("&Item_category=");
            sb.append(URLEncoder.encode(Category, "UTF-8"));

            sb.append("&Item_id=");
            sb.append(URLEncoder.encode(ID, "UTF-8"));

            System.out.println(sb.toString());



        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    private class ReminderTask extends AsyncTask<String, Void, String> {

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
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to add routine, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

    }
}
