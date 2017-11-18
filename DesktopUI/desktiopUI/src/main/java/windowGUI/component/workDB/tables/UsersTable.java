package windowGUI.component.workDB.tables;

import okhttp3.ResponseBody;
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

    private static UsersTable instance;

    public static UsersTable getInstance() {
        if(instance == null){
            instance = new UsersTable();
        }
        return instance;
    }

    private UsersTable() {
        infoAllUsers();
    }

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    private void infoAllUsers(){
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
     * метод, отвечающий за авторизацию
     * */
    public boolean authorized(String userLogin, String userPassword){
        try {
            Response<ResponseBody> response = REST_API_FOR_USERS_TABLE.authorized(userLogin, userPassword).execute();
            System.out.println(response.raw());
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * метод, добавляющий пользователя
     * */
    public void addUser(String userLogin , boolean userAdmin, String userPassword, boolean userActive){
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
    public LinkedHashMap<String, Boolean> getListLoginAndActive() {
        for (int i = 0; i < getListLogin().size(); i++) {
            LIST_LOGIN_AND_ACTIVE.put(getListLogin().get(i), getListActive().get(i));
        }
        return LIST_LOGIN_AND_ACTIVE;
    }

    /*
     * метод, возвращающий связанный спискок логин и пароль пользователя
     * */
    public LinkedHashMap<String, String> getListLoginAndPassword() {
        for (int i = 0; i < getListLogin().size(); i++) {
            LIST_LOGIN_AND_PASSWORD.put(getListLogin().get(i), getListPassword().get(i));
        }
        return LIST_LOGIN_AND_PASSWORD;
    }

    /*
     * <getters>
     * */
    public ArrayList<String> getListLogin(){
        return LIST_LOGIN;
    }
    public ArrayList<String> getListPassword(){
        return LIST_PASSWORD;
    }
    private ArrayList<Boolean> getListActive(){
        return LIST_ACTIVE;
    }
    /*
     * </getters>
     * */
}
