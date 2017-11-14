package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;

public interface RestApiForPersonPageRankTable {

    @GET("/user/ui/getPersonIDFromPersonPageRankTable")
    Call<ArrayList<Integer>> getListPersonIDFromPersonPageRankTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки PersonID из таблицы PersonPageRank

    @GET("/user/ui/getPageIDFromPersonPageRankTable")
    Call<ArrayList<Integer>> getListPageIDFromPersonPageRankTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки PageID из таблицы PersonPageRank

    @GET("/user/ui/getRankFromPersonPageRankTable")
    Call<ArrayList<Integer>> getListRankFromPersonPageRankTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки Rank из таблицы PersonPageRank
}
