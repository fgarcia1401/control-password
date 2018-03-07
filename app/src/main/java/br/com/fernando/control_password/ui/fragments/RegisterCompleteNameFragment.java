package br.com.fernando.control_password.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.presentation.contract.CompleteNameView;
import br.com.fernando.control_password.presentation.presenter.CompleteNamePresenter;
import br.com.fernando.control_password.ui.activities.RegisterActivity;
import br.com.fernando.control_password.ui.widgets.CustomButton;
import br.com.fernando.control_password.util.FragmentUtil;
import br.com.fernando.control_password.util.SimpleTextWatcher;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterCompleteNameFragment
        extends MvpFragment<CompleteNameView, CompleteNamePresenter>
        implements CompleteNameView {

    @BindView(R.id.ed_name)
    EditText nameEdit;
    @BindView(R.id.bt_continuar)
    CustomButton continueBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_complete_name, container, false);
        initComponents(view);
        return view;
    }

    @NonNull
    @Override
    public CompleteNamePresenter createPresenter() {
        return new CompleteNamePresenter();
    }

    @OnClick(R.id.bt_continuar)
    public void setName() {
        ((RegisterActivity) getActivity()).goNext(RegisterEmailFragment.newInstance(getPresenter().getRegisterRequest()));
    }

    private void initComponents(View view) {
        ButterKnife.bind(this, view);
        nameEdit.addTextChangedListener(getWatcher());
        enableButton(false);

        ((RegisterActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((RegisterActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (continueBtn.isEnabled()) {
                    ((RegisterActivity) getActivity()).goNext(RegisterEmailFragment.
                            newInstance(getPresenter().getRegisterRequest()));
                } else {
                    FragmentUtil.closeKeyboard(getActivity());
                }
                return true;
            }
            return false;
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    @NonNull
    private TextWatcher getWatcher() {
        return new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getPresenter().checkName(s.toString());
            }
        };
    }
}
