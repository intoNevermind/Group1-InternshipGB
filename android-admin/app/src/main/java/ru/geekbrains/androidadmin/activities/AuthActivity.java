package ru.geekbrains.androidadmin.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import org.jsoup.Jsoup;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.geekbrains.androidadmin.App;
import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.databinding.ActivityAuthBinding;
import ru.geekbrains.androidadmin.model.ErrorResponse;
import ru.geekbrains.androidadmin.webapi.WebApi;

/**
 * Created by curly on 25.11.2017.
 */

public class AuthActivity extends BaseActivity {

    private ActivityAuthBinding binding;
    private WebApi webApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        webApi = App.getWebApi();
        binding.etLogin.setText("PenF00k");
        binding.etPassword.setText("000000");

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response<ResponseBody> response = webApi.login().execute();
                            String token = Jsoup
                                    .parse(response.body().string())
                                    .body()
                                    .selectFirst("input[name=_csrf]")
                                    .attr("value");
                            App.setToken(token);
//                            do {
                                response = webApi.login(//token,
                                        binding.etLogin.getText().toString(),
                                        binding.etPassword.getText().toString()).execute();
                                if (!response.isSuccessful() && response.code() != 403)
                                    throw new RuntimeException("Не удалось авторизоваться: " + response.errorBody().string());
//                            } while (response.code() == 403);

                            startActivity(new Intent(AuthActivity.this, MainActivity.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                            showErrorDialog(e);
                        }
                    }
                }).start();
            }
        });
    }
}
