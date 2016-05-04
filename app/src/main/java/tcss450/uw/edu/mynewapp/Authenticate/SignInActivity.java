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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tcss450.uw.edu.mynewapp.CategoryActivity;
import tcss450.uw.edu.mynewapp.MyBookRecyclerViewAdapter;
import tcss450.uw.edu.mynewapp.R;
import tcss450.uw.edu.mynewapp.model.BookContent;

public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginInteractionListener {

    private SharedPreferences mSharedPreferences;
    private Map <String,String> mMap;
    private static final String COURSE_URL
            = "http://cssgate.insttech.washington.edu/~vsmirnov/Android/test.php?cmd=users";

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
        }
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            DownloadCoursesTask task = new DownloadCoursesTask();
            task.execute(new String[]{COURSE_URL});
        }
    }
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
               // System.out.println(value + " " + key);
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
            }

            //new LoginTask().execute(url);
//            try {
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
//                        openFileOutput("tcss450.uw.edu.mynewapp.LOGIN_FILE"
//                                , Context.MODE_PRIVATE));
//                outputStreamWriter.write("email = " + userId + ";");
//                outputStreamWriter.write("password = " + pwd);
//                outputStreamWriter.close();
//                Toast.makeText(this, "Stored in File Successfully!", Toast.LENGTH_LONG)
//                        .show();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    private class DownloadCoursesTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {

            mMap = new HashMap<String,String>();
            System.out.print("Map created\n\n\n");

            String answer = parseJSON(result, mMap);

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

        private String parseJSON (String courseJSON, Map <String,String> myMap) {
            String reason = "1";
            if (courseJSON != null) {
                try {
                    JSONArray arr = new JSONArray(courseJSON);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);

//                        BookContent book = new BookContent(obj.getString(BookContent.Item_id), obj.getString(BookContent.Item_title),
//                                obj.getString(BookContent.Item_price), obj.getString(BookContent.Item_Condition), obj.getString(BookContent.Item_description),
//                                obj.getString(BookContent.seller_location), obj.getString(BookContent.seller_contact));

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
