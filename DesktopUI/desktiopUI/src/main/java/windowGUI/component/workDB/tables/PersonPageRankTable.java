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

    private static final PersonsTable PERSONS_TABLE = PersonsTable.getInstance();
    private static final ArrayList<Integer> LIST_ID_PERSONS = PERSONS_TABLE.getListID();

    private static PersonPageRankTable instance;

    public static PersonPageRankTable getInstance() {
        if(instance == null){
            instance = new PersonPageRankTable();
        }
        return instance;
    }

    private PersonPageRankTable() {
        infoAllPersonsPageRank();
    }
    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    private void infoAllPersonsPageRank(){
        try {
            for (int i = 0; i < LIST_ID_PERSONS.size() ; i++) {
                LIST_PERSON_ID.add(LIST_ID_PERSONS.get(i));
                Response<ArrayList<PojoPersonPageRank>> response = REST_API_FOR_PERSON_PAGE_RANK_TABLE.getPersonPageRankByPersonId(LIST_ID_PERSONS.get(i)).execute();
                ArrayList<PojoPersonPageRank> list = response.body();
                for (int j = 0; j < list.size(); j++) {
                    LIST_PAGE_ID.add(list.get(j).getPageID());
                    LIST_RANK.add(list.get(j).getRank());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Получение>
     * */

    /*
     * <getters>
     * */
    public ArrayList<Integer> getListPersonID() {
        return LIST_PERSON_ID;
    }
    public ArrayList<Integer> getListPageID() {
        return LIST_PAGE_ID;
    }
    public ArrayList<Integer> getListRank() {
        return LIST_RANK;
    }
    /*
     * </getters>
     * */
}
