package windowGUI.component.workWithDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import windowGUI.component.workWithDB.restApi.pojo.PojoGeneralStatistics;

import java.util.ArrayList;

public interface QueriesForGeneralStatistics {
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
