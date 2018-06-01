package cz.umaka.mareknovy.vsetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cz.umaka.mareknovy.vsetest.fragments.LoginFragment;
import cz.umaka.mareknovy.vsetest.fragments.LoginResult;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginInteractionListener {

    public static final String SHARED_PREF_KEY = "LOGIN_PREFS";
    public static final String USER_ID = "USER_ID";
    public static final String USER_PASSWORD = "USER_PASSWORD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivityFrame, new LoginFragment())
                .commit();
    }

    @Override
    public void onLoggedIn() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivityFrame, new LoginResult())
                .commit();


    }
}
