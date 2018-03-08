package br.com.fernando.control_password.presentation.presenter;


import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import br.com.fernando.control_password.model.RegisterRequest;
import br.com.fernando.control_password.presentation.contract.CompleteNameView;
import br.com.fernando.control_password.presentation.contract.RegisteredEmailView;

/**
 * Created by Fernando
 */

public class RegisteredEmailPresenter
        extends MvpNullObjectBasePresenter<RegisteredEmailView> {

    private RegisterRequest registerRequest;

    public RegisteredEmailPresenter() {
        registerRequest = new RegisterRequest();
    }

    public void verifyEmail(String emailStr) {
        if (!TextUtils.isEmpty(emailStr) && emailStr.length() >= 6 && emailStr.contains("@")) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
                getView().hideError();
                getView().enableButton(true);
                registerRequest.setEmail(emailStr.trim());
            } else {
                handleFail();
            }
        } else {
            getView().hideError();
            getView().enableButton(false);
        }
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void setRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }

    private void handleFail() {
        getView().enableButton(false);
        getView().showError();
    }

}
