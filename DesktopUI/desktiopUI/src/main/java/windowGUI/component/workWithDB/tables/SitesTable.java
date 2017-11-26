package windowGUI.component.workWithDB.tables;

import windowGUI.component.workWithDB.restApi.pojo.PojoSites;
import windowGUI.component.workWithDB.restApi.QueriesForSitesTable;

import okhttp3.ResponseBody;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-таблица, отвечающий за получение(отправку) данных из таблицы Sites, в REST-сервер
 * */
public class SitesTable extends ConnectServer {
    private static final QueriesForSitesTable QUERIES_FOR_SITES_TABLE = getRetrofit().create(QueriesForSitesTable.class);

    private static final ArrayList<Integer> LIST_ID = new ArrayList<>();
    private static final ArrayList<String> LIST_NAME = new ArrayList<>();
    private static final ArrayList<String> LIST_URL = new ArrayList<>();
    private static final ArrayList<Boolean> LIST_ACTIVE = new ArrayList<>();
    private static final LinkedHashMap<String, String> LIST_NAME_AND_URL = new LinkedHashMap<>();
    private static final LinkedHashMap<String, Boolean> LIST_NAME_AND_ACTIVE = new LinkedHashMap<>();
    private static final LinkedHashMap<Integer, String> LIST_ID_AND_NAME = new LinkedHashMap<>();

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    public static void infoAllSites(){
        LIST_ID.clear();
        LIST_NAME.clear();
        LIST_URL.clear();
        LIST_ACTIVE.clear();
        LIST_ID_AND_NAME.clear();
        LIST_NAME_AND_URL.clear();
        LIST_NAME_AND_ACTIVE.clear();

        try {
            Response<ArrayList<PojoSites>> response = QUERIES_FOR_SITES_TABLE.getListAllSites().execute();

            ArrayList<PojoSites> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_ID.add(list.get(i).getId());
                LIST_NAME.add(list.get(i).getName());
                LIST_URL.add(list.get(i).getUrl());
                LIST_ACTIVE.add(list.get(i).getActive());
                LIST_ID_AND_NAME.put(LIST_ID.get(i), LIST_NAME.get(i));
                LIST_NAME_AND_URL.put(LIST_NAME.get(i), LIST_URL.get(i));
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
     * метод, добавляющий сайт
     * */
    public static void addSite(String nameSite, String urlSite, boolean activeSite){
        try {
            Response<ResponseBody> response = QUERIES_FOR_SITES_TABLE.addSite(nameSite, urlSite, activeSite).execute();

            if(response.isSuccessful()) response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, удаляющий сайт
     * */
    public static void delSite(int siteID){
        try {
            Response<ResponseBody> response = QUERIES_FOR_SITES_TABLE.delSite(siteID).execute();

            if(response.isSuccessful()) response.body().string();
            else response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, редактирующий сайт
     * */
    public static void modifySite(int siteID, String nameSite, String urlSite, boolean activeSite){
        try {
            Response<ResponseBody> response = QUERIES_FOR_SITES_TABLE.modifySite(siteID, nameSite, urlSite, activeSite).execute();

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
    public static ArrayList<Boolean> getListActive() {
        return LIST_ACTIVE;
    }
    public static LinkedHashMap<Integer, String> getListIDAndName() {
        return LIST_ID_AND_NAME;
    }
    public static LinkedHashMap<String, String> getListNameAndURL() {
        return LIST_NAME_AND_URL;
    }
    public static LinkedHashMap<String, Boolean> getListNameAndActive() {
        return LIST_NAME_AND_ACTIVE;
    }
    /*
     * </getters>
     * */
}
