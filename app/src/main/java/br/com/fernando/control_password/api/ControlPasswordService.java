package br.com.fernando.control_password.api;

import br.com.fernando.control_password.model.LoginRequest;
import br.com.fernando.control_password.model.RegisterRequest;
import br.com.fernando.control_password.model.ResponseApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tqiuser on 19/02/18.
 */

public interface ControlPasswordService {

    @POST("register")
    Call<ResponseApi> addUser(@Body RegisterRequest registerRequest);

    @POST("login")
    Call<ResponseApi> loginUser(@Body LoginRequest loginRequest);

}
