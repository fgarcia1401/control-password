package br.com.fernando.control_password.presentation.contract;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by tqi_coliveira on 28/06/2017.
 */

public interface RegisteredEmailView extends MvpView {

    void enableButton(Boolean b);

    void hideError();

    void showError();
}
