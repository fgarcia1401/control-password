package br.com.fernando.control_password.presentation.presenter;


import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import br.com.fernando.control_password.model.RegisterRequest;
import br.com.fernando.control_password.presentation.contract.CompleteNameView;

/**
 * Created by tqi_coliveira on 28/06/2017.
 */

public class CompleteNamePresenter
        extends MvpNullObjectBasePresenter<CompleteNameView> {

    private RegisterRequest registerRequest;

    public CompleteNamePresenter() {
        registerRequest = new RegisterRequest();
    }

    public void checkName(String name) {
        try {
            checkFullName(name);
            getView().enableButton(true);
            registerRequest.setName(name.trim());
        } catch (IllegalArgumentException e) {
            getView().enableButton(false);
        }
    }

    private void checkFullName(String name) throws IllegalArgumentException {
        try {
            String[] separatedName = name.trim().split(" ");
            if (separatedName[0].length() < 2 || separatedName[1].length() < 2) {
                throw new IllegalArgumentException();
            }
        } catch (Exception ignore) {
            throw new IllegalArgumentException();
        }
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }
}
