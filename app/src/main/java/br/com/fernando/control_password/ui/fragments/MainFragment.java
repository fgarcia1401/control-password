package br.com.fernando.control_password.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;

import javax.inject.Inject;

import br.com.fernando.control_password.R;
import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.app.ControlPasswordApplication;
import br.com.fernando.control_password.component.ControlPasswordComponent;
import br.com.fernando.control_password.data.DataStorage;
import br.com.fernando.control_password.data.database.AppDatabase;
import br.com.fernando.control_password.model.SiteRegister;
import br.com.fernando.control_password.ui.activities.MainActivity;
import br.com.fernando.control_password.ui.activities.RegisterActivity;
import br.com.fernando.control_password.ui.adapter.LineAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainFragment extends Fragment {

    @BindView(R.id.rv_sites)
    RecyclerView mRvSites;

    private LineAdapter mAdapter;

    private List<SiteRegister> mSites;

    private AppDatabase db;

    private ControlPasswordComponent component;

    @Inject
    ControlPasswordService controlPasswordService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
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

        component = ((ControlPasswordApplication) getActivity().getApplication()).getComponent();
        component.inject( this );

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        populate();
        setupRecycler();
    }

    @OnClick(R.id.add_site)
    public void addSite(){
        ((MainActivity) getActivity()).goNext(new RegisterSiteFragment());
    }

    private void setupRecycler() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), VERTICAL, false);

        mRvSites.setLayoutManager(layoutManager);

        mAdapter = new LineAdapter(mSites, controlPasswordService, getContext(), item -> {
            ((MainActivity) getActivity()).goNext(RegisterSiteFragment.newInstance(item));

        });
        mRvSites.setAdapter(mAdapter);

        mRvSites.addItemDecoration(
                new DividerItemDecoration(getActivity(), VERTICAL));
    }

    private void populate() {
        String email = DataStorage.getInstance(getContext()).getEmail();
        mSites = db.siteRegisterDao().findByEmail(email);
    }
    
}
