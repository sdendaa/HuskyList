package tcss450.uw.edu.mynewapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ContactSellerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_seller);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button ContactButton = (Button) findViewById(R.id.contact_seller_button);
        ContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactSellerActivityFragment contactFragment = new ContactSellerActivityFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, contactFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
