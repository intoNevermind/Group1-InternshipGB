package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;

public interface RestApiForKeyWordsTable {

    @GET("/user/ui/getIDFromKeyWordsTable")
    Call<ArrayList<Integer>> getListIDFromKeyWordsTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы KeyWorlds

    @GET("/user/ui/getNameFromKeyWordsTable")
    Call<ArrayList<String>> getListNameFromKeyWordsTable();//получить на выходе ArrayList<String>, содержащий все значения колонки Name из таблицы PKeyWorlds

    @GET("/user/ui/getPersonIDFromKeyWordsTable")
    Call<ArrayList<Integer>> getListPersonIDFromKeyWordsTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки PersonID из таблицы KeyWorlds

}
