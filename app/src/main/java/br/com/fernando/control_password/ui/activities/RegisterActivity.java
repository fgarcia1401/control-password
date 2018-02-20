package br.com.fernando.control_password.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.util.FragmentUtil;
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

}
