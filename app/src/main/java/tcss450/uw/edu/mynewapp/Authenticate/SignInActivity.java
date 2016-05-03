package tcss450.uw.edu.mynewapp.Authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import tcss450.uw.edu.mynewapp.CategoryActivity;
import tcss450.uw.edu.mynewapp.R;

public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginInteractionListener {

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mSharedPreferences = getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS", Context.MODE_PRIVATE);

        if (!mSharedPreferences.getBoolean("loggedin", false)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new LoginFragment())
                    .commit();
        }
        else {
            Intent i = new Intent(this, CategoryActivity.class);
            startActivity(i);
            finish();
        }
    }
    public void login(String userId, String pwd) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Check if the login and password are valid
            //new LoginTask().execute(url);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        openFileOutput("tcss450.uw.edu.mynewapp.LOGIN_FILE"
                                , Context.MODE_PRIVATE));
                outputStreamWriter.write("email = " + userId + ";");
                outputStreamWriter.write("password = " + pwd);
                outputStreamWriter.close();
                Toast.makeText(this, "Stored in File Successfully!", Toast.LENGTH_LONG)
                        .show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mSharedPreferences
                .edit()
                .putBoolean("loggedin", true)
                .commit();
        Intent i = new Intent(this, CategoryActivity.class);
        startActivity(i);
        finish();
    }
}
