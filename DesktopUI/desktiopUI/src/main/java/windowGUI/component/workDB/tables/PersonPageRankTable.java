package windowGUI.component.workDB.tables;

import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.restApi.RestApiForPersonPageRankTable;

import java.io.IOException;
import java.util.ArrayList;

public class PersonPageRankTable extends ConnectServer {

/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForPersonPageRankTable restApiForPersonPageRankTable = getRetrofit().create(RestApiForPersonPageRankTable.class);

    private ArrayList<Integer> getListPageIDReal() {
        try {
            Response<ArrayList<Integer>> response = restApiForPersonPageRankTable.getListPageIDFromPersonPageRankTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    private ArrayList<Integer> getListPersonIDReal(){
        try {
            Response<ArrayList<Integer>> response = restApiForPersonPageRankTable.getListPersonIDFromPersonPageRankTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    public ArrayList<Integer> getListRankReal(){
        try {
            Response<ArrayList<Integer>> response = restApiForPersonPageRankTable.getListRankFromPersonPageRankTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }
/*
</РЕАЛ>
*/

/*
<ФЕЙК>
Часть кода для проверки работоспособности обработки данных из БД
*/
    private static final ArrayList<Integer> listPersonID = new ArrayList<>();
    private static final ArrayList<Integer> listPageID = new ArrayList<>();
    private static final ArrayList<Integer> listRank = new ArrayList<>();

    public ArrayList<Integer> getListPersonID() {
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);

        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);

        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);
        return listPersonID;
    }

    public ArrayList<Integer> getListPageID() {
        listPageID.add(1);
        listPageID.add(2);
        listPageID.add(3);
        listPageID.add(4);
        listPageID.add(5);
        listPageID.add(6);
        listPageID.add(1);
        listPageID.add(2);
        listPageID.add(3);
        listPageID.add(4);
        listPageID.add(5);
        listPageID.add(6);
        listPageID.add(1);
        listPageID.add(2);
        listPageID.add(3);
        listPageID.add(4);
        listPageID.add(5);
        listPageID.add(6);
        return listPageID;
    }

    public ArrayList<Integer> getListRank() {
        listRank.add(9);
        listRank.add(7);
        listRank.add(6);
        listRank.add(7);
        listRank.add(4);
        listRank.add(18);
        listRank.add(0);
        listRank.add(5);
        listRank.add(8);
        listRank.add(3);
        listRank.add(1);
        listRank.add(0);
        listRank.add(0);
        listRank.add(7);
        listRank.add(8);
        listRank.add(0);
        listRank.add(20);
        listRank.add(19);
        return listRank;
    }
/*
</ФЕЙК>
*/
}
