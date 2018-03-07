package br.com.fernando.control_password.presentation.contract;

import com.hannesdorfmann.mosby.mvp.MvpView;

import br.com.fernando.control_password.model.ResponseApi;

/**
 * Created by tqi_coliveira on 03/04/2017.
 */

public interface RegisteredPasswordView extends MvpView {

    void showProcessing();

    void hideProcessing();

    void showSuccess(ResponseApi responseApi);

    void showError();

    void hideKeyboard();

}
