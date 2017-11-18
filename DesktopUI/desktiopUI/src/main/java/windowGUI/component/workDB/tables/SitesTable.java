package windowGUI.component.workDB.tables;

import windowGUI.component.workDB.restApi.PojoSites;
import windowGUI.component.workDB.restApi.RestApiForSitesTable;

import okhttp3.ResponseBody;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-таблица, отвечающий за получение(отправку) данных из таблицы Sites, в REST-сервер
 * */
public class SitesTable extends ConnectServer {
    private static final RestApiForSitesTable REST_API_FOR_SITES_TABLE = getRetrofit().create(RestApiForSitesTable.class);
    private static final ArrayList<Integer> LIST_ID = new ArrayList<>();
    private static final ArrayList<String> LIST_NAME = new ArrayList<>();
    private static final ArrayList<String> LIST_URL = new ArrayList<>();
    private static final ArrayList<Boolean> LIST_ACTIVE = new ArrayList<>();
    private static final LinkedHashMap<String,String> LIST_NAME_AND_URL = new LinkedHashMap<>();
    private static final LinkedHashMap<String,Boolean> LIST_NAME_AND_ACTIVE = new LinkedHashMap<>();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME = new LinkedHashMap<>();

    private static SitesTable instance;

    public static SitesTable getInstance() {
        if(instance == null){
            instance = new SitesTable();
        }
        return instance;
    }

    private SitesTable() {
       infoAllSites();
    }

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    private void infoAllSites(){
        try {
            Response<ArrayList<PojoSites>> response = REST_API_FOR_SITES_TABLE.getListAllSites().execute();
            ArrayList<PojoSites> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_ID.add(list.get(i).getId());
                LIST_NAME.add(list.get(i).getName());
                LIST_URL.add(list.get(i).getUrl());
                LIST_ACTIVE.add(list.get(i).getActive());
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
    public void addSite(String siteName, String siteUrl, boolean siteActive){
        try {
            Response<ResponseBody> response = REST_API_FOR_SITES_TABLE.addSite(siteName, siteUrl, siteActive).execute();
            System.out.println(response.raw());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, удаляющий сайт
     * */
    public void delSite(int siteID){
        try {
            Response<ResponseBody> response = REST_API_FOR_SITES_TABLE.delSite(siteID).execute();
            System.out.println(response.raw());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, редактирующий сайт
     * */
    public void modifySite(int siteID, String siteName, String siteUrl, boolean siteActive){
        try {
            Response<ResponseBody> response = REST_API_FOR_SITES_TABLE.modifySite(siteID, siteName, siteUrl, siteActive).execute();
            System.out.println(response.raw());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Отправка>
     * */

    /*
     * метод, возвращающий связанный спискок ID и имени сайта
     * */
    public LinkedHashMap<Integer, String> getListIDAndName() {
        for (int i = 0; i < getListID().size(); i++) {
            LIST_ID_AND_NAME.put(getListID().get(i), getListName().get(i));
        }
        return LIST_ID_AND_NAME;
    }

    /*
     * метод, возвращающий связанный имени и URL сайта
     * */
    public LinkedHashMap<String, String> getListNameAndURL() {
        for (int i = 0; i < getListName().size(); i++) {
            LIST_NAME_AND_URL.put(getListName().get(i), getListURL().get(i));
        }
        return LIST_NAME_AND_URL;
    }

    /*
     * метод, возвращающий связанный имени и активности сайта
     * */
    public LinkedHashMap<String, Boolean> getListNameAndActive() {
        for (int i = 0; i < getListName().size(); i++) {
            LIST_NAME_AND_ACTIVE.put(getListName().get(i), getListActive().get(i));
        }
        return LIST_NAME_AND_ACTIVE;
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
    private ArrayList<String> getListURL(){
        return LIST_URL;
    }
    private ArrayList<Boolean> getListActive() {
        return LIST_ACTIVE;
    }
    /*
     * </getters>
     * */
}
