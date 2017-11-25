package ru.geekbrains.androidadmin.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

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
                try {
//                    Response<ResponseBody> response = webApi.login(token,
//                            binding.etLogin.getText().toString(),
//                            binding.etPassword.getText().toString()).execute();
//                    if (!response.isSuccessful()) throw new RuntimeException("Не удалось авторизоваться");

                    webApi.login(//token,
                            binding.etLogin.getText().toString(),
                            binding.etPassword.getText().toString()).enqueue(new Callback<ErrorResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<ErrorResponse> call, @NonNull Response<ErrorResponse> response) {
                            if (!response.isSuccessful() && response.code() != 403)
//                                throw new RuntimeException("Не удалось авторизоваться, code = " + response.code());
                                try {
                                    throw new RuntimeException(String.format("Не удалось авторизоваться, code: %d, body: %s",
                                            response.code(), response.errorBody().string()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            finish();
                        }

                        @Override
                        public void onFailure(@NonNull Call<ErrorResponse> call, @NonNull Throwable t) {
                            if (t instanceof IllegalStateException) finish();
                            else showErrorDialog(t);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    showErrorDialog(e);
                }
            }
        });
    }
}
