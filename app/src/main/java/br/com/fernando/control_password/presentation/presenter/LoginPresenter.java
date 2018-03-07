package br.com.fernando.control_password.presentation.presenter;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import br.com.fernando.control_password.api.ControlPasswordService;
import br.com.fernando.control_password.model.LoginRequest;
import br.com.fernando.control_password.model.ResponseApi;
import br.com.fernando.control_password.model.ResponseErrorApi;
import br.com.fernando.control_password.presentation.contract.LoginView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView> {


    private ControlPasswordService controlPasswordService;

    public LoginPresenter(ControlPasswordService controlPasswordService) {
        this.controlPasswordService = controlPasswordService;
    }

    public void loginUser(LoginRequest loginRequest) {
        getView().showProcessing();
        getView().hideKeyboard();
        getResponseService(loginRequest);
    }

    private void getResponseService(LoginRequest loginRequest) {
        controlPasswordService.loginUser(loginRequest).enqueue(new Callback<ResponseApi>() {

            @Override
            public void onResponse(Call<ResponseApi> call, retrofit2.Response<ResponseApi> response) {
                if (response.isSuccessful()) {
                    getView().hideProcessing();
                    getView().showSuccess(response.body());
                } else {
                    getView().hideProcessing();
                    handleFail(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                handleFail(null);
            }
        });

    }

    private void handleFail(ResponseBody responseBody) {

        getView().hideProcessing();

        if(responseBody != null) {
            Gson gson = new Gson();
            ResponseErrorApi responseErrorApi = gson.fromJson(responseBody.charStream(), ResponseErrorApi.class);
            getView().showError(responseErrorApi);
        } else {
            getView().showError(null );

        }

    }

}
