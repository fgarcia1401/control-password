package br.com.fernando.control_password.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.app.ControlPasswordApplication;
import br.com.fernando.control_password.component.ControlPasswordComponent;
import br.com.fernando.control_password.data.DataStorage;
import br.com.fernando.control_password.data.database.AppDatabase;
import br.com.fernando.control_password.model.SiteRegister;
import br.com.fernando.control_password.ui.activities.LoginActivity;
import br.com.fernando.control_password.ui.activities.MainActivity;
import br.com.fernando.control_password.ui.adapter.LineAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class PerfilFragment extends Fragment {

    @BindView(R.id.tv_email)
    TextView mTvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        ButterKnife.bind(this, view);

        String email = DataStorage.getInstance(getContext()).getEmail();
        mTvEmail.setText(email);

    }

    @OnClick(R.id.bt_logout)
    public void logout() {
        callLogin();
    }

    private void callLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
    
}
