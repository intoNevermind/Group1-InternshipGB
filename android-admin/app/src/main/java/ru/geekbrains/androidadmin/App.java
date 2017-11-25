package ru.geekbrains.androidadmin;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.androidadmin.webapi.TokenInterceptor;
import ru.geekbrains.androidadmin.webapi.WebApi;

/**
 * Created by curly on 18.11.2017.
 */

public class App extends Application {

    private static WebApi webApi;
    private static volatile String token;

    @Override
    public void onCreate() {
        super.onCreate();

        TokenInterceptor tokenInterceptor = new TokenInterceptor(this);

        OkHttpClient.Builder httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(tokenInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://54.154.158.193/unauthorized/")
                .baseUrl("http://54.154.158.193/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        webApi = retrofit.create(WebApi.class);
    }

    public static WebApi getWebApi() {
        return webApi;
    }

    public static String getToken() {
        return token;
    }

    public static synchronized void setToken(String token) {
        App.token = token;
    }
}
