package br.com.fernando.control_password.presentation.presenter;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.model.RegisterRequest;
import br.com.fernando.control_password.model.ResponseApi;
import br.com.fernando.control_password.presentation.contract.RegisteredPasswordView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PasswordPresenter extends MvpNullObjectBasePresenter<RegisteredPasswordView> {


    public RegisterRequest registerRequest;

    private ControlPasswordService controlPasswordService;

    public PasswordPresenter(ControlPasswordService controlPasswordService) {
        registerRequest = new RegisterRequest();
        this.controlPasswordService = controlPasswordService;
    }

    public void registerAccount() {
        getView().showProcessing();
        getView().hideKeyboard();
        getResponseService();
    }

    private void getResponseService() {
        controlPasswordService.addUser(registerRequest).enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                if (response.isSuccessful()) {
                    getView().hideProcessing();
                    getView().showSuccess(response.body());
                } else {
                    getView().hideProcessing();
                    handleFail();
                }
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                handleFail();
            }
        });

    }

    private void handleFail() {
        getView().hideProcessing();
        getView().showError();
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void setRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }
}
