package windowGUI.component.workDB.restApi;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;
import java.util.Date;

public interface RestApiForPagesTable {

    @GET("/user/ui/getAllPages")
    Call<ArrayList<PojoPages>> getListAllPages();

}
