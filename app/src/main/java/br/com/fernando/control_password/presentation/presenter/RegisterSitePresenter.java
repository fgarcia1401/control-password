package br.com.fernando.control_password.presentation.presenter;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import br.com.fernando.control_password.data.database.AppDatabase;
import br.com.fernando.control_password.model.SiteRegister;
import br.com.fernando.control_password.presentation.contract.RegisterSiteView;


public class RegisterSitePresenter extends MvpNullObjectBasePresenter<RegisterSiteView> {

    private AppDatabase db;

    public RegisterSitePresenter(AppDatabase db) {
        this.db = db;
    }

    public void addSite(SiteRegister siteRegister) {
        getView().hideKeyboard();
        save(siteRegister);
    }

    public void updateSite(SiteRegister siteRegister) {
        getView().hideKeyboard();
        update(siteRegister);
    }

    public void deleteSite(Integer id) {
        getView().hideKeyboard();
        db.siteRegisterDao().deleteById(id);
    }

    private void save(SiteRegister siteRegister) {
        long insert = db.siteRegisterDao().insert(siteRegister);

        if(insert != 0) {
            getView().showSuccess();
        } else {
            getView().showError("Falha ao salvar site, tente novamente mais tarde!");
        }

    }

    private void update(SiteRegister siteRegister) {
        int update = db.siteRegisterDao().update(siteRegister);

        if(update != 0) {
            getView().showSuccess();
        } else {
            getView().showError("Falha ao atualizar site, tente novamente mais tarde!");
        }

    }



}
