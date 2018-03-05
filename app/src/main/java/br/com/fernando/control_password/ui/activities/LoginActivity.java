package br.com.fernando.control_password.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import br.com.fernando.control_password.R;


public class LoginActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //FragmentUtil.addFragment(getSupportFragmentManager(), R.id.fragment_container, new LoginFragment());
    }

}
