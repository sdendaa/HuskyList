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

import tcss450.uw.edu.mynewapp.Authenticate.SignInActivity;
import tcss450.uw.edu.mynewapp.model.BookContent;

public class CategoryActivity extends AppCompatActivity implements BookListFragment.OnListFragmentInteractionListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        RegisterUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //This code will be to register users
//
//                CourseAddFragment courseAddFragment = new CourseAddFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, courseAddFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });

        TextView book = (TextView) findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryActivity.this, BookActivity.class);
                startActivity(i);
                onBackPressed();
                finish();
            }
        });

//        Button register = (Button) findViewById(R.id.register_button);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoginFragment loginFragment = new LoginFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, loginFragment)
//                        .addToBackStack(null)
//                        .commit();
                Intent i = new Intent(CategoryActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        });


//        if (findViewById(R.id.fragment_container)!= null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, new CategoryListFragment())
//                    .commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(BookContent item) {

    }

}
