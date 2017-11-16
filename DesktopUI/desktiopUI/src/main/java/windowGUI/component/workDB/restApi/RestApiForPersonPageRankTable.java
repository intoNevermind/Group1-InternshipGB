package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.ArrayList;

public interface RestApiForPersonPageRankTable {

    @GET("user/ui/getPersonPageRankByPersonId?id={PersonID}")
    Call<ArrayList<PojoPersonPageRank>> getPersonPageRankByPersonId(@Path("PersonID") int personID);

}
