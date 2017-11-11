package windowGUI.component.workDB.RestApi;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;

public interface RestApiForSitesTable {

    @GET("/user/ui/getIDFromSitesTable")
    Call<ArrayList<Integer>> getListIDFromSitesTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы Sites

    @GET("/user/ui/getNameFromSitesTable")
    Call<ArrayList<String>> getListNameFromSitesTable();//получить на выходе ArrayList<String>, содержащий все значения колонки Name из таблицы Sites

    @GET("/user/ui/getURLFromSitesTable")
    Call<ArrayList<String>> getListURLFromSitesTable();//получить на выходе ArrayList<String>, содержащий все значения колонки URL из таблицы Sites

    @GET("/user/ui/getActiveFromSitesTable")
    Call<ArrayList<Integer>> getListActiveFromSitesTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки Active из таблицы Sites
}
