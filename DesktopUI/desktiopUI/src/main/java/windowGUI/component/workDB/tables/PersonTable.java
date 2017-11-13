package windowGUI.component.workDB.tables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import okhttp3.ResponseBody;
import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.restApi.RestApiForPersonTable;

public class PersonTable extends ConnectServer {
/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForPersonTable restApiForPersonTable = getRetrofit().create(RestApiForPersonTable.class);
    private static final LinkedHashMap<Integer,String> listIDAndNameReal = new LinkedHashMap<>();
    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */
   public ArrayList<Integer> getListIDReal() {
        try {
            Response<ArrayList<Integer>> response = restApiForPersonTable.getListIDFromPersonTable().execute();
            System.out.println(response.body());
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<String> getListNameReal(){
        try {
            Response<ArrayList<String>> response = restApiForPersonTable.getListNameFromPersonTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Integer> getListActiveReal(){
        try {
            Response<ArrayList<Integer>> response = restApiForPersonTable.getListActiveFromPersonTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
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
    public void addPerson(String personName, boolean personActive){
        try {
            Response<ResponseBody> response = restApiForPersonTable.addPerson(personName, personActive).execute();
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

/*
<ФЕЙК>
Часть кода для проверки работоспособности обработки данных из БД
*/
    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listName = new ArrayList<>();
    private static final ArrayList<Integer> listActive = new ArrayList<>();
    private static final LinkedHashMap<Integer,String> listIDAndName = new LinkedHashMap<>();

    public ArrayList<Integer> getListID(){
        listID.add(1);
        listID.add(2);
        listID.add(3);
        return listID;
    }

    public ArrayList<String> getListName(){
        listName.add("Путин");
        listName.add("Навальный");
        listName.add("Собчак");
        return listName;
    }

    public ArrayList<Integer> getListActive(){
        listActive.add(0);
        listActive.add(1);
        return listActive;
    }

    public LinkedHashMap<Integer, String> getListIDAndName() {
        listIDAndName.put(1,"Путин");
        listIDAndName.put(2,"Навальный");
        listIDAndName.put(3,"Собчак");
        return listIDAndName;
    }
/*
</ФЕЙК>
*/
}
