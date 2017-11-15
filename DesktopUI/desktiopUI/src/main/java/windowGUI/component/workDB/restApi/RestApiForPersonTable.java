package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.ArrayList;

public interface RestApiForPersonTable {

/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("getAllPersons")
    Call<ArrayList<PojoPersons>> getListAllPersons();
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("addPerson")
    Call<ResponseBody> addPerson(@Query("PersonName") String personName ,
                                 @Query("personActive") boolean personActive);// добавляет личность и активность в БД

    @GET("delPerson")
    Call<ResponseBody> delPerson(@Query("PersonID") int personID);// удаляет личность по ID из БД

    @GET("modifyPerson")
    Call<ResponseBody> modifyPerson(@Query("PersonID") int personID,
                                    @Query("PersonName") String personName ,
                                    @Query("personActive") boolean personActive);// редактирует имя личности и активность по ID личности(сам ID редоктировать нельзя)
/*
* </Отправка>
* */
}
