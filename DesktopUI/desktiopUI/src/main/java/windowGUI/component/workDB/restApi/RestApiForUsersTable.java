package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface RestApiForUsersTable {
    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */
    @GET("/user/ui/getAllUsers")
    Call<LinkedHashMap<String,String>> getLoginAndPasswordUsersTable();//получить на выходе LinkedHashMap<String,String>, содержащий все значения колонки Login и Password из таблицы Users

    /*
     * </Получение>
     * */

    /*
     * <Отправка>
     * запросы с помощью которых, можно отправить данные в БД
     * */

    /*
     * </Отправка>
     * */
}
