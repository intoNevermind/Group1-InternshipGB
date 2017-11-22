package windowGUI.component.workDB.tables;

import windowGUI.component.workDB.restApi.PojoKeyWords;
import windowGUI.component.workDB.restApi.RestApiForKeyWordsTable;

import okhttp3.ResponseBody;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-таблица, отвечающий за получение(отправку) данных из таблицы KeyWords, в REST-сервер
 * */
public class KeyWordsTable extends ConnectServer {
    private static final RestApiForKeyWordsTable REST_API_FOR_KEY_WORDS_TABLE = getRetrofit().create(RestApiForKeyWordsTable.class);

    private static final ArrayList<Integer> LIST_ID = new ArrayList<>();
    private static final ArrayList<String> LIST_NAME = new ArrayList<>();
    private static final ArrayList<Integer> LIST_PERSON_ID = new ArrayList<>();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME = new LinkedHashMap<>();

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    public static void infoAllKeyWords(){
        LIST_ID.clear();
        LIST_NAME.clear();
        LIST_PERSON_ID.clear();
        LIST_ID_AND_NAME.clear();

        try {
            Response<ArrayList<PojoKeyWords>> response = REST_API_FOR_KEY_WORDS_TABLE.getListAllKeyWords().execute();

            ArrayList<PojoKeyWords> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_ID.add(list.get(i).getId());
                LIST_NAME.add(list.get(i).getName());
                LIST_PERSON_ID.add(list.get(i).getPersonId());
                LIST_ID_AND_NAME.put(LIST_ID.get(i),LIST_NAME.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Получение>
     * */

    /*
     * <Отправка>
     * запросы с помощью которых, можно отправить данные в БД
     * */

    /*
     * метод, добавляющий ключевое слово
     * */
    public static void addKeyWord(String nameKeyWord, int personID){
        try {
            Response<ResponseBody> response = REST_API_FOR_KEY_WORDS_TABLE.addKeyWord(nameKeyWord,personID).execute();

            if (response.isSuccessful())response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, удаляющий ключевое слово
     * */
    public static void delKeyWord(int keyWordID){
        try {
            Response<ResponseBody> response = REST_API_FOR_KEY_WORDS_TABLE.delKeyWord(keyWordID).execute();

            if (response.isSuccessful())response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, редактирующий ключевое слово
     * */
    public static void modifyKeyWord(int keyWordID, String nameKeyWord , int personID){
        try {
            Response<ResponseBody> response = REST_API_FOR_KEY_WORDS_TABLE.modifyKeyWord(keyWordID, nameKeyWord,personID).execute();

            if (response.isSuccessful())response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Отправка>
     * */

    /*
     * <getters>
     * */
    public static ArrayList<String> getListName(){
        return LIST_NAME;
    }
    public static ArrayList<Integer> getListPersonID(){
        return LIST_PERSON_ID;
    }
    public static LinkedHashMap<Integer, String> getListIDAndName() {
        return LIST_ID_AND_NAME;
    }
    /*
     * </getters>
     * */
}
