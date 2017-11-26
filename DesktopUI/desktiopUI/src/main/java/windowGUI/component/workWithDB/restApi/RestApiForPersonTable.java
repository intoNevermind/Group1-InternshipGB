package windowGUI.component.workWithDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.ArrayList;
/*
 * Интерфейс для описания запросов для получения(отправки) данных из таблицы Person, в REST-сервер
 * */
public interface RestApiForPersonTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("unauthorized/admin/ui/getAllPersons")
    Call<ArrayList<PojoPersons>> getListAllPersons();
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("unauthorized/admin/ui/addPerson")
    Call<ResponseBody> addPerson(@Query("name") String personName ,
                                @Query("active") boolean personActive);// добавляет личность и активность в БД

    @GET("unauthorized/admin/ui/delPerson")
    Call<ResponseBody> delPerson(@Query("id") int personID);// удаляет личность по ID из БД

    @GET("unauthorized/admin/ui/modifyPerson")
    Call<ResponseBody> modifyPerson(@Query("id") int personID,
                                    @Query("name") String personName ,
                                    @Query("active") boolean personActive);// редактирует имя личности и активность по ID личности(сам ID редоктировать нельзя)
/*
* </Отправка>
* */
}
