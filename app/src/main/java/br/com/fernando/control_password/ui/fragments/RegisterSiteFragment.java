package br.com.fernando.control_password.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import br.com.fernando.control_password.R;
import br.com.fernando.control_password.data.DataStorage;
import br.com.fernando.control_password.data.database.AppDatabase;
import br.com.fernando.control_password.model.SiteRegister;
import br.com.fernando.control_password.presentation.contract.RegisterSiteView;
import br.com.fernando.control_password.presentation.presenter.RegisterSitePresenter;
import br.com.fernando.control_password.ui.activities.MainActivity;
import br.com.fernando.control_password.util.FragmentUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.*;


public class RegisterSiteFragment extends MvpFragment<RegisterSiteView, RegisterSitePresenter>
        implements RegisterSiteView {


    private static final String SITE_REGISTER = "SITE_REGISTER";

    private SiteRegister siteRegisterEdit;

    @BindView(R.id.input_site)
    EditText inputSite;

    @BindView(R.id.input_email)
    EditText inputEmail;

    @BindView(R.id.input_password)
    EditText inputPassword;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.bt_delete)
    Button btDelete;

    private SiteRegister siteRegister;

    private AppDatabase db;


    public static RegisterSiteFragment newInstance(SiteRegister param1) {
        RegisterSiteFragment fragment = new RegisterSiteFragment();

        if(param1 != null) {
            Bundle args = new Bundle();
            args.putSerializable(SITE_REGISTER, param1);
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            this.siteRegisterEdit = (SiteRegister) getArguments().getSerializable(SITE_REGISTER);
            btDelete.setVisibility(VISIBLE);
            this.populateFields(siteRegisterEdit);
        } else {
            btDelete.setVisibility(INVISIBLE);
            this.siteRegisterEdit = null;
        }

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_site, container, false);
        initComponents(view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDatabase(getActivity().getApplicationContext());
    }


    private void initComponents(View view) {
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.bt_save)
    public void save() {
        if (validate()) {

            if (siteRegisterEdit != null) {
                siteRegisterEdit.setEmail(inputEmail.getText().toString());
                siteRegisterEdit.setSite(inputSite.getText().toString());
                siteRegisterEdit.setPassword(inputPassword.getText().toString());
                getPresenter().updateSite(siteRegisterEdit);
            } else {
                getPresenter().addSite(this.populateFields());
            }

        } else {
            this.showError(getString(R.string.fields_required));
        }
    }

    @OnClick(R.id.bt_delete)
    public void delete() {
        getPresenter().deleteSite( (int) siteRegisterEdit.getId());
        getFragmentManager().popBackStack();
    }

    private SiteRegister populateFields() {

        SiteRegister siteRegister = new SiteRegister();
        siteRegister.setEmail(inputEmail.getText().toString().trim());
        siteRegister.setSite(inputSite.getText().toString().trim());
        siteRegister.setPassword(inputPassword.getText().toString());

        String email = DataStorage.getInstance(getContext()).getEmail();
        siteRegister.setEmailAccount(email);

        return siteRegister;
    }

    private void populateFields(SiteRegister siteRegister) {

        inputEmail.setText(siteRegister.getEmail());
        inputSite.setText(siteRegister.getSite());
        inputPassword.setText(siteRegister.getPassword());
    }

    private boolean validate(){
        return inputEmail.getText().toString().length() > 0
                && inputSite.getText().length() > 0
                && inputPassword.getText().length() > 0;
    }

    @Override
    public RegisterSitePresenter createPresenter() {
          return new RegisterSitePresenter(db);
    }

    @Override
    public void showSuccess() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void showError(String mensagem) {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, mensagem , Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorError));

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                snackbar.getView().getLayoutParams();
        params.setMargins(0, 30, 0, 60);
        snackbar.getView().setLayoutParams(params);
        snackbar.show();
    }



    @Override
    public void hideKeyboard() {
        FragmentUtil.closeKeyboard(getActivity());
    }
}




