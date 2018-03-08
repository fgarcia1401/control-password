package br.com.fernando.control_password.ui.fragments;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;
import br.com.fernando.control_password.R;
import br.com.fernando.control_password.data.DataStorage;
import br.com.fernando.control_password.ui.activities.MainActivity;
import br.com.fernando.control_password.util.FragmentUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFingerPrintFragment extends Fragment implements FingerPrintAuthCallback {

    private static final String CALLED_BY_LOGIN = "CALLED_BY_LOGIN";

    private FingerPrintAuthHelper mFingerPrintAuthHelper;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    private boolean calledByLoginSuccess;

    public static LoginFingerPrintFragment newInstance(boolean calledByLoginSuccess) {
        LoginFingerPrintFragment fragment = new LoginFingerPrintFragment();
        Bundle args = new Bundle();
        args.putBoolean(CALLED_BY_LOGIN, calledByLoginSuccess);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_fingerprint, container, false);
        initComponents(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            calledByLoginSuccess = getArguments().getBoolean(CALLED_BY_LOGIN, false);
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

    private void initComponents(View view) {
        ButterKnife.bind(this, view);

        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(getContext(), this);

        DataStorage.getInstance(getContext()).setFingerPrintFirstTime(false);

        hideKeyboard();
    }


    @OnClick(R.id.bt_cancelar)
    public void cancel() {
        if(calledByLoginSuccess){
            callMain();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void hideKeyboard() {
        FragmentUtil.closeKeyboard(getActivity());
    }


    @Override
    public void onNoFingerPrintHardwareFound() {}

    @Override
    public void onNoFingerPrintRegistered() {}

    @Override
    public void onBelowMarshmallow() {}

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        DataStorage.getInstance(getContext()).setFingerPrintAuthenticate(true);
        callMain();
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        if (!TextUtils.isEmpty(errorMessage))
        return;

        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.invalid_fingerprint) , Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorError));
        snackbar.show();

    }

    private void callMain(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}




