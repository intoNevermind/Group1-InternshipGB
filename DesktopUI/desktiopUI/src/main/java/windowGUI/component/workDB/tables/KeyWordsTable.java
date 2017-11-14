package windowGUI.component.workDB.tables;

import okhttp3.ResponseBody;
import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.restApi.RestApiForKeyWordsTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class KeyWordsTable extends ConnectServer {
/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForKeyWordsTable restApiForKeyWordsTable = getRetrofit().create(RestApiForKeyWordsTable.class);

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */
    private ArrayList<Integer> getListIDReal() {
        try {
            Response<ArrayList<Integer>> response = restApiForKeyWordsTable.getListIDFromKeyWordsTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    private ArrayList<String> getListNameReal(){
        try {
            Response<ArrayList<String>> response = restApiForKeyWordsTable.getListNameFromKeyWordsTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    public ArrayList<Integer> getListPersonIDReal(){
        try {
            Response<ArrayList<Integer>> response = restApiForKeyWordsTable.getListPersonIDFromKeyWordsTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }
    /*
     * </Получение>
     * */

    /*
     * <Отправка>
     * запросы с помощью которых, можно отправить данные в БД
     * */
    public void addKeyWord(String keyWordName, int personID){
        try {
            Response<ResponseBody> response = restApiForKeyWordsTable.addKeyWord(keyWordName,personID).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delKeyWord(int keyWordID){
        try {
            Response<ResponseBody> response = restApiForKeyWordsTable.delKeyWord(keyWordID).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyKeyWord(int keyWordID, String keyWordName , int personID){
        try {
            Response<ResponseBody> response = restApiForKeyWordsTable.modifyKeyWord(keyWordID, keyWordName, personID).execute();
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
    private static final ArrayList<Integer> listPersonID = new ArrayList<>();
    private static final LinkedHashMap<Integer,String> listIDAndName = new LinkedHashMap<>();

    public ArrayList<Integer> getListID(){
        for (int i = 1; i <= 9; i++) {
            listID.add(i);
        }
        return listID;
    }

    public ArrayList<String> getListName(){
        listName.add("Путин");
        listName.add("Путинa");
        listName.add("Путинy");
        listName.add("Путиным");
        listName.add("Навальный");
        listName.add("Навальным");
        listName.add("Навальному");
        listName.add("Навального");
        listName.add("Собчак");
        return listName;
    }

    public ArrayList<Integer> getListPersonID(){
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(3);
        return listPersonID;
    }

    public LinkedHashMap<Integer, String> getListIDAndName() {
        listIDAndName.put(1,"Путин");
        listIDAndName.put(2,"Путина");
        listIDAndName.put(3,"Путину");
        listIDAndName.put(4,"Путиным");
        listIDAndName.put(5,"Навальный");
        listIDAndName.put(6,"Навальным");
        listIDAndName.put(7,"Навальному");
        listIDAndName.put(8,"Навального");
        listIDAndName.put(9,"Собчак");
        return listIDAndName;
    }
/*
</ФЕЙК>
*/
}
