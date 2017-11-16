package windowGUI.component.workDB.tables;

import windowGUI.component.workDB.restApi.PojoPages;
import windowGUI.component.workDB.restApi.RestApiForPagesTable;

import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class PagesTable extends ConnectServer {
/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForPagesTable restApiForPagesTable = getRetrofit().create(RestApiForPagesTable.class);

    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listURL = new ArrayList<>();
    private static final ArrayList<Integer> listSiteID = new ArrayList<>();
    private static final ArrayList<Date> listFoundDateTime = new ArrayList<>();
    private static final LinkedHashMap<Integer,Date> listIDAndFoundDateTime = new LinkedHashMap<>();

    private static PagesTable instance;

    public static PagesTable getInstance() {
        if(instance == null){
            instance = new PagesTable();
        }
        return instance;
    }

    private PagesTable() {
        infoAllPages();
    }

    private void infoAllPages(){
        try {
            Response<ArrayList<PojoPages>> response = restApiForPagesTable.getListAllPages().execute();
            ArrayList<PojoPages> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                /*тут будут заполнятся списки*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getListID(){
        return listID;
    }

    public ArrayList<String> getListURL(){
        return listURL;
    }

    public ArrayList<Integer> getListSiteID(){
        return listSiteID;
    }

    public ArrayList<Date> getListFoundDateTime(){
        return listFoundDateTime;
    }

    public LinkedHashMap<Integer,Date> getListIDAndFoundDateTime() {
        return listIDAndFoundDateTime;
    }
}
