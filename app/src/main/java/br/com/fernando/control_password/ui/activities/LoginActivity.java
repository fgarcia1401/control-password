package br.com.fernando.control_password.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.ui.fragments.LoginFragment;
import br.com.fernando.control_password.util.FragmentUtil;



public class LoginActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        start();

    }

    private void start() {
        FragmentUtil.addFragment(getSupportFragmentManager(), R.id.fragment_container, new LoginFragment());
    }

    public void goNext(Fragment fragment) {
        FragmentUtil.closeKeyboard(this);
        FragmentUtil.replaceFragmentWithBackStack(getSupportFragmentManager(), R.id.fragment_container,
                fragment);
    }

}
