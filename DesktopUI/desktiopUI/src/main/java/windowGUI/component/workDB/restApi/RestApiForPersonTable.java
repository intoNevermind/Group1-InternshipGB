package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.ArrayList;

public interface RestApiForPersonTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("/user/ui/getIDFromPersonTable")
    Call<ArrayList<Integer>> getListIDFromPersonTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы Persons

    @GET("/user/ui/getNameFromPersonTable")
    Call<ArrayList<String>> getListNameFromPersonTable();//получить на выходе ArrayList<String>, содержащий все значения колонки Name из таблицы Persons

    @GET("/user/ui/getActiveFromPersonTable")
    Call<ArrayList<Integer>> getListActiveFromPersonTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки Active из таблицы Persons
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("/admin/ui/addPerson")
    Call<ResponseBody> addPerson(@Query("PersonName") String personName ,
                                 @Query("personActive") boolean personActive);// добавляет личность и активность в БД

    @GET("/admin/ui/delPerson")
    Call<ResponseBody> delPerson(@Query("PersonID") int personID);// удаляет личность по ID из БД

    @GET("/admin/ui/modifyPerson")
    Call<ResponseBody> modifyPerson(@Query("PersonID") int personID,
                                    @Query("PersonName") String personName ,
                                    @Query("personActive") boolean personActive);// редактирует имя личности и активность по ID личности(сам ID редоктировать нельзя)
/*
* </Отправка>
* */
}
