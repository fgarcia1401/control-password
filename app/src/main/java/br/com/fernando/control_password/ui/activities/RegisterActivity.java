package br.com.fernando.control_password.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import br.com.fernando.control_password.R;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this );

    }

    private void start() {
        /*FragmentUtil.addFragment(getSupportFragmentManager(),
                R.id.fragment_container, new RegisterNameFragment()); */
    }

}
