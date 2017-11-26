package windowGUI.component.workWithDB.tables;

import windowGUI.component.workWithDB.restApi.pojo.PojoPersons;
import windowGUI.component.workWithDB.restApi.QueriesForPersonTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import okhttp3.ResponseBody;
import retrofit2.Response;
/*
 * Класс-таблица, отвечающий за получение(отправку) данных из таблицы Person, в REST-сервер
 * */
public class PersonsTable extends ConnectServer {
    private static final QueriesForPersonTable QUERIES_FOR_PERSON_TABLE = getRetrofit().create(QueriesForPersonTable.class);

    private static final ArrayList<Integer> LIST_ID = new ArrayList<>();
    private static final ArrayList<String> LIST_NAME = new ArrayList<>();
    private static final ArrayList<Boolean> LIST_ACTIVE = new ArrayList<>();
    private static final LinkedHashMap<Integer, String> LIST_ID_AND_NAME = new LinkedHashMap<>();
    private static final LinkedHashMap<String, Boolean> LIST_NAME_AND_ACTIVE = new LinkedHashMap<>();

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    public static void infoAllPersons(){
        LIST_ID.clear();
        LIST_NAME.clear();
        LIST_ACTIVE.clear();
        LIST_ID_AND_NAME.clear();
        LIST_NAME_AND_ACTIVE.clear();

        try {
            Response<ArrayList<PojoPersons>> response = QUERIES_FOR_PERSON_TABLE.getListAllPersons().execute();

            ArrayList<PojoPersons> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_ID.add(list.get(i).getId());
                LIST_NAME.add(list.get(i).getName());
                LIST_ACTIVE.add(list.get(i).getActive());
                LIST_ID_AND_NAME.put( LIST_ID.get(i), LIST_NAME.get(i));
                LIST_NAME_AND_ACTIVE.put(LIST_NAME.get(i), LIST_ACTIVE.get(i));
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
     * метод, добавляющий личность
     * */
    public static void addPerson(String namePerson, boolean activePerson){
        try {
            Response<ResponseBody> response = QUERIES_FOR_PERSON_TABLE.addPerson(namePerson, activePerson).execute();

            if(response.isSuccessful()) response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, удаляющий личность
     * */
    public static void delPerson(int personID){
        try {
            Response<ResponseBody> response = QUERIES_FOR_PERSON_TABLE.delPerson(personID).execute();

            if(response.isSuccessful()) response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, редактирующий личность
     * */
    public static void modifyPerson(int personID, String namePerson, boolean activePerson){
        try {
            Response<ResponseBody> response = QUERIES_FOR_PERSON_TABLE.modifyPerson(personID, namePerson, activePerson).execute();

            if(response.isSuccessful()) response.body().string();
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
    public static LinkedHashMap<Integer, String> getListIDAndName() {
        return LIST_ID_AND_NAME;
    }
    public static LinkedHashMap<String, Boolean> getListNameAndActive() {
        return LIST_NAME_AND_ACTIVE;
    }
    /*
     * </getters>
     * */
}
