package br.com.fernando.control_password.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.model.RegisterRequest;
import br.com.fernando.control_password.presentation.contract.CompleteNameView;
import br.com.fernando.control_password.presentation.contract.RegisteredEmailView;
import br.com.fernando.control_password.presentation.presenter.CompleteNamePresenter;
import br.com.fernando.control_password.presentation.presenter.RegisteredEmailPresenter;
import br.com.fernando.control_password.ui.activities.RegisterActivity;
import br.com.fernando.control_password.ui.widgets.CustomButton;
import br.com.fernando.control_password.util.FragmentUtil;
import br.com.fernando.control_password.util.SimpleTextWatcher;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterEmailFragment
        extends MvpFragment<RegisteredEmailView, RegisteredEmailPresenter>
        implements RegisteredEmailView {

    private static final String REGISTER_REQUEST = "REGISTER_REQUEST";

    @BindView(R.id.ed_email)
    EditText nameEdit;
    @BindView(R.id.bt_continuar)
    CustomButton continueBtn;
    @BindView(R.id.ti_email)
    TextInputLayout emailTi;

    public static RegisterEmailFragment newInstance(RegisterRequest param1) {
        RegisterEmailFragment fragment = new RegisterEmailFragment();
        Bundle args = new Bundle();
        args.putParcelable(REGISTER_REQUEST, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_complete_name, container, false);
        initComponents(view);
        return view;
    }

    @NonNull
    @Override
    public RegisteredEmailPresenter createPresenter() {
        return new RegisteredEmailPresenter();
    }

    @OnClick(R.id.bt_continuar)
    public void setEmail() {
        Log.i("Teste", "Ir para a tela de senha");
        //((RegisterActivity) getActivity()).goNext(RegisterEmailFragment.newInstance(getPresenter().getRegisterRequest()));
    }

    private void initComponents(View view) {
        ButterKnife.bind(this, view);
        enableButton(false);

        emailTi.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (continueBtn.isEnabled()) {
                    //go next
                } else {
                    FragmentUtil.closeKeyboard(getActivity());
                }
                return true;
            }
            return false;
        });

        emailTi.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                getPresenter().verifyEmail(s.toString());
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            getPresenter().setRegisterRequest(getArguments().getParcelable(REGISTER_REQUEST));
        }

        ((RegisterActivity) getActivity()).reqFocus(nameEdit);
    }

    @Override
    public void enableButton(Boolean enabledButton) {
        continueBtn.setEnabled(enabledButton);
        if (enabledButton) {
            continueBtn.setAlpha(1f);
        } else {
            continueBtn.setAlpha(0.5f);
        }
    }

    @Override
    public void hideError() {
        emailTi.setErrorEnabled(false);
        emailTi.setHintTextAppearance(R.style.primaryAppearanceTil);
    }

    @Override
    public void showError() {
        emailTi.setErrorEnabled(true);
        emailTi.setError(getString(R.string.error_email_register));
        emailTi.setHintTextAppearance(R.style.error_appearance_til);
        emailTi.setErrorTextAppearance(R.style.error_appearance_til);
    }


}




