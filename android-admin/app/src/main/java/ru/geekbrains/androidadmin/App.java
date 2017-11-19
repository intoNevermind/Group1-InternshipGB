package ru.geekbrains.androidadmin;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.androidadmin.webapi.WebApi;

/**
 * Created by curly on 18.11.2017.
 */

public class App extends Application {

    private static WebApi webApi;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.154.158.193/unauthorized/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webApi = retrofit.create(WebApi.class);
    }

    public static WebApi getWebApi() {
        return webApi;
    }
}
