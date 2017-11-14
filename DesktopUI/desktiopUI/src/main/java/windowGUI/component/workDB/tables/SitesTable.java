package windowGUI.component.workDB.tables;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.restApi.RestApiForSitesTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SitesTable extends ConnectServer {
/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForSitesTable restApiForSitesTable = getRetrofit().create(RestApiForSitesTable.class);
    private static final LinkedHashMap<Integer,String> listIDAndNameReal = new LinkedHashMap<>();
    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    private ArrayList<Integer> getListIDReal() {
        try {
            Response<ArrayList<Integer>> response = restApiForSitesTable.getListIDFromSitesTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    private ArrayList<String> getListNameReal(){
        try {
            Response<ArrayList<String>> response = restApiForSitesTable.getListNameFromSitesTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }


    private ArrayList<String> getListURLReal(){
        try {
            Response<ArrayList<String>> response = restApiForSitesTable.getListURLFromSitesTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    public ArrayList<Integer> getListActiveReal(){
        try {
            Response<ArrayList<Integer>> response = restApiForSitesTable.getListActiveFromSitesTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }
    /*
     * </Получение>
     * */
    public LinkedHashMap<Integer, String> getListIDAndNameReal() {
        for (int i = 0; i < getListIDReal().size(); i++) {
            for (int j = 0; j < getListNameReal().size(); j++) {
                listIDAndNameReal.put(getListIDReal().get(i),getListNameReal().get(j));
            }
        }
        return listIDAndNameReal;
    }
    /*
     * <Отправка>
     * запросы с помощью которых, можно отправить данные в БД
     * */
    public void addSite(String siteName, String siteUrl, boolean siteActive){
        try {
            Response<ResponseBody> response = restApiForSitesTable.addSite(siteName, siteUrl, siteActive).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delSite(int siteID){
        try {
            Response<ResponseBody> response = restApiForSitesTable.delSite(siteID).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifySite(int siteID, String siteName, String siteUrl, boolean siteActive){
        try {
            Response<ResponseBody> response = restApiForSitesTable.modifySite(siteID, siteName, siteUrl, siteActive).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Отправка>
     * */
/*
</РЕАЛ>
*/

/*
<ФЕЙК>
Часть кода для проверки работоспособности обработки данных из БД
*/
    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listName = new ArrayList<>();
    private static final ArrayList<String> listURL = new ArrayList<>();
    private static final ArrayList<Integer> listActive = new ArrayList<>();
    private static final LinkedHashMap<Integer,String> listIDAndName = new LinkedHashMap<>();
    private static final LinkedHashMap<String,String> listNameAndURL = new LinkedHashMap<>();
    private static final LinkedHashMap<String,Integer> listNameAndActive = new LinkedHashMap<>();


    private static SitesTable instance;

    public static SitesTable getInstance() {
        if(instance == null){
            instance = new SitesTable();
        }
        return instance;
    }

    private SitesTable() {
        listID.add(1);
        listID.add(2);

        listName.add("Лента.ру");
        listName.add("РБК");

        listURL.add("http:/lenta.ru/");
        listURL.add("http:/rbk.ru/");

        listActive.add(0);
        listActive.add(1);

        listIDAndName.put(1,"Лента.ру");
        listIDAndName.put(2,"РБК");

        listNameAndURL.put("Лента.ру", "http:/lenta.ru/");
        listNameAndURL.put("РБК", "http:/rbk.ru/");
    }

    public static ArrayList<Integer> getListID(){
        return listID;
    }

    public static ArrayList<String> getListName(){
        return listName;
    }

    public static ArrayList<String> getListURL(){
        return listURL;
    }

    public static ArrayList<Integer> getListActive(){
        return listActive;
    }

    public static LinkedHashMap<Integer, String> getListIDAndName() {
        return listIDAndName;
    }

    public static LinkedHashMap<String, String> getListNameAndURL() {
        return listNameAndURL;
    }

    public LinkedHashMap<String, Integer> getListNameAndActive() {
        listNameAndActive.put("Лента.ру", 1);
        listNameAndActive.put("РБК", 1);
        return listNameAndActive;
    }
/*
</ФЕЙК>
*/
}
