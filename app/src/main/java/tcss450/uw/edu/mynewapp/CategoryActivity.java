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
import android.widget.Toast;

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
public class CategoryActivity extends AppCompatActivity implements BookListFragment.OnBookListFragmentInteractionListener ,
View.OnClickListener{

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
        initControl();

        Button addButton = (Button) findViewById(R.id.create_newAds_button);
        if(addButton != null)
        addButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param view is the given view.
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, AddItemActivity.class);
                startActivity(i);

            }
        });
    }


    private void initControl() {

        TextView book = (TextView) findViewById(R.id.books);
        TextView vehicle = (TextView) findViewById(R.id.vehicles);
        TextView computer = (TextView) findViewById(R.id.computers);
        TextView cellPhone = (TextView) findViewById(R.id.cellphones);
        TextView videoGame = (TextView) findViewById(R.id.video_gaming);
        TextView houseHold = (TextView) findViewById(R.id.household_items);
        Button register = (Button) findViewById(R.id.register_button);
        Button login = (Button) findViewById(R.id.login_button);
        Button add = (Button) findViewById(R.id.add_ads_button);

        assert book != null;assert vehicle != null;assert cellPhone != null;assert add != null;
        assert computer !=null; assert videoGame !=null; assert houseHold != null; assert register != null;
        assert login !=null;
      //      add.setOnClickListener(this);
        book.setOnClickListener(this);
        vehicle.setOnClickListener(this);
        computer.setOnClickListener(this);
        cellPhone.setOnClickListener(this);
        videoGame.setOnClickListener(this);
        houseHold.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.books:
                Intent book = new Intent(this, BookActivity.class);
                startActivity(book);
                break;
            case R.id.vehicles:
                Intent vel = new Intent(this, VehiclesActivity.class);
                startActivity(vel);
                break;
            case R.id.computers:
                Intent comp = new Intent(CategoryActivity.this, ComputerActivity.class);
                startActivity(comp);
                break;
            case R.id.cellphones:
                Intent cell = new Intent(this, CellPhoneActivity.class);
                startActivity(cell);
                break;
            case R.id.video_gaming:
                Intent vid = new Intent(this, VideoGameActivity.class);
                startActivity(vid);
                break;
            case R.id.household_items:
                Intent hous = new Intent(this, HouseHoldActivity.class);
                startActivity(hous);
                break;
            case R.id.login_button:
                Intent log = new Intent(this, SignInActivity.class);
                startActivity(log);
                break;
            case R.id.register_button:
                Intent reg = new Intent(this, RegisterActivity.class);
                startActivity(reg);
                break;
//            case R.id.add_ads_button:
//                Intent add = new Intent(this, AddItemActivity.class);
//                startActivity(add);
//                break;

        }
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

        if (id == R.id.action_logout && sharedPreferences.getBoolean("loggedin", false)) {
            sharedPreferences.edit().putBoolean("loggedin", false)
                    .apply();

            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
            return true;
        }else if(id == R.id.action_logout && !sharedPreferences.getBoolean("loggedin", false)){

            Context context = getApplicationContext();
            CharSequence text = "Login Here First!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
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
