package windowGUI.component.workDB.tables;

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

    public LinkedHashMap<Integer, String> getListIDAndNameReal() {
        for (int i = 0; i < getListIDReal().size(); i++) {
            for (int j = 0; j < getListNameReal().size(); j++) {
                listIDAndNameReal.put(getListIDReal().get(i),getListNameReal().get(j));
            }
        }
        return listIDAndNameReal;
    }
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

    public ArrayList<Integer> getListID(){
        listID.add(1);
        listID.add(2);
        return listID;
    }

    public ArrayList<String> getListName(){
        listName.add("Лента.ру");
        listName.add("РБК");
        return listName;
    }

    public ArrayList<String> getListURL(){
        listURL.add("http:/lenta.ru/");
        listURL.add("http:/lenta.ru/");
        return listURL;
    }

    public ArrayList<Integer> getListActive(){
        listActive.add(0);
        listActive.add(1);
        return listActive;
    }

    public LinkedHashMap<Integer, String> getListIDAndName() {
        listIDAndName.put(1,"Лента.ру");
        listIDAndName.put(2,"РБК");
        return listIDAndName;
    }
/*
</ФЕЙК>
*/
}
