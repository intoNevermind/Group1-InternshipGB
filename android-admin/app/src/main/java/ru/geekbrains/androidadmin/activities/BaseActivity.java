package ru.geekbrains.androidadmin.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.geekbrains.androidadmin.App;
import ru.geekbrains.androidadmin.R;
import ru.geekbrains.androidadmin.model.Keyword;
import ru.geekbrains.androidadmin.model.Person;
import ru.geekbrains.androidadmin.model.Site;
import ru.geekbrains.androidadmin.model.User;
import ru.geekbrains.androidadmin.webapi.WebApi;

/**
 * Created by curly on 18.11.2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    WebApi api = App.getWebApi();

    protected void showProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            throw new RuntimeException("Сначала закрой диалог, потом показывай новый");
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Подождите");
        progressDialog.setMessage("Идет загрузка");
        progressDialog.show();
    }

    protected void closeProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    protected void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    protected void showErrorDialog(Throwable t) {
        showErrorDialog("Exception", String.format("%s\n%s", t.getClass().getSimpleName(), t.getMessage()));
    }

    public void showUserDialog(User user) {
    }

    public void showSiteDialog(Site site) {
    }

    public void showPersonDialog(Person person) {
    }

    public void showKeywordDialog(Keyword keyword) {
    }

    public void showPopupMenu(View view, final Object data) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_modify:
                        if (data instanceof User)
                            showUserDialog((User) data);
                        else if (data instanceof Site)
                            showSiteDialog((Site) data);
                        else if (data instanceof Person)
                            showPersonDialog((Person) data);
                        else if (data instanceof Keyword)
                            showKeywordDialog((Keyword) data);
                        return true;
                    case R.id.menu_item_delete:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Response<ResponseBody> response = null;
                                    String subject = null;
                                    Integer id = null;
                                    if (data instanceof User) {
                                        id = ((User) data).getId();
                                        response = api.delUser(id).execute();
                                        subject = "пользователя";
                                    } else if (data instanceof Site) {
                                        id = ((Site) data).getId();
                                        response = api.delSite(id).execute();
                                        subject = "сайта";
                                    } else if (data instanceof Person) {
                                        id = ((Person) data).getId();
                                        response = api.delPerson(id).execute();
                                        subject = "личности";
                                    } else if (data instanceof Keyword) {
                                        id = ((Keyword) data).getId();
                                        response = api.delKeyword(id).execute();
                                        subject = "ключевого слова";
                                    }
                                    if (response == null || !response.isSuccessful())
                                        throw new RuntimeException(String.format("Ошибка при удалении %s (id = %d)", subject, id));
                                    updateData();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    showErrorDialog(e);
                                }
                            }
                        }).start();
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    protected void updateData() {}
}
