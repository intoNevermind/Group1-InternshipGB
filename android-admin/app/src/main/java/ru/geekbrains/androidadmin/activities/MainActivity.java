package ru.geekbrains.androidadmin.activities;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.adapters.MyViewPagerAdapter;
import ru.geekbrains.androidadmin.databinding.ActivityMainBinding;
import ru.geekbrains.androidadmin.databinding.DialogPersonBinding;
import ru.geekbrains.androidadmin.databinding.DialogSiteBinding;
import ru.geekbrains.androidadmin.databinding.DialogUserBinding;
import ru.geekbrains.androidadmin.fragments.BaseFragment;
import ru.geekbrains.androidadmin.model.Person;
import ru.geekbrains.androidadmin.model.Site;
import ru.geekbrains.androidadmin.model.User;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String LOG_TAG = "MainActivity";

    private ActivityMainBinding binding;

    private List<User> users = new ArrayList<>();
    private List<Site> sites = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();

    private BaseFragment<User> usersFragment;
    private BaseFragment<Site> sitesFragment;
    private BaseFragment<Person> personsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.swipeRefresh.setOnRefreshListener(this);

        MyViewPagerAdapter vpAdapter = new MyViewPagerAdapter(getSupportFragmentManager());

        usersFragment = BaseFragment.newInstance(users, new User(), this);
        sitesFragment = BaseFragment.newInstance(sites, new Site(),this);
        personsFragment = BaseFragment.newInstance(persons, new Person(),this);

        vpAdapter.addFragment(usersFragment, "Пользователи");
        vpAdapter.addFragment(sitesFragment, "Сайты");
        vpAdapter.addFragment(personsFragment, "Личности");

        binding.viewPager.setAdapter(vpAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        updateData();
    }

    @Override
    protected void updateData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefresh.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.swipeRefresh.setRefreshing(true);
                    }
                });
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<List<User>> usersResponse = api.getAllUsers().execute();
                    Response<List<Site>> sitesResponse = api.getAllSites().execute();
                    Response<List<Person>> personsResponse = api.getAllPersons().execute();
                    StringBuilder sb = new StringBuilder();
                    if (!usersResponse.isSuccessful()) sb.append("Ошибка при загрузке списка пользователей");
                    if (!sitesResponse.isSuccessful()) sb.append("\nОшибка при загрузке списка сайтов");
                    if (!personsResponse.isSuccessful()) sb.append("\nОшибка при загрузке списка личностей");
                    Log.d("LOGLOG", "usersResponse code = " + usersResponse.code());
                    Log.d("LOGLOG", "sitesResponse code = " + sitesResponse.code());
                    Log.d("LOGLOG", "personsResponse code = " + personsResponse.code());

                    if (sb.length() > 0) throw new RuntimeException(sb.toString());
                    users.clear();
                    sites.clear();
                    persons.clear();
                    List<User> newUsers = usersResponse.body();
                    List<Site> newSites = sitesResponse.body();
                    List<Person> newPersons = personsResponse.body();
                    if (newUsers != null) users.addAll(newUsers);
                    if (newSites != null) sites.addAll(newSites);
                    if (newPersons != null) persons.addAll(newPersons);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            usersFragment.getRvAdapter().notifyDataSetChanged();
                            sitesFragment.getRvAdapter().notifyDataSetChanged();
                            personsFragment.getRvAdapter().notifyDataSetChanged();
                            binding.swipeRefresh.setRefreshing(false);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    showErrorDialog(e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.swipeRefresh.setRefreshing(false);
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        updateData();
    }

    @Override
    public void showUserDialog(final User user) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setTitle(user == null ? "Добавить пользователя" : "Редактировать пользователя");
        final DialogUserBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(this), R.layout.dialog_user, null, false);
        dialog.setContentView(binding.getRoot());

        if (user != null) {
            binding.etUsername.setText(user.getLogin());
            binding.cbAdmin.setChecked(user.getAdmin());
            binding.cbActive.setChecked(user.getActive());
            binding.btnOk.setText(R.string.save);
        }
        else {
            binding.btnOk.setText(R.string.add);
        }
        binding.btnCancel.setText(R.string.cancel);

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etUsername.getText())) {
                    binding.etUsername.setError("Введите имя");
                    binding.etUsername.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(binding.etPassword.getText())){
                    binding.etPassword.setError("Введите пароль");
                    binding.etPassword.requestFocus();
                    return;
                }

                if (user == null) {
                    api.addUser(binding.etUsername.getText().toString(),
                            binding.etPassword.getText().toString(),
                            binding.cbAdmin.isChecked(),
                            binding.cbActive.isChecked()).enqueue(new Callback<ResponseBody>() {
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
                    api.modifyUser(user.getId(),
                            binding.etUsername.getText().toString(),
                            binding.etPassword.getText().toString(),
                            binding.cbAdmin.isChecked(),
                            binding.cbActive.isChecked()).enqueue(new Callback<ResponseBody>() {
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

    @Override
    public void showSiteDialog(final Site site) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setTitle(site == null ? "Добавить сайт" : "Редактировать сайт");
        final DialogSiteBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(this), R.layout.dialog_site, null, false);
        dialog.setContentView(binding.getRoot());

        if (site != null) {
            binding.etName.setText(site.getName());
            binding.etUrl.setText(site.getUrl());
            binding.cbActive.setChecked(site.getActive());
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
                    binding.etName.setError("Введите название");
                    binding.etName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(binding.etUrl.getText())){
                    binding.etUrl.setError("Введите адрес");
                    binding.etUrl.requestFocus();
                    return;
                }

                if (site == null) {
                    api.addSite(binding.etName.getText().toString(),
                            binding.etUrl.getText().toString(),
                            binding.cbActive.isChecked()).enqueue(new Callback<ResponseBody>() {
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
                    api.modifySite(site.getId(),
                            binding.etName.getText().toString(),
                            binding.etUrl.getText().toString(),
                            binding.cbActive.isChecked()).enqueue(new Callback<ResponseBody>() {
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

    @Override
    public void showPersonDialog(final Person person) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setTitle(person == null ? "Добавить личность" : "Редактировать личность");
        final DialogPersonBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(this), R.layout.dialog_person, null, false);
        dialog.setContentView(binding.getRoot());

        if (person != null) {
            binding.etName.setText(person.getName());
            binding.cbActive.setChecked(person.getActive());
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
                    binding.etName.setError("Введите название");
                    binding.etName.requestFocus();
                    return;
                }

                if (person == null) {
                    api.addPerson(binding.etName.getText().toString(),
                            binding.cbActive.isChecked()).enqueue(new Callback<ResponseBody>() {
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
                    api.modifyPerson(person.getId(),
                            binding.etName.getText().toString(),
                            binding.cbActive.isChecked()).enqueue(new Callback<ResponseBody>() {
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
