package windowGUI.options.workSQL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import retrofit2.Response;

public class PersonTable extends ConnectServer {
/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForPersonTable restApiForPersonTable = getRetrofit().create(RestApiForPersonTable.class);
    private static final LinkedHashMap<Integer,String> listIDAndNameReal = new LinkedHashMap<>();

    private ArrayList<Integer> getListIDReal() {
        try {
            Response<ArrayList<Integer>> response = restApiForPersonTable.getListIDFromPersonTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<Integer>();
        }
    }

    private ArrayList<String> getListNameReal(){
        try {
            Response<ArrayList<String>> response = restApiForPersonTable.getListNameFromPersonTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<String>();
        }
    }

    public ArrayList<Integer> getListActiveReal(){
        try {
            Response<ArrayList<Integer>> response = restApiForPersonTable.getListActiveFromPersonTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<Integer>();
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
