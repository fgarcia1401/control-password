package br.com.fernando.control_password.util;

import android.text.Editable;
import android.text.TextWatcher;

public class SimpleTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // No logic.
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // No logic.
    }

    @Override
    public void afterTextChanged(Editable s) {
        // No logic.
    }
}
