package windowGUI.component.workDB.tables;

import okhttp3.ResponseBody;
import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.restApi.PojoSites;
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
    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listName = new ArrayList<>();
    private static final ArrayList<String> listURL = new ArrayList<>();
    private static final ArrayList<Boolean> listActive = new ArrayList<>();
    private static final LinkedHashMap<String,String> listNameAndURL = new LinkedHashMap<>();
    private static final LinkedHashMap<String,Boolean> listNameAndActive = new LinkedHashMap<>();
    private static final LinkedHashMap<Integer,String> listIDAndName = new LinkedHashMap<>();

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
    private void infoAllSites(){
        try {
            Response response = restApiForSitesTable.getListAllSites().execute();
            ArrayList<PojoSites> list = (ArrayList<PojoSites>) response.body();
            for (int i = 0; i < list.size(); i++) {
                listID.add(list.get(i).getId());
                listName.add(list.get(i).getName());
                listURL.add(list.get(i).getUrl());
                listActive.add(list.get(i).getActive());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> getListID() {
        return listID;
    }

    public ArrayList<String> getListName(){
       return listName;
    }


    private ArrayList<String> getListURL(){
        return listURL;
    }

    private ArrayList<Boolean> getListActive(){
        return listActive;
    }
    /*
     * </Получение>
     * */
    public LinkedHashMap<Integer, String> getListIDAndName() {
        for (int i = 0; i < getListID().size(); i++) {
            listIDAndName.put(getListID().get(i), getListName().get(i));
        }
        return listIDAndName;
    }

    public LinkedHashMap<String, String> getListNameAndURL() {
        for (int i = 0; i < getListName().size(); i++) {
            listNameAndURL.put(getListName().get(i), getListURL().get(i));
        }
        return listNameAndURL;
    }

    public LinkedHashMap<String, Boolean> getListNameAndActive() {
        for (int i = 0; i < getListName().size(); i++) {
            listNameAndActive.put(getListName().get(i), getListActive().get(i));
        }
        return listNameAndActive;
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

}
