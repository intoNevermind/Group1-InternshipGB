package windowGUI.component.workDB.tables;

import retrofit2.Response;
import windowGUI.component.workDB.restApi.PojoPages;
import windowGUI.component.workDB.restApi.RestApiForPagesTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
/*
 * Класс-таблица, отвечающий за получение данных из таблицы Pages, в REST-сервер
 * */
public class PagesTable extends ConnectServer {
    private static final RestApiForPagesTable REST_API_FOR_PAGES_TABLE = getRetrofit().create(RestApiForPagesTable.class);

    private static final ArrayList<Integer> LIST_ID = new ArrayList<>();
    private static final ArrayList<Integer> LIST_SITE_ID = new ArrayList<>();
    private static final ArrayList<Date> LIST_FOUND_DATE_TIME = new ArrayList<>();
    private static final LinkedHashMap<Integer,Date> LIST_ID_AND_FOUND_DATE_TIME = new LinkedHashMap<>();

    private static PagesTable instance;

    public static PagesTable getInstance() {
        if(instance == null){
            instance = new PagesTable();
        }
        return instance;
    }

    private PagesTable() {
//        infoAllPages();// запросы еще не работают
    }

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    private void infoAllPages(){
        try {
            Response<ArrayList<PojoPages>> responseID = REST_API_FOR_PAGES_TABLE.getListIDFromPagesTable().execute();
            ArrayList<PojoPages> listID = responseID.body();
            for (int i = 0; i < listID.size(); i++) {
                LIST_ID.add(listID.get(i).getId());
            }

            Response<ArrayList<PojoPages>> responseSiteID = REST_API_FOR_PAGES_TABLE.getListSiteIDFromPagesTable().execute();
            ArrayList<PojoPages> listSiteID = responseSiteID.body();
            for (int i = 0; i < listSiteID.size(); i++) {
                LIST_SITE_ID.add(listSiteID.get(i).getSiteId());
            }

            Response<ArrayList<PojoPages>> responseFoundDateTime = REST_API_FOR_PAGES_TABLE.getListFoundDateTimeFromPagesTable().execute();
            ArrayList<PojoPages> listFoundDateTime = responseFoundDateTime.body();
            for (int i = 0; i < listFoundDateTime.size(); i++) {
                LIST_FOUND_DATE_TIME.add(listFoundDateTime.get(i).getFoundDateTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * </Получение>
     * */

    /*
     * метод, возвращающий связанный спискок ID и даты публикации
     * */
    public LinkedHashMap<Integer, Date> getListIDAndFoundDateTime() {
        for (int i = 0; i < getListID().size(); i++) {
            LIST_ID_AND_FOUND_DATE_TIME.put(getListID().get(i),getListFoundDateTime().get(i));
        }
        return LIST_ID_AND_FOUND_DATE_TIME;
    }

    /*
     * <getters>
     * */
    public ArrayList<Integer> getListID(){
        return LIST_ID;
    }
    public ArrayList<Integer> getListSiteID(){
        return LIST_SITE_ID;
    }
    private ArrayList<Date> getListFoundDateTime(){
        return LIST_FOUND_DATE_TIME;
    }
    /*
     * </getters>
     * */
}
