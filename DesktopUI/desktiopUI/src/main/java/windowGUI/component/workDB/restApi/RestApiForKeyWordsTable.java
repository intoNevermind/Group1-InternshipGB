package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


import java.util.ArrayList;

public interface RestApiForKeyWordsTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("/user/ui/getIDFromKeyWordsTable")
    Call<ArrayList<Integer>> getListIDFromKeyWordsTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки ID из таблицы KeyWorlds

    @GET("/user/ui/getNameFromKeyWordsTable")
    Call<ArrayList<String>> getListNameFromKeyWordsTable();//получить на выходе ArrayList<String>, содержащий все значения колонки Name из таблицы PKeyWorlds

    @GET("/user/ui/getPersonIDFromKeyWordsTable")
    Call<ArrayList<Integer>> getListPersonIDFromKeyWordsTable();//получить на выходе ArrayList<Integer>, содержащий все значения колонки PersonID из таблицы KeyWorlds
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("/admin/ui/addKeyword")
    Call<ResponseBody> addKeyWord(@Query("KeyWordName") String keyWordName ,
                                  @Query("PersonID") int personID);// добавляет ключевое слово по ID личности в БД

    @GET("/admin/ui/delKeyword")
    Call<ResponseBody> delKeyWord(@Query("KeyWordID") int keyWordID);// удаляет ключевое слово по ID из БД

    @GET("/admin/ui/modifyKeyword")
    Call<ResponseBody> modifyKeyWord(@Query("KeyWordID") int keyWordID,
                                     @Query("KeyWordName") String keyWordName ,
                                     @Query("PersonID") int personID);// редактирует имя ключевого слова и ID личности по ID ключевого слова(сам ID редоктировать нельзя)
/*
* </Отправка>
* */
}
