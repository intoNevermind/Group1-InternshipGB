package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.ArrayList;
/*
 * Интерфейс для описания запросов для получения данных из таблицы PersonPageRank, в REST-сервер
 * */
public interface RestApiForPersonPageRankTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("unauthorized/user/ui/getPersonPageRankByPersonId")
    Call<ArrayList<PojoPersonPageRank>> getPersonPageRankByPersonId(@Query("id") int id);
/*
* </Получение>
* */
}
