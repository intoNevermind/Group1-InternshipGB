package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.ArrayList;
/*
 * Интерфейс для описания запросов для получения(отправки) данных из таблицы Sites, в REST-сервер
 * */
public interface RestApiForSitesTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("unauthorized/admin/ui/getAllSites")
    Call<ArrayList<PojoSites>> getListAllSites();
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("unauthorized/admin/ui/addSite")
    Call<ResponseBody> addSite(@Query("name") String siteName ,
                               @Query("url") String siteUrl,
                               @Query("active") boolean siteActive);// добавляет сайт, URL и активность в БД

    @GET("unauthorized/admin/ui/delSite")
    Call<ResponseBody> delSite(@Query("id") int siteID);// удаляет сайт по ID из БД

    @GET("unauthorized/admin/ui/modifySite")
    Call<ResponseBody> modifySite(@Query("id") int siteID,
                                  @Query("name") String siteName ,
                                  @Query("url") String siteUrl,
                                  @Query("active") boolean siteActive);// редактирует имя сайта его URL и активность по ID  сайта(сам ID редоктировать нельзя)
/*
* </Отправка>
*/
}
