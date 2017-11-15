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
    @GET("getAllKeywords")
    Call<ArrayList<PojoKeyWords>> getListAllKeyWords();
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @GET("addKeyword")
    Call<ResponseBody> addKeyWord(@Query("KeyWordName") String keyWordName ,
                                  @Query("PersonID") int personID);// добавляет ключевое слово по ID личности в БД

    @GET("delKeyword")
    Call<ResponseBody> delKeyWord(@Query("KeyWordID") int keyWordID);// удаляет ключевое слово по ID из БД

    @GET("modifyKeyword")
    Call<ResponseBody> modifyKeyWord(@Query("KeyWordID") int keyWordID,
                                     @Query("KeyWordName") String keyWordName ,
                                     @Query("PersonID") int personID);// редактирует имя ключевого слова и ID личности по ID ключевого слова(сам ID редоктировать нельзя)
/*
* </Отправка>
* */
}
