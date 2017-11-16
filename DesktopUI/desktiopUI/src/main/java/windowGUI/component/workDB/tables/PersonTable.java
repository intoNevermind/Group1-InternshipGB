package windowGUI.component.workDB.tables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import okhttp3.ResponseBody;
import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.restApi.PojoPersons;
import windowGUI.component.workDB.restApi.RestApiForPersonTable;

public class PersonTable extends ConnectServer {
/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForPersonTable restApiForPersonTable = getRetrofit().create(RestApiForPersonTable.class);

    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listName = new ArrayList<>();
    private static final ArrayList<Boolean> listActive = new ArrayList<>();
    private static final LinkedHashMap<Integer,String> listIDAndName = new LinkedHashMap<>();
    private static final LinkedHashMap<String,Boolean> listNameAndActive = new LinkedHashMap<>();

    private static PersonTable instance;

    public static PersonTable getInstance() {
        if(instance == null){
            instance = new PersonTable();
        }
        return instance;
    }

    private PersonTable() {
        infoAllPersons();
    }
    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */
    private void infoAllPersons(){
        try {
            Response response = restApiForPersonTable.getListAllPersons().execute();
            ArrayList<PojoPersons> list = (ArrayList<PojoPersons>) response.body();
            for (int i = 0; i < list.size(); i++) {
                listID.add(list.get(i).getId());
                listName.add(list.get(i).getName());
                listActive.add(list.get(i).getActive());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getListID() {
        return listID;
    }

    public ArrayList<String> getListName(){
       return listName;
    }

    private ArrayList<Boolean> getListActive(){
       return listActive;
    }
    /*
     * </Получение>
     * */
    public LinkedHashMap<Integer, String> getListIDAndName() {
        for (int i = 0; i < getListID().size(); i++) {
            listIDAndName.put(getListID().get(i),getListName().get(i));
        }
        return listIDAndName;
    }

    public LinkedHashMap<String, Boolean> getListNameAndActive() {
        for (int i = 0; i < getListID().size(); i++) {
            listNameAndActive.put(getListName().get(i),getListActive().get(i));
        }
        return listNameAndActive;
    }

    /*
     * <Отправка>
     * запросы с помощью которых, можно отправить данные в БД
     * */
    public void addPerson(String personName, boolean personActive){
        try {
            Response<ResponseBody> response = restApiForPersonTable.addPerson(personName, personActive).execute();
            System.out.println(response.code());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delPerson(int personID){
        try {
            Response<ResponseBody> response = restApiForPersonTable.delPerson(personID).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyPerson(int personID, String personName, boolean personActive){
        try {
            Response<ResponseBody> response = restApiForPersonTable.modifyPerson(personID, personName, personActive).execute();
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
