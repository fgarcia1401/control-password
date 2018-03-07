package br.com.fernando.control_password.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import br.com.fernando.control_password.ui.activities.LoginActivity;
import br.com.fernando.control_password.ui.activities.MainActivity;
import br.com.fernando.control_password.ui.activities.RegisterActivity;
import br.com.fernando.control_password.util.FragmentUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import org.w3c.dom.Text;

import javax.inject.Inject;

import static android.view.View.*;


public class LoginFragment extends MvpFragment<LoginView, LoginPresenter>
        implements LoginView, FingerPrintAuthCallback {


    @BindView(R.id.tv_digital)
    TextView mTvDigital;

    @BindView(R.id.input_email)
    EditText mInputEmail;

    @BindView(R.id.save_login)
    Switch mSaveLogin;

    @BindView(R.id.input_password)
    EditText mInputPassword;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    private ProgressDialog progressDialog;

    private ControlPasswordComponent component;

    private boolean fingerPrinterAvailable = true;

    @Inject
    ControlPasswordService controlPasswordService;

    private FingerPrintAuthHelper mFingerPrintAuthHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initComponents(view);
        return view;
    }


    private void initComponents(View view) {
        ButterKnife.bind(this, view);

        hideKeyboard();

        populateLogin();

        progressDialog = new ProgressDialog(getContext());

        component = ((ControlPasswordApplication) getActivity().getApplication()).getComponent();
        component.inject( this );

        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(getContext(), this);

        boolean fingerPrintAuthenticate = DataStorage.getInstance(getContext()).getFingerPrintAuthenticate();
        if ( fingerPrintAuthenticate ) {
            mTvDigital.setVisibility(VISIBLE);
        } else {
            mTvDigital.setVisibility(GONE);
        }
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

    private void populateLogin() {
        String password = DataStorage.getInstance(getContext()).getPassword();

        if( !TextUtils.isEmpty(password) ) {
           String email = DataStorage.getInstance(getContext()).getEmail();
           mInputEmail.setText(email);
           mInputPassword.setText(password);
           mSaveLogin.setChecked(true);
        }
    }


    @OnClick(R.id.tv_signup)
    public void callRegister() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_login)
    public void loginUser() {

        if(validateLogin()) {
            LoginRequest loginRequest = new LoginRequest(mInputEmail.getText().toString(),mInputPassword.getText().toString());
            getPresenter().loginUser(loginRequest);
        } else {
            this.showErrorValidate();
        }

    }

    @OnClick(R.id.tv_digital)
    public void callFingerPrint() {
        ((LoginActivity) getActivity()).goNext(LoginFingerPrintFragment.newInstance());
    }

    private boolean validateLogin() {
        return mInputEmail.getText().length() > 0 && mInputPassword.getText().length() > 0 ? true : false;
    }

    @Override
    public LoginPresenter createPresenter() {
          return new LoginPresenter(controlPasswordService);
    }


    @Override
    public void showProcessing() {
        progressDialog.setMessage(getString(R.string.message_waiting));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProcessing() {
        if( progressDialog != null ){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showSuccess(ResponseApi responseApi) {

        if(mSaveLogin.isChecked()) {
            DataStorage.getInstance(getContext()).setPassword(mInputPassword.getText().toString());
        } else {
            DataStorage.getInstance(getContext()).setPassword("");
        }

        DataStorage.getInstance(getContext()).setToken(responseApi.getToken());
        DataStorage.getInstance(getContext()).setEmail(mInputEmail.getText().toString());

        boolean fingerPrintFirstTime = DataStorage.getInstance(getContext()).getFingerPrintFirstTime();
        if(fingerPrinterAvailable && fingerPrintFirstTime){
            ((LoginActivity) getActivity()).goNext(LoginFingerPrintFragment.newInstance());
        } else {
            this.callMain();
        }

    }

    private void callMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void showError(ResponseErrorApi body) {
        String error = body != null ? body.getMessage() : getString(R.string.error_login);
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, error , Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorError));
        snackbar.show();
    }

    @Override
    public void hideKeyboard() {
        FragmentUtil.closeKeyboard(getActivity());
    }

    public void showErrorValidate() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.error_validate_login) , Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorError));
        snackbar.show();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        fingerPrinterAvailable = false;
    }

    @Override
    public void onNoFingerPrintRegistered() {
        fingerPrinterAvailable = false;
    }

    @Override
    public void onBelowMarshmallow() {
        fingerPrinterAvailable = false;
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {}

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {}
}




