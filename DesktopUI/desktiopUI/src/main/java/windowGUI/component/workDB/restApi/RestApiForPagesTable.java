package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;
/*
* Интерфейс для описания запросов для получения данных из таблицы Pages, в REST-сервер
* */
public interface RestApiForPagesTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("unauthorized/user/ui/getIDFromPagesTable")
    Call<ArrayList<PojoPages>> getListIDFromPagesTable();

    @GET("unauthorized/user/ui/getSiteIDFromPagesTable")
    Call<ArrayList<PojoPages>> getListSiteIDFromPagesTable();

    @GET("unauthorized/user/ui/getFoundDateTimeFromPagesTable")
    Call<ArrayList<PojoPages>> getListFoundDateTimeFromPagesTable();
/*
* </Получение>
* */
}
