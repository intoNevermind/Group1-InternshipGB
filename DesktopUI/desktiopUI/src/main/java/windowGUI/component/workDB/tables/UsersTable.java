package windowGUI.component.workDB.tables;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import windowGUI.component.workDB.restApi.PojoUsers;
import windowGUI.component.workDB.restApi.RestApiForUsersTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-таблица, отвечающий за получение(отправку) данных из таблицы Users, в REST-сервер
 * */
public class UsersTable extends ConnectServer{
    private static final RestApiForUsersTable REST_API_FOR_USERS_TABLE = getRetrofit().create(RestApiForUsersTable.class);
    private static final ArrayList<String> LIST_LOGIN = new ArrayList<>();
    private static final ArrayList<String> LIST_PASSWORD = new ArrayList<>();
    private static final ArrayList<Boolean> LIST_ACTIVE = new ArrayList<>();
    private static final LinkedHashMap<String,Boolean> LIST_LOGIN_AND_ACTIVE = new LinkedHashMap<>();
    private static final LinkedHashMap<String,String> LIST_LOGIN_AND_PASSWORD = new LinkedHashMap<>();

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    public static void infoAllUsers(){
        LIST_LOGIN.clear();
        LIST_PASSWORD.clear();
        LIST_ACTIVE.clear();
        try {
            Response<ArrayList<PojoUsers>> response = REST_API_FOR_USERS_TABLE.getListAllUsers().execute();
            ArrayList<PojoUsers> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_LOGIN.add(list.get(i).getLogin());
                LIST_PASSWORD.add(LIST_LOGIN.get(i));
                LIST_ACTIVE.add(list.get(i).getActive());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Получение>
     * */

    /*
     * <Отправка>
     * запросы с помощью которых, можно отправить данные в БД
     * */

    /*
     * метод, отвечающий за авторизацию, запрос не работает(REST-сервер еще не написал запрос)
     * */
    private static boolean b = false;
    public static boolean authorized(String userLogin, String userPassword){

            REST_API_FOR_USERS_TABLE.authorized(userLogin, userPassword).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    System.out.println(response.raw());
                    if(response.isSuccessful()){
                        b = true;
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
            return b;
    }

    /*
     * метод, добавляющий пользователя
     * */
    public static void addUser(String userLogin , boolean userAdmin, String userPassword, boolean userActive){
        try {
            Response<ResponseBody> response = REST_API_FOR_USERS_TABLE.addUser(userLogin, userAdmin,userPassword, userActive).execute();
            System.out.println(response.raw());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Отправка>
     * */

    /*
     * метод, возвращающий связанный спискок логин и активность пользователя
     * */
    public static LinkedHashMap<String, Boolean> getListLoginAndActive() {
        for (int i = 0; i < getListLogin().size(); i++) {
            LIST_LOGIN_AND_ACTIVE.put(getListLogin().get(i), getListActive().get(i));
        }
        return LIST_LOGIN_AND_ACTIVE;
    }

    /*
     * метод, возвращающий связанный спискок логин и пароль пользователя
     * */
    public static LinkedHashMap<String, String> getListLoginAndPassword() {
        for (int i = 0; i < getListLogin().size(); i++) {
            LIST_LOGIN_AND_PASSWORD.put(getListLogin().get(i), getListPassword().get(i));
        }
        return LIST_LOGIN_AND_PASSWORD;
    }

    /*
     * <getters>
     * */
    public static ArrayList<String> getListLogin(){
        return LIST_LOGIN;
    }
    private static ArrayList<String> getListPassword(){
        return LIST_PASSWORD;
    }
    private static ArrayList<Boolean> getListActive(){
        return LIST_ACTIVE;
    }
    /*
     * </getters>
     * */
}
