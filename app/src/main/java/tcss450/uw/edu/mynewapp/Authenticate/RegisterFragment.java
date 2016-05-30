/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp.Authenticate;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

import tcss450.uw.edu.mynewapp.R;
/**
 * The RegisterFragment is responsible for implementing the the register
 * functionality.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class RegisterFragment extends Fragment {
    /** This variable holds the course add url. */
    private final static String COURSE_ADD_URL
            = "http://cssgate.insttech.washington.edu/~vsmirnov/Android/addUser.php?";
    /** This variable holds the email EditText */
    private EditText mEmail;
    /** This variable holds the password EditText */
    private EditText mPassword;
    /** This variable holds the register listener */
    private RegisterListener mListener;

    /**
     * This is the RegisterFragment constructor.
     */
    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * This method is called when the fragment is created.
     *
     * @param savedInstanceState is a bundle containing the saved state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * This method is called when the fragment is created and
     * is used to create the view.
     *
     * @param inflater is the given layout inflater.
     * @param container is the given view group container.
     * @param savedInstanceState is a bundle containing the saved state.
     * @return is the inflated view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        mEmail = (EditText) v.findViewById(R.id.email);
        mPassword = (EditText) v.findViewById(R.id.password);

        Button addCourseButton = (Button) v.findViewById(R.id.register_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the button is clicked.
             *
             * @param v is the given view.
             */
            @Override
            public void onClick(View v) {
                if (!mEmail.getText().toString().contains("@")) {
                    Toast.makeText(v.getContext(), "Enter a valid email address"
                            , Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (mPassword.getText().toString().length() < 6) {
                    Toast.makeText(v.getContext(), "Enter password of at least 8 characters"
                            , Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                String url = buildUserURL(v);
                mListener.addUser(url);
                SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("tcss450.uw.edu.mynewapp.PREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("ID",mEmail.getText().toString());
                editor.apply();
            }
        });
        return v;
    }

    /**
     * This method is used to build the user URL.
     *
     * @param v is the given view.
     * @return is a String containing the encoded email/password.
     */
    private String buildUserURL(View v) {

        StringBuilder sb = new StringBuilder(COURSE_ADD_URL);

        try {

            String email = mEmail.getText().toString();
            sb.append("email=");
            sb.append(email);


            String password = mPassword.getText().toString();
            sb.append("&pwd=");
            sb.append(URLEncoder.encode(password, "UTF-8"));


            Log.i("RegisterFragment", sb.toString());

        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    /**
     * This method is called when the fragment is attached.
     *
     * @param context is the given context.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegisterListener) {
            mListener = (RegisterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * This method is called when the fragment is detached.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activites that use
     * this fragment.
     */
    public interface RegisterListener {
        /**
         * This method must be overridden by activities that use
         * this fragment.
         *
         * @param url is the given URL.
         */
        public void addUser(String url);
    }
}
