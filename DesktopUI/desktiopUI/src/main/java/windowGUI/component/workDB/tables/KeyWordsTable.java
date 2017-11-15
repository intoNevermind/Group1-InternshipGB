package windowGUI.component.workDB.tables;

import okhttp3.ResponseBody;
import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.restApi.PojoKeyWords;
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
        infoAllKeyWords();
    }
    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    private void infoAllKeyWords(){
        try {
            Response response = restApiForKeyWordsTable.getListAllKeyWords().execute();
            ArrayList<PojoKeyWords> list = (ArrayList<PojoKeyWords>) response.body();
            for (int i = 0; i < list.size(); i++) {
                listID.add(list.get(i).getId());
                listName.add(list.get(i).getName());
                listPersonID.add(list.get(i).getPersonId());
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

    public ArrayList<Integer> getListPersonID(){
        return listPersonID;
    }
    /*
     * </Получение>
     * */

    public LinkedHashMap<Integer, String> getListIDAndName() {
        for (int i = 0; i < listID.size(); i++) {
            listIDAndName.put(getListID().get(i),getListName().get(i));
        }
        return listIDAndName;
    }

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
}
