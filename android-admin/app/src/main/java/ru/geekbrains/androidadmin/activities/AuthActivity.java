package ru.geekbrains.androidadmin.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.databinding.ActivityAuthBinding;

/**
 * Created by curly on 25.11.2017.
 */

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etLogin.getText())) {
                    binding.etLogin.setError("Введите логин");
                    binding.etLogin.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(binding.etPassword.getText())) {
                    binding.etPassword.setError("Введите пароль");
                    binding.etPassword.requestFocus();
                    return;
                }
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
            }
        });
    }
}
