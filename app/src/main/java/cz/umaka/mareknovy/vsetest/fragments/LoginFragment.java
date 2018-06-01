package cz.umaka.mareknovy.vsetest.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import cz.umaka.mareknovy.vsetest.MainActivity;
import cz.umaka.mareknovy.vsetest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private OnLoginInteractionListener listener;


    public LoginFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonLogin = view.findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputLayout userTextLayout = getView().findViewById(R.id.textInputLayoutNumber);
                TextInputLayout passwordTextLayout = getView().findViewById(R.id.textInputLayoutPassword);

                String user = userTextLayout.getEditText().getText().toString();
                String password = passwordTextLayout.getEditText().getText().toString();

                Boolean error;

                if (user.length() < 3) {
                    userTextLayout.setErrorEnabled(true);
                    userTextLayout.setError("Minimum length 3 chars");
                } else {
                    userTextLayout.setErrorEnabled(false);
                }
                if (password.length() < 3) {
                    passwordTextLayout.setErrorEnabled(true);
                    passwordTextLayout.setError("Minimum length 3 chars");
                } else {
                    passwordTextLayout.setErrorEnabled(false);
                }


                if (!userTextLayout.isErrorEnabled() || !passwordTextLayout.isErrorEnabled()) {

                    saveUserId(user, password);


                    if (listener != null) {
                        listener.onLoggedIn();
                    }
                } else {
                    Toast.makeText(getContext(), "Please correct the errors", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    private void saveUserId(String userId, String userPassword) {
        SharedPreferences preferences = getContext().
                getSharedPreferences(MainActivity.SHARED_PREF_KEY, Context.MODE_PRIVATE);

        preferences.edit()
                .putString(MainActivity.USER_ID, userId)
                .putString(MainActivity.USER_PASSWORD, userPassword)
                .commit();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnLoginInteractionListener) {
            listener = (OnLoginInteractionListener) context;
        } else {
            throw new RuntimeException(context.getClass().getName() +
                    " does not implement OnLoginInteractionListener"
            );
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnLoginInteractionListener {
        void onLoggedIn();

    }

}
