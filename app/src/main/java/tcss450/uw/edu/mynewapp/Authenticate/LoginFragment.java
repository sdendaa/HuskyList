/*
* HuskyList App
* Authors: Vladimir Smirnov and Shelema Bekele
*/
package tcss450.uw.edu.mynewapp.Authenticate;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tcss450.uw.edu.mynewapp.R;
/**
 * The LoginFragment class is responsible for implementing the login function.
 *
 * @author Shelema Bekele
 * @author Vladimir Smirnov
 * @version 1.0
 */
public class LoginFragment extends Fragment {

    /**
     * This is the LoginFragment constructor.
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * This method is used to create the view.
     *
     * @param inflater is the given layout inflate.
     * @param container is the given view group container.
     * @param savedInstanceState is the bundle holding the saved state.
     * @return is the inflated view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        final EditText userIdText = (EditText) v.findViewById(R.id.user_id);
        final EditText pwdText = (EditText) v.findViewById(R.id.password);
        Button signInButton = (Button) v.findViewById(R.id.login_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userIdText.getText().toString();
                String pwd = pwdText.getText().toString();
                if (TextUtils.isEmpty(userId))  {
                    Toast.makeText(v.getContext(), "Enter userid"
                            , Toast.LENGTH_SHORT)
                            .show();
                    userIdText.requestFocus();
                    return;
                }
                if (!userId.contains("@")) {
                    Toast.makeText(v.getContext(), "Enter a valid email address"
                            , Toast.LENGTH_SHORT)
                            .show();
                    userIdText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pwd))  {
                    Toast.makeText(v.getContext(), "Enter password"
                            , Toast.LENGTH_SHORT)
                            .show();
                    pwdText.requestFocus();
                    return;
                }
                if (pwd.length() < 6) {
                    Toast.makeText(v.getContext(), "Enter password of at least 6 characters"
                            , Toast.LENGTH_SHORT)
                            .show();
                    pwdText.requestFocus();
                    return;
                }

                ((SignInActivity) getActivity()).login(userId, pwd);
            }
        });

        return v;
    }

    /**
     * This interface must be implemented by activities who use
     * this fragment.
     */
    public interface LoginInteractionListener {
        /**
         * This method must be overridden in the activity that uses this fragment.
         *
         * @param userId is the given userId.
         * @param pwd is the given password.
         */
        public void login(String userId, String pwd);
    }

}
