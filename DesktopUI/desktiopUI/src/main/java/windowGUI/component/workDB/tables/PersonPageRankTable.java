package windowGUI.component.workDB.tables;

import windowGUI.component.workDB.restApi.PojoPersonPageRank;
import windowGUI.component.workDB.restApi.RestApiForPersonPageRankTable;

import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
/*
 * Класс-таблица, отвечающий за получение(отправку) данных из таблицы PersonPageRank, в REST-сервер
 * */
public class PersonPageRankTable extends ConnectServer {
    private static final RestApiForPersonPageRankTable REST_API_FOR_PERSON_PAGE_RANK_TABLE = getRetrofit().create(RestApiForPersonPageRankTable.class);

    private static final ArrayList<Integer> LIST_PERSON_ID = new ArrayList<>();
    private static final ArrayList<Integer> LIST_PAGE_ID = new ArrayList<>();
    private static final ArrayList<Integer> LIST_RANK = new ArrayList<>();


    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    public static void infoAllPersonsPageRank(){
        LIST_PERSON_ID.clear();
        LIST_PAGE_ID.clear();
        LIST_RANK.clear();
        PersonsTable.infoAllPersons();
        ArrayList<Integer> listIdPersons = PersonsTable.getListID();
        for (int i = 0; i < listIdPersons.size() ; i++) {
            LIST_PERSON_ID.add(listIdPersons.get(i));
            try {
                Response<ArrayList<PojoPersonPageRank>> response = REST_API_FOR_PERSON_PAGE_RANK_TABLE.getPersonPageRankByPersonId(listIdPersons.get(i)).execute();
                ArrayList<PojoPersonPageRank> list = response.body();
                for (int j = 0; j < list.size(); j++) {
                    LIST_PAGE_ID.add(list.get(j).getPageID());
                    LIST_RANK.add(list.get(j).getRank());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * </Получение>
     * */

    /*
     * <getters>
     * */
    public static ArrayList<Integer> getListPersonID() {
        return LIST_PERSON_ID;
    }
    public static ArrayList<Integer> getListPageID() {
        return LIST_PAGE_ID;
    }
    public static ArrayList<Integer> getListRank() {
        return LIST_RANK;
    }
    /*
     * </getters>
     * */
}
