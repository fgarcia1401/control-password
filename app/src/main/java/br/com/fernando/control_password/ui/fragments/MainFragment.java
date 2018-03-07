package br.com.fernando.control_password.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import br.com.fernando.control_password.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainFragment extends Fragment {

    @BindView(R.id.rv_sites)
    RecyclerView mRvSites;

    //private LineMenuAdapter mAdapter;

    private List<String> menuModels;

    //private LastTransactionTask mTask = null;

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getContext());

        populate();
        setupRecycler();
    }

    private void setupRecycler() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), VERTICAL, false);

        /*mRvMenu.setLayoutManager(layoutManager);

        mAdapter = new LineMenuAdapter(menuModels, item -> {
            redirect(item);
        });
        mRvMenu.setAdapter(mAdapter);

        mRvMenu.addItemDecoration(
                new DividerItemDecoration(getActivity(), VERTICAL)); */
    }

    private void populate() {
        //menuModels = Arrays.asList(getResources().getStringArray(R.array.menu));
    }
    
}
