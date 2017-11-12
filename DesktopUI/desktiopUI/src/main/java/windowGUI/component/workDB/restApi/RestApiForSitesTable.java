package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.ArrayList;

public interface RestApiForSitesTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("/user/ui/getIDFromSitesTable")
    Call<ArrayList<Integer>> getListIDFromSitesTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы Sites

    @GET("/user/ui/getNameFromSitesTable")
    Call<ArrayList<String>> getListNameFromSitesTable();//получить на выходе ArrayList<String>, содержащий все значения колонки Name из таблицы Sites

    @GET("/user/ui/getURLFromSitesTable")
    Call<ArrayList<String>> getListURLFromSitesTable();//получить на выходе ArrayList<String>, содержащий все значения колонки URL из таблицы Sites

    @GET("/user/ui/getActiveFromSitesTable")
    Call<ArrayList<Integer>> getListActiveFromSitesTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки Active из таблицы Sites
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("/admin/ui/addSite")
    Call<ResponseBody> addSite(@Query("SiteName") String siteName ,
                               @Query("SiteURL") String siteUrl,
                               @Query("siteActive") boolean siteActive);// добавляет сайт, URL и активность в БД

    @GET("/admin/ui/delSite")
    Call<ResponseBody> delSite(@Query("SiteID") int siteID);// удаляет сайт по ID из БД

    @GET("/admin/ui/modifySite")
    Call<ResponseBody> modifySite(@Query("SiteID") int siteID,
                                  @Query("SiteName") String siteName ,
                                  @Query("SiteURL") String siteUrl,
                                  @Query("siteActive") boolean siteActive);// редактирует имя сайта его URL и активность по ID  сайта(сам ID редоктировать нельзя)
/*
* </Отправка>
*/
}
