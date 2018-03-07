package br.com.fernando.control_password.module;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import br.com.fernando.control_password.BuildConfig;
import br.com.fernando.control_password.api.ControlPasswordService;
import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tqiuser on 19/02/18.
 */

@Module
public class ControlPasswordModule {

    private Application app;

    public ControlPasswordModule(Application app) {
        this.app = app;
    }

    @Provides
    public ControlPasswordService getControlPasswordService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_CONTROL_PASSWORD_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ControlPasswordService controlPasswordService = retrofit.create(ControlPasswordService.class);

        return controlPasswordService;
    }

    private okhttp3.OkHttpClient getOkHttpClient() {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }


}
