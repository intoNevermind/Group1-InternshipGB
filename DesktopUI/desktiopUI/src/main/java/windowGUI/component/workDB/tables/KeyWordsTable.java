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

    private static KeyWordsTable instance;

    public static KeyWordsTable getInstance() {
        if(instance == null){
            instance = new KeyWordsTable();
        }
        return instance;
    }

    private KeyWordsTable() {
        infoAllKeyWords();
    }

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    private void infoAllKeyWords(){
        try {
            Response<ArrayList<PojoKeyWords>> response = REST_API_FOR_KEY_WORDS_TABLE.getListAllKeyWords().execute();
            ArrayList<PojoKeyWords> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_ID.add(list.get(i).getId());
                LIST_NAME.add(list.get(i).getName());
                LIST_PERSON_ID.add(list.get(i).getPersonId());
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
    public void addKeyWord(String keyWordName, int personID){
        try {
            Response<ResponseBody> response = REST_API_FOR_KEY_WORDS_TABLE.addKeyWord(keyWordName,personID).execute();
            System.out.println(response.raw());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, удаляющий ключевое слово
     * */
    public void delKeyWord(int keyWordID){
        try {
            Response<ResponseBody> response = REST_API_FOR_KEY_WORDS_TABLE.delKeyWord(keyWordID).execute();
            System.out.println(response.raw());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, редактирующий ключевое слово
     * */
    public void modifyKeyWord(int keyWordID, String keyWordName , int personID){
        try {
            Response<ResponseBody> response = REST_API_FOR_KEY_WORDS_TABLE.modifyKeyWord(keyWordID, keyWordName, personID).execute();
            System.out.println(response.raw());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Отправка>
     * */

    /*
     * метод, возвращающий связанный спискок ID и имени ключевого слова
     * */
    public LinkedHashMap<Integer, String> getListIDAndName() {
        for (int i = 0; i < LIST_ID.size(); i++) {
            LIST_ID_AND_NAME.put(getListID().get(i),getListName().get(i));
        }
        return LIST_ID_AND_NAME;
    }

    /*
     * <getters>
     * */
    private ArrayList<Integer> getListID() {
        return LIST_ID;
    }
    public ArrayList<String> getListName(){
        return LIST_NAME;
    }
    public ArrayList<Integer> getListPersonID(){
        return LIST_PERSON_ID;
    }
    /*
     * </getters>
     * */
}
