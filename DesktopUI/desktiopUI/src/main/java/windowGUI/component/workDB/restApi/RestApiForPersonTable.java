package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;

public interface RestApiForPersonTable {

    @GET("/user/ui/getIDFromPersonTable")
    Call<ArrayList<Integer>> getListIDFromPersonTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы Persons

    @GET("/user/ui/getNameFromPersonTable")
    Call<ArrayList<String>> getListNameFromPersonTable();//получить на выходе ArrayList<String>, содержащий все значения колонки Name из таблицы Persons

    @GET("/user/ui/getActiveFromPersonTable")
    Call<ArrayList<Integer>> getListActiveFromPersonTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки Active из таблицы Persons

}
