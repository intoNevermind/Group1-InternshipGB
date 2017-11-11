package windowGUI.component.workDB.RestApi;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;
import java.util.Date;

public interface RestApiForPagesTable {

    @GET("/user/ui/getIDFromPagesTable")
    Call<ArrayList<Integer>> getListIDFromPagesTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы Pages

    @GET("/user/ui/getURLFromPagesTable")
    Call<ArrayList<String>> getListURLFromPagesTable();//получить на выходе ArrayList<String>, содержащий все значения колонки URL из таблицы Pages

    @GET("/user/ui/getSiteIDFromPagesTable")
    Call<ArrayList<Integer>> getListSiteIDFromPagesTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки SiteID из таблицы Pages

    @GET("/user/ui/getFoundDateTimeFromPagesTable")
    Call<ArrayList<Date>> getListFoundDateTimeFromPagesTable();//получить на выходе ArrayList<Date>, содержащий все значения колонки FoundDateTime из таблицы Pages

    @GET("/user/ui/getLastScanDateFromPagesTable")
    Call<ArrayList<Date>> getListLastScanDateFromPagesTable();//получить на выходе ArrayList<Date>, содержащий все значения колонки LastScanDate из таблицы Pages

}
