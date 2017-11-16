package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.ArrayList;

public interface RestApiForUsersTable {

    @GET("admin/ui/getAllUsers")
    Call<ArrayList<PojoUsers>> getListAllUsers();

    @POST("admin/ui/addUser")
    Call<ResponseBody> addUser(@Query("login") String userLogin ,
                               @Query("admin") boolean userAdmin,
                               @Query("password") String userPassword,
                               @Query("active") boolean userActive);

    @POST("admin/ui/delUser")
    Call<ResponseBody> delUser(@Query("id") int userID);

    @POST("admin/ui/modifyUser")
    Call<ResponseBody> modifyUser(@Query("id") int userID,
                                  @Query("login") String userLogin ,
                                  @Query("admin") boolean userAdmin,
                                  @Query("password") String userPassword,
                                  @Query("active") boolean userActive);

}
