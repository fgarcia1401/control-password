package br.com.fernando.control_password.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.app.ControlPasswordApplication;
import br.com.fernando.control_password.component.ControlPasswordComponent;
import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.ui.fragments.LoginFragment;
import br.com.fernando.control_password.ui.fragments.MainFragment;
import br.com.fernando.control_password.ui.fragments.PerfilFragment;
import br.com.fernando.control_password.util.FragmentUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView mTitle;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Inject
    ControlPasswordService controlPasswordService;

    @BindView(R.id.toolbar_top)
    Toolbar toolbar;

    private ControlPasswordComponent component;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                start();
                return true;
            case R.id.navigation_perfil:
                goNext(new PerfilFragment());
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        component = ((ControlPasswordApplication) getApplication()).getComponent();
        component.inject(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setToolbar();
        start();
    }

    private void start() {
        FragmentUtil.addFragment(getSupportFragmentManager(), R.id.fragment_container, new MainFragment());
    }


    public void goNext(Fragment fragment) {
        FragmentUtil.closeKeyboard(this);
        FragmentUtil.replaceFragmentWithBackStack(getSupportFragmentManager(), R.id.fragment_container,
                fragment);
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return true;

    }

}
