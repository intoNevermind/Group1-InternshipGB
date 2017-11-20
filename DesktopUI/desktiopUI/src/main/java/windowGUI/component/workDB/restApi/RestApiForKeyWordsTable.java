package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.ArrayList;
/*
 * Интерфейс для описания запросов для получения(отправки) данных из таблицы KeyWords, в REST-сервер
 * */
public interface RestApiForKeyWordsTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("unauthorized/admin/ui/getAllKeywords")
    Call<ArrayList<PojoKeyWords>> getListAllKeyWords();
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("unauthorized/admin/ui/addKeyword")
    Call<ResponseBody> addKeyWord(@Query("name") String keyWordName ,
                                  @Query("personId") int personID);

    @GET("unauthorized/admin/ui/delKeyword")
    Call<ResponseBody> delKeyWord(@Query("id") int keyWordID);

    @GET("unauthorized/admin/ui/modifyKeyword")
    Call<ResponseBody> modifyKeyWord(@Query("id") int keyWordID,
                                     @Query("name") String keyWordName ,
                                     @Query("personId") int personID);
/*
* </Отправка>
* */
}
