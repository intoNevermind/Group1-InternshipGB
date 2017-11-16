package windowGUI.component.workDB.tables;

import windowGUI.component.workDB.restApi.PojoPersonPageRank;
import windowGUI.component.workDB.restApi.RestApiForPersonPageRankTable;

import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;

public class PersonPageRankTable extends ConnectServer {
    private RestApiForPersonPageRankTable restApiForPersonPageRankTable = getRetrofit().create(RestApiForPersonPageRankTable.class);

    private static final ArrayList<Integer> listPersonID = new ArrayList<>();
    private static final ArrayList<Integer> listPageID = new ArrayList<>();
    private static final ArrayList<Integer> listRank = new ArrayList<>();
    private static final PersonTable personTable = PersonTable.getInstance();
    private static final ArrayList<Integer> listIDPerson = personTable.getListID();

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

    private void infoAllPersonsPageRank(){
        try {
            for (int i = 0; i <listIDPerson.size() ; i++) {
                listPersonID.add(listIDPerson.get(i));
                Response<ArrayList<PojoPersonPageRank>> response = restApiForPersonPageRankTable.getPersonPageRankByPersonId(listIDPerson.get(i)).execute();
                ArrayList<PojoPersonPageRank> list = response.body();
                for (int j = 0; j < list.size(); j++) {
                    listPageID.add(list.get(j).getPageID());
                    listRank.add(list.get(j).getRank());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getListPersonID() {
        return listPersonID;
    }

    public ArrayList<Integer> getListPageID() {
        return listPageID;
    }

    public ArrayList<Integer> getListRank() {
        return listRank;
    }
}
