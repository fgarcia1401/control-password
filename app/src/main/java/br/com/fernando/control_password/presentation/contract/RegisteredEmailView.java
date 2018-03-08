package br.com.fernando.control_password.presentation.contract;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Fernando
 */

public interface RegisteredEmailView extends MvpView {

    void enableButton(Boolean b);

    void hideError();

    void showError();
}
