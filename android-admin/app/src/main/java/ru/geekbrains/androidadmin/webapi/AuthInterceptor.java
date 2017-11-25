package ru.geekbrains.androidadmin.webapi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by curly on 22.11.2017.
 */

public class AuthInterceptor implements Interceptor {

    private String authToken;

    public AuthInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request()
                .newBuilder()
                .header(WebApi.X_CSRF_TOKEN, authToken)
                .build());
    }
}
