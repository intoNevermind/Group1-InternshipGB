package ru.geekbrains.androidadmin.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.jsoup.Jsoup;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.geekbrains.androidadmin.App;
import ru.geekbrains.androidadmin.activities.AuthActivity;

/**
 * Created by curly on 22.11.2017.
 */

public class TokenInterceptor implements Interceptor {
    private final Context context;
    private String token;

    public TokenInterceptor(Context context) {
        this.context = context;
        this.token = App.getToken();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request modifiedRequest = request;

        if (token != null)
            modifiedRequest = request.newBuilder()
                    .addHeader(WebApi.X_CSRF_TOKEN, token)
                    .build();
        Response response = chain.proceed(modifiedRequest);

        // Если в ответ прилетел html документ, то парсим его и запоминаем токен
        if (("text/html;charset=UTF-8").equals(response.header("Content-Type"))) {
            String token = Jsoup
                    .parse(response.body().string())
                    .body()
                    .selectFirst("input[name=_csrf]")
                    .attr("value");
            App.setToken(token);
            startAuthActivity();
            modifiedRequest.newBuilder().header("Content-Type", "application/json;charset=UTF-8");
            response = chain.proceed(modifiedRequest);
        }
        return response;
    }

    private void startAuthActivity() {
        context.startActivity(new Intent(context, AuthActivity.class));
    }
}
