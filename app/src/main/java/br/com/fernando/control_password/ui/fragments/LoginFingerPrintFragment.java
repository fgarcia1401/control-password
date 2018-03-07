package br.com.fernando.control_password.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import javax.inject.Inject;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.app.ControlPasswordApplication;
import br.com.fernando.control_password.component.ControlPasswordComponent;
import br.com.fernando.control_password.data.DataStorage;
import br.com.fernando.control_password.model.LoginRequest;
import br.com.fernando.control_password.model.ResponseApi;
import br.com.fernando.control_password.model.ResponseErrorApi;
import br.com.fernando.control_password.presentation.contract.LoginView;
import br.com.fernando.control_password.presentation.presenter.LoginPresenter;
import br.com.fernando.control_password.ui.activities.RegisterActivity;
import br.com.fernando.control_password.util.FragmentUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFingerPrintFragment extends Fragment implements FingerPrintAuthCallback {


    private FingerPrintAuthHelper mFingerPrintAuthHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_fingerprint, container, false);
        initComponents(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onPause() {
        super.onPause();

        mFingerPrintAuthHelper.stopAuth();

    }

    private void initComponents(View view) {
        ButterKnife.bind(this, view);

        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(getContext(), this);


        hideKeyboard();

    }

    public void hideKeyboard() {
        FragmentUtil.closeKeyboard(getActivity());
    }


    @Override
    public void onNoFingerPrintHardwareFound() {

    }

    @Override
    public void onNoFingerPrintRegistered() {

    }

    @Override
    public void onBelowMarshmallow() {

    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {

    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        switch (errorCode) {    //Parse the error code for recoverable/non recoverable error.
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                //Cannot recognize the fingerprint scanned.
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                //This is not recoverable error. Try other options for user authentication. like pin, password.
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                //Any recoverable error. Display message to the user.
                break;
        }
    }
}




