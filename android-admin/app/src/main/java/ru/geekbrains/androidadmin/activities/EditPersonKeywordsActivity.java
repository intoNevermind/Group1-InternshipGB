package ru.geekbrains.androidadmin.activities;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.geekbrains.androidadmin.App;
import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.adapters.MyRecyclerViewAdapter;
import ru.geekbrains.androidadmin.databinding.ActivityEditPersonKeywordsBinding;
import ru.geekbrains.androidadmin.databinding.DialogKeywordBinding;
import ru.geekbrains.androidadmin.databinding.DialogPersonBinding;
import ru.geekbrains.androidadmin.fragments.BaseFragment;
import ru.geekbrains.androidadmin.model.Keyword;

/**
 * Created by curly on 19.11.2017.
 */

public class EditPersonKeywordsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String LOG_TAG = "MainActivity";

    private ActivityEditPersonKeywordsBinding binding;

    private List<Keyword> keywords = new ArrayList<>();
    private BaseFragment<Keyword> keywordsFragment;

    private Integer personId;
    private String personName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        personId = intent.getIntExtra(MyRecyclerViewAdapter.EXTRA_PERSON_ID, -1);
        personName = intent.getStringExtra(MyRecyclerViewAdapter.EXTRA_PERSON_NAME);
        if (personId == -1) {
            showErrorDialog(new RuntimeException("В интенте не пришел personId"));
            finish();
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_person_keywords);
        binding.swipeRefresh.setOnRefreshListener(this);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(String.format("Кл. слова для \"%s\"", personName));
        actionBar.setDisplayHomeAsUpEnabled(true);

        keywordsFragment = BaseFragment.newInstance(keywords, new Keyword(),this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, keywordsFragment)
                .commit();
        updateData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void updateData() {
        binding.swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefresh.setRefreshing(true);
            }
        });

        App.getWebApi().getAllKeywords().enqueue(new Callback<List<Keyword>>() {
            @Override
            public void onResponse(@NonNull Call<List<Keyword>> call, @NonNull Response<List<Keyword>> response) {
                keywords.clear();
                List<Keyword> newKeywords = response.body();
                List<Keyword> filteredKeywords = new ArrayList<>();
                if (newKeywords == null) throw new RuntimeException("От сервера вместо списка ключевых слов пришел null");
                for (int i = 0; i < newKeywords.size(); i++) {
                    Keyword keyword = newKeywords.get(i);
                    if (keyword.getPersonId().equals(personId)) filteredKeywords.add(keyword);
                }
                keywords.addAll(filteredKeywords);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        keywordsFragment.getRvAdapter().notifyDataSetChanged();
                        binding.swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Keyword>> call, @NonNull Throwable t) {
                showErrorDialog(t);
            }
        });
    }

    @Override
    public void onRefresh() {
        updateData();
    }

    @Override
    public void showKeywordDialog(final Keyword keyword) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setTitle(keyword == null ? "Добавить ключевое слово" : "Редактировать ключевое слово");
        final DialogKeywordBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(this), R.layout.dialog_keyword, null, false);
        dialog.setContentView(binding.getRoot());

        if (keyword != null) {
            binding.etName.setText(keyword.getName());
            binding.btnOk.setText(R.string.save);
        }
        else {
            binding.btnOk.setText(R.string.add);
        }
        binding.btnCancel.setText(R.string.cancel);

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etName.getText())) {
                    binding.etName.setError("Введите ключевое слово");
                    binding.etName.requestFocus();
                    return;
                }

                if (keyword == null) {
                    api.addKeyword(binding.etName.getText().toString(), personId).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            updateData();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                            showErrorDialog(t);
                        }
                    });
                } else {
                    api.modifyKeyword(keyword.getId(),
                            binding.etName.getText().toString(),
                            personId).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            updateData();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                            showErrorDialog(t);
                        }
                    });
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
