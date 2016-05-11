/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp.Authenticate;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tcss450.uw.edu.mynewapp.CategoryActivity;
import tcss450.uw.edu.mynewapp.R;

/**
 * The SignInActivity holds the LoginFragment which is responsible
 * for implementing the login functionality.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginInteractionListener {
    /** This variable holds the shared preferences. */
    private SharedPreferences mSharedPreferences;
    /** This variable holds a map that has all of the email/password pairs. */
    private Map <String,String> mMap;
    /** This variable holds the user URL */
    private static final String USER_URL
            = "http://cssgate.insttech.washington.edu/~vsmirnov/Android/test.php?cmd=users";

    /**
     * This method is called when the activity is created.
     *
     * @param savedInstanceState is the given bundle holding the saved state.
     */
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
            Toast.makeText(getApplicationContext(), "Already logged in!"
                    , Toast.LENGTH_LONG)
                    .show();
            Intent i = new Intent(this, CategoryActivity.class);
            startActivity(i);
        }
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            DownloadUserTask task = new DownloadUserTask();
            task.execute(new String[]{USER_URL});
        }
    }

    /**
     * This method passes the login information from the LoginFragment.
     *
     * @param userId is the given userId.
     * @param pwd is the given password.
     */
    public void login(String userId, String pwd) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Check if the login and password are valid
            Iterator<Map.Entry<String, String>> iterator = mMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String,String> pairs = (Map.Entry<String,String>)iterator.next();
                String value =  pairs.getValue();
                String key = pairs.getKey();
               // Passwords match
                if (userId.equals(key) && pwd.equals(value)) {
                    System.out.println("Logged in");
                    mSharedPreferences
                            .edit()
                            .putBoolean("loggedin", true)
                            .commit();
                    Toast.makeText(getApplicationContext(), "Logged in successfully!"
                            , Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(this, CategoryActivity.class);
                    startActivity(i);
                }
                // On last pair and passwords do not match.
                if ((!iterator.hasNext()) && ((!userId.equals(key) && !pwd.equals(value)))) {
                    Toast.makeText(getApplicationContext(), "Invalid email or password!"
                            , Toast.LENGTH_LONG)
                            .show();
                }
            }

        }
    }
    /**
     * The DownloadUserTask downloads all of the users.
     *
     * @author Shelema Bekele
     * @author Vladimir Smirnov
     * @version 1.0
     */
    private class DownloadUserTask extends AsyncTask<String, Void, String> {

        /**
         * This method is called when the task is executed.
         *
         * @param result is the given JSON result.
         */
        @Override
        protected void onPostExecute(String result) {

            mMap = new HashMap<String,String>();
            System.out.print("Map created\n\n\n");

            String answer = parseJSON(result, mMap);

        }

        /**
         * This method runs in the background.
         *
         * @param urls is the given URL.
         * @return is a String representing the response.
         */
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
                    response = "Unable to download the list of courses, Reason: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * This method is responsible for parsing the JSON String.
         *
         * @param userJSON is the user JSON string.
         * @param myMap is a map holding the email/password pairs.
         * @return is a String that is 1 if successful.
         */
        private String parseJSON (String userJSON, Map <String,String> myMap) {
            String reason = "1";
            if (userJSON != null) {
                try {
                    JSONArray arr = new JSONArray(userJSON);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        myMap.put(obj.getString("email"), obj.getString("pwd"));
                    }

                } catch (JSONException e) {
                    reason = "Unable to parse data, Reason: " + e.getMessage();
                }
            }
            return reason;
        }

    }

}
