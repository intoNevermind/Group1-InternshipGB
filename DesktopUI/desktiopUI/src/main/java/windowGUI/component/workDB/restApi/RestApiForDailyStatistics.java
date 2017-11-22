package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.ArrayList;
/*
 * Интерфейс для описания запросов для получения данных из таблицы DailyStatistics, в REST-сервер
 * */
public interface RestApiForDailyStatistics {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("unauthorized/user/ui/getDailyStatistics")
    Call<ArrayList<PojoDailyStatistics>> getListDailyStatistics(@Query("siteID") int siteID,
                                                                @Query("datefrom") String datefrom,
                                                                @Query("dateto") String dateto);
/*
* </Получение>
* */

}
