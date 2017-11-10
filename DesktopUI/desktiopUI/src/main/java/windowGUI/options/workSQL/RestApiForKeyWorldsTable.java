package windowGUI.options.workSQL;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;

public interface RestApiForKeyWorldsTable {

    @GET("/user/ui/getIDFromKeyWorldsTable")
    Call<ArrayList<Integer>> getListIDFromKeyWorldsTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы KeyWorlds

    @GET("/user/ui/getNameFromKeyWorldsTable")
    Call<ArrayList<String>> getListNameFromKeyWorldsTable();//получить на выходе ArrayList<String>, содержащий все значения колонки Name из таблицы PKeyWorlds

    @GET("/user/ui/getPersonIDFromKeyWorldsTable")
    Call<ArrayList<Integer>> getListPersonIDFromKeyWorldsTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки PersonID из таблицы KeyWorlds

}
