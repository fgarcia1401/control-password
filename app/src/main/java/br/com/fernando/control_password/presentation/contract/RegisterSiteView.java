package br.com.fernando.control_password.presentation.contract;

import com.hannesdorfmann.mosby.mvp.MvpView;

import br.com.fernando.control_password.model.ResponseApi;
import br.com.fernando.control_password.model.ResponseErrorApi;

/**
 * Created by Fernando
 */

public interface RegisterSiteView extends MvpView {

    void showSuccess();

    void showError(String s);

    void hideKeyboard();

}
