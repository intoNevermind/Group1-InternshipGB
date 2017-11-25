package windowGUI.component.workWithDB.tables;

import windowGUI.component.workWithDB.restApi.PojoUsers;
import windowGUI.component.workWithDB.restApi.RestApiForUsersTable;

import okhttp3.ResponseBody;
import retrofit2.Response;
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
    private static final LinkedHashMap<String,String> LIST_LOGIN_AND_PASSWORD = new LinkedHashMap<>();

    private static boolean authorized = false;

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
        LIST_LOGIN_AND_PASSWORD.clear();

        try {
            Response<ArrayList<PojoUsers>> response = REST_API_FOR_USERS_TABLE.getListAllUsers().execute();

            ArrayList<PojoUsers> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_LOGIN.add(list.get(i).getLogin());
                LIST_PASSWORD.add(LIST_LOGIN.get(i));//фэйк
                LIST_LOGIN_AND_PASSWORD.put(LIST_LOGIN.get(i), LIST_PASSWORD.get(i));
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
     * метод, отвечающий за авторизацию(фэйковая авторизация для имитации реальных действий)
     * */
    public static boolean isAuthorized(String nameUser, String passwordUser){
        if (nameUser == null || passwordUser == null) return false;

        try {
            Response<ResponseBody> response = REST_API_FOR_USERS_TABLE.authorized().execute();

            for (int i = 0; i <LIST_LOGIN.size(); i++) {
                if(response.isSuccessful() && nameUser.equals(LIST_LOGIN.get(i))
                        && passwordUser.equals( LIST_LOGIN_AND_PASSWORD.get(nameUser))){
                    authorized = true;
                }
            }
            return authorized;
        } catch (IOException e) {
            e.printStackTrace();
            return authorized;
        }
    }

    /*
     * метод, добавляющий пользователя
     * */
    public static void addUser(String userLogin , boolean userAdmin, String userPassword, boolean userActive){
        try {
            Response<ResponseBody> response = REST_API_FOR_USERS_TABLE.addUser(userLogin, userAdmin,userPassword, userActive).execute();

            if (response.isSuccessful())response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Отправка>
     * */

    /*
     * <getters>
     * */
    public static ArrayList<String> getListLogin(){
        return LIST_LOGIN;
    }
    /*
     * </getters>
     * */
}
