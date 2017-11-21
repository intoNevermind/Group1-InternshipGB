package windowGUI.component.workDB.tables;

import retrofit2.Call;
import retrofit2.Callback;
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


    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД(REST-сервер еще не написал запрос)
     * */
    public static void infoAllPages(){
        LIST_ID.clear();
        LIST_SITE_ID.clear();
        LIST_FOUND_DATE_TIME.clear();
        try {
            Response<ArrayList<PojoPages>> responseID = REST_API_FOR_PAGES_TABLE.getListIDFromPagesTable().execute();
            ArrayList<PojoPages> listID = responseID.body();
            if(listID.size() > 0){
                for (int i = 0; i < listID.size(); i++) {
                    LIST_ID.add(listID.get(i).getId());
                }
            }

            Response<ArrayList<PojoPages>> responseSiteID = REST_API_FOR_PAGES_TABLE.getListSiteIDFromPagesTable().execute();
            ArrayList<PojoPages> listSiteID = responseSiteID.body();
            if(listSiteID.size() > 0){
                for (int i = 0; i < listSiteID.size(); i++) {
                    LIST_SITE_ID.add(listSiteID.get(i).getSiteId());
                }
            }

            Response<ArrayList<PojoPages>> responseFoundDateTime = REST_API_FOR_PAGES_TABLE.getListFoundDateTimeFromPagesTable().execute();
            ArrayList<PojoPages> listFoundDateTime = responseFoundDateTime.body();
            if(listFoundDateTime.size() > 0){
                for (int i = 0; i < listFoundDateTime.size(); i++) {
                    LIST_FOUND_DATE_TIME.add(listFoundDateTime.get(i).getFoundDateTime());
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
     * метод, возвращающий связанный спискок ID и даты публикации
     * */
    public static LinkedHashMap<Integer, Date> getListIDAndFoundDateTime() {
        for (int i = 0; i < LIST_ID.size(); i++) {
            LIST_ID_AND_FOUND_DATE_TIME.put(LIST_ID.get(i),LIST_FOUND_DATE_TIME.get(i));
        }
        return LIST_ID_AND_FOUND_DATE_TIME;
    }

    /*
     * <getters>
     * */
    public static ArrayList<Integer> getListID(){
        return LIST_ID;
    }
    public static ArrayList<Integer> getListSiteID(){
        return LIST_SITE_ID;
    }
    private static ArrayList<Date> getListFoundDateTime(){
        return LIST_FOUND_DATE_TIME;
    }
    /*
     * </getters>
     * */
}
