package br.com.fernando.control_password.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import br.com.fernando.control_password.data.DataStorage;
import br.com.fernando.control_password.model.RegisterRequest;
import br.com.fernando.control_password.model.ResponseApi;
import br.com.fernando.control_password.presentation.contract.RegisteredPasswordView;
import br.com.fernando.control_password.presentation.presenter.PasswordPresenter;
import br.com.fernando.control_password.ui.activities.LoginActivity;
import br.com.fernando.control_password.ui.activities.RegisterActivity;
import br.com.fernando.control_password.ui.widgets.CustomButton;
import br.com.fernando.control_password.util.FragmentUtil;
import br.com.fernando.control_password.util.SimpleTextWatcher;
import br.com.fernando.control_password.util.ValidPassword;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import br.com.fernando.control_password.R;


public class RegisterPasswordFragment
 extends MvpFragment<RegisteredPasswordView, PasswordPresenter>
        implements RegisteredPasswordView {

    private static final String REGISTER_REQUEST = "REGISTER_REQUEST";

    @BindView(R.id.bt_finalize)
    CustomButton continueBtn;

    @BindView(R.id.register_password_scroll)
    ScrollView passScroll;

    @BindView(R.id.client_password_til)
    TextInputLayout passTil;

    @BindView(R.id.warning_msg_txt)
    TextView warningTxt;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    private Boolean errorOnOff, scrollFocusDown;

    private RegisterRequest registerRequest;

    private ProgressDialog progressDialog;

    public static RegisterPasswordFragment newInstance(RegisterRequest param1) {
        RegisterPasswordFragment fragment = new RegisterPasswordFragment();
        Bundle args = new Bundle();
        args.putParcelable(REGISTER_REQUEST, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public PasswordPresenter createPresenter() {
        return new PasswordPresenter(((RegisterActivity) getActivity()).getControlPasswordService());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        errorOnOff = false;
        scrollFocusDown = false;
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            registerRequest = getArguments().getParcelable(REGISTER_REQUEST);
        }
    }

    @OnClick(R.id.bt_finalize)
    public void continuous() {
        if (validatePassword()) {
            registerRequest.setPassword(passTil.getEditText().getText().toString().trim());
            getPresenter().registerAccount();
        } else {
            showMsgError(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_password, container, false);
        initComponents(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            getPresenter().setRegisterRequest(getArguments().getParcelable(REGISTER_REQUEST));
        }
    }

    private void initComponents(View view) {

        ButterKnife.bind(this, view);
        passTil.getEditText().addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!scrollFocusDown && s.length() > 0) {
                    scrollDown();
                }
                if (s.toString().trim().isEmpty()) {
                    passTil.setPasswordVisibilityToggleEnabled(false);
                } else if (!passTil.isPasswordVisibilityToggleEnabled()) {
                    passTil.setPasswordVisibilityToggleEnabled(true);
                }
                if (s.toString().trim().length() > 9) {
                    warningTxt.setVisibility(View.GONE);
                    enableButton(true);
                } else {
                    enableButton(false);
                    warningTxt.setVisibility(View.VISIBLE);
                }
                if (errorOnOff) {
                    showMsgError(false);
                }
            }
        });
        passTil.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (continueBtn.isEnabled()) {
                    registerRequest.setPassword(passTil.getEditText().getText().toString().trim());
                   /* RegisterCpfFragment fragment = RegisterCpfFragment.newInstance(registerRequest);
                    ((RegisterActivity) getActivity()).goNext(fragment); */
                } else {
                    FragmentUtil.closeKeyboard(getActivity());
                }
                return true;
            }
            return false;
        });
        ((RegisterActivity) getActivity()).reqFocus(passTil.getEditText());
        warningTxt = view.findViewById(R.id.warning_msg_txt);
        warningTxt.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(getContext());
        enableButton(false);
    }

    private void scrollDown() {
        passScroll.post(() -> {
            passScroll.scrollTo(0, continueBtn.getBottom());
            scrollFocusDown = true;
        });
    }

    private void showMsgError(Boolean b) {
        if (b) {
            passTil.setErrorEnabled(true);
            passTil.setError(getString(R.string.error_password_register));
            passTil.setHintTextAppearance(R.style.error_appearance_til);
            passTil.setErrorTextAppearance(R.style.error_appearance_til);
            passTil.setPasswordVisibilityToggleTintList(getResources().getColorStateList(R.color.red_error));
            errorOnOff = true;
        } else {
            passTil.setErrorEnabled(false);
            errorOnOff = false;
            passTil.setPasswordVisibilityToggleTintList(getResources().getColorStateList(R.color.colorTilHintActive));
            passTil.setHintTextAppearance(R.style.primaryAppearanceTil);
        }
    }

    private void enableButton(boolean b) {
        if (b) {
            continueBtn.setEnabled(true);
            continueBtn.setAlpha(1f);
        } else {
            continueBtn.setEnabled(false);
            continueBtn.setAlpha(0.5f);
        }
    }

    private Boolean validatePassword() {
        String passwordStr = passTil.getEditText().getText().toString().trim();
        return ValidPassword.validatePassword(passwordStr);

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

        DataStorage.getInstance(getContext()).setToken(responseApi.getToken());
        this.callLogin();

    }

    @Override
    public void showError() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, getString(R.string.error_save_user) , Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorError));
        snackbar.show();
    }

    @Override
    public void hideKeyboard() {
        FragmentUtil.closeKeyboard(getActivity());
    }

    private void callLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
