package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.ArrayList;

public interface RestApiForPersonPageRankTable {

    @GET("user/ui/getPersonPageRankByPersonId")
    Call<ArrayList<PojoPersonPageRank>> getPersonPageRankByPersonId(@Query("id") int id);

}
