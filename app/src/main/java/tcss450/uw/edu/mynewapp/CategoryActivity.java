/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tcss450.uw.edu.mynewapp.Authenticate.RegisterActivity;
import tcss450.uw.edu.mynewapp.Authenticate.SignInActivity;
import tcss450.uw.edu.mynewapp.model.ItemContent;
/**
 * The CategoryActivity basically the main activity. It holds the
 * list of categories, the buttons and the logo.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class CategoryActivity extends AppCompatActivity implements BookListFragment.OnBookListFragmentInteractionListener {

    /**
     * This method is called when the activity is created.
     *
     * @param savedInstanceState is a bundle holding the saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button addButton = (Button) findViewById(R.id.create_newAds_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, AddBookActivity.class);
                startActivity(i);

            }
        });

        TextView book = (TextView) findViewById(R.id.books);
        book.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, BookActivity.class);
                startActivity(i);
            }
        });
        TextView vehicle = (TextView) findViewById(R.id.vehicles);
        vehicle.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, VehiclesActivity.class);
                startActivity(i);
            }
        });
        TextView computer = (TextView) findViewById(R.id.computers);
        computer.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, ComputerActivity.class);
                startActivity(i);
            }
        });
        TextView cellPhone = (TextView) findViewById(R.id.cellphones);
        cellPhone.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, CellPhoneActivity.class);
                startActivity(i);
            }
        });
        TextView videoGame = (TextView) findViewById(R.id.video_gaming);
        videoGame.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, VideoGameActivity.class);
                startActivity(i);
            }
        });
        TextView houseHold = (TextView) findViewById(R.id.household_items);
        houseHold.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, HouseHoldActivity.class);
                startActivity(i);
            }
        });

        Button register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, SignInActivity.class);
                startActivity(i);

            }
        });
    }

    /**
     * This method is called when the activity is created. It
     * is used to create the options menu.
     *
     * @param menu is the given menu.
     * @return is a Boolean representing the outcome.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method is used to handle the bar item clicks.
     *
     * @param item is the given menu item.
     * @return is a Boolean representing the outcome.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        SharedPreferences sharedPreferences =
                getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS", Context.MODE_PRIVATE);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_logout && sharedPreferences.getBoolean("loggedin", false)) {
            sharedPreferences.edit().putBoolean("loggedin", false)
                    .commit();

            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is to interact with the BookListFragment.
     *
     * @param item is the given book content.
     */
    @Override
    public void onBookListFragmentInteraction(ItemContent item) {

    }

}
