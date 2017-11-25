package ru.geekbrains.androidadmin.webapi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.geekbrains.androidadmin.model.ErrorResponse;
import ru.geekbrains.androidadmin.model.Keyword;
import ru.geekbrains.androidadmin.model.Person;
import ru.geekbrains.androidadmin.model.Site;
import ru.geekbrains.androidadmin.model.User;

/**
 * Created by curly on 18.11.2017.
 */

public interface WebApi {

    String X_CSRF_TOKEN = "X-CSRF-TOKEN";

    @GET("login")
    Call<ResponseBody> login();

    @POST("login")
    Call<ResponseBody> login(//@Header(X_CSRF_TOKEN) String token,
                              @Query("username") String username,
                              @Query("password") String password);

    // get all
    @GET("admin/ui/getAllUsers")
    Call<List<User>> getAllUsers();

    @GET("admin/ui/getAllSites")
    Call<List<Site>> getAllSites();

    @GET("admin/ui/getAllPersons")
    Call<List<Person>> getAllPersons();

    @GET("admin/ui/getAllKeywords")
    Call<List<Keyword>> getAllKeywords();

    // add
    @GET("admin/ui/addUser")
    Call<ResponseBody> addUser(@Query("login") String login,
                               @Query("password") String password,
                               @Query("admin") boolean admin,
                               @Query("active") boolean active);

    @GET("admin/ui/addSite")
    Call<ResponseBody> addSite(@Query("name") String name,
                               @Query("url") String url,
                               @Query("active") boolean active);

    @GET("admin/ui/addPerson")
    Call<ResponseBody> addPerson(@Query("name") String name,
                               @Query("active") boolean active);

    @GET("admin/ui/addKeyword")
    Call<ResponseBody> addKeyword(@Query("name") String name,
                               @Query("personId") Integer personId);

    // modify
    @GET("admin/ui/modifyUser")
    Call<ResponseBody> modifyUser(@Query("id") int id,
                                  @Query("login") String login,
                                  @Query("password") String password,
                                  @Query("admin") boolean admin,
                                  @Query("active") boolean active);

    @GET("admin/ui/modifySite")
    Call<ResponseBody> modifySite(@Query("id") int id,
                                  @Query("name") String name,
                                  @Query("url") String url,
                                  @Query("active") boolean active);

    @GET("admin/ui/modifyPerson")
    Call<ResponseBody> modifyPerson(@Query("id") int id,
                                    @Query("name") String name,
                                    @Query("active") boolean active);

    @GET("admin/ui/modifyKeyword")
    Call<ResponseBody> modifyKeyword(@Query("id") int id,
                                     @Query("name") String name,
                                     @Query("personId") Integer personId);

    // delete
    @GET("admin/ui/delUser")
    Call<ResponseBody> delUser(@Query("id") int id);

    @GET("admin/ui/delSite")
    Call<ResponseBody> delSite(@Query("id") int id);

    @GET("admin/ui/delPerson")
    Call<ResponseBody> delPerson(@Query("id") int id);

    @GET("admin/ui/delKeyword")
    Call<ResponseBody> delKeyword(@Query("id") int id);
}
