package br.com.fernando.control_password.api;

import br.com.fernando.control_password.model.LoginRequest;
import br.com.fernando.control_password.model.RegisterRequest;
import br.com.fernando.control_password.model.ResponseApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Fernando
 */

public interface ControlPasswordService {

    @POST("register")
    Call<ResponseApi> addUser(@Body RegisterRequest registerRequest);

    @POST("login")
    Call<ResponseApi> loginUser(@Body LoginRequest loginRequest);

    @GET
    Call<ResponseBody> getImage(@Header("authorization") String authorization, @Url String url);

}
