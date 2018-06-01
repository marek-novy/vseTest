package cz.umaka.mareknovy.vsetest.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.umaka.mareknovy.vsetest.MainActivity;
import cz.umaka.mareknovy.vsetest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginResult extends Fragment {

    private String userLogin;
    private String password;

    public LoginResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadLoginData();

        TextView userTextView = getView().findViewById(R.id.textViewUser);
        TextView passwordTextView = getView().findViewById(R.id.textViewPassword);

        userTextView.setText(userLogin);
        passwordTextView.setText(password);

    }

    private void loadLoginData() {

        SharedPreferences preferences = getContext().
                getSharedPreferences(MainActivity.SHARED_PREF_KEY, Context.MODE_PRIVATE);

        userLogin = preferences.getString(MainActivity.USER_ID,"");
        password = preferences.getString(MainActivity.USER_PASSWORD,"");



    }

}
