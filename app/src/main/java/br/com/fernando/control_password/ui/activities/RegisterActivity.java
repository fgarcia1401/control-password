package br.com.fernando.control_password.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import javax.inject.Inject;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.app.ControlPasswordApplication;
import br.com.fernando.control_password.component.ControlPasswordComponent;
import br.com.fernando.control_password.ui.fragments.RegisterCompleteNameFragment;
import br.com.fernando.control_password.util.FragmentUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_top)
    Toolbar toolbar;

    @Inject
    ControlPasswordService controlPasswordService;

    private ControlPasswordComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this );

        component = ((ControlPasswordApplication) getApplication()).getComponent();

        component.inject( this );

        start();
        setToolbar();

    }

    public void setToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void start() {
        FragmentUtil.addFragment(getSupportFragmentManager(),
                R.id.fragment_container, new RegisterCompleteNameFragment());
    }

    public void goNext(Fragment fragment) {
        FragmentUtil.closeKeyboard(this);
        FragmentUtil.replaceFragmentWithBackStack(getSupportFragmentManager(), R.id.fragment_container,
                fragment);
    }

    public void reqFocus(EditText editText) {
        editText.requestFocus();
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ControlPasswordService getControlPasswordService() {
        return controlPasswordService;
    }


}
