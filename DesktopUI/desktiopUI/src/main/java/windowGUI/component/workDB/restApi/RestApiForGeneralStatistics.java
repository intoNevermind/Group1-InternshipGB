package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.ArrayList;

public interface RestApiForGeneralStatistics {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("unauthorized/user/ui/getGeneralStatistics")
    Call<ArrayList<PojoGeneralStatistics>> getListGeneralStatistics(@Query("siteID") int siteID);
/*
* </Получение>
* */

}
