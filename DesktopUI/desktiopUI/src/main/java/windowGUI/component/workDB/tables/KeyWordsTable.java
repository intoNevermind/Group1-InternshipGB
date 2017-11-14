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
    public void addKeyWordReal(String keyWordName, int personID){
        try {
            Response<ResponseBody> response = restApiForKeyWordsTable.addKeyWord(keyWordName,personID).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delKeyWordReal(int keyWordID){
        try {
            Response<ResponseBody> response = restApiForKeyWordsTable.delKeyWord(keyWordID).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyKeyWordReal(int keyWordID, String keyWordName , int personID){
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

    private static KeyWordsTable instance;

    public static KeyWordsTable getInstance() {
        if(instance == null){
            instance = new KeyWordsTable();
        }
        return instance;
    }

    private KeyWordsTable() {
        for (int i = 1; i <= 9; i++) {
            listID.add(i);
        }

        listName.add("Путин");
        listName.add("Путинa");
        listName.add("Путинy");
        listName.add("Путиным");
        listName.add("Навальный");
        listName.add("Навальным");
        listName.add("Навальному");
        listName.add("Навального");
        listName.add("Собчак");

        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(3);

        listIDAndName.put(1,"Путин");
        listIDAndName.put(2,"Путина");
        listIDAndName.put(3,"Путину");
        listIDAndName.put(4,"Путиным");
        listIDAndName.put(5,"Навальный");
        listIDAndName.put(6,"Навальным");
        listIDAndName.put(7,"Навальному");
        listIDAndName.put(8,"Навального");
        listIDAndName.put(9,"Собчак");
    }

    public static void addKeyWord(String keyWordName, int personID){
    }

    public static void delKeyWord(int keyWordID){
    }

    public static void modifyKeyWord(int keyWordID, String keyWordName , int personID){

    }

    public static ArrayList<Integer> getListID(){
        return listID;
    }

    public static ArrayList<String> getListName(){
        return listName;
    }

    public static ArrayList<Integer> getListPersonID(){
        return listPersonID;
    }

    public static LinkedHashMap<Integer, String> getListIDAndName() {
        return listIDAndName;
    }
/*
</ФЕЙК>
*/
}
